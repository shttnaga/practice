package sample.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import sample.Service.ShopService;

@Controller
@RequestMapping("/AddNewItem")
public class AddNewItemController {

    // ファイル保存先のパス
    private static String UPLOADED_FOLDER = "src/main/resources/static/picture/";

    @Autowired
    private ShopService shopService;

    @GetMapping("/addItems") // 管理者ページの表示
    public String showAddNewItemPage() {
        return "addNewItem";
    }

    @PostMapping("/AddNewItem") // developerの新規商品情報追加
    public String addNewItem(
            @RequestParam("product_name") String product_name,
            @RequestParam("price") Integer price,
            @RequestParam("quantity") Integer quantity,
            @RequestParam("image") MultipartFile image,
            @RequestParam("new_image_name") String newImageName, // 新しい画像名の追加
            Model model) {

        // 商品名の重複チェック
        if (shopService.existCount(product_name)) {
            model.addAttribute("errorMessage", "商品名 " + product_name + " は既に存在します。");
            return "addNewItem"; // エラーがある場合は入力フォームを再表示
        } else {
            try {
                // 画像が選択されているか確認
                if (image.isEmpty()) {
                    model.addAttribute("errorMessage", "画像を選択してください。");
                    return "addNewItem"; // エラーがある場合は入力フォームを再表示
                }

                // 新しい画像名が指定されているか確認
                if (newImageName.isEmpty()) {
                    model.addAttribute("errorMessage", "新しい画像名を入力してください。");
                    return "addNewItem"; // エラーがある場合は入力フォームを再表示
                }

                // 画像を保存するパスを作成
                byte[] bytes = image.getBytes();
                String originalFilename = image.getOriginalFilename();
                Path path = Paths.get(UPLOADED_FOLDER + originalFilename);
                Files.write(path, bytes);

                // 新しい画像名で保存先を変更するパスを作成
                String newFileName = UPLOADED_FOLDER + newImageName + getFileExtension(originalFilename);
                Path newPath = Paths.get(newFileName);

                // 画像を移動またはリネーム
                Files.move(path, newPath);

                // 新商品を追加
                shopService.AddNewItem(product_name, price, quantity);

                return "endadd"; // 登録完了ページにリダイレクト
            } catch (IOException e) {
                model.addAttribute("errorMessage", "画像の処理中にエラーが発生しました。" + e.getMessage());
                return "error"; // エラーページを表示する
            } catch (Exception e) {
                model.addAttribute("errorMessage", "商品の登録に失敗しました。" + e.getMessage());
                return "error"; // エラーページを表示する
            }
        }
    }

    @PostMapping("/developer") // 管理者ページへ戻る
    public String goToDeveloperPage() {
        return "developer";
    }

    // ファイルの拡張子を取得するユーティリティメソッド
    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex);
    }
}

package sample.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sample.Service.ShopService;

@Controller
@RequestMapping("/AddNewItem")
public class AddNewItemController {

    @Autowired
    private ShopService shopService;

    @GetMapping("/addItems") // 管理者ページの表示
    public String showDeveloperPage() {
        return "developer";
    }

    @PostMapping("/AddNewItem") // developerの新規商品情報追加
    public String addNewItem(
            @RequestParam("product_name") String product_name,
            @RequestParam("price") Integer price,
            @RequestParam("quantity") Integer quantity,
            Model model) {
    	
    	if(shopService.existCount(product_name)) {
    		model.addAttribute("errorMessage","商品名 "+product_name+"は既に存在します。");
    		return "AddNewItem";
    	}else {

        try {
            // 登録サービスを呼び出して新規商品情報を登録する
            shopService.AddNewItem(product_name, price, quantity);
            return "endadd";
        } catch (IllegalArgumentException e) {
            // 商品名が重複している場合のエラー処理
            model.addAttribute("errorMessage", e.getMessage());
            return "error"; // エラーページを表示する
        }
    	}
    }

    @PostMapping("/developer") // 管理者ページへ戻る
    public String goToDeveloperPage() {
        return "developer";
    }
}

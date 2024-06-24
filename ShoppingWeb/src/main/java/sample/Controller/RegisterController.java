package sample.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import sample.Entity.Register;
import sample.Entity.ShoppingItem;
import sample.Repository.ShopRepository;
import sample.Service.RegisterService;

@Controller
@RequestMapping("/login")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private HttpServletRequest request;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // login.htmlに対応するテンプレート名を返す
    }

    @PostMapping("/doregister")
    public String authenticateUser(@RequestParam("id") String id,
                                   @RequestParam("password") String password,
                                   HttpSession session
                                   ) {
        // ユーザー認証の処理を実装（例えば、データベースからユーザー情報を検索して認証する）
   
        Optional<Register> user = registerService.login(id, password);

        if (user == null) {
            request.setAttribute("errorMessage", "ログインに失敗しました。IDまたはパスワードが正しくありません。");
            return "redirect:/login/login";
        }

        // ログイン成功時の処理
        session.setAttribute("user", user);

        try {
            // 商品情報リストを取得
            List<ShoppingItem> itemList = (List<ShoppingItem>) shopRepository.findAll();

            // 商品リストをセッションにセット
            session.setAttribute("itemList", itemList);

        } catch (Exception e) {
            e.printStackTrace();
            // エラーハンドリング（例外処理）
            request.setAttribute("errorMessage", "商品情報の取得に失敗しました。");
            return "redirect:/login/login";
        }

        // ショッピングページに遷移
        return "Shopping"; 
    }
}

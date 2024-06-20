package sample.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import sample.Entity.ShoppingItem;
import sample.Service.RegisterService;

@Controller
@RequestMapping("/purchaseController")
public class PurchaseController {

    @Autowired
    private RegisterService registerService;

    // 商品購入のPOSTリクエストを処理するメソッド
    @PostMapping("/processPurchase")
    public String processPurchase() {

        return "end";
    }

    // 購入完了画面を表示するGETリクエストを処理するメソッド
    @GetMapping("/purchaseComplete")
    public String showPurchaseComplete(Model model, HttpSession session) {
        // セッションからshoppingItemを取得してモデルに追加
        ShoppingItem shoppingItem = (ShoppingItem) session.getAttribute("shoppingItem");
        model.addAttribute("shoppingItem", shoppingItem);

        // 購入完了画面のビュー名を返す（例：purchaseComplete.jsp）
        return "PurchaseBuy";
    }
}

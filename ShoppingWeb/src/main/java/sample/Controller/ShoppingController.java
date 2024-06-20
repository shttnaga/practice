package sample.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/Shopping")
public class ShoppingController {

    // 他のコードは省略
	@Autowired
	HttpSession session;

    @PostMapping("/purchase")
    public String addToCart(@RequestParam("product_id") String productId,
                            @RequestParam("product_name") String productName,
                            @RequestParam("price") String price,
                            @RequestParam("quantity") String quantity,
                            Model model) {

        if (productId != null && !productId.isEmpty() && productName != null && !productName.isEmpty()) {
            // 商品の価格と数量から合計金額を計算
            int priceInt = Integer.parseInt(price);
            int quantityInt = Integer.parseInt(quantity);
            int totalPrice = priceInt * quantityInt;

            // モデルに必要な情報を追加
            model.addAttribute("product_id", productId);
            model.addAttribute("product_name", productName);
            model.addAttribute("price", price);
            model.addAttribute("quantity", quantity);
            model.addAttribute("totalPrice", totalPrice); // 合計金額を追加
            
            session.setAttribute("product_id", productId);
            session.setAttribute("product_name", productName);
            session.setAttribute("price", price);
            session.setAttribute("quantity", quantity);
            session.setAttribute("totalPrice", totalPrice);
            
            return "PurchaseBuy";
        } else {
            String errorMessage = "商品が選択されていません。";
            model.addAttribute("errormessage", errorMessage);
            return "purchase";
        }
    }
}

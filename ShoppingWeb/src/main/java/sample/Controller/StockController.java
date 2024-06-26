package sample.Controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import sample.Entity.ShoppingItem;
import sample.Service.PurchaseService;
import sample.Service.ShopService;

@Controller
@RequestMapping("/Stock")
public class StockController {
    
    @Autowired
    private ShopService shopService;

    @Autowired
    private PurchaseService purchaseService;
/*
    @GetMapping("/Stock")
    public String showStockPage() {
        return "Stock";
    }*/

    @PostMapping("/Stock")
    public String doAddProducts(HttpServletRequest request, HttpSession session, Model model) {

        // セッションから商品リストを取得する（実際の実装に合わせて調整する）
        Iterable<ShoppingItem> productsList = (Iterable<ShoppingItem>) session.getAttribute("itemList");

        // 商品の追加処理を行う
        HashMap<ShoppingItem, Integer> changeProductsMap = new HashMap<>();
        for (ShoppingItem p : productsList) {
        	 String strQuantity = request.getParameter( p.getProduct_name());
            if (strQuantity != null && !strQuantity.equals("0")) {
                Integer quantity = Integer.parseInt(strQuantity);
                changeProductsMap.put(p, quantity);
               
            }
        }
        
        // サービスを通じて数量を追加する
        Iterable<ShoppingItem> changeProductsList = shopService.addQuantityRegist(changeProductsMap);
        if (changeProductsList != null && !((List<ShoppingItem>) changeProductsList).isEmpty()) {
            model.addAttribute("changeProductsList", changeProductsList);
        } else {
            String errmsg = "商品を追加してください";
            model.addAttribute("errmsg", errmsg);
            return "stock"; // "addProducts" は適切な遷移先のビュー名に置き換えてください
        }

        return "stockComplete";
    }
}


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

    @GetMapping("/addItems")
    public String showDeveloperPage() {
        return "developer";
    }

    @PostMapping("/AddNewItem")
    public String addNewItem(
            @RequestParam("product_name") String productName,
            @RequestParam("price") Integer price,
            @RequestParam("quantity") Integer quantity,
            Model model) {

        // 商品が既に存在するか確認
        try {
            shopService.addNewItem(productName, price, quantity);
            model.addAttribute("success", "Product added successfully.");
        } catch (RuntimeException e) {
            model.addAttribute("error", "Failed to add product: " + e.getMessage());
            return "developer"; // エラーメッセージを表示してdeveloperページに戻る
        }

        return "endadd"; // 成功した場合はendaddページに遷移
    }

    @PostMapping("/developer")
    public String goToDeveloperPage() {
        return "developer";
    }
}

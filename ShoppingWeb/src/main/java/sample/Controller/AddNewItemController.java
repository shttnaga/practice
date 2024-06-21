package sample.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sample.Service.ShopService;




@Controller
@RequestMapping("/AddNewItem")
public class  AddNewItemController{
	
	 @Autowired
	    private ShopService ShopService;
		
		@GetMapping("/addItems")//管理者ページの表示
		public String showDeveloperPage() {
			return "developer";
		}
		
		@PostMapping("/AddNewItem")//developerの新規商品情報追加
		public String addNewItem(
								@RequestParam("product_name") String product_name, 
			  					@RequestParam("price") Integer price,
			  					@RequestParam("quantity") Integer quantity) {
		
			   // 登録サービスを呼び出して新規商品情報を登録する
			ShopService.AddNewItem(product_name, price, quantity);
			return "endadd";
		}
			  					
		
		@PostMapping("/developer")//管理者ページへ戻る
		public String goToDeveloperPage() {
			return "developer";
		}
}
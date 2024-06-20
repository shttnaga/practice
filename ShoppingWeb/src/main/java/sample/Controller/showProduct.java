package sample.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
;

@Controller
@RequestMapping("/show")
public class showProduct {

	@PostMapping("/show")
	public String  showProduct() {
		return "Shopping";
		// TODO 自動生成されたコンストラクター・スタブ
	}

}

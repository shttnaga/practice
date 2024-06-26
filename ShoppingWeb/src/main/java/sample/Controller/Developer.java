package sample.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("develop")
public class Developer {
	@GetMapping("showDevelop")
	public String developer() {
		return "developer";
	}
	@GetMapping("develop")
	public String develop() {
		return "AddNewItem";
	}
	@GetMapping("changeProducts")
	public String changeProducts() {
		return "stock";
	}

}

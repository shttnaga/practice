package sample.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import sample.Entity.Register;
import sample.Service.RegisterService;

@Controller
@RequestMapping("/register")
public class RegisterUserController {
    
    @Autowired
    private RegisterService registerService;
    
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }
    
    @PostMapping("/doregister")
    public String registerUser(@RequestParam("id") String id,
                               @RequestParam("password") String password,
                               @RequestParam("confirmPassword") String confirmPassword,
                               @RequestParam("email") String email,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {
    	
        // パスワードと確認用パスワードが一致するかチェック
        if (!password.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("errorMessage", "パスワードが一致しません。");
            return "redirect:/register";
        }

        // User オブジェクトにユーザー情報を設定
        Register reg = new Register();
        reg.setId(id);
        reg.setPassword(password);
        reg.setEmail(email);
        registerService.regist(reg);

        // セッションにユーザー情報を保存
        session.setAttribute("user", reg);

        // 登録成功ページにリダイレクト
        return "registerComplete";
    }
}

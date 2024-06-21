package service;


import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import sample.ShoppingWebApplication;
import sample.Entity.Register;
import sample.Repository.RegisterRepository;
import sample.Service.RegisterService;

@SpringBootTest(classes=ShoppingWebApplication.class)
public class RegisterServiceTest {
	
	@Autowired
	 RegisterService registerService;
	
	@MockBean
	RegisterRepository registerRepository;
	
	@Test
    public void registerServiceTest() {
        
        // テスト対象メソッドの呼び出し
        Optional<Register> register = registerService.login("takashi", "11111");
        if(register.isEmpty()){
        	System.out.print("registerに値ない");
        }if(register.isPresent()) {
        	System.out.print("registerに値あり");
        }

       
     
	}
	public void registerFindAll() {
		Iterable<Register> list=registerRepository.findAll();
		
	}
}
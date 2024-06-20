package sample.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.Entity.Register;
import sample.Repository.RegisterRepository;

@Service
public class RegisterService{
	
	@Autowired
	RegisterRepository registerRepository;

	public List<Register> findAll(){
		return (List<Register>) registerRepository.findAll();
	}
	public Register regist(Register register){
		return registerRepository.save(register);
	}
	public Register login(String id, String password) {
		Register register=registerRepository.login(id,password);
		return register;
	}
}

package sample.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.Entity.Register;
import sample.Repository.RegisterRepository;

@Service
public class RegisterService{
	
	@Autowired
	RegisterRepository registerRepository;

	public Iterable<Register> findAll(){
		return registerRepository.findAll();
	}
	public Register regist(Register register){
		return registerRepository.save(register);
	}
	public Optional<Register> login(String id, String password) {
		Optional<Register> register=registerRepository.login(id,password);
		return register;
	}
}

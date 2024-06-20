package sample.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.Entity.Purchase;
import sample.Repository.PurchaseRepository;

@Service
public class PurchaseService {
	
	@Autowired
	PurchaseRepository purchaseRepository;
	
	public List<Purchase>findAll(){
		return (List<Purchase>) purchaseRepository.findAll();
	}
	public Optional<Purchase> getById(Integer id){
		return purchaseRepository.findById(id);
	}
	}


package sample.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.Entity.ShoppingItem;
import sample.Repository.ShopRepository;

@Service
public class StockService {
    
    @Autowired
   private  ShopRepository ShopRepository;
    
    public List<ShoppingItem> findAll() {
        return (List<ShoppingItem>) StockRepository.findAll(); 
    }
}

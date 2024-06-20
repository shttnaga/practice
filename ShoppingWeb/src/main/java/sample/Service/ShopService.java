package sample.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.Entity.ShoppingItem;
import sample.Repository.ShopRepository;

@Service
public class ShopService {
    
    @Autowired
    ShopRepository shopRepository;
    
    public List<ShoppingItem> findAll() {
        return (List<ShoppingItem>) shopRepository.findAll(); // corrected the syntax
    }
}

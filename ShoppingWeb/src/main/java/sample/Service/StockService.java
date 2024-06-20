package sample.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.Entity.ShoppingItem;
import sample.Repository.StocksRepository;

@Service
public class StockService {
    
    @Autowired
   private  StockRepository stockRepository;
    
    public List<ShoppingItem> findAll() {
        return (List<ShoppingItem>) StockRepository.findAll(); 
    }
}

package sample.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.Entity.ShoppingItem;
import sample.Repository.ShopRepository;

@Service
public class AddNewItemService {
	@Autowired
	ShopRepository shopRepository;
	
    public ShoppingItem AddNewItem(String product_name, Integer price, Integer quantity) {
        // ShoppingItemエンティティを作成して情報をセット
        ShoppingItem newItem = new ShoppingItem();
        newItem.setProduct_name(product_name);
        newItem.setPrice(price);
        newItem.setQuantity(quantity);

        // データベースに保存
        return shopRepository.save(newItem);
    }
}
package sample.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.Entity.ShoppingItem;
import sample.Repository.ShopRepository;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    public List<ShoppingItem> findAll() {
        return (List<ShoppingItem>) shopRepository.findAll();
    }

    public ShoppingItem addNewItem(String productName, Integer price, Integer quantity) {
        // 既存の商品名チェック
        if (shopRepository.existsByProductName(productName)) {
            throw new RuntimeException("同じ商品名の商品が既に存在します。");
        }

        // ShoppingItemエンティティを作成して情報をセット
        ShoppingItem newItem = new ShoppingItem();
        newItem.setProduct_name(productName);
        newItem.setPrice(price);
        newItem.setQuantity(quantity);

        // データベースに保存
        return shopRepository.save(newItem);
    }

    public boolean existsByProductName(String productName) {
        return shopRepository.existsByProductName(productName);
    }
}






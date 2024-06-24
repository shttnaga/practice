package sample.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    public ShoppingItem AddNewItem(String product_name, Integer price, Integer quantity) {
        // 既存の商品名があるか確認する
        int existingCount = shopRepository.countProductsWithName(product_name);
        if (existingCount > 0) {
            throw new IllegalArgumentException("商品名 '" + product_name + "' は既に存在します。");
        }

        // 新しいShoppingItemエンティティを作成して情報をセット
        ShoppingItem newItem = new ShoppingItem();
        newItem.setProduct_name(product_name);
        newItem.setPrice(price);
        newItem.setQuantity(quantity);

        // データベースに保存
        try {
            return shopRepository.save(newItem);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("商品の追加中にエラーが発生しました。", e);
        }
    }
   
	//該当の購入情報から、在庫の量を増加する
	Iterable<ShoppingItem> addQuantityRegist(Map<ShoppingItem,Integer> purchaseMap) {
		List<ShoppingItem> purchaseProductsList=new ArrayList<ShoppingItem>();
		for(ShoppingItem p:purchaseMap.keySet()) {
			if(!(purchaseMap.get(p)==0)) {
				purchaseProductsList.add(p);
				Integer quantity=p.getQuantity();
				p.setQuantity(quantity+(purchaseMap.get(p)));
				updateByShoppingItem(p);
			}
		}
		return purchaseProductsList;
		
	}
	
	public ShoppingItem updateByShoppingItem(ShoppingItem Items) {
		Integer product_id=Items.getProduct_id();
		Integer quantity=Items.getQuantity();
		shopRepository.changeQuantity(quantity, product_id);
		return Items;
	}

}


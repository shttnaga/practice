package sample.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sample.Entity.ShoppingItem;
@Repository
public interface ShopRepository extends CrudRepository< ShoppingItem, Integer>{
	
}


package sample.Repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sample.Entity.ShoppingItem;
@Repository
public interface ShopRepository extends CrudRepository< ShoppingItem, Integer>{
	@Modifying
	@Query(value="UPDATE product SET quantity= :quantity WHERE product_id= :product_id")
	void changeQuantity(@Param("quantity") Integer quantity,@Param("product_id")Integer product_id);
    boolean existsByProductName(String productName);
}

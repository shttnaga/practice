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
	@Query("SELECT COUNT(*) FROM product WHERE product_name = :product_name")
	int countProductsWithName(@Param("product_name") String productName);
	
    @Modifying
    @Query(value = "INSERT INTO product (product_name, price, quantity) " +
                   "SELECT :productName, :price, :quantity " +
                   "WHERE NOT EXISTS (SELECT 1 FROM product WHERE product_name = :productName)")
    void insertIfNotExists(
            @Param("productName") String productName,
            @Param("price") Integer price,
            @Param("quantity") Integer quantity);

}



package sample.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sample.Entity.ShoppingItem;

@Repository
public interface AddNewItemRepository extends CrudRepository<ShoppingItem, Integer>{
}

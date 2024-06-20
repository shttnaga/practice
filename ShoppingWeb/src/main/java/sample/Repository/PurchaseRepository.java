package sample.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sample.Entity.Purchase;
@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Integer>{
}

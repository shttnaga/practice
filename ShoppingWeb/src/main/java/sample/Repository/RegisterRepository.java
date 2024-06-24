package sample.Repository;



import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sample.Entity.Register;
@Repository
public interface RegisterRepository extends CrudRepository<Register, String> {
	 @Query("SELECT * FROM register WHERE id = :id AND password = :password")	
	 Optional<Register> login(@Param("id")String id,@Param("password")String password);
}


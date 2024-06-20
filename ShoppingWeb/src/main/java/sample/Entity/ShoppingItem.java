package sample.Entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="product")
public class ShoppingItem implements Serializable{
	@Id
	private Integer  product_id;
	private String product_name;
	private Integer price;
	private  Integer quantity;

}

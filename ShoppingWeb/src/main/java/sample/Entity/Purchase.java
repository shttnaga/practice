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
@Table(name="purchase")
public class Purchase implements Serializable {
	@Id
	private Integer number;
    private Integer  userId;
    private Integer productId;
    private Integer purchase_quantity;
    private String product_name;
    private Integer totalprice;

}

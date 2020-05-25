package pl.eureka.product.data;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force=true)
@RequiredArgsConstructor
@ToString
@Table(name="product")
@Entity
public class Product {
	
	@Id
	@NotNull
	@Column(name = "CreditID")
	private final Long id;
	@NotNull
	private final String productName;
	@NotNull
	private final Long value;
}

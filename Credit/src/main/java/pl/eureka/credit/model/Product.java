package pl.eureka.credit.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force=true)
@AllArgsConstructor
@ToString
public class Product {
	@ToString.Exclude
	private Long id;
	private final String productName;
	private final Long value;
}

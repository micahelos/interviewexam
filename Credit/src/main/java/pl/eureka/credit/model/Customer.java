package pl.eureka.credit.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE,force=true)
@RequiredArgsConstructor
@ToString
public class Customer {
	@ToString.Exclude
	private Long id;
	private final String firstName;
	private final String surname;
	private final String pesel;
}

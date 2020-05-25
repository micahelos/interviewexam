package pl.eureka.customer.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.pl.PESEL;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE,force=true)
@RequiredArgsConstructor
@Entity
@Table(name="customer")
@ToString
public class Customer {

	@Id
	@NotNull
	@Column(name="CreditID")
	private final Long id;
	@NotNull
	private final String firstName;
	@NotNull
	private final String surname;
	@Column(length = 11)
	@NotNull
	@PESEL
	private final String pesel;
}

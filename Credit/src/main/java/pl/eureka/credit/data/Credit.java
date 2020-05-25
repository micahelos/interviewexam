package pl.eureka.credit.data;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force=true)
@Entity
@Table(name="credit")
@ToString
public class Credit {

	@Id
	@ToString.Exclude
	@JsonIgnore
	@GeneratedValue(strategy=GenerationType.AUTO)
	@NotNull
	@Column(name = "id")
	private Long id;
	@NotNull
	private final String creditName;

}

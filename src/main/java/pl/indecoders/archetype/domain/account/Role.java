package pl.indecoders.archetype.domain.account;

import static javax.persistence.EnumType.STRING;
import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;
import static org.joda.time.DateTimeZone.UTC;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.joda.time.DateTime;

import pl.indecoders.archetype.domain.AbstractEntity;

/**
 * The Class Role.
 * @author Mateusz
 */
@Entity
@Table(name = "role")
public class Role extends AbstractEntity {

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "Role_SEQUENCE", sequenceName = "role_seq")
	@GeneratedValue(generator = "Role_SEQUENCE")
	private Long id;
	
	@Column(name = "role_name")
	@Enumerated(STRING)
	private RoleType role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoleType getRole() {
		return role;
	}

	public void setRole(RoleType role) {
		this.role = role;
	}

	public Role(RoleType role) {
		super(DateTime.now(UTC));
		this.role = role;
	}

	public Role() {
		super(DateTime.now(UTC));
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, SHORT_PREFIX_STYLE);
	}
}

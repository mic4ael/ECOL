package pl.indecoders.archetype.domain.account;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.FetchType.EAGER;
import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;
import static org.joda.time.DateTimeZone.UTC;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import pl.indecoders.archetype.domain.AbstractEntity;

@Entity
@Table(name = "account")
public class Account extends AbstractEntity {

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "Account_SEQUENCE", sequenceName = "account_seq")
	@GeneratedValue(generator = "Account_SEQUENCE")
	private Long id;

	@Column(unique = true, name = "identifier")
	private String identifier;
	
	@Column(unique = true, name = "email")
	private String email;
	
	@Column(name = "token")
	private String token;
	
	@Column(name = "token_creation_date")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime tokenCreationDate;

	@JsonIgnore
	@Column(name = "password")
	private String password;

	@ManyToMany(cascade = { PERSIST, MERGE, REMOVE }, fetch = EAGER)
	private List<Role> roles;

    @OneToOne(fetch = EAGER, cascade = ALL)
    @JoinColumn(name = "account_profile_id")
    private Profile profile;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public DateTime getTokenCreationDate() {
		return tokenCreationDate;
	}

	public void setTokenCreationDate(DateTime tokenCreationDate) {
		this.tokenCreationDate = tokenCreationDate;
	}
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Account() {
		super(DateTime.now(UTC));
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, SHORT_PREFIX_STYLE);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Role> getRoles() {
		return roles;
	}
}

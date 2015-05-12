package pl.indecoders.archetype.domain.activity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import pl.indecoders.archetype.domain.account.Account;

@Entity
@Table(name = "activities")
public class Activity {
	
	@Id
	@Column
	@SequenceGenerator(name = "Activity_SEQUENCE", sequenceName = "activity_seq")
	@GeneratedValue(generator = "Activity_SEQUENCE")
	private Integer id;
	
	@Column
	private String activity;
	
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public Account getUser() {
		return account;
	}

	public void setUser(Account user) {
		this.account = user;
	}
}

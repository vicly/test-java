package vic.test.jpa.relation.one2one;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import vic.test.jpa.BaseEntity;

@Entity
public class Account extends BaseEntity {
	
	@Column
	private String accountNumber;
	
	//
	// unidirectional
	//
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "account_name_id", nullable = false)
	//@PrimaryKeyJoinColumn
	private AccountName name;
	
	//
	// bidirectional
	//
	@OneToOne
	//@JoinColumn(name = "profile_id", nullable = false)
	@PrimaryKeyJoinColumn
	private AccountProfile profile;
	
	public Account() {
		
	}
	
	public String getAccountNumber() {
		return this.accountNumber;
	}
	
	public Account setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
		return this;
	}
	
	public AccountName getName() {
		return name;
	}

	public Account setName(AccountName name) {
		this.name = name;
		return this;
	}
	
	public AccountProfile getProfile() {
		return this.profile;
	}
	
	public Account setProfile(AccountProfile profile) {
		this.profile = profile;
		return this;
	}
	
	public String toString() {
		return "Account {id=" + getId() + ", accountNumber=" + this.accountNumber + "}";
	}

	
}

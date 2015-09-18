package vic.test.jpa.relation.one2one;

import javax.persistence.Column;
import javax.persistence.Entity;

import vic.test.jpa.BaseEntity;

@Entity
public class AccountName extends BaseEntity {

	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	public AccountName() {}

	public String getFirstName() {
		return firstName;
	}

	public AccountName setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public AccountName setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}
	
	public String toString() {
		return "AccountName {id=" + getId() + ", firstName=" + this.firstName + ", lastName=" + this.lastName + "}";
	}
	
}

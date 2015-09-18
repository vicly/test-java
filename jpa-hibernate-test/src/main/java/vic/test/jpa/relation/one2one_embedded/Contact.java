package vic.test.jpa.relation.one2one_embedded;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import vic.test.jpa.BaseEntity;

@Entity
public class Contact extends BaseEntity {

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="zip", column=@Column(name="home_zip")),
		@AttributeOverride(name="address", column=@Column(name="home_address"))
	})
	private Address homeAddress;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="zip", column=@Column(name="work_zip")),
		@AttributeOverride(name="address", column=@Column(name="work_address"))
	})
	private Address workAddress;

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public Address getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(Address workAddress) {
		this.workAddress = workAddress;
	}

}

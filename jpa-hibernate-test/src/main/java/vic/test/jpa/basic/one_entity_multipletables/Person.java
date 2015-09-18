package vic.test.jpa.basic.one_entity_multipletables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import vic.test.jpa.BaseEntity;

import com.google.common.base.MoreObjects;

/*
 * assume the secondary table exist, otherwise, runtime error
 */
@Entity
@Table(name = "person")
//@SecondaryTables({
//	@SecondaryTable(name = "person_contact", pkJoinColumns = { @PrimaryKeyJoinColumn(name = "id") }),
//	@SecondaryTable(name = "person_address", pkJoinColumns = { @PrimaryKeyJoinColumn(name = "id") })
//})
public class Person extends BaseEntity {
	
	@Column
	private String name;
	
//	@Column(table = "person_contract")
	private String contact;
	
//	@Column(table = "person_address")
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("name", this.name)
				.add("contact", this.contact)
				.add("address", this.address)
				.toString();
	}
}

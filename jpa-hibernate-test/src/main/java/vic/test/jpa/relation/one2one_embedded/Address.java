package vic.test.jpa.relation.one2one_embedded;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.google.common.base.MoreObjects;

@Embeddable
public class Address {
	@Column
	private String zip;
	@Column
	private String address;
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
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
				.add("zip", zip)
				.add("address", address)
				.toString();
	}
}

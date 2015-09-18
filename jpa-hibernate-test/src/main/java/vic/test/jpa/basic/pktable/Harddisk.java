package vic.test.jpa.basic.pktable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

import com.google.common.base.MoreObjects;

@Entity
public class Harddisk {

	@TableGenerator(name = "harddisk_generator", 
			table = "generated_keys", 
			pkColumnName = "pk_name",
			valueColumnName = "pk_value",
			pkColumnValue = "harddisk_id",
			allocationSize = 1) // increase by 1
	@Id
	private int id;
	
	@Column
	private String name;
	
	public Harddisk() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id", id)
				.add("name", name)
				.toString();
	}
}

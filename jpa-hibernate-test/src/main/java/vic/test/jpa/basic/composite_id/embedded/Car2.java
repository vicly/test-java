package vic.test.jpa.basic.composite_id.embedded;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.google.common.base.MoreObjects;

@Entity
public class Car2 {

	@EmbeddedId
	private CarPK2 pk;
 
    @Column
    private String name; // Yes, some people like to give name to theirs cars.
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
    
    public CarPK2 getPk() {
    	return this.pk;
    }
    
    public void setPk(CarPK2 pk) {
    	this.pk = pk;
    }
    
    @Override
    public String toString() {
    	return MoreObjects.toStringHelper(this)
    			.add("chassisSerialNumber", pk.getChassisSerialNumber())
    			.add("engineSerialNumber", pk.getEngineSerialNumber())
    			.add("name", this.name)
    			.toString();
    }
}
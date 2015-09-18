package vic.test.jpa.basic.composite_id.idclass;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;

@Entity
@Table(name="CAR")
@IdClass(value=CarPK.class)
public class Car {
 
    @Id
    private String chassisSerialNumber;
    @Id
    private String engineSerialNumber;
 
    @Column
    private String name; // Yes, some people like to give name to theirs cars.
 
    public String getChassisSerialNumber() {
        return chassisSerialNumber;
    }
 
    public void setChassisSerialNumber(String chassisSerialNumber) {
        this.chassisSerialNumber = chassisSerialNumber;
    }
 
    public String getEngineSerialNumber() {
        return engineSerialNumber;
    }
 
    public void setEngineSerialNumber(String engineSerialNumber) {
        this.engineSerialNumber = engineSerialNumber;
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
    			.add("chassisSerialNumber", chassisSerialNumber)
    			.add("engineSerialNumber", engineSerialNumber)
    			.add("name", this.name)
    			.toString();
    }
}
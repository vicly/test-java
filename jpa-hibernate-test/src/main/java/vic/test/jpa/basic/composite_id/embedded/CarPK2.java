package vic.test.jpa.basic.composite_id.embedded;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CarPK2 implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column
	private String chassisSerialNumber;
	
	@Column
	private String engineSerialNumber;

	public CarPK2() {
		// Your class must have a no-arq constructor
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CarPK2) {
			CarPK2 carPk = (CarPK2) obj;

			if (!carPk.getChassisSerialNumber().equals(chassisSerialNumber)) {
				return false;
			}

			if (!carPk.getEngineSerialNumber().equals(engineSerialNumber)) {
				return false;
			}

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return chassisSerialNumber.hashCode() + engineSerialNumber.hashCode();
	}

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
}

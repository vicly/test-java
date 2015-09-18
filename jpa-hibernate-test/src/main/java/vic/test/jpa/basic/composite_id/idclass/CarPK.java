package vic.test.jpa.basic.composite_id.idclass;

import java.io.Serializable;

public class CarPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private String chassisSerialNumber;
	private String engineSerialNumber;

	public CarPK() {
		// Your class must have a no-arq constructor
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CarPK) {
			CarPK carPk = (CarPK) obj;

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

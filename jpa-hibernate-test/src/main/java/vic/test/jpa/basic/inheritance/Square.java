package vic.test.jpa.basic.inheritance;

import javax.persistence.Entity;

@Entity
public class Square extends Shape {

	private int sideLength;

	public int getSideLength() {
		return sideLength;
	}

	public void setSideLength(int sideLength) {
		this.sideLength = sideLength;
	}
	
}

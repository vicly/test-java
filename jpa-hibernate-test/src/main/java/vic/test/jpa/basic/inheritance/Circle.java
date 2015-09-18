package vic.test.jpa.basic.inheritance;

import javax.persistence.Entity;

@Entity
public class Circle extends Shape {

	private int radius;

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
	
}

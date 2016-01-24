package vic.test.jersey;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Task Model
 */
@XmlRootElement
public class Task {
	private String id;
	private String name;

	public Task() {}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

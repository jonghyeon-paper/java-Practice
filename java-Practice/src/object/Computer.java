package object;

import java.util.Map;

public class Computer {

	private String name;
	private String attribute;
	private String value;
	private Map<String, Object> attributes;
	
	public Computer(String name, String attribute, String value) {
		this.name = name;
		this.attribute = attribute;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAttribute() {
		return attribute;
	}
	
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	@Override
	public String toString() {
		return "Computer [name=" + name + ", attribute=" + attribute + ", value=" + value + ", attributes=" + attributes
				+ "]";
	}
}

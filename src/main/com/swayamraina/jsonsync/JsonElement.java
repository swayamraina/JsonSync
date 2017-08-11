package main.com.swayamraina.jsonsync;

public class JsonElement {
	
	private String element;
	private int type;
	
	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public JsonElement(String element, int type) {
		this.setElement(element);
		this.setType(type);
	}
}



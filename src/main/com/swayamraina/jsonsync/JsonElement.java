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
	
	@Override
	public boolean equals(Object other) {
	    if(other == this) return true;
	    if(!(other instanceof JsonElement)) return false;
	    JsonElement tempJsonElement = (JsonElement) other;
	    return (tempJsonElement.getElement().equals(this.getElement()) && tempJsonElement.getType()==this.getType()) ? true : false;
	}
}



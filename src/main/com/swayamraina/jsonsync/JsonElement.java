package main.com.swayamraina.jsonsync;

public class JsonElement {
	
	private String elementPath;
	private int action;

	public String getElementPath() {
		return elementPath;
	}

	public void setElementPath(String elementPath) {
		this.elementPath = elementPath;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}
	
	public JsonElement(String elementPath, int action) {
		this.setElementPath(elementPath);
		this.setAction(action);
	}
	
	@Override
	public boolean equals(Object other) {
	    if(other == this) return true;
	    if(!(other instanceof JsonElement)) return false;
	    JsonElement tempJsonElement = (JsonElement) other;
	    return (tempJsonElement.getElementPath().equals(this.getElementPath()) && tempJsonElement.getAction()==this.getAction()) ? true : false;
	}
}



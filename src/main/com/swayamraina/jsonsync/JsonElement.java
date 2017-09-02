package main.com.swayamraina.jsonsync;

public class JsonElement {
	
	private static final String EMPTY = "";
	
	private String elementPath;
	private int action;
	private String dataType;

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
	
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	public JsonElement(String elementPath, int action, String datatype) {
		this.setElementPath(elementPath);
		this.setAction(action);
		this.setDataType(datatype);
	}
	
	public JsonElement(String elementPath, int action) {
		this(elementPath, action, EMPTY);
	}
	
	@Override
	public boolean equals(Object other) {
	    if(other == this) return true;
	    if(!(other instanceof JsonElement)) return false;
	    JsonElement tempJsonElement = (JsonElement) other;
	    return (tempJsonElement.getElementPath().equals(this.getElementPath()) && 
	    		tempJsonElement.getAction()==this.getAction() &&
	    		tempJsonElement.getDataType().equals(this.getDataType())) ? true : false;
	}

}



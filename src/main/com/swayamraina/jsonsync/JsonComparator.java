package main.com.swayamraina.jsonsync;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import main.com.swayam.json.JsonObject;

public class JsonComparator {
	
	private static final int INSERT = 1;
	private static final int DELETE = 2;
	
	private static final String DELIMITER = ">";
	
	private static final String EMPTY_STRING = "";
	private static final Set<String> EMPTY_SET = new HashSet<>();
	private static final Pair<String, Pair<JsonObject, JsonObject>> EMPTY_PAIR = new Pair<>(EMPTY_STRING, new Pair<>(null,null));
	
	private Queue<Pair<String, Pair<JsonObject, JsonObject>>> keyComparisonQueue;
	public List<JsonElement> updatedKeys;
	private StringBuilder basePath;
	
	
	public JsonComparator() {
		this.keyComparisonQueue = new LinkedList<>();
		this.updatedKeys = new ArrayList<>();
		this.basePath = new StringBuilder();
	}
	
	
	/**
	 * This method compares keys of a particular level and collects
	 * keys that are needed to be updated.
	 * 
	 * The updates collected are to be done on data files
	 * 
	 * @param oldTemplate
	 * @param newTemplate
	 * @return
	 */
	private List<JsonElement> compareLevelKeys(Set<String> oldTemplate, Set<String> newTemplate) {
		List<JsonElement> diffElements = new ArrayList<>();
		for(String oldTemplateKey : oldTemplate) {
			if(!newTemplate.contains(oldTemplateKey)) {
				diffElements.add(new JsonElement(createElementPath(oldTemplateKey), DELETE));
			}
		}
		for(String newTemplateKey : newTemplate) {
			if(!oldTemplate.contains(newTemplateKey)) {
				diffElements.add(new JsonElement(createElementPath(newTemplateKey), INSERT));
			}
		}
		return diffElements;
	}
	
	
	/**
	 * This method creates a list of keys under a particular root node
	 * to be compared with keys in new template
	 * 
	 * @param rootElement
	 * @return
	 */
	private Set<String> getLevelKeys(JsonObject rootElement) {
		return (rootElement!=null) ? rootElement.getJson().keySet() : EMPTY_SET; 
	}
	
	
	/**
	 * This method creates a list of keys that have Json as corresponding value
	 * 
	 * @param rootElement
	 * @return
	 */
	private Set<String> getMultiLevelKeys(JsonObject rootElement) {
		Set<String> multiLevelKeys = new HashSet<>();
		for(Map.Entry<String, Object> entry : rootElement.getJson().entrySet()) {
			if(entry.getValue() instanceof JsonObject) {
				multiLevelKeys.add(entry.getKey());
			}
		}
		return multiLevelKeys;
	}
	
	
	/**
	 * Utility method to check if a given key is multi-level or not
	 * 
	 * @param json
	 * @param key
	 * @return
	 */
	private boolean isMultiLevelKey(JsonObject json, String key) {
		return (json.get(key) instanceof JsonObject) ? true : false;
	}
    

	/**
	 * This method receives a JSON key and returns the path to the key
	 * 
	 * @param key
	 * @return
	 */
    	private String createElementPath(String key) {
    		return (basePath.length() > 0) ? (basePath.toString() + DELIMITER + key) : key;
    	}
    
    
        /**
     	 * This method updates the base path of the JSON object
     	 * 
     	 */
    	private void updateBasePath(String path) {
    		basePath.append(DELIMITER);
    		basePath.append(path);
    	}
    
    
    	/**
     	 * This method accepts the path to the key in JSON and
     	 * returns the data type of the value
     	 * 
     	 * @param json
     	 * @param path
     	 * @return
     	 */
    	private static String getDataType(final JsonObject json, final String path, String key) {
    		String[] steps = path.split(DELIMITER);
    		JsonObject value = json;
    		for(int i=1; i<steps.length; i++) {
	    		value = (JsonObject)value.get(steps[i]);
    		}
    		return value.get(key).getClass().getSimpleName();
    	}
    
}


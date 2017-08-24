package main.com.swayamraina.jsonsync;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import main.com.swayam.json.JsonObject;

public class JsonComparator {
	
	private static final int INSERT = 1;
	private static final int DELETE = 2;
	
	
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
	private static List<JsonElement> compareLevelKeys(Set<String> oldTemplate, Set<String> newTemplate) {
		List<JsonElement> diffElements = new ArrayList<>();
		for(String oldTemplateKey : oldTemplate) {
			if(!newTemplate.contains(oldTemplateKey)) {
				diffElements.add(new JsonElement(oldTemplateKey, DELETE));
			}
		}
		for(String newTemplateKey : newTemplate) {
			if(!oldTemplate.contains(newTemplateKey)) {
				diffElements.add(new JsonElement(newTemplateKey, INSERT));
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
	private static Set<String> getLevelKeys(JsonObject rootElement) {
		return rootElement.getJson().keySet();
	}
	
	
	/**
	 * This method creates a list of keys that have Json as corresponding value
	 * 
	 * @param rootElement
	 * @return
	 */
	private static Set<String> getMultiLevelKeys(JsonObject rootElement) {
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
	private static boolean isMultiLevelKey(JsonObject json, String key) {
		return (json.get(key) instanceof JsonObject) ? true : false;
	}

}


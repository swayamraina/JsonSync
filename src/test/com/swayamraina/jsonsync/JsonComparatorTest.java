package test.com.swayamraina.jsonsync;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import main.com.swayam.json.JsonObject;
import main.com.swayam.json.JsonTokenizer;
import main.com.swayamraina.jsonsync.JsonComparator;
import main.com.swayamraina.jsonsync.JsonElement;

public class JsonComparatorTest {

	@Test
	public void testCompareLevelKeys() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Set<String> oldT = new HashSet<>();
		oldT.add("id");
		oldT.add("first name");
		oldT.add("last name");
		oldT.add("dob");
		oldT.add("mobile");
		
		Set<String> newT = new HashSet<>();
		newT.add("osis id");
		newT.add("full name");
		newT.add("dob");
		newT.add("mobile no");
		
		List<JsonElement> expected = new ArrayList<>();
		expected.add(new JsonElement("id", 2));
		expected.add(new JsonElement("first name", 2));
		expected.add(new JsonElement("last name", 2));
		expected.add(new JsonElement("mobile", 2));
		expected.add(new JsonElement("osis id", 1));
		expected.add(new JsonElement("full name", 1));
		expected.add(new JsonElement("mobile no", 1));
		
		List<JsonElement> actual = (List<JsonElement>) TestingUtility.getPrivateMethod("compareLevelKeys", Set.class, Set.class).invoke(null, oldT, newT);
		assertEquals(actual.size(), expected.size());
		TestingUtility.assertArrayEqual(expected, actual);
	}
	
	@Test
	public void testGetLevelKeys() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String jsonText = "{\"id\":\"12345\",\"name\":{\"first\":\"swayam\",\"last\":\"raina\"}}";
		JsonObject json = new JsonTokenizer().tokenize(jsonText);
		List<String> actual = new ArrayList<>();
		actual.add("first");
		actual.add("last");
		List<String> expected = (List<String>) TestingUtility.getPrivateMethod("getLevelKeys", JsonObject.class).invoke(null, json.get("name"));
		assertEquals(expected.size(), actual.size());
		TestingUtility.assertArrayEqual(expected, actual);
	}
	
}


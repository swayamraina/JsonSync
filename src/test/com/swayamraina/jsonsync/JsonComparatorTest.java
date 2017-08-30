package test.com.swayamraina.jsonsync;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
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
	
	private static final int INSERT = 1;
	private static final int DELETE = 2;

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
		expected.add(new JsonElement("id", DELETE));
		expected.add(new JsonElement("first name", DELETE));
		expected.add(new JsonElement("last name", DELETE));
		expected.add(new JsonElement("mobile", DELETE));
		expected.add(new JsonElement("osis id", INSERT));
		expected.add(new JsonElement("full name", INSERT));
		expected.add(new JsonElement("mobile no", INSERT));
		
		JsonComparator comparator = new JsonComparator();
		
		List<JsonElement> actual = (List<JsonElement>) TestingUtility.getPrivateMethod("compareLevelKeys", Set.class, Set.class).invoke(comparator, oldT, newT);
		assertEquals(actual.size(), expected.size());
		TestingUtility.assertArrayEqual(expected, actual);
	}
	
	@Test
	public void testGetLevelKeys1() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String jsonText = "{\"id\":\"12345\",\"name\":{\"first\":\"swayam\",\"last\":\"raina\"}}";
		JsonObject json = new JsonTokenizer().tokenize(jsonText);
		Set<String> actual = new HashSet<>();
		actual.add("first");
		actual.add("last");
		JsonComparator comparator = new JsonComparator();
		Set<String> expected = (Set<String>) TestingUtility.getPrivateMethod("getLevelKeys", JsonObject.class).invoke(comparator, json.get("name"));
		assertEquals(expected.size(), actual.size());
		TestingUtility.assertSetEqual(expected, actual);
	}
	
	@Test
	public void testGetLevelKeys2() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String jsonText = "{\"id\":\"12345\",\"name\":{\"first\":\"swayam\",\"last\":\"raina\"},\"level\":\"advanced\"}";
		JsonObject json = new JsonTokenizer().tokenize(jsonText);
		Set<String> actual = new HashSet<>();
		actual.add("id");
		actual.add("name");
		actual.add("level");
		JsonComparator comparator = new JsonComparator();
		Set<String> expected = (Set<String>) TestingUtility.getPrivateMethod("getLevelKeys", JsonObject.class).invoke(comparator, json);
		assertEquals(expected.size(), actual.size());
		TestingUtility.assertSetEqual(expected, actual);
	}
	
	@Test
	public void testGetMultiLevelKey() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String jsonText = "{\"id\":\"12345\",\"name\":{\"first\":\"swayam\",\"last\":\"raina\"},\"levels\":{\"beginner\":\"1\",\"advanced\":\"2\"}}";
		JsonObject json = new JsonTokenizer().tokenize(jsonText);
		Set<String> actual = new HashSet<>();
		actual.add("name");
		actual.add("levels");
		JsonComparator comparator = new JsonComparator();
		Set<String> expected = (Set<String>) TestingUtility.getPrivateMethod("getMultiLevelKeys", JsonObject.class).invoke(comparator, json);
		assertEquals(expected.size(), actual.size());
		TestingUtility.assertSetEqual(expected, actual);
	}
	
	@Test
	public void testIsMultiLevelKey() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String jsonText = "{\"uuid\":\"12345\",\"name\":{\"first\":\"swayam\",\"last\":\"raina\"},\"levels\":{\"beginner\":\"1\",\"advanced\":\"2\"}}";
		JsonObject json = new JsonTokenizer().tokenize(jsonText);
		JsonComparator comparator = new JsonComparator();
		boolean expected1 = (boolean) TestingUtility.getPrivateMethod("isMultiLevelKey", JsonObject.class, String.class).invoke(comparator, json, "name");
		assertEquals(expected1, true);
		
		boolean expected2 = (boolean) TestingUtility.getPrivateMethod("isMultiLevelKey", JsonObject.class, String.class).invoke(comparator, json, "uuid");
		assertEquals(expected2, false);
	}
	
}


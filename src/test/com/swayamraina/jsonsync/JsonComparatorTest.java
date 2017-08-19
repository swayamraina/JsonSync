package test.com.swayamraina.jsonsync;

import static org.junit.Assert.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import main.com.swayamraina.jsonsync.JsonComparator;
import main.com.swayamraina.jsonsync.JsonElement;

public class JsonComparatorTest {

	@Test
	public void testCompareLevelObjectHelper() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
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
		
		List<JsonElement> diff = new ArrayList<>();
		diff.add(new JsonElement("id", 2));
		diff.add(new JsonElement("first name", 2));
		diff.add(new JsonElement("last name", 2));
		diff.add(new JsonElement("mobile", 2));
		diff.add(new JsonElement("osis id", 1));
		diff.add(new JsonElement("full name", 1));
		diff.add(new JsonElement("mobile no", 1));
		
		List<JsonElement> obtained = (List<JsonElement>) getPrivateMethod("compareLevelObjectHelper").invoke(null, oldT, newT);
		Assert.assertEquals(obtained.size(), diff.size());
		for(int i=0; i<obtained.size(); i++) {
			if(!diff.contains(obtained.get(i))) {
				fail("extra element");
			}
		}
		for(int i=0; i<obtained.size(); i++) {
			if(!obtained.contains(diff.get(i))) {
				fail("extra element");
			}
		}
	}
	
	private Method getPrivateMethod(String name) throws NoSuchMethodException, SecurityException {
		Method method = JsonComparator.class.getDeclaredMethod(name, Set.class, Set.class);
		method.setAccessible(true);
		return method;
	}
}


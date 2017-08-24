package test.com.swayamraina.jsonsync;

import static org.junit.Assert.fail;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

import main.com.swayamraina.jsonsync.JsonComparator;

public class TestingUtility {
	
	public static Method getPrivateMethod(String name, Class... classes) throws NoSuchMethodException, SecurityException {
		Method method = JsonComparator.class.getDeclaredMethod(name, classes);
		method.setAccessible(true);
		return method;
	}
	
	public static <T> void assertArrayEqual(List<T> expected, List<T> actual) {
		for(int i=0; i<expected.size(); i++) {
			if(!actual.contains(expected.get(i))) {
				fail("extra element");
			}
		}
		for(int i=0; i<expected.size(); i++) {
			if(!expected.contains(actual.get(i))) {
				fail("extra element");
			}
		}
	}
	
	public static <T> void assertSetEqual(Set<T> expected, Set<T> actual) {
		if(!actual.containsAll(expected) || !expected.containsAll(actual)) {
			fail("extra element");
		}
	}
	
}


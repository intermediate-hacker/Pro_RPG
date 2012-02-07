package pro;

import java.util.Map;
import java.util.Map.Entry;

/*
 * Utility Class, contains helpful static methods that
 * are irrelevant in other classes.
 */
public class Utility {
	
	public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
	    for (Entry<T, E> entry : map.entrySet()) {
	        if (value.equals(entry.getValue())) {
	            return entry.getKey();
	        }
	    }
	    return null;
	}
}

package org.dyndns.warenix.util.array;

public class ArrayUtil {
	public static String join(String[] array, String seperator) {
		String string = array[0];
		for (int i = 1; i < array.length; ++i) {
			string += seperator + array[i];
		}
		return string;
	}

}

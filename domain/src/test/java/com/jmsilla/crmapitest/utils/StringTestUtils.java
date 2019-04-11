package com.jmsilla.crmapitest.utils;

import java.util.stream.*;

public class StringTestUtils {
	public static String generateStringOfLength(int length) {
		Stream<String> stream = Stream.generate(() -> "1");
		return stream.limit(length).collect(Collectors.joining());
	}
}

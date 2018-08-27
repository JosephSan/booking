package com.smarthost.booking;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class FileUtilsTest {

	@Test
	public void testOpenFile() {
		String content = FileContentUtils.readFile("src/main/resources/data.txt");
		Assert.assertEquals("[  23,  45,  155,  374,  22,  99,  100,  101,  115,  209,]", content);

	}

	@Test
	public void testParseContent() {
		String testString = "[  23,  45,  155,]";
		List<Integer> Integers = FileContentUtils.parseFile(testString);
		Assert.assertEquals(Arrays.asList(Integer.valueOf(23), Integer.valueOf(45), Integer.valueOf(155)), Integers);

	}

}

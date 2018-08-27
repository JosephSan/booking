package com.smarthost.booking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

public class FileContentUtils {

	public static String readFile(String fileName) {
		String content = "";
		String line;
		BufferedReader in;

		try {
			in = new BufferedReader(new FileReader(fileName));
			line = in.readLine();

			while (line != null) {
				content += line;
				line = in.readLine();
			}

			System.out.println(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return content;
	}

	public static List<Integer> parseFile(final String content) {

		String reworkedContent = StringUtils.replace(content, "[", "");
		reworkedContent = StringUtils.replace(reworkedContent, "]", "");
		List<String> stringValues = Arrays.asList(reworkedContent.split(","));
		return stringValues.stream().map(s -> s.replaceAll(" ", "")).map(Integer::parseInt)
				.collect(Collectors.toList());
	}
}

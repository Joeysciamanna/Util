package ch.g_7.util.stream.test;

import java.io.IOException;

import org.junit.Test;

import ch.g_7.util.stream.StreamUtil;

public class StreamUtilTest {

	@Test
	public void getStringifyParserTest() {
		try {
			StreamUtil.writeString("test", "asd", false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

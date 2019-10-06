package ch.g_7.util.stream.test;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import ch.g_7.util.parse.IDestringifyer;
import ch.g_7.util.parse.SerializationParserUtil;
import ch.g_7.util.parse.Stringifyable;
import ch.g_7.util.reflection.ClassUtil;
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

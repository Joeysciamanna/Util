package ch.g_7.util.parse.test;

import org.junit.Assert;
import org.junit.Test;

import ch.g_7.util.parse.IDestringifyer;
import ch.g_7.util.parse.SerializationParserUtil;
import ch.g_7.util.parse.Stringifyable;
import ch.g_7.util.reflection.ClassUtil;

public class SerializationParserUtilTest {

	
	@Test
	public void getStringifyParserTest() {
		Stringifyable stringifyable = ClassUtil.implemment(Stringifyable.class, (i) -> "this string identifiese the stringifyable");
		SerializationParserUtil.setDestringifyer(ClassUtil.implemment(IDestringifyer.class, (i) -> stringifyable));
		
		String value = parseString(Stringifyable.class, stringifyable);
		Stringifyable stringifyable2 = parseObject(Stringifyable.class, value);
		
		Assert.assertEquals(stringifyable.stringify(), stringifyable2.stringify());
	}

	@Test
	public void getPrimitiveParserTest() {
		String value = parseString(Integer.class, 123);
		Integer value2 = parseObject(Integer.class, value);
		Assert.assertTrue(123 == value2);
	}
	
	@Test
	public void getStringParserTest() {
		String value = parseString(String.class, "123");
		String value2 = parseObject(String.class, value);
		Assert.assertTrue("123".equals(value2));
	}
	
	
	private <I> String parseString(Class<I> clazz, I object) {
		return SerializationParserUtil.getToStringParser(clazz).run(object);
	}
	
	private <I> I parseObject(Class<I> clazz, String string) {
		return SerializationParserUtil.getFromStringParser(clazz).run(string);
	}
}

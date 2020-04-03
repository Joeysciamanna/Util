package ch.g_7.util.parse.test;

import org.junit.Assert;
import org.junit.Test;

import ch.g_7.util.helper.ReflectionUtil;
import ch.g_7.util.parse.IDestringifier;
import ch.g_7.util.parse.ParserUtil;
import ch.g_7.util.parse.Stringifyable;

public class SerializationParserUtilTest {

	
	@Test
	public void getStringifyParserTest() {
		Stringifyable stringifyable = ReflectionUtil.implemment(Stringifyable.class, (m, args) -> "this string identifiese the stringifyable");
		ParserUtil.setDestringifyer(ReflectionUtil.implemment(IDestringifier.class, (m, args) -> stringifyable));
		
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
		return ParserUtil.getToStringParser(clazz).apply(object);
	}
	
	private <I> I parseObject(Class<I> clazz, String string) {
		return ParserUtil.getFromStringParser(clazz).apply(string);
	}
}

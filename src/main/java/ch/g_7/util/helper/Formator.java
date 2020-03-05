package ch.g_7.util.helper;

import java.util.ArrayList;
import java.util.List;

public final class Formator {
	
	
	/**
	 *
	 * @param text
	 * @param values lenght must be %2==0, pattern: key, value, key, value...
	 * @return
	 */
	public static String format(String text, String... values){
		if (values.length % 2 != 0) throw new IllegalArgumentException("Ivalid number of key / values");
		StringBuilder textBuilder = new StringBuilder();
		for(int i = 0; i<text.length(); i++){
			char c = text.charAt(i);
			if(text.substring(i).matches("\\{[a-zA-Z0-9_.]+\\}.*")){
				StringBuilder identifier = new StringBuilder();
				c = text.charAt(++i);
				while(c != '}') {
					identifier.append(c);
					c = text.charAt(++i);
				}
				
				String value = "";
				for (int v = 0; v<values.length; v+=2) {
					if(values[v].equals(identifier.toString())) {
						value = values[v+1];
						break;
					}
				}
				textBuilder.append(value);
				
			}else{
				textBuilder.append(c);
			}
		}
		return textBuilder.toString();
	}

	/**
	 * % include til next match
	 * # include one
	 * * skip til next match
	 * ? skip one
	 * @param from
	 * @param pattern
	 */
	public static List<String> extract(String from, String pattern){
		from = from + "[";
		pattern = pattern + "[";
		int fromIndex=0;
		int patternIndex=0;
		StringBuilder keyBuilder = new StringBuilder();
		List<String> keys = new ArrayList<>();
		for(int i = 0; i < from.length(); i++){
			if(patternIndex >= pattern.length() || fromIndex >= from.length()){
				return  keys;
			}
			if(from.charAt(fromIndex) == pattern.charAt(patternIndex)){
				fromIndex++;
				patternIndex++;
			}else if(pattern.charAt(patternIndex) == '\\'){
				patternIndex+=2;
				fromIndex++;
			} else if(pattern.charAt(patternIndex) == '%'){
				while (pattern.charAt(patternIndex+1) != from.charAt(fromIndex)){
					keyBuilder.append(from.charAt(fromIndex++));
				}
				patternIndex++;
				keys.add(keyBuilder.toString());
				keyBuilder.setLength(0);
			}else if(pattern.charAt(patternIndex) == '#'){
				keyBuilder.append(from.charAt(fromIndex));
				fromIndex++;
				patternIndex++;
				keys.add(keyBuilder.toString());
				keyBuilder.setLength(0);
			}else if(pattern.charAt(patternIndex) == '*'){
				while (pattern.charAt(patternIndex+1) != from.charAt(fromIndex)){
					fromIndex++;
				}
				patternIndex++;
			}else if(pattern.charAt(patternIndex) == '?'){
				fromIndex++;
				patternIndex++;
			}
		}
		return keys;
	}


	public static String formatLine(String text) {
		if(text.endsWith("\n")) {
			return text;
		}else {
			return text + "\n";
		}
	}
	
	public static String fill(String s, char c, int len){
		while (s.length()<len) {
			s = c + s;
		}
		return s;
	}
}

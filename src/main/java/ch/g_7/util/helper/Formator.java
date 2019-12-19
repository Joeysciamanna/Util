package ch.g_7.util.helper;

import java.util.function.Supplier;

public final class Formator {
	
	private final static Properties PROPERTIES = Properties.getInstance();
	
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
					}
				}
				if(value.isEmpty() && PROPERTIES.contains(identifier.toString())) {
					value = PROPERTIES.get(identifier.toString(), "").getValue();
				}
				textBuilder.append(PROPERTIES.get(identifier.toString(), "").getValue());
				
			}else{
				textBuilder.append(c);
			}
		}
		return textBuilder.toString();
	}

	

	public static String formatLine(String text) {
		if(text.endsWith("\n")) {
			return text;
		}else {
			return text + "\n";
		}
	}
	
	public static String fill(Object o, char c, int len){
		String s = o.toString();
		while (s.length()<len) {
			s = c + s;
		}
		return s;
	}
}

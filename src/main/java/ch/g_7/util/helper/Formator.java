package ch.g_7.util.helper;

import java.util.function.Supplier;

public final class Formator {
	
	private final static Properties PROPERTIES = Properties.getInstance();
	
	@SafeVarargs
	public static String format(String text, BiValue<String, Supplier<String>>... values){
		
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
				for (BiValue<String, Supplier<String>> biValue : values) {
					if(biValue.getKey().equals(identifier.toString())) {
						value = biValue.getValue().get();
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

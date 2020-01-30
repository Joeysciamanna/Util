package ch.g_7.util.properties;

import java.util.HashSet;
import java.util.Set;

import ch.g_7.util.helper.Formator;

public class PropertyParser {

	public static Set<Prop> fromString(String txt) {
		Set<Prop> properties = new HashSet<>();
		String[] lines = txt.split(System.getProperty("line.separator"));
		StringBuilder comment = new StringBuilder();
		for (String line : lines) {
			if (!line.startsWith("#") && !line.startsWith("%") && !line.trim().isEmpty()) {
				String[] data = line.split("\\=");
				if (data.length == 2) {
					properties.add(new Prop(data[0], data[1], comment.toString()));
					comment.setLength(0);
				} else {
					throw new IllegalArgumentException("Invalid property: " + line);
				}
			} else if (line.startsWith("#")) {
				comment.append(line);
			}
		}
		return properties;
	}

	public static String toString(Set<Prop> properties) {
		StringBuilder string = new StringBuilder();
		for (Prop prop : properties) {
			if (prop.getComment() != null && !prop.getComment().isBlank()) {
				string.append("#");
				string.append(Formator.formatLine(prop.getComment()));
			}
			string.append(Formator.formatLine(prop.getKey() + "=" + prop.getValue()));
		}
		return string.toString();
	}
}

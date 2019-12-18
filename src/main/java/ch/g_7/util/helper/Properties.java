package ch.g_7.util.helper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;

public final class Properties {

	private Set<Prop> appProperties;
	private Set<Prop> properties;
	private static Properties instance;
	
	private Properties(Set<Prop> properties) {
		this.properties = properties;
		this.appProperties = new HashSet<Prop>();
	}

	private static Properties getInstance(Set<Prop> properties) {
		if(instance==null) {
			instance = new Properties(properties);
		}else {
			throw new IllegalStateException("Properties are alredy loaded");
		}
		return instance;
	}
	
	public static Properties getInstance() {
		if(instance == null) {
			throw new IllegalStateException("Properties not jet loaded");
		}
		return instance;
	}

	public static Properties read(InputStream inputStream) throws IOException  {
		return getInstance(parse(IOUtil.toString(inputStream)));
	}
		
	public static Properties read(String txt) {
		return getInstance(parse(txt));
	}
	
	
	public void initTimeProperties() {
		set("DT.mm", ()->Formator.fill(Calendar.getInstance().get(Calendar.MINUTE), '0', 2));
		set("DT.ss", ()->Formator.fill(Calendar.getInstance().get(Calendar.SECOND), '0', 2));
		set("DT.hh", ()->Formator.fill(Calendar.getInstance().get(Calendar.HOUR_OF_DAY), '0', 2));
		set("DT.dd", ()->Formator.fill(Calendar.getInstance().get(Calendar.DAY_OF_MONTH), '0', 2));
		set("DT.MM", ()->Formator.fill(Calendar.getInstance().get(Calendar.MONTH)+1, '0', 2));
		set("DT.yyyy", ()->String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
		set("DT.yy",   ()->String.valueOf(Calendar.getInstance().get(Calendar.YEAR)).substring(2));
	}
	
	
	private static Set<Prop> parse(String txt){
		Set<Prop> properties = new HashSet<>();
		String[] lines = txt.split(System.getProperty("line.separator"));
		StringBuilder comment = new StringBuilder();
		for (String line : lines) {
			if (!line.startsWith("#") && !line.startsWith("%") && !line.trim().isEmpty()) {
				String[] data = line.split("\\=");
				if (data.length == 2) {
					properties.add(new Prop(data[0], data[1], comment.toString()));
					comment.setLength(0);
				}else {
					throw new IllegalArgumentException("Invalid property: " + line);
				}
			}else if(line.startsWith("#")) {
				comment.append(line);
			}
		}
		return properties;
	}
	
	private static String parse(Set<Prop> properties){
		StringBuilder string = new StringBuilder();
		for (Prop prop : properties) {
			if(prop.getComment() != null && !prop.getComment().isEmpty()) {
				string.append("#");
				string.append(Formator.formatLine(prop.getComment()));
			}
			string.append(Formator.formatLine(prop.getKey() + "=" + prop.getValue()));
		}
		return string.toString();
	}
	
	public void write(OutputStream outputStream) throws IOException {
		outputStream.write(parse(properties).getBytes());
		outputStream.close();
	}
	
	public String getFormated(PropKey<String> key) {
		return Formator.format(get(key));
	}
	
	public <T> T get(PropKey<T> key) {
		return get(key, null);
	}

	public <T> T get(PropKey<T> key, String defauld) {
		return key.cast(get(key.name, defauld).getValue());
	}

	public Prop get(String key, String defauld) {
		Optional<Prop> value = properties.stream().filter((p)->p.getKey().equals(key)).findFirst();
		if(!value.isPresent()) {
			value = appProperties.stream().filter((p)->p.getKey().equals(key)).findFirst();
		}
		if(!value.isPresent() && defauld != null) {
			value = Optional.of(new Prop(key, defauld, ""));
		}
		return value.orElseThrow(()->new IllegalArgumentException("No property with name " + key));
	}
	
	public void set(String key, Supplier<String> value) {
		appProperties.add(new Prop(key, value, ""));
	}
	
	public void set(String key, String value) {
		appProperties.add(new Prop(key, value, ""));
	}
	
	public Prop put(String key, String value) {
		return put(key, value, null);
	}
	
	public Prop put(String key, String value, String comment) {
		Prop prop = new Prop(key, value, comment); 
		properties.add(prop); 
		return prop;
	}
	
	public boolean isEmpty() {
		return properties.isEmpty();
	}
	
	public String getVersion() {
		return get("VERSION", "-1").getValue();
	}
	
	public boolean contains(String key) {
		return properties.stream().anyMatch((p)->p.getKey().equals(key)) || appProperties.stream().anyMatch((p)->p.getKey().equals(key));
	}

	

}

package ch.g_7.util.io;


import java.io.IOException;
import java.io.InputStream;


public abstract class LocalFileLoader implements IDataLoader {

    @Override
    public InputStream load(String path) throws IOException {
    	InputStream inputStream = getClass().getResourceAsStream("/" + path);
    	if(inputStream == null) {
    		throw new IOException("File [/"+path+"] not found");
    	}
        return inputStream;
    }
}

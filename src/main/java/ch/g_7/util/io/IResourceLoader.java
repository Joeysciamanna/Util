package ch.g_7.util.io;

import java.io.IOException;
import java.io.InputStream;


public interface IResourceLoader {

    InputStream loadResource(String path) throws IOException;

    default InputStream loadResourceThrowRuntime(String path){
        try {
            return  loadResource(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

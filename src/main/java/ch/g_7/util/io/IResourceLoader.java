package ch.g_7.util.io;

import java.io.IOException;
import java.io.InputStream;

public interface IResourceLoader {

    InputStream loadFile(String path) throws IOException;
}

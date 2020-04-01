package ch.g_7.util.io;

import java.io.IOException;
import java.io.InputStream;

public interface IDataLoader {

    InputStream load(String path) throws IOException;
}

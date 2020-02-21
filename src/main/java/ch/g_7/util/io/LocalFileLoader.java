package ch.g_7.util.io;


import java.io.InputStream;


public abstract class LocalFileLoader implements IFileLoader {

    @Override
    public InputStream loadFile(String path) {
        return getClass().getResourceAsStream("/" + path);
    }
}

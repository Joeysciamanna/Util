package ch.g_7.util.io;


import java.io.File;


public abstract class LocalFileLoader implements IFileLoader {

    @Override
    public File loadFile(String path) {
        return new File(getClass().getResource("/" + path).getFile());
    }
}

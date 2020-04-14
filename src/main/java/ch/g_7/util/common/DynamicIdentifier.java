package ch.g_7.util.common;

import java.util.Objects;

public class DynamicIdentifier implements IIdentifier {

    private final String name;

    public DynamicIdentifier(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (null == o) return false;
        if (this == o) return true;
        if (getClass() != o.getClass()) return false;
        return ((DynamicIdentifier) o).name.equals(name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

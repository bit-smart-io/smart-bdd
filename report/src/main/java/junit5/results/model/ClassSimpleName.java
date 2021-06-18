package junit5.results.model;

import java.util.Objects;

public class ClassSimpleName {
    private final String value;

    private ClassSimpleName(String value) {
        this.value = value;
    }

    public static ClassSimpleName classSimpleName(Class clazz) {
        return new ClassSimpleName(clazz.getSimpleName());
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClassSimpleName)) return false;
        ClassSimpleName classSimpleName = (ClassSimpleName) o;
        return Objects.equals(value, classSimpleName.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "ClassName{" +
            "value='" + value + '\'' +
            '}';
    }
}

package junit5.parameters.foo;

import java.util.Objects;

public class Foo {
    private final String name;

    public Foo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Foo)) return false;
        Foo foo = (Foo) o;
        return Objects.equals(name, foo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}


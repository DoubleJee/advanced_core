package datastructure.hash.model;

public class Key {
    protected int value;

    public Key(int value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return value / 20;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj.getClass() != getClass()) return false;
        return this.value == ((Key)obj).value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}

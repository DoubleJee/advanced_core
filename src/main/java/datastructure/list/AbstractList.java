package datastructure.list;

public abstract class AbstractList<E> implements List<E> {
    /**
     * 大小
     */
    protected int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(E element) {
        return indexOf(element) != EXCEPTION_NOT_FIND;
    }

    public void add(E element) {
        add(size, element);
    }

    protected void indexException(int index) {
        throw new IndexOutOfBoundsException("Index:" + index + ",Size:" + size);
    }

    protected void checkIndex(int index) {
        if (index < 0 || index >= size) {
            indexException(index);
        }
    }

    protected void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            indexException(index);
        }
    }
}

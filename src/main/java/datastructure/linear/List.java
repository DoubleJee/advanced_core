package datastructure.linear;

public interface List<E> {

    /**
     * 异常下标
     */
    int EXCEPTION_NOT_FIND = -1;

    int size();

    boolean isEmpty();

    boolean contains(E element);

    void add(E element);

    void add(int index, E element);

    E get(int index);

    E set(int index, E element);

    E remove(int index);

    int indexOf(E element);

    void clear();
}

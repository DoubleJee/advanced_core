package datastructure.list.stack;

import datastructure.list.List;
import datastructure.list.array.ArrayList;

public class Stack<E> {

    private List<E> arrayList;

    public Stack(){
        arrayList = new ArrayList<>();
    }

    public int size(){
        return arrayList.size();
    }

    public boolean isEmpty(){
        return arrayList.isEmpty();
    }

    public void push(E element){
        arrayList.add(element);
    }

    public E pop(){
        return arrayList.remove(size() - 1);
    }

    public E top(){
        return arrayList.get(size() - 1);
    }

    @Override
    public String toString() {
        return arrayList.toString();
    }
}

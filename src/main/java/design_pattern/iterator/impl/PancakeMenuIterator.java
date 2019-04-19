package design_pattern.iterator.impl;

import design_pattern.iterator.Iterator;

import java.util.ArrayList;

public class PancakeMenuIterator implements Iterator {

    private ArrayList menuItems;
    /**
     * 当前操作下标
     */
    private int position = 0;

    public PancakeMenuIterator(ArrayList menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public boolean hasNext() {
        if(position >= menuItems.size() || menuItems.get(position) == null){
            return false;
        }
        return true;
    }

    @Override
    public Object next() {
        Object menuItem = menuItems.get(position);
        position++;
        return menuItem;
    }
}

package design_pattern.iterator.impl;

import design_pattern.iterator.Iterator;
import design_pattern.iterator.MenuItem;

public class DinnerMenuIterator implements Iterator {

    private MenuItem[] menuItems;

    /**
     * 当前操作下标
     */
    private int position = 0;

    public DinnerMenuIterator(MenuItem[] menuItems){
        this.menuItems = menuItems;
    }

    @Override
    public boolean hasNext() {
        if(position >= menuItems.length || menuItems[position] == null){
            return false;
        }
        return true;
    }

    @Override
    public Object next() {
        MenuItem menuItem = menuItems[position];
        position++;
        return menuItem;
    }
}

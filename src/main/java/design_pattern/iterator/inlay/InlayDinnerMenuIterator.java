package design_pattern.iterator.inlay;

import design_pattern.iterator.MenuItem;

import java.util.Iterator;

public class InlayDinnerMenuIterator implements Iterator {

    private MenuItem[] menuItems;

    /**
     * 当前操作下标
     */
    private int position = 0;

    public InlayDinnerMenuIterator(MenuItem[] menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public boolean hasNext() {
        if (position >= menuItems.length || menuItems[position] == null) {
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

    @Override
    public void remove() {
        if (position < 0) {
            throw new IllegalStateException("没有下一个值");
        }
        if (menuItems[position + 1] != null) {
            for (int i = position + 1; i < menuItems.length - 1; i++) {
                menuItems[i - 1] = menuItems[i];
            }
            menuItems[menuItems.length - 1] = null;
        }
    }
}

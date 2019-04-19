package design_pattern.iterator.inlay;

import design_pattern.iterator.MenuItem;

import java.util.Hashtable;
import java.util.Iterator;

//咖啡菜单，只做菜单存储，遍历交给迭代器做
public class CafeMenu implements Menu {

    private Hashtable menuItems;

    public CafeMenu(){
        menuItems = new Hashtable();
        addItem("摩卡咖啡","很浓香",true,7);
        addItem("卡布奇诺","醇香，奶味",true,10);
        addItem("苦咖啡","很能让你提神哦",false,5);
    }

    public void addItem(String name, String description, boolean vegetarian, double price){
        MenuItem menuItem = new MenuItem(name,description,vegetarian,price);
        menuItems.put(menuItem.getName(),menuItem);
    }

    @Override
    public Iterator createIterator() {
        return menuItems.values().iterator();
    }
}

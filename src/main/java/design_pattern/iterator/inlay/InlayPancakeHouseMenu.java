package design_pattern.iterator.inlay;

import design_pattern.iterator.MenuItem;

import java.util.ArrayList;
import java.util.Iterator;

public class InlayPancakeHouseMenu implements Menu {

    private ArrayList menuItems;

    public InlayPancakeHouseMenu(){
        menuItems = new ArrayList();
        addItem("杂粮煎饼","薄饼加上蛋，香味十足",true,5);
        addItem("葱油饼","油闷厚饼，味道很棒",true,3);
        addItem("牛肉馅饼","牛肉馅，好吃美味",false,6);
    }

    public void addItem(String name, String description, boolean vegetarian, double price){
        MenuItem menuItem = new MenuItem(name,description,vegetarian,price);
        menuItems.add(menuItem);
    }

//    public ArrayList getMenuItems() {
//        return menuItems;
//    }

    @Override
    public Iterator createIterator() {
        return menuItems.iterator();
    }
}

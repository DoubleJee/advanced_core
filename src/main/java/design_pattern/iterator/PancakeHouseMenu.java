package design_pattern.iterator;

import design_pattern.iterator.impl.PancakeMenuIterator;

import java.util.ArrayList;
//早餐菜单，只做菜单存储，遍历交给迭代器做
public class PancakeHouseMenu {

    private ArrayList menuItems;

    public PancakeHouseMenu(){
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

    public Iterator createIterator(){
        return new PancakeMenuIterator(menuItems);
    }
}

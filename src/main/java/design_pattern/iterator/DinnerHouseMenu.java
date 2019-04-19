package design_pattern.iterator;

import design_pattern.iterator.impl.DinnerMenuIterator;
//晚餐菜单，只做菜单存储，遍历交给迭代器做
public class DinnerHouseMenu {
    private static final int MAX_ITEMS = 6;
    private int numberOfItems = 0;
    private MenuItem[] menuItems;

    public DinnerHouseMenu(){
        menuItems = new MenuItem[MAX_ITEMS];
        addItem("小炒肉","家常炒菜，色香味俱全",false,15);
        addItem("老干妈炒饭","炒饭，辣辣的",true,13);
        addItem("馄饨","混沌，虾米带汤",true,10);
    }

    public void addItem(String name, String description, boolean vegetarian, double price){
        if(numberOfItems >= MAX_ITEMS){
            System.err.println("对不起，菜单已满，不可以再增加菜品了。");
            return;
        }
        MenuItem menuItem = new MenuItem(name,description,vegetarian,price);
        menuItems[numberOfItems] = menuItem;
        numberOfItems++;
    }

//    public MenuItem[] getMenuItems() {
//        return menuItems;
//    }
    public Iterator createIterator(){
        return new DinnerMenuIterator(menuItems);
    }
}

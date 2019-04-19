package design_pattern.iterator;

public class Waiter {

    private DinnerHouseMenu dinnerHouseMenu;
    private PancakeHouseMenu pancakeHouseMenu;

    public Waiter(DinnerHouseMenu dinnerHouseMenu, PancakeHouseMenu pancakeHouseMenu) {
        this.dinnerHouseMenu = dinnerHouseMenu;
        this.pancakeHouseMenu = pancakeHouseMenu;
    }

    public void printMenuItem(MenuItem menuItem){
        System.out.print(menuItem.getName() + ", ");
        System.out.print(menuItem.getPrice() + " -- ");
        System.out.println(menuItem.getDescription());
    }

    /**
     * 打印菜单每一项
     */
    public void printMenu(){
        Iterator pancakeHouseMenuIterator = pancakeHouseMenu.createIterator();
        Iterator dinnerHouseMenuIterator = dinnerHouseMenu.createIterator();
        System.out.println("菜单\n---------------\n早餐");
        printMenu(pancakeHouseMenuIterator);
        System.out.println("\n晚餐");
        printMenu(dinnerHouseMenuIterator);

    }

    /**
     * 服务员不需要知道，我们使用哪一个实现（哪一个容器），反正她都是使用相同的接口，也就是迭代器的接口来遍历菜单项（解耦了）
     * @param iterator
     */
    public void printMenu(Iterator iterator){
        while (iterator.hasNext()){
            MenuItem menuItem = (MenuItem)iterator.next();
            printMenuItem(menuItem);
        }
    }



    /**
     * 打印早晨项
     */
    public void printBreakfastMenu(){
        Iterator pancakeHouseMenuIterator = pancakeHouseMenu.createIterator();
        printMenu(pancakeHouseMenuIterator);
    }

    /**
     * 打印晚餐项
     */
    public void printDinnerMenu(){
        Iterator dinnerHouseMenuIterator = dinnerHouseMenu.createIterator();
        printMenu(dinnerHouseMenuIterator);
    }

    /**
     * 打印所有的素食项
     */
    public void printVegetarianMenu(){

    }

    /**
     * 判断此项是不是素食
     * @param name 菜品名
     * @return boolean 素食结果
     */
    public boolean isVegetarian(String name){
        return true;
    }

}

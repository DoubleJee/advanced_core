package design_pattern.iterator.inlay;

public class InlayIteratorTest {

    public static void main(String[] args) {
        //早餐菜单
        Menu pancakeHouseMenu = new InlayPancakeHouseMenu();
        //午餐菜单
        Menu dinnerHouseMenu = new InlayDinnerHouseMenu();
        //咖啡菜单
        Menu cafeMenu = new CafeMenu();
        InlayWaiter waiter = new InlayWaiter(dinnerHouseMenu,pancakeHouseMenu,cafeMenu);
        waiter.printMenu();
    }
}

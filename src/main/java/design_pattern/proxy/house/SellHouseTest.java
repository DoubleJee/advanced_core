package design_pattern.proxy.house;

import design_pattern.proxy.house.impl.Landlord;
import design_pattern.proxy.house.impl.MiddlemanProxy;

public class SellHouseTest {

    public static void main(String[] args) {
        // 客户 -> 中介（代理） -> 房东
        Landlord landlord = new Landlord();
        MiddlemanProxy middlemanProxy = new MiddlemanProxy(landlord);
        middlemanProxy.sellHouse();
        //房东是真实主题，中介是代理，SellHouse是主题
    }
}

package design_pattern.decorator;

import design_pattern.decorator.decorator.impl.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Coffee {

    public static void main(String[] args) throws IOException {
        Beverage beverage = new Espresso();
        beverage = new Big(beverage);
        System.out.println(beverage.getName() + " RMB：" + beverage.cost());

        Beverage beverage2 = new HouseBlend();
        //摩卡调味料装饰混合咖啡
        beverage2 = new Mocha(beverage2);
        //双倍摩卡
        beverage2 = new Mocha(beverage2);
        //再加点奶油
        beverage2 = new Whip(beverage2);
        beverage2 = new Small(beverage2);

        System.out.println(beverage2.getName() + " RMB：" + beverage2.cost());

        Beverage beverage3 = new HouseBlend();
        //混合咖啡加上摩卡，豆浆，奶油调味料
        beverage3 = new Mocha(beverage3);
        beverage3 = new Soy(beverage3);
        beverage3 = new Whip(beverage3);
        beverage3 = new Medium(beverage3);
        System.out.println(beverage3.getName() + " RMB：" + beverage3.cost());

        int c = 0;
        InputStream inputStream = new FileInputStream("D:\\test.txt");
        //小写装饰
        inputStream = new LowerCaseInputStream(inputStream);
        while ((c = inputStream.read()) > 0){
            System.out.print((char)c);
        }
        inputStream.close();

        //装饰者模式：动态的将责任附加到对象上，想要扩展功能，装饰者提供于有别于继承的另一种选择。

    }
}

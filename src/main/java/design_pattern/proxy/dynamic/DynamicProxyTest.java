package design_pattern.proxy.dynamic;

import design_pattern.proxy.dynamic.impl.PersonBeanImpl;

import java.lang.reflect.Proxy;

public class DynamicProxyTest {

    //personBean参数作为真实主题者
    public static PersonBean getOwnerProxy(PersonBean personBean) {
        return (PersonBean) Proxy.newProxyInstance(personBean.getClass().getClassLoader(),
                personBean.getClass().getInterfaces(),
                new OwnerInvocationHandler(personBean));
    }

    public static PersonBean getNonOwnerProxy(PersonBean personBean) {
        return (PersonBean) Proxy.newProxyInstance(personBean.getClass().getClassLoader(),
                personBean.getClass().getInterfaces(),
                new NonOwnerInvocationHandler(personBean));
    }

    public static void main(String[] args) {
        PersonBean gzz = new PersonBeanImpl("巩振振");
        PersonBean ownerProxy = getOwnerProxy(gzz);
        System.out.println("我的名字是：" + ownerProxy.getName());
        ownerProxy.setGender("男");
        ownerProxy.setInterests("唱歌，跳舞，表演");
        System.out.println("通过拥有者代理设置了属性");
        try {
            ownerProxy.setHotOrNotRating(10);
        }catch (Exception ex){
            System.out.println("不可以通过拥有者代理，设置自己的评分");
        }
        System.out.println("评分是：" + ownerProxy.getHotOrNotRating());
        System.out.println();

        PersonBean nonOwnerProxy = getNonOwnerProxy(gzz);
        try {
            nonOwnerProxy.setGender("女");
        }catch (Exception ex){
            System.out.println("不可以通过非拥有者代理，设置自己的这个属性");
        }
        System.out.println("名字是：" + nonOwnerProxy.getName());

        nonOwnerProxy.setHotOrNotRating(10);
        System.out.println("通过非拥有者代理设置了评分");
        System.out.println("评分是：" + nonOwnerProxy.getHotOrNotRating());

        //判断此对象是不是代理对象
        boolean isProxy = Proxy.isProxyClass(nonOwnerProxy.getClass());
        System.out.println(isProxy);
    }

}

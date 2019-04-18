package design_pattern.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

//代理调用的处理者 Proxy会把调用转发给此类处理
public class NonOwnerInvocationHandler implements InvocationHandler {

    private PersonBean personBean;

    public NonOwnerInvocationHandler(PersonBean personBean){
        this.personBean = personBean;
    }

    //代理对象方法被调用的时候，转发给此方法处理
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getName().equals("setHotOrNotRating") || method.getName().startsWith("get")){
            return method.invoke(personBean,args);
        }
        throw new IllegalAccessException();
    }
}

package cn.rivamed.springbootmybatiswithredis.util.CglibDemo;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class EasyIntercepter implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("before");
        Object result=methodProxy.invokeSuper(obj,args);
        System.out.println("after");
        return result;
    }
}

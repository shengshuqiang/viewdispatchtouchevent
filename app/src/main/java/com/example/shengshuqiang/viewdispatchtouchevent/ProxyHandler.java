package com.example.shengshuqiang.viewdispatchtouchevent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by shengshuqiang on 2018/1/7.
 */
public class ProxyHandler implements InvocationHandler {
    private Object rawObj;

    public ProxyHandler(Object rawObj) {
        this.rawObj = rawObj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Helper.log("ProxyHandler#pre invoke:rawObj=" + rawObj + ", proxy=" + proxy + ", method=" + method + ", args=" + args);
        Object result = method.invoke(rawObj, args);
        Helper.log("ProxyHandler#post invoke:invoke:rawObj=" + rawObj + ", proxy=" + proxy + ", method=" + method + ", args=" + args + ", result=" + result);

        return result;
    }
}

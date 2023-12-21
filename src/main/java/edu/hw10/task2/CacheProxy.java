package edu.hw10.task2;

import java.lang.reflect.Proxy;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CacheProxy {
    @SuppressWarnings("unchecked")
    public static <T> T create(T object, Class<T> clazz, String file) {
        return (T) Proxy.newProxyInstance(
            clazz.getClassLoader(),
            clazz.getInterfaces(),
            new CacheInvocationHandler(
                object,
                file
            )
        );
    }
}

package edu.hw10.task2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import lombok.SneakyThrows;

public class CacheInvocationHandler implements InvocationHandler {
    private final Object object;
    private final HashMap<String, Object> inMemoryCache = new HashMap<>();
    private final FileHashMap fileCache;

    public CacheInvocationHandler(Object object, String file) {
        this.object = object;
        this.fileCache = new FileHashMap(file);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Cache.class)) {
            if (method.getAnnotation(Cache.class).persist()) {
                return invokeFileCache(method, args);
            } else {
                return invokeInMemoryCache(method, args);
            }
        }
        return method.invoke(object, args);
    }

    @SneakyThrows
    private Object invokeInMemoryCache(Method method, Object[] args) {
        String key = generateKey(method, args);
        if (inMemoryCache.containsKey(key)) {
            return inMemoryCache.get(key);
        }
        Object result = method.invoke(object, args);
        inMemoryCache.put(key, result);
        return result;
    }

    @SneakyThrows
    private Object invokeFileCache(Method method, Object[] args) {
        String key = generateKey(method, args);
        if (fileCache.containsKey(key)) {
            return fileCache.get(key);
        }
        Object result = method.invoke(object, args);
        fileCache.put(key, result);
        return result;
    }

    private String generateKey(Method method, Object[] args) {
        return method.getName() + Arrays.toString(args);
    }
}

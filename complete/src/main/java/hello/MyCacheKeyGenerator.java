package hello;

import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;

/**
 * @author daimao  Date: 16/5/19 Time: 下午12:04
 * @version $Id$
 */
public class MyCacheKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(target.getClass().getSimpleName()).append("~").append(method.getName()).append("~");
        for (Object param : params) {
            stringBuilder.append(param.toString()).append("-_-");
        }

        return stringBuilder.toString();
    }
}
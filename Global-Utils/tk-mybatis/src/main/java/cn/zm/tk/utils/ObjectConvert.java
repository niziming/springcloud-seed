package cn.zm.tk.utils;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.stream.Collectors;

public class ObjectConvert<T> {

    protected void beforeConvert() {
    }

    protected void afterConvert(T t) {
    }

    public T convert() {
        T t = null;
        beforeConvert();
        try {
            t = newInstance();
            BeanUtils.copyProperties(this, t);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Bean convert faild, Caused by " + e);
        }
        afterConvert(t);
        return t;
    }

    @SuppressWarnings("unchecked")
    private T newInstance() throws Exception {
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return clazz.newInstance();
    }


}

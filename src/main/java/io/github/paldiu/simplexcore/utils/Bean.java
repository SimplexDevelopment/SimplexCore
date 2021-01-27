package io.github.paldiu.simplexcore.utils;

public class Bean<T> {
    protected T bean;

    public Bean(T bean) {
        this.bean = bean;
    }

    public void setBean(T bean) {
        this.bean = bean;
    }

    public T getBean() {
        return bean;
    }
}

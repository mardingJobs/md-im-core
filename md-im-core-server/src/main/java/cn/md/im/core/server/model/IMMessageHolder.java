package cn.md.im.core.server.model;

/**
 * * @Author: Martin
 * * @Date    2024/9/3 15:06
 * * @Description
 **/
public class IMMessageHolder<T> {

    private Integer type;
    private T data;

    public IMMessageHolder() {
    }

    public IMMessageHolder(Integer type, T data) {
        this.type = type;
        this.data = data;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

package cn.md.im.core.server.model;

/**
 * * @Author: Martin
 * * @Date    2024/9/3 15:06
 * * @Description
 **/
public class IMMessageHolder<T> {

    private Integer cmd;
    private T data;

    public IMMessageHolder() {
    }

    public IMMessageHolder(Integer cmd, T data) {
        this.cmd = cmd;
        this.data = data;
    }

    public Integer getCmd() {
        return cmd;
    }

    public void setCmd(Integer cmd) {
        this.cmd = cmd;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

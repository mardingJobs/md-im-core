package cn.md.im.core.server;

/**
 * IM 通讯服务接口
 * * @Author: jack
 * * @Date    2024/9/3 14:23
 * * @Description 相信时间的力量！
 **/
public interface IMServer {


    boolean isReady();

    void start();

    void shutdown();


}

package com.skaleto.things.designpattern.proxy;

/**
 * @author : ybyao
 * @Create : 2019-08-01 20:54
 */
public interface IGamePlayer {
    //登录游戏
    void login(String user, String password);

    void logout(String user);
}

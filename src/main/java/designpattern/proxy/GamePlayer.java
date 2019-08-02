package designpattern.proxy;

/**
 * @author : ybyao
 * @Create : 2019-08-01 20:55
 */


public class GamePlayer implements IGamePlayer {
    //玩家的名字
    private String name = "";

    public GamePlayer(String name) {
        this.name = name;
    }


    //登录游戏
    public void login(String user, String password) {
    }

    //登录游戏
    public void logout(String user) {
    }


}
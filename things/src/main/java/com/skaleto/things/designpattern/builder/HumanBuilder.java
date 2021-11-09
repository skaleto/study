package com.skaleto.things.designpattern.builder;

/**
 * @author : ybyao
 * @Create : 2019-08-01 17:46
 */
public class HumanBuilder implements IBuildHuman {
    private Human human = new Human();

    public void buildHead() {
        human.setHead("");
    }

    public void buildBody() {
        human.setBody("");
    }

    public void buildFoot() {
        human.setFoot("");
    }

    public Human getHuman() {
        return human;
    }
}

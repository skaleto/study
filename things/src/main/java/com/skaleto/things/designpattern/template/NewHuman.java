package com.skaleto.things.designpattern.template;

import com.skaleto.things.designpattern.builder.Human;

/**
 * @author : ybyao
 * @Create : 2019-08-01 20:30
 */
public class NewHuman extends HumanTemplate {

    Human human = new Human();

    public void buildHead() {
        human.setHead("head");
    }

    public void buildBody() {
        human.setBody("body");
    }

    public void buildFoot() {
        human.setFoot("foot");
    }
}

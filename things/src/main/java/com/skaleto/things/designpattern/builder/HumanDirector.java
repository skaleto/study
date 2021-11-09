package com.skaleto.things.designpattern.builder;

/**
 * @author : ybyao
 * @Create : 2019-08-01 17:46
 */
public class HumanDirector {

    public static Human buildHuman(IBuildHuman iBuildHuman) {
        iBuildHuman.buildHead();
        iBuildHuman.buildBody();
        iBuildHuman.buildFoot();
        return iBuildHuman.getHuman();
    }


    public static void main(String[] args) {
        HumanDirector.buildHuman(new HumanBuilder());
    }
}

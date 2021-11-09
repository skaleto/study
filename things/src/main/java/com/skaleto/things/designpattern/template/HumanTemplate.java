package com.skaleto.things.designpattern.template;

/**
 * @author : ybyao
 * @Create : 2019-08-01 20:28
 */
public abstract class HumanTemplate {

    public final void buildHuman(){
        buildHead();
        buildBody();
        buildFoot();
    }

    public abstract void buildHead();

    public abstract void buildBody();

    public abstract void buildFoot();
}

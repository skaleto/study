package com.skaleto.things.designpattern.builder;

/**
 * @author : ybyao
 * @Create : 2019-08-01 17:44
 */
public interface IBuildHuman {

    void buildHead();

    void buildBody();

    void buildFoot();

    Human getHuman();

}

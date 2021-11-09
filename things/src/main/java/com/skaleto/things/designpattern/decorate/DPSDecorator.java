package com.skaleto.things.designpattern.decorate;

import com.skaleto.things.designpattern.factory.Character;

/**
 * @author : ybyao
 * @Create : 2019-08-05 21:09
 */
public class DPSDecorator extends Decorator {

    public DPSDecorator(Character character){
        super(character);
    }

    public String getHp(){
        super.getHp();
        System.out.println("HP");
        return null;
    }

}

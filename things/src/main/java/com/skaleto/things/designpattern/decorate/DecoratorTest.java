package com.skaleto.things.designpattern.decorate;

import com.skaleto.things.designpattern.factory.Character;
import com.skaleto.things.designpattern.factory.DPS;

/**
 * @author : ybyao
 * @Create : 2019-08-05 21:26
 */
public class DecoratorTest {

    public static void main(String[] args) {
        Character dps = new DPS();
        DPSDecorator dpsDecorator = new DPSDecorator(dps);
        TankDecorator tankDecorator = new TankDecorator(dpsDecorator);

        tankDecorator.getMp();
    }
}

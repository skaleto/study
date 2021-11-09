package com.skaleto.things.designpattern.factory.factorymethod;

import com.skaleto.things.designpattern.factory.Character;
import com.skaleto.things.designpattern.factory.Tank;

/**
 * @author : ybyao
 * @Create : 2019-08-01 15:28
 */
public class TankFactory implements Factory {
    @Override
    public Character createCharacter() {
        return new Tank();
    }
}

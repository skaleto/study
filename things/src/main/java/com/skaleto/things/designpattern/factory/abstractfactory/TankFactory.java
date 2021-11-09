package com.skaleto.things.designpattern.factory.abstractfactory;

import com.skaleto.things.designpattern.factory.Character;
import com.skaleto.things.designpattern.factory.SpCharacter;
import com.skaleto.things.designpattern.factory.SpTank;
import com.skaleto.things.designpattern.factory.Tank;

/**
 * @author : ybyao
 * @Create : 2019-08-01 16:07
 */
public class TankFactory implements IFactory {
    @Override
    public Character createCharacter() {
        return new Tank();
    }

    @Override
    public SpCharacter createSpCharacter() {
        return new SpTank();
    }

}

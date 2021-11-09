package com.skaleto.things.designpattern.factory.factorymethod;

import com.skaleto.things.designpattern.factory.Character;
import com.skaleto.things.designpattern.factory.Healer;

/**
 * @author : ybyao
 * @Create : 2019-08-01 15:30
 */
public class HealerFactory implements Factory {
    @Override
    public Character createCharacter() {
        return new Healer();
    }
}

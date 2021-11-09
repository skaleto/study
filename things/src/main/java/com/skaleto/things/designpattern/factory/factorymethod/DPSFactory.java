package com.skaleto.things.designpattern.factory.factorymethod;

import com.skaleto.things.designpattern.factory.Character;
import com.skaleto.things.designpattern.factory.DPS;

/**
 * @author : ybyao
 * @Create : 2019-08-01 15:30
 */
public class DPSFactory implements Factory {
    @Override
    public Character createCharacter() {
        return new DPS();
    }
}

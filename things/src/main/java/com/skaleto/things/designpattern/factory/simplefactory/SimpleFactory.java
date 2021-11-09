package com.skaleto.things.designpattern.factory.simplefactory;

import com.skaleto.things.designpattern.factory.Character;
import com.skaleto.things.designpattern.factory.DPS;
import com.skaleto.things.designpattern.factory.Healer;
import com.skaleto.things.designpattern.factory.Tank;

/**
 * @author : ybyao
 * @Create : 2019-08-01 15:01
 */
public class SimpleFactory {

    public Character createCharacter(CharacterType type) {
        switch (type) {
            case TANK:
                return new Tank();
            case DPS:
                return new DPS();
            case HEALER:
                return new Healer();
            default:
                return null;
        }
    }

    enum CharacterType {
        TANK,
        DPS,
        HEALER
    }
}

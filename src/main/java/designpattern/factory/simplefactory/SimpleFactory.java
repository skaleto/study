package designpattern.factory.simplefactory;

import designpattern.factory.Character;
import designpattern.factory.DPS;
import designpattern.factory.Healer;
import designpattern.factory.Tank;

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

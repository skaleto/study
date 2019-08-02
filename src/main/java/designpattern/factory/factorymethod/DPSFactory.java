package designpattern.factory.factorymethod;

import designpattern.factory.Character;
import designpattern.factory.DPS;

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

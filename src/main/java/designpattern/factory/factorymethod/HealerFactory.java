package designpattern.factory.factorymethod;

import designpattern.factory.Character;
import designpattern.factory.Healer;

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

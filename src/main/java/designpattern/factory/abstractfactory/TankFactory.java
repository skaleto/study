package designpattern.factory.abstractfactory;

import designpattern.factory.Character;
import designpattern.factory.SpCharacter;
import designpattern.factory.SpTank;
import designpattern.factory.Tank;

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

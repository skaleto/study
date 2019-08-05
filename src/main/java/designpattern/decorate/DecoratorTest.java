package designpattern.decorate;

import designpattern.factory.Character;
import designpattern.factory.DPS;

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

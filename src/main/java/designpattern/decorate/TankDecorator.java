package designpattern.decorate;

import designpattern.factory.Character;

/**
 * @author : ybyao
 * @Create : 2019-08-05 21:09
 */
public class TankDecorator extends Decorator {

    public TankDecorator(Character character) {
        super(character);
    }

    public String getMp() {
        character.getMp();
        System.out.println("MP");
        return null;
    }

}

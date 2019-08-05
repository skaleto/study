package designpattern.decorate;

import designpattern.factory.Character;

/**
 * @author : ybyao
 * @Create : 2019-08-05 21:08
 */
public abstract class Decorator implements Character {

    protected Character character;

    public Decorator(Character character) {
        this.character = character;
    }

    @Override
    public String getHp() {
        return null;
    }

    @Override
    public String getMp() {
        return null;
    }
}

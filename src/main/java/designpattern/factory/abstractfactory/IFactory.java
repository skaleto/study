package designpattern.factory.abstractfactory;

import designpattern.factory.Character;
import designpattern.factory.SpCharacter;

/**
 * @author : ybyao
 * @Create : 2019-08-01 15:46
 */
public interface IFactory {

    Character createCharacter();

    SpCharacter createSpCharacter();
}

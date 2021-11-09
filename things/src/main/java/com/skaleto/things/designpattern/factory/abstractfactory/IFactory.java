package com.skaleto.things.designpattern.factory.abstractfactory;

import com.skaleto.things.designpattern.factory.Character;
import com.skaleto.things.designpattern.factory.SpCharacter;

/**
 * @author : ybyao
 * @Create : 2019-08-01 15:46
 */
public interface IFactory {

    Character createCharacter();

    SpCharacter createSpCharacter();
}

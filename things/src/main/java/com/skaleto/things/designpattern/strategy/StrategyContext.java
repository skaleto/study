package com.skaleto.things.designpattern.strategy;

/**
 * @author : ybyao
 * @Create : 2019-08-01 19:42
 */
public class StrategyContext {

    IStrategy strategy;

    public StrategyContext(IStrategy strategy) {
        this.strategy = strategy;
    }

    public void battle() {
        strategy.battle();
    }


}

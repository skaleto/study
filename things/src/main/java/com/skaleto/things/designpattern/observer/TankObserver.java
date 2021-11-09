package com.skaleto.things.designpattern.observer;

/**
 * @author : ybyao
 * @Create : 2019-08-01 20:00
 */
public class TankObserver extends Observer {

    public TankObserver(Subject subject){
        this.subject=subject;
    }

    public void notifyOb() {
        System.out.println(subject.state);
    }
}

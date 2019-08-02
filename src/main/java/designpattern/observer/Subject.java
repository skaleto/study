package designpattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : ybyao
 * @Create : 2019-08-01 19:58
 */
public class Subject {

    private List<Observer> observers = new ArrayList<Observer>();
    int state;

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.notifyOb();
        }
    }

    public static void main(String[] args) {
        Subject subject=new Subject();
        new TankObserver(subject);
        subject.notifyObservers();
    }
}

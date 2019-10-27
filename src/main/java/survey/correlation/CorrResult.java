package survey.correlation;


import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : ybyao
 * @Create : 2019-10-23 14:32
 */
public class CorrResult {

    private int matrixOffset;

    private double angle;

    private double inner;

    private double cos;

    public int getMatrixOffset() {
        ReentrantLock lock=new ReentrantLock();
        lock.lock();
        return matrixOffset;
    }

    public CorrResult setMatrixOffset(int matrixOffset) {
        this.matrixOffset = matrixOffset;
        return this;
    }

    public double getAngle() {
        return angle;
    }

    public CorrResult setAngle(double angle) {
        this.angle = angle;
        return this;
    }

    public double getInner() {
        return inner;
    }

    public CorrResult setInner(double inner) {
        this.inner = inner;
        return this;
    }

    public double getCos() {
        return cos;
    }

    public CorrResult setCos(double cos) {
        this.cos = cos;
        return this;
    }

    @Override
    public String toString() {
        return "CorrResult{" +
                "matrixOffset=" + matrixOffset +
                ", angle=" + angle +
                ", inner=" + inner +
                ", cos=" + cos +
                '}';
    }
}

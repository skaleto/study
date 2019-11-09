package multithread;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author : ybyao
 * @Create : 2019-11-08 17:21
 */
public class ForkTask extends RecursiveTask<Integer> {
    /**
     * The main computation performed by this task.
     *
     * @return the result of the computation
     */
    @Override
    protected Integer compute() {
        return null;
    }

    public static void main(String[] args) {
        //创建ForkJoinPool，默认会根据CPU核心数创建线程池的大小
        ForkJoinPool pool = new ForkJoinPool();


    }
}

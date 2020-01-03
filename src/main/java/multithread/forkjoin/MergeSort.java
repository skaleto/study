package multithread.forkjoin;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author : ybyao
 * @Create : 2019-11-15 15:50
 */
public class MergeSort {

    private static int MAX = 10000;

    private static int inits[] = new int[MAX];

    // 随机生成10000个用于排序的数据
    static {
        Random r = new Random();
        for (int index = 1; index <= MAX; index++) {
            inits[index - 1] = r.nextInt(10000000);
        }
    }


    public static void main(String[] args) {
        //创建ForkJoinPool，默认会根据CPU核心数创建线程池的大小
        ForkJoinPool pool = new ForkJoinPool();
        SplitAndMergeTask task = new SplitAndMergeTask(inits);

//        Collections.sort();

        Future<int[]> result = pool.submit(task);
        try {
            System.out.println(Arrays.toString(result.get()));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }



}

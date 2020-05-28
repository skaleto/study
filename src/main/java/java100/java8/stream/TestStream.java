package java100.java8.stream;

import org.junit.Test;

import java.awt.geom.Point2D;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class TestStream {

    private static double calc(List<Integer> ints) {
        //临时中间集合
        List<Point2D> point2DList = new ArrayList<>();
        for (Integer i : ints) {
            point2DList.add(new Point2D.Double((double) i % 3, (double) i / 3));
        }
        //临时变量，纯粹是为了获得最后结果需要的中间变量
        double total = 0;
        int count = 0;

        for (Point2D point2D : point2DList) {
            //过滤
            if (point2D.getY() > 1) {
                //算距离
                double distance = point2D.distance(0, 0);
                total += distance;
                count++;
            }
        }
        //注意count可能为0的可能
        return count > 0 ? total / count : 0;
    }

    //转换为lambda后↓
    private static double calcLambda(List<Integer> ints) {
        return ints.stream()
                //映射为点
                .map(i -> new Point2D.Double((double) i % 3, (double) i / 3))
                //过滤y大于1的点
                .filter(p -> p.getY() > 1)
                //映射为Double
                .mapToDouble(p -> p.distance(0, 0))
                //取平均，这里的结果是一个OptionalDouble，可以避免空指针
                .average()
                //判空
                .orElse(0);
    }


    private static ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();

    public static void testMapCompute() {
        //computeIfAbsent将检查map中是否有对应key的项，如果没有，则执行lambda表达式，将生成的项作为该key的value加入map
        map.computeIfAbsent("a",
                i -> new Object()
        );
        System.out.println(map.size());
    }


    /**
     * 测试并行流
     */
    @Test
    public void testParallel() {
        IntStream.rangeClosed(1, 100).parallel().forEach(i -> {
            System.out.println(LocalDateTime.now() + " : " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        });
    }


    @Test
    public void completableFuture() throws InterruptedException, ExecutionException {
        int taskCount = 10000;
        int threadCount = 20;
        //总操作次数计数器
        AtomicInteger atomicInteger = new AtomicInteger();
        //自定义一个并行度=threadCount的ForkJoinPool
        ForkJoinPool forkJoinPool = new ForkJoinPool(threadCount);
        //使用CompletableFuture.runAsync通过指定线程池异步执行任务
        CompletableFuture.runAsync(() -> IntStream.rangeClosed(1, taskCount).parallel().forEach(i -> atomicInteger.incrementAndGet()), forkJoinPool).get();
        //查询计数器当前值
        System.out.println(atomicInteger.get());
    }

}

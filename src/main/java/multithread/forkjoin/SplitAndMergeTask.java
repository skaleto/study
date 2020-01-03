package multithread.forkjoin;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

/**
 * @author : ybyao
 * @Create : 2019-11-08 17:21
 * <p>
 * 尝试使用fork/join框架来实现一个归并排序
 * *归并排序：将数组递归等分成两份，直到等分为长度1或2的数组，进行直接比较后将多组数据组合起来
 */
public class SplitAndMergeTask extends RecursiveTask<int[]> {

    private int[] source;

    public SplitAndMergeTask(int[] source) {
        this.source = source;
    }

    @Override
    protected int[] compute() {
        //假如长度大于2，则分割成两个
        if (source.length > 2) {
            int middleIndex = source.length / 2;
            int[] firstSource = Arrays.copyOfRange(source, 0, middleIndex);
            int[] secondSource = Arrays.copyOfRange(source, middleIndex, source.length);
            SplitAndMergeTask task1 = new SplitAndMergeTask(firstSource);
            SplitAndMergeTask task2 = new SplitAndMergeTask(secondSource);

            task1.fork();
            task2.fork();

            int[] firstRes = task1.join();
            int[] secondRes = task2.join();

            return combineAndSortArrays(firstRes, secondRes);

        } else {
            //数组长度可能为1可能为2
            if (source.length == 1 || source[0] <= source[1]) {
                return source;
            } else {
                return new int[]{source[1], source[0]};
            }
        }
    }

    private int[] combineAndSortArrays(int[] a, int[] b) {
        //创建一个新的数组，使用插入排序方式将ab数据插入
        int[] newArray = new int[a.length + b.length];

        //以新数组的index为准开始遍历，取每个数组当前索引处的值比较，较小的放入新数组，并右移较小数所在的数组索引，直到填满新数组
        //注意，当某个数组的当前index已经超过了该数组的最大索引，则当前数组的值可以设置为最大值，以便进行比较
        for (int i = 0, index1 = 0, index2 = 0; i < newArray.length; i++) {
            int value1 = index1 < a.length ? a[index1] : Integer.MAX_VALUE;
            int value2 = index2 < b.length ? b[index2] : Integer.MAX_VALUE;
            if (value1 < value2) {
                newArray[i] = value1;
                index1++;
            } else {
                newArray[i] = value2;
                index2++;
            }
        }

        return newArray;
    }
}

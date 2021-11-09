package com.skaleto.things.leet;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 123
 * 输出: 321
 *  示例 2:
 * <p>
 * 输入: -123
 * 输出: -321
 * 示例 3:
 * <p>
 * 输入: 120
 * 输出: 21
 * 注意:
 * <p>
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q4 {

    public static void main(String[] args) {
        Q4.reverse(1534236469);
    }

    public static int reverse(int x) {
        int ret = 0;

        while (x != 0) {
            //x模10为取右侧第一位数字
            int pop = x % 10;
            //x除10位去掉右侧第一位数字后的数值
            x = x / 10;

            if ((ret == Integer.MAX_VALUE / 10 && pop > 7)
                    || ret > Integer.MAX_VALUE / 10) {
                return 0;
            }

            if ((ret == Integer.MIN_VALUE / 10 && pop < -8)
                    || ret < Integer.MIN_VALUE / 10) {
                return 0;
            }

            ret = ret * 10 + pop;

        }

        return ret;
    }
}

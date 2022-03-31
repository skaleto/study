package com.skaleto.things.leet;

import java.util.Stack;

public class Q331 {

    public boolean isValidSerialization(String preorder) {
        String[] pre = preorder.split(",");
        Stack<String> deque = new Stack<>();

        for (int i = 0; i < pre.length; i++) {
            String s = pre[i];

            deque.push(s);

            while (deque.size() >= 3) {
                String top1 = deque.pop();
                String top2 = deque.pop();
                String top3 = deque.pop();

                if ("#".equals(top1) && "#".equals(top2) && !"#".equals(top3)) {
                    deque.push("#");
                } else {
                    deque.push(top3);
                    deque.push(top2);
                    deque.push(top1);
                    break;
                }

            }
        }

        return deque.size() == 1 && "#".equals(deque.peek());
    }

    public static void main(String[] args) {
        Q331 q = new Q331();
        System.out.println(q.isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"));
//        System.out.println(q.isValidSerialization("9,#,#,1"));
    }
}

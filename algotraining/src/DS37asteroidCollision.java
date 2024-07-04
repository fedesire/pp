import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

/**
 *给定一个整数数组 asteroids，表示在同一行的小行星。
 *
 * 对于数组中的每一个元素，其绝对值表示小行星的大小，正负表示小行星的移动方向（正表示向右移动，负表示向左移动）。每一颗小行星以相同的速度移动。
 *
 * 找出碰撞后剩下的所有小行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞
 * 示例 1：
 *
 * 输入：asteroids = [5,10,-5]
 * 输出：[5,10]
 * 解释：10 和 -5 碰撞后只剩下 10 。 5 和 10 永远不会发生碰撞。
 * 示例 2：
 *
 * 输入：asteroids = [8,-8]
 * 输出：[]
 * 解释：8 和 -8 碰撞后，两者都发生爆炸。
 * @date 2024/5/1 10:24
 */
public class DS37asteroidCollision {
    /*
    * 栈内存有遍历到当前位置，所有可以保留的行星
栈顶负
当前负：两个行星都向左，同向而行，不会碰撞，当前行星直接入栈；
当前正：栈向左，当前向右，距离越来越远，背向而行，不会碰撞，当前行星直接入栈；
栈顶正
当前负：栈顶向右，当前向左，相向而行，一定会碰撞，碰撞检测
当前正：两个都向右，同向而行，不会碰撞，当前星系直接入栈
代码实现：依次遍历所有行星，循环判断当前行星是否爆炸
碰撞检测：当前行星为小于0(左移) && 栈顶大于0(向右移动)说明会碰撞，否则跳出循环
爆炸：当前左行星绝对值小于栈顶绝对值，不会存活，直接结束
不爆炸：当前左行星绝对值大于栈顶，栈顶爆炸，继续循环碰撞检测
跳出循环后，如果当前行星没爆炸就加入栈*/
    public int[] asteroidCollision(int[] asteroids) {
        ArrayDeque<Integer> stack = new ArrayDeque<Integer>();
        for (int aster : asteroids) {// 依次遍历所有行星
            boolean boom = false;
            // 当前行星为小于0(左移) && 栈顶大于0(向右移动) <=> 说明会碰撞
            while (!boom && aster < 0 && !stack.isEmpty() && stack.peek() > 0) {
                boom = stack.peek() >= -aster; // 碰撞后，aster 是否爆炸
                if (stack.peek() <= -aster) {  // 碰撞后，栈顶行星是否爆炸
                    stack.pop();
                }
            }
            if (!boom) {// 当前行星没有爆炸，入栈
                stack.push(aster);
            }
        }
        int size = stack.size();
        int[] ans = new int[size];
        for (int i = size - 1; i >= 0; i--) {
            ans[i] = stack.pop();
        }
        return ans;
//        LinkedList<Integer> list=new LinkedList<>();
//        Collections.reverse(list);
//        // 必须要反转后返回 但是deque没找到方便的reverse方法 list可以用Collections.reverse(list);
//        int[] array = stack.stream().mapToInt(Integer::valueOf).toArray();
//        return array;
    }

    public static void main(String[] args) {
        int[] asteroids = {5,10,-5};
        DS37asteroidCollision ds37asteroidCollision = new DS37asteroidCollision();
        int[] ints = ds37asteroidCollision.asteroidCollision(asteroids);
        for (int i : ints) {
            System.out.println(i);
        }
    }
}

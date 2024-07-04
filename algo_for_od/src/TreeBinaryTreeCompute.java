import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringJoiner;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/19 11:44
 */
/* https://blog.csdn.net/qfc_128220/article/details/134607772
* 输入描述
2行整数，第1行表示二叉树的中序遍历，第2行表示二叉树的前序遍历，以空格分割

例如：

7 -2 6 6 9
6 7 -2 9 6

输出描述
1行整数，表示求和树的中序遍历，以空格分割

例如：

-2 0 20 0 6*/
import java.util.*;

public class TreeBinaryTreeCompute {
    static class TreeNode {
        int num; // 当前节点的值
        int childSum; // 当前节点的左子树+右子树的和
        TreeNode leftChild;
        TreeNode rightChild;

        public TreeNode(int num) {
            this.num = num;
            this.childSum = 0;
            this.leftChild = null;
            this.rightChild = null;
        }
    }

    // 中序遍历序列
    static int[] midOrder;

    // 前序遍历序列
    static int[] preOrder;

    // 记录中序遍历序列中，序列元素值所在位置，本题中可能存在重复元素，因此某个序列元素值可能有多个位置
    static HashMap<Integer, ArrayList<Integer>> midIndexMap = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        midOrder = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        preOrder = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = midOrder.length;
        for (int i = 0; i < n; i++) {
            int num = midOrder[i];
            midIndexMap.putIfAbsent(num, new ArrayList<>());
            midIndexMap.get(num).add(i);
        }

        // 根据中序序列和前序序列还原树结构
        TreeNode root = buildTree(0, n - 1, 0, n - 1);

        // 记录新的二叉树的的中序遍历序列
        StringJoiner sj = new StringJoiner(" ");
        getMidOrder(root, sj);
        System.out.println(sj);
    }

    // 二叉树中序遍历
    public static void getMidOrder(TreeNode root, StringJoiner sj) {
        if (root == null) {
            return;
        }

        // 先遍历左子树
        TreeNode leftChild = root.leftChild;
        if (leftChild != null) {
            getMidOrder(leftChild, sj);
        }

        // 再遍历根
        sj.add(root.childSum + "");

        // 最后遍历右子树
        TreeNode rightChild = root.rightChild;
        if (rightChild != null) {
            getMidOrder(rightChild, sj);
        }
    }

    /**
     * 根据中序遍历序列、前序遍历序列还原树结构
     *
     * @param midL 中序遍历子序列的左边界
     * @param midR 中序遍历子序列的右边界
     * @param preL 前序遍历子序列的左边界
     * @param preR 前序遍历子序列的右边界
     * @return 树结构的根节点
     */
    public static TreeNode buildTree(int midL, int midR, int preL, int preR) {
        // 某个节点（子树）对应一段子序列，如果对应子序列范围不存在，则子树也不存在
        if (preL > preR) return null;

        // 先根据前序遍历序列得到根节点，前序序列的首元素就是根节点
        int rootNum = preOrder[preL];
        TreeNode root = new TreeNode(rootNum);

        // 在中序遍历序列中，找到对应根值的位置，这个位置可能有多个，但是只有一个是正确的
        for (int idx : midIndexMap.get(rootNum)) {
            // 如果对应根值位置越界，则不是正确的
            if (idx < midL || idx > midR) continue;

            // 如果中序的左子树，和前序的左子树不同，则对应根值位置不正确 idx是根节点在中序序列的索引 所以左子树最后一个节点
            // 是idx-1 左子树是[midl,idx-1]所以长度是leftLen
            int leftLen = idx - midL;
            //前序便利根节点是preL 左子树节点从preL+1开始
            if (notEquals(midL, preL + 1, leftLen)) continue;

            // 如果中序的右子树，和前序的右子树不同，则对应根值位置不正确
            int rightLen = midR - idx;
            if (notEquals(idx + 1, preR - rightLen + 1, rightLen)) continue;

            // 找到正确根值位置后，开始分治递归处理左子树和右子树
            root.leftChild = buildTree(midL, idx - 1, preL + 1, preL + leftLen);
            root.rightChild = buildTree(idx + 1, midR, preR - rightLen + 1, preR);

            // 记录该节点：左子树+右子树的和（本题新二叉树节点的值）
            root.childSum =
                    (root.leftChild == null ? 0 : (root.leftChild.num + root.leftChild.childSum))
                            + (root.rightChild == null ? 0 : (root.rightChild.num + root.rightChild.childSum));

            break;
        }

        return root;
    }

    /**
     * 判断两个子数组是否相同（元素相同，顺序可以不同）
     *
     * @param midL 子数组1的左边界
     * @param preL 子数组2的左边界
     * @param size 子数组的长度
     * @return 子数组1和子数组2是否相同
     */
    public static boolean notEquals(int midL, int preL, int size) {
        int[] arr1 = Arrays.stream(Arrays.copyOfRange(midOrder, midL, midL + size)).sorted().toArray();
        int[] arr2 = Arrays.stream(Arrays.copyOfRange(preOrder, preL, preL + size)).sorted().toArray();

        for (int i = 0; i < size; i++) {
            if (arr1[i] != arr2[i]) {
                return true;
            }
        }

        return false;
    }
}

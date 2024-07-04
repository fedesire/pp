/**
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 * @date 2024/1/15 11:54
 */
public class LBT114flatten {
    /*递归+回溯的思路，将前序遍历反过来遍历，那么第一次访问的就是前序遍历中最后一个节点。那么可以调整最后一个节点，
    再将最后一个节点保存到pre里，再调整倒数第二个节点，将它的right指针设置为pre，再调整倒数第三个节点，依次类推直到
    调整完毕。*/
    TreeNode pre=null;
    public void flatten(TreeNode root) {
        if(root==null)
            return ;
        flatten(root.right);
        flatten(root.left);
        //因为前面root.right root.left已经递归函数结束了(.left .right都已经处理完毕） 所以此时改变不会影响
        // 自己这一轮递归结束后要么回到他旁边的左孩子开始进入递归 要么回到他的父节点继续完成递归函数 都不会有影响
        root.right=pre;
        root.left=null;
        pre=root;
    }

    public void flatten1(TreeNode root){
        if(root==null)
            return ;
        //将左子树展开为链表
        flatten1(root.left);
        //将右子树展开为链表
        flatten1(root.right);
        //接下来需要将右子树的开头接到左子树链表的结尾
        TreeNode node=root.left;
        if(node!=null){ // 如果左子树不为空才需要执行下面的操作 如果左子树为空 那右子树已经展开为链表了 结果就有了
            // while循环结束后的node即为左子树链表的结尾
            while(node.right!=null)
                node=node.right;
            // 将右子树的开头接到左子树链表的结尾
            node.right=root.right;
            //将左子树转移到右子树 左子树指向空
            root.right=root.left;
            root.left=null;
        }
    }
}

import java.util.Scanner;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/16 15:27
 */
/*
* 定义构造三叉搜索树规则如下：
每个节点都存有一个数，当插入一个新的数时，从根节点向下寻找，直到找到一个合适的空节点插入。查找的规则是：
如果数小于节点的数减去500，则将数插入节点的左子树
如果数大于节点的数加上500，则将数插入节点的右子树
否则，将数插入节点的中子树
给你一系列数，请按以上规则，按顺序将数插入树中，构建出一棵三叉搜索树，最后输出树的高度。
输入描述
第一行为一个数 N，表示有 N 个数，1 ≤ N ≤ 10000
第二行为 N 个空格分隔的整数，每个数的范围为[1,10000]
输出描述
输出树的高度（根节点的高度为1）
输入	9
5000 2000 5000 8000 1800 7500 4500 1400 8100
输出	4
* */
public class TreeTriangle {
    static class TreeNode{
        int val;
        // 节点所在的高度
        int height;
        TreeNode left;
        TreeNode mid;
        TreeNode right;
        TreeNode(int val){
            this.val=val;
        }

    }
    static class Tree{
        TreeNode root;
        int height;

        public void add(int value) {
            TreeNode node=new TreeNode(value);
            if(this.height==0){
                node.height=1;
                root=node;
                this.height=1;
            }
            else {
                // 如果树不是空的，则从根节点开始比较
                TreeNode cur=root;
                while(true){
                    // 假设创建的节点node是当前节点cur的子节点，则node节点高度值=cur节点高度值+1
                    node.height = cur.height + 1;
                    // 如果创建的node进入新层，则更新树的高度
                    this.height=Math.max(this.height,node.height);
                    // 寻找node节点应该插入的位置
                    if(value<cur.val-500){
                        if(cur.left==null){
                            cur.left=node;
                            break;
                        }
                        else cur=cur.left;
                    }
                    else if(value>cur.val+500){
                        if(cur.right==null){
                            cur.right=node;
                            break;
                        }
                        else cur=cur.right;
                    }
                    else{
                        if(cur.mid==null){
                            cur.mid=node;
                            break;
                        }
                        else cur=cur.mid;
                    }
                }

            }
        }
    }
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        Tree tree=new Tree();
        while(true){
            try{
                int n=scanner.nextInt();
                for (int i = 0; i < n; i++) {
                    tree.add(scanner.nextInt());
                }
                System.out.println(tree.height);
            }catch(Exception e){
                break;
            }
        }
    }
}

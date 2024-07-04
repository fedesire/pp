/*前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，
例如自动补完和拼写检查。请你实现 Trie 类：*/
public class Trie {
    // 这里的TreeNode 节点上不存储字母 而是节点的连线即边存储字母
    class TreeNode{
        TreeNode[] next=new TreeNode[26]; //通过new TreeNode()创建对象时会进行默认初始化和显示初始化 默认初始化使得flag
        //初始化为false next先默认初始化为null 因为有 TreeNode[] next=new TreeNode[26] 所以还会进行显示初始化 即next
        // 里面存的是一个地址值 该地址指向一个26size的数组 数组里的每个元素是null
        boolean flag; //flag用来表示当前节点处的字符是否是一个结束字符

        //这三个成员变量没有用 是我为了测试不同类型的变量创建对象时初始化的不同情况 a初始化后就是null b初始化后就是一个大小
        //为2的数组 数组里面每个元素是0  c初始化后是'\u0000' 表示空字符
        int[] a;
        int[] b=new int[2];
        char c;
    }
    TreeNode root;
    public Trie() {
        root=new TreeNode();
    }

    public void insert(String word) {
        char[] arr=word.toCharArray();
        TreeNode cur=root;
        for (int i = 0; i < arr.length; i++) {
            int index=arr[i]-'a';
            if(cur.next[index]==null){
                cur.next[index]=new TreeNode();
            }
            cur=cur.next[index];

        }
        cur.flag=true;
    }

    public boolean search(String word) {
        char[] arr=word.toCharArray();
        TreeNode cur=root;
        for (int i = 0; i < arr.length; i++) {
            TreeNode node=cur.next[arr[i]-'a'];
            if(node==null) return false;
            cur=node;
        }
        return cur.flag;
    }

    public boolean startsWith(String prefix) {
        char[] arr=prefix.toCharArray();
        TreeNode cur=root;
        for (int i = 0; i < arr.length; i++) {
            TreeNode node=cur.next[arr[i]-'a'];
            if(node==null) return false;
            cur=node;
        }
        return true;
    }

    public static void main(String[] args) {
        Trie trie=new Trie();
        trie.insert("apple");
        trie.search("apple");
    }
}


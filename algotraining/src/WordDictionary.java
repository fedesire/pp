/**
 * 请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。实现词典类 WordDictionary ：
 * WordDictionary() 初始化词典对象  void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
 * bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回  false 。word 中可能包含一些 '.'
 * ，每个 . 都可以表示任何一个字母。
 * @date 2024/2/14 11:22
 */
class WordDictionary {
    class TreeNode{
        TreeNode[] children=new TreeNode[26];
        boolean endFlag;
    }
    TreeNode root;

    public WordDictionary() {
        root=new TreeNode();
    }

    public void addWord(String word) {
        TreeNode cur=root;
        for(int i=0;i<word.length();i++){
            int index=word.charAt(i)-'a';
            if(cur.children[index]==null){
                cur.children[index]=new TreeNode();
            }
            cur=cur.children[index];
        }
        cur.endFlag=true;

    }

    public boolean search(String word) {
        return dfs(word,0,root);

    }

    //在node的子节点上查找能否找到能匹配word下标为index位置的字符
    public boolean dfs(String word,int index ,TreeNode node){
        if(index==word.length()) return node.endFlag;
        char ch=word.charAt(index);
        if(ch!='.'){
            int childrenIndex=ch-'a';
            return node.children[childrenIndex]!=null&&dfs(word,index+1,node.children[childrenIndex]);
        }
        //如果当前字符是点号，由于点号可以表示任何字母，因此需要对当前结点的所有非空子结点继续搜索下一个字符。
        else{
            for(int i=0;i<26;i++){
                if(node.children[i]!=null&&dfs(word,index+1,node.children[i]))
                    return true;
            }
        }

        return false;
    }
}


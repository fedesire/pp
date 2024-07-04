import java.util.*;

/**
 * 字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列 beginWord -> s1 -> s2 -> ... -> sk：
 *
 * 每一对相邻的单词只差一个字母。
 *  对于 1 <= i <= k 时，每个 si 都在 wordList 中。注意， beginWord 不需要在 wordList 中。
 * sk == endWord
 * 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，返回 从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。
 * 如果不存在这样的转换序列，返回 0 。
 *
 *
 * 示例 1：
 *
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出：5
 * 解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
 * @date 2024/5/2 11:02
 */
public class DBFS127LadderLength {
    // https://leetcode.cn/problems/word-ladder/solutions/473761/shou-hua-tu-jie-127-dan-ci-jie-long-bfsde-dian-x-2/
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        if (set.isEmpty() || !set.contains(endWord)) {
            return 0;
        }
        Queue<String> queue = new LinkedList<>();
        int level = 1;
        queue.offer(beginWord);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String currWord = queue.poll();
                if (currWord.equals(endWord)) {
                    return level;
                }

                for (char j = 'a'; j <= 'z'; j++) {
                    for (int k = 0; k < currWord.length(); k++) {
                        String newWord = currWord.substring(0, k) + j + currWord.substring(k + 1);
                        if (set.contains(newWord)) {
                            queue.offer(newWord);
                            set.remove(newWord);
                        }
                    }
                }
            }
            level++;
        }
        return 0;
    }

    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        if (set.isEmpty() || !set.contains(endWord)) {
            return 0;
        }
        Queue<String> queue = new LinkedList<>();
        int level = 1;
        queue.offer(beginWord);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String currWord = queue.poll();
                if (currWord.equals(endWord)) {
                    return level;
                }
                // 不能直接对集合进行遍历，因为遍历过程中会删除元素，会报错并发修改异常
                for (String newWord : new ArrayList<>(set)) {
                    if(changeOne(newWord,currWord)) {
                        queue.offer(newWord);
                        set.remove(newWord);
                    }
                }
                // 这样也行
              /*  Iterator<String> iterator = set.iterator();
                while (iterator.hasNext()) {
                    String newWord = iterator.next();
                    if (changeOne(newWord,currWord)) {
                        queue.offer(newWord);
                        iterator.remove();
                    }
                }*/

            }
            level++;
        }
        return 0;
    }

    private boolean changeOne(String str1, String str2) {
        int n = str1.length();
        int c = 0;
        for (int i=0; i < n; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                c++;
            }
        }
        return c == 1;
    }
}

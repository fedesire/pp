import java.util.*;

/**
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 * 示例 1：
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 * 示例 2：
 * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * 输出：false
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
 * @date 2024/5/12 10:00
 */
class DBFS207CanFinish {
    /*这种叫 有向无环图，把一个 有向无环图 转成 线性的排序 就叫 拓扑排序。
有向图有 入度 和 出度 的概念：
如果存在一条有向边 A --> B，则这条边给 A 增加了 1 个出度，给 B 增加了 1 个入度。

作者：笨猪爆破组
链接：https://leetcode.cn/problems/course-schedule/solutions/250377/bao-mu-shi-ti-jie-shou-ba-shou-da-tong-tuo-bu-pai-/
*/
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 入度数组记录的是课程A依赖几门课 map记录的是课程A被哪些课程依赖 即课程A完成后 哪些课程可能可以达到条件能完成了

        //定义入度数组，索引处（课程号）对应入度，比如课程0的入度为0
        int[] inDegree = new int[numCourses];
        //定义map数组，key课程号，value：依赖key的课程号，比如key为1，依赖的value为3，4
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < prerequisites.length;i++){
            //遍历依赖关系表；在入度数组对应索引处++
            inDegree[prerequisites[i][0]]++;
            //没有对map初始化，这里对map初始化一个list数组，存放依赖的课程
            map.putIfAbsent(prerequisites[i][1],new ArrayList<>());
            //在对应被依赖课程key处添加依赖key的课程
            map.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        //新建列表，把入度为0的课放进来
        Queue<Integer> que = new LinkedList<>();
        for(int i = 0 ; i <inDegree.length;i++){
            if(inDegree[i]==0){
                que.offer(i);
            }
        }

        while(!que.isEmpty()){
            //弹出已选课程，在map找到依赖它的课程，在入度数组--
            int course = que.poll();
            numCourses--;
            for(int nextCourse : map.getOrDefault(course,new ArrayList<>())){
                if(--inDegree[nextCourse]==0){
                    que.offer(nextCourse);
                }
            }
        }
        return numCourses==0;
    }
}
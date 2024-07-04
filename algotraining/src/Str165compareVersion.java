/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/5/1 11:54
 */
public class Str165compareVersion {
    public int compareVersion(String version1, String version2) {
        // 注意这里不能直接用.匹配，因为.是正则表达式中的特殊字符，需要转义
        String[] arr1=version1.split("\\.");
        String[] arr2=version2.split("\\.");
        int m=arr1.length,n=arr2.length;
        int i=0;
        while(i<Math.max(m,n)){
            String x=i<m?arr1[i]:"0";
            String y=i<n?arr2[i]:"0";
            // Integer.parseInt会将前导0去掉
            int res=Integer.compare(Integer.parseInt(x),Integer.parseInt(y));
            if(res!=0)
                return res;
            i++;
        }
        return 0;
    }

    public static void main(String[] args) {
        Str165compareVersion s=new Str165compareVersion();
        System.out.println(s.compareVersion("7.5.2.4","7.5.3"));
    }
}

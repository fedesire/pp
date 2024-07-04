/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/29 22:25
 */
public class Ar605Flower {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count=0;
        int m=flowerbed.length;
        for(int i=0;i<m;i++){
            if(flowerbed[i]==0&&(i==0||flowerbed[i-1]==0)&&(i==m-1||flowerbed[i+1]==0)){
                flowerbed[i]=1;
                count++;
            }
        }
        return count>=n;
    }
}
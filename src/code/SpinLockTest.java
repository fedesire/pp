package code;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/13 22:58
 */
public class SpinLockTest {
    AtomicBoolean available=new AtomicBoolean(false);
    void lock(){
        // 循环检测尝试获取锁
        while(!tryLock()){

        }
    }

    private boolean tryLock() {
       return available.compareAndSet(false,true);
    }
    void unLock(){
        if (available.compareAndSet(true,false)) {
            throw new RuntimeException("释放锁失败");
        }
    }
}

// package code;
//
// import javax.annotation.Resource;
// import java.util.List;
//
// /**
//  * @author xqi
//  * @version 1.0
//  * @description TODO
//  * @date 2024/5/18 17:04
//  */
//
// import com.finchina.example.entity.User;
// import com.finchina.example.service.UserService;
// import org.springframework.data.redis.core.RedisTemplate;
// import org.springframework.data.redis.core.ValueOperations;
//
// import javax.annotation.Resource;
// import java.util.List;
// import java.util.concurrent.CompletableFuture;
// import java.util.concurrent.ThreadPoolExecutor;
// import java.util.stream.Collectors;
//
// // 没有做非空判断
// // 每次循环都要进行 Redis 查询，大量的 Redis 查询会导致性能瓶颈。 改为使用Redis批量查询 ，减少网络往返次数。
// // 发送消息的方法可能是耗时操作，如果是同步调用，会导致线程阻塞。
// public class SendMsgService {
//
//     @Resource
//     private UserService userService;
//
//     @Resource
//     private RedisTemplate<String, String> redisTemplate;
//
//     @Resource
//     private ThreadPoolExecutor threadPoolExecutor;
//
//     public void send(List<String> userIds) {
//         List<User> userList = userService.findAllUsersByIds(userIds);
//         // 批量查询 Redis 状态，减少网络往返次数
//         List<String> onlineKeys = userList.stream()
//                 .map(user -> "online:" + user.getId())
//                 .collect(Collectors.toList());
//         List<String> onlineUserIds = redisTemplate.opsForValue().multiGet(onlineKeys).stream()
//                 .filter(status -> status != null)
//                 .collect(Collectors.toList());
//         if(onlineUserIds==null||onlineUserIds.isEmpty()) return ;
//
//         if (userList != null && !userList.isEmpty()) {
//             for (User user : userList) {
//                 if (onlineUserIds.contains("online:" + user.getId())) {
//                     // 调用第三方推送信息的方法
//                     threadPoolExecutor.execute(()->{
//                         sendMsg(user.getPhone());
//                     });
//
//                 }
//             }
//         }
//     }
//
//     private boolean isUserOnline(String userId) {
//         // 检查用户是否在线
//         return stringOperations.hasKey("online:" + userId) &&
//                 stringOperations.get("online:" + userId) != null;
//     }
//
//     // 假设的sendMsg方法实现
//     private void sendMsg(String phone) {
//         // 实现推送逻辑
//     }
// }
//
// import com.finchina.example.entity.User;
// import com.finchina.example.service.UserService;
// import org.springframework.data.redis.core.RedisTemplate;
// import javax.annotation.Resource;
//
// public class SendMsgService {
//     @Resource
//     UserService userService;
//
//     @Resource
//     RedisTemplate<String, String> redisTemplate;
//
//     public void send(List<String> UserIds) {
//         List<User> userPhones = userService.findAllPhone(UserIds);
//         for (User user : userPhones) {
//             // 这里是为了校验用户是否在线，redis中存在online表示在线
//             if (redisTemplate.opsForValue().get("online_" + user.getId())) {
//                 //调用第三方推送信息的伪方法
//                 sendMsg(user.getPhone());
//             }
//         }
//     }
// }
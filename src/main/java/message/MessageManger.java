package message;//=======================================================
//		          .----.
//		       _.'__    `.
//		   .--(^)(^^)---/!\
//		 .' @          /!!!\
//		 :         ,    !!!!
//		  `-..__.-' _.-\!!!/
//		        `;_:    `"'
//		      .'"""""`.
//		     /,  ya  ,\\
//		    // 狗神保佑 \\
//		    `-._______.-'
//		    ___`. | .'___
//		   (______|______)
//=======================================================

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * [Zhihu]https://www.zhihu.com/people/Sweets07
 * [Github]https://github.com/MatrixSeven
 * Created by seven on 2018/9/4.
 */

public class MessageManger {
    private static final Map<EventType, List<SubscribeInterface>> subs=new HashMap<>();

    private static final ExecutorService newFixedThreadPool= Executors.newFixedThreadPool(2);

    private static final DefaultPublishImpl defaultPublish=new DefaultPublishImpl();

    static {

    }
    private static void RunnerEventPublic(EventRunner eventRunner){
        newFixedThreadPool.execute(eventRunner);

    }

    public static void PublishEvent(EventType eventType,Message message){
        System.out.println("发布消息:"+eventType);
        subs.getOrDefault(eventType,new ArrayList<>()).stream()
                .map(it->EventRunner.create(it, eventType, message))
                .forEach(MessageManger::RunnerEventPublic);
    }
    public static void PublishEvent(Message message){
        PublishEvent(message.getType(), message);
    }
    public static void PublishEvent(EventType eventType){
        PublishEvent(eventType,null);
    }
    public static void RegEvent(EventType eventType,SubscribeInterface subscribeInterface){
        List<SubscribeInterface> orDefault = subs.getOrDefault(eventType, new ArrayList<SubscribeInterface>());
        orDefault.add(subscribeInterface);
        subs.putIfAbsent(eventType,orDefault);
    }
}

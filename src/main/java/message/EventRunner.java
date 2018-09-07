package message;

//=======================================================
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

/**
 * [Zhihu]https://www.zhihu.com/people/Sweets07
 * [Github]https://github.com/MatrixSeven
 * Created by seven on 2018/9/4.
 */

public class EventRunner implements Runnable {

    private SubscribeInterface subscribeInterface;
    private EventType type;
    private Message message;

    private EventRunner(SubscribeInterface subscribeInterface, EventType type, Message message) {
        this.subscribeInterface = subscribeInterface;
        this.type = type;
        this.message = message;
    }

    public static EventRunner create(SubscribeInterface subscribeInterface, EventType eventType, Message message){
        return new EventRunner(subscribeInterface,eventType,message);
    }

    @Override
    public void run() {
        System.out.println("收到消息:"+type+"参数:"+message);
        try{
            subscribeInterface.subscribe(type, message);
        }catch (Exception ex){
            System.out.println("执行事件出错,相关信息:"+ex.getMessage());
        }
        System.err.println("执行完毕,线程回收完成");
    }
}

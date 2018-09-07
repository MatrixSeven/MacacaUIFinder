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

public class DefaultRegSubscribeImpl implements RegSubscribeInterface{

    @Override
    public RegSubscribeInterface regSubscribe(EventType eventType, SubscribeInterface subscribeInterface) {
        MessageManger.RegEvent(eventType, subscribeInterface);
        return this;
    }
}

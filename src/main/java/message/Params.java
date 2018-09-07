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

import java.util.HashMap;

/**
 * [Zhihu]https://www.zhihu.com/people/Sweets07
 * [Github]https://github.com/MatrixSeven
 * Created by seven on 2018/9/4.
 */

public class Params extends HashMap<String,Object> {

    public Params put(String key,Object v) {
        super.put(key, v);
        return this;
    }
    public <T> T getParams(Object key) {
        return (T)super.get(key);
    }

    public static Params create(){
        return new Params();
    }
}

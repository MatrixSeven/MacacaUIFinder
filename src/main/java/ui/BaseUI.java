package ui;

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

import message.DefaultRegSubscribeImpl;
import message.EventType;
import message.RegSubscribeInterface;
import message.SubscribeInterface;

import javax.swing.*;

/**
 * [Zhihu]https://www.zhihu.com/people/Sweets07
 * [Github]https://github.com/MatrixSeven
 * Created by seven on 2018/8/29.
 */

public abstract class BaseUI  extends JPanel implements SubscribeInterface{
    static {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
    public static RegSubscribeInterface reg =new DefaultRegSubscribeImpl();

    public BaseUI regSubscribe(EventType eventType) {
        reg.regSubscribe(eventType, this);
        return this;
    }


    public abstract void regProcess();

    public BaseUI() {
        this.initialize();
        this.regProcess();
    }
    abstract protected void initialize();


}

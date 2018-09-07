package ui.event.impl;

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

import message.EventType;
import message.Message;
import message.MessageManger;
import ui.event.UIEventInterface;
import ui.panle.LeftToolPanel;
import ui.panle.setting.LeftToolName;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * [Zhihu]https://www.zhihu.com/people/Sweets07
 * [Github]https://github.com/MatrixSeven
 * Created by seven on 2018/9/4.
 */

public class LeftToolPanelEvent extends UIEventInterface<LeftToolPanel> implements ActionListener {

    public LeftToolPanelEvent(LeftToolPanel lefttoolpanel) {
        super(lefttoolpanel);
    }

    @Override
    public void EventProcess(EventType eventType, Message message) {

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch (actionCommand){
            case LeftToolName.CONFIG :{
                MessageManger.PublishEvent(EventType.OPTION_SETTING_BUTTON);
                break;
            }
            case LeftToolName.RUN:{
                MessageManger.PublishEvent(EventType.OPTION_DOIT_BUTTON);
                break;
            }
        }
    }
}


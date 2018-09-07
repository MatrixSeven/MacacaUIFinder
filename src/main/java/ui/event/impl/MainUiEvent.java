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
import ui.MainUI;
import ui.event.UIEventInterface;

/**
 * [Zhihu]https://www.zhihu.com/people/Sweets07
 * [Github]https://github.com/MatrixSeven
 * Created by seven on 2018/9/4.
 */

public class MainUiEvent extends UIEventInterface<MainUI> {


    public MainUiEvent(MainUI mainUI) {
        super(mainUI);
    }

    @Override
    public void EventProcess(EventType eventType, Message message) {
        switch (eventType){
            case OPTION_DOIT_BUTTON: {
                this.comp.contentPane.remove(comp.settingPanel);
                this.comp.contentPane.add(comp.doItPanel);
                break;
            }
            case OPTION_SETTING_BUTTON: {
                this.comp.contentPane.remove(comp.doItPanel);
                this.comp.contentPane.add(comp.settingPanel);
                break;
            }
        }
        this.comp.contentPane.updateUI();


    }
}

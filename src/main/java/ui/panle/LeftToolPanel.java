package ui.panle;

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
import ui.BaseUI;
import ui.event.impl.LeftToolPanelEvent;
import ui.panle.setting.LeftToolName;

import javax.swing.*;
import java.awt.*;

/**
 * [Zhihu]https://www.zhihu.com/people/Sweets07
 * [Github]https://github.com/MatrixSeven
 * Created by seven on 2018/8/29.
 */

public class LeftToolPanel extends BaseUI {

    public LeftToolPanelEvent leftToolPanelEvent;


    public LeftToolPanel() {

    }


    @Override
    public void subscribe(EventType eventType, Message message) {
        leftToolPanelEvent.EventProcess(eventType, message);

    }

    protected void initialize(){
        leftToolPanelEvent=new LeftToolPanelEvent(this);
        this.setLayout(new FlowLayout(FlowLayout.LEADING));
        this.setPreferredSize(new Dimension(100,100 ));
        this.setBackground(Color.BLACK);
        JButton setting = new JButton(LeftToolName.CONFIG);
        JButton runner = new JButton(LeftToolName.RUN);
        setting.addActionListener(leftToolPanelEvent);
        runner.addActionListener(leftToolPanelEvent);
        this.add(setting);
        this.add(runner);
    }

    @Override
    public void regProcess() {

    }
}












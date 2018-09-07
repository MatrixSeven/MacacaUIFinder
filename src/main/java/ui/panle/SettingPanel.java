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
import ui.event.impl.SettingPanleEvent;
import ui.panle.setting.SettingName;

import javax.swing.*;
import java.awt.*;

/**
 * [Zhihu]https://www.zhihu.com/people/Sweets07
 * [Github]https://github.com/MatrixSeven
 * Created by seven on 2018/8/29.
 */

public class SettingPanel extends BaseUI {

    public JLabel pathText;
    public TextField pathConf;
    public JButton reloadConf;
    public JButton editConf;
    public JButton saveConf;
    public JTextArea conf;
    public JScrollPane confJsc;
    public JPanel top;
    public JPanel left;
    public SettingPanleEvent settingPanleEvent;


    @Override
    protected void initialize() {
        this.newComp();
        this.setComp();
        this.addEvent();
        this.addComp();


    }

    private void newComp() {
        this.settingPanleEvent = new SettingPanleEvent(this);
        this.pathText = new JLabel(SettingName.DEFAULT_PATH_NAME);
        this.pathConf = new TextField(SettingName.DEFAULT_PATH);
        this.reloadConf = new JButton(SettingName.LOAD_CONFIG);
        this.editConf = new JButton(SettingName.EDIT_CONFIG);
        this.saveConf = new JButton(SettingName.SAVE_CONFIG);
        this.conf = new JTextArea();
        this.confJsc = new JScrollPane();

    }

    private void setComp() {
        this.setLayout(null);
        this.top = new JPanel();
        this.left = new JPanel();
        this.pathConf.setEditable(false);
        this.conf.setLineWrap(true);
        this.conf.setEditable(false);
        this.conf.setWrapStyleWord(true);
        this.conf.setPreferredSize(new Dimension(960, 590));
        this.confJsc.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.confJsc.setPreferredSize(new Dimension(960, 500));
        this.confJsc.setBounds(0, 0, 650, 560);
        this.confJsc.setViewportView(conf);
        this.top.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.left.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.top.setBounds(10, 10, 860, 50);
        this.left.setBounds(10, 50, 860, 590);
    }

    private void addComp() {
        this.top.add(pathText);
        this.top.add(pathConf);
        this.top.add(reloadConf);
        this.top.add(editConf);
        this.top.add(saveConf);
        this.left.add(confJsc);
        this.add(top);
        this.add(left);

    }

    private void addEvent() {
        this.saveConf.addActionListener(settingPanleEvent);
        this.editConf.addActionListener(settingPanleEvent);
        this.reloadConf.addActionListener(settingPanleEvent);
    }

    @Override
    public void regProcess() {
        this.regSubscribe(EventType.EDIT_CONFIG_EVENT_DONE)
                .regSubscribe(EventType.EDIT_CONFIG_EVENT_START)
                .regSubscribe(EventType.SAVE_CONFIG_EVENT_DONE)
                .regSubscribe(EventType.SAVE_CONFIG_EVENT_START)
                .regSubscribe(EventType.LOAD_CONFIG_EVENT_START)
                .regSubscribe(EventType.LOAD_CONFIG_EVENT_DONE);
    }

    @Override
    public void subscribe(EventType eventType, Message message) {
        this.settingPanleEvent.EventProcess(eventType, message);
    }
}

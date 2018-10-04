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
import message.Params;
import ui.event.UIEventInterface;
import ui.panle.SettingPanel;
import ui.panle.setting.SettingName;
import utils.Utils;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * [Zhihu]https://www.zhihu.com/people/Sweets07
 * [Github]https://github.com/MatrixSeven
 * Created by seven on 2018/9/4.
 */

public class SettingPanleEvent extends UIEventInterface<SettingPanel> implements AncestorListener, ActionListener {

    boolean isEditing = false;
    String config;

    public SettingPanleEvent(SettingPanel settingPanel) {
        super(settingPanel);
    }


    @Override
    public void EventProcess(EventType eventType, Message message) {
        switch (eventType) {
            case EDIT_CONFIG_EVENT_START: {
                edit_config_event_start(message);
                break;
            }
            case EDIT_CONFIG_EVENT_DONE: {
                edit_config_event_done(message);
                break;
            }
            case SAVE_CONFIG_EVENT_START: {
                save_config_event_start(message);
                break;
            }
            case SAVE_CONFIG_EVENT_DONE:
                save_config_event_done(message);
                break;
            case LOAD_CONFIG_EVENT_START: {
                load_config_event_start(message);
                break;
            }
            case LOAD_CONFIG_EVENT_DONE: {
                load_config_event_done(message);
                break;
            }
        }

    }

    private void edit_config_event_start(Message message) {
        this.isEditing = true;
        this.comp.conf.setEditable(true);

    }

    private void edit_config_event_done(Message message) {
        this.isEditing = false;
        this.comp.conf.setEditable(false);

    }

    private void save_config_event_start(Message message) {
        this.isEditing = false;
        this.comp.conf.setEditable(false);
        Utils.saveConfig(this.comp.conf.getText().trim());
    }

    private void save_config_event_done(Message message) {
        this.isEditing = false;

        boolean save_res = message.getMessage().getParams("save_res");
        String error = message.getMessage().getParams("error");
        if(save_res){
            this.comp.conf.setEditable(false);
            JOptionPane.showMessageDialog(this.comp,
                    "保存成功!！",
                    "提示",
                    JOptionPane.OK_OPTION);
        }else {
            this.comp.conf.setEditable(true);
            JOptionPane.showMessageDialog(this.comp,
                    "保存失败!！失败信息:"+error,
                    "提示",
                    JOptionPane.WARNING_MESSAGE);

        }
    }

    private void load_config_event_start(Message message) {
        if (isEditing) {
            JOptionPane.showMessageDialog(this.comp,
                    "编辑配置文件中,不允许加载配置文件!!！",
                    "警告",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Utils.loadConfig();
    }

    private void load_config_event_done(Message message) {
        Params params = message.getMessage();
        boolean res = params.getParams("load_res");
        if (res) {
            this.config = params.getParams("config");
            this.comp.conf.setText(this.config);
        } else {
            JOptionPane.showMessageDialog(this.comp,
                    "加载配置文件出错了!!！",
                    "警告",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case SettingName.EDIT_CONFIG: {
                MessageManger.PublishEvent(EventType.EDIT_CONFIG_EVENT_START);
                break;
            }
            case SettingName.LOAD_CONFIG: {
                MessageManger.PublishEvent(EventType.LOAD_CONFIG_EVENT_START);
                break;

            }
            case SettingName.SAVE_CONFIG: {
                MessageManger.PublishEvent(EventType.SAVE_CONFIG_EVENT_START);
                break;
            }
        }
    }


    @Override
    public void ancestorRemoved(AncestorEvent event) {

    }

    @Override
    public void ancestorMoved(AncestorEvent event) {

    }

    @Override
    public void ancestorAdded(AncestorEvent event) {
        this.comp.conf.setCaretPosition(this.comp.conf.getText().length());

    }
}

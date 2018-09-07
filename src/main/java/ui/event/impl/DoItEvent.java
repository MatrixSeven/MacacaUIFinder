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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import macaca.client.common.GetElementWay;
import message.EventType;
import message.Message;
import message.MessageManger;
import message.Params;
import ui.event.UIEventInterface;
import ui.panle.DoItPanel;
import ui.panle.setting.DoItName;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * [Zhihu]https://www.zhihu.com/people/Sweets07
 * [Github]https://github.com/MatrixSeven
 * Created by seven on 2018/9/4.
 */

public class DoItEvent extends UIEventInterface<DoItPanel> implements ActionListener , AncestorListener {

    private static Map<String, GetElementWay> getWay;
    private JSONArray config;
    private  GetElementWay getElementWay=GetElementWay.XPATH;
    private  String currentAction=DoItName.FIND_AND_CLICK;
    private  String xPath;

    static {
        getWay = new HashMap<>();
        getWay.put(DoItName.GET_WAY_XPATH, GetElementWay.XPATH);
        getWay.put(DoItName.GET_WAY_ID, GetElementWay.ID);
        getWay.put(DoItName.GET_WAY_NAME, GetElementWay.NAME);
    }


    public DoItEvent(DoItPanel doItPanel) {
        super(doItPanel);
    }


    @Override
    public void EventProcess(EventType eventType, Message message) {

        switch (eventType) {
            case LOAD_CONFIG_EVENT_DONE: {
                load_config_event_done(message);
                break;
            }
            case SWITCH_CONFIG_EVENT_START: {
                switch_config_event_start();
                break;
            }
            case SWITCH_CONFIG_EVENT_DONE: {
                switch_config_event_done(message);
                break;
            }
            case FIND_ELEMENT_EVENT_DONE: {
                find_element_event_done(message);
                break;
            }
            case FIND_ELEMENT_EVENT_START: {
                find_element_event_start();
                break;
            }
        }
    }

    private void find_element_event_start() {
        this.comp.switchConfigRunner.setEnabled(false);
        this.comp.runCommand.setEnabled(false);
    }

    private void find_element_event_done(Message message) {
        this.comp.switchConfigRunner.setEnabled(true);
        this.comp.runCommand.setEnabled(true);
        Params params = message.getMessage();
        String res=params.getParams("res").toString();
        if (comp.findWayResult.getText().length()>1000){
            this.comp.findWayResult.setText("");
        }
        this.comp.findWayResult.append("\n"+res);
        this.comp.findWayResult.setCaretPosition(this.comp.findWayResult.getText().length());
    }

    private void switch_config_event_done(Message message) {
        this.comp.switchConfigRunner.setEnabled(true);
        this.comp.runCommand.setEnabled(true);
        Params params = message.getMessage();
        Boolean b=params.getParams("res");
//        Boolean error=params.getParams("error");
        if(b){
            JOptionPane.showMessageDialog(this.comp,
                    "加载配置成功,并且初始化Macaca成功!!！",
                    "提示",
                    JOptionPane.INFORMATION_MESSAGE);
        }else {
            JOptionPane.showMessageDialog(this.comp,
                    "初始化Macaca失败!!请检查Macaca服务和相关配置信息！",
                    "警告",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void switch_config_event_start() {
//        this.comp.switchConfigRunner.setEnabled(false);
//        this.comp.runCommand.setEnabled(false);
    }


    private void load_config_event_done(Message message) {
        Params params = message.getMessage();
        this.comp.switchConfigRunner.setText(DoItName.SWITCH_BUTTON);
        boolean res = params.getParams("load_res");
        this.comp.currentConfig.removeAllItems();
        if (res) {
            this.config = JSONArray.parseArray(params.get("config").toString());
            config.stream().forEach(it ->
                    comp.currentConfig.addItem(((JSONObject) it).get("name")));
        } else {
            comp.currentConfig.addItem("没有数据");
        }


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case DoItName.RUN_COMMAND_BUTTON: {
                run_command_button();
                break;

            }
            case DoItName.INIT_BUTTON:{
                this.comp.switchConfigRunner.setText(DoItName.SWITCH_BUTTON);
                MessageManger.PublishEvent(EventType.LOAD_CONFIG_EVENT_START);
                break;
            }
            case DoItName.SWITCH_BUTTON:{
                switch_button();
                break;
            }
            case DoItName.GET_WAY_ID:
            case DoItName.GET_WAY_NAME:
            case DoItName.GET_WAY_XPATH:
           {
                this.getElementWay=getWay.get(command);
                break;
            }
            case DoItName.ONLY_FIND:
            case DoItName.FIND_AND_CLICK:
            case DoItName.FIND_AND_SEND_KEYS:
            case DoItName.FIND_AND_SEND_KEYS_BY_DRIVER:{
                this.currentAction =command;
                break;
            }
        }

    }

    private void switch_button() {
        this.comp.runCommand.setEnabled(false);
        this.comp.switchConfigRunner.setEnabled(false);
        int  actionCommand = this.comp.currentConfig.getSelectedIndex();
            MessageManger.PublishEvent(Message.
               Create(EventType.SWITCH_CONFIG_EVENT_START,
                Params.create()
                .put("config",config.get(actionCommand))));

    }

    private void run_command_button() {
        this.comp.runCommand.setEnabled(false);
        this.comp.switchConfigRunner.setEnabled(false);
        xPath =this.comp.xpath.getText().trim();
        String sendkeys=this.comp.sendkeys.getText().trim();
        MessageManger.PublishEvent(Message.Create(EventType.FIND_ELEMENT_EVENT_START,
                Params.create()
                        .put("getWay", getElementWay)
                        .put("action", currentAction)
                        .put("xpath", xPath)
                        .put("sendkeys", sendkeys)));
    }

    @Override
    public void ancestorAdded(AncestorEvent event) {
        this.comp.findWayResult.setCaretPosition(this.comp.findWayResult.getText().length());
    }

    @Override
    public void ancestorRemoved(AncestorEvent event) {

    }

    @Override
    public void ancestorMoved(AncestorEvent event) {
        this.comp.findWayResult.setCaretPosition(this.comp.findWayResult.getText().length());
    }
}

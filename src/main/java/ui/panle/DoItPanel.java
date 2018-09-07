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
import ui.event.impl.DoItEvent;
import ui.panle.setting.DoItName;

import javax.swing.*;

import java.awt.*;

import static message.EventType.*;

/**
 * [Zhihu]https://www.zhihu.com/people/Sweets07
 * [Github]https://github.com/MatrixSeven
 * Created by seven on 2018/8/29.
 */

public class DoItPanel extends BaseUI {

    public JLabel currentLabel;
    public JComboBox currentConfig;
    public JComboBox findWay;
    public JButton switchConfigRunner;
    public JButton runCommand;
    public JRadioButton onlyFind;
    public JRadioButton findAndClick;
    public JRadioButton findAndSendKeys;
    public JRadioButton findAndSendKeysByDriver;
    public JRadioButton getByXpath;
    public JRadioButton getByName;
    public JRadioButton getById;
    public JLabel getByLabel;
    public JLabel actionLabel;
    public ButtonGroup actionGroup;
    public ButtonGroup getByGroup;
    public JLabel sendKeysLabel;
    public JTextField sendkeys;
    public JTextArea xpath;
    public JTextArea findWayResult;
    public DoItEvent event;
    public JScrollPane findWayResultJsp;



    @Override
    protected void initialize() {
        newComp();
        setComp();
        addEvent();
        addComp();

    }


    private void newComp(){
        xpath=new JTextArea();
        findWayResult=new JTextArea();
        event=new DoItEvent(this);
        sendkeys=new JTextField();
        currentLabel =new JLabel(DoItName.CONFIG_NAME);
        currentConfig =new JComboBox();
        findWay=new JComboBox();
        findWayResultJsp=new JScrollPane();
        actionGroup=new ButtonGroup();
        switchConfigRunner=new JButton(DoItName.INIT_BUTTON);
        runCommand=new JButton(DoItName.RUN_COMMAND_BUTTON);
        onlyFind=new JRadioButton(DoItName.ONLY_FIND);
        findAndClick=new JRadioButton(DoItName.FIND_AND_CLICK);
        findAndSendKeys=new JRadioButton(DoItName.FIND_AND_SEND_KEYS);
        findAndSendKeysByDriver=new JRadioButton(DoItName.FIND_AND_SEND_KEYS_BY_DRIVER);
        getByGroup=new ButtonGroup();
        getById=new JRadioButton(DoItName.GET_WAY_ID);
        getByName=new JRadioButton(DoItName.GET_WAY_NAME);
        getByXpath=new JRadioButton(DoItName.GET_WAY_XPATH);
        sendKeysLabel =new JLabel(DoItName.SEND_KEYS_LABEL);
        getByLabel =new JLabel(DoItName.GET_BY_LABEL);
        actionLabel =new JLabel(DoItName.ACTION_LABEL);
    }

    private void setComp() {
        this.setLayout(null);
        sendkeys.setColumns(20);
        xpath.setColumns(300);
        xpath.setLineWrap(true);
        xpath.setWrapStyleWord(true);
        xpath.setPreferredSize(new Dimension(400, 1000));
        findWayResult.setPreferredSize(new Dimension(400, 430));
        findWayResult.setEditable(false);
        findWayResult.setColumns(3000);
        findWayResult.setLineWrap(true);
        findWayResult.setWrapStyleWord(true);
        findWayResult.setPreferredSize(new Dimension(400, 1000));
        findWayResultJsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        findWayResultJsp.setPreferredSize(new Dimension(400, 430));
//        findWayResultJsp.setBounds(0, 0, 300, 200);
        findWayResultJsp.setViewportView(findWayResult);
        findAndClick.setSelected(true);
        getByXpath.setSelected(true);
        }
    private void addComp() {
        this.getByGroup.add(getById);
        this.getByGroup.add(getByName);
        this.getByGroup.add(getByXpath);
        //======================================================//
        this.actionGroup.add(onlyFind);
        this.actionGroup.add(findAndClick);
        this.actionGroup.add(findAndSendKeys);
        this.actionGroup.add(findAndSendKeysByDriver);
        JPanel j=new JPanel();
        j.setLayout(new FlowLayout(FlowLayout.LEFT));
        j.add(currentLabel);
        j.add(currentConfig);
        j.add(switchConfigRunner);
        j.add(runCommand);
        j.setBounds(10, 5, 1300, 50);
        JPanel x=new JPanel();
        x.setLayout(new FlowLayout(FlowLayout.LEFT));
        x.add(new JLabel("执行动作:"));
        x.add(onlyFind);
        x.add(findAndClick);
        x.add(findAndSendKeys);
        x.add(findAndSendKeysByDriver);
        x.setBounds(10, 50, 1300, 50);
        JPanel y=new JPanel();
        y.setLayout(new FlowLayout(FlowLayout.LEFT));
        y.setBounds(10, 90, 1300, 50);
        y.add(new JLabel("获取方式:"));
        y.add(getById);
        y.add(getByName);
        y.add(getByXpath);
        y.add(new JLabel("发送的内容:"));
        y.add(sendkeys);

        xpath.setBounds(10, 130, 400, 430);
        findWayResultJsp.setBounds(470, 130, 400,430 );
        this.add(xpath);
        this.add(findWayResultJsp);
        this.add(j);
        this.add(x);
        this.add(y);
    }

    private void addEvent() {
       this.currentConfig.addActionListener(event);
       this.runCommand.addActionListener(event);
       this.onlyFind.addActionListener(event);
       this.findAndClick.addActionListener(event);
       this.findAndSendKeys.addActionListener(event);
       this.findAndSendKeysByDriver.addActionListener(event);
       this.getByXpath.addActionListener(event);
       this.getByName.addActionListener(event);
       this.getById.addActionListener(event);
       this.switchConfigRunner.addActionListener(event);
       this.findWayResult.addAncestorListener(event);
    }


    @Override
    public void subscribe(EventType eventType, Message message) {
        event.EventProcess(eventType, message);
    }

    @Override
    public void regProcess() {
        regSubscribe(EventType.LOAD_CONFIG_EVENT_DONE)
            .regSubscribe(FIND_ELEMENT_EVENT_START)
            .regSubscribe(FIND_ELEMENT_EVENT_DONE)
            .regSubscribe(SWITCH_CONFIG_EVENT_DONE);
    }
}

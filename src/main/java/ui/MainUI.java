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

import config.UIConfig;
import macaca.MacacaUtils;
import message.*;
import ui.event.UIEventInterface;
import ui.event.impl.MainUiEvent;
import ui.panle.DoItPanel;
import ui.panle.JMenuBars;
import ui.panle.LeftToolPanel;
import ui.panle.SettingPanel;

import javax.swing.*;
import java.awt.*;

/**
 * [Zhihu]https://www.zhihu.com/people/Sweets07
 * [Github]https://github.com/MatrixSeven
 * Created by seven on 2018/8/29.
 */

public class MainUI extends JFrame implements SubscribeInterface {
    public DoItPanel doItPanel;
    public SettingPanel settingPanel;
    public LeftToolPanel leftToolPanel;
    public JPanel contentPane;
    public JMenuBars jMenuBars;
    public UIEventInterface MainUiEvent;
    public MacacaUtils macacaUtils;

    public MainUI(String title) throws HeadlessException {
        super(title);
        initialize();
        initializeMessage();
    }

    public MainUI() throws HeadlessException {
        this("Macaca元素查找器  {作者:Seven}");
    }

    private void initialize() {
        this.macacaUtils=new MacacaUtils();
        this.MainUiEvent=new MainUiEvent(this);
        this.doItPanel = new DoItPanel();
        this.jMenuBars=new JMenuBars();
        this.settingPanel = new SettingPanel();
        this.leftToolPanel = new LeftToolPanel();
        this.contentPane = new JPanel(true);
        this.contentPane.setLayout(new BorderLayout(1, 2));
        this.contentPane.setPreferredSize(new Dimension(this.getWidth(), this.getWidth()));
        this.contentPane.add(leftToolPanel, BorderLayout.WEST);
        this.contentPane.add(settingPanel, BorderLayout.CENTER);
        this.contentPane.add(doItPanel, BorderLayout.CENTER);
        this.setJMenuBar(jMenuBars);
        this.setContentPane(contentPane);
        this.setDefaultCloseOperation(3);
        this.setSize(UIConfig.WINDOW_WIDTH, UIConfig.WINDOW_HEIGHT);
        this.setResizable(false);
        this.setAlwaysOnTop(false);
        this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 3);
        this.setVisible(true);
        MessageManger.PublishEvent(EventType.LOAD_CONFIG_EVENT_START);

    }

    private void initializeMessage(){
      new DefaultRegSubscribeImpl()
              .regSubscribe(EventType.OPTION_DOIT_BUTTON,this )
              .regSubscribe(EventType.OPTION_SETTING_BUTTON,this);
    }

    @Override
    public void subscribe(EventType eventType, Message message) {
        this.MainUiEvent.EventProcess(eventType, message);
    }
}
























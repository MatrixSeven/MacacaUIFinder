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

/**
 * [Zhihu]https://www.zhihu.com/people/Sweets07
 * [Github]https://github.com/MatrixSeven
 * Created by seven on 2018/9/4.
 */

public enum EventType {
    /**
     * 寻找元素事件开始
     */
    FIND_ELEMENT_EVENT_START("FIND_ELEMENT_EVENT_START"),

    /**
     * 寻找元素事件完成
     */
    FIND_ELEMENT_EVENT_DONE("FIND_ELEMENT_EVENT_DONE"),


    /**
     * 加载配置文件事件开始
     */
    LOAD_CONFIG_EVENT_START("LOAD_CONFIG_EVENT_START"),

    /**
     * 加载配置文件事件完成
     */
    LOAD_CONFIG_EVENT_DONE("LOAD_CONFIG_EVENT_DONE"),

    /**
     * 编辑配置文件事件开始
     */
    EDIT_CONFIG_EVENT_START("EDIT_CONFIG_EVENT_START"),

    /**
     * 编辑配置文件事件开始
     */
    EDIT_CONFIG_EVENT_DONE("EDIT_CONFIG_EVENT_DONE"),

    /**
     * 保存配置文件事件开始
     */
    SAVE_CONFIG_EVENT_START("SAVE_CONFIG_EVENT_START"),

    /**
     * 保存配置文件事件完成
     */
    SAVE_CONFIG_EVENT_DONE("SAVE_CONFIG_EVENT_DONE"),

    /**
     * 切换配置文件事件开始
     */
    SWITCH_CONFIG_EVENT_START("SWITCH_CONFIG_EVENT_START"),

    /**
     * 切换配置文件事件完成
     */
    SWITCH_CONFIG_EVENT_DONE("SWITCH_CONFIG_EVENT_DONE"),

    /**
     * 主页设置按钮触发
     */
    OPTION_SETTING_BUTTON("OPTION_SETTING_BUTTON"),

    /**
     * 主页功能按钮触发
     */
    OPTION_DOIT_BUTTON("OPTION_DOIT_BUTTON");


    public String event;
    EventType(String eventType) {
        event=eventType;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}

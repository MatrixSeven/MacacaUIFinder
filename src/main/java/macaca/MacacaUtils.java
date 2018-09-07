package macaca;

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

import com.alibaba.fastjson.JSONObject;
import macaca.client.MacacaClient;
import macaca.client.commands.Element;
import macaca.client.common.GetElementWay;
import message.*;
import ui.panle.setting.DoItName;

/**
 * [Zhihu]https://www.zhihu.com/people/Sweets07
 * [Github]https://github.com/MatrixSeven
 * Created by seven on 2018/9/4.
 */

public class MacacaUtils implements SubscribeInterface {


    private static MacacaClient macacaClient = null;

    public MacacaUtils() {
        new DefaultRegSubscribeImpl()
                .regSubscribe(EventType.FIND_ELEMENT_EVENT_START, this)
                .regSubscribe(EventType.SWITCH_CONFIG_EVENT_START,this );

    }

    @Override
    public void subscribe(EventType eventType, Message message) {
        switch (eventType) {
            case FIND_ELEMENT_EVENT_START: {
                find_element_event_start(message);
                break;
            }
            case SWITCH_CONFIG_EVENT_START: {
                switch_config_event_start(message);
                break;
            }
        }
    }

    private void switch_config_event_start(Message message) {
        boolean res = true;
        String error="";
        try {
            macacaClient=new MacacaClient();
            JSONObject c = message.getMessage().getParams("config");
            JSONObject config=new JSONObject();
            config.put("desiredCapabilities", c);
            macacaClient.initDriver(config);
        } catch (Exception e) {
            macacaClient=null;
            error=e.getMessage();
            e.printStackTrace();
            res = false;
        }
        MessageManger.PublishEvent(Message.Create(EventType.SWITCH_CONFIG_EVENT_DONE,
                Params.create().put("res", res)));
    }

    private void find_element_event_start(Message message) {
        Params params = message.getMessage();
        MessageManger.PublishEvent(Message.Create(EventType.FIND_ELEMENT_EVENT_DONE,
                runner(params)));
    }
    private Params runner( Params params) {
        GetElementWay getElementWay=params.getParams("getWay");
        String action=params.getParams("action");
        String xpath=params.getParams("xpath");
        String sendkeys=params.getParams("sendkeys");
        Params result = Params.create();
        JSONObject all = new JSONObject();
        try {
            if(macacaClient==null){
                all.put("返回结果", "寻找元素失败,请初始化Macaca-client");
                result.put("res",all );
                return result;
            }
            Element element = macacaClient.getElement(getElementWay, xpath);
            if (element == null) {
                all.put("返回结果", "寻找元素失败");
                result.put("res",all );
                return result;
            }
            all.put("返回结果", "寻找元素成功");
            JSONObject ack = new JSONObject();
            JSONObject info = new JSONObject();
            info.put("text", element.getText());
            info.put("Height", element.getHeight());
            info.put("Width", element.getWidth());
            info.put("CenterX", element.getCenterX());
            info.put("CenterY", element.getCenterY());
            info.put("OriginX", element.getOriginX());
            info.put("OriginY", element.getOriginY());
            info.put("Rect", element.getRect());
            info.put("ElementId", element.getElementId());
            all.put("元素信息", info);
            switch (action) {
                case DoItName.FIND_AND_CLICK: {
                    try {
                        element.click();
                        ack.put("点击元素","成功");
                    } catch (Exception e) {
                        ack.put("点击元素","失败");
                        ack.put("错误信息", e.getMessage());
                        e.printStackTrace();
                    }
                }
                case DoItName.FIND_AND_SEND_KEYS: {
                    try {
                        element.sendKeys(sendkeys);
                        ack.put("输入文字","成功");
                    } catch (Exception e) {
                        e.printStackTrace();
                        ack.put("输入文字","失败");
                        ack.put("错误信息",e.getMessage());
                    }
                }
                case DoItName.FIND_AND_SEND_KEYS_BY_DRIVER: {
                    try {
                        element.click();
                        macacaClient.keys(sendkeys);
                        ack.put("输入文字","成功");
                    } catch (Exception e) {
                        e.printStackTrace();
                        ack.put("输入文字","失败");
                        ack.put("错误信息",e.getMessage());
                    }

                }
                all.put("动作信息", ack);
            }
        } catch (Exception e) {
            e.printStackTrace();
            all.put("返回结果", "寻找元素失败");
            all.put("错误信息",e.getMessage());
        }
        result.put("res", all);
        return result;
    }
}

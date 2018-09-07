package utils;

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

import java.io.*;

/**
 * [Zhihu]https://www.zhihu.com/people/Sweets07
 * [Github]https://github.com/MatrixSeven
 * Created by seven on 2018/9/4.
 */

public class Utils {
    private static final String HOME = System.getProperty("user.home");
    private static final File CONFIG = new File(HOME.concat(File.separator)
            .concat(".MacacaUIFinder")
            .concat(File.separator)
            .concat("conf.json"));

    static {
        try {
            CONFIG.getParentFile().mkdirs();
            if (!CONFIG.exists()) {
                CONFIG.createNewFile();
            }
        } catch (Exception ex) {
            System.err.println("初始化目录失败,请检测权限" + ex);
        }
    }

    public static void laodConifg() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(CONFIG));
            String tempString;
            StringBuffer sb = new StringBuffer();
            while ((tempString = bufferedReader.readLine()) != null) {
                sb.append(tempString);
            }
            tempString = sb.toString();

            MessageManger.PublishEvent(Message.Create(EventType.LOAD_CONFIG_EVENT_DONE,
                    Params.create()
                        .put("config", tempString)
                       .put("load_res", !tempString.equals("") &&checkConfig(tempString))));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static boolean checkConfig(String json) {
        //TODO
//        try {
//            StatusHelper.setStatus(MainUICompent.SETTING_PANLE ,"config_json" , JSONArray.parseArray(json));
//            return true;
//        }catch (Exception ex){
//            return false;
//        }
        return true;
    }

    public static void saveConfig(String json) {
        try {
            if (checkConfig(json)) {
                FileWriter fileWriter = new FileWriter(CONFIG, false);
                fileWriter.write(json);
                fileWriter.flush();
            }
            MessageManger.PublishEvent(Message.Create(EventType.SAVE_CONFIG_EVENT_DONE,
                    Params.create().put("save_res", true)));
            laodConifg();
        } catch (IOException e) {
            MessageManger.PublishEvent(Message.Create(EventType.SAVE_CONFIG_EVENT_DONE,
                    Params.create()
                        .put("save_res", false)
                        .put("error", e.getMessage())));
            e.printStackTrace();
        }
    }


}

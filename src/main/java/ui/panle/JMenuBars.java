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

import javax.swing.*;

/**
 * [Zhihu]https://www.zhihu.com/people/Sweets07
 * [Github]https://github.com/MatrixSeven
 * Created by seven on 2018/9/3.
 */

public class JMenuBars extends JMenuBar  {

    JMenu JFILE =new JMenu("打开配置文件");
    JMenuItem JABOUT =new JMenuItem("关于");
    public JMenuBars() {
        this.add(JFILE);
        this.add(JABOUT);
        JFILE.setEnabled(false);
        JABOUT.addActionListener(e -> {
            if (java.awt.Desktop.isDesktopSupported()) {
                try {
                    java.net.URI uri = java.net.URI.create("https://www.zhihu.com/people/Sweets07");
                    java.awt.Desktop dp = java.awt.Desktop.getDesktop();
                    if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {
                        dp.browse(uri);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        });
    }

}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class MyMenu extends JMenuBar {

    private JTextField one;//第一文本框的内容
    private JTextField two;//第二个文本框的内容
    static OS os;

    public static OS getOs() {
        return os;
    }

    public MyMenu() {
        /**
         * 用户添加进程的ui  对应两个输入框和两个标签
         */
        JLabel oneLab = new JLabel("到达时间:");
        JLabel twoLab = new JLabel("服务时间:");
        one = new JTextField(10);
        two = new JTextField("100",10);
        //设置组件的长度
        one.setColumns(10);
        two.setColumns(10);
        one.setFont(new Font("宋体", Font.BOLD,16));
        two.setFont(new Font("宋体", Font.BOLD,16));
        oneLab.setFont(new Font("宋体", Font.BOLD,16));
        twoLab.setFont(new Font("宋体", Font.BOLD,16));
        add(oneLab);
        add(one);
        add(twoLab);
        add(two);

        /**
         * 对应添加进程按钮
         */
        JButton button1 = new JButton("添加进程");
        button1.setFont(new Font("宋体", Font.BOLD,16));
        addAction action1 = new addAction();
        button1.addActionListener(action1);
        add(button1);

        /**
         * 对应开始按钮
         */
        JButton button3 = new JButton("开始");
        button3.setFont(new Font("宋体", Font.BOLD,16));
        start action2 = new start();
        button3.addActionListener(action2);
        add(button3);

        /**
         * 对应下拉菜单
         */
        /*tpyes = new String[]{"进程1","进程2"};
        jComboBox = new JComboBox(tpyes);
        add(jComboBox);*/

        /**
         * 对应撤销进程按钮
         */
        /*JButton button2 = new JButton("撤销进程");
        add(button2);*/

    }


    /**
     * 对应添加进程按钮按下后执行的进程
     */
    private class addAction implements ActionListener
    {

        private int i;

        public addAction()
        {
            os = new OS();
            os.firstTime = 2;
            os.secondTime = 4;
            os.thirdTime = 8;
            os.proNum = 5;
            os.pro = new Progress[os.proNum];
            os.AOO = new ArrayList<Progress>();

        }

        public void actionPerformed(ActionEvent event)
        {
            os.operator(Integer.parseInt(one.getText()),(Integer.parseInt(two.getText())));
        }
    }


    /**
     * 对应开始按钮按下后执行的方法
     */
    private class start implements ActionListener
    {

        public start()
        {
        }

        public void actionPerformed(ActionEvent event)
        {
            System.out.println(11);
            Arrays.sort(os.pro);
            os.progressScheduling(os.pro);
            System.out.println(os);
        }

    }




}

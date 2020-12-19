import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;


public class MyMenu extends JMenuBar {

    private JTextField one;//第一文本框的内容
    private JTextField two;//第二个文本框的内容
    static OS os;
    MyComponent myComponent;

    public static OS getOs() {
        return os;
    }

    public MyMenu() {
        /**
         * 用户添加进程的ui  对应两个输入框和两个标签
         */
        JLabel oneLab = new JLabel("到达时间:");
        JLabel twoLab = new JLabel("服务时间:");
        one = new JTextField("0",10);
        two = new JTextField("0",10);
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
         * 清除按钮
         */
        JButton button2 = new JButton("清除");
        button2.setFont(new Font("宋体", Font.BOLD,16));
        close close1 = new close();
        button2.addActionListener(close1);
        add(button2);

    }


    /**
     * 对应 添加进程按钮按 下后执行的进程
     */
    private class addAction implements ActionListener
    {

        private int i;

        public addAction()
        {
            os = new OS();
            os.firstTime = 200;
            os.secondTime = 400;
            os.thirdTime = 800;
            os.proNum = 5;
            os.pro = new Progress[os.proNum];
            os.AOO = new ArrayList<Progress>();

        }

        public void actionPerformed(ActionEvent event)
        {

            /*///////*/

            os.pro[0] = new Progress();
            os.pro[0].id = 1;
            os.pro[0].reachTime = 0;
            os.pro[0].cpuTime = 400;
            os.pro[0].needTime = os.pro[0].cpuTime;
            os.pro[0].state = 'R';
            os.pro[0].queue = 0;
            os.pro[0].closeTime = 0;

            os.pro[1] = new Progress();
            os.pro[1].id = 2;
            os.pro[1].reachTime = 200;
            os.pro[1].cpuTime = 300;
            os.pro[1].needTime = os.pro[1].cpuTime;
            os.pro[1].state = 'R';
            os.pro[1].queue = 0;
            os.pro[1].closeTime = 0;

            os.pro[2] = new Progress();
            os.pro[2].id = 3;
            os.pro[2].reachTime = 300;
            os.pro[2].cpuTime = 200;
            os.pro[2].needTime = os.pro[2].cpuTime;
            os.pro[2].state = 'R';
            os.pro[2].queue = 0;
            os.pro[2].closeTime = 0;

            os.pro[3] = new Progress();
            os.pro[3].id = 4;
            os.pro[3].reachTime = 400;
            os.pro[3].cpuTime = 800;
            os.pro[3].needTime = os.pro[3].cpuTime;
            os.pro[3].state = 'R';
            os.pro[3].queue = 0;
            os.pro[3].closeTime = 0;

            os.pro[4] = new Progress();
            os.pro[4].id = 5;
            os.pro[4].reachTime = 1600;
            os.pro[4].cpuTime = 300;
            os.pro[4].needTime = os.pro[4].cpuTime;
            os.pro[4].state = 'R';
            os.pro[4].queue = 0;
            os.pro[4].closeTime = 0;

            /*//////////////*/
            //os.operator(Integer.parseInt(one.getText()),(Integer.parseInt(two.getText())));
        }
    }


    /**
     * 对应 开始按钮按 下后执行的方法
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
            os.myComponent = myComponent;
            os.progressScheduling(os.pro);
            System.out.println(os);
        }

    }


    /**
     * 对应 撤销按钮 按下后执行的方法
     */
    private class close implements ActionListener
    {

        public close()
        {
        }

        public void actionPerformed(ActionEvent event)
        {

            os.firstTime = 200;
            os.secondTime = 400;
            os.thirdTime = 800;
            os.firstQueue = new LinkedList<>();
            os.secondQueue = new LinkedList<>();
            os.thirdQueue = new LinkedList<>();
            os.proNum = 5;
            os.i = 0;
            os.pro = new Progress[os.proNum];
            os.AOO = new ArrayList<Progress>();
            os.myComponent = myComponent;
            os.reload();

        }

    }


}

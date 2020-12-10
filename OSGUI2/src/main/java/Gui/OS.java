package Gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * @Class MSFQS
 * @Description 多级反馈队列调度算法
 * @Author Naren
 * @Date 2020/5/30 10:46
 * @Version 1.0
 */
public class OS extends JComponent {
    /*三个队列*/
    static Queue<Progress> firstQueue = new LinkedList<>();
    static Queue<Progress> secondQueue = new LinkedList<>();
    static Queue<Progress> thirdQueue = new LinkedList<>();
    static int firstTime;  //第一队列cpu时间片
    static int secondTime; //第二队列cpu时间片
    static int thirdTime;  //第三队列cpu时间片
    static int proNum;     //进程数量
    static Progress[] pro;//获取到进程数组
    static int i = 0;
    static ArrayList<AllInOne> AOO;


    /**
     * 进程调度算法：Multi-stage feedback queue scheduling algorithm
     */
    static void progressScheduling(Progress[] pro,Graphics2D g2){
        int firstCpu = firstTime;
        int secondCpu = secondTime;
        int thirdCpu = thirdTime;
        int currentTime = 0;
        int num = 0;
        //System.out.println(Arrays.toString(pro));
        /*当有进程未运行时或进程队列不为空时，以每1时间片为单位*/
        while(num < proNum || !firstQueue.isEmpty() || !secondQueue.isEmpty() || !thirdQueue.isEmpty()){
            /*当前时刻有进程到达，则添加入第一队列*/
            while(num < proNum && pro[num].reachTime == currentTime)
                firstQueue.offer(pro[num++]);
            //打印上一秒各队列进程状态
            viewMenu(currentTime,g2);
            /*当前为队列1在运行进程*/
            if(!firstQueue.isEmpty()){
                if (secondQueue.peek() != null) secondQueue.peek().state = 'R';
                if (thirdQueue.peek() != null) thirdQueue.peek().state = 'R';
                //仍需时间：-1
                firstQueue.peek().needTime -= 1;
                //CPU剩余时间片：-1
                firstTime -= 1;
                //更新当前时间：+1
                currentTime++;
                //进程正在运行，状态：E.
                if(firstQueue.peek().needTime > 0){
                    firstQueue.peek().state = 'E';
                    //当前队列CPU时间片用完而进程仍未运行完时，进程出队，入次优先级队尾
                    if(firstTime == 0) {
                        firstQueue.peek().state = 'R';
                        secondQueue.offer(firstQueue.poll());
                        firstTime = firstCpu;
                    }

                }
                //进程运行完毕，状态：F，记录完成时刻并出队
                else if(firstQueue.peek().needTime == 0){
                    firstQueue.peek().state = 'F';
                    System.out.printf("\n当前时刻：%d,此进程运行结束：\n",currentTime);
                    System.out.println(firstQueue.peek());
                    Objects.requireNonNull(firstQueue.poll());
                    firstTime = firstCpu;
                }
            }
            /*当前为队列2在运行进程*/
            else if(!secondQueue.isEmpty()){
                if (thirdQueue.peek() != null) thirdQueue.peek().state = 'R';
                //仍需时间：-1
                secondQueue.peek().needTime -= 1;
                //CPU剩余时间片：-1
                secondTime -= 1;
                //更新当前时间：+1
                currentTime++;

                //进程运行完毕，状态：F，记录完成时刻并出队
                if(secondQueue.peek().needTime == 0){
                    secondTime = secondCpu;
                    secondQueue.peek().state = 'F';
                    System.out.printf("\n当前时刻：%d,此进程运行结束：\n",currentTime);
                    System.out.println(secondQueue.peek());
                    Objects.requireNonNull(secondQueue.poll());
                }
                //进程正在运行，状态：E.
                else if(secondQueue.peek().needTime > 0){
                    secondQueue.peek().state = 'E';
                    //当前队列CPU时间片用完而进程仍未运行完时，进程出队，入次优先级队尾
                    if(secondTime == 0) {
                        secondQueue.peek().state = 'R';
                        thirdQueue.offer(secondQueue.poll());
                        secondTime = secondCpu;
                    }
                }
            }
            /*当前为队列3在运行进程*/
            else if(!thirdQueue.isEmpty()){
                //仍需时间：-1
                thirdQueue.peek().needTime -= 1;
                //CPU剩余时间片：-1
                thirdTime -= 1;
                //更新当前时间：+1
                currentTime++;

                //进程正在运行，状态：R.
                if(thirdQueue.peek().needTime > 0){
                    thirdQueue.peek().state = 'E';
                    //当前队列CPU时间片用完而进程仍未运行完时，进程出队，入次优先级队尾
                    if(thirdTime == 0) {
                        thirdQueue.peek().state = 'R';
                        thirdQueue.offer(thirdQueue.poll());
                        thirdTime = thirdCpu;
                    }
                }
                //进程运行完毕，状态：F，记录完成时刻并出队
                else{
                    firstTime = firstCpu;
                    thirdQueue.peek().state = 'F';
                    System.out.printf("\n当前时刻：%d,此进程运行结束：\n",currentTime);
                    System.out.println(thirdQueue.peek());
                    Objects.requireNonNull(thirdQueue.poll());
                }
            }
        }
        System.out.println(AOO);
    }

    /**
     * 输入面板：获取到进程数组
     */
    static Progress[] operator(int a, int b){
        System.out.println("-----------------3118004950 柴政-----------------\n");
        System.out.println("欢迎进入多级队列反馈调度模拟系统，队列个数：3。\n\n");
        System.out.println("请按队列优先级从高到低的顺序输入各个队列的时间片长度：");
        /*firstTime = sc.nextInt();
        secondTime = sc.nextInt();
        thirdTime = sc.nextInt();*/
        System.out.print( "请输入进程数:" );
        /*proNum = sc.nextInt();*/

        /*获取到进程数组*/
        //pro = new Progress[proNum];
        System.out.println( "请依次输入进程标识符,进程到达时间,进程运行时间:" );
        //for( int i = 0; i < proNum; i++ ) {
            pro[i] = new Progress();
            pro[i].id = "A";
            pro[i].reachTime = a;
            pro[i].cpuTime = b;
            pro[i].needTime = pro[i].cpuTime;
            pro[i].state = 'R';
            i++;
        //}
        //对进程按照compareTo()的要求按照到达时间排序
        //Arrays.sort(pro);
        return pro;
    }
    /**
     * 输出面板：实时输出运行结果
     */
    private static void viewMenu(int currentTime,Graphics2D g2){
        System.out.printf("\n当前时刻：%d\n",currentTime);
        System.out.println("---------------------------------------------");
        System.out.println("            到达时间 运行时间  剩余时间  状态");
        if(firstQueue.isEmpty()) System.out.println("队列一：空");
        else {
            String str = "队列一：\n"+ firstQueue.toString()
                    .replace("[", "").replace("]", "")
                    .replace(", ", "");
            System.out.println(str);

            AOO.add(new AllInOne(firstQueue.peek().id,firstQueue.peek().reachTime,firstQueue.peek().cpuTime,
                    firstQueue.peek().needTime,firstQueue.peek().state));
            g2.drawLine(50,100,150,100);

            /*AOO.id = firstQueue.peek().id;
            AOO.reachTime = firstQueue.peek().reachTime;
            AOO.cpuTime =  firstQueue.peek().cpuTime;
            AOO.needTime =  firstQueue.peek().needTime;
            AOO.state =    firstQueue.peek().state;*/
            //g2.drawLine(50+AOO.reachTime,150,50+AOO.reachTime+AOO.cpuTime,150);

        }
        if(secondQueue.isEmpty()) System.out.println("队列二：空");
        else {
            System.out.println("队列二：\n"+ secondQueue.toString()
                    .replace("[", "").replace("]", "")
                    .replace(", ", ""));
            AOO.add(new AllInOne(firstQueue.peek().id,firstQueue.peek().reachTime,firstQueue.peek().cpuTime,
                    firstQueue.peek().needTime,firstQueue.peek().state));
        }

        if(thirdQueue.isEmpty()) System.out.println("队列三：空");
        else {
            System.out.println("队列三：\n"+ thirdQueue.toString()
                    .replace("[", "").replace("]", "")
                    .replace(", ", ""));
            AOO.add(new AllInOne(firstQueue.peek().id,firstQueue.peek().reachTime,firstQueue.peek().cpuTime,
                    firstQueue.peek().needTime,firstQueue.peek().state));
        }
        System.out.println("=============================================");
    }


    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        System.out.println(2222);
        g2.setStroke(new BasicStroke(2.0f));
        //第一个点
        int start =50;
        int end = 100;
        for (int i = 0; i < 14; i++) {
            g2.drawLine(start,100,end,100);
            start = end;
            end = end+50;
        }
        for (int i = 0; i < 13; i++) {
            g2.drawLine(start-50,100,start-50,90);
            g2.drawString(String.valueOf(start-50),start-50,80);
            start = start -50;
        }
        g2.drawLine(start-50,100,start-50,400);
        g2.drawString("p1",30,150);
        g2.drawString("p2",30,200);
        g2.drawString("p3",30,250);
        g2.drawString("p4",30,300);
        g2.drawString("p5",30,350);

        progressScheduling(pro,g2);

    }

    /**
     * main()
     */
    /*public static void main(String[] args) {
        progressScheduling(operator());
    }*/


}


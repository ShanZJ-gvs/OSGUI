import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class MyComponent extends JComponent {

    MyMenu myMenu;
    public int index;

    public MyComponent() {

        System.out.println(11111);
    }

    public void paintComponent(Graphics g) {
        /********设置画笔属性***********/
        Graphics2D g2 = (Graphics2D) g;
        System.out.println("hello comp"+index);
        g2.setFont(new Font("宋体", Font.BOLD,16));
        System.out.println(2222);
        g2.setStroke(new BasicStroke(2.0f));
        /*****************************/
        //第一个点
        int start =50;
        int end = 100;
        for (int i = 0; i < 20; i++) {
            g2.drawLine(start,100,end,100);
            start = end;
            end = end+50;
        }
        for (int i = 0; i < 19; i++) {
            g2.drawLine(start-50,100,start-50,90);
            g2.drawString(String.valueOf(end+800),start-50,80);
            start = start -50;
            end = end-100;
        }
        g2.drawLine(start-50,100,start-50,400);
        g2.drawString("p1",30,150);
        g2.drawString("p2",30,200);
        g2.drawString("p3",30,250);
        g2.drawString("p4",30,300);
        g2.drawString("p5",30,350);
        paintTable(g2);
        paintLine(g2);

    }

    //画表格(只有框架和表头)
    public void paintTable(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(new Font("宋体", Font.BOLD,18));
        g2.setStroke(new BasicStroke(1.0f));
        //第一个点
        for (int i = 500; i < 850; i+=50) {
            //画表格的横线
            g2.drawLine(50,i,800,i);
            //画表格的竖线
            if(i<800){
                g2.drawLine(i*3-1450,500,i*3-1450,800);
            }
        }
        g2.drawString("进程",100,530);
        g2.drawString("到达时间",235,530);
        g2.drawString("服务时间",385,530);
        g2.drawString("当前队列",540,530);
        g2.drawString("完成时间",690,530);

        ArrayList<Progress> aoo = myMenu.getOs().AOO;
        int[] ints = new int[]{0,0,0,0,0,0};
        for (Progress i : aoo) {
            System.out.println(i);
            if (ints[i.id] < i.closeTime){
                ints[i.id] = i.closeTime;
            }
        }

        for (int i = 0; i < aoo.size(); i++) {
            if (i==0){
                g2.drawString(Integer.toString(aoo.get(i).id),110,580+(aoo.get(i).id-1)*50);
                g2.drawString(Integer.toString(aoo.get(i).reachTime),245,580+(aoo.get(i).id-1)*50);
                g2.drawString(Integer.toString(aoo.get(i).cpuTime),395,580+(aoo.get(i).id-1)*50);
                g2.drawString(Integer.toString(aoo.get(i).queue),550,580+(aoo.get(i).id-1)*50);
                g2.drawString(String.valueOf(ints[aoo.get(i).id]),700,580+(aoo.get(i).id-1)*50);
            }
            if (i!=0){
                g2.drawString(Integer.toString(aoo.get(i).id),110,580+(aoo.get(i).id-1)*50);
                g2.drawString(Integer.toString(aoo.get(i).reachTime),245,580+(aoo.get(i).id-1)*50);
                g2.drawString(Integer.toString(aoo.get(i).cpuTime),395,580+(aoo.get(i).id-1)*50);
                g2.drawString(Integer.toString(aoo.get(i).queue),550,580+(aoo.get(i).id-1)*50);
                g2.drawString(String.valueOf(ints[aoo.get(i).id]),700,580+(aoo.get(i).id-1)*50);
            }

        }

    }




    public void paintLine(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        System.out.println(2222222);
        g2.setStroke(new BasicStroke(2.0f));
        //第一个点
        int start =50;
        int end = 50;
        int index = 100;

        ArrayList<Progress> aoo = myMenu.getOs().AOO;
        if (aoo.size()!=0){
            for (int i = 0; i < this.index; i++) {

                if (i/10==0){
                    int x2 = start+aoo.get(i/10).getCloseTime()/2/10;
                    int y = 100+aoo.get(i/10).getId()*50;
                    g2.drawLine(start,y,x2,y);
                    start = start+aoo.get(i/10).getCloseTime()/2/10;
                    //g2.drawString(aoo.get(i).toString(),100,index+350);
                    //index=index+20;
                /*g2.drawString(Integer.toString(aoo.get(i).id),110,580+(aoo.get(i).id-1)*50);
                g2.drawString(Integer.toString(aoo.get(i).reachTime),245,580+(aoo.get(i).id-1)*50);
                g2.drawString(Integer.toString(aoo.get(i).cpuTime),395,580+(aoo.get(i).id-1)*50);
                g2.drawString(Integer.toString(aoo.get(i).queue),550,580+(aoo.get(i).id-1)*50);
                g2.drawString(Integer.toString(aoo.get(i).closeTime),700,580+(aoo.get(i).id-1)*50);*/
                }else if (i/10==10){
                    int x2 = start+(aoo.get(i/10-1).getCloseTime()/2-aoo.get(i/10-2).getCloseTime()/2)/10;
                    int y = 100+aoo.get(i/10).getId()*50;
                    g2.drawLine(start,y,x2,y);
                    start = start+(aoo.get(i/100-1).getCloseTime()/2-aoo.get(i/10-2).getCloseTime()/2)/10;
                }else if(i/10!=0){
                    int x2 = start+(aoo.get(i/10).getCloseTime()/2-aoo.get(i/10-1).getCloseTime()/2)/10;
                    int y = 100+aoo.get(i/10).getId()*50;
                    g2.drawLine(start,y,x2,y);
                    start = start+(aoo.get(i/10).getCloseTime()/2-aoo.get(i/10-1).getCloseTime()/2)/10;
                }
                if (i/10!=0){

                    //g2.drawString(aoo.get(i).toString(),100,index+350);
                    //index=index+20;
                /*g2.drawString(Integer.toString(aoo.get(i).id),110,580+(aoo.get(i).id-1)*50);
                g2.drawString(Integer.toString(aoo.get(i).reachTime),245,580+(aoo.get(i).id-1)*50);
                g2.drawString(Integer.toString(aoo.get(i).cpuTime),395,580+(aoo.get(i).id-1)*50);
                g2.drawString(Integer.toString(aoo.get(i).queue),550,580+(aoo.get(i).id-1)*50);
                g2.drawString(Integer.toString(aoo.get(i).closeTime),700,580+(aoo.get(i).id-1)*50);*/
                }


            }
        }



    }



}

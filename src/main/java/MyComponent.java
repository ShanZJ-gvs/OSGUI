import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import java.util.Objects;

public class MyComponent extends JComponent {

    MyMenu myMenu;

    public MyComponent() {

        System.out.println(11111);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setFont(new Font("宋体", Font.BOLD,16));
        System.out.println(2222);
        g2.setStroke(new BasicStroke(2.0f));
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

    }


    public void paintTable(Graphics2D g2) {
        System.out.println(2222222);
        g2.setStroke(new BasicStroke(2.0f));
        //第一个点
        int start =50;
        int end = 50;
        int index = 100;


        ArrayList<Progress> aoo = myMenu.getOs().AOO;

        for (int i = 0; i < aoo.size(); i++) {
            if (i==0){
                int x2 = start+aoo.get(i).getCloseTime()/2;
                int y = 100+aoo.get(i).getId()*50;
                g2.drawLine(start,y,x2,y);
                start = start+aoo.get(i).getCloseTime()/2;
                g2.drawString(aoo.get(i).toString(),index,index+350);
                index=index+20;
            }
            if (i!=0){
                int a = i-1;
                int x2 = start+aoo.get(i).getCloseTime()/2-aoo.get(a).getCloseTime()/2;
                int y = 100+aoo.get(i).getId()*50;
                g2.drawLine(start,y,x2,y);
                start = start+aoo.get(i).getCloseTime()/2-aoo.get(a).getCloseTime()/2;
                g2.drawString(aoo.get(i).toString(),index,index+350);
                index=index+20;
            }

        }


    }




}

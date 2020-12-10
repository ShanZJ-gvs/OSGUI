import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.Objects;

public class MyComponent extends JComponent {
    static OS os;

    public MyComponent() {

        System.out.println(11111);
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


    }

    /*private static void viewMenu(int currentTime){
        System.out.printf("\n当前时刻：%d\n",currentTime);
        System.out.println("---------------------------------------------");
        System.out.println("            到达时间 运行时间  剩余时间  状态");
        if(os.firstQueue.isEmpty()) System.out.println("队列一：空");
        else System.out.println("队列一：\n"+ os.firstQueue.toString()
                .replace("[", "").replace("]", "")
                .replace(", ", ""));
        if(os.secondQueue.isEmpty()) System.out.println("队列二：空");
        else System.out.println("队列二：\n"+ os.secondQueue.toString()
                .replace("[", "").replace("]", "")
                .replace(", ", ""));
        if(os.thirdQueue.isEmpty()) System.out.println("队列三：空");
        else System.out.println("队列三：\n"+ os.thirdQueue.toString()
                .replace("[", "").replace("]", "")
                .replace(", ", ""));
        System.out.println("=============================================");
    }
*/

}

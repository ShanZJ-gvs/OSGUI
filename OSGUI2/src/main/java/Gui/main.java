package Gui;

import javax.swing.*;
import java.awt.*;


public class main extends Thread{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> {
            JFrame frame = new MyFrame();
            frame.setTitle("多级反馈队列调度算法");
            frame.setSize(1000, 500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

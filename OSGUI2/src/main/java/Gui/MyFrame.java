package Gui;

import javax.swing.*;


public class MyFrame extends JFrame {


    public MyFrame() {

        MyMenu myMenu = new MyMenu();
        setJMenuBar(myMenu);
        OS os = new OS();
        add(os);
        pack();
    }



}

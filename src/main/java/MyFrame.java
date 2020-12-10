import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MyFrame extends JFrame {


    public MyFrame() {

        MyMenu myMenu = new MyMenu();
        setJMenuBar(myMenu);
        add(new MyComponent());
        pack();
    }



}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MyFrame extends JFrame {


    public MyFrame() {

        MyMenu myMenu = new MyMenu();
        setJMenuBar(myMenu);
        MyComponent myComponent = new MyComponent();
        //将两个组件的OS对象统一
        myComponent.os = myMenu.os;
        add(myComponent);
        pack();
    }



}

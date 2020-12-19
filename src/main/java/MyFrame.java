import javax.swing.*;

public class MyFrame extends JFrame {


    public MyFrame() {

        MyMenu myMenu = new MyMenu();
        MyComponent myComponent = new MyComponent();
        myMenu.myComponent = myComponent;
        myComponent.myMenu = myMenu;
        myComponent.os = myMenu.os;
        setJMenuBar(myMenu);
        add(myComponent);
        pack();
    }



}

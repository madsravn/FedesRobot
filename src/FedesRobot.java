import java.awt.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FedesRobot extends JFrame {

    public FedesRobot() {
        initUI();
    }

    public final void initUI() {

        final JPanel panel = new JPanel();
        getContentPane().add(panel);

        panel.setLayout(null);


        JButton button = new JButton("Start");
        button.setBounds(100, 220, 100, 30);
        //button.setToolTipText("A button component");
        final JTextField textField1 = new JTextField("");
        textField1.setBounds(100,20,100, 30);
        final JTextField textField2 = new JTextField("");
        textField2.setBounds(100, 70, 100, 30);
        final JTextField textField3 = new JTextField("");
        textField3.setBounds(100, 120, 100, 30);
        final JTextField textField4 = new JTextField("");
        textField4.setBounds(100, 170, 100, 30);
        final JLabel label1 = new JLabel("Første");
        label1.setBounds(35,30,65,15);
        final JLabel label2 = new JLabel("Anden");
        label2.setBounds(35,80,65,15);
        final JLabel label3 = new JLabel("Tredje");
        label3.setBounds(35,130,65,15);
        final JLabel label4 = new JLabel("Fjerde");
        label4.setBounds(35,180,65,15);

        KeyListener keyListener = new KeyListener() {

            public void keyPressed(KeyEvent keyEvent) {
                //printIt("Pressed", keyEvent);
                //checkForSpace(keyEvent);
            }

            public void keyReleased(KeyEvent keyEvent) {
                //printIt("Released", keyEvent);
                checkForSpace(keyEvent);
            }

            public void keyTyped(KeyEvent keyEvent) {
                //printIt("Typed", keyEvent);
                //checkForSpace(keyEvent);

            }
            private void printIt(String title, KeyEvent keyEvent) {
                int keyCode = keyEvent.getKeyCode();
                String keyText = KeyEvent.getKeyText(keyCode);
                System.out.println(title + " : " + keyText + " / " + keyEvent.getKeyChar());
            }

            private void checkForSpace(KeyEvent keyEvent) {
                Component compFocusOwner =
                        KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
                JTextField textField = (JTextField)compFocusOwner;
                String text = textField.getText();
                System.out.println(textField.getText());
                if(text.equals(" ")) {
                    PointerInfo a = MouseInfo.getPointerInfo();
                    Point b = a.getLocation();
                    int x = (int) b.getX();
                    int y = (int) b.getY();

                    ((JTextField)compFocusOwner).setText(""+x+","+y);
                }
            }
        };

        textField1.addKeyListener(keyListener);
        textField2.addKeyListener(keyListener);
        textField3.addKeyListener(keyListener);
        textField4.addKeyListener(keyListener);

        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });


        panel.add(button);
        panel.add(textField1);
        panel.add(textField2);
        panel.add(textField3);
        panel.add(textField4);
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);

        setTitle("Mr. Roboto");
        setSize(300, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                FedesRobot ex = new FedesRobot();
                ex.setVisible(true);
            }
        });
    }
}
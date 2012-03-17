import java.awt.*;
import javax.swing.*;

import java.awt.event.*;

public class FedesRobot extends JFrame {

    public FedesRobot() {
        initUI();
    }

    private static MrRoboto roboto;
    private static Thread t;
    
    public final void initUI() {

        final JPanel panel = new JPanel();
        getContentPane().add(panel);

        panel.setLayout(null);

        // Setting up button
        JButton start = new JButton("Start");
        start.setBounds(45, 270, 100, 30);
        JButton stop = new JButton("Stop");
        stop.setBounds(155, 270, 100, 30);

        // Setting up text fields
        final JTextField textField1 = new JTextField("");
        textField1.setBounds(100,20,100, 30);
        final JTextField textField2 = new JTextField("");
        textField2.setBounds(100, 70, 100, 30);
        final JTextField textField3 = new JTextField("");
        textField3.setBounds(100, 120, 100, 30);
        final JTextField textField4 = new JTextField("");
        textField4.setBounds(100, 170, 100, 30);
        final JTextField textField5 = new JTextField("");
        textField5.setBounds(100, 220, 100, 30);

        // Setting up labels
        final JLabel label1 = new JLabel("Første");
        label1.setBounds(35,30,65,15);
        final JLabel label2 = new JLabel("Anden");
        label2.setBounds(35,80,65,15);
        final JLabel label3 = new JLabel("Tredje");
        label3.setBounds(35,130,65,15);
        final JLabel label4 = new JLabel("Fjerde");
        label4.setBounds(35,180,65,15);
        final JLabel label5 = new JLabel("Femte");
        label5.setBounds(35,230,65,15);        

        // KeyListener for the text fields
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

            // Debug method
            private void printIt(String title, KeyEvent keyEvent) {
                int keyCode = keyEvent.getKeyCode();
                String keyText = KeyEvent.getKeyText(keyCode);
                System.out.println(title + " : " + keyText + " / " + keyEvent.getKeyChar());
            }

            // Primary function
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

        // Adding keylistener action to the text fields
        textField1.addKeyListener(keyListener);
        textField2.addKeyListener(keyListener);
        textField3.addKeyListener(keyListener);
        textField4.addKeyListener(keyListener);
        textField5.addKeyListener(keyListener);


        start.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                Point first = new Point(Integer.parseInt(textField1.getText().split(",")[0])
                        ,Integer.parseInt(textField1.getText().split(",")[1]));
                Point second = new Point(Integer.parseInt(textField2.getText().split(",")[0])
                        ,Integer.parseInt(textField2.getText().split(",")[1]));
                Point third = new Point(Integer.parseInt(textField3.getText().split(",")[0])
                        ,Integer.parseInt(textField3.getText().split(",")[1]));
                Point fourth = new Point(Integer.parseInt(textField4.getText().split(",")[0])
                        ,Integer.parseInt(textField4.getText().split(",")[1]));
                Point fifth = new Point(Integer.parseInt(textField5.getText().split(",")[0])
                        ,Integer.parseInt(textField5.getText().split(",")[1]));
                System.out.println("Starting");
                roboto.setPoints(first,second,third,fourth, fifth);
                t = new Thread(roboto);
                t.start();
            }
        });

        stop.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionEvent) {
                t.interrupt();
            }
        });

        // Adding all our component to the panel
        panel.add(start);
        panel.add(stop);
        panel.add(textField1);
        panel.add(textField2);
        panel.add(textField3);
        panel.add(textField4);
        panel.add(textField5);
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        panel.add(label5);

        setTitle("Mr. Roboto");
        setSize(300, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private final void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            System.err.println("WE ARE NOT TIRED - " + e.getMessage());
        }        
    }

    public static void main(String[] args) {
        try {
            roboto = new MrRoboto();
        } catch (Exception e) {

        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                FedesRobot ex = new FedesRobot();
                ex.setVisible(true);
            }
        });
    }
}
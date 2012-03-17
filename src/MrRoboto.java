import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class MrRoboto extends Robot implements Runnable{

    private Point first, second, third, fourth, fifth;
    private Random randomGenerator;
    private FileReader fileReader;
    ArrayList<String> excuses, animals, colors;


    public void setPoints(Point first, Point second, Point third, Point fourth, Point fifth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.fifth = fifth;
    }
    private String animal;
    private String color;


    public MrRoboto() throws AWTException
    {
        super();
        Date date = new Date();
        randomGenerator = new Random(date.getTime());
        fileReader = new FileReader();
        excuses = fileReader.readFile(new File("excuses"));
        animals = fileReader.readFile(new File("animals"));
        colors = fileReader.readFile(new File("colors"));


    }

    public void pasteClipboard()
    {
        keyPress(KeyEvent.VK_CONTROL);
        keyPress(KeyEvent.VK_V);
        delay(50);
        keyRelease(KeyEvent.VK_V);
        keyRelease(KeyEvent.VK_CONTROL);
    }

    public void type(String text)
    {
        writeToClipboard(text);
        pasteClipboard();
    }

    private void writeToClipboard(String s)
    {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable transferable = new StringSelection(s);
        clipboard.setContents(transferable, null);
    }

    public void moveAndClick(int x, int y) {
        mouseMove(x,y);
        delay(50);
        mousePress(InputEvent.BUTTON1_MASK);
        delay(50);
        mouseRelease(InputEvent.BUTTON1_MASK);

    }

    public void run() {
        Thread thisThread = Thread.currentThread();
        color = colors.get(randomBetween(1,colors.size()-2));
        animal = animals.get(randomBetween(1,animals.size()-2));
        int iForExcuses = 0;
        boolean running = true;
        while(running) {
            try {

                moveAndClick(randomBetween((int)first.getX(),(int)second.getX()), randomBetween((int)first.getY(),(int)second.getY()));
                thisThread.sleep(5000);
                moveAndClick((int)third.getX(),(int)third.getY());
                thisThread.sleep(5000);
                
                type(excuses.get(iForExcuses) + ". Best regards from the " + color + " " + animal);
                thisThread.sleep(5000);
                moveAndClick((int)fourth.getX(), (int)fourth.getY());
                thisThread.sleep(5000);
                moveAndClick((int)fifth.getX(), (int)fifth.getY());
                iForExcuses++;
                thisThread.sleep(10000);
                if(iForExcuses == (excuses.size()-1)) {
                    iForExcuses = 0;
                    String tempColor = color;
                    String tempAnimal = animal;
                    while(tempColor.equals(color)) {
                        color = colors.get(randomBetween(1,colors.size()-2));
                    }
                    while(tempAnimal.equals(animal)) {
                        animal = animals.get(randomBetween(1,animals.size()-2));
                    }
                }
            } catch (InterruptedException e) {
                running = false;
            }

        }
    }

    // Random number between x and y
    private int randomBetween(int x, int y) {
        return x + randomGenerator.nextInt(Math.abs(y-x));

    }

}
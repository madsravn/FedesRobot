import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;

/**
 * Created by IntelliJ IDEA.
 * User: mads
 * Date: 3/14/12
 * Time: 9:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class MrRoboto extends Robot {
    public MrRoboto() throws AWTException
    {
        super();
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
        mousePress(1);
        delay(50);
        mouseRelease(1);

    }

}
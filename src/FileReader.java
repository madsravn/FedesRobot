import java.io.*;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: mads
 * Date: 3/14/12
 * Time: 9:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileReader {

    public ArrayList<String> readFile(File file) {

        ArrayList<String> ICANREAD = new ArrayList<String>();

        try{

            FileInputStream fstream = new FileInputStream(file);

            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;

            while ((strLine = br.readLine()) != null)   {

                ICANREAD.add(strLine);

            }

            in.close();
        }catch (Exception e){

            System.err.println("Error: " + e.getMessage());
        }
        return ICANREAD;
    }

}
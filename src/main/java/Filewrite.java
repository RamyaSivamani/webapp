import java.io.*;
import java.util.*;
public class Filewrite
{
public void writefile()
{
    FileWriter fr = null;
        String data = "First File writing program";
        try {
            fr = new FileWriter("information.txt");
            fr.write(data);
            System.out.println(System.getProperty("line.separator"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
}
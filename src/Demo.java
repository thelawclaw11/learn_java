import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Stack;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

class ListOfNumbers{
    private List<Integer> list;
    private static final int SIZE = 10;

    public ListOfNumbers(){
        list = new ArrayList<>(SIZE);
        for (int i = 0; i < SIZE; i++) {
            list.add(i);
        }
    }

    public void writeList(){
        PrintWriter out = null;

        try{

            out = new PrintWriter(new FileWriter("OutFile.txt"));

            for (int i = 0; i < SIZE; i++) {
                out.println("Value at: " + i + " = " + list.get(i));
            }
        }catch (IndexOutOfBoundsException e ){
            System.err.println("IndexOutOfBoundException: " + e.getMessage());
        }catch (IOException e){
            System.err.println("Caught IOException: " + e.getMessage());
        }finally{
            if(out != null){
                System.out.println("Closing PrintWriter");
                out.close();
            }else{
                System.out.println("PrintWriter not open");
            }
        }

    }


}





public class Demo {

    public static void writeToFileContents(String zipFileName, String outputFileName) throws IOException{
        Charset charset = java.nio.charset.StandardCharsets.US_ASCII;

        Path outputFilePath = Paths.get(outputFileName);

        try(
                ZipFile zf = new ZipFile(zipFileName);
                BufferedWriter writer = Files.newBufferedWriter(outputFilePath, charset);
                ){
            for(Enumeration entries = zf.entries(); entries.hasMoreElements();){
                String newLine = System.getProperty("line.separator");
                String zipEntryName = ((java.util.zip.ZipEntry) entries.nextElement()).getName() + newLine;
                writer.write(zipEntryName, 0, zipEntryName.length());
            }
        }
    }





    static String readFirstLineFromFile(String path) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(path));
        try{
            return br.readLine();
        }finally{
            br.close();
        }
    }

    public static void main(String[] args){


    }
}

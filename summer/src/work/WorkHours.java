package work;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class WorkHours {

    double startTime;
    double endTime;
    String fileName = "MyHours";
    String first = "";
    String last = "";
    int[] intFirst;
    int[] intLast;
    double countHours = 0;
    double a, b;
//--------------------------------------------------------------------------
    //Constructor
    public WorkHours() throws IOException {
        createFile();
        toFile();
        fromFile();
    }
//--------------------------------------------------------------------------
    //Creates a file if there isn't any
    public void createFile() throws IOException {
        File file = new File(fileName);
        if(file.createNewFile()){
            System.out.println("File created" + file.getName());
        }
    }
//--------------------------------------------------------------------------
    //Write to file and count the hours
    public void toFile() throws IOException {

        first = JOptionPane.showInputDialog("Vilken tid började du?");
        last = JOptionPane.showInputDialog("Vilken tid slutade du?");

        startTime = Double.parseDouble(first);
        endTime = Double.parseDouble(last);

        if(first.contains(".")){
            String[] arrFirst = String.valueOf(startTime).split("\\.");
            intFirst = new int[2];
            intFirst[0] = Integer.parseInt(arrFirst[0]);
            intFirst[1] = Integer.parseInt(arrFirst[1]);
            if(intFirst[1] < 10){
                intFirst[1] *= 10;
            }
            a = endTime - intFirst[0];
            b = Math.abs((double) intFirst[1]/60);
            countHours = a - b;
            //System.out.println(Arrays.toString(intFirst));
        }
        if(last.contains(".")){
            String[] arrLast = String.valueOf(endTime).split("\\.");
            intLast = new int[2];
            intLast[0] = Integer.parseInt(arrLast[0]);
            intLast[1] = Integer.parseInt(arrLast[1]);
            if(intLast[1] < 10){
                intLast[1] *= 10.0;
            }
            a = intLast[0] - startTime;
            b = Math.abs((double) intLast[1]/60);
            countHours = a + b;
            //System.out.println(Arrays.toString(intLast));
        }
        if(first.contains(".") && last.contains(".")){
            a = intLast[0] - intFirst[0];
            b = Math.abs((double) intLast[1]/60 - (double) intFirst[1]/60);
            countHours = a + b;
        } else if(!first.contains(".") && !last.contains(".")){
            a = endTime - startTime;
            b = 0;
            countHours = (double) (a + b);
        }

        double keepOneCount = Math.floor(countHours * 10) / 10;
        Date currentDate = new Date();
        try (PrintWriter fw = new PrintWriter(new FileWriter(fileName, true))) {
            fw.write(currentDate.getDate() + "\t" + first + "\t" +
                    last + "\t" + keepOneCount + "\n");
            fw.println("----------------------------------------");
        }
    }
//--------------------------------------------------------------------------
    //To read from file
    public void fromFile() throws IOException{
        List<String> list = new ArrayList<>();
        BufferedReader read = new BufferedReader(new FileReader(fileName));
        Scanner scan = new Scanner(read);
        String data = scan.nextLine();

        while (scan.hasNextLine()){
            data = scan.nextLine();
            list.addAll(Collections.singletonList(data));
        }
        scan.close();
        for (String s : list) {
            System.out.println(s);
        }
    }
//--------------------------------------------------------------------------
    public static void main(String[] args) throws IOException {
        WorkHours p = new WorkHours();
    }

}

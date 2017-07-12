import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        String csvFile = "H:\\Inteliji\\DemoOpenCSV\\out/country3.csv";
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(csvFile), ',' , '"' , 1);
            String[] line;
            while ((line = reader.readNext()) != null) {
                System.out.println("Country [id= " + line[0] + ", code= " + line[1] + " , name=" + line[2] + "]");
//                System.out.println("Country [id= " + line[0]);
//                System.out.println("Main.main "+line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

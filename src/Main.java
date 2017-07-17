import Models.Employee;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    //link lesson
    //http://www.journaldev.com/12014/opencsv-csvreader-csvwriter-example
    static String csvFile = "";
    static String csvFile1 = "";
    public static void main(String[] args) throws FileNotFoundException {
        csvFile = "H:\\Inteliji\\DemoOpenCSV\\out/country.csv";
        csvFile1 = "H:\\Inteliji\\DemoOpenCSV\\out/country1.csv";
        File f = new File(csvFile1);
//        CSVReader reader = null;
        try {
            if(!f.exists()){
                System.out.println("file not exist ,Create new file");
                f.createNewFile();
            }
//            reader = new CSVReader(new FileReader(csvFile), ',' , '"' , 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main main = new Main();

//        main.OpenCSVReaderLineByLineExample();
//        main.OpenCSVParseToBeanExample ();
//        try {
//            List<Employee> list = main.parseCSVWithHeader();
//            System.out.println(list.size());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        main.OpenCSVWriterExample();
    }

    private void OpenCSVWriterExample() {
        StringWriter writer = new StringWriter();

        //using custom delimiter and quote character
        CSVWriter csvWriter = new CSVWriter(writer, '#', '\'');

        List<Employee> emps = null;
        try {
            emps = parseCSVWithHeader();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String[]> data = toStringArray(emps);

        csvWriter.writeAll(data);

        try {
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(writer);
    }
    private static List<String[]> toStringArray(List<Employee> emps) {
        List<String[]> records = new ArrayList<String[]>();

        // adding header record
        records.add(new String[] { "ID", "Name", "Age", "Country" });

        Iterator<Employee> it = emps.iterator();
        while (it.hasNext()) {
            Employee emp = it.next();
            records.add(new String[] { emp.getId(), emp.getName(), emp.getAge(), emp.getCountry() });
        }
        return records;
    }
    private List<Employee> parseCSVWithHeader() throws IOException {
        CSVReader reader = new CSVReader(new FileReader(csvFile1), ',');

        HeaderColumnNameMappingStrategy<Employee> beanStrategy = new HeaderColumnNameMappingStrategy<Employee>();
        beanStrategy.setType(Employee.class);

        CsvToBean<Employee> csvToBean = new CsvToBean<Employee>();
        List<Employee> emps = csvToBean.parse(beanStrategy, reader);

        System.out.println(emps);
        reader.close();

        return emps;
    }

    private void OpenCSVParseToBeanExample() {
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(csvFile), ',');
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ColumnPositionMappingStrategy<Employee> beanStrategy = new ColumnPositionMappingStrategy<Employee>();
        beanStrategy.setType(Employee.class);
        beanStrategy.setColumnMapping(new String[] {"id","name","age","country"});

        CsvToBean<Employee> csvToBean = new CsvToBean<Employee>();

        List<Employee> emps = csvToBean.parse(beanStrategy, reader);

        System.out.println(emps);
    }

    private void OpenCSVReaderLineByLineExample() {
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(csvFile), ',');
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<Employee> emps = new ArrayList<Employee>();

        // read line by line
        String[] record = null;

        try {
            while ((record = reader.readNext()) != null) {
                Employee emp = new Employee();
                emp.setId(record[0]);
                emp.setName(record[1]);
                emp.setAge(record[2]);
                emp.setCountry(record[3]);
                emps.add(emp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(emps);

        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

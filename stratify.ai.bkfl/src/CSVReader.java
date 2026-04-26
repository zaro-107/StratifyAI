import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVReader {

    public int[] readCSV(String filePath) {

        ArrayList<Integer> list = new ArrayList<>();

        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);

            if (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] values = line.split(",");

                for (String v : values) {
                    int num = Integer.parseInt(v.trim());
                    list.add(num);
                }
            }

            sc.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        int[] data = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            data[i] = list.get(i);
        }

        return data;
    }
}
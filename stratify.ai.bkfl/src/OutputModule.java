import java.util.Arrays;

public class OutputModule {

    public void display(int[] data, String pattern, int prediction) {
        System.out.println("Input Data: " + Arrays.toString(data));
        System.out.println("Pattern: " + pattern);
        System.out.println("Predicted Value: " + prediction);
    }
}
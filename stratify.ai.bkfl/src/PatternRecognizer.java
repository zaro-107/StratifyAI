public class PatternRecognizer {

    public String detectPattern(int[] data) {
        int increasing = 0;
        int decreasing = 0;

        for (int i = 1; i < data.length; i++) {
            if (data[i] > data[i - 1]) {
                increasing++;
            } else if (data[i] < data[i - 1]) {
                decreasing++;
            }
        }

        if (increasing > decreasing) {
            return "Increasing Trend 📈";
        } else if (decreasing > increasing) {
            return "Decreasing Trend 📉";
        } else {
            return "No Clear Trend ⚖️";
        }
    }
}
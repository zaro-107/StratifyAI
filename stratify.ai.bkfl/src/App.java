public class App {

    public static void main(String[] args) {

        // Step 1: Input data
        CSVReader reader = new CSVReader();
        int[] data = reader.readCSV("C:\\Users\\SRIYANS\\OneDrive\\Documents\\STRATIFY.AI\\stratify.ai.bkfl\\src\\data.csv");

        // Step 2: Store data using DataProcessor
        DataProcessor dp = new DataProcessor();
        dp.setData(data);

        // Step 3: Detect pattern
        PatternRecognizer pr = new PatternRecognizer();
        String pattern = pr.detectPattern(dp.getData());

        // Step 4: Choose model (Polymorphism)
        BaseModel model;

        // You can switch between Forecast and AdvancedForecast
        model = new ForecastModel();  
        // model = new AdvancedForecast();  // try this also

        int prediction = model.predict(dp.getData());

        // Step 5: Output result
        OutputModule out = new OutputModule();
        out.display(dp.getData(), pattern, prediction);
    }
}
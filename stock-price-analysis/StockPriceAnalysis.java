import java.util.ArrayList;

public class StockPriceAnalysis {

    public static void main(String[] args) {

        // Array of opening stock prices for 10 days
        float[] stockPricesArray = {
            125.50f, 130.75f, 128.00f, 135.25f, 130.75f,
            140.00f, 138.50f, 142.25f, 130.75f, 145.00f
        };

        // ArrayList of opening stock prices for the same 10 days
        ArrayList<Float> stockPricesList = new ArrayList<>();
        stockPricesList.add(125.50f);
        stockPricesList.add(130.75f);
        stockPricesList.add(128.00f);
        stockPricesList.add(135.25f);
        stockPricesList.add(130.75f);
        stockPricesList.add(140.00f);
        stockPricesList.add(138.50f);
        stockPricesList.add(142.25f);
        stockPricesList.add(130.75f);
        stockPricesList.add(145.00f);

        float targetPrice = 130.75f;

        // Display results from array methods
        System.out.println("===== Stock Price Analysis Using Array =====");
        System.out.println("Average Stock Price: " + calculateAveragePrice(stockPricesArray));
        System.out.println("Maximum Stock Price: " + findMaximumPrice(stockPricesArray));
        System.out.println("Occurrences of " + targetPrice + ": " + countOccurrences(stockPricesArray, targetPrice));

        // Display results from ArrayList methods
        System.out.println("\n===== Stock Price Analysis Using ArrayList =====");
        System.out.println("Average Stock Price: " + calculateAveragePrice(stockPricesList));
        System.out.println("Maximum Stock Price: " + findMaximumPrice(stockPricesList));
        System.out.println("Cumulative Sum: " + computeCumulativeSum(stockPricesList));
    }

    // Method to calculate average price using an array
    public static float calculateAveragePrice(float[] prices) {
        float sum = 0;

        for (int i = 0; i < prices.length; i++) {
            sum += prices[i];
        }

        return sum / prices.length;
    }

    // Method to calculate average price using an ArrayList
    public static float calculateAveragePrice(ArrayList<Float> prices) {
        float sum = 0;

        for (int i = 0; i < prices.size(); i++) {
            sum += prices.get(i);
        }

        return sum / prices.size();
    }

    // Method to find maximum price using an array
    public static float findMaximumPrice(float[] prices) {
        float maximumPrice = prices[0];

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > maximumPrice) {
                maximumPrice = prices[i];
            }
        }

        return maximumPrice;
    }

    // Method to find maximum price using an ArrayList
    public static float findMaximumPrice(ArrayList<Float> prices) {
        float maximumPrice = prices.get(0);

        for (int i = 1; i < prices.size(); i++) {
            if (prices.get(i) > maximumPrice) {
                maximumPrice = prices.get(i);
            }
        }

        return maximumPrice;
    }

    // Method to count occurrences of a target price in an array
    public static int countOccurrences(float[] prices, float targetPrice) {
        int count = 0;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] == targetPrice) {
                count++;
            }
        }

        return count;
    }

    // Method to compute cumulative sum using an ArrayList
    public static ArrayList<Float> computeCumulativeSum(ArrayList<Float> prices) {
        ArrayList<Float> cumulativeSumList = new ArrayList<>();
        float runningTotal = 0;

        for (int i = 0; i < prices.size(); i++) {
            runningTotal += prices.get(i);
            cumulativeSumList.add(runningTotal);
        }

        return cumulativeSumList;
    }
}
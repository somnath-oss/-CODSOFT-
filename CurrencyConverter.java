import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class CurrencyConverter {
    private static final String API_URL = "https://api.frankfurter.app/latest";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("======= Currency Converter =======");

        System.out.print("Enter base currency (e.g., USD, INR, EUR): ");
        String base = sc.nextLine().trim().toUpperCase();

        System.out.print("Enter target currency (e.g., USD, INR, EUR): ");
        String target = sc.nextLine().trim().toUpperCase();

        if (base.equals(target)) {
            System.out.println("Base and target currencies cannot be the same.");
            return;
        }

        System.out.print("Enter amount to convert: ");
        double amount;
        try {
            amount = Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount entered.");
            return;
        }

        try {
            double rate = fetchRate(base, target);
            double converted = amount * rate;
            System.out.printf(" %.2f %s = %.2f %s\n", amount, base, converted, target);
        } catch (IOException e) {
            System.out.println(" Error fetching exchange rate: " + e.getMessage());
        }

        sc.close();
    }

    public static double fetchRate(String base, String target) throws IOException {
        String apiUrl = API_URL + "?from=" + base + "&to=" + target;

        URL url = new URL(apiUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        int code = con.getResponseCode();
        if (code != 200) {
            throw new IOException("API responded with code: " + code);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String input;
        StringBuilder response = new StringBuilder();

        while ((input = in.readLine()) != null) {
            response.append(input);
        }
        in.close();

        String json = response.toString();

        // Extract the rate value manually
        int idx = json.indexOf(target) + 5;
        int end = json.indexOf("}", idx);
        String rateStr = json.substring(idx, end);

        return Double.parseDouble(rateStr);
    }
}

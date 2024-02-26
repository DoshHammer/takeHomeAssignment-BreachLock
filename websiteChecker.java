import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class websiteChecker {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the URL to check: ");
        String url = scanner.nextLine();

        int statusCode = checkWebsite(url);
        if (statusCode == HttpURLConnection.HTTP_OK) {
            System.out.println("Website is available!");
        } else if (statusCode == HttpURLConnection.HTTP_NOT_FOUND) {
            if (isCustom404(url)) {
                System.out.println("Custom 404 - Page not found!");
            } else {
                System.out.println("Default 404 - Page not found!");
            }
        } else {
            System.out.println("Status Code: " + statusCode);
        }
    }

    private static boolean isCustom404(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("HEAD");
        connection.connect();
        String contentType = connection.getContentType();

        //Method to check what Content Type is returned on the HTTP call
        // to determine if this is a default page of server(ex- nginx, tomcat).
        //<meta charset=utf-8> or contentType is text/html for default pages of servers

        return contentType.equalsIgnoreCase("text/html; charset=UTF-8");
    }

    private static int checkWebsite(String url) throws IOException {

        //Method to obtain the correct Http response code for the asked URL
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("HEAD");
        connection.connect();

        return connection.getResponseCode();
    }
}

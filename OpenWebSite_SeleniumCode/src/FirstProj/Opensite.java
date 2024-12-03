package FirstProj;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Opensite {
    WebDriver driver;

    // Constructor
    public Opensite() {
        try {
            // Set ChromeDriver path
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\akash\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

            // Configure Chrome options
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications"); // Disable notifications
            options.addArguments("--start-maximized"); // Start browser maximized
            options.addArguments("--remote-allow-origins=*"); // Allow remote origins
            
            // Optional: Set a custom user data directory
            // options.addArguments("user-data-dir=C:\\path\\to\\your\\custom\\profile"); // Uncomment and set a valid path if needed

            // Initialize ChromeDriver with options
            driver = new ChromeDriver(options);

            // Set an implicit wait time
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to open a website
    public void openWebsite(String url) {
        if (driver != null) {
            driver.get(url);
            System.out.println("Opened website: " + url);
        }
    }

    // Method to close the browser
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed.");
        }
    }

    // Main method to execute
    public static void main(String[] args) {
        Opensite site = new Opensite();
        site.openWebsite("https://www.facebook.com"); // Replace with desired URL

        // Keep the browser open for 10 seconds (or change the value as needed)
        try {
            Thread.sleep(10000); // 10000 milliseconds = 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Close the browser after the delay
        site.closeBrowser();
    }
}
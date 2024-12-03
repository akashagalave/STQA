package website;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Mouthshut {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:/soft/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the target URL: ");
        String targetUrl = scanner.nextLine();
        scanner.close();
        
        try {
            // Open the target review page
            driver.get(targetUrl);
            Random random = new Random();

            // Scroll down slowly to simulate human behavior
            for (int i = 0; i < 5; i++) {
                int scrollDistance = random.nextInt(300) + 200; // Random scroll distance between 200-500 pixels
                ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, arguments[0]);", scrollDistance);
                Thread.sleep(random.nextInt(3000) + 2000); // Random delay between 2-5 seconds
            }

            // JavaScript executor to fetch all review texts
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            List<WebElement> reviews = driver.findElements(By.cssSelector(".review-text")); // Adjust selector if necessary
            System.out.println("Number of reviews found: " + reviews.size());

            // Generate a unique filename with a timestamp
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String fileName = "Reviews_" + timestamp + ".txt";
            FileWriter writer = new FileWriter(fileName);

            // Write reviews with random delays between processing
            for (WebElement review : reviews) {
                String reviewText = (String) jsExecutor.executeScript("return arguments[0].textContent;", review);
                if (reviewText != null && !reviewText.isEmpty()) { // Write only non-empty reviews
                    writer.write("Review: " + reviewText.trim() + "\n");
                    System.out.println("Review written: " + reviewText.trim()); // Debugging output
                    Thread.sleep(random.nextInt(2000) + 1000); // Random delay between 1-3 seconds
                }
            }
            writer.close();
            System.out.println("Data scraped successfully. Saved to " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("An error occurred during scraping.");
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}

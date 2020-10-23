package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

//Base class to allow access to browser from hooks
public class BaseClass {
    //local variable that gets assigned below after properties class is instantiated
    public static WebDriver driver;
    public static Properties Pro;



    public static WebDriver getDriver() throws IOException
    {
        Pro = new Properties();
        FileInputStream fls = new FileInputStream("src\\test\\resources\\Objects\\object.properties");
        Pro.load(fls);

        System.setProperty("webdriver.chrome.driver", "./Browser/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        return driver;

    }

    public static Properties propFile() throws Exception {
        Pro = new Properties();
        FileInputStream fls = new FileInputStream("src\\test\\resources\\global.properties");
        Pro.load(fls);

        return Pro;

    }

    public static String randomDate() {

        LocalDate from = LocalDate.of(2000, 1, 1);
        LocalDate to = LocalDate.of(2017, 1, 1);
        long days = from.until(to, ChronoUnit.DAYS);
        long randomDays = ThreadLocalRandom.current().nextLong(days + 1);
        LocalDate randomDate = from.plusDays(randomDays);
        return randomDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static String todaysDate() {
        LocalDate today = LocalDate.now();
        return today.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static String tomorrowsDate() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        return tomorrow.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static String todaysDatePortal() {
        LocalDate today = LocalDate.now();
        return today.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static String tomorrowsDatePortal() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        return tomorrow.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static void waitForPageToLoad()
    {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
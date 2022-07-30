package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import java.time.Duration;
public class Driver {
       private Driver() {// Singleton Pattern yaptik
    }
    static WebDriver driver;
    public static WebDriver getDriver() {
        if (driver == null) {// bunu yapmamızın sebebi driver hic acilmamissa yeni bir
            // driver ac ve ayarları yap acilmissa return driver ile devam et diyor
            switch(ConfigReader.getProperty("browser")){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver =new ChromeDriver();
                    break;
                case "safari":
                    WebDriverManager.safaridriver().setup();
                    driver=new SafariDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver=new FirefoxDriver();
                    break;
                case "headless-chrome":
                    WebDriverManager.chromedriver().setup();
                    driver=new ChromeDriver(new ChromeOptions().setHeadless(true));
                    break;
                    default:
                        WebDriverManager.chromedriver().setup();
                        driver=new ChromeDriver();
            }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {// driver'a deger atanmissa
        driver.close();
        driver=null;
        }
    }
}

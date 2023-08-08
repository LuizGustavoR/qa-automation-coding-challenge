package setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

public class MyWebDriver {

    private static final ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    private static void initDriver() {
        // Setup chromedriver os version
        String osName = System.getProperty("os.name").toLowerCase();
            String webdriversPath = "src/test/resources/webdrivers/";
        if(osName.contains("windows")){
            System.setProperty("webdriver.chrome.driver", webdriversPath + "chromedriver-windows-114.exe");
        }else if(osName.contains("macos") || osName.contains("mac os")){
            System.setProperty("webdriver.chrome.driver", webdriversPath + "chromedriver-macos-115");
        }

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1080");
        options.addArguments("disable-infobars");
//        options.addArguments("--headless=new");

        webDriverThreadLocal.set(new ChromeDriver(options));
        webDriverThreadLocal.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
    }

    public static WebDriver getInstance() {
        if(webDriverThreadLocal.get() == null || ((RemoteWebDriver) webDriverThreadLocal.get()).getSessionId() == null){
            initDriver();
        }
        return webDriverThreadLocal.get();
    }

    public static void tearDown(){
        if(webDriverThreadLocal.get() != null) {
            webDriverThreadLocal.get().quit();
        }
    }

}
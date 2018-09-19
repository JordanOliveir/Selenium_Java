package suporte;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class LoginBankManager {
    public static final String USERNAME = "jordanoliveira1";
    public static final String AUTOMATE_KEY = "sYk1nWCjwWYJXfmYQqy7";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    public static WebDriver logarBKM(){//realiza o teste na maquina
        /*System.setProperty("webdriver.chrome.driver","C:\\Users\\jordan.silva\\Drivers\\geckodriver.exe");
        WebDriver navegador = new FirefoxDriver();
        navegador.get("http://bankmanager.finnet.hom/");*/

        System.setProperty("webdriver.chrome.driver","C:\\Users\\jordan.silva\\Drivers\\chromedriver.exe");
        WebDriver navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // Ir para página desejada
        navegador.get("http://bankmanager.finnet.hom/");


        return navegador;
    }
    public static WebDriver creatBrowserStack(){//realiza o teste na nuvem
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "62.0");
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("resolution", "1280x800");

        WebDriver navegador = null;

        try {
           navegador  = new RemoteWebDriver(new URL(URL), caps);
           navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            // Ir para página desejada
            navegador.get("http://bankmanager.finnet.hom/");
        } catch (MalformedURLException e) {
            //e.printStackTrace();
            System.out.println("Houveram problema com a URL" + e.getMessage());
        }

        return navegador;

    }
}

package BankManager;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import pages.BKMPages;
import pages.LoginPage;
import sun.misc.FormattedFloatingDecimal;
import sun.rmi.runtime.Log;
import suporte.Generator;
import suporte.LoginBankManager;
import suporte.Screenshot;

import javax.jws.WebMethod;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TelaConsultaManutenção {

    private WebDriver navegador;

    @Rule
    public TestName test = new TestName();

    @Before
    public void setUp() {
        navegador = LoginBankManager.logarBKM();
        new LoginPage(navegador)
                .clicarSignIn()
                .fazerLogin("jordan.master","finnet11@");
    }
    @Test
    public void ConsultaManutenção(){
        new BKMPages(navegador)
                .clicarConsultaManutencao();

        String screenshotArquivo = "C:\\Users\\jordan.silva\\Pictures\\ScreenShot\\" + Generator.dataHoraParaArquivo() + test.getMethodName()+".png";
        Screenshot.tirar(navegador, screenshotArquivo);

    }

    @After
    public void tearDown(){
        // Fechar o navegador
        navegador.quit();
        //close() fecha apenas a aba
    }
}

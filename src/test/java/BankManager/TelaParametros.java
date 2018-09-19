package BankManager;

import static org.junit.Assert.*;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.BKMPages;
import pages.LoginPage;
import suporte.Generator;
import suporte.LoginBankManager;
import suporte.Screenshot;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "AbrindoMenuParametros.csv")

public class TelaParametros {
    private WebDriver navegador;

    @Rule
    public TestName test = new TestName();

    @Before
    public void setUp() {
        navegador = LoginBankManager.logarBKM();// utilizando creatbrowserstack executa na nuvem e logarBKM no chrome da maquina
        String textoBemVindo = new LoginPage(navegador)//com o String textoBemVindo consigo realizar assert do texto, se não tem assert não precisa desta parte
                .clicarSignIn()
                .fazerLogin("jordan.master","finnet11@")
                .textoBemVindo();
                assertEquals("Bem Vindo",textoBemVindo);
    }

    @Test
    public void AbrindoMenuParametros (@Param(name = "empresa")String empresa){
        new BKMPages(navegador)
                .clicarParametros();

        //seleciona lista de empresa desejada através do arquivo csv criado
        WebElement selecionar = navegador.findElement(By.id("emp_id_FILTRO"));
        new Select(selecionar).selectByValue(empresa);

        navegador.findElement(By.xpath("//*[@id=\"FiltroParametros\"]/div[2]/input")).click();

        // Tira print após execução da etapa acima
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

package tests;

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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import suporte.Generator;
import suporte.Screenshot;
import suporte.Web;

import java.util.concurrent.TimeUnit;

// runWith + DataLoader vão buscar o arquivo resourcers que defini o nome e inserir as informações que está lá de contato
@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "testAdicionarInformacaoAdicionalDoUsuario.csv")

public class automacao {
    private WebDriver navegador;

    @Rule
    public TestName test = new TestName();

    @Before
    public void setUp(){
        navegador = Web.createChrome();

        // Clicar no link que possue o texto "Sign in"
        navegador.findElement(By.linkText("Sign in")).click();

        // Identificando o formulário de login
        WebElement formularioSignInBox = navegador.findElement(By.id("signinbox"));

        // Digitar de name "login" que esta dentro do formulário de id "signinbox" o texto "jordan10"
        formularioSignInBox.findElement(By.name("login")).sendKeys("jordan10");

        // Digitar de name "password" que esta dentro do formulário de id "signinbox" o texto "123456"
        formularioSignInBox.findElement(By.name("password")).sendKeys("123456");
        // Clicar no link com o texto "SIGN IN"
        navegador.findElement(By.linkText("SIGN IN")).click();

        // Clicar em link que tenha a class "me"
        navegador.findElement(By.className("me")).click();

        // Clicar em um link que possui o texto "MORE DATA ABOUT YOU"
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();

    }
    // adicionando @Param passo os campos existentes no arquivo "vairosLogins" para add as informações corretas
    @Test
    public void testAdicionarInformacaoAdicionalDoUsuario (@Param(name = "tipo")String tipo, @Param(name = "contato")String contato, @Param(name = "mensagem")String mensagemEsperada) {
        // Clicar no botão através do seu xpath //button[@data-target="addmoredata"]
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        // Identificar a popup onde está o formuloário de id "addmoredata"
        WebElement popuAddMoreData = navegador.findElement(By.id("addmoredata"));

        // Na combo de nome "type" escolher a opção "Phone"
        WebElement campoType = popuAddMoreData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText(tipo);

        // No campo de name "contact" digitar "+5511999999999"
        popuAddMoreData.findElement(By.name("contact")).sendKeys(contato);

        // Clicar no link de text "SAVE" que está na popup
        popuAddMoreData.findElement(By.linkText("SAVE")).click();

        // Na mensagem de id "toast-container" validar que o texto é"Your contact has been added!"
        WebElement toastContainer = navegador.findElement(By.id("toast-container"));
        String mensagem = toastContainer.getText();
        assertEquals(mensagemEsperada, mensagem);

        String screenshotArquivo = "C:\\Users\\jordan.silva\\Pictures\\ScreenShot\\" + Generator.dataHoraParaArquivo() + test.getMethodName()+".png";
        Screenshot.tirar(navegador, screenshotArquivo);


        /*
        // Validar que dentro do elemento com class "me" está exibindo o texto "Hi, jordan"
        WebElement me = navegador.findElement(By.className("me"));
        String textoNoElementoMe = me.getText();
        assertEquals("Hi, jordan", textoNoElementoMe);*/

    }

    //@Test
    public void removerUmContatoDoUsuario(){
        // Clicar no elemento pelo seu xpath //span[text()="+5511999999999"]/following-sibling::a
        navegador.findElement(By.xpath("//span[text()=\"+5511999999999\"]/following-sibling::a")).click();

        // Confirmar na janela javascript
        navegador.switchTo().alert().accept();

        // Validar que a mensagem apresentada foi Rest in peace, dear phone!
        WebElement toastContainer = navegador.findElement(By.id("toast-container"));
        String mensagem = toastContainer.getText();
        assertEquals("Rest in peace, dear phone!", mensagem);

        String screenshotArquivo = "C:\\Users\\jordan.silva\\Pictures\\ScreenShot" + Generator.dataHoraParaArquivo() + test.getMethodName()+".png";
        Screenshot.tirar(navegador, screenshotArquivo);

        // Aguardar até 10 segundos para que janela desapareça
        WebDriverWait aguardar = new WebDriverWait(navegador,10);
        aguardar.until(ExpectedConditions.stalenessOf(toastContainer));

        // Clicar no link com texto "Logout"
        navegador.findElement(By.linkText("Logout")).click();

    }

    @After
    public void tearDown(){
        // Fechar o navegador
        navegador.quit();


    }
}

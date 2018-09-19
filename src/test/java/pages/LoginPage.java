package pages;

import BankManager.TelaParametros;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{// cria class por página, no caso aqui é a página de login
    public LoginPage(WebDriver navegador) {
        super(navegador);
    }

    public LoginBKMPage clicarSignIn(){// construtor LoginBKMPage não existia então por isso criou ou class para puxar informações
        navegador.findElement(By.className("ajstdashboard")).click();//busca lugar onde realizar login

        return new LoginBKMPage(navegador);
    }
}

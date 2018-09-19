package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginBKMPage extends BasePage{

    public LoginBKMPage(WebDriver navegador) {
        super(navegador);
    }

    public LoginBKMPage digitarLogin(String login){ //nome LoginBKMPage pq retorna para mesma tela
        navegador.findElement(By.id("frm_login")).findElement(By.id("LoginAcesso")).sendKeys(login);//encontra e imputa valor no campo de login

        return this; //returno this por retorna para mesma página
    }
    public LoginBKMPage digitarPassword (String password){
        navegador.findElement(By.id("frm_login")).findElement(By.id("SenhaAcesso")).sendKeys(password);

        return this;
    }
    public TelaInicial clicarSignIn(){
        navegador.findElement(By.id("BtnAcessar")).click();

        return new TelaInicial(navegador);
    }
    public TelaInicial fazerLogin(String login, String password){// metodo funcional abaixo diminui numeros de linha no teste, e usando as variaveis do estutural acima posso puxar cada variavel individualmente caso ocorra algum problema
        digitarLogin(login);
        digitarPassword(password);
        clicarSignIn();
        return new TelaInicial(navegador);
    }
}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
    protected WebDriver navegador;// deixa como base o public navegador não precisando mais digitar em todas as paginas abertas somente puxa esta

    public BasePage(WebDriver navegador){

        this.navegador = navegador;
    }
    public String textoBemVindo(){
        return navegador.findElement(By.id("DescricaoModulo")).getText();
    }
}

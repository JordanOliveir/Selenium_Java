package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BKMPages extends BasePage {
    public BKMPages(WebDriver navegador) {
        super(navegador);
    }
    public PaginaParametros clicarParametros(){//realiza buscas até encontrar opção desejada e clica nela
        navegador.findElement(By.id("format_layout")).findElement(By.className("menu_itens_content")).findElement(By.id("9")).findElement(By.linkText("Parâmetros")).click();

        return new PaginaParametros(navegador);

    }
    public PaginaConsultaManutencao clicarConsultaManutencao(){
        navegador.findElement(By.id("format_layout")).findElement(By.className("menu_itens_content")).findElement(By.id("9")).findElement(By.linkText("Consulta e Manutenção")).click();

        return new PaginaConsultaManutencao(navegador);
    }
}

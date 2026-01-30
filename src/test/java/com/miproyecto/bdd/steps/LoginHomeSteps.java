package com.miproyecto.bdd.steps;

import com.miproyecto.bdd.context.TestContext;
import com.miproyecto.pages.HomePage;
import com.miproyecto.pages.LoginPage;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LoginHomeSteps {

    private static final String LOGIN_URL = "https://demo.applitools.com/hackathonV2.html";
    private static final String EXPECTED_HOME_URL = "https://demo.applitools.com/hackathonAppV2.html";

    @Dado("que estoy en la página de login")
    public void queEstoyEnLaPaginaDeLogin() {
        WebDriver driver = TestContext.getDriver();
        if (driver != null) {
            driver.get(LOGIN_URL);
        }
    }

    @Cuando("inicio sesión con usuario {string} y contraseña {string}")
    public void inicioSesionConUsuarioYContrasena(String usuario, String contrasena) {
        WebDriver driver = TestContext.getDriver();
        if (driver == null) {
            throw new IllegalStateException("WebDriver no inicializado. Verifica el tag @ui en el feature.");
        }
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setNameusername(usuario);
        loginPage.setpassword(contrasena);
        loginPage.clickButton();
    }

    @Entonces("debo ser redirigido a la página de gastos")
    public void deboSerRedirigidoALaPaginaDeGastos() {
        WebDriver driver = TestContext.getDriver();
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.startsWith(EXPECTED_HOME_URL),
                "No redirigió a la página esperada. URL actual: " + currentUrl);
    }

    @Entonces("la tabla de transacciones debe mostrar exactamente {int} filas")
    public void laTablaDebeMostrarExactamenteFilas(int cantidad) {
        WebDriver driver = TestContext.getDriver();
        HomePage homePage = new HomePage(driver);
        int rowCount = homePage.getTransactionRowCount();
        assertEquals(cantidad, rowCount, "La tabla no tiene exactamente " + cantidad + " transacciones.");
    }

    @Entonces("el balance debe contener {string}")
    public void elBalanceDebeContener(String valor) {
        WebDriver driver = TestContext.getDriver();
        HomePage homePage = new HomePage(driver);
        String balanceText = homePage.balancefield();
        assertTrue(balanceText.contains(valor), "El balance no contiene: " + valor);
    }

    @Entonces("el crédito disponible debe contener {string}")
    public void elCreditoDisponibleDebeContener(String valor) {
        WebDriver driver = TestContext.getDriver();
        HomePage homePage = new HomePage(driver);
        String creditAvailable = homePage.creditAvailable();
        assertTrue(creditAvailable.contains(valor), "El crédito disponible no contiene: " + valor);
    }

    @Entonces("los montos positivos deben mostrarse en verde")
    @Y("los montos negativos deben mostrarse en rojo")
    public void losMontosDebenTenerColoresCorrectos() {
        WebDriver driver = TestContext.getDriver();
        HomePage homePage = new HomePage(driver);
        List<WebElement> amountSpans = homePage.getAllAmountSpans();
        for (WebElement amountSpan : amountSpans) {
            String valueText = amountSpan.getText();
            String classAttribute = amountSpan.getAttribute("class");
            if (valueText.contains("+")) {
                assertTrue(classAttribute.contains("text-success"), "Valor positivo no en verde: " + valueText);
            }
            if (valueText.contains("-")) {
                assertTrue(classAttribute.contains("text-danger"), "Valor negativo no en rojo: " + valueText);
            }
        }
    }
}

package com.miproyecto.tests;

import com.miproyecto.pages.HomePage;
import com.miproyecto.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.List;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestLoginAndHome {

    private WebDriver driver;
    private static final String EXPECTED_HOME_URL = "https://demo.applitools.com/hackathonAppV2.html";

    @BeforeAll
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("user-data-dir=C:\\Users\\dfrom\\OneDrive\\Desktop\\mi-proyecto\\User Data");
        options.addArguments("profile-directory=Default");
        options.addArguments("--incognito");
        options.addArguments("--disable-cache");

        driver = new ChromeDriver(options);

        // Solo una vez, ir al login e iniciar sesión
        driver.get("https://demo.applitools.com/hackathonV2.html");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setNameusername("testuser");
        loginPage.setpassword("testpassword");
        loginPage.clickButton();
    }

    @Test
    public void verificarRedireccionALaTablaDeGastos() {
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertTrue(
            currentUrl.startsWith(EXPECTED_HOME_URL),
            "No redirigió a la página esperada. URL actual: " + currentUrl
        );
    }

    @Test
    public void verificarQueExistenExactamenteSeisTransacciones() {
        HomePage homePage = new HomePage(driver);
        int rowCount = homePage.getTransactionRowCount();
        Assertions.assertEquals(6, rowCount, "La tabla de gastos no tiene exactamente 6 transacciones.");
    }

    @Test
    public void verificarBalanceContieneValorEsperado() {
        HomePage homePage = new HomePage(driver);
        String balanceText = homePage.balancefield();
        System.out.println("Balance capturado: " + balanceText);
        Assertions.assertTrue(balanceText.contains("350"), "El balance no contiene el valor esperado.");
    }

    @Test
    public void verificarCreditoDisponibleContieneValorEsperado() {
        HomePage homePage = new HomePage(driver);
        String creditAvailable = homePage.creditAvailable();
        System.out.println("Credito disponible: " + creditAvailable);
        Assertions.assertTrue(creditAvailable.contains("17,800"), "El credito disponible no contiene el valor esperado.");
    }

    @Test
    public void verificarColoresDeMontos() {
        HomePage homePage = new HomePage(driver);
        List<WebElement> amountSpans = homePage.getAllAmountSpans();
        System.out.println("Cantidad de montos: " + amountSpans.size());
        for (WebElement amountSpan : amountSpans) {

            String valueText = amountSpan.getText();
            String classAttribute = amountSpan.getAttribute("class");

            if (valueText.contains("+")) {
                Assertions.assertTrue(
                    classAttribute.contains("text-success"),
                    "El valor positivo no está en verde: " + valueText
                );
            }

            if (valueText.contains("-")) {
                Assertions.assertTrue(
                    classAttribute.contains("text-danger"),
                    "El valor negativo no está en rojo: " + valueText
                );
            }
        }
    }

    @AfterAll
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

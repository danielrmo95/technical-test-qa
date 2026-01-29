package com.miproyecto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Selectores
    private By balancefield = By.id("totalBalance");
    private By creditAvailable = By.id("creditAvailable");
    private By amountSpans = By.cssSelector("#transactionsTable tbody tr td:nth-child(5) span");
    private By transactionRows = By.cssSelector("#transactionsTable tbody tr");


    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    public String balancefield() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(balancefield));
        return element.getText();
    }
    public String creditAvailable() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(creditAvailable));
        return element.getText();
    }

    // Mantener el método original usado por los tests antiguos
    public List<WebElement> getAllAmounts() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(amountSpans));
    }

    public List<WebElement> getAllAmountSpans() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(amountSpans));
    }

    public int getTransactionRowCount() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(transactionRows)).size();
    }

}

package com.nttdata.screens;

import io.appium.java_client.pagefactory.AndroidFindBy;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SauceLoginScreen extends PageObject {

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/nameET")
    private WebElement txtUsuario;

    @AndroidFindBy(xpath = "(//android.widget.EditText)[2]")
    private WebElement txtPassword;

    @AndroidFindBy(accessibility = "Tap to login with given credentials")
    private WebElement btnLogin;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/loginTV")
    private WebElement tituloApp;

    @AndroidFindBy(xpath = "//androidx.recyclerview.widget.RecyclerView[@content-desc=\"Displays all products of catalog\"]/android.view.ViewGroup")
    private List<WebElement> lista2;


    public void ingresarUsuario(String texto) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(txtUsuario));
        waitFor(ExpectedConditions.elementToBeClickable(txtUsuario));
        txtUsuario.sendKeys(texto);
    }

    public void esperarElemento(WebElement elemento) {
        waitFor(ExpectedConditions.elementToBeClickable(elemento));
    }

    public void ingresarClave(String arg0) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(txtPassword));
        waitFor(ExpectedConditions.elementToBeClickable(txtPassword));
        txtPassword.sendKeys(arg0);
    }

    public void ingresar() {
        btnLogin.click();
    }

    public int getCountElements() {
        //List<WebElement> lista = getDriver().findElements(By.xpath("//android.view.ViewGroup[@content-desc=\"test-Item\"]"));
        return lista2.size();
    }

    //
    public String getTitulo() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.ViewGroup[@content-desc=\"test-Cart drop zone\"]/android.view.ViewGroup/android.widget.TextView")));

        //WebElement titulo2 = getDriver().findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-Cart drop zone\"]/android.view.ViewGroup/android.widget.TextView"));

        //return titulo2.getText();
        return tituloApp.getText();
    }

    public boolean validarPantallaLogin() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(tituloApp));
        return tituloApp.isDisplayed();
    }
}

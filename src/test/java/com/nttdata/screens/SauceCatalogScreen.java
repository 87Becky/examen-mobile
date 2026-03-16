package com.nttdata.screens;

import io.appium.java_client.pagefactory.AndroidFindBy;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.WebElement;

public class SauceCatalogScreen extends PageObject {

    @AndroidFindBy(id= "com.saucelabs.mydemoapp.android:id/menuIV")
    private WebElement btn_menu;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Log In\"]")
    private WebElement btn_login;

    @AndroidFindBy(id ="com.saucelabs.mydemoapp.android:id/productTV")
    private WebElement lbl_product;

    public void clickMenu() {
        btn_menu.click();
    }

    public void clickLogin() {
        btn_login.click();
    }

    public boolean validarPantallaInicial() {
        return lbl_product.isDisplayed();
    }
}

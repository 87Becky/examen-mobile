package com.nttdata.screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SauceCatalogScreen extends PageObject {

    @AndroidFindBy(id= "com.saucelabs.mydemoapp.android:id/menuIV")
    private WebElement btn_menu;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Log In\"]")
    private WebElement btn_login;

    @AndroidFindBy(xpath = "//androidx.recyclerview.widget.RecyclerView[@content-desc=\"Displays all products of catalog\"]/android.view.ViewGroup")
    private List<WebElement> lista2;

    @AndroidFindBy(id ="com.saucelabs.mydemoapp.android:id/productTV")
    private WebElement lbl_product;

    public void clickMenu() {
        btn_menu.click();
    }

    public void clickLogin() {
        btn_login.click();
    }

    public boolean validarPantallaInicial() {

        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

            wait.until(driver -> lista2.size() > 0);

            System.out.println("✅ Productos cargados");

            return true;

        } catch (Exception e) {

            System.out.println("❌ No cargaron productos");
            return false;
        }
    }
    public int getCountElements(){
        return lista2.size();
    }
    public void seleccionarProducto(String nombreProducto) {

        try {
            // 🔥 Scroll usando accessibilityId (content-desc)
            getDriver().findElement(
                    AppiumBy.androidUIAutomator(
                            "new UiScrollable(new UiSelector().scrollable(true))" +
                                    ".scrollIntoView(new UiSelector().description(\"" + nombreProducto + "\"));"
                    )
            );

            System.out.println("🔍 Producto encontrado: " + nombreProducto);

            // 🔥 Click directo al elemento correcto
            WebElement producto = getDriver().findElement(
                    AppiumBy.accessibilityId(nombreProducto)
            );

            producto.click();

            System.out.println("✅ Click en imagen del producto");

        } catch (Exception e) {
            throw new AssertionError("❌ No se pudo seleccionar el producto: " + nombreProducto);
        }
    }


}

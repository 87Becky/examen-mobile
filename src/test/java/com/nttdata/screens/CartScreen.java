package com.nttdata.screens;

import io.appium.java_client.AppiumBy;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.WebElement;

public class CartScreen extends PageObject {

    public void irAlCarrito() {
        WebElement btn = getDriver().findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/cartRL"));
        btn.click();
        System.out.println("🔽 Navegando al carrito");
    }


    public int obtenerCantidadBadge() {
        try {
            WebElement badge = getDriver().findElement(
                    AppiumBy.id("com.saucelabs.mydemoapp.android:id/cartTV")
            );

            String text = badge.getText().trim();
            if (!text.isEmpty()) {
                return Integer.parseInt(text);
            } else {
                System.out.println("Badge vacío, retorno 0");
                return 0;
            }

        } catch (Exception e) {
            System.out.println("No se encontró el badge, retorno 0");
            return 0;
        }
    }

    // ===============================
    // OBTENER CANTIDAD DEL PRODUCTO
    // ===============================
    public int obtenerCantidadProducto() {
        try {
            WebElement cantidadEl = getDriver().findElement(
                    AppiumBy.id("com.saucelabs.mydemoapp.android:id/productQuantityTV") // ID correcto del quantity en carrito
            );

            String valor = cantidadEl.getText().trim();
            System.out.println("Cantidad detectada en carrito: " + valor);
            return Integer.parseInt(valor);

        } catch (Exception e) {
            System.out.println("No se pudo leer la cantidad del producto, retorno 0");
            return 0;
        }
    }

}

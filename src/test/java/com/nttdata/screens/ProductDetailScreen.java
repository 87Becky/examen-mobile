package com.nttdata.screens;

import io.appium.java_client.pagefactory.AndroidFindBy;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.WebElement;
import io.appium.java_client.AppiumBy;
import java.time.Duration;

public class ProductDetailScreen extends PageObject {

    @AndroidFindBy(id= "com.saucelabs.mydemoapp.android:id/plusIV")
    private WebElement btnMas;

    @AndroidFindBy(id= "com.saucelabs.mydemoapp.android:id/cartBt")
    private WebElement btnAdd;

    @AndroidFindBy(id= "com.saucelabs.mydemoapp.android:id/noTV")
    private WebElement txtCantidad;


    public boolean validarPantallaDetalle() {
        try {
            element(btnMas)
                    .withTimeoutOf(Duration.ofSeconds(10))
                    .waitUntilVisible();

            System.out.println("Pantalla de detalle cargada");
            return true;

        } catch (Exception e) {
            System.out.println(" Producto cierra automáticamente. Pantalla no visible.");
            return false;
        }
    }

    public void seleccionarCantidad(int cantidadDeseada) {

        if (!validarPantallaDetalle()) {
            System.out.println("⚠ No se puede seleccionar cantidad, pantalla cerrada");
            return;
        }

        element(txtCantidad).withTimeoutOf(Duration.ofSeconds(10)).waitUntilVisible();

        int cantidadActual = Integer.parseInt(txtCantidad.getText().trim());
        System.out.println("Cantidad inicial: " + cantidadActual);

        while (cantidadActual < cantidadDeseada) {
            btnMas.click();
            final int valorAnterior = cantidadActual;

            waitForCondition().until(driver -> {
                String texto = txtCantidad.getText().trim();
                return Integer.parseInt(texto) > valorAnterior;
            });

            cantidadActual = Integer.parseInt(txtCantidad.getText().trim());
            System.out.println("Cantidad actualizada: " + cantidadActual);
        }

        System.out.println("Cantidad final alcanzada: " + cantidadActual);
    }


    public void scrollHastaAddToCart() {
        try {
            getDriver().findElement(
                    AppiumBy.androidUIAutomator(
                            "new UiScrollable(new UiSelector().scrollable(true))" +
                                    ".scrollIntoView(new UiSelector().resourceId(\"com.saucelabs.mydemoapp.android:id/cartBt\"));"
                    )
            );
            System.out.println("Scroll hasta Add to Cart");
        } catch (Exception e) {
            System.out.println("Scroll no necesario o falló");
        }
    }

    public void agregarAlCarrito() {

        scrollHastaAddToCart();

        int intentos = 0;
        boolean agregado = false;

        while (intentos < 3 && !agregado) {

            try {
                element(btnAdd)
                        .withTimeoutOf(Duration.ofSeconds(10))
                        .waitUntilClickable()
                        .click();

                agregado = true;
                System.out.println("Producto agregado al carrito");

            } catch (Exception e) {

                System.out.println("Intento fallido #" + (intentos + 1));

                try {
                    evaluateJavascript("arguments[0].click();", btnAdd);
                    agregado = true;
                    System.out.println("Click con JS ejecutado");
                } catch (Exception ex) {
                    System.out.println("Fallback falló");
                }
            }

            intentos++;
        }

        if (!agregado) {
            System.out.println("⚠ Producto no agregado: Pantalla cerrada o btnAdd inaccesible");
        }
    }

}

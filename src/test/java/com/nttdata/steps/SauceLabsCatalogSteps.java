package com.nttdata.steps;

import com.nttdata.screens.SauceCatalogScreen;

public class SauceLabsCatalogSteps {
    SauceCatalogScreen catalog;

    public void validarVentanaInicial() {
        // Validar que se encuentra en la pantalla inicial de SauceDemo
        boolean isOnInitialScreen = catalog.validarPantallaInicial();
        if (!isOnInitialScreen)
            throw new AssertionError("No se encuentra en la pantalla inicial de SauceDemo");
    }

    public void irALaVentanaDeLogin() {
        catalog.clickMenu();
        catalog.clickLogin();
    }
}

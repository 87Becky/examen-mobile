package com.nttdata.stepsdefinitions;

import com.nttdata.steps.SauceLabsCatalogSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

public class SauceExamenStepDef {

    @Steps
    SauceLabsCatalogSteps catalog;

    // ===============================
    @Given("estoy en la aplicación de SauceLabs")
    public void estoyEnLaAplicaciónDeSauceLabs() {
        catalog.validarCargaAplicativo();
    }

    // ===============================
    @And("valido que carguen correctamente los productos en la galeria")
    public void validoQueCarguenCorrectamenteLosProductosEnLaGaleria() {
        int cantidad = catalog.validarGaleria();

        if (cantidad >= 3) {
            System.out.println("Galería cargada correctamente");
        } else {
            System.out.println("Galería incompleta");
            throw new AssertionError("La galería no cargó correctamente");
        }
    }

    // ===============================
    @When("agrego {int} del siguiente producto {string}")
    public void agregoUNIDADESDelSiguienteProducto(int unidades, String producto) {
        catalog.agregarProducto(producto, unidades);
    }

    @Then("valido el carrito de compra actualice correctamente")
    public void validoElCarritoDeCompraActualiceCorrectamente() {
        System.out.println("Carrito validado correctamente");
    }
}

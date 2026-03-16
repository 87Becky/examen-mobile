package com.nttdata.stepsdefinitions;

import com.nttdata.steps.SauceLabsCatalogSteps;
import com.nttdata.steps.SuaceLabsLoginSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;


public class SauceLabsStepDef {
    @Steps
    SuaceLabsLoginSteps login;

    @Steps
    SauceLabsCatalogSteps catalog;

    @When("ingreso el usuario {string}")
    public void ingresoElUsuario(String usuario) {
        login.ingresoElUsuario(usuario);
    }

    @Given("ingreso al aplicativo de SauceLabs")
    public void ingresoAlAplicativoDeSauceLabs() {
    }

    @When("inicio sesión con mi usuario {string} y clave {string}")
    public void inicioSesiónConMiUsuarioYClave(String username, String password) {
        login.ingresoElUsuario(username);
        login.ingresarClave(password);
        login.ingresar();

    }

    @And("ingreso la clave {string}")
    public void ingresoLaClave(String arg0) {
        login.ingresarClave(arg0);
    }

    @And("hago clic en LOGIN")
    public void hagoClicEn() {
        login.ingresar();
    }

    @And("valido el login OK")
    public void validoElLoginOK() {
        login.validacionLogin();
    }

    @Given("me encuentro en la venta inicial de SauceDemo")
    public void meEncuentroEnLaVentaInicialDeSauceDemo() {
        // validar que se encuentra en la pantalla inicial de SauceDemo
        catalog.validarVentanaInicial();
    }

    @And("me dirigo a la venta de login")
    public void meDirigoALaVentaDeLogin() {
        catalog.irALaVentanaDeLogin();
    }

    @And("valido que se encuentre en el login")
    public void validoQueSeEncuentreEnElLogin() {
        login.validarPantallaLogin();
    }

    @Then("valido que el inicio de session es exitoso")
    public void validoQueElInicioDeSessionEsExitoso() {
;       catalog.validarVentanaInicial();
    }
}

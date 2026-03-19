package com.nttdata.steps;

import com.nttdata.screens.SauceCatalogScreen;
import com.nttdata.screens.ProductDetailScreen;
import com.nttdata.screens.CartScreen;
import org.junit.Assert;

public class SauceLabsCatalogSteps {

    SauceCatalogScreen catalogo;
    ProductDetailScreen detail;
    CartScreen cartScreen;


    public void validarCargaAplicativo() {
        boolean isOnInitialScreen = catalogo.validarPantallaInicial();
        if (!isOnInitialScreen) {
            throw new AssertionError("No me encuentro en la pantalla inicial de SauceDemo");
        } else {
            System.out.println("Me encuentro en el aplicativo");
        }
    }


    public int validarGaleria() {
        int cantidad = catalogo.getCountElements();
        System.out.println("Productos encontrados: " + cantidad);
        return cantidad;
    }

    // Variables privadas
    private String productoActual;
    private int cantidadEsperada;


    public void agregarProducto(String producto, int unidades) {
        catalogo.seleccionarProducto(producto);

        detail.validarPantallaDetalle();
        detail.seleccionarCantidad(unidades);
        detail.agregarAlCarrito();

        // Guardamos el producto y la cantidad actual
        this.productoActual = producto;
        this.cantidadEsperada = unidades;

        System.out.println("Producto agregado: " + producto + " | Cantidad: " + unidades);
    }


    public void validarCarrito(String producto, int cantidad) {
        // Guardamos para seguimiento interno si quieres
        this.productoActual = producto;
        this.cantidadEsperada = cantidad;

        // Navegar al carrito
        cartScreen.irAlCarrito();

        // Obtener cantidad del badge
        int cantidadEnBadge = cartScreen.obtenerCantidadBadge();
        System.out.println("🔹 Badge del carrito: " + cantidadEnBadge);

        if (cantidadEnBadge < cantidad) {
            Assert.fail("Badge del carrito (" + cantidadEnBadge + ") menor que la cantidad esperada (" + cantidad + ")");
        }

        // Obtener cantidad del producto en el carrito
        int cantidadEnCarrito = cartScreen.obtenerCantidadProducto();
        Assert.assertEquals(" Error: El producto " + producto + " no tiene la cantidad correcta",
                cantidad, cantidadEnCarrito);
    }

    // ===============================
    // IR A LOGIN
    // ===============================
    public void irALaVentanaDeLogin() {
        catalogo.clickMenu();
        catalogo.clickLogin();
    }
}

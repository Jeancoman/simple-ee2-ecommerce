<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="style.css" />
    </head>
    <body>
        <header class="header">
            <div class="logo">
                <a href="Controlador?accion=Home">
                    <img src="./img/logo.png" alt="Logo">
                </a>
            </div>
            <div>
                <c:if var="clienteActual" test="${clienteActual != null}">
                    <a class="mis-compras" href="Controlador?accion=MisCompras">
                        Mis compras
                    </a>
                </c:if>
            </div>
            <div class="shopping-cart">
                <a href="Controlador?accion=Carrito">
                    <img src="./img/shopping_cart.svg" alt="Carrito">
                </a>
                <div class="cantidad">
                    ${contador}
                </div>
            </div>
            <div class="cuenta">
                <a href="Controlador?accion=Cuenta">
                    <img src="./img/account_circle.svg" alt="Cuenta">
                </a>
            </div>
        </header>
        <main>
            <div class="cart-container">
                <div class="static-row">
                    <div class="product-col">
                        Producto
                    </div>
                    <div class="precio-col">
                        Precio
                    </div>
                    <div class="cantidad-col">
                        Cantidad
                    </div>
                    <div class="total-col">
                        Total
                    </div>
                </div>
                <c:forEach var="producto" items="${carrito}">
                    <div class="dynamic-row">
                        <div class="row-product">
                            <img src="${producto.getFoto()}" alt="producto">
                            <div class="row-name">
                                ${producto.getNombre()}
                                <div class="item">
                                    <small>#${producto.getItem()}</small>
                                </div>
                            </div>
                        </div>
                        <div class="row-price">
                            USD ${producto.getPrecioFormateado()}
                        </div>
                        <div class="row-cantidad">
                            <div class="cantidad-button">
                                <a class="decrement" href="Controlador?accion=Reducir&amp;idProducto=${producto.getItem()}">
                                    —
                                </a>
                                <div class="text">
                                    ${producto.getCantidad()}
                                </div>
                                <a class="increment" href="Controlador?accion=Añadir&amp;idProducto=${producto.getItem()}">
                                    +
                                </a>
                            </div>
                        </div>
                        <div class="row-total">
                            USD ${producto.getSubTotalFormateado()}
                        </div>
                        <div>
                            <a class="eliminar" href="Controlador?accion=Eliminar&amp;idProducto=${producto.getItem()}">
                                X
                            </a>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="carrito-comprar">
                <div class="carrito-total">
                    Total: USD ${totalPagar}
                </div>
                <div>
                    <a class="boton-seccion"  href="Controlador?accion=Comprar">
                        Comprar
                    </a>
                </div>
            </div>
        </main>
    </body>
</html>

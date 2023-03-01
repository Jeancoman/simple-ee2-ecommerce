<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
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
            <div class="product-container">
                <c:forEach var="producto" items="${productos}">
                    <div class="product">
                        <div class="product-img">
                            <img src="${producto.getFoto()}" alt="Producto">
                        </div>
                        <hr>
                        <div class="product-info">
                            <div class="product-price">
                                USD ${producto.getPrecioFormateado()}
                            </div>
                            <div class="product-name">
                                ${producto.getNombre()}
                            </div>
                        </div>
                        <div>
                            <a href="Controlador?accion=AñadirCarrito&amp;id=${producto.getId()}" class="product-button">
                                Añadir al carrito
                            </a>
                        </div>
                    </div>      
                </c:forEach>
            </div>
        </main>
    </body>
</html>

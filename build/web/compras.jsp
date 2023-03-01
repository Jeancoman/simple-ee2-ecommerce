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
            <div class="compras-container">
                <div class="static-row-compras">
                    <div>
                        Codigo de Compra
                    </div>
                    <div>
                        Codigo de Pago
                    </div>
                    <div>
                        Fecha de Compra
                    </div>
                    <div>
                        Monto
                    </div>
                    <div>
                        Estado
                    </div>
                </div>
                <c:forEach var="compra" items="${compras}">
                    <a href="Controlador?accion=Detalles&amp;idCompra=${compra.getId()}" class="dynamic-row-compras">
                        <div>
                            ${compra.getId()}
                        </div>
                        <div>
                            ${compra.getIdPago()}
                        </div>
                        <div>
                            ${compra.getFecha()}
                        </div>
                        <div>
                            USD ${compra.getMontoFormateado()}
                        </div>
                        <div>
                            ${compra.getEstado()}
                        </div>

                    </a>
                </c:forEach>
            </div>
        </main>
    </body>
</html>

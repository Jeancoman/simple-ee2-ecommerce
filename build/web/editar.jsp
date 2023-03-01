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
            <div class="editar-container">
                <form action="Controlador?accion=Editar" method="POST" class="editar-form">
                    <input type="text" name="nombre" placeholder="Nombre">
                    <input type="text" name="apellido" placeholder="Apellido">
                    <input type="text" name="cedula" placeholder="Cedula">
                    <input type="text" name="direccion" placeholder="Dirección">
                    <input type="email" name="email" placeholder="Email">
                    <input type="password" name="password" placeholder="Contraseña">
                    <button>Guardar</button>
                </form>
            </div>
        </main>
    </body>
</html>
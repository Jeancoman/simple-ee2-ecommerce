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
            <div >
                <a class="mis-compras" href="Controlador?accion=MisCompras">
                    Mis compras
                </a>
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
            <a href="Controlador?accion=Carrito" class="exito">
                ¡Compra realizada exitosamente!
            </a>
        </main>
    </body>
</html>

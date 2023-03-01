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
            <div class="perfil-container">
                <div class="acciones-cont">
                    <div>
                        <h2>Mi perfil</h2>
                    </div>
                    <div class="opciones">
                        <div class="logout">
                            <a href="Controlador?accion=Logout">
                                <img src="./img/logout.svg" alt="Salir de la cuenta">
                            </a>
                        </div>
                        <div class="editar">
                            <a href="Controlador?accion=EditarCuenta">
                                <img src="./img/edit.svg" alt="Editar">
                            </a>
                        </div>
                        <div class="eliminar-cuenta">
                            <a href="Controlador?accion=EliminarCuenta">
                                <img src="./img/delete_forever.svg" alt="Logo">
                            </a>
                        </div> 
                    </div>
                </div>

                <div class="informacion-general">
                    ${clienteActual.getNombre()} ${clienteActual.getApellido()}
                </div>
                <div class="informacion-especifica">
                    <div class="informacion-container">
                        <div>
                            <div>
                                Email
                            </div>
                            <div>
                                ${clienteActual.getEmail()}
                            </div>
                        </div>
                        <div>
                            <div>
                                Dirección
                            </div>
                            <div>
                                ${clienteActual.getDireccion()}
                            </div> 
                        </div>
                        <div>
                            <div>
                                Cedula
                            </div>
                            <div>
                                ${clienteActual.getCedula()}
                            </div> 
                        </div>
                    </div>
                </div>

            </div>
        </main>
    </body>
</html>

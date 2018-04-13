<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>accueil</title>
        <link href="css/moncss.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <nav>
            <c:url value="FrontControleur?section=menu-main" var="url01" />
            <c:import url="${url01}"/>
        </nav>
        <hr />
        <main>
            <section>
                <h1>Home</h1>
                <p>Bienvenue dans votre magasin</p>
                <p class="info">${info}</p>
            </section>
            
        </main>
    </body>
</html>

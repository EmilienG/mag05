<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/moncss.css" rel="stylesheet" type="text/css"/>
        <title>login</title>
    </head>
    <body>
        <nav>
            <c:url value="FrontControleur?section=menu-main" var="url01" />
            <c:import url="${url01}"/>
        </nav>
        <hr />
        <main>
            <section>
                <h1>Connexion</h1>
                <form action="FrontControleur" method="POST" accept-charset="UTF-8">
                    <input type="hidden" name="section" value="login" />
                    <label>pseudo</label><br />
                    <input type="text" name="pseudo" value="${pseudoF}" /><br />
                    <label>mot de passe </label><br />
                    <input type="password" name="mdp" /><br />
                    <input type="submit" value="valider" /><br/ ><label class="erreur">${errLogin}</label>
                </form>
            </section>
        </main>
    </body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/moncss.css" rel="stylesheet" type="text/css"/>
        <title>inscription</title>
    </head>
    <body>
        <nav>
            <c:url value="FrontControleur?section=menu-main" var="url01" />
            <c:import url="${url01}"/>
        </nav>
        <hr />
        <main>
            <section>
                <h1>Inscription</h1>
                <form action="FrontControleur" method="POST" accept-charset="UTF-8">
                    <input type="hidden" name="section" value="inscription" />
                    <label>pseudo</label><br />
                    <input type="text" name="pseudo" value="${pseudoF}" /><label class="erreur">${errPseudo}</label><br />
                    <label>email</label><br />
                    <input type="text" name="mail" value="${mailF}" /><label class="erreur">${errMail}</label><br />
                    <label>mot de passe </label><br />
                    <input type="password" name="mdp" /><label class="erreur">${errMdp}</label><br />
                    <input type="submit" value="valider" />
                </form>
            </section>
        </main>
    </body>
</html>

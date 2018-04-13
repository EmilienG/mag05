<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>catalogue</title>
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
                <h1>Catalogue</h1>
                <table>
                    <thead>
                        <tr>
                            <th>reference</th>
                            <th>nom</th>
                            <th>description</th>
                            <th>prixHT</th>
                            <th>tva</th>
                            <th>prixTTC</th>
                            <th>stock</th>
                            <th>action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${catalogue}" var="p"> 
                            <tr>
                                <td>${p.reference}</td>
                                <td>${p.nom}</td>
                                <td>${p.description}</td>
                                <td><fmt:formatNumber minIntegerDigits="1" minFractionDigits="2" maxFractionDigits="2"  value="${p.prixHT}" /></td>
                                <td>${p.taxe.taux}</td>
                                <td><fmt:formatNumber minIntegerDigits="1" minFractionDigits="2" maxFractionDigits="2"  value="${p.prixTTC}" /></td>
                                <td>${p.stock}</td>
                                <td>
                                    <c:url value="FrontControleur?section=operations-panier&action=add&ref=${p.reference}" var="url111" />
                                    <a href="${url111}">ajouter au panier</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                
            </section>
        </main>
    </body>
</html>

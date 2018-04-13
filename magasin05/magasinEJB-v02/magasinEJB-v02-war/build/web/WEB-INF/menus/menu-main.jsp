<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<ul class="menu-main">
    <li>
        <c:url value="FrontControleur?section=creer-donnees" var="url01" />
        <a href="${url01}">creer les données</a> | 
    </li>
    <li>
        <c:url value="FrontControleur?section=afficher-catalogue" var="url02" />
        <a href="${url02}" >calatalogue</a> | 
    </li>
    <li>
        <a href="#" >panier(${infoPanier} : <fmt:formatNumber minIntegerDigits="1" maxFractionDigits="2" minFractionDigits="2" value="${prixTotal}" />) €</a> | 
    </li>
    <li>
        <c:url value="FrontControleur?section=vers-login" var="url249" />
        <a href="${url249}" >connexion</a> | 
    </li>
    <li>
        <c:url value="FrontControleur?section=vers-inscription" var="url250" />
        <a href="${url250}" >inscription</a>
    </li>
    <li>
        <c:url value="FrontControleur?section=modifier" var="url251" />
        <a href="${url251}" >modifier</a>
    </li>
</ul>

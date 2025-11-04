<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Donneurs Compatibles</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 font-sans min-h-screen">

<header class="bg-red-600 text-white py-4 shadow-md">
    <div class="container mx-auto text-center">
        <h1 class="text-2xl font-bold uppercase tracking-wide">
            Liste des Donneurs Compatibles
        </h1>
    </div>
</header>

<main class="container mx-auto mt-8 bg-white p-6 rounded-xl shadow-lg">
    <c:choose>
        <c:when test="${not empty donneursCompatibles}">
            <table class="w-full text-left border-collapse">
                <thead>
                    <tr class="bg-red-500 text-white">
                        <th class="p-3">#</th>
                        <th class="p-3">Nom</th>
                        <th class="p-3">Groupe Sanguin</th>
                        <th class="p-3">Disponibilité</th>
                        <th class="p-3 text-center">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="d" items="${donneursCompatibles}" varStatus="loop">
                        <tr class="hover:bg-gray-50 border-b">
                            <td class="p-3">${loop.index + 1}</td>
                            <td class="p-3 font-semibold text-gray-700">${d.nom} ${d.prenom}</td>
                            <td class="p-3">${d.groupeSanguin}</td>
                            <td class="p-3">
                                <span class="px-3 py-1 text-sm rounded-full
                                    ${d.statut == 'DISPONIBLE' ? 'bg-green-100 text-green-700' : 'bg-gray-200 text-gray-500'}">
                                    ${d.statut == 'DISPONIBLE' ? 'Disponible' : 'Indisponible'}
                                </span>
                            </td>

                            <!-- Bouton Associer -->
                            <td class="p-3 text-center">
                                <form action="assosiate" method="post" class="inline">
                                    <input type="hidden" name="action" value="associate">
                                    <input type="hidden" name="donneurId" value="${d.id}">
                                    <input type="hidden" name="receveurId" value="${param.receveurId}">

                                    <button type="submit"
                                        class="font-semibold px-4 py-2 rounded-lg transition
                                            ${d.statut == 'DISPONIBLE' ? 'bg-green-500 hover:bg-green-600 text-white' : 'bg-gray-300 text-gray-600 cursor-not-allowed'}"
                                        ${d.statut != 'DISPONIBLE' ? 'disabled' : ''}>
                                        Associer
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:when>

        <c:otherwise>
            <div class="text-center py-10">
                <h2 class="text-xl font-semibold text-gray-600">Aucun donneur compatible trouvé</h2>
                <a href="${pageContext.request.contextPath}/receveurs" class="mt-4 inline-block bg-red-500 hover:bg-red-600 text-white px-6 py-2 rounded-lg">
                    Retour
                </a>
            </div>
        </c:otherwise>
    </c:choose>
</main>

</body>
</html>

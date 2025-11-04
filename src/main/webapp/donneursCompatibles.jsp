<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Donneurs Compatibles - Banque de Sang</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50 min-h-screen">

    <!-- Header -->
    <header class="bg-red-600 text-white shadow-lg">
        <div class="container mx-auto px-4 py-4 flex justify-between items-center">
            <h1 class="text-2xl font-bold flex items-center gap-2">
                <span class="text-3xl">&#128137;</span>
                Banque de Sang
            </h1>
            <nav>
                <ul class="flex space-x-6">
                    <li><a href="index.jsp" class="hover:underline transition">Accueil</a></li>
                    <li><a href="Donneurs.jsp" class="hover:underline transition">Donneurs</a></li>
                    <li><a href="Receveurs.jsp" class="hover:underline transition">Receveurs</a></li>
                    <li><a href="creation.jsp" class="hover:underline transition">Ajouter</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <main class="container mx-auto px-4 py-8">
        <!-- Page Header -->
        <div class="mb-8">
            <div class="flex items-center justify-between">
                <div>
                    <h2 class="text-4xl font-bold text-gray-800 uppercase tracking-wide">
                        Donneurs Compatibles
                    </h2>
                    <p class="text-gray-600 mt-2">
                        Liste des donneurs compatibles pour le receveur sélectionné
                    </p>
                </div>
                <a href="receveurs" class="bg-gray-600 text-white px-6 py-3 rounded-lg hover:bg-gray-700 transition font-semibold shadow-lg">
                    ← Retour aux Receveurs
                </a>
            </div>
        </div>

        <!-- Receveur Info Card -->
        <c:if test="${not empty receveur}">
            <div class="bg-gradient-to-r from-red-50 to-orange-50 border-l-4 border-red-600 p-6 rounded-lg shadow-md mb-8">
                <div class="flex items-center gap-4">
                    <div class="bg-red-600 text-white p-4 rounded-full">
                        <span class="text-3xl">&#128100;</span>
                    </div>
                    <div class="flex-1">
                        <h3 class="text-xl font-bold text-gray-800">
                            ${receveur.nom} ${receveur.prenom}
                        </h3>
                        <div class="flex gap-6 mt-2 text-sm text-gray-700">
                            <span><strong>Groupe:</strong> ${receveur.groupeSanguin}</span>
                            <span><strong>Urgence:</strong> ${receveur.urgence}</span>
                            <span><strong>Poches nécessaires:</strong> ${receveur.pochesNecessaires}</span>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>

        <!-- Success/Error Messages -->
        <c:if test="${not empty successMessage}">
            <div class="bg-green-100 border-l-4 border-green-500 text-green-700 p-4 mb-6 rounded-lg shadow">
                <p class="font-semibold">&#9989; ${successMessage}</p>
            </div>
        </c:if>

        <c:if test="${not empty errorMessage}">
            <div class="bg-red-100 border-l-4 border-red-500 text-red-700 p-4 mb-6 rounded-lg shadow">
                <p class="font-semibold">&#10060; ${errorMessage}</p>
            </div>
        </c:if>

        <!-- Donneurs Table -->
        <div class="bg-white rounded-xl shadow-lg overflow-hidden">
            <c:choose>
                <c:when test="${not empty donneursCompatibles}">
                    <!-- Statistics Bar -->
                    <div class="bg-gradient-to-r from-red-500 to-red-600 text-white px-6 py-4">
                        <div class="flex justify-between items-center">
                            <span class="text-lg font-semibold">
                                ${donneursCompatibles.size()} donneurs compatibles trouvés
                            </span>
                            <span class="text-sm">
                                Donneurs disponibles:
                                <c:set var="countDisponible" value="0"/>
                                <c:forEach var="d" items="${donneursCompatibles}">
                                    <c:if test="${d.statut == 'DISPONIBLE'}">
                                        <c:set var="countDisponible" value="${countDisponible + 1}"/>
                                    </c:if>
                                </c:forEach>
                                <strong>${countDisponible}</strong>
                            </span>
                        </div>
                    </div>

                    <!-- Table -->
                    <div class="overflow-x-auto">
                        <table class="w-full text-left border-collapse">
                            <thead>
                                <tr class="bg-gray-50 border-b-2 border-gray-200">
                                    <th class="px-6 py-4 text-xs font-medium text-gray-500 uppercase tracking-wider">#</th>
                                    <th class="px-6 py-4 text-xs font-medium text-gray-500 uppercase tracking-wider">Nom Complet</th>
                                    <th class="px-6 py-4 text-xs font-medium text-gray-500 uppercase tracking-wider">CIN</th>
                                    <th class="px-6 py-4 text-xs font-medium text-gray-500 uppercase tracking-wider">Groupe Sanguin</th>
                                    <th class="px-6 py-4 text-xs font-medium text-gray-500 uppercase tracking-wider">Téléphone</th>
                                    <th class="px-6 py-4 text-xs font-medium text-gray-500 uppercase tracking-wider">Disponibilité</th>
                                    <th class="px-6 py-4 text-xs font-medium text-gray-500 uppercase tracking-wider text-center">Action</th>
                                </tr>
                            </thead>
                            <tbody class="bg-white divide-y divide-gray-200">
                                <c:forEach var="d" items="${donneursCompatibles}" varStatus="loop">
                                    <tr class="hover:bg-gray-50 transition">
                                        <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">
                                            ${loop.index + 1}
                                        </td>
                                        <td class="px-6 py-4 whitespace-nowrap">
                                            <div class="flex items-center">
                                                <div class="bg-red-100 p-2 rounded-full mr-3">
                                                    <span class="text-lg">&#128100;</span>
                                                </div>
                                                <div>
                                                    <div class="text-sm font-semibold text-gray-900">${d.nom} ${d.prenom}</div>
                                                    <div class="text-xs text-gray-500">${d.sexe}</div>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-700">
                                            ${d.cin}
                                        </td>
                                        <td class="px-6 py-4 whitespace-nowrap">
                                            <span class="px-3 py-1 inline-flex text-sm leading-5 font-semibold rounded-full bg-red-100 text-red-800">
                                                ${d.groupeSanguin}
                                            </span>
                                        </td>
                                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-700">
                                            ${d.telephone}
                                        </td>
                                        <td class="px-6 py-4 whitespace-nowrap">
                                            <c:choose>
                                                <c:when test="${d.statut == 'DISPONIBLE'}">
                                                    <span class="px-3 py-1 inline-flex text-xs leading-5 font-semibold rounded-full bg-green-100 text-green-700">
                                                        &#9989; Disponible
                                                    </span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="px-3 py-1 inline-flex text-xs leading-5 font-semibold rounded-full bg-gray-200 text-gray-600">
                                                        &#10060; Indisponible
                                                    </span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>

                                        <!-- Bouton Associer -->
                                        <td class="px-6 py-4 text-center">
                                            <form action="assosiate" method="post" class="inline">
                                                <input type="hidden" name="action" value="associate">
                                                <input type="hidden" name="donneurId" value="${d.id}">
                                                <input type="hidden" name="receveurId" value="${param.receveurId}">

                                                <c:choose>
                                                    <c:when test="${d.statut == 'DISPONIBLE'}">
                                                        <button type="submit"
                                                            class="bg-green-500 hover:bg-green-600 text-white font-semibold px-6 py-2 rounded-lg transition shadow-md">
                                                            &#10004; Associer
                                                        </button>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <button type="button" disabled
                                                            class="bg-gray-300 text-gray-600 font-semibold px-6 py-2 rounded-lg cursor-not-allowed">
                                                            &#10060; Indisponible
                                                        </button>
                                                    </c:otherwise>
                                                </c:choose>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:when>

                <c:otherwise>
                    <!-- Empty State -->
                    <div class="text-center py-16 px-6">
                        <div class="text-6xl mb-4">&#128577;</div>
                        <h3 class="text-2xl font-semibold text-gray-700 mb-2">
                            Aucun donneur compatible trouvé
                        </h3>
                        <p class="text-gray-500 mb-6">
                            Il n'y a actuellement aucun donneur compatible disponible pour ce receveur.
                        </p>
                        <a href="receveurs"
                           class="inline-block bg-red-600 hover:bg-red-700 text-white px-8 py-3 rounded-lg font-semibold transition shadow-lg">
                            ← Retour aux Receveurs
                        </a>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

        <!-- Info Box -->
        <div class="mt-8 bg-blue-50 border-l-4 border-blue-500 p-6 rounded-lg shadow">
            <div class="flex items-start">
                <div class="text-2xl mr-3">&#128161;</div>
                <div>
                    <h4 class="font-semibold text-blue-900 mb-2">Règles de compatibilité sanguine</h4>
                    <p class="text-sm text-blue-800 leading-relaxed">
                        Les donneurs affichés sont compatibles avec le groupe sanguin du receveur selon les règles de transfusion sanguine.
                        Seuls les donneurs avec le statut <strong>"DISPONIBLE"</strong> peuvent être associés au receveur.
                    </p>
                </div>
            </div>
        </div>
    </main>

</body>
</html>
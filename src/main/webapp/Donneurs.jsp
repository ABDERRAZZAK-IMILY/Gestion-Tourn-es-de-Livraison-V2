<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des Donneurs - Banque de Sang</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50">

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
                    <li><a href="${pageContext.request.contextPath}/donneurs" class="font-bold underline">Donneurs</a></li>
                    <li><a href="${pageContext.request.contextPath}/receveurs" class="hover:underline transition">Receveurs</a></li>
                    <li><a href="creation.jsp" class="hover:underline transition">Ajouter</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <div class="container mx-auto px-4 py-8">
        <!-- Page Header -->
        <div class="flex justify-between items-center mb-8">
            <div>
                <h2 class="text-4xl font-bold text-gray-800">Liste des Donneurs</h2>
                <p class="text-gray-600 mt-2">Gestion et suivi des donneurs de sang</p>
            </div>
            <a href="creation.jsp" class="bg-red-600 text-white px-6 py-3 rounded-lg hover:bg-red-700 transition font-semibold shadow-lg">
                + Nouveau Donneur
            </a>
        </div>

        <!-- Statistics Cards -->
        <div class="grid grid-cols-1 md:grid-cols-4 gap-6 mb-8">
            <div class="bg-white rounded-lg shadow p-6">
                <div class="flex items-center justify-between">
                    <div>
                        <p class="text-gray-500 text-sm font-medium">Total Donneurs</p>
                        <p class="text-3xl font-bold text-gray-800 mt-1">${totalDonneurs}</p>
                    </div>
                    <div class="bg-blue-100 p-3 rounded-full">
                        <span class="text-2xl">&#128100;</span>
                    </div>
                </div>
            </div>

            <div class="bg-white rounded-lg shadow p-6">
                <div class="flex items-center justify-between">
                    <div>
                        <p class="text-gray-500 text-sm font-medium">Disponibles</p>
                        <p class="text-3xl font-bold text-green-600 mt-1">${donneursDisponibles}</p>
                    </div>
                    <div class="bg-green-100 p-3 rounded-full">
                        <span class="text-2xl">&#9989;</span>
                    </div>
                </div>
            </div>

            <div class="bg-white rounded-lg shadow p-6">
                <div class="flex items-center justify-between">
                    <div>
                        <p class="text-gray-500 text-sm font-medium">Non Disponibles</p>
                        <p class="text-3xl font-bold text-orange-600 mt-1">${donneursNonDisponibles}</p>
                    </div>
                    <div class="bg-orange-100 p-3 rounded-full">
                        <span class="text-2xl">&#9888;</span>
                    </div>
                </div>
            </div>

            <div class="bg-white rounded-lg shadow p-6">
                <div class="flex items-center justify-between">
                    <div>
                        <p class="text-gray-500 text-sm font-medium">Non Eligibles</p>
                        <p class="text-3xl font-bold text-red-600 mt-1">${donneursNonEligibles}</p>
                    </div>
                    <div class="bg-red-100 p-3 rounded-full">
                        <span class="text-2xl">&#10060;</span>
                    </div>
                </div>
            </div>
        </div>

        <!-- Filters and Search -->
        <!-- <div class="bg-white rounded-lg shadow p-6 mb-6">
            <form action="donneurs" method="GET" class="grid grid-cols-1 md:grid-cols-4 gap-4">
                <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">Rechercher</label>
                    <input type="text" name="search" value="${param.search}"
                           placeholder="Nom, prenom, CIN..."
                           class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-red-500 focus:border-transparent">
                </div>

                <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">Groupe Sanguin</label>
                    <select name="groupeSanguin" class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-red-500 focus:border-transparent">
                        <option value="">Tous les groupes</option>
                        <option value="O-" ${param.groupeSanguin == 'O-' ? 'selected' : ''}>O-</option>
                        <option value="O+" ${param.groupeSanguin == 'O+' ? 'selected' : ''}>O+</option>
                        <option value="A-" ${param.groupeSanguin == 'A-' ? 'selected' : ''}>A-</option>
                        <option value="A+" ${param.groupeSanguin == 'A+' ? 'selected' : ''}>A+</option>
                        <option value="B-" ${param.groupeSanguin == 'B-' ? 'selected' : ''}>B-</option>
                        <option value="B+" ${param.groupeSanguin == 'B+' ? 'selected' : ''}>B+</option>
                        <option value="AB-" ${param.groupeSanguin == 'AB-' ? 'selected' : ''}>AB-</option>
                        <option value="AB+" ${param.groupeSanguin == 'AB+' ? 'selected' : ''}>AB+</option>
                    </select>
                </div>

                <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">Statut</label>
                    <select name="statut" class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-red-500 focus:border-transparent">
                        <option value="">Tous les statuts</option>
                        <option value="DISPONIBLE" ${param.statut == 'DISPONIBLE' ? 'selected' : ''}>Disponible</option>
                        <option value="NON_DISPONIBLE" ${param.statut == 'NON_DISPONIBLE' ? 'selected' : ''}>Non Disponible</option>
                        <option value="NON_ELIGIBLE" ${param.statut == 'NON_ELIGIBLE' ? 'selected' : ''}>Non Eligible</option>
                    </select>
                </div>

                <div class="flex items-end gap-2">
                    <button type="submit" class="flex-1 bg-red-600 text-white px-4 py-2 rounded-lg hover:bg-red-700 transition font-semibold">
                        Filtrer
                    </button>
                    <a href="donneurs" class="bg-gray-200 text-gray-700 px-4 py-2 rounded-lg hover:bg-gray-300 transition font-semibold">
                        Reset
                    </a>
                </div>
            </form>
        </div>--!>

        <!-- Success/Error Messages -->
       <c:if test="${not empty sessionScope.successMessage}">
           <div class="bg-green-100 border-l-4 border-green-500 text-green-700 p-4 mb-6 rounded">
               <p class="font-semibold">&#9989; ${sessionScope.successMessage}</p>
           </div>
           <c:remove var="successMessage" scope="session"/>
       </c:if>

       <c:if test="${not empty sessionScope.errorMessage}">
           <div class="bg-red-100 border-l-4 border-red-500 text-red-700 p-4 mb-6 rounded">
               <p class="font-semibold">&#10060; ${sessionScope.errorMessage}</p>
           </div>
           <c:remove var="errorMessage" scope="session"/>
       </c:if>


        <!-- Donneurs Table -->
        <div class="bg-white rounded-lg shadow overflow-hidden">
            <div class="overflow-x-auto">
                <table class="min-w-full divide-y divide-gray-200">
                    <thead class="bg-gray-50">
                        <tr>
                            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">ID</th>
                            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Nom Complet</th>
                            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">CIN</th>
                            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Groupe Sanguin</th>
                            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Age/Poids</th>
                            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Telephone</th>
                            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Statut</th>
                            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Receveur Associe</th>
                            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Actions</th>
                        </tr>
                    </thead>
                    <tbody class="bg-white divide-y divide-gray-200">
                        <c:choose>
                            <c:when test="${empty donneurs}">
                                <tr>
                                    <td colspan="9" class="px-6 py-12 text-center text-gray-500">
                                        <div class="text-6xl mb-4">&#128533;</div>
                                        <p class="text-lg font-semibold">Aucun donneur trouve</p>
                                        <p class="text-sm mt-2">Ajoutez un nouveau donneur pour commencer</p>
                                    </td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="donneur" items="${donneurs}">
                                    <tr class="hover:bg-gray-50 transition">
                                        <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">
                                            #${donneur.id}
                                        </td>
                                        <td class="px-6 py-4 whitespace-nowrap">
                                            <div class="text-sm font-medium text-gray-900">${donneur.nom} ${donneur.prenom}</div>
                                            <div class="text-sm text-gray-500">${donneur.sexe}</div>
                                        </td>
                                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                                            ${donneur.cin}
                                        </td>
                                        <td class="px-6 py-4 whitespace-nowrap">
                                            <span class="px-3 py-1 inline-flex text-sm leading-5 font-semibold rounded-full bg-red-100 text-red-800">
                                                ${donneur.groupeSanguin}
                                            </span>
                                        </td>
                                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                                            <div>${donneur.age} ans</div>
                                            <div class="text-gray-500">${donneur.poids} kg</div>
                                        </td>
                                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                                            ${donneur.telephone}
                                        </td>
                                        <td class="px-6 py-4 whitespace-nowrap">
                                            <c:choose>
                                                <c:when test="${donneur.statut == 'DISPONIBLE'}">
                                                    <span class="px-3 py-1 inline-flex text-xs leading-5 font-semibold rounded-full bg-green-100 text-green-800">
                                                        &#9989; Disponible
                                                    </span>
                                                </c:when>
                                                <c:when test="${donneur.statut == 'NON_DISPONIBLE'}">
                                                    <span class="px-3 py-1 inline-flex text-xs leading-5 font-semibold rounded-full bg-orange-100 text-orange-800">
                                                        &#9888; Non Disponible
                                                    </span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="px-3 py-1 inline-flex text-xs leading-5 font-semibold rounded-full bg-red-100 text-red-800">
                                                        &#10060; Non Eligible
                                                    </span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td class="px-6 py-4 whitespace-nowrap text-sm">
                                            <c:choose>
                                                <c:when test="${not empty donneur.receveur}">
                                                    <a href="receveurs?id=${donneur.receveur.id}" class="text-blue-600 hover:text-blue-800 font-medium">
                                                        ${donneur.receveur.nom} ${donneur.receveur.prenom}
                                                    </a>
                                                    <div class="text-xs text-gray-500">${donneur.receveur.groupeSanguin}</div>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="text-gray-400">Aucun</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td class="px-6 py-4 whitespace-nowrap text-sm font-medium">
                                            <div class="flex gap-2">

                                              <button type="button"
                                                onclick="openEditModal(${donneur.id}, '${donneur.nom}', '${donneur.prenom}', '${donneur.cin}', '${donneur.telephone}', '${donneur.groupeSanguin}', ${donneur.poids}, '${donneur.statut}')"
                                                class="text-blue-600 hover:text-blue-900 bg-blue-50 px-3 py-1 rounded transition text-center">
                                                Modifier
                                              </button>

                                                <button onclick="confirmDelete(${donneur.id}, '${donneur.nom} ${donneur.prenom}')"
                                                        class="text-red-600 hover:text-red-900 bg-red-50 px-3 py-1 rounded transition">
                                                    Supprimer
                                                </button>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- Pagination -->
        <c:if test="${totalPages > 1}">
            <div class="flex justify-center mt-8">
                <nav class="flex gap-2">
                    <c:if test="${currentPage > 1}">
                        <a href="donneurs?page=${currentPage - 1}&search=${param.search}&groupeSanguin=${param.groupeSanguin}&statut=${param.statut}"
                           class="px-4 py-2 bg-white border border-gray-300 rounded-lg hover:bg-gray-50 transition">
                            Precedent
                        </a>
                    </c:if>

                    <c:forEach var="i" begin="1" end="${totalPages}">
                        <a href="donneurs?page=${i}&search=${param.search}&groupeSanguin=${param.groupeSanguin}&statut=${param.statut}"
                           class="px-4 py-2 ${currentPage == i ? 'bg-red-600 text-white' : 'bg-white border border-gray-300 hover:bg-gray-50'} rounded-lg transition">
                            ${i}
                        </a>
                    </c:forEach>

                    <c:if test="${currentPage < totalPages}">
                        <a href="donneurs?page=${currentPage + 1}&search=${param.search}&groupeSanguin=${param.groupeSanguin}&statut=${param.statut}"
                           class="px-4 py-2 bg-white border border-gray-300 rounded-lg hover:bg-gray-50 transition">
                            Suivant
                        </a>
                    </c:if>
                </nav>
            </div>
        </c:if>
    </div>

    <!-- Delete Confirmation Modal -->
    <div id="deleteModal" class="hidden fixed inset-0 bg-gray-600 bg-opacity-50 overflow-y-auto h-full w-full z-50">
        <div class="relative top-20 mx-auto p-5 border w-96 shadow-lg rounded-lg bg-white">
            <div class="mt-3 text-center">
                <div class="mx-auto flex items-center justify-center h-12 w-12 rounded-full bg-red-100">
                    <span class="text-3xl">&#9888;</span>
                </div>
                <h3 class="text-lg leading-6 font-medium text-gray-900 mt-4">Confirmer la suppression</h3>
                <div class="mt-2 px-7 py-3">
                    <p class="text-sm text-gray-500">
                        Etes-vous sur de vouloir supprimer le donneur <strong id="donneurName"></strong> ?
                        Cette action est irreversible.
                    </p>
                </div>
                <div class="flex gap-4 px-4 py-3">
                    <button onclick="closeDeleteModal()"
                            class="flex-1 px-4 py-2 bg-gray-200 text-gray-800 rounded-lg hover:bg-gray-300 transition font-semibold">
                        Annuler
                    </button>
                    <form id="deleteForm" action="Donneur/delete"  method="POST" class="flex-1">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" id="deleteId">
                        <button type="submit"
                                class="w-full px-4 py-2 bg-red-600 text-white rounded-lg hover:bg-red-700 transition font-semibold">
                            Supprimer
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>

     <!-- Edit Donneur Modal -->
     <div id="editModal" class="hidden fixed inset-0 bg-gray-600 bg-opacity-50 overflow-y-auto h-full w-full z-50">
         <div class="relative top-20 mx-auto p-6 border w-96 shadow-lg rounded-lg bg-white">
             <h3 class="text-lg font-semibold text-gray-900 mb-4 text-center">Modifier le Donneur</h3>

             <form id="editForm" action="donneur/update" method="POST" class="space-y-4">
                 <input type="hidden" name="id" id="editId">

                 <div>
                     <label class="block text-sm font-medium text-gray-700">Nom</label>
                     <input type="text" name="nom" id="editNom"
                            class="w-full border-gray-300 rounded-lg px-3 py-2 focus:ring-2 focus:ring-red-500">
                 </div>

                 <div>
                     <label class="block text-sm font-medium text-gray-700">Prenom</label>
                     <input type="text" name="prenom" id="editPrenom"
                            class="w-full border-gray-300 rounded-lg px-3 py-2 focus:ring-2 focus:ring-red-500">
                 </div>

                 <div>
                     <label class="block text-sm font-medium text-gray-700">CIN</label>
                     <input type="text" name="cin" id="editCin"
                            class="w-full border-gray-300 rounded-lg px-3 py-2 focus:ring-2 focus:ring-red-500">
                 </div>

                 <div>
                     <label class="block text-sm font-medium text-gray-700">Téléphone</label>
                     <input type="text" name="telephone" id="editTelephone"
                            class="w-full border-gray-300 rounded-lg px-3 py-2 focus:ring-2 focus:ring-red-500">
                 </div>

                 <div>
                     <label class="block text-sm font-medium text-gray-700">Poids (kg)</label>
                     <input type="number" name="poids" id="editPoids"
                            class="w-full border-gray-300 rounded-lg px-3 py-2 focus:ring-2 focus:ring-red-500">
                 </div>

                 <div>
                     <label class="block text-sm font-medium text-gray-700">Groupe Sanguin</label>
                     <select name="groupeSanguin" id="editGroupeSanguin"
                             class="w-full border-gray-300 rounded-lg px-3 py-2 focus:ring-2 focus:ring-red-500">
                         <option value="O-">O-</option>
                         <option value="O+">O+</option>
                         <option value="A-">A-</option>
                         <option value="A+">A+</option>
                         <option value="B-">B-</option>
                         <option value="B+">B+</option>
                         <option value="AB-">AB-</option>
                         <option value="AB+">AB+</option>
                     </select>
                 </div>

                 <div>
                     <label class="block text-sm font-medium text-gray-700">Statut</label>
                     <select name="statut" id="editStatut"
                             class="w-full border-gray-300 rounded-lg px-3 py-2 focus:ring-2 focus:ring-red-500">
                         <option value="DISPONIBLE">Disponible</option>
                         <option value="NON_DISPONIBLE">Non Disponible</option>
                         <option value="NON_ELIGIBLE">Non Eligible</option>
                     </select>
                 </div>

                 <div class="flex gap-4 mt-4">
                     <button type="button" onclick="closeEditModal()"
                             class="flex-1 bg-gray-200 text-gray-800 px-4 py-2 rounded-lg hover:bg-gray-300 transition font-semibold">
                         Annuler
                     </button>

                     <button type="submit"
                             class="flex-1 bg-red-600 text-white px-4 py-2 rounded-lg hover:bg-red-700 transition font-semibold">
                         Enregistrer
                     </button>
                 </div>
             </form>
         </div>
     </div>


    <script>
        function confirmDelete(id, name) {
            document.getElementById('deleteId').value = id;
            document.getElementById('donneurName').textContent = name;
            document.getElementById('deleteModal').classList.remove('hidden');
        }

        function closeDeleteModal() {
            document.getElementById('deleteModal').classList.add('hidden');
        }

        // Close modal on outside click
        document.getElementById('deleteModal').addEventListener('click', function(e) {
            if (e.target === this) {
                closeDeleteModal();
            }
        });
function openEditModal(id, nom, prenom, cin, telephone, groupeSanguin, poids, statut) {
        document.getElementById('editId').value = id;
        document.getElementById('editNom').value = nom;
        document.getElementById('editPrenom').value = prenom;
        document.getElementById('editCin').value = cin;
        document.getElementById('editTelephone').value = telephone;
        document.getElementById('editGroupeSanguin').value = groupeSanguin;
        document.getElementById('editPoids').value = poids;
        document.getElementById('editStatut').value = statut;
        document.getElementById('editModal').classList.remove('hidden');
    }

    function closeEditModal() {
        document.getElementById('editModal').classList.add('hidden');
    }

    document.getElementById('editModal').addEventListener('click', function(e) {
        if (e.target === this) closeEditModal();
    });
    </script>

</body>
</html>
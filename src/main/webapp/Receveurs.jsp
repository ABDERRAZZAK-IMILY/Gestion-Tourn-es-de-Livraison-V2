<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des Receveurs - Banque de Sang</title>
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
                    <li><a href="${pageContext.request.contextPath}/donneurs" class="hover:underline transition">Donneurs</a></li>
                    <li><a href="${pageContext.request.contextPath}/receveurs" class="font-bold underline">Receveurs</a></li>
                    <li><a href="creation.jsp" class="hover:underline transition">Ajouter</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <div class="container mx-auto px-4 py-8">
        <!-- Page Header -->
        <div class="flex justify-between items-center mb-8">
            <div>
                <h2 class="text-4xl font-bold text-gray-800">Liste des Receveurs</h2>
                <p class="text-gray-600 mt-2">Gestion et suivi des receveurs - Tri automatique par priorite</p>
            </div>
            <a href="creation.jsp" class="bg-red-600 text-white px-6 py-3 rounded-lg hover:bg-red-700 transition font-semibold shadow-lg">
                + Nouveau Receveur
            </a>
        </div>

        <!-- Statistics Cards -->
        <div class="grid grid-cols-1 md:grid-cols-5 gap-6 mb-8">
            <div class="bg-white rounded-lg shadow p-6">
                <div class="flex items-center justify-between">
                    <div>
                        <p class="text-gray-500 text-sm font-medium">Total Receveurs</p>
                        <p class="text-3xl font-bold text-gray-800 mt-1">${totalReceveurs}</p>
                    </div>
                    <div class="bg-blue-100 p-3 rounded-full">
                        <span class="text-2xl">&#128100;</span>
                    </div>
                </div>
            </div>

            <div class="bg-white rounded-lg shadow p-6">
                <div class="flex items-center justify-between">
                    <div>
                        <p class="text-gray-500 text-sm font-medium">CRITIQUE</p>
                        <p class="text-3xl font-bold text-red-600 mt-1">${receveursCritique}</p>
                    </div>
                    <div class="bg-red-100 p-3 rounded-full">
                        <span class="text-2xl">&#128165;</span>
                    </div>
                </div>
            </div>

            <div class="bg-white rounded-lg shadow p-6">
                <div class="flex items-center justify-between">
                    <div>
                        <p class="text-gray-500 text-sm font-medium">URGENT</p>
                        <p class="text-3xl font-bold text-orange-600 mt-1">${receveursUrgent}</p>
                    </div>
                    <div class="bg-orange-100 p-3 rounded-full">
                        <span class="text-2xl">&#9888;</span>
                    </div>
                </div>
            </div>

            <div class="bg-white rounded-lg shadow p-6">
                <div class="flex items-center justify-between">
                    <div>
                        <p class="text-gray-500 text-sm font-medium">NORMAL</p>
                        <p class="text-3xl font-bold text-green-600 mt-1">${receveursNormal}</p>
                    </div>
                    <div class="bg-green-100 p-3 rounded-full">
                        <span class="text-2xl">&#128994;</span>
                    </div>
                </div>
            </div>

            <div class="bg-white rounded-lg shadow p-6">
                <div class="flex items-center justify-between">
                    <div>
                        <p class="text-gray-500 text-sm font-medium">SATISFAIT</p>
                        <p class="text-3xl font-bold text-blue-600 mt-1">${receveursSatisfait}</p>
                    </div>
                    <div class="bg-blue-100 p-3 rounded-full">
                        <span class="text-2xl">&#9989;</span>
                    </div>
                </div>
            </div>
        </div>

        <!-- Filters and Search -->
         <!-- <div class="bg-white rounded-lg shadow p-6 mb-6">
            <form action="receveurs" method="GET" class="grid grid-cols-1 md:grid-cols-4 gap-4">
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
                    <label class="block text-sm font-medium text-gray-700 mb-2">Urgence</label>
                    <select name="urgence" class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-red-500 focus:border-transparent">
                        <option value="">Toutes les urgences</option>
                        <option value="CRITIQUE" ${param.urgence == 'CRITIQUE' ? 'selected' : ''}>CRITIQUE</option>
                        <option value="URGENT" ${param.urgence == 'URGENT' ? 'selected' : ''}>URGENT</option>
                        <option value="NORMAL" ${param.urgence == 'NORMAL' ? 'selected' : ''}>NORMAL</option>
                    </select>
                </div>

                <div class="flex items-end gap-2">
                    <button type="submit" class="flex-1 bg-red-600 text-white px-4 py-2 rounded-lg hover:bg-red-700 transition font-semibold">
                        Filtrer
                    </button>
                    <a href="receveurs" class="bg-gray-200 text-gray-700 px-4 py-2 rounded-lg hover:bg-gray-300 transition font-semibold">
                        Reset
                    </a>
                </div>
            </form>
        </div> -->

        <!-- Success/Error Messages -->
       <c:if test="${not empty sessionScope.successMessage}">
           <div class="bg-green-100 border-l-4 border-green-500 text-green-700 p-4 mb-6 rounded">
               <p class="font-semibold">&#9989; ${sessionScope.successMessage}</p>
           </div>
           <c:remove var="successMessage" scope="session"/>
       </c:if>


        <c:if test="${not empty errorMessage}">
            <div class="bg-red-100 border-l-4 border-red-500 text-red-700 p-4 mb-6 rounded">
                <p class="font-semibold">&#10060; ${errorMessage}</p>
            </div>
        </c:if>

        <!-- Receveurs Table -->
        <div class="bg-white rounded-lg shadow overflow-hidden">
            <div class="overflow-x-auto">
                <table class="min-w-full divide-y divide-gray-200">
                    <thead class="bg-gray-50">
                        <tr>
                            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">ID</th>
                            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Nom Complet</th>
                            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">CIN</th>
                            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Groupe Sanguin</th>
                            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Telephone</th>
                            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Urgence</th>
                            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Etat</th>
                            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Donneurs (Poches)</th>
                            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Actions</th>
                        </tr>
                    </thead>
                    <tbody class="bg-white divide-y divide-gray-200">
                        <c:choose>
                            <c:when test="${empty receveurs}">
                                <tr>
                                    <td colspan="10" class="px-6 py-12 text-center text-gray-500">
                                        <div class="text-6xl mb-4">&#128533;</div>
                                        <p class="text-lg font-semibold">Aucun receveur trouve</p>
                                        <p class="text-sm mt-2">Ajoutez un nouveau receveur pour commencer</p>
                                    </td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="receveur" items="${receveurs}">
                                    <tr class="hover:bg-gray-50 transition">
                                        <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">
                                            #${receveur.id}
                                        </td>
                                        <td class="px-6 py-4 whitespace-nowrap">
                                            <div class="text-sm font-medium text-gray-900">${receveur.nom} ${receveur.prenom}</div>
                                            <div class="text-sm text-gray-500">${receveur.sexe}</div>
                                        </td>
                                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                                            ${receveur.cin}
                                        </td>
                                        <td class="px-6 py-4 whitespace-nowrap">
                                            <span class="px-3 py-1 inline-flex text-sm leading-5 font-semibold rounded-full bg-red-100 text-red-800">
                                                ${receveur.groupeSanguin}
                                            </span>
                                        </td>

                                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                                            ${receveur.telephone}
                                        </td>
                                        <td class="px-6 py-4 whitespace-nowrap">
                                            <c:choose>
                                                <c:when test="${receveur.urgence == 'CRITIQUE'}">
                                                    <span class="px-3 py-1 inline-flex text-xs leading-5 font-semibold rounded-full bg-red-100 text-red-800">
                                                        &#128165; CRITIQUE (4)
                                                    </span>
                                                </c:when>
                                                <c:when test="${receveur.urgence == 'URGENT'}">
                                                    <span class="px-3 py-1 inline-flex text-xs leading-5 font-semibold rounded-full bg-orange-100 text-orange-800">
                                                        &#9888; URGENT (3)
                                                    </span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="px-3 py-1 inline-flex text-xs leading-5 font-semibold rounded-full bg-green-100 text-green-800">
                                                        &#128994; NORMAL (1)
                                                    </span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td class="px-6 py-4 whitespace-nowrap">
                                            <c:choose>
                                                <c:when test="${receveur.etat == 'SATISFAIT'}">
                                                    <span class="px-3 py-1 inline-flex text-xs leading-5 font-semibold rounded-full bg-blue-100 text-blue-800">
                                                        &#9989; SATISFAIT
                                                    </span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="px-3 py-1 inline-flex text-xs leading-5 font-semibold rounded-full bg-yellow-100 text-yellow-800">
                                                        &#8987; EN_ATTENTE
                                                    </span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td class="px-6 py-4">
                                            <div class="text-sm">
                                                <c:choose>
                                                    <c:when test="${not empty receveur.donneurs}">
                                                        <div class="font-medium text-gray-900 mb-1">
                                                            ${receveur.donneurs.size()}/${receveur.pochesNecessaires} poches
                                                        </div>
                                                        <div class="w-full bg-gray-200 rounded-full h-2 mb-2">
                                                            <div class="bg-blue-600 h-2 rounded-full" style="width: ${(receveur.donneurs.size() * 100) / receveur.pochesNecessaires}%"></div>
                                                        </div>

                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="text-gray-400">0/${receveur.pochesNecessaires} poches</span>
                                                        <div class="text-xs text-red-600 mt-1">Aucun donneur</div>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                        </td>
                                        <td class="px-6 py-4 whitespace-nowrap text-sm font-medium">
                                            <div class="flex flex-col gap-2">
                                                <!-- Bouton Voir Donneurs Compatibles -->
                                                <a href="compatibleDonors?receveurId=${receveur.id}"
                                                   class="text-green-600 hover:text-green-900 bg-green-50 px-3 py-1 rounded transition text-center">
                                                    Voir Donneurs
                                                </a>

                                              <button href="#"
                                                 onclick="openEditModal(${receveur.id}, '${receveur.nom}', '${receveur.prenom}', '${receveur.cin}', '${receveur.telephone}', '${receveur.groupeSanguin}', '${receveur.urgence}' ,'${receveur.dateNaissance}')"
                                                 class="text-blue-600 hover:text-blue-900 bg-blue-50 px-3 py-1 rounded transition text-center">
                                                 Modifier
                                              </button>


                                                <button onclick="confirmDelete(${receveur.id}, '${receveur.nom} ${receveur.prenom}')"
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

        <!-- Priority Legend -->
        <div class="mt-6 bg-blue-50 border-l-4 border-blue-500 p-4 rounded">
            <div class="flex items-start">
                <div class="text-2xl mr-3">&#128161;</div>
                <div>
                    <h4 class="font-semibold text-blue-900 mb-2">Information sur le tri</h4>
                    <p class="text-sm text-blue-800">
                        Les receveurs sont automatiquement tries par ordre de priorite decroissant :
                        <strong>CRITIQUE</strong> (4 poches) → <strong>URGENT</strong> (3 poches) → <strong>NORMAL</strong> (1 poche)
                    </p>
                </div>
            </div>
        </div>

        <!-- Pagination -->
        <c:if test="${totalPages > 1}">
            <div class="flex justify-center mt-8">
                <nav class="flex gap-2">
                    <c:if test="${currentPage > 1}">
                        <a href="receveurs?page=${currentPage - 1}&search=${param.search}&groupeSanguin=${param.groupeSanguin}&urgence=${param.urgence}"
                           class="px-4 py-2 bg-white border border-gray-300 rounded-lg hover:bg-gray-50 transition">
                            Precedent
                        </a>
                    </c:if>

                    <c:forEach var="i" begin="1" end="${totalPages}">
                        <a href="receveurs?page=${i}&search=${param.search}&groupeSanguin=${param.groupeSanguin}&urgence=${param.urgence}"
                           class="px-4 py-2 ${currentPage == i ? 'bg-red-600 text-white' : 'bg-white border border-gray-300 hover:bg-gray-50'} rounded-lg transition">
                            ${i}
                        </a>
                    </c:forEach>

                    <c:if test="${currentPage < totalPages}">
                        <a href="receveurs?page=${currentPage + 1}&search=${param.search}&groupeSanguin=${param.groupeSanguin}&urgence=${param.urgence}"
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
                        Etes-vous sur de vouloir supprimer le receveur <strong id="receveurName"></strong> ?
                        Cette action est irreversible.
                    </p>
                </div>
                <div class="flex gap-4 px-4 py-3">
                    <button onclick="closeDeleteModal()"
                            class="flex-1 px-4 py-2 bg-gray-200 text-gray-800 rounded-lg hover:bg-gray-300 transition font-semibold">
                        Annuler
                    </button>
                    <form id="deleteForm"  action="receveurs/delete" method="POST" class="flex-1">
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

    <!-- Edit Receveur Modal -->
    <div id="editModal" class="hidden fixed inset-0 bg-gray-600 bg-opacity-50 overflow-y-auto h-full w-full z-50">
        <div class="relative top-20 mx-auto p-6 border w-96 shadow-lg rounded-lg bg-white">
            <h3 class="text-lg font-semibold text-gray-900 mb-4 text-center">Modifier le Receveur</h3>

            <form id="editForm" action="${pageContext.request.contextPath}/receveur/update" method="POST" class="space-y-4">
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
                    <label class="block text-sm font-medium text-gray-700">Date de naissance</label>
                    <input type="date" name="dateNaissance" id="editDateNaissance"
                           class="w-full border-gray-300 rounded-lg px-3 py-2 focus:ring-2 focus:ring-red-500">
                </div>


                <div>
                    <label class="block text-sm font-medium text-gray-700">Téléphone</label>
                    <input type="text" name="telephone" id="editTelephone"
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
                    <label class="block text-sm font-medium text-gray-700">Urgence</label>
                    <select name="urgence" id="editUrgence"
                            class="w-full border-gray-300 rounded-lg px-3 py-2 focus:ring-2 focus:ring-red-500">
                        <option value="CRITIQUE">CRITIQUE</option>
                        <option value="URGENT">URGENT</option>
                        <option value="NORMAL">NORMAL</option>
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
            document.getElementById('receveurName').textContent = name;
            document.getElementById('deleteModal').classList.remove('hidden');
        }

        function closeDeleteModal() {
            document.getElementById('deleteModal').classList.add('hidden');
        }

        document.getElementById('deleteModal').addEventListener('click', function(e) {
            if (e.target === this) {
                closeDeleteModal();
            }
        });

      function openEditModal(id, nom, prenom, cin, telephone, groupeSanguin, urgence, etat, dateNaissance) {
          document.getElementById('editId').value = id;
          document.getElementById('editNom').value = nom;
          document.getElementById('editPrenom').value = prenom;
          document.getElementById('editCin').value = cin;
          document.getElementById('editTelephone').value = telephone;
          document.getElementById('editGroupeSanguin').value = groupeSanguin;
          document.getElementById('editUrgence').value = urgence;
          document.getElementById('editDateNaissance').value = dateNaissance;
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
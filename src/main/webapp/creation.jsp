<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajouter Donneur/Receveur - Banque de Sang</title>
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
                    <li><a href="${pageContext.request.contextPath}/receveurs" class="hover:underline transition">Receveurs</a></li>
                    <li><a href="creation.jsp" class="font-bold underline">Ajouter</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <div class="container mx-auto px-4 py-8">
        <!-- Page Header -->
        <div class="text-center mb-8">
            <h2 class="text-4xl font-bold text-gray-800">Ajouter un Donneur ou Receveur</h2>
            <p class="text-gray-600 mt-2">Remplissez le formulaire correspondant avec les informations requises</p>
        </div>

        <!-- Tab Navigation -->
        <div class="flex justify-center mb-8">
            <div class="inline-flex rounded-lg shadow-sm bg-white p-1">
                <button onclick="showTab('donneur')" id="tabDonneur"
                        class="px-8 py-3 rounded-lg font-semibold transition bg-red-600 text-white">
                    &#128137; Donneur
                </button>
                <button onclick="showTab('receveur')" id="tabReceveur"
                        class="px-8 py-3 rounded-lg font-semibold transition text-gray-700 hover:bg-gray-100">
                    &#127973; Receveur
                </button>
            </div>
        </div>

        <!-- Success/Error Messages -->
        <c:if test="${not empty successMessage}">
            <div class="max-w-4xl mx-auto mb-6 bg-green-100 border-l-4 border-green-500 text-green-700 p-4 rounded">
                <p class="font-semibold">&#9989; ${successMessage}</p>
            </div>
        </c:if>

        <c:if test="${not empty errorMessage}">
            <div class="max-w-4xl mx-auto mb-6 bg-red-100 border-l-4 border-red-500 text-red-700 p-4 rounded">
                <p class="font-semibold">&#10060; ${errorMessage}</p>
            </div>
        </c:if>

        <!-- Donneur Form -->
        <div id="donneurForm" class="max-w-4xl mx-auto bg-white rounded-lg shadow-lg p-8">
            <h3 class="text-2xl font-bold text-gray-800 mb-6 flex items-center gap-2">
                <span class="text-3xl">&#128137;</span>
                Formulaire Donneur
            </h3>

            <form action="donneurs" method="POST" class="space-y-6">
                <input type="hidden" name="action" value="create">

                <!-- Informations Personnelles -->
                <div class="border-b pb-6">
                    <h4 class="text-lg font-semibold text-gray-700 mb-4">Informations Personnelles</h4>
                    <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                        <div>
                            <label class="block text-sm font-medium text-gray-700 mb-2">Nom *</label>
                            <input type="text" name="nom" required class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-red-500 focus:border-transparent">
                        </div>
                        <div>
                            <label class="block text-sm font-medium text-gray-700 mb-2">Prenom *</label>
                            <input type="text" name="prenom" required class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-red-500 focus:border-transparent">
                        </div>
                        <div>
                            <label class="block text-sm font-medium text-gray-700 mb-2">CIN *</label>
                            <input type="text" name="cin" required pattern="[A-Z]{1,2}[0-9]{5,6}" placeholder="Ex: AB123456" class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-red-500 focus:border-transparent">
                        </div>
                        <div>
                            <label class="block text-sm font-medium text-gray-700 mb-2">Date de Naissance *</label>
                            <input type="date" name="dateNaissance" required class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-red-500 focus:border-transparent">
                        </div>
                        <div>
                            <label class="block text-sm font-medium text-gray-700 mb-2">Sexe *</label>
                            <select name="sexe" required class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-red-500 focus:border-transparent">
                                <option value="">Selectionner...</option>
                                <option value="HOMME">Homme</option>
                                <option value="FEMME">Femme</option>
                            </select>
                        </div>
                        <div>
                            <label class="block text-sm font-medium text-gray-700 mb-2">Telephone *</label>
                            <input type="tel" name="telephone" required pattern="[0-9]{10}" placeholder="0612345678" class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-red-500 focus:border-transparent">
                        </div>
                    </div>
                </div>

                <!-- Informations Medicales -->
                <div class="border-b pb-6">
                    <h4 class="text-lg font-semibold text-gray-700 mb-4">Informations Medicales</h4>
                    <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                        <div>
                            <label class="block text-sm font-medium text-gray-700 mb-2">Groupe Sanguin *</label>
                            <select name="groupeSanguin" required class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-red-500 focus:border-transparent">
                                <option value="">Selectionner...</option>
                                <option>O-</option><option>O+</option>
                                <option>A-</option><option>A+</option>
                                <option>B-</option><option>B+</option>
                                <option>AB-</option><option>AB+</option>
                            </select>
                        </div>
                        <div>
                            <label class="block text-sm font-medium text-gray-700 mb-2">Poids (kg) *</label>
                            <input type="number" name="poids" required min="30" max="200" step="0.1" placeholder="Minimum 50 kg pour eligibilite" class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-red-500 focus:border-transparent">
                        </div>
                    </div>
                </div>

                <!-- Contre-indications -->
                <div>
                    <h4 class="text-lg font-semibold text-gray-700 mb-4">Contre-indications Medicales</h4>
                    <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
                        <label><input type="checkbox" name="hepatiteB" value="true"> Hépatite B</label>
                        <label><input type="checkbox" name="hepatiteC" value="true"> Hépatite C</label>
                        <label><input type="checkbox" name="vih" value="true"> VIH</label>
                        <label><input type="checkbox" name="diabete" value="true"> Diabète insulino-dépendant</label>
                        <label><input type="checkbox" name="grossesse" value="true"> Grossesse</label>
                        <label><input type="checkbox" name="allaitement" value="true"> Allaitement</label>
                    </div>
                </div>

                <!-- Buttons -->
                <div class="flex gap-4 pt-6">
                    <button type="submit" class="flex-1 bg-red-600 text-white px-6 py-3 rounded-lg hover:bg-red-700 transition font-semibold shadow-lg">Enregistrer le Donneur</button>
                    <button type="reset" class="px-6 py-3 bg-gray-200 text-gray-700 rounded-lg hover:bg-gray-300 transition font-semibold">Réinitialiser</button>
                </div>
            </form>
        </div>

        <!-- Receveur Form -->
        <div id="receveurForm" class="hidden max-w-4xl mx-auto bg-white rounded-lg shadow-lg p-8">
            <h3 class="text-2xl font-bold text-gray-800 mb-6 flex items-center gap-2">
                <span class="text-3xl">&#127973;</span>
                Formulaire Receveur
            </h3>

            <form action="receveurs" method="POST" class="space-y-6">
                <input type="hidden" name="action" value="create">
                <div class="border-b pb-6">
                    <h4 class="text-lg font-semibold text-gray-700 mb-4">Informations Personnelles</h4>
                    <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                        <div><label class="block text-sm font-medium text-gray-700 mb-2">Nom *</label><input type="text" name="nom" required class="w-full px-4 py-2 border rounded-lg"></div>
                        <div><label class="block text-sm font-medium text-gray-700 mb-2">Prenom *</label><input type="text" name="prenom" required class="w-full px-4 py-2 border rounded-lg"></div>
                        <div><label class="block text-sm font-medium text-gray-700 mb-2">CIN *</label><input type="text" name="cin" required pattern="[A-Z]{1,2}[0-9]{5,6}" class="w-full px-4 py-2 border rounded-lg"></div>
                        <div><label class="block text-sm font-medium text-gray-700 mb-2">Date de Naissance *</label><input type="date" name="dateNaissance" required class="w-full px-4 py-2 border rounded-lg"></div>
                        <div><label class="block text-sm font-medium text-gray-700 mb-2">Sexe *</label><select name="sexe" required class="w-full px-4 py-2 border rounded-lg"><option value="">Selectionner...</option><option value="HOMME">Homme</option><option value="FEMME">Femme</option></select></div>
                        <div><label class="block text-sm font-medium text-gray-700 mb-2">Téléphone *</label><input type="tel" name="telephone" required pattern="[0-9]{10}" class="w-full px-4 py-2 border rounded-lg"></div>
                    </div>
                </div>

                <div class="border-b pb-6">
                    <h4 class="text-lg font-semibold text-gray-700 mb-4">Informations Médicales</h4>
                    <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                        <div>
                            <label class="block text-sm font-medium text-gray-700 mb-2">Groupe Sanguin *</label>
                            <select name="groupeSanguin" required class="w-full px-4 py-2 border rounded-lg">
                                <option value="">Selectionner...</option>
                                <option>O-</option><option>O+</option>
                                <option>A-</option><option>A+</option>
                                <option>B-</option><option>B+</option>
                                <option>AB-</option><option>AB+</option>
                            </select>
                        </div>
                        <div>
                            <label class="block text-sm font-medium text-gray-700 mb-2">Niveau d'Urgence *</label>
                            <select name="urgence" required id="urgenceSelect" onchange="updatePochesInfo()" class="w-full px-4 py-2 border rounded-lg">
                                <option value="">Selectionner...</option>
                                <option value="CRITIQUE">CRITIQUE - 4 poches necessaires</option>
                                <option value="URGENT">URGENT - 3 poches necessaires</option>
                                <option value="NORMAL">NORMAL - 1 poche necessaire</option>
                            </select>
                        </div>
                    </div>
                    <div id="urgenceInfo" class="hidden mt-4 p-4 rounded-lg"></div>
                </div>

                <div class="flex gap-4 pt-6">
                    <button type="submit" class="flex-1 bg-red-600 text-white px-6 py-3 rounded-lg hover:bg-red-700 transition font-semibold shadow-lg">Enregistrer le Receveur</button>
                    <button type="reset" class="px-6 py-3 bg-gray-200 text-gray-700 rounded-lg hover:bg-gray-300 transition font-semibold">Réinitialiser</button>
                </div>
            </form>
        </div>
    </div>

    <script>
        function showTab(tab) {
            const d = document.getElementById('donneurForm');
            const r = document.getElementById('receveurForm');
            const td = document.getElementById('tabDonneur');
            const tr = document.getElementById('tabReceveur');
            if (tab === 'donneur') {
                d.classList.remove('hidden'); r.classList.add('hidden');
                td.classList.add('bg-red-600','text-white'); tr.classList.remove('bg-red-600','text-white');
            } else {
                r.classList.remove('hidden'); d.classList.add('hidden');
                tr.classList.add('bg-red-600','text-white'); td.classList.remove('bg-red-600','text-white');
            }
        }

        function updatePochesInfo() {
            const sel = document.getElementById('urgenceSelect');
            const info = document.getElementById('urgenceInfo');
            const val = sel.value;
            info.classList.remove('hidden');
            if (val === 'CRITIQUE') info.innerHTML = '<div class="bg-red-50 border-l-4 border-red-500 p-4">💥 CRITIQUE - 4 poches nécessaires</div>';
            else if (val === 'URGENT') info.innerHTML = '<div class="bg-orange-50 border-l-4 border-orange-500 p-4">⚠️ URGENT - 3 poches nécessaires</div>';
            else if (val === 'NORMAL') info.innerHTML = '<div class="bg-green-50 border-l-4 border-green-500 p-4">🟢 NORMAL - 1 poche nécessaire</div>';
            else info.classList.add('hidden');
        }
    </script>
</body>
</html>
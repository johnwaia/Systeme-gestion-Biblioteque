## README pour le package db
## Getting Started

Bienvenue dans le package db. Ce package contient des classes pour interagir avec une base de données MySQL (bibliotheque) à travers des opérations CRUD et de recherche.
Folder Structure

La structure du package db est la suivante :

    DatabaseConnection.java: Cette classe gère la connexion à la base de données MySQL et fournit des méthodes pour exécuter des requêtes SQL.
    Diverses méthodes (inscrire, searchLivre, searchRevue, searchVideo, supprimerLivre, supprimerRevue, supprimerVideo) dans DatabaseConnection pour interagir avec les tables de la base de données.

Dependency Management

Ce package utilise le pilote JDBC MySQL pour établir la connexion à la base de données. Assurez-vous d'avoir configuré correctement le pilote JDBC dans votre projet.
Utilisation des classes

    DatabaseConnection.java: Cette classe contient des méthodes pour inscrire un nouveau client dans la base de données (inscrire), rechercher des livres (searchLivre), des revues (searchRevue) et des vidéos (searchVideo) par titre et auteur, ainsi que des méthodes pour supprimer des enregistrements (supprimerLivre, supprimerRevue, supprimerVideo).
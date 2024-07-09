## README pour le package documents
## Getting Started

Bienvenue dans le package documents. Ce package contient des classes et interfaces pour la gestion de différents types de documents tels que Livres, Revues et Vidéos.
Folder Structure

La structure du package documents est la suivante :

    Document.java: Classe abstraite définissant les méthodes nécessaires pour gérer les informations de base d'un document.
    DocumentBuilder.java: Interface définissant les méthodes pour construire un document de manière modulaire.
    DocumentFactory.java: Interface définissant les méthodes pour créer des requêtes SQL et définir les paramètres pour insérer des documents dans la base de données.
    Livre.java, Revue.java, Video.java: Classes concrètes qui étendent Document pour implémenter les spécificités de chaque type de document.
    LivreBuilder.java, RevueBuilder.java, VideoBuilder.java: Implémentations de DocumentBuilder pour construire des objets Livre, Revue et Video.
    LivreFactory.java, RevueFactory.java, VideoFactory.java: Implémentations de DocumentFactory pour générer des requêtes SQL spécifiques à chaque type de document.

Dependency Management

Ce package utilise les dépendances suivantes :

    DatabaseConnection du package db pour établir une connexion à la base de données MySQL.
    JDBC pour interagir avec la base de données.

Assurez-vous que votre projet inclut les dépendances appropriées et que la configuration de la base de données est correctement définie.
Utilisation des classes

    Document.java: Classe abstraite qui définit les méthodes nécessaires pour manipuler les informations de base d'un document.
    DocumentBuilder.java: Interface qui permet de construire un document de manière modulaire en utilisant des méthodes de construction.
    DocumentFactory.java: Interface qui définit les méthodes pour générer des requêtes SQL spécifiques à chaque type de document et pour définir les paramètres nécessaires à l'insertion dans la base de données.
    Livre.java, Revue.java, Video.java: Classes concrètes représentant respectivement un Livre, une Revue et une Vidéo, implémentant les méthodes abstraites de Document pour fournir les détails spécifiques à chaque type de document.
    LivreBuilder.java, RevueBuilder.java, VideoBuilder.java: Implémentations de DocumentBuilder pour construire des instances de Livre, Revue et Video en utilisant les données fournies.
    LivreFactory.java, RevueFactory.java, VideoFactory.java: Implémentations de DocumentFactory pour générer les requêtes SQL nécessaires à l'insertion de Livre, Revue et Video dans la base de données.
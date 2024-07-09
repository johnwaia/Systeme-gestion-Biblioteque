## README pour le package auth
Getting Started

Bienvenue dans le package auth. Ce package contient des classes permettant la gestion de l'authentification des utilisateurs.
Folder Structure

La structure du package auth est la suivante :

    Client.java: Cette classe définit un utilisateur avec ses attributs comme id, nom, motDePasse et isAdmin.
    ClientAuthentication.java: Cette classe fournit des méthodes pour authentifier un utilisateur à partir de la base de données.
    PasswordUtils.java: Cette classe contient des utilitaires pour chiffrer et déchiffrer des mots de passe.

Dependency Management

Ce package ne nécessite aucune dépendance externe spécifique autre que les bibliothèques standard de Java.
Utilisation des classes

    Client.java: Cette classe représente un utilisateur du système. Elle peut être instanciée avec un nom et un mot de passe. Elle offre des méthodes pour vérifier la validité du mot de passe et récupérer des informations sur l'utilisateur.

    ClientAuthentication.java: Cette classe fournit une méthode statique verifierConnexion qui permet de vérifier les informations d'identification d'un utilisateur dans la base de données. Elle utilise PasswordUtils pour déchiffrer et vérifier les mots de passe stockés de manière sécurisée.

    PasswordUtils.java: Cette classe contient des méthodes statiques pour chiffrer et déchiffrer des mots de passe en utilisant l'algorithme AES. Elle utilise une clé secrète et un vecteur d'initialisation (IV) pour sécuriser les opérations de chiffrement.
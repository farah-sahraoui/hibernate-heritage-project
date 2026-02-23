**Implémentation des stratégies d’héritage avec Hibernate**


Ce projet met en œuvre les trois stratégies d’héritage proposées par JPA/Hibernate : SINGLE_TABLE, JOINED et TABLE_PER_CLASS.
Chaque stratégie est appliquée à un modèle distinct afin d’observer concrètement la structure des tables générées automatiquement par Hibernate.


## 1-Génération automatique des tables et des relations par Hibernate:

![WhatsApp Image 2026-02-23 at 21 49 00](https://github.com/user-attachments/assets/34de8ffd-bd45-41fe-b4fc-bb447fba98f0)


![WhatsApp Image 2026-02-23 at 21 49 32](https://github.com/user-attachments/assets/d45e9154-b38d-4d7c-ad54-8b402bb86bbe)

![WhatsApp Image 2026-02-23 at 21 49 32](https://github.com/user-attachments/assets/eaa85962-ab55-4a54-a6cb-66d48b1962aa)


Lors du démarrage de l’application, Hibernate génère automatiquement les tables de la base de données à partir des entités définies dans le projet.
Les captures d’écran montrent les instructions SQL (DDL) produites, notamment la création des tables correspondant aux trois stratégies d’héritage implémentées.


## 2-Insertion et consultation des données – Stratégie SINGLE_TABLE:

![WhatsApp Image 2026-02-23 at 21 51 47](https://github.com/user-attachments/assets/a51dc2f9-67fc-444e-9fc6-b73907841b28)

![WhatsApp Image 2026-02-23 at 21 52 18](https://github.com/user-attachments/assets/c739e37a-c902-4faf-aa5f-3e94e42f214b)


Cette étape illustre l’exécution de la stratégie SINGLE_TABLE au moment du test de l’application. Les captures montrent d’abord les requêtes INSERT générées automatiquement
par Hibernate lors de l’enregistrement des objets Voiture et Moto dans la table unique catalogue_vehicules. On observe que toutes les données sont insérées dans une seule table,
avec une colonne categorie permettant de distinguer les types d’entités (par exemple AUTO et BIKE).
La seconde capture présente la requête SELECT exécutée pour récupérer l’ensemble des véhicules enregistrés. Hibernate interroge la même table unique et retourne les données
correspondant aux différentes sous-classes. L’affichage console confirme que les objets sont correctement persistés puis reconstruits avec leurs attributs spécifiques (portes, climatisation, énergie, etc.).




## 3-Affichage des entités persistées – Résultat de la requête polymorphique:

![WhatsApp Image 2026-02-23 at 21 52 52](https://github.com/user-attachments/assets/5217def7-4df3-41a0-9e60-21141b0b40aa)


Cette capture montre le résultat de la requête de récupération exécutée après l’insertion des véhicules dans la table catalogue_vehicules.
Hibernate reconstruit correctement les objets à partir des données stockées et affiche leurs attributs en fonction de leur type réel.
On observe l’affichage d’une seconde voiture (BMW Série 3) ainsi qu’une moto (Yamaha MT-07). Chaque entité conserve ses caractéristiques spécifiques : 
nombre de portes et climatisation pour la voiture, cylindrée et type de transmission pour la moto.



## 4-Insertion des entités – Stratégie JOINED:

![WhatsApp Image 2026-02-23 at 21 53 12](https://github.com/user-attachments/assets/a92971ff-d2d2-4cee-a6ca-72c7b5548328)

![WhatsApp Image 2026-02-23 at 21 53 36](https://github.com/user-attachments/assets/18484ca9-c47a-4992-ac5b-d7042b55aa44)



Les captures montrent que, lors de l’enregistrement du personnel, Hibernate exécute plusieurs requêtes INSERT.
Une première insertion est réalisée dans la table principale personnel pour les attributs communs, puis une seconde 
dans la table spécifique (equipe_developpement ou direction) selon le type d’entité.Cela confirme que la stratégie JOINED 
répartit les données entre la table mère et les tables filles reliées par la même clé primaire.


## 5-Consultation des données – Stratégie JOINED:

![WhatsApp Image 2026-02-23 at 21 54 10](https://github.com/user-attachments/assets/70d5b949-9ba4-4a25-8fc4-67f2fe58e580)

![WhatsApp Image 2026-02-23 at 21 54 39](https://github.com/user-attachments/assets/0970cd41-b399-4cb2-b14d-2ef8733361a1)

![WhatsApp Image 2026-02-23 at 21 55 19](https://github.com/user-attachments/assets/4f3f60ca-90f4-4d97-9aef-a190fde78c62)




Les captures montrent la requête SELECT générée par Hibernate pour récupérer l’ensemble du personnel. On observe 
l’utilisation de LEFT OUTER JOIN entre la table principale personnel et les tables spécialisées equipe_developpement et direction.
Cela permet à Hibernate de reconstruire correctement les objets selon leur type réel (développeur ou manager).
L’affichage console confirme que chaque employé est restitué avec ses informations communes (nom, email, date d’embauche) ainsi que 
ses attributs spécifiques (technologie et expérience pour les développeurs, ou données liées à la direction).

Cette étape illustre le fonctionnement de la stratégie JOINED, où la lecture des données nécessite des jointures entre la table mère et les tables filles.


## 6-Insertion des données – Stratégie TABLE_PER_CLASS:

![WhatsApp Image 2026-02-23 at 21 55 48](https://github.com/user-attachments/assets/4575eb6b-3f3f-4e29-99f0-69a58eed82fa)

![WhatsApp Image 2026-02-23 at 21 56 14](https://github.com/user-attachments/assets/73b93806-ce3d-4791-a04b-7105fb4441ca)


Les captures montrent les requêtes exécutées lors de l’enregistrement des produits du catalogue. On observe d’abord l’appel à la séquence 
hibernate_sequence pour la génération des identifiants, puis des requêtes INSERT distinctes dans les tables catalogue_livres et catalogue_electronique.
Contrairement aux autres stratégies, chaque classe concrète possède ici sa propre table contenant l’ensemble de ses attributs, y compris ceux hérités de la classe mère. 
Les données ne sont donc pas réparties entre plusieurs tables liées, mais stockées séparément selon le type de produit.Cela illustre le fonctionnement de la stratégie TABLE_PER_CLASS,
où chaque sous-classe est mappée vers une table indépendante.


## 7-Consultation des données – Stratégie TABLE_PER_CLASS:

![WhatsApp Image 2026-02-23 at 21 56 51](https://github.com/user-attachments/assets/80255346-bcf5-424f-af3d-70575bcf08cb)


![WhatsApp Image 2026-02-23 at 21 57 23](https://github.com/user-attachments/assets/a3c1bceb-0370-4c9f-807d-5da4318f561a)


![WhatsApp Image 2026-02-23 at 21 57 42](https://github.com/user-attachments/assets/9ea3f218-fe88-4470-8ba5-06e752219dd8)

Les captures montrent la requête générée par Hibernate pour récupérer l’ensemble des produits. On observe que la requête utilise un UNION ALL
entre les tables catalogue_livres et catalogue_electronique.Hibernate sélectionne les colonnes des deux tables en complétant avec des valeurs null lorsque 
certains attributs ne concernent pas une classe donnée.Cela permet de reconstruire les objets correspondant aux différentes sous-classes tout en effectuant
une seule requête globale sur l’entité mère.

## 8-Affichage des Produits et Gestion du Schéma Hibernate:

![WhatsApp Image 2026-02-23 at 21 58 09](https://github.com/user-attachments/assets/ceac439c-ecdb-4c4d-a53b-20a231e621e6)


![WhatsApp Image 2026-02-23 at 21 58 33](https://github.com/user-attachments/assets/d9f26e11-6a04-4bcf-b692-0c193d8f7479)


Cette étape montre l’exécution de l’application avec l’affichage des différents produits enregistrés dans le catalogue. Les informations générales 
(identifiant, nom, prix, description, date d’ajout) ainsi que les attributs spécifiques à chaque type de produit sont correctement affichées, 
ce qui confirme le bon fonctionnement de l’héritage et du polymorphisme dans le modèle. La dernière capture présente les commandes générées automatiquement par Hibernate
pour la suppression et la recréation des tables, validant ainsi la configuration correcte du mapping et la synchronisation entre les entités Java et la base de données.


## 9-Modélisation UML des Stratégies d’Héritage : SINGLE_TABLE, JOINED et TABLE_PER_CLASS:

## 1-Stratégie SINGLE_TABLE – Hiérarchie Vehicule:

Cette hiérarchie illustre la stratégie d’héritage SINGLE_TABLE, où toutes les classes (Vehicule, Voiture, Moto) sont stockées
dans une seule table en base de données. La classe Vehicule contient les attributs communs, tandis que Voiture et Moto ajoutent
leurs caractéristiques spécifiques. Cette stratégie optimise les performances en évitant les jointures, mais peut générer des colonnes nulles pour les attributs non utilisés selon le type.


![WhatsApp Image 2026-02-23 at 23 42 37](https://github.com/user-attachments/assets/b06d14c4-672a-434b-8e2d-e3f815b4f4c9)


## 2-Stratégie JOINED – Hiérarchie Employe:

Ce diagramme représente la stratégie JOINED, dans laquelle chaque classe possède sa propre table. La table Employe 
contient les attributs communs, tandis que Developpeur et Manager disposent de tables séparées contenant leurs attributs spécifiques.
Les tables sont liées par une clé primaire commune. Cette approche améliore la normalisation des données, mais nécessite des jointures lors des requêtes.


![WhatsApp Image 2026-02-23 at 23 39 40](https://github.com/user-attachments/assets/cb52ce4b-686a-4e15-92d8-89274bb27773)


## 3-Stratégie TABLE_PER_CLASS – Hiérarchie Produit:

Cette hiérarchie applique la stratégie TABLE_PER_CLASS, où chaque classe concrète (Livre, Electronique) possède sa propre table
contenant à la fois les attributs hérités de Produit et ses attributs spécifiques. La classe Produit est abstraite et ne génère pas
de table directe. Cette stratégie évite les colonnes inutiles mais peut rendre certaines requêtes plus complexes.


<img width="878" height="492" alt="image" src="https://github.com/user-attachments/assets/a00d450b-24c2-4629-8924-5e335fd3b963" />



## Conclusion:

Ce projet a permis de comprendre les différentes stratégies d’héritage en JPA/Hibernate : SINGLE_TABLE, JOINED et TABLE_PER_CLASS. 
Bien que la hiérarchie des classes reste identique en UML, chaque stratégie influence différemment la structure des tables en base de données.











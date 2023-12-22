Pour compiler et exécuter le jeu, suivez les instructions fournies au début de ce ReadMe.
Aller sur le répertoire courant contenant le Main.

Exécuter sur le terminal la commande :
>> javac Main.java
puis :
>> java Main

Il vous suffit alors d'appuyer "entrée" pour jouer.

Dans ce "ReadMe", plusieurs points seront traités pour aider à une meilleur comprehension du  Projet. 

Tout d'abord, pour avoir une base solide et une bonne construction du jeu, nous avons décidé d'utiliser l'outil de création de jeux : "Twine". Dont le site web est : https://twinery.org/
Cet outil nous a permis de cree un "schéma", avec tous les liens possibles entre les noeuds.
Pour mieux comprendre et visualiser, il est possible de consulter notre schéma. Pour cela, cliquez sur le lien ci-dessus, puis cliquez sur "Use in your browser". Puis "bibliothèque"=>"import"=>"choisir fichier" et choississez le fichier "ProjetJava.twee". 
Il s'agit d'un schéma très complet avec l'histoire complete (i.e le script). 
Cet outil nous a permis d'avoir une meilleur organisation, ainsi qu'une construction solide du Projet. 

Ensuite, nous avons dû réfléchir à un système de combat. Il était important d'apporter une dimension interactive et captivante au jeu. Notre choix s'est porté sur un modèle de combat basé sur des choix, similaire à un "pierre-feuille-ciseaux", mais avec une approche unique.

Système de Combat :
Le joueur est confronté à des décisions cruciales pendant les phases de combat, telles que "Attaquer", "Brise-Garde" et "Defense". Chaque option a ses avantages et ses inconvénients, et le joueur doit choisir stratégiquement pour progresser dans l'histoire. Pour une expérience immersive, nous avons également intégré des éléments de hasard, ce qui rend chaque confrontation unique.Precisement ici, voici l'ordre de supériorité : Attaquer => Brise-Garde => Defense => ATtaque
"=>": signifie vaincre. 

Classes et Capacités :
Le jeu propose différentes classes de personnages, chacune avec ses propres capacités spéciales. Cela permet aux joueurs de personnaliser leur style de jeu et d'adapter leurs stratégies en fonction de leur classe. 

Systeme de difficulté et de vie: 
Pour offrir une expérience de jeu plus personnalisée, nous avons mis en place un système de difficulté qui influence directement la quantité de vie dont dispose le joueur. Il existe trois niveaux de difficulté : Facile, Moyen et Difficile. Plus le niveau de difficulté est élevé, moins le joueur aura de points de vie et plus le jeu deviens difficile a finir. Cela encourage une approche tactique et une prise de décision réfléchie lors des combats. Par exemple : 
=> Facile : Le niveau de difficulté facile garantit une expérience plus accessible, idéale pour ceux qui préfèrent se concentrer sur l'histoire sans se soucier d'un défi important. Les ennemis sont moins redoutables, et le joueur dispose de 5 points de vie.

Ce système assure une adaptation de la difficulté en fonction des préférences du joueur, garantissant une expérience engageante et stimulante pour tous les types de joueurs.

Concernant les tests: 
Comme aucun de nous deux n'utilise Eclips, il était difficile de vérifier si les testes marchaient bien. C'est une des difficultés que nous avons rencontré durant ce Projet.

Concernant la hierarchies de nos classes:
Nous avons encore une fois réalisé plusieurs schéma afin de faciliter la compréhension de notre Projet. Vous pouvez les retrouver sur "Schéma_hierarchie"

Voir plus de détail dans le fichier index.html (javadoc) :


Le jeu étant très difficle à finir, voici un chemin recommandé :

D:Oui
D:Epee
D:Se rapprocher et lui donner un coup.
D:GAUCHE
D:l'homme
D:Refuser et ne rien dire
D:Gauche
D:Verser du vin dans le calice.
D:Trouver un abri en se cachant dans la foret
D:Au dessus d'un arbre pour le surprendre
D:Le reveiller
D:Le boire
D:A droite
D:Lui montre la cire que vous avez
D:Lui donner la cire
D:Tuer Dedale
D:Attendre qu’il vienne pour contre-attaquer.
D:TOUT DROIT
D:Ne rien faire et continuer la route.
D:Essayer de la calmer
D:GAUCHE
D:Oui
D:Trahir Persée et l'utiliser comme Distraction
D:Accepter de prendre le fil
D:Tirer sur le fil magique
D:Esquiver
D:Tuer Thesee et sortir en utilisant le fil.


Voici le chemin le plus court : 
D:Oui
D:Arc
D:Essayer d'attaquer en prenant distance
D:Droite
D:Lui montrer la cire que vous avez
D:Poignarder et arracher les ailes
D:attaquer avant qu'il attaque 
D:Tout droit
D:La tuer tant qu'elle est attaché
D:toud droit
D:Accepter de prendre le file
D:Tirer sur le fil magique
D:Esquiver
D:Tuer Thesee et sortir en utilisant le fil


# Présentation du projet

Le projet consiste à recréer le jeu du fizz-buzz sous la forme d'une API REST.

## Règles du jeu

La règle de base consiste à énumérer des nombres tout en remplaçant les multiples de 3 par "fizz", les multiples de 5 par "buzz" et les multiples des 3 et 5 par "fizzbuzz"

Exemple : 1,2,fizz,4,buzz,fizz,7,8,fizz,buzz,11,fizz,13,14,fizzbuzz,16,etc.

# L'API

L'API FizzBuzz expose deux endpoints : 

- /game/fizzbuzz et qui prend en paramètre les données suivantes : 
  - multiple1 : correspond au premier multiple du jeu (ex: 3)
  - multiple2 : correspond au second multiple du jeu (ex: 5)
  - limit : correspond au dernier numéro à énumérer (ou à remplacer)
  - substitutionWordForMultiple1 : correspond au mot à remplacer si le numéro courant est multiple de 'multiple1'
  - substitutionWordForMultiple2 : correspond au mot à remplacer si le numéro courant est multiple de 'multiple2'

- /game/fizzbuzz/stats : permet d'avoir des statistiques sur la requête la plus exécutée

Un endpoint technique est aussi disponible pour savoir si l'application est UP and running :
- /actuator/health

# Comment lancer l'API

Plusieurs manières pour lancer l'API : 

- Soit par maven, en se plaçant dans le dossier du pom et en faisant : `mvn spring-boot:run`
- Soit par Docker en construisant l'image avec `mvn spring-boot:build-image` et l'exécuter avec `docker run -it -p8080:8080 fizz-buzz-game:0.0.1-SNAPSHOT`

Exemple d'appel : 

Ensuite on peut faire des requêtes sur http://localhost:8080/game/fizzbuzz?multiple1=3&multiple2=5&limit=100&substitutionWordForMultiple1=fizz&substitutionWordForMultiple2=buzz

# Swagger UI

Une fois que l'application est lancée, un swagger UI est disponible sous http://localhost:8080/swagger-ui.html

Il est possible de tester l'application grâce à cette interface 

# Améliorations possibles

Plusieurs améliorations sont possibles pour rendre le serveur de jeu plus robuste : 
- Persister les statistiques pour conserver les statistiques entre deux arrêts/relances
- Avoir une interface graphique pour utiliser l'API comme arbitre lorsqu'on joue entre amis
- Avoir une gestion plus fine des erreurs pour avoir des erreurs moins verbeuses lorsqu'on fait une mauvaise requête

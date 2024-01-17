# Projet de démonstration Spring Boot

Il s'agit d'un projet de démonstration Spring Boot illustrant l'utilisation de Spring Boot avec Spring Data JPA, Spring Web, et d'autres dépendances connexes. Le projet comprend deux contrôleurs pour la gestion des types de produits et des produits.

## Prérequis

Assurez-vous d'avoir les éléments suivants installés avant d'exécuter le projet :

- Java 17
- Maven
- **Docker**
- **Docker Compose**

### Développement en local (sans Docker)

1. **Cloner le dépôt :**

   ```bash
   git clone git@github.com:aliounekanoute/modelsis-backend-Alioune-KANOUTE.git
   cd modelsis-backend-Alioune-KANOUTE
   ```

2. **Construire le projet avec Maven :**

   ```bash
   mvn clean install
   ```

3. **Exécuter l'application :**

   ```bash
   mvn spring-boot:run
   ```

   L'application démarrera sur `http://localhost:8080`.

4. **Configuration d'une base de données PostgreSQL locale :**

   Mettez à jour le fichier `src/main/resources/application.yml` avec le contenu suivant :

   ```yaml
   server:
     error:
       include-message: always
     port: 8080

   spring:
     datasource:
       url: jdbc:postgresql://localhost:5432/db_demo
       username: postgres
       password: postgres
       initialize: true
     jpa:
       hibernate.ddl-auto: update
       show-sql: true
       properties:
         hibernate:
           format_sql: true
           dialect: org.hibernate.dialect.PostgreSQLDialect
     sql:
       init:
         mode: always
   ```

5. **Exécuter les tests en local :**

   ```bash
   mvn test
   ```

6. **Accéder à la documentation OpenAPI en local :**

   La documentation OpenAPI est disponible à l'adresse `http://localhost:8080/swagger-ui.html`.

### Développement avec Docker

1. **Exécuter l'application avec Docker Compose :**

   ```bash
   docker-compose up
   ```

   L'application Spring Boot sera accessible à `http://localhost:8080`. La base de données PostgreSQL sera disponible sur le port 5433.

2. **Accéder à la documentation OpenAPI dans l'environnement Docker :**

   La documentation OpenAPI est disponible à l'adresse `http://localhost:8080/swagger-ui.html`.

---

N'hésitez pas à personnaliser les fichiers Dockerfile, docker-compose.yml et application.yml selon vos besoins de projet. Profitez du développement et du déploiement de votre application Spring Boot !
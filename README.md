# Social Network Backend con Spring Boot

## Descrizione del Progetto

Questo progetto rappresenta il backend di un social network sviluppato utilizzando Spring Boot. Il social network combina le funzionalità di Twitter e Instagram, permettendo agli utenti di postare foto e immagini, commentare i post, mettere "like" ai post, seguire gli utenti, visualizzare i profili degli amici e molte altre funzioni tipiche dei social network. In futuro, verrà implementata anche la funzionalità di messaggistica.

## Funzionalità Principali

- **Gestione dei Post:** Creazione, recupero, aggiornamento e cancellazione di post contenenti foto e immagini.
- **Commenti sui Post:** Aggiunta, recupero e cancellazione di commenti sui post.
- **Like sui Post:** Possibilità di mettere "like" e rimuovere "like" dai post.
- **Gestione Utenti:** Registrazione, autenticazione, follow e unfollow degli utenti.
- **Profili Utenti:** Visualizzazione e aggiornamento dei profili utente.

## Funzionalità Future

- **Messaggistica:** Implementazione di una funzionalità di messaggistica per permettere agli utenti di inviare messaggi diretti.

## Requisiti

- Java 11 o superiore
- Maven

## Installazione

1. **Clonare il repository:**
   ```bash
   git clone <URL del repository>
   ```
2. **Navigare nella directory del progetto:**
   ```bash
   cd nome-del-progetto
   ```
3. **Compilare il progetto e scaricare le dipendenze:**
   ```bash
   mvn clean install
   ```
4. **Avviare l'applicazione:**
   ```bash
   mvn spring-boot:run
   ```

## Struttura del Progetto

- **src/main/java:** Contiene il codice sorgente dell'applicazione.
  - **controller:** Contiene i controller REST che gestiscono le richieste HTTP.
  - **service:** Contiene la logica di business.
  - **repository:** Contiene le interfacce di accesso ai dati (DAO/Repository).
  - **model:** Contiene le classi di dominio (entity).
- **src/main/resources:** Contiene le risorse dell'applicazione, come i file di configurazione.
- **src/test/java:** Contiene i test unitari e di integrazione.

## Endpoints Principali

### Utenti

- **Registrazione:** `POST /api/users/register`
- **Login:** `POST /api/users/login`
- **Follow:** `POST /api/users/{id}/follow`
- **Unfollow:** `POST /api/users/{id}/unfollow`
- **Visualizzazione Profilo:** `GET /api/users/{id}`

### Post

- **Creazione Post:** `POST /api/posts`
- **Recupero Post:** `GET /api/posts/{id}`
- **Aggiornamento Post:** `PUT /api/posts/{id}`
- **Cancellazione Post:** `DELETE /api/posts/{id}`

### Commenti

- **Aggiunta Commento:** `POST /api/posts/{postId}/comments`
- **Recupero Commenti:** `GET /api/posts/{postId}/comments`
- **Cancellazione Commento:** `DELETE /api/comments/{id}`

### Like

- **Mettere Like:** `POST /api/posts/{postId}/like`
- **Rimuovere Like:** `DELETE /api/posts/{postId}/like`

## Contribuire

Le contribuzioni sono benvenute! Per favore, segui questi passaggi:

1. **Fork del repository**
2. **Crea un nuovo branch:**
   ```bash
   git checkout -b feature/nome-della-feature
   ```
3. **Fai le modifiche necessarie e commit:**
   ```bash
   git commit -m 'Aggiunta una nuova funzionalità'
   ```
4. **Push al branch:**
   ```bash
   git push origin feature/nome-della-feature
   ```
5. **Crea una Pull Request**

## Contatti

Per qualsiasi domanda o suggerimento, sentiti libero di contattarmi all'indirizzo email: umbertociccia@icloud.com

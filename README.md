# language-school-DB
Sistema gestionale per una scuola di lingua inglese: Database realizzato in SQL e MYSQL, secondo il modello relazionale E-R e Thin-client in Java per il suo utilizzo  
## Architettura:
-Database relazionale costruito partendo dalla traccia del committente,individuando tutti gli elementi necessari come vari attori,operazioni,costo delle singole operazioni.

-impiego di stored procedure,secondo il rispetto delle regole ACID invocabili tramite il client java

-trigger

-indici secondari per velocizzare le query,identificazione delle varie tipologie di utenti mediante password.

-Client java realizzato secondo il pattern Model-View-Controller

-impiego delle DAO e jdbc per l'invocazione delle query verso il server

-impiego del pattern singleton per garantire sul singolo client l'utilizzo specifico di un unica connessione condivisa da tutte le classi.

DROP SCHEMA IF EXISTS `scuola_lingue`;
CREATE SCHEMA IF NOT EXISTS `scuola_lingue` ;
USE `scuola_lingue`;



DROP TABLE IF EXISTS `scuola_lingue`.`Utenti`;
CREATE TABLE IF NOT EXISTS `scuola_lingue`.`Utenti`(
   
   `Username` varchar(45)  NOT NULL, 
    `Password` varchar(45) NOT NULL,
	`ruolo` ENUM('amministratore','segreteria','insegnante') NOT NULL,
    PRIMARY KEY (`Username`))
    ENGINE = InnoDB;
     

DROP TABLE IF EXISTS `scuola_lingue`.`Livello`;

CREATE TABLE IF NOT EXISTS `scuola_lingue`.`Livello`(
 `Nome` varchar(20) primary key,
  `Libro` varchar(45) not null,
  `Esame` tinyint not null
  )
ENGINE = InnoDB;

DROP TABLE IF EXISTS `scuola_lingue`.`Corso`;

CREATE TABLE IF NOT EXISTS `scuola_lingue`.`Corso`(
   `Codice` INT AUTO_INCREMENT primary key,
   `Livello` varchar(20) not null,
   `Data_inizio` DATE not null,
   `Numero_inscritti` INT not null,
   UNIQUE KEY `unique_livello_codice` (`Livello`, `Codice`),
   foreign key(`Livello`) references `scuola_lingue`.`Livello`(Nome)
   ON DELETE RESTRICT
   ON UPDATE CASCADE
  )
  ENGINE = InnoDB;

DROP TABLE IF EXISTS `scuola_lingue`.`Insegnante`;

CREATE TABLE IF NOT EXISTS `scuola_lingue`.`Insegnante`(
  `Matricola` INT auto_increment primary key,
  `Nome` varchar(30) not null,
  `Nazione` varchar(30) not null,
  `Indirizzo` varchar(30) not null
)

ENGINE = InnoDB;

DROP TABLE IF EXISTS `scuola_lingue`.`Studente`;

CREATE TABLE IF NOT EXISTS `scuola_lingue`.`Studente`(
  `Matricola` INT auto_increment primary key,
  `Nome` varchar(30) not null,
  `Cognome` varchar(30) not null,
  `Telefono` varchar(40) not null
)

ENGINE = InnoDB;

DROP TABLE IF EXISTS `scuola_lingue`.`Lezione`;
  
  CREATE TABLE IF NOT EXISTS `scuola_lingue`.`Lezione`(
    `Id_lezione` INT auto_increment not null primary key,
    `Insegnante` INT not null,
    `Corso` INT not null,
    `orario_inizio` TIME not null,
    `orario_fine`   TIME not null,
    `giorno_settimana` DATE not null,
    foreign key (`Insegnante`) references `scuola_lingue`.`Insegnante`(Matricola)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
    foreign key (`Corso`) references `scuola_lingue`.`Corso`(Codice)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    )
    
    
    ENGINE = InnoDB;


DROP TABLE IF EXISTS `scuola_lingue`.`Registro`;

CREATE TABLE IF NOT EXISTS `scuola_lingue`.`Registro`(
  `Lezione` INT not null ,
  `Studente` INT not null ,
  primary key(`Lezione`,`Studente`),
  `Assenza` tinyint not null,
  foreign key(`Lezione`) references `scuola_lingue`.`Lezione`(`Id_lezione`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  foreign key(`Studente`) references `scuola_lingue`.`Studente`(`Matricola`)
  ON DELETE CASCADE
  ON UPDATE CASCADE
  )
  
  ENGINE = InnoDB;
  
  DROP TABLE IF EXISTS `scuola_lingue`.`Inscrizione`;
  
CREATE TABLE IF NOT EXISTS `scuola_lingue`.`Inscrizione`(
  `Corso` INT not null ,
  `Studente` INT not null ,
  `Data_inscrizione` DATE not null,
  primary key(`Corso`,`Studente`,`Data_inscrizione`),
  `n°_assenze` INT not null default 0,
  foreign key(`Corso`) references `scuola_lingue`.`Corso`(`Codice`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  foreign key(`Studente`) references `scuola_lingue`.`Studente`(`Matricola`)
  ON DELETE CASCADE
  ON UPDATE CASCADE
 )
  ENGINE = InnoDB;
  
DROP TABLE IF EXISTS `scuola_lingue`.`Assegnazione`;
    
CREATE TABLE IF NOT EXISTS `scuola_lingue`.`Assegnazione`(
  `Insegnante` INT not null ,
  `Corso` INT not null ,
  primary key(`Insegnante`,`Corso`),
  foreign key (`Insegnante`) references `scuola_lingue`.`Insegnante`(`Matricola`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  foreign key(`Corso`) references `scuola_lingue`.`Corso`(`codice`)
  ON DELETE CASCADE
  ON UPDATE CASCADE
  )
  
  ENGINE = InnoDB;

DROP TABLE IF EXISTS `scuola_lingue`.`Log_assenze`;

CREATE TABLE IF NOT EXISTS `scuola_lingue`.`Log_assenze`(
   `Corso` INT NOT NULL,
   `Studente` INT NOT NULL,
   `numero_assenze` INT NOT NULL,
   `data_registrazione` DATE NOT NULL,
   PRIMARY KEY(`Corso`,`Studente`,`data_registrazione`))
   
   ENGINE = InnoDB;
  
  
DELIMITER ;
USE `scuola_lingue`;
CREATE INDEX idx_lezione_corso ON scuola_lingue.Lezione(Corso);
CREATE INDEX idx_lezione_insegnante ON scuola_lingue.Lezione(Insegnante);
CREATE INDEX idx_corso_livello ON scuola_lingue.Corso(Livello);
CREATE INDEX idx_inscrizione_studente_corso ON scuola_lingue.Inscrizione(Studente, Corso);
CREATE INDEX idx_assegnazione_insegnante ON scuola_lingue.Assegnazione(Insegnante);
CREATE INDEX idx_assegnazione_corso ON scuola_lingue.Assegnazione(Corso);

DELIMITER $$ 


$$
CREATE VIEW `Report_professori`(Matricola,ID_corso,Nome_corso,Lezione,inizio,fine,giorno_settimana) AS 
	SELECT `Matricola`,`Codice`,`Livello`,`ID_lezione`,`orario_inizio`,`orario_fine`,`giorno_settimana`
    FROM `scuola_lingue`.`Insegnante` I JOIN `scuola_lingue`.`Lezione` L ON I.Matricola=L.Insegnante JOIN Corso C on L.Corso=C.Codice
    order by `Matricola`;
$$    

$$
CREATE VIEW `Report_studenti`(`Matricola`,`Corso`,`Nome_corso`,`Assenze`) as 
	SELECT `Matricola`,`Codice`,`Livello`,`n°_assenze`
    FROM `scuola_lingue`.`Studente` as S JOIN `scuola_lingue`.`Inscrizione`as I ON S.`Matricola`= I.`Studente` JOIN `scuola_lingue`.`Corso` as C ON I.`Corso`= C.`Codice`
    order by `Matricola`;
$$  

$$
CREATE VIEW `Report`as 
    SELECT `Nome`,`Matricola`,`Codice`,`Livello`,COUNT(*) as N°_lezioni
    FROM `scuola_lingue`.`Insegnante` I JOIN `scuola_lingue`.`Assegnazione`A JOIN `scuola_lingue`.Corso C JOIN `scuola_lingue`.Lezione L 
    WHERE I.Matricola=A.Insegnante AND A.Corso = C.Codice AND C.Codice = L.Corso 
    GROUP BY `Nome`,`Matricola`,`Codice`,`Livello`;
$$ 

CREATE PROCEDURE `login` (in var_username varchar(45), in var_pass varchar(45), out var_role INT)
BEGIN
    declare var_user_role varchar(50);
    
    select `ruolo` from `scuola_lingue`.`Utenti`
        where `Username` = var_username
        and `Password` = md5(var_pass)
        into var_user_role;
        
    -- See the corresponding enum in the client
        IF var_user_role IS NULL then set var_role=5;
        ELSEIF var_user_role = 'amministratore' then
            set var_role = 1;
        ELSEIF var_user_role = 'segreteria' then
            set var_role = 2;
        ELSEIF var_user_role = 'insegnante' then
            set var_role = 3;
		else set var_role = 4;
        end if;
END$$









$$
CREATE PROCEDURE `Crea_corso`(in Livello_corso VARCHAR(20),in Data_inizio DATE)

BEGIN
   DECLARE EXIT HANDLER FOR SQLEXCEPTION
   BEGIN
     ROLLBACK;
     RESIGNAL;
   END;
   set transaction isolation level repeatable read;
   START TRANSACTION;
   IF NOT EXISTS(SELECT 1 FROM `scuola_lingue`.`Livello` WHERE `scuola_lingue`.`Livello`.`Nome`=Livello_corso)
	THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Il livello inserito non corrisponde con quelli esistenti';
   END IF;
   INSERT INTO `scuola_lingue`.`Corso`(`Livello`,`Data_inizio`,`Numero_inscritti`) values (Livello_corso,Data_inizio,0);
   commit;
   END
   
   $$
   
   $$
   CREATE PROCEDURE `Nuovo_livello`(IN Nome_livello varchar(20),IN Libro varchar(30),In Esame tinyint)
   
      BEGIN 
         SET TRANSACTION ISOLATION LEVEL read committed;
         START TRANSACTION;
         INSERT INTO `scuola_lingue`.`Livello`(`Nome`,`Libro`,`Esame`) values(Nome_livello,Libro,Esame);
         commit;
      END;
      $$
    
   $$
   CREATE PROCEDURE `Nuovo_studente`(IN Nome_studente varchar(30),IN Cognome_studente varchar(30),IN Telefono_studente varchar(30))
       BEGIN
         SET TRANSACTION ISOLATION LEVEL read committed ;
         START TRANSACTION;
         INSERT INTO `scuola_lingue`.`Studente`(`Nome`,`Cognome`,`Telefono`) values (Nome_studente,Cognome_studente,Telefono_studente);
         COMMIT;
	   END;
	$$
    
    $$
    CREATE PROCEDURE `Nuovo_insegnante`(IN Nome_insegnante varchar(30),IN Nazione_insegnante varchar(30),IN Indirizzo_insegnante varchar(30))
		BEGIN
		SET TRANSACTION ISOLATION LEVEL repeatable read;
        START TRANSACTION;
        INSERT INTO `scuola_lingue`.`Insegnante`(`Nome`,`Nazione`,`Indirizzo`) values (Nome_insegnante,Nazione_insegnante,Indirizzo_insegnante);
        COMMIT;
        END;
        $$
   
   
   $$
   CREATE PROCEDURE `Nuova_inscrizione`(IN Matricola_studente INT,IN ID_corso INT,IN Data_inscrizione DATE)
	BEGIN
		DECLARE EXIT HANDLER FOR SQLEXCEPTION
        BEGIN
          ROLLBACK;
          RESIGNAL;
		END;
    
    SET TRANSACTION ISOLATION LEVEL repeatable read;
    START TRANSACTION;
    IF NOT EXISTS(SELECT 1 FROM `scuola_lingue`.`Corso` WHERE `Codice`=ID_corso) THEN
       SIGNAL SQLSTATE '45001'
       SET MESSAGE_TEXT='Non esiste alcun corso corrispondente con il codice inserito';
	
    END IF;
    IF NOT EXISTS(SELECT 1 FROM `scuola_lingue`.`Studente` WHERE `Matricola`= Matricola_studente) THEN
      SIGNAL SQLSTATE '45001'
      SET MESSAGE_TEXT='Non esiste alcun studente corrispondente alla matricola inserita';
      
    END IF;
    
	INSERT INTO `scuola_lingue`.`Inscrizione`(`Corso`,`Studente`,`Data_inscrizione`) values (ID_corso,Matricola_studente,Data_inscrizione);
    COMMIT;
    END;
    $$
       
   
   $$
CREATE PROCEDURE `Nuova_lezione`(IN ID_corso INT, IN Mat_Ins INT, IN Inizio TIME,IN Fine TIME, IN Giorno DATE)
 BEGIN
   DECLARE EXIT HANDLER FOR SQLEXCEPTION
   BEGIN
     ROLLBACK;
     RESIGNAL;
   END;
   SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
   START TRANSACTION;
     IF NOT EXISTS (SELECT 1 FROM `scuola_lingue`.`Corso` WHERE `scuola_lingue`.`Corso`.`Codice`=Id_corso)
      THEN
      SIGNAL SQLSTATE '45001'
      SET MESSAGE_TEXT='codice di corso errato';
   
   END IF;
   IF NOT EXISTS(SELECT 1 FROM `scuola_lingue`.`Insegnante` WHERE `scuola_lingue`.`Insegnante`.`Matricola`=Mat_Ins)
   THEN
   SIGNAL SQLSTATE '45001'
   SET MESSAGE_TEXT= 'matricola professore errata';

   END IF;
   IF NOT EXISTS(SELECT 1 FROM `scuola_lingue`.`Assegnazione` WHERE `scuola_lingue`.`Assegnazione`.`Insegnante`= Mat_Ins AND (`scuola_lingue`.`Assegnazione`.`Corso`=ID_Corso)) THEN
     SIGNAL SQLSTATE '45001'
     SET MESSAGE_TEXT='l’insegnate specificato non è assegnato al corso inserito';
   END IF;
   INSERT INTO `scuola_lingue`.`Lezione`(`Corso`,`Insegnante`,`orario_inizio`,`orario_fine`,`giorno_settimana`)
	values(ID_corso,Mat_Ins,Inizio,Fine,Giorno);
	COMMIT;
END;
$$
 
$$
CREATE PROCEDURE `Assegnazione_insegnante`(IN ID_Insegnante INT, IN ID_corso INT)
BEGIN
   DECLARE EXIT HANDLER FOR SQLEXCEPTION
   BEGIN
     ROLLBACK;
     RESIGNAL;
   END;
   SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
   START TRANSACTION;
    IF not exists(SELECT 1 FROM `scuola_lingue`.`Corso` WHERE `scuola_lingue`.`Corso`.`Codice`= ID_corso)
    THEN SIGNAL SQLSTATE '45001'
    SET MESSAGE_TEXT='codice corso errato';
    
    END IF;
    IF not exists(SELECT 1 FROM `scuola_lingue`.`Insegnante` WHERE `scuola_lingue`.`Insegnante`.`Matricola`= ID_Insegnante)
    THEN SIGNAL SQLSTATE '45001'
    SET MESSAGE_TEXT='matricola insegnante errata';
    
    END IF;
    INSERT into `scuola_lingue`.`Assegnazione`(`Insegnante`,`Corso`)
      values(ID_Insegnante, ID_corso);
   COMMIT;
END;
$$


$$
CREATE PROCEDURE `Appello`(IN Lezione_ID INT,IN Matricola_studente INT,IN Stato TINYINT)
  BEGIN
     BEGIN
     DECLARE EXIT HANDLER FOR SQLEXCEPTION
       ROLLBACK;
       RESIGNAL;
	END;
    SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
    START TRANSACTION;
    IF NOT EXISTS(SELECT 1 FROM `scuola_lingue`.`Lezione` WHERE `ID_lezione`= Lezione_ID) THEN
    SIGNAL SQLSTATE '45001'
    SET MESSAGE_TEXT='Non esiste alcuna lezione corrispondente all id inserito';
    END IF;
    IF NOT EXISTS(SELECT 1 FROM `scuola_lingue`.`Studente` WHERE `Matricola`= Matricola_studente) THEN 
    SIGNAL SQLSTATE '45001'
    SET MESSAGE_TEXT='Non esiste alcun studente corrispondente alla matricola inserita';
    END IF;
    IF NOT EXISTS(SELECT 1 FROM `scuola_lingue`.`Lezione` as L JOIN `scuola_lingue`.`Inscrizione` as I on L.corso = I. Corso Where Lezione_ID = L.Id_lezione AND Matricola_studente = I.Studente) THEN
    SIGNAL SQLSTATE '45001'
    SET MESSAGE_TEXT='Lo studente inserito non è assegnato al corso per la seguente lezione';
    END IF;
   INSERT INTO `scuola_lingue`.`Registro`(`Lezione`,`Studente`,`Assenza`) values (Lezione_ID,Matricola_studente,Stato);
    COMMIT;
    END;
$$
    



$$
CREATE PROCEDURE `Agenda`(IN ID_Insegnante INT)

BEGIN
   SELECT *
   FROM `scuola_lingue`.`Report_professori` R
   WHERE R.Matricola=ID_Insegnante and WEEK(R.`giorno_settimana`)=WEEK(CURDATE())  AND YEAR(R.`giorno_settimana`)=YEAR(CURDATE())
order by DAYOFWEEK(R.`giorno_settimana`);

END;
$$

$$

CREATE PROCEDURE `Report_mensile`()

BEGIN
   SELECT *
   FROM `scuola_lingue`.`Report` R
   
order by R.Nome;
END;
$$


CREATE PROCEDURE `Attività_studenti`()
BEGIN
   SELECT *
   FROM `scuola_lingue`.`Report_studenti_corso`
   Order by Matricola_Studente;
END;
$$


CREATE TRIGGER `Incremento_assenze`
   AFTER INSERT ON `scuola_lingue`.`Registro`
   FOR EACH ROW 
   BEGIN
   DECLARE `Codice_corso` int;
   IF new.Assenza=1 THEN
	  SELECT `codice`INTO `Codice_corso`
      FROM `scuola_lingue`.`Registro` as R join `scuola_lingue`.`Lezione` as L ON R.`Lezione`=L.`ID_lezione` Join `scuola_lingue`.`Corso` as C ON L.`Corso`=C.`Codice`
      LIMIT 1;
      UPDATE `scuola_lingue`.`Inscrizione`
      SET `n°_assenze`=`n°_assenze`+1
      WHERE `Studente`=NEW.`Studente` and `Corso`= `Codice_corso`;
      IF (SELECT`n°_assenze` FROM `scuola_lingue`.`Inscrizione` WHERE `Studente`= NEW.`Studente`) > 10 THEN 
        SIGNAL SQLSTATE '45001'
        SET MESSAGE_TEXT='Numero massimo di assenze ragiunto';
        END IF;
    END IF;
    END;
    $$
	CREATE TRIGGER `Incremnento_inscrizioni`
		AFTER INSERT ON `scuola_lingue`.`Inscrizione`
        FOR EACH ROW 
        BEGIN 
        UPDATE `scuola_lingue`.`Corso`
        SET `Corso`.`Numero_inscritti`=`Numero_inscritti`+1
        WHERE `Codice`=NEW.`Corso`;
        END;
        
$$
CREATE TRIGGER `Assegnazione_insegnanti`
    BEFORE INSERT ON `scuola_lingue`.`Assegnazione`
    FOR EACH ROW
    BEGIN 
      DECLARE `numero_corsi` INT;
      
      SELECT COUNT(*) INTO `numero_corsi`
      FROM `scuola_lingue`.`Assegnazione`
      WHERE `Insegnante`=new.`Insegnante`;
      IF `numero_corsi` >= 2 THEN
      SIGNAL SQLSTATE '45001'
      SET MESSAGE_TEXT='Numero massimo corsi raggiunto per il seguente insegnante';
      END IF;
      END;
$$

$$
CREATE TRIGGER `Verifica_sovrapposizione`
  BEFORE INSERT ON `scuola_lingue`.`Lezione`
  FOR EACH ROW
  BEGIN
    IF EXISTS (SELECT 1 FROM Lezione WHERE `Corso`=new.`Corso` 
    and `Insegnante`=new.`Insegnante` 
    and `giorno_settimana`=new.`giorno_settimana`
    and (new.`orario_inizio` between `orario_inizio` and `orario_fine`
    or new.`orario_fine` between `orario_inizio` and `orario_fine`
    or (new.`orario_inizio`<=`orario_inizio` and new.`orario_fine`>=`orario_fine`))) THEN
    SIGNAL SQLSTATE '45001'
    SET MESSAGE_TEXT='IL professore specifico ha già una lezione per la specifica fascia oraria';
    END IF;
    END;
$$

$$    
CREATE TRIGGER `Verifica_inscrizioni`
  BEFORE INSERT ON `scuola_lingue`.`Inscrizione`
  FOR EACH ROW
  BEGIN 
    DECLARE num_inscritti int;
    SELECT COUNT(*) into num_inscritti
    FROM `scuola_lingue`.`Inscrizione`
    WHERE `Corso`=new.`Corso`;
    IF num_inscritti >=20 THEN
	SIGNAL SQLSTATE '45001'
    SET MESSAGE_TEXT='numero massimo inscritti raggiunto per lo specifico corso';
    END IF;
    END;
$$
    
$$
CREATE TRIGGER `Lezioni_doppie`
  BEFORE INSERT ON `scuola_lingue`.`Lezione`
  FOR EACH ROW
  BEGIN
    IF EXISTS(SELECT 1 FROM `scuola_lingue`.`Lezione` WHERE `Corso`=new.`Corso` and `giorno_settimana`=new.`giorno_settimana` and `orario_inizio`=new.`orario_inizio`) THEN
      SIGNAL SQLSTATE '45001'
      SET MESSAGE_TEXT='è gia presente una lezione per lo specifico corso e la specifica data e ora';
	END IF;
  END;
$$
  

  
DELIMITER ;

SET SQL_MODE = '';
DROP USER IF Exists 'login';
CREATE USER 'login' IDENTIFIED BY 'login';  
SET SQL_MODE = 'TRADITIONAL,ALLOW_INVALID_DATES'; 
GRANT EXECUTE ON procedure `scuola_lingue`.`login` TO 'login';



SET SQL_MODE = '';
DROP USER if exists 'amministratore';
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'amministratore' IDENTIFIED BY 'amministratore';
GRANT EXECUTE ON procedure `scuola_lingue`.`login` TO 'amministratore';
GRANT EXECUTE ON PROCEDURE `scuola_lingue`.`Nuovo_livello` to 'amministratore';
GRANT EXECUTE ON PROCEDURE `scuola_lingue`.`Crea_corso` to 'amministratore';
GRANT EXECUTE ON PROCEDURE `scuola_lingue`.`Nuovo_insegnante` to 'amministratore';
GRANT EXECUTE ON PROCEDURE `scuola_lingue`.`Nuova_lezione` to 'amministratore';
GRANT EXECUTE ON PROCEDURE `scuola_lingue`.`Report_mensile` to 'amministratore';
  



SET SQL_MODE='';
DROP USER IF EXISTS 'segreteria';
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'segreteria' IDENTIFIED BY 'segreteria';
GRANT EXECUTE ON PROCEDURE `scuola_lingue`.`login` TO 'segreteria';
GRANT EXECUTE ON PROCEDURE `scuola_lingue`.`Nuova_inscrizione` to 'segreteria';
GRANT EXECUTE ON PROCEDURE `scuola_lingue`.`Nuovo_studente` to 'segreteria';



SET SQL_MODE='';
DROP USER IF EXISTS 'insegnante';
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'insegnante' IDENTIFIED BY 'insegnante';
GRANT EXECUTE ON PROCEDURE `scuola_lingue`.`login` TO 'insegnante';
GRANT EXECUTE ON PROCEDURE `scuola_lingue`.`Agenda` TO 'insegnante';
GRANT EXECUTE ON PROCEDURE `scuola_lingue`.`Appello` TO 'insegnante';


DELIMITER $$
$$
CREATE EVENT `PuliziaIscrizioniObsolete`  
ON SCHEDULE EVERY 5 YEAR 
DO 
BEGIN 
DELETE FROM `scuola_lingue`.`Inscrizione` WHERE `Data_inscrizione` < DATE_SUB(CURDATE(), INTERVAL 5 YEAR);
END;
$$


$$
CREATE EVENT `Verifica_assenze_mensili` 
ON SCHEDULE EVERY 1 MONTH STARTS CURRENT_TIMESTAMP
DO
BEGIN 
   INSERT INTO `scuola_lingue`.`Log_assenze`(`Corso`,`Studente`,`data_registrazione`,`numero_assenze`) 
   SELECT `Corso`,`Studente`,CURDATE(),`n°_assenze`
   FROM `scuola_lingue`.`Inscrizione` 
   WHERE `n°_assenze` >=10; 
   END;
$$


  
$$  

DELIMITER ;

START TRANSACTION; 
INSERT INTO `scuola_lingue`.`Utenti`(`Username`,`Password`,`Ruolo`) VALUES ('l.bianchi','81dc9bdb52d04dc20036dbd8313ed055','amministratore');
INSERT INTO `scuola_lingue`.`Utenti`(`Username`,`Password`,`Ruolo`) VALUES ('m.rossi','81dc9bdb52d04dc20036dbd8313ed055','insegnante');
INSERT INTO `scuola_lingue`.`Utenti`(`Username`,`Password`,`Ruolo`) VALUES ('v.jordan','81dc9bdb52d04dc20036dbd8313ed055','segreteria');

COMMIT;



CALL Nuovo_livello('a1','english level a1',0);
CALL Nuovo_livello('a2','english level a2',1);
CALL Nuovo_livello('b1','english level b1',0);
CALL Nuovo_livello('b2','english level b2',0);
CALL Nuovo_livello('c1','english level c1',1);
CALL Nuovo_livello('c2','english level c2',1);
CALL Crea_corso('a1','2025-02-02');
CALL Crea_corso('a2','2025-02-02');
CALL Crea_corso('b1','2025-02-02');
CALL Crea_corso('b2','2025-02-02');
CALL Crea_corso('c1','2025-02-02');
CALL Crea_corso('c2','2025-02-02');
CALL Nuovo_studente('Mario','Rossi','0776');
CALL Nuovo_studente('fabio','venturi','0776');
CALL Nuovo_studente('lorenzo','gargano','0773');
CALL Nuovo_Insegnante('Mario','Italia','Via rossi');
CALL Nuovo_Insegnante('gianni','Italia','Via marini');
CALL Nuovo_Insegnante('alessio','Italia','Via eco');
CALL Nuovo_Insegnante('alessandro','Italia','Via napoleone');
CALL Nuovo_Insegnante('giovanni','Italia','Via settembrini');
CALL Nuovo_Insegnante('arturo','Italia','Via XX');
CALL Nuova_Inscrizione(1,1,'2025-02-02');
CALL Nuova_Inscrizione(2,1,'2025-02-03');

CALL Assegnazione_insegnante(1,1);
CALL Assegnazione_insegnante(2,1);
CALL Assegnazione_insegnante(3,2);
CALL Nuova_lezione(1,1,'09:30','10:30','2025-04-06');
CALL Nuova_lezione(2,2,'09:30','10:30','2025-04-06');




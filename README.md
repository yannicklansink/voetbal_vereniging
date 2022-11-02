# voetbal_vereniging
Spring boot backend eindproject

Het voetbalclubregistratie systeem lost het probleem op dat er geen papierwerk meer wordt gebruikt bij het bijhouden van de registratie van gegevens van een voetbalvereniging. Zo werd er in het verleden gebruik gemaakt van papieren schriften om speler gegevens bij te houden, op te zoeken bij welk team de speler was ingedeeld en wat voor wedstrijden er in de toekomst gepland staan. Al dit soort gegevens, en meer, zijn nu vervangen door een backend met gekoppelde database waar API request naartoe kunnen worden gestuurd om gegevens te creëren, updaten, deleten en op te vragen. Deze manier van werken voorkomt veel voorkomende handmatige fouten. 


## 1 Benodigdheden


### 1.1	 Besturingssysteem
De installatiehandleiding is geschreven voor een Windows besturingssysteem. Waarbij uit is gegaan van Windows 10 of hoger.

### 1.2	Localhost
Als computernetwerk wordt verwijst naar de locatie van het eigen systeem. Men zal naar poort 8080 moeten navigeren om daar met de applicatie te communiceren.

### 1.3	JDK
Het voetbalclubregistratie systeem is gebouwd in een Java omgeving. De JDK, Java Development Kit, is een distributie van de Java technologie. Door de JDK te installeren zal er ook een runtime environment gedownload worden waar de applicatie op kan draaien. Het dient als onderliggende technologie wat als communicatie middel wordt gebruikt tussen het Java programma en het besturingssysteem. Download de Java SE Development Kit 17. Een download link is gegeven in de bronvermelding. Voor Windows besturingssysteem wordt aangeraden in de eerste tabel de Windows x64 Installer te kiezen. 

### 1.4	Database
Om de applicatie te draaien moet er een onderliggende database aan gekoppeld worden. De database dat wordt gebruikt is PostgreSQL. Postgres is een relationele database management systeem. De download link is gegeven in de bronvermelding onder PostgreSQL download. Er wordt aangeraden de laatste versie van Windows x86 te kiezen. Tijdens de installatieproces is het belangrijk ook de server de downloaden waar de database op gaat draaien. In bijlage 2.1 lees je over de installatie instructies.

### 1.5	API Communicatie
Om de backend endpoints te gebruiken worden er API request gemaakt. Voor de API request wordt Postman gebruikt. De download link voor Postman is weergegeven in de bronvermelding. 

### 1.6	IDE
Voor de opdrachtgever is het van belang de code kwaliteit te kunnen beoordelen. Om dit te doen wordt er gebruik gemaakt van een Integrated Development Environment. De code is geschreven in IntelliJ. De download link is vindbaar in de bronvermelding. Download de laatste Ultimate versie.


## 2.	Installatie Instructies

### 2.1	StappenplanUitvoeren
1	Start pgAdmin op. 

2	 Kies een master wachtwoord. Het is aan te raden hetzelfde wachtwoord te kiezen dat tijdens de installatie van postgres is gebruikt. 
In bijlage2 is “postgres” als wachtwoord gebruikt. 

3	 Kies een wachtwoord voor de connectie met de server. Nogmaals, het is aan te raden het zelfde wachtwoord te gebruiken. 

4	 Dan zie je het volgende scherm. We gaan nu een database toevoegen.
5	 Klik op Object -> Create -> Database… Om op bovenstaand scherm te komen. Vervolgens vul je de database naam “voetbalvereniging” in. Bij een afwijkende naam zal er een extra stap in de application.properties file moeten worden opgenomen. 

6	De database server is nu succesvol opgestart. Start nu vervolgens jouw IntelliJ ultimate edition. 

7	Klik na het opstarten op file -> new -> project from existing sources. Er is hier vanuit gegaan dat men al eerder IntelliJ heeft opgestart. 

8	Selecteer de folder dat geïmporteerd dient te worden. De folder is aangeleverd in de zip file in het opgegeven product. 
9	Kies import project from external model en vervolgens op Maven. 

10	Het project zal worden ingeladen; wacht daarom even tot de IDE hiermee klaar is.

11 Open de applicatie.properties file (wat gevonden kan worden in voetbal_vereniging -> src -> main -> resources -> application.properties)
Verander hier zodoende het wachtwoord en gebruikersnaam dat je hebt gekozen. 
Als je aan de geadviseerde wachtwoorden hebt gehouden dan zal dat voor beide “postgres” zijn.
Ook de url, regel 2, moet overeen komen met de gekozen database naam.

12 Voer nu de applicatie uit door met rechtermuis knop op de FootbalClubApplication te klikken en vervolgens op “Run FootbalClubApplication”. 

 

# Dokumentaatio

### Kuvaus
MahtiUutiset on uutissivusto, jossa voi lukea ja luoda uutisia. 

### Käyttötapaukset

* Käyttäjä voi lukea uutisen
* Käyttäjä voi listata uutiset tietystä kategoriasta
* Käyttäjä voi listata kaikki uutiset
* Käyttäjä voi luoda uutisen
* Käyttäjä voi muokata uutista

### Tietokantakaavio
![Alt text](/Dokumentaatio/tietokantakaavio.png)

### Puuttuvat ominaisuudet ja parannusehdotuksia 
* Kuvaominaisuus poistettu (ei saatu toimimaan herokun puolella)
* Validointi ja virheviestit
* Kirjautuminen
  * Uutisten lisäys ja muokkaus olis hyvä olla mahdollista vain kirjautuneena
* Testit erittäin vähäisiä
  * Testikattavuutta pitäisi mahdollisessa jatkokehityksessä parantaa huomattavasti
  * Seleniumia ei saatu toimimaan
  * jacoco?
* 

### Asennus- ja käyttöohje
* Sovellus toimii osoitteessa http://mahtiuutiset.herokuapp.com
  * Sovelluksen voi myös kopioida GitHubista ja ajaa jar-tiedoston, minkä jälkeen sovellus toimii osoitteessa localhost:8080
* Sivusto on melko yksinkertainen, joten kummempia käyttöohjeita ei tarvita, mutta tässä silti joitain mainintoja:
  * Sivun ylälaidassa on navigaatiopalkki, missä on linkit etusivulle ja eri kategorioiden sekä kaikkien uutisten listaukseen
  * Yläpalkissa on myös linkki uutisen lisäykseen, josta avautuu lomake, johon täytetään uutisen sisältö
  * Uutisen voi avata klikkaamalla sitä
  * Uutisen sivulla on linkki kyseisen uutisen muokkaukseen

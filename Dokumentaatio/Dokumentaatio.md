# Dokumentaatio

### Kuvaus
MahtiUutiset on uutissivusto, jossa voi lukea, listata ja luoda uutisia. 

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
* Kirjautuminen ja uutisen poistamismahdollisuus
  * Uutisten lisäys, muokkaus ja poisto olisi hyvä olla mahdollista vain kirjautuneena
* Testit erittäin vähäisiä
  * Testikattavuutta pitäisi mahdollisessa jatkokehityksessä parantaa huomattavasti
  * Seleniumia ei saatu toimimaan

### Asennus- ja käyttöohje
* Sovellus toimii osoitteessa http://mahtiuutiset.herokuapp.com
  * Sovelluksen voi myös kopioida GitHubista, minkä jälkeen se toimii osoitteessa http://localhost:8080
* Sivusto on melko yksinkertainen, joten kummempia käyttöohjeita ei tarvita, mutta tässä silti joitain mainintoja:
  * Sivun ylälaidassa on navigaatiopalkki, missä on linkit etusivulle ja eri kategorioiden sekä kaikkien uutisten listaukseen
  * Yläpalkissa on myös linkki uutisen lisäykseen, josta avautuu lomake, johon täytetään uutisen sisältö
  * Uutisen voi avata klikkaamalla sitä
  * Uutisen sivulla uutisen lopussa on linkki kyseisen uutisen muokkaukseen

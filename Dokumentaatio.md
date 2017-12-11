# Dokumentaatio

### Kuvaus

### Käyttötapaukset

* Käyttäjä voi lukea uutisen
* Käyttäjä voi listata uutiset tietystä kateoriasta
* Käyttäjä voi listata kaikki uutiset
* Käyttäjä voi luoda uutisen
* Käyttäjä voi muokata uutista

### Tietokantakaavio


### Puuttuvat ominaisuudet ja parannusehdotuksia 
* Kirjautuminen
  * Uutisten lisäys ja muokkaus olis hyvä olla mahdollista vain kirjautuneena
* Kuvaominaisuus poistettu (ei saatu toimimaan herokun puolella)
* Validointi
* Testit hyvin suppeita
  * seleniumia ei saatu toimimaan
  * jacoco?

### Asennus- ja käyttöohje
* Sovellus toimii osoitteessa http://mahtiuutiset.herokuapp.com
  * Sovelluksen voi myös kopioida GitHubista ja ajaa jar-tiedoston, minkä jälkeen sovellus toimii osoitteessa localhost:8080
* Sivusto on melko yksinkertainen, joten kummempia käyttöohjeita ei tarvita
  * Eri kategorioihin, etusivulle ja kaikkien uutisten listaukseen on linkit ylälaidassa
  * Linkki uutisen lisäykseen on myös yläpalkissa
  * Uutisen voi avata klikkaamalla sitä
  * Uutisen sivulla on linkki kyseisen uutisen muokkaukseen

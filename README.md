# Projekt grupy Drużyna Pierścienia
Projekt na przedmiot Projektowanie i Programowanie Aplikacji Biznesowych.

## Jak uruchomić serwer?
Najpierw co najmniej raz zbuduj projekt za pomocą komendy:
```bash
mvn clean package
```
To polecenie wygeneruje w katalogu `web/target` paczkę WAR 
niezbędną do zbudowania kontenera.
Następnie zbuduj i uruchom kontener za pomocą poniższych poleceń:
```bash
sudo docker build . -t sos-atins
sudo docker run -p 8080:8080 sos-atins
```
## Testowy punkt końcowy
```bash
GET http://localhost:8080/test
```

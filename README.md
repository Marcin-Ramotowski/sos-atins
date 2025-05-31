# Projekt grupy Drużyna Pierścienia
Projekt na przedmiot Projektowanie i Programowanie Aplikacji Biznesowych.

## Jak uruchomić serwer?
```bash
sudo docker build . -t sos-atins
sudo docker run -p 8080:8080 sos-atins
```
## Testowy punkt końcowy
```bash
GET http://localhost:8080/test
```
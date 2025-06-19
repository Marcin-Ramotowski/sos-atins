# Projekt grupy Drużyna Pierścienia
Projekt na przedmiot Projektowanie i Programowanie Aplikacji Biznesowych.

## Jak uruchomić serwer?
Najpierw co najmniej raz zbuduj projekt za pomocą komendy:
```bash
mvn clean package
```
To polecenie wygeneruje w katalogu `web/target` paczkę WAR 
niezbędną do zbudowania kontenera aplikacji.

Następnie zbuduj i uruchom kontenery całego systemu:
```bash
 sudo docker compose up
```

## Testowy punkt końcowy
```bash
GET http://localhost:8080/test
```

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
 sudo docker compose up --build
```

## Testowe punkty końcowe
```bash
GET http://localhost:8080/test
```

### Books
```bash
# Wszystkie książki
curl -X GET -H 'Accept: application/json' http://localhost:8080/books

# Jedna książka
curl -X GET -H 'Accept: application/json' http://localhost:8080/books/202
```

### Loans
```bash
# Wszystkie książki pożyczone przez studenta
curl -X GET -H 'Accept: application/json' http://localhost:8080/students/1/loans
```

### Payments
```bash
# Wszystkie płatności
curl -X GET -H 'Accept: application/json' http://localhost:8080/payments

# Wszystkie płatności studenta
curl -X GET -H 'Accept: application/json' http://localhost:8080/students/1/payments
```

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
### Zewnętrzne API
API, które aplikacja wystawia dla zewnętrznych użytkowników (np. frontendu).

### Students
```bash
# Stwórz studenta
curl -X POST http://localhost:8080/students -H 'Content-Type: application/json' -d '{"admin":false,"active":true,"mfaEnabled":false,"birthDate":[1999,10,10],"email":"user@uni.edu","firstName":"User","lastName":"Userowicz","login":"user","agreementNum":123,"avgScore":5.0,"titleOfGrade":"BACHELOR","currentSemester":3,"graduationDate":[2026,6,30],"enrollmentYear":[2022,10,1],"enrollSemester":2,"modeOfStudy":"FULL_TIME","scholarshipHolder":true,"specialization":"COMPUTER_GRAPHICS","studentNumber":20234,"password":"","department":{"id":0}}'

# Pokaż studenta
curl -X GET http://localhost:8080/students/2

# Uaktualnij dane studenta
curl -X PUT http://localhost:8080/students/2 -H 'Content-Type: application/json' -d '{"mfaEnabled":true,"email":"test@domain.name","admin":true}'

# Usuń studenta
curl -X DELETE http://localhost:8080/students/2
```

### Teachers
```bash
# Stwórz wykładowcę
curl -X POST http://localhost:8080/teachers -H 'Content-Type: application/json' -d '{"admin":true,"active":true,"mfaEnabled":true,"birthDate":[1986,5,1],"email":"tu@uni.edu","firstName":"Test","lastName":"Userson","login":"tuserson","degree":"PhD","employmentType":"FULL_TIME","hireDate":[2020,6,28],"officeNumber":"42","title":"Dr prof","password":"","department":{"id":0}}'

# Pokaż wykładowcę
curl -X GET http://localhost:8080/teachers/2

# Uaktualnij dane wykładowcy
curl -X PUT http://localhost:8080/teachers/2 -H 'Content-Type: application/json' -d '{"mfaEnabled":true,"email":"test@domain.name","admin":true}'

# Usuń wykładowcę
curl -X DELETE http://localhost:8080/teachers/2
```

### Books
```bash
# Pokaż wszystkie książki
curl -X GET -H 'Accept: application/json' http://localhost:8080/books

# Pokaż jedną książka
curl -X GET -H 'Accept: application/json' http://localhost:8080/books/202
```

### Loans
```bash
# Pokaż wszystkie książki pożyczone przez studenta
curl -X GET -H 'Accept: application/json' http://localhost:8080/students/1/loans
```
 
### Payments
```bash
# Pokaż wszystkie płatności
curl -X GET -H 'Accept: application/json' http://localhost:8080/payments

# Pokaż wszystkie płatności studenta
curl -X GET -H 'Accept: application/json' http://localhost:8080/students/1/payments
```

### Wewnętrzne API
API do testowania metod DAO do wewnętrznego użytkowania. 

#### Logins
```bash
# Dodaj logowanie użytkownika
curl -X POST http://localhost:8080/internal/logins -H 'Content-Type: application/json' -d '{"user":{"id":0},"succeeded":false}'

# Pokaż wszystkie logowania użytkownika
curl -X GET http://localhost:8080/internal/users/0/logins
```

#### Users
```bash
# Aktywuj użytkownika
curl -X POST http://localhost:8080/internal/users/0/activate

# Pokaż użytkownika o podanym loginie
curl -X GET http://localhost:8080/internal/users/logins/jkowalski
```
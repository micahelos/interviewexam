# Zadanie Rekrutacyjne
Zadanie ewaluacyjne na młodszego programistę JAVA dla firmy Inteca.

## Opis
Repozytorium zawiera komponenty niezbędne do poprawnego działania aplikacji. 
Usługi: 
 - Product-Service
 - Credit-Service
 - Customer-Service

Serwer Netflix Eureka:
-	eureka-discover-server

Domyślnie usługi są rejestrowane na serwerze Netfilx Eureka nasłuchującym pod adresem:
`http://localhost:8761`

Credit-Service wystawia dwie usługi Rest o nazwie:
- GetCredits `allow Method: GET`
- CreateCredit `allow Method: POST`

Jako bazę danych wykorzystano bazę MySQL.
Domyślna konfiguracja zakłada działanie serwera na porcie `3306`.
Każda z usług zakłada własny schemat bazy danych, dokładny opis w punkcie <b>Baza Danych</b>

### Przykład wywołania usługi GetCredits:
Do wywołania usługi REST GetCredits nie są wymagane, żadne parametry. W wiadomości zwrotnej w formacie JSON zostaną zwrócone wszystkie zapisane w bazie danych kredyty. W formacie:
 - customer:
	 - firstName: `<String>`
	 - surname: `<String>`
	 - pesel: `Long`
-	product:
	-	productName : `<String>`
	-	value: `Long`
-	credit:
	-	creditName: `<String>`

Przykładowy adres do wywołania usługi:
`http://localhost:<port>/GetCredits/`
Gdzie:
- `<port>` - oznacza port na którym nasłuchuje usługa credit-service, pobrany lub odczytany np. z serwera Eureka

`[
        {
        "customer": {
            "FirstName": "Jan",
            "Surname": "Nowak",
            "Pesel": "52010579876"
        },
        "product": {
            "ProductName": "Kredyt dla młodych",
            "Value": "5000"
        },
        "credit": {
            "creditName": "Detaliczny"
        }
    },
    {
        "customer": {
            "FirstName": "Piotr",
            "Surname": "Kowalski",
            "Pesel": "78032558466"
        },
        "product": {
            "ProductName": "Kredyt na mieszkanie",
            "Value": "100000"
        },
        "credit": {
            "creditName": "Detaliczny"
        }
    },
    {
        "customer": {
            "FirstName": "Piotr",
            "Surname": "Wiśnia",
            "Pesel": "91032391411"
        },
        "product": {
            "ProductName": "Na zakup maszyn",
            "Value": "250000"
        },
        "credit": {
            "creditName": "Agro"
        }
    },
    {
        "customer": {
            "FirstName": "Anna",
            "Surname": "Adamiak",
            "Pesel": "64021599257"
        },
        "product": {
            "ProductName": "Konsolidacyja kredytow",
            "Value": "3000"
        },
        "credit": {
            "creditName": "Konsolidacyjny"
        }
    }
]`



### Przykład wywołania usługi CreateCredits:
Usługa REST CreateCredits przyjmuje na wejściu w formacie JSON w następujące informacje:
 - customer:
	 - firstName: `<String>`
	 - surname: `<String>`
	 - pesel: `<String>`
-	product:
	-	productName : `<String>`
	-	value: `Long`
-	credit:
	-	creditName: `<String>`

Usługa waliduje poprawnośc wprowadzonych danych.
Żadne z podanych wartości nie może być `Null`. Numer Pesel musi być poprawnym numerem składających się dokładnie z 11 cyfr.
W przypadku błędów, niepoprawnych danych lub niedostępności którejś z usług, która uniemożliwiłaby zapis w bazie danych nowego kredytu wszelkie zmiany zostaną wycofane.

Przykładowy adres wywołania usługi:
	`http://localhost:<port>/CreateCredit/`
Przykładowe parametry JSON:
`      {
        "customer": {
            "firstName": "Piotr",
            "surname": "Wiśnia",
            "pesel": "91032391411"
        },
        "product": {
            "productName": "Na zakup maszyn",
            "value": 250000
        }, 
        "credit": {
    		"creditName": "Agro" 
    	}
    }
`
<b>Status Http</b>: 201 Created
`{
    "creditID": 7
}`
## Uruchomienie i budowanie z pomocą Apache Maven
Musisz zbudować i uruchomić każdą aplikację osobno. We wszystkich przypadkach możesz zbudować aplikację za pomocą Maven w następujący sposób: 
`% ./mwnw clean package`
Po zbudowaniu projektów możesz uruchamiać ich pliki wykonywalne JAR indywidualnie. Na przykład możesz to zrobić w ten sposób:=
 1. Rejestr Usług - Netflix Eureka Discover Server:  `java -jar target/eureka-discover-server.jar`
2.	Product-Service: `java -jar target/product-service.jar`
3. Customer-Service: `java -jar target/customer-service.jar`
4. Credit-Service: `java -jar target/credit-service.jar`

Domyślnie Serwer Eureka uruchamiany jest na porcie `8761`, więc po uruchomieniu usług można przejść w przeglądarce pod adres `localhost:8761`, aby sprawdzić czy wszystkie usługi wystartowały prawidłowo. Numer portów dla pozostałych usług jest przydzielany losowo.

## Docker
<TODO>

## Baza Danych
Każda z usług w momencie uruchomienia aplikacji tworzy własny schemat bazy danych, o ile jeszcze taki nie istnieje. Jeżeli baza danych już istnieje w momencie ponownego uruchomienia aplikacji dane są jedynie uaktualniane lub dodawane nowe.

### Schemat:
1.	Usługa customer-service tworzy nowy schemat danych o nazwie customerdb
`Hibernate: create table customer (creditid bigint not null, first_name varchar(255) not null, pesel varchar(11) not null, surname varchar(255) not null, primary key (creditid)) engine=InnoDB`
	
2.	Usługa product-service tworzy nowy schemat bazy danych o nawie productdb
`Hibernate: create table product (creditid bigint not null, product_name varchar(255), value bigint, primary key (creditid)) engine=InnoDB`

3.	Usługa credit-service tworzy nowy schemat bazy danych o nazwie creditdb:
`Hibernate: create table credit (id bigint not null, credit_name varchar(255), primary key (id)) engine=InnoDB`

Dodatkowo zostanie HIbernate utworzy sekwencje:
`Hibernate: create table hibernate_sequence (next_val bigint) engine=InnoDB
Hibernate: insert into hibernate_sequence values ( 1 )`
  
### Uwagi do zadania:
1.	Wystawienie dwóch osobnych usług REST CreateCredit oraz GetCredit wykonałem zgodnie z instrukcją w .pdf, natomiast uważam, że znacznie lepszym pomysłem byłoby stworzenie Restful api lub zmiana nazw wystawionych usług.
Na przykładowy adres `localhost:8080/credits/`można byłoby zarówno wywołać metodę GET zwracającą wszystkie kredyty jak i za pomocą POST założyć nowy kredyt. W przypadku założenia nowego kredytu skłaniałbym się jednak ku adresowi zbliżonemu do `localhost:8080/credits/create`.
2.	 Zwracaną wartością dla wywołania usługi CreateCredit zamiast numeru założonego kredytu można by zwracać cały model kredytu, z informacją dla kogo został założony i na jaki produkt. 

## Search-Web

- /api/search-web/name
- REQUEST: GET
- URL: https://localhost:8443/api/search-web/name?name=American%20Whey
- ANSWER:
```
{  
  "id": 1,  
  "name": "American Whey",
  "address": "Avenida España 43 ",
  "description": "Description",
  "email": "american@whey.com",
  "foodType": "American",
  "menuPrice": 15,
  "breakfast": true,
  "lunch": true,
  "dinner": true,
  "roles": "ROLE_RESTAURANT",
  "phone": 658742154,
  "rate": 5,
  "password": "$2a$10$4KYzqpN1qPjYp0BA/jr4GeDmHQNmSteJXOVqoNAWxNyCaTZSSi.c."
}
```

___
- /api/search-web/foodtypeandcity
- REQUEST: GET
- URL:https://localhost:8443/api/search-web/foodtypeandcity?typeFood=American&&city=Madrid
- ANSWER:
```
[
  {
    "id": 1,
    "name": "American Whey",
    "address": "Avenida España 43 ",
    "description": "Description",
    "email": "american@whey.com",
    "foodType": "American",
    "menuPrice": 15,
    "breakfast": true,
    "lunch": true,
    "dinner": true,
    "roles": "ROLE_RESTAURANT",
    "phone": 658742154,
    "rate": 5,
    "password": "$2a$10$4KYzqpN1qPjYp0BA/jr4GeDmHQNmSteJXOVqoNAWxNyCaTZSSi.c."
  }
]
```
___
- /api/search-web/filters
- REQUEST:GET
- URL:https://localhost:8443/api/search-web/filters?min=3&&max=5&&minPrice=3&&maxPrice=20
- ANSWER:
```
[
    {
        "id": 1,
        "name": "American Whey",
        "address": "Avenida España 43 ",
        "description": "Description",
        "email": "american@whey.com",
        "foodType": "American",
        "menuPrice": 15,
        "breakfast": true,
        "lunch": true,
        "dinner": true,
        "roles": "ROLE_RESTAURANT",
        "phone": 658742154,
        "rate": 5,
        "password": "$2a$10$4KYzqpN1qPjYp0BA/jr4GeDmHQNmSteJXOVqoNAWxNyCaTZSSi.c."
    },
    {
        "id": 2,
        "name": "Meson Mariano",
        "address": "Avenida America 2",
        "description": "Description",
        "email": "meson@mariano.com",
        "foodType": "tapas",
        "menuPrice": 12,
        "breakfast": true,
        "lunch": true,
        "dinner": true,
        "roles": "ROLE_RESTAURANT",
        "phone": 652312342,
        "rate": 4.9,
        "password": "$2a$10$.9EqWu2hgEr80.2TD..bz.UKPU7ynJmTOLHJPiAj0rtAC0FK0jExq"
    },
    {
        "id": 3,
        "name": "Meson Felipe",
        "address": "Avenida Barcelona 43",
        "description": "Description",
        "email": "meson@felipe.com",
        "foodType": "Galician",
        "menuPrice": 5,
        "breakfast": true,
        "lunch": true,
        "dinner": true,
        "roles": "ROLE_RESTAURANT",
        "phone": 123213123,
        "rate": 3.5,
        "password": "$2a$10$SL2H63x5yrd8jsxImyF/mOlqEDf20ngX/jjkeo03XQ7EXsr8iPUbe"
    },
    {
        "id": 5,
        "name": "Meson Daniel",
        "address": "Plaza de España 69",
        "description": "Description",
        "email": "meson@daniel.com",
        "foodType": "Japanese",
        "menuPrice": 16,
        "breakfast": true,
        "lunch": true,
        "dinner": true,
        "roles": "ROLE_RESTAURANT",
        "phone": 123213123,
        "rate": 3.2,
        "password": "$2a$10$G01kvUXUkgwILWY0WFl5A.vV.l.iDqM/6UlD1GkhAiBxXFAdSb54y"
    },
    {
        "id": 6,
        "name": "Meson Eulalio",
        "address": "Calle Margaret 12",
        "description": "Description",
        "email": "meson@eulalio.com",
        "foodType": "Chinese",
        "menuPrice": 18,
        "breakfast": true,
        "lunch": true,
        "dinner": true,
        "roles": "ROLE_RESTAURANT",
        "phone": 123213123,
        "rate": 4.8,
        "password": "$2a$10$hueo3MFJjZPZoqmHnoceQO4ZljfaBh.vVbxFcH9UdcWalC9G3W9rm"
    },
    {
        "id": 7,
        "name": "Meson Eusebio",
        "address": "Callejón Paco 1",
        "description": "Description",
        "email": "meson@eusebio.com",
        "foodType": "Mexican",
        "menuPrice": 12,
        "breakfast": true,
        "lunch": true,
        "dinner": true,
        "roles": "ROLE_RESTAURANT",
        "phone": 123213123,
        "rate": 3.1,
        "password": "$2a$10$PXjsOh9useve7sllKqQJIuROOWrEVeilPJ47VEgNBDVa4UErtcA.."
    },
    {
        "id": 8,
        "name": "Meson Gento",
        "address": "Calle azulona 76",
        "description": "Description",
        "email": "meson@gento.com",
        "foodType": "Indian",
        "menuPrice": 15,
        "breakfast": true,
        "lunch": true,
        "dinner": true,
        "roles": "ROLE_RESTAURANT",
        "phone": 123213123,
        "rate": 3.5,
        "password": "$2a$10$qxXOa04cZIajW4EFFpOMduJW/H9TL4GYe2Hv7esVuCVjnTA34VdDi"
    },
    {
        "id": 9,
        "name": "Meson Genaro",
        "address": "Plaza de la reina 3",
        "description": "Description",
        "email": "meson@genaro.com",
        "foodType": "Thai",
        "menuPrice": 20,
        "breakfast": true,
        "lunch": true,
        "dinner": true,
        "roles": "ROLE_RESTAURANT",
        "phone": 123213123,
        "rate": 4.1,
        "password": "$2a$10$XRJydtIGVSdtxnpeHjnkh.ZLnVID4WQwfmZlZDrfj5EF7tKZcZDkG"
    },
    {
        "id": 11,
        "name": "American Whey2",
        "address": "Avenida España 43 ",
        "description": "Description",
        "email": "american@whey2.com",
        "foodType": "American",
        "menuPrice": 15,
        "breakfast": true,
        "lunch": true,
        "dinner": true,
        "roles": "ROLE_RESTAURANT",
        "phone": 658742154,
        "rate": 5,
        "password": "$2a$10$Y3IbjHbl9MmezlnG8Zqu/eqLIy38UYx06P.8p52ZQmg17mKl3Kr96"
    }
]
```
## LOGIN CONTROLLER

- api/logIn/user
- REQUEST:GET
- URL:
- ANSWER:
___
- api/logIn/restaurant
- REQUEST: GET
- URL: https://localhost:8443/api/logIn/restaurant
- ANSWER:
```
 {
  "id": 1,
  "name": "American Whey",
  "address": "Avenida España 43 ",
  "description": "Description",
  "email": "american@whey.com",
  "foodType": "American",
  "menuPrice": 15,
  "breakfast": true,
  "lunch": true,
  "dinner": true,
  "roles": "ROLE_RESTAURANT",
  "phone": 658742154,
  "rate": 5,
  "password": "$2a$10$Pdlc3ThotuKj1AShGXrZuuOdIKIOiZdGXiWwpCvdM3utfmuJOx3dO"
}
```
___
- api/logOut
- REQUEST: GET
- URL:https://localhost:8443/api/logOut
- ANSWER: `true`

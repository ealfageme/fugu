# API
- Search-Web
- Login Controller
- Client
- City

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


## CLIENT

- /api/clients/{id}
- REQUEST: GET
- URL: https://localhost:8443/api/clients/2
- ANSWER:
```
{
    "id": 2,
    "name": "Peter",
    "email": "peter@website.com",
    "age": 31,
    "description": "Description",
    "favouriteFood": "Tapas",
    "followingUsers": [
        {
            "id": 4,
            "nameFollowing": "Samuel",
            "emailFollowing": "samuel@website.com"
        }
    ],
    "followingRestaurants": [
        {
            "id": 1,
            "nameFollowing": "American Whey",
            "emailFollowing": "american@whey.com"
        },
        {
            "id": 6,
            "nameFollowing": "Meson Eulalio",
            "emailFollowing": "meson@eulalio.com"
        },
        {
            "id": 10,
            "nameFollowing": "Meson Agapito",
            "emailFollowing": "meson@agapito.com"
        }
    ],
    "vouchers": [
        {
            "id": 3,
            "voucherName": "30% discount",
            "voucherDescription": "Available on mondays only"
        },
        {
            "id": 4,
            "voucherName": "40% discount",
            "voucherDescription": "Available on tuesdays only"
        }
    ],
    "booking": [
        {
            "id": 5,
            "number": 1,
            "specialRequirements": "",
            "date": "2017-05-01 14:00:00.0"
        },
        {
            "id": 6,
            "number": 6,
            "specialRequirements": "",
            "date": "2017-04-14 21:00:00.0"
        }
    ],
    "reviews": [
        {
            "id": 2,
            "date": "2017-03-31 10:11:41.0",
            "content": "This restaurant must be improved",
            "rate": 2.2
        },
        {
            "id": 5,
            "date": "2017-03-31 10:11:41.0",
            "content": "This place have classic British dishes, served in a nery warm, homely, atmosphere. Love their calves' liver dish - it never fails. Welcoming staff and good service!",
            "rate": 2.2
        }
    ]
}

```
___
- /api/clients/signin
- REQUEST: POST
- URL: https://localhost:8443/api/clients/signin
- ANSWER:
___
- /api/clients/
- REQUEST: PUT
- URL: https://localhost:8443/api/clients
- ANSWER:

___
- /api/clients/
- REQUEST: GET
- URL: https://localhost:8443/api/clients/
- ANSWER:
```
{
    "number": 0,
    "numberOfElements": 4,
    "totalElements": 4,
    "totalPages": 1,
    "size": 20,
    "content": [
        {
            "id": 1,
            "name": "John",
            "email": "john@website.com",
            "age": 21,
            "description": "Description",
            "favouriteFood": "Italian",
            "followingUsers": [
                {
                    "id": 2,
                    "nameFollowing": "Peter",
                    "emailFollowing": "peter@website.com"
                }
            ],
            "followingRestaurants": [
                {
                    "id": 1,
                    "nameFollowing": "American Whey",
                    "emailFollowing": "american@whey.com"
                },
                {
                    "id": 2,
                    "nameFollowing": "Meson Mariano",
                    "emailFollowing": "meson@mariano.com"
                },
                {
                    "id": 5,
                    "nameFollowing": "Meson Daniel",
                    "emailFollowing": "meson@daniel.com"
                },
                {
                    "id": 9,
                    "nameFollowing": "Meson Genaro",
                    "emailFollowing": "meson@genaro.com"
                },
                {
                    "id": 8,
                    "nameFollowing": "Meson Gento",
                    "emailFollowing": "meson@gento.com"
                }
            ],
            "vouchers": [
                {
                    "id": 1,
                    "voucherName": "2*1 in salads",
                    "voucherDescription": "Come with a friend and eat for the half price"
                },
                {
                    "id": 2,
                    "voucherName": "FREE drinks on fridays",
                    "voucherDescription": "Every friday we offer free drinks with hamburger menus"
                }
            ],
            "booking": [
                {
                    "id": 1,
                    "number": 5,
                    "specialRequirements": "One high chair for the baby",
                    "date": "2017-03-24 22:00:00.0"
                },
                {
                    "id": 3,
                    "number": 3,
                    "specialRequirements": "High chair",
                    "date": "2017-05-16 20:00:00.0"
                }
            ],
            "reviews": [
                {
                    "id": 1,
                    "date": "2017-03-31 10:11:41.0",
                    "content": "Fucking amazing",
                    "rate": 5
                },
                {
                    "id": 4,
                    "date": "2017-03-31 10:11:41.0",
                    "content": "Cute decoration, friendly staff and a good cook, also dogs allowed My favourite restaurant for English Breakfast!!!",
                    "rate": 5
                }
            ]
        },
        {
            "id": 2,
            "name": "Peter",
            "email": "peter@website.com",
            "age": 31,
            "description": "Description",
            "favouriteFood": "Tapas",
            "followingUsers": [
                {
                    "id": 4,
                    "nameFollowing": "Samuel",
                    "emailFollowing": "samuel@website.com"
                }
            ],
            "followingRestaurants": [
                {
                    "id": 1,
                    "nameFollowing": "American Whey",
                    "emailFollowing": "american@whey.com"
                },
                {
                    "id": 6,
                    "nameFollowing": "Meson Eulalio",
                    "emailFollowing": "meson@eulalio.com"
                },
                {
                    "id": 10,
                    "nameFollowing": "Meson Agapito",
                    "emailFollowing": "meson@agapito.com"
                }
            ],
            "vouchers": [
                {
                    "id": 3,
                    "voucherName": "30% discount",
                    "voucherDescription": "Available on mondays only"
                },
                {
                    "id": 4,
                    "voucherName": "40% discount",
                    "voucherDescription": "Available on tuesdays only"
                }
            ],
            "booking": [
                {
                    "id": 5,
                    "number": 1,
                    "specialRequirements": "",
                    "date": "2017-05-01 14:00:00.0"
                },
                {
                    "id": 6,
                    "number": 6,
                    "specialRequirements": "",
                    "date": "2017-04-14 21:00:00.0"
                }
            ],
            "reviews": [
                {
                    "id": 2,
                    "date": "2017-03-31 10:11:41.0",
                    "content": "This restaurant must be improved",
                    "rate": 2.2
                },
                {
                    "id": 5,
                    "date": "2017-03-31 10:11:41.0",
                    "content": "This place have classic British dishes, served in a nery warm, homely, atmosphere. Love their calves' liver dish - it never fails. Welcoming staff and good service!",
                    "rate": 2.2
                }
            ]
        },
        {
            "id": 3,
            "name": "Lucas",
            "email": "lucas@website.com",
            "age": 69,
            "description": "Description",
            "favouriteFood": "American",
            "followingUsers": [],
            "followingRestaurants": [
                {
                    "id": 2,
                    "nameFollowing": "Meson Mariano",
                    "emailFollowing": "meson@mariano.com"
                },
                {
                    "id": 8,
                    "nameFollowing": "Meson Gento",
                    "emailFollowing": "meson@gento.com"
                },
                {
                    "id": 1,
                    "nameFollowing": "American Whey",
                    "emailFollowing": "american@whey.com"
                }
            ],
            "vouchers": [],
            "booking": [],
            "reviews": []
        },
        {
            "id": 4,
            "name": "Samuel",
            "email": "samuel@website.com",
            "age": 54,
            "description": "Description",
            "favouriteFood": "Thai",
            "followingUsers": [
                {
                    "id": 1,
                    "nameFollowing": "John",
                    "emailFollowing": "john@website.com"
                },
                {
                    "id": 2,
                    "nameFollowing": "Peter",
                    "emailFollowing": "peter@website.com"
                }
            ],
            "followingRestaurants": [
                {
                    "id": 2,
                    "nameFollowing": "Meson Mariano",
                    "emailFollowing": "meson@mariano.com"
                },
                {
                    "id": 7,
                    "nameFollowing": "Meson Eusebio",
                    "emailFollowing": "meson@eusebio.com"
                },
                {
                    "id": 10,
                    "nameFollowing": "Meson Agapito",
                    "emailFollowing": "meson@agapito.com"
                }
            ],
            "vouchers": [
                {
                    "id": 5,
                    "voucherName": "50% discount",
                    "voucherDescription": "Available on thursdays only"
                },
                {
                    "id": 6,
                    "voucherName": "10% discount",
                    "voucherDescription": "Available on fridays only"
                }
            ],
            "booking": [
                {
                    "id": 2,
                    "number": 2,
                    "specialRequirements": "Champagne",
                    "date": "2017-04-04 22:30:00.0"
                },
                {
                    "id": 4,
                    "number": 4,
                    "specialRequirements": "",
                    "date": "2017-03-20 00:00:00.0"
                }
            ],
            "reviews": [
                {
                    "id": 3,
                    "date": "2017-03-31 10:11:41.0",
                    "content": "If you want to spend a good time eating, come here",
                    "rate": 4.1
                },
                {
                    "id": 6,
                    "date": "2017-03-31 10:11:41.0",
                    "content": "Nice and near to Liverpool Street station. Lovely food with good service and the atmosphere is buzzing as it gets quite busy in the evening would definitely go again.",
                    "rate": 4.1
                }
            ]
        }
    ]
}
```
___
- /api/clients/{id}/following
- REQUEST: GET
- URL: https://localhost:8443/api/clients/1/following
- ANSWER:
```
[
    {
        "id": 4,
        "name": "Samuel",
        "email": "samuel@website.com",
        "age": 54,
        "description": "Description",
        "favouriteFood": "Thai",
        "followingUsers": [
            {
                "id": 1,
                "nameFollowing": "John",
                "emailFollowing": "john@website.com"
            },
            {
                "id": 2,
                "nameFollowing": "Peter",
                "emailFollowing": "peter@website.com"
            }
        ],
        "followingRestaurants": [
            {
                "id": 2,
                "nameFollowing": "Meson Mariano",
                "emailFollowing": "meson@mariano.com"
            },
            {
                "id": 7,
                "nameFollowing": "Meson Eusebio",
                "emailFollowing": "meson@eusebio.com"
            },
            {
                "id": 10,
                "nameFollowing": "Meson Agapito",
                "emailFollowing": "meson@agapito.com"
            }
        ],
        "vouchers": [
            {
                "id": 5,
                "voucherName": "50% discount",
                "voucherDescription": "Available on thursdays only"
            },
            {
                "id": 6,
                "voucherName": "10% discount",
                "voucherDescription": "Available on fridays only"
            }
        ],
        "booking": [
            {
                "id": 2,
                "number": 2,
                "specialRequirements": "Champagne",
                "date": "2017-04-04 22:30:00.0"
            },
            {
                "id": 4,
                "number": 4,
                "specialRequirements": "",
                "date": "2017-03-20 00:00:00.0"
            }
        ],
        "reviews": [
            {
                "id": 3,
                "date": "2017-03-31 10:11:41.0",
                "content": "If you want to spend a good time eating, come here",
                "rate": 4.1
            },
            {
                "id": 6,
                "date": "2017-03-31 10:11:41.0",
                "content": "Nice and near to Liverpool Street station. Lovely food with good service and the atmosphere is buzzing as it gets quite busy in the evening would definitely go again.",
                "rate": 4.1
            }
        ]
    },
    {
        "id": 2,
        "name": "Peter",
        "email": "peter@website.com",
        "age": 31,
        "description": "Description",
        "favouriteFood": "Tapas",
        "followingUsers": [
            {
                "id": 4,
                "nameFollowing": "Samuel",
                "emailFollowing": "samuel@website.com"
            }
        ],
        "followingRestaurants": [
            {
                "id": 1,
                "nameFollowing": "American Whey",
                "emailFollowing": "american@whey.com"
            },
            {
                "id": 6,
                "nameFollowing": "Meson Eulalio",
                "emailFollowing": "meson@eulalio.com"
            },
            {
                "id": 10,
                "nameFollowing": "Meson Agapito",
                "emailFollowing": "meson@agapito.com"
            }
        ],
        "vouchers": [
            {
                "id": 3,
                "voucherName": "30% discount",p
                "voucherDescription": "Available on mondays only"
            },
            {
                "id": 4,
                "voucherName": "40% discount",
                "voucherDescription": "Available on tuesdays only"
            }
        ],
        "booking": [
            {
                "id": 5,
                "number": 1,
                "specialRequirements": "",
                "date": "2017-05-01 14:00:00.0"
            },
            {
                "id": 6,
                "number": 6,
                "specialRequirements": "",
                "date": "2017-04-14 21:00:00.0"
            }
        ],
        "reviews": [
            {
                "id": 2,
                "date": "2017-03-31 10:11:41.0",
                "content": "This restaurant must be improved",
                "rate": 2.2
            },
            {
                "id": 5,
                "date": "2017-03-31 10:11:41.0",
                "content": "This place have classic British dishes, served in a nery warm, homely, atmosphere. Love their calves' liver dish - it never fails. Welcoming staff and good service!",
                "rate": 2.2
            }
        ]
    }
]
```
___ 
- /api/clients/{id}/unfollow
- REQUEST: DELETE
- URL: 
- ANSWER:
```
[
    {
        "id": 2,
        "name": "Peter",
        "email": "peter@website.com",
        "age": 31,
        "description": "Description",
        "favouriteFood": "Tapas",
        "followingUsers": [
            {
                "id": 4,
                "nameFollowing": "Samuel",
                "emailFollowing": "samuel@website.com"
            }
        ],
        "followingRestaurants": [
            {
                "id": 1,
                "nameFollowing": "American Whey",
                "emailFollowing": "american@whey.com"
            },
            {
                "id": 6,
                "nameFollowing": "Meson Eulalio",
                "emailFollowing": "meson@eulalio.com"
            },
            {
                "id": 10,
                "nameFollowing": "Meson Agapito",
                "emailFollowing": "meson@agapito.com"
            }
        ],
        "vouchers": [
            {
                "id": 3,
                "voucherName": "30% discount",
                "voucherDescription": "Available on mondays only"
            },
            {
                "id": 4,
                "voucherName": "40% discount",
                "voucherDescription": "Available on tuesdays only"
            }
        ],
        "booking": [
            {
                "id": 5,
                "number": 1,
                "specialRequirements": "",
                "date": "2017-05-01 14:00:00.0"
            },
            {
                "id": 6,
                "number": 6,
                "specialRequirements": "",
                "date": "2017-04-14 21:00:00.0"
            }
        ],
        "reviews": [
            {
                "id": 2,
                "date": "2017-03-31 10:11:41.0",
                "content": "This restaurant must be improved",
                "rate": 2.2
            },
            {
                "id": 5,
                "date": "2017-03-31 10:11:41.0",
                "content": "This place have classic British dishes, served in a nery warm, homely, atmosphere. Love their calves' liver dish - it never fails. Welcoming staff and good service!",
                "rate": 2.2
            }
        ]
    }
]
```

## City

- /api/city/
- REQUEST: GET
- URL: https://localhost:8443/api/city/
- ANSWER:
```
[
    {
        "name": "Madrid",
        "id": 1
    },
    {
        "name": "Barcelona",
        "id": 2
    },
    {
        "name": "Valencia",
        "id": 3
    },
    {
        "name": "Sevilla",
        "id": 4
    },
    {
        "name": "Zaragoza",
        "id": 5
    },
    {
        "name": "Bilbao",
        "id": 6
    }
]
```
___

- /api/city/{name}
- REQUEST: GET
- URL:https://localhost:8443/api/city/Madrid
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
    "password": "$2a$10$q1LCivWQJ/oIpHgAgwQTu.6R6vaf5Hk3MeajHBuvldTCseFZRCXk."
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
    "password": "$2a$10$ujl8G7tATKdjiPp6FuZsCeZklhAys3rJpwPF9ha5MSVfhYu6v7Fk."
  },
  {
    "id": 10,
    "name": "Meson Agapito",
    "address": "Avenida Pablo",
    "description": "Description",
    "email": "meson@agapito.com",
    "foodType": "Galician",
    "menuPrice": 8,
    "breakfast": true,
    "lunch": true,
    "dinner": true,
    "roles": "ROLE_RESTAURANT",
    "phone": 123213123,
    "rate": 2.6,
    "password": "$2a$10$cLmGm9Z1D50ioFMEOz/ydebyZ9MZcHxbS9TtM7MTK3.i2r4nH6vI6"
  }
]
```
## RESTAURANT

- /api/restaurant/{id}
- REQUEST: GET
- URL: https://localhost:8443/api/restaurant/1
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
    "password": "$2a$10$/g07/lB9CU5MH00Eu7I1ZufIGL5Tb9gU.klYaN.OiKFCgxwWM0y3y",
    "users": [
        {
            "id": 1,
            "name": "John",
            "email": "john@website.com",
            "age": 21,
            "description": "Description",
            "favouriteFood": "Italian",
            "followingUsers": [
                {
                    "id": 4,
                    "nameFollowing": "Samuel",
                    "emailFollowing": "samuel@website.com"
                },
                {
                    "id": 2,
                    "nameFollowing": "Peter",
                    "emailFollowing": "peter@website.com"
                }
            ],
            "followingRestaurants": [
                {
                    "id": 1,
                    "nameFollowing": "American Whey",
                    "emailFollowing": "american@whey.com"
                },
                {
                    "id": 2,
                    "nameFollowing": "Meson Mariano",
                    "emailFollowing": "meson@mariano.com"
                },
                {
                    "id": 5,
                    "nameFollowing": "Meson Daniel",
                    "emailFollowing": "meson@daniel.com"
                },
                {
                    "id": 9,
                    "nameFollowing": "Meson Genaro",
                    "emailFollowing": "meson@genaro.com"
                },
                {
                    "id": 8,
                    "nameFollowing": "Meson Gento",
                    "emailFollowing": "meson@gento.com"
                }
            ],
            "vouchers": [
                {
                    "id": 1,
                    "voucherName": "2*1 in salads",
                    "voucherDescription": "Come with a friend and eat for the half price"
                },
                {
                    "id": 2,
                    "voucherName": "FREE drinks on fridays",
                    "voucherDescription": "Every friday we offer free drinks with hamburger menus"
                }
            ],
            "booking": [
                {
                    "id": 1,
                    "number": 5,
                    "specialRequirements": "One high chair for the baby",
                    "date": "2017-03-24 22:00:00.0"
                },
                {
                    "id": 3,
                    "number": 3,
                    "specialRequirements": "High chair",
                    "date": "2017-05-16 20:00:00.0"
                }
            ],
            "reviews": [
                {
                    "id": 1,
                    "date": "2017-03-31 12:29:55.0",
                    "content": "Fucking amazing",
                    "rate": 5
                },
                {
                    "id": 4,
                    "date": "2017-03-31 12:29:55.0",
                    "content": "Cute decoration, friendly staff and a good cook, also dogs allowed My favourite restaurant for English Breakfast!!!",
                    "rate": 5
                }
            ]
        },
        {
            "id": 2,
            "name": "Peter",
            "email": "peter@website.com",
            "age": 31,
            "description": "Description",
            "favouriteFood": "Tapas",
            "followingUsers": [
                {
                    "id": 4,
                    "nameFollowing": "Samuel",
                    "emailFollowing": "samuel@website.com"
                }
            ],
            "followingRestaurants": [
                {
                    "id": 1,
                    "nameFollowing": "American Whey",
                    "emailFollowing": "american@whey.com"
                },
                {
                    "id": 6,
                    "nameFollowing": "Meson Eulalio",
                    "emailFollowing": "meson@eulalio.com"
                },
                {
                    "id": 10,
                    "nameFollowing": "Meson Agapito",
                    "emailFollowing": "meson@agapito.com"
                }
            ],
            "vouchers": [
                {
                    "id": 3,
                    "voucherName": "30% discount",
                    "voucherDescription": "Available on mondays only"
                },
                {
                    "id": 4,
                    "voucherName": "40% discount",
                    "voucherDescription": "Available on tuesdays only"
                }
            ],
            "booking": [
                {
                    "id": 5,
                    "number": 1,
                    "specialRequirements": "",
                    "date": "2017-05-01 14:00:00.0"
                },
                {
                    "id": 6,
                    "number": 6,
                    "specialRequirements": "",
                    "date": "2017-04-14 21:00:00.0"
                }
            ],
            "reviews": [
                {
                    "id": 2,
                    "date": "2017-03-31 12:29:55.0",
                    "content": "This restaurant must be improved",
                    "rate": 2.2
                },
                {
                    "id": 5,
                    "date": "2017-03-31 12:29:55.0",
                    "content": "This place have classic British dishes, served in a nery warm, homely, atmosphere. Love their calves' liver dish - it never fails. Welcoming staff and good service!",
                    "rate": 2.2
                }
            ]
        },
        {
            "id": 3,
            "name": "Lucas",
            "email": "lucas@website.com",
            "age": 69,
            "description": "Description",
            "favouriteFood": "American",
            "followingUsers": [],
            "followingRestaurants": [
                {
                    "id": 2,
                    "nameFollowing": "Meson Mariano",
                    "emailFollowing": "meson@mariano.com"
                },
                {
                    "id": 8,
                    "nameFollowing": "Meson Gento",
                    "emailFollowing": "meson@gento.com"
                },
                {
                    "id": 1,
                    "nameFollowing": "American Whey",
                    "emailFollowing": "american@whey.com"
                }
            ],
            "vouchers": [],
            "booking": [],
            "reviews": []
        }
    ],
    "menus": [
        {
            "dish": "Nachos",
            "price": 10.5,
            "description": " with cheese",
            "id": 1
        },
        {
            "dish": "BBQ Wings Chicken Bites",
            "price": 20.5,
            "description": "with sauce",
            "id": 2
        },
        {
            "dish": "Spaguetti",
            "price": 10.5,
            "description": " Bolognese",
            "id": 14
        },
        {
            "dish": "BBQ Wings ",
            "price": 20.5,
            "description": "with BBQ sauce",
            "id": 15
        },
        {
            "dish": "Fries",
            "price": 5,
            "description": "with ketchup and mayo",
            "id": 16
        },
        {
            "dish": "Chicken",
            "price": 12.5,
            "description": " with BBQ",
            "id": 17
        },
        {
            "dish": "Pasta",
            "price": 3.5,
            "description": "with sauce",
            "id": 18
        },
        {
            "dish": "Pasta",
            "price": 8,
            "description": "with Salad",
            "id": 19
        },
        {
            "dish": "Pork Fajita",
            "price": 6.5,
            "description": "with ketchup",
            "id": 20
        },
        {
            "dish": "Pork Triple Burger",
            "price": 12.5,
            "description": " with BBQ",
            "id": 21
        },
        {
            "dish": "Georgian Pork",
            "price": 3.5,
            "description": "with sauce",
            "id": 22
        },
        {
            "dish": "Pork Breast Sandwich",
            "price": 8,
            "description": "with Salad",
            "id": 23
        },
        {
            "dish": "Pork Double Burger",
            "price": 15.5,
            "description": " with BBQ",
            "id": 24
        },
        {
            "dish": "Bulgarian Chicken",
            "price": 3.5,
            "description": "with sauce",
            "id": 25
        },
        {
            "dish": "Pork Finger Sandwich",
            "price": 8,
            "description": "with Salad",
            "id": 26
        }
    ],
    "bookings": [
        {
            "id": 1,
            "date": 1490389200000,
            "number": 5,
            "specialRequirements": "One high chair for the baby",
            "state": "In process"
        }
    ],
    "city": {
        "name": "Madrid",
        "id": 1
    },
    "restaurantReviews": [
        {
            "content": "Fucking amazing",
            "rate": 5,
            "date": 1490956195000,
            "id": 1
        },
        {
            "content": "This restaurant must be improved",
            "rate": 2.2,
            "date": 1490956195000,
            "id": 2
        }
    ]
}
```
___
- /api/restaurant/ 
- REQUEST: PUT
- URL: https://localhost:8443/api/restaurant/
- ANSWER: 
```
{
    "id": 1,
    "name": "American Wheysito",
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
    "password": "$2a$10$8cVRIYGagjKEi3LhmAYCD.6GK1HxMuI8Ss5r6aYaABnhvZ6Fj8/tm",
    "users": [
        {
            "id": 1,
            "name": "John",
            "email": "john@website.com",
            "age": 21,
            "description": "Description",
            "favouriteFood": "Italian",
            "followingUsers": [],
            "followingRestaurants": [],
            "vouchers": [],
            "booking": [],
            "reviews": [
                {
                    "id": 1,
                    "date": 1490956933000,
                    "content": "Fucking amazing",
                    "rate": 5
                },
                {
                    "id": 4,
                    "date": 1490956933000,
                    "content": "Cute decoration, friendly staff and a good cook, also dogs allowed My favourite restaurant for English Breakfast!!!",
                    "rate": 5
                }
            ]
        },
        {
            "id": 2,
            "name": "Peter",
            "email": "peter@website.com",
            "age": 31,
            "description": "Description",
            "favouriteFood": "Tapas",
            "followingUsers": [],
            "followingRestaurants": [],
            "vouchers": [],
            "booking": [],
            "reviews": [
                {
                    "id": 2,
                    "date": 1490956933000,
                    "content": "This restaurant must be improved",
                    "rate": 2.2
                },
                {
                    "id": 5,
                    "date": 1490956933000,
                    "content": "This place have classic British dishes, served in a nery warm, homely, atmosphere. Love their calves' liver dish - it never fails. Welcoming staff and good service!",
                    "rate": 2.2
                }
            ]
        },
        {
            "id": 3,
            "name": "Lucas",
            "email": "lucas@website.com",
            "age": 69,
            "description": "Description",
            "favouriteFood": "American",
            "followingUsers": [],
            "followingRestaurants": [],
            "vouchers": [],
            "booking": [],
            "reviews": []
        }
    ],
    "menus": [
        {
            "dish": "Nachos",
            "price": 10.5,
            "description": " with cheese",
            "id": 1
        },
        {
            "dish": "BBQ Wings Chicken Bites",
            "price": 20.5,
            "description": "with sauce",
            "id": 2
        },
        {
            "dish": "Spaguetti",
            "price": 10.5,
            "description": " Bolognese",
            "id": 14
        },
        {
            "dish": "BBQ Wings ",
            "price": 20.5,
            "description": "with BBQ sauce",
            "id": 15
        },
        {
            "dish": "Fries",
            "price": 5,
            "description": "with ketchup and mayo",
            "id": 16
        },
        {
            "dish": "Chicken",
            "price": 12.5,
            "description": " with BBQ",
            "id": 17
        },
        {
            "dish": "Pasta",
            "price": 3.5,
            "description": "with sauce",
            "id": 18
        },
        {
            "dish": "Pasta",
            "price": 8,
            "description": "with Salad",
            "id": 19
        },
        {
            "dish": "Pork Fajita",
            "price": 6.5,
            "description": "with ketchup",
            "id": 20
        },
        {
            "dish": "Pork Triple Burger",
            "price": 12.5,
            "description": " with BBQ",
            "id": 21
        },
        {
            "dish": "Georgian Pork",
            "price": 3.5,
            "description": "with sauce",
            "id": 22
        },
        {
            "dish": "Pork Breast Sandwich",
            "price": 8,
            "description": "with Salad",
            "id": 23
        },
        {
            "dish": "Pork Double Burger",
            "price": 15.5,
            "description": " with BBQ",
            "id": 24
        },
        {
            "dish": "Bulgarian Chicken",
            "price": 3.5,
            "description": "with sauce",
            "id": 25
        },
        {
            "dish": "Pork Finger Sandwich",
            "price": 8,
            "description": "with Salad",
            "id": 26
        }
    ],
    "bookings": [
        {
            "id": 1,
            "date": 1490389200000,
            "number": 5,
            "specialRequirements": "One high chair for the baby",
            "state": "In process"
        }
    ],
    "city": {
        "name": "Madrid",
        "id": 1
    },
    "restaurantReviews": [
        {
            "content": "Fucking amazing",
            "rate": 5,
            "date": 1490956933000,
            "id": 1
        },
        {
            "content": "This restaurant must be improved",
            "rate": 2.2,
            "date": 1490956933000,
            "id": 2
        }
    ]
}
```
___
- /api/restaurant/
- REQUEST: GET
- URL: https://localhost:8443/api/restaurant/
- ANSWER: 
```
{
    "number": 0,
    "numberOfElements": 12,
    "totalElements": 12,
    "totalPages": 1,
    "size": 20,
    "content": [
        {
            "id": 1,
            "name": "American Wheysito",
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
            "password": "$2a$10$8cVRIYGagjKEi3LhmAYCD.6GK1HxMuI8Ss5r6aYaABnhvZ6Fj8/tm"
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
            "password": "$2a$10$sbpBWmTWDAmWfkw47T9zA.dPAKaH4l1pSponIVtuHjlHU3292yysa"
        },
        {
            "id": 12,
            "name": "American WheysitoNoMames",
            "address": "Avenida España 43 ",
            "description": "Description",
            "email": "american2@whey.com",
            "foodType": "American",
            "menuPrice": 15,
            "breakfast": true,
            "lunch": true,
            "dinner": true,
            "roles": "ROLE_RESTAURANT",
            "phone": 658742154,
            "rate": 5,
            "password": "$2a$10$NS/WHmhGTAhXXb0rXiQRv.pwY0AbCLKrTSdTDD9mI9a9cNhbHmX82"
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
            "password": "$2a$10$/0KQ.4YC8F.dKVqvWVtm4uElJ5eMaWQDKpncilLwaB1BZsGyl4LIS"
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
            "password": "$2a$10$h6NUgwDULLrlg9enCIMthuKlVxVQBDN1fJk3sggk/A6DtIUT5VXvu"
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
            "password": "$2a$10$YV/ZSY07OBVcanVu5v.0qOvjf.loAa3hToPmmvU5zoUUQi1wWKuGi"
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
            "password": "$2a$10$dBUZDklPc6fjPtOohueP9uKF5OVdZHyY6Eh/Hn8rkw1xj1QuCGMQm"
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
            "password": "$2a$10$cwWt.bvJ7.nPb6cQnPXNh.3ZpG2/shlssJGaadQMFhCQr0Bw0N/pm"
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
            "password": "$2a$10$A1HfMc./BkfiBGIBhGtE1.nIQGPYsFHTEcXqqg2KFjdUj9UXvkXV2"
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
            "password": "$2a$10$tV0Dw/4NrReGor9IujsFZezBoHWNrRmZAx7EkbigGmuL84zaLQK5m"
        },
        {
            "id": 10,
            "name": "Meson Agapito",
            "address": "Avenida Pablo",
            "description": "Description",
            "email": "meson@agapito.com",
            "foodType": "Galician",
            "menuPrice": 8,
            "breakfast": true,
            "lunch": true,
            "dinner": true,
            "roles": "ROLE_RESTAURANT",
            "phone": 123213123,
            "rate": 2.6,
            "password": "$2a$10$oogmorwht9uqdY.IicZLduICYciYcnFAp.mTpZNdYovOcjt6Bx3cy"
        },
        {
            "id": 4,
            "name": "Meson Lito",
            "address": "Calle de la amagura 5",
            "description": "Description",
            "email": "meson@lito.com",
            "foodType": "Italian",
            "menuPrice": 13,
            "breakfast": true,
            "lunch": true,
            "dinner": true,
            "roles": "ROLE_RESTAURANT",
            "phone": 123213123,
            "rate": 1.6,
            "password": "$2a$10$Xi0b3UF2fBO7ELkVXRcYCOaGgRX.y6.ixq71D/tjztpsaCPBXgqou"
        }
    ]
}
```
___
- /api/restaurant/signin 
- REQUEST: POST
- URL: https://localhost:8443/api/restaurant/signin 
- ANSWER: 
```
{
    "id": 12,
    "name": "American WheysitoNoMames",
    "address": "Avenida España 43 ",
    "description": "Description",
    "email": "american2@whey.com",
    "foodType": "American",
    "menuPrice": 15,
    "breakfast": true,
    "lunch": true,
    "dinner": true,
    "roles": "ROLE_RESTAURANT",
    "phone": 658742154,
    "rate": 5,
    "password": "$2a$10$NS/WHmhGTAhXXb0rXiQRv.pwY0AbCLKrTSdTDD9mI9a9cNhbHmX82",
    "users": [
        {
            "id": 1,
            "name": "John",
            "email": "john@website.com",
            "age": 21,
            "description": "Description",
            "favouriteFood": "Italian",
            "followingUsers": [],
            "followingRestaurants": [],
            "vouchers": [],
            "booking": [],
            "reviews": [
                {
                    "id": 1,
                    "date": 1490956933000,
                    "content": "Fucking amazing",
                    "rate": 5
                },
                {
                    "id": 4,
                    "date": 1490956933000,
                    "content": "Cute decoration, friendly staff and a good cook, also dogs allowed My favourite restaurant for English Breakfast!!!",
                    "rate": 5
                }
            ]
        },
        {
            "id": 2,
            "name": "Peter",
            "email": "peter@website.com",
            "age": 31,
            "description": "Description",
            "favouriteFood": "Tapas",
            "followingUsers": [],
            "followingRestaurants": [],
            "vouchers": [],
            "booking": [],
            "reviews": [
                {
                    "id": 2,
                    "date": 1490956933000,
                    "content": "This restaurant must be improved",
                    "rate": 2.2
                },
                {
                    "id": 5,
                    "date": 1490956933000,
                    "content": "This place have classic British dishes, served in a nery warm, homely, atmosphere. Love their calves' liver dish - it never fails. Welcoming staff and good service!",
                    "rate": 2.2
                }
            ]
        },
        {
            "id": 3,
            "name": "Lucas",
            "email": "lucas@website.com",
            "age": 69,
            "description": "Description",
            "favouriteFood": "American",
            "followingUsers": [],
            "followingRestaurants": [],
            "vouchers": [],
            "booking": [],
            "reviews": []
        }
    ],
    "menus": [
        {
            "dish": "Nachos",
            "price": 10.5,
            "description": " with cheese",
            "id": 1
        },
        {
            "dish": "BBQ Wings Chicken Bites",
            "price": 20.5,
            "description": "with sauce",
            "id": 2
        },
        {
            "dish": "Spaguetti",
            "price": 10.5,
            "description": " Bolognese",
            "id": 14
        },
        {
            "dish": "BBQ Wings ",
            "price": 20.5,
            "description": "with BBQ sauce",
            "id": 15
        },
        {
            "dish": "Fries",
            "price": 5,
            "description": "with ketchup and mayo",
            "id": 16
        },
        {
            "dish": "Chicken",
            "price": 12.5,
            "description": " with BBQ",
            "id": 17
        },
        {
            "dish": "Pasta",
            "price": 3.5,
            "description": "with sauce",
            "id": 18
        },
        {
            "dish": "Pasta",
            "price": 8,
            "description": "with Salad",
            "id": 19
        },
        {
            "dish": "Pork Fajita",
            "price": 6.5,
            "description": "with ketchup",
            "id": 20
        },
        {
            "dish": "Pork Triple Burger",
            "price": 12.5,
            "description": " with BBQ",
            "id": 21
        },
        {
            "dish": "Georgian Pork",
            "price": 3.5,
            "description": "with sauce",
            "id": 22
        },
        {
            "dish": "Pork Breast Sandwich",
            "price": 8,
            "description": "with Salad",
            "id": 23
        },
        {
            "dish": "Pork Double Burger",
            "price": 15.5,
            "description": " with BBQ",
            "id": 24
        },
        {
            "dish": "Bulgarian Chicken",
            "price": 3.5,
            "description": "with sauce",
            "id": 25
        },
        {
            "dish": "Pork Finger Sandwich",
            "price": 8,
            "description": "with Salad",
            "id": 26
        }
    ],
    "bookings": [
        {
            "id": 1,
            "date": 1490389200000,
            "number": 5,
            "specialRequirements": "One high chair for the baby",
            "state": "In process"
        }
    ],
    "city": {
        "name": "Madrid",
        "id": 1
    },
    "restaurantReviews": [
        {
            "content": "Fucking amazing",
            "rate": 5,
            "date": 1490956933000,
            "id": 1
        },
        {
            "content": "This restaurant must be improved",
            "rate": 2.2,
            "date": 1490956933000,
            "id": 2
        }
    ]
}
```
___
- /api/restaurant/{id}/menus 
- REQUEST: GET
- URL: https://localhost:8443/api/restaurant/2/menus GET
- ANSWER: 
```
{
    "number": 0,
    "numberOfElements": 2,
    "totalElements": 2,
    "totalPages": 1,
    "size": 20,
    "content": [
        {
            "dish": "Beef Double Burger",
            "price": 15.5,
            "description": " with BBQ",
            "id": 11
        },
        {
            "dish": "Bulgarian Wings",
            "price": 3.5,
            "description": "with sauce",
            "id": 12
        }
    ]
}
```
___
- /api/restaurant/{id}/menus 
- REQUEST: POST
- URL /api/restaurant/2/menus POST
- ANSWER:
```
{
    "dish": "BeefOr not Beeff Double Burger",
    "price": 15.5,
    "description": " with BBQ",
    "id": 11
}
```
___

- /api/restaurant/{id}/menus
- REQUEST: POST
- URL: /api/restaurant/2/menus 
- ANSWER:
```
{
    "dish": "BeefOr not Beeff Double Burger",
    "price": 15.5,
    "description": " with BBQ",
    "id": 11
}
```
___
- /api/restaurant/{id}/reviews
- REQUEST  GET
- URL: /api/restaurant/3/reviews
- ANSWER:
```
{
    "number": 0,
    "numberOfElements": 1,
    "totalElements": 1,
    "totalPages": 1,
    "size": 20,
    "content": [
        {
            "content": "Cute decoration, friendly staff and a good cook, also dogs allowed My favourite restaurant for English Breakfast!!!",
            "rate": 5,
            "date": 1490956933000,
            "id": 4
        }
    ]
}
```
___
- /api/restaurant/{id}/reviews
- REQUEST: POST
- URL: /api/restaurant/3/reviews
- ANSWER:
___
- /api/restaurant/{id}/book
- REQUEST: POST
- URL: /api/restaurant/1/book
- ANSWER
___
- /api/restaurant/{id}/book 
- REQUEST: GET
- URL: /api/restaurant/2/book 
- ANSWER:
```
{
    "number": 0,
    "numberOfElements": 1,
    "totalElements": 1,
    "totalPages": 1,
    "size": 20,
    "content": [
        {
            "id": 3,
            "date": 1494957600000,
            "number": 3,
            "specialRequirements": "High chair",
            "state": "In process"
        }
    ]
}
```
___
- /api/restaurant/{id}/book
- REQUEST: POST
- URL: /api/restaurant/2/book
- ANSWER:
___
- /api/restaurant/{id}/voucher
- REQUEST  GET
- URL: /api/restaurant/2/voucher
- ANSWER:
```
{
    "number": 0,
    "numberOfElements": 1,
    "totalElements": 1,
    "totalPages": 1,
    "size": 20,
    "content": [
        {
            "name": "FREE drinks on fridays",
            "description": "Every friday we offer free drinks with hamburger menus",
            "expiryDate": 1490959112000,
            "id": 2
        }
    ]
}
```
___
-  /api/restaurant/{id}/follow 
- REQUEST: POST
- URL:  /api/restaurant/2/follow 
- ANSWER:
___
- /api/restaurant/{id}/unfollow
- REQUEST  POST
- URL: /api/restaurant/2/unfollow
- ANSWER:

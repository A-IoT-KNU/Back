# API Documentation

---

### Client

---

1) `HTTP POST {}/client/register`: returns client auth tokens
- **JSON Fields Requirements**:
  
   - `firstName`: at least 3 characters, no special chars or numbers are allowed
  
   - `lastName`: at least 3 characters, no special chars or numbers are allowed
  
   - `email`: in format `uniquename@domain.com`
  
   - `username`: at least 3 characters, underscore signs, and number are allowed
  
   - `password`: 8-30 characters, only Latin letters and special characters, required at least:
     
      - a lowercase letter
        
         - an uppercase letter
        
         - a digit
        
         - a special character: ASCII characters with codes 33-47, 58-64, 91-96, 123-126

- **Json Request Example**:
  
  ```json
  {
      "firstName": "Ivan",
      "lastName": "Ivanov",
      "email": "ivan.ivanov@gmail.com",
      "username": "user123",
      "password": "password123"
  }
  ```

- **Json Response Example (Status: 200 OK)**:
  
  ```json
  {
      "accessToken": "eyJhbGciOiJSUzI1NiIs...",
      "refreshToken": "eyJhbGciOiJIUzI1NiIs..."
  }
  ```

- **Json Response Example (Status: 400 Bad Request)**:
  
  ```json
  {
    "errors": [
        "First name length should have more than 3 characters",
        "First name cannot be empty",
        "Password is not valid"    
    ]      
  }
  ```
  
   - All types of error messages:
     
      - `"First name cannot be empty"` (Status: 400 Bad Request)
     
      - `"First name length should have more than 3 characters"` (Status: 400 Bad Request)
     
      - `"First name is not valid"` (Status: 400 Bad Request)
     
      - `"Last name cannot be empty"` (Status: 400 Bad Request)
     
      - `"Last name length should have more than 3 characters"` (Status: 400 Bad Request)
     
      - `"Last name is not valid"` (Status: 400 Bad Request)
     
      - `"Email cannot be empty"` (Status: 400 Bad Request)
     
      - `"Email is not valid"` (Status: 400 Bad Request)
     
      - `"User exists with same email"` (Status: 400 Bad Request)
     
      - `"Username cannot be empty"` (Status: 400 Bad Request)
     
      - `"Username length should have more than 3 characters"` (Status: 400 Bad Request)
     
      - `"Username is not valid"` (Status: 400 Bad Request)
     
      - `"User exists with same username"` (Status: 400 Bad Request)
     
      - `"Password cannot be empty"` (Status: 400 Bad Request)
     
      - `"Password is not valid"` (Status: 400 Bad Request)
     
      - `"Internal server error"` (Status: 500 Internal Server Error)

---

2) `HTTP POST {}/client/login`: returns client auth tokens
- **JSON Field Requirements**:
  
   - `username`: at least 3 characters, underscore signs, and number are allowed
  
   - `password`: 8-30 characters, only Latin letters and special characters, required at least:
     
      - a lowercase letter
     
      - an uppercase letter
     
      - a digit
     
      - a special character: ASCII characters with codes 33-47, 58-64, 91-96, 123-126

- **Json Request Example**:
  
  ```json
  {
      "username": "user123",
      "password": "password123"
  }
  ```

- **Json Response Example (Status: 200 OK)**:
  
  ```json
  {
      "accessToken": "eyJhbGciOiJSUzI1NiIs...",
      "refreshToken": "eyJhbGciOiJIUzI1NiIs..."
  }
  ```

- **Json Response Example (Status: 400 Bad Request)**:
  
  ```json
  {
    "errors": [
        "Invalid user credentials"    
    ]      
  }
  ```
  
   - All types of error messages:
     
      - `"Username cannot be empty"` (Status: 400 Bad Request)
     
      - `"Username length should have more than 3 characters"` (Status: 400 Bad Request)
     
      - `"Username is not valid"` (Status: 400 Bad Request)
     
      - `"Password cannot be empty"` (Status: 400 Bad Request)
     
      - `"Password is not valid"` (Status: 400 Bad Request)
     
      - `"Invalid user credentials"` (Status: 400 Bad Request)
     
      - `"Internal server error"` (Status: 500 Internal Server Error)

---

3) `HTTP POST {}/client/logout`: return result of logout
- **JSON Fields Requirements**:
  
   - `accessToken`: not empty, must be valid JWT
  
   - `refreshToken`: not empty, must be valid JWT

- **JSON Request Example**:
  
  ```json
  {    
    "accessToken": "eyJhbGciOiJSUzI1NiIs...",
    "refreshToken": "eyJhbGciOiJIUzI1NiIs..."
  }
  ```

- **Json Response Example (Status: 200 OK)**:
  
  ```json
  
  ```

- **Json Response Example (Status: 400 Bad Request)**:
  
  ```json
  {
    "errors": [
        "Invalid refresh token"    
      ]      
  }
  ```
  
   - All types of error messages:
     
      - `"Access token cannot be empty"` (Status: 400 Bad Request)
     
      - `"Refresh token cannot be empty"` (Status: 400 Bad Request)
     
      - `"Invalid refresh token"` (Status: 400 Bad Request)
     
      - `"Internal server error"` (Status: 500 Internal Server Error)    

---

### Location

---

4) `HTTP POST {}/location/create`: return result of creating location
- **JSON Fields Requirements**:
  
   - `token`:
     
      - `accessToken`: not empty, must be valid JWT
      - `refreshToken`: not empty, must be valid JWT`
  
   - `name`: not empty

- **JSON Request Example**:
  
  ```json
  {
      "token": {
          "accessToken": "eyJhbGciOiJSUzI1NiIs...",
         "refreshToken": "eyJhbGciOiJIUzI1NiIs..."    
      },
      "name": "Location1"
  }
  ```

- **Json Response Example (Status: 200 OK)**:
  
  ```json
  
  ```

- **Json Response Example (Status: 400 Bad Request)**:
  
  ```json
  {
    "errors": [
        "Invalid token"    
      ]      
  }
  ```
  
   - All types of error messages:
     
      - `"Access token cannot be empty"` (Status: 400 Bad Request)
     
      - `"Refresh token cannot be empty"` (Status: 400 Bad Request)
     
      - `"Location name cannot be empty"` (Status: 400 Bad Request)
     
      - `"Invalid token"` (Status: 400 Bad Request)
     
      - Other (Status: 500 Internal Server Error)

---

5) `HTTP POST {}/location/list`: return list of locations
- **JSON Fields Requirements**:
  
   - `accessToken`: not empty, must be valid JWT
  
   - `refreshToken`: not empty, must be valid JWT

- **JSON Request Example**:
  
  ```json
  {
      "accessToken": "eyJhbGciOiJSUzI1NiIs...",
     "refreshToken": "eyJhbGciOiJIUzI1NiIs..."        
  }
  ```

- **Json Response Example (Status: 200 OK)**:
  
  ```json
  [
      {
          "id": 1,
          "name": "123"
      },
      {
          "id": 2,
          "name": "adsf"
      },
      {
          "id": 3,
          "name": "asdfff"
      }
  ]
  ```

- **Json Response Example (Status: 400 Bad Request)**:
  
  ```json
  {
    "errors": [
        "Invalid token"    
    ]      
  }
  ```
  
   - All types of error messages:
     
      - `"Access token cannot be empty"` (Status: 400 Bad Request)
     
      - `"Refresh token cannot be empty"` (Status: 400 Bad Request)
     
      - `"Invalid token"` (Status: 400 Bad Request)
     
      - Other (Status: 500 Internal Server Error)

---

6. `HTTP POST {}/location/edit`: return list of locations
- **JSON Fields Requirements**:
  
   - `token`:
     
      - `accessToken`: not empty, must be valid JWT
     
      - `refreshToken`: not empty, must be valid JWT
  
   - `location`:
     
      - `id`: not null, greater than zero
     
      - `name`: not empty

- **JSON Request Example**:
  
  ```json
  {
      "token": {
          "accessToken": "eyJhbGciOiJSUzI1NiIs...",
          "refreshToken": "eyJhbGciOiJIUzI1NiIs..."    
      },
      "location": {
          "id": "9",
          "name": "7773"
      }        
  }
  ```

- **Json Response Example (Status: 200 OK)**:
  
  ```json
  
  ```

- **Json Response Example (Status: 400 Bad Request)**:
  
  ```json
  {
    "errors": [
        "Invalid token"    
    ]      
  }
  ```
  
   - All types of error messages:
     
      - `"Access token cannot be empty"` (Status: 400 Bad Request)
     
      - `"Refresh token cannot be empty"` (Status: 400 Bad Request)
     
      - `"Invalid token"` (Status: 400 Bad Request)
     
      - Other (Status: 500 Internal Server Error)

---

7. `HTTP DELETE {}/location/delete`: return result of deleting location
- **JSON Fields Requirements**:
  
   - `token`:
     
      - `accessToken`: not empty, must be valid JWT
     
      - `refreshToken`: not empty, must be valid JWT
  
   - `id`: not null, greater than zero

- **JSON Request Example**:
  
  ```json
  {
      "token": {
          "accessToken": "eyJhbGciOiJSUzI1NiIs...",
          "refreshToken": "eyJhbGciOiJIUzI1NiIs..."    
      },
      "id": "3"
  }
  ```

- **Json Response Example (Status: 200 OK)**:
  
  ```json
  
  ```

- **Json Response Example (Status: 400 Bad Request)**:
  
  ```json
  {
    "errors": [
        "Invalid token"    
      ]      
  }
  ```
  
   - All types of error messages:
     
      - `"Access token cannot be empty"` (Status: 400 Bad Request)
     
      - `"Refresh token cannot be empty"` (Status: 400 Bad Request)
     
      - `"Location id cannot be empty"` (Status: 400 Bad Request)
     
      - `"Location id must be greater than zero"` (Status: 400 Bad Request)
     
      - `"Invalid token"` (Status: 400 Bad Request)
     
      - Other (Status: 500 Internal Server Error)

---

### Room

---

8. `HTTP POST {}/room/create`: return result of creating room
- **JSON Fields Requirements**:
  
   - `token`:
     
      - `accessToken`: not empty, must be valid JWT
     
      - `refreshToken`: not empty, must be valid JWT
  
   - `locationId`: not null, greater than zero
  
   - `name`: not blank

- **JSON Request Example**:
  
  ```json
  {
      "token": {
          "accessToken": "eyJhbGciOiJSUzI1NiIs...",
          "refreshToken": "eyJhbGciOiJIUzI1NiIs..."    
      },
      "locationId": "3",
      "name": "Room1"
  }
  ```

- **Json Response Example (Status: 200 OK)**:
  
  ```json
  
  ```

- **Json Response Example (Status: 400 Bad Request)**:
  
  ```json
  {
    "errors": [
        "Invalid token"    
      ]      
  }
  ```
  
   - All types of error messages:
     
      - `"Access token cannot be empty"` (Status: 400 Bad Request)
     
      - `"Refresh token cannot be empty"` (Status: 400 Bad Request)
     
      - `"Location id cannot be empty"` (Status: 400 Bad Request)
     
      - `"Location id must be greater than zero"` (Status: 400 Bad Request)
     
      - `"Room name cannot be empty"` (Status: 400 Bad Request)
     
      - `"Invalid token"` (Status: 400 Bad Request)
     
      - `"Location does not exist"` (Status: 400 Bad Request)
     
      - Other (Status: 500 Internal Server Error)

---

9. `HTTP POST {}/room/list`: return list of rooms
- **JSON Fields Requirements**:
  
   - `token`:
     
      - `accessToken`: not empty, must be valid JWT
     
      - `refreshToken`: not empty, must be valid JWT
  
   - `locationId`: not null, greater than zero

- **JSON Request Example**:
  
  ```json
  {
      "token": {
          "accessToken": "eyJhbGciOiJSUzI1NiIs...",
          "refreshToken": "eyJhbGciOiJIUzI1NiIs..."    
      },
      "locationId": "3"
  }
  ```

- **Json Response Example (Status: 200 OK)**:
  
  ```json
  [
      {
          "id": 4,
          "name": "Room5"
      },
      {
          "id": 5,
          "name": "Room6"
      }
  ]
  ```

- **Json Response Example (Status: 400 Bad Request)**:
  
  ```json
  {
    "errors": [
        "Invalid token"    
      ]      
  }
  ```
  
   - All types of error messages:
     
      - `"Access token cannot be empty"` (Status: 400 Bad Request)
     
      - `"Refresh token cannot be empty"` (Status: 400 Bad Request)
     
      - `"Location id cannot be empty"` (Status: 400 Bad Request)
     
      - `"Location id must be greater than zero"` (Status: 400 Bad Request)
     
      - `"Invalid token"` (Status: 400 Bad Request)
     
      - `"Location does not exist"` (Status: 400 Bad Request)
     
      - Other (Status: 500 Internal Server Error)

---

10. `HTTP PUT {}/room/edit`: return result of editing room
- **JSON Fields Requirements**:
  
   - `token`:
     
      - `accessToken`: not empty, must be valid JWT
     
      - `refreshToken`: not empty, must be valid JWT
  
   - `room`: 
     
      - `id`: not null, greater than zero
     
      - `name`: not empty

- **JSON Request Example**:
  
  ```json
  {
      "token": {
          "accessToken": "eyJhbGciOiJSUzI1NiIs...",
          "refreshToken": "eyJhbGciOiJIUzI1NiIs..."    
      },
      "room": {
          "id": "5",
          "name": "Room5"
      }
  }
  ```

- **Json Response Example (Status: 200 OK)**:
  
  ```json
  
  ```

- **Json Response Example (Status: 400 Bad Request)**:
  
  ```json
  {
    "errors": [
        "Invalid token"    
      ]      
  }
  ```
  
   - All types of error messages:
     
      - `"Access token cannot be empty"` (Status: 400 Bad Request)
     
      - `"Refresh token cannot be empty"` (Status: 400 Bad Request)
     
      - `"Room id cannot be empty"` (Status: 400 Bad Request)
     
      - `"Room id must be greater than zero"` (Status: 400 Bad Request)
     
      - `"Invalid token"` (Status: 400 Bad Request)
     
      - `"Location does not exist"` (Status: 400 Bad Request)
     
      - Other (Status: 500 Internal Server Error)

---

11. `HTTP DELETE {}/room/delete`: return result of deleting room
- **JSON Fields Requirements**:
  
   - `token`:
     
      - `accessToken`: not empty, must be valid JWT
     
      - `refreshToken`: not empty, must be valid JWT
  
   - `id`: not null, greater than zero

- **JSON Request Example**:
  
  ```json
  {
      "token": {
          "accessToken": "eyJhbGciOiJSUzI1NiIs...",
          "refreshToken": "eyJhbGciOiJIUzI1NiIs..."    
      },
      "id": "5"
  }
  ```

- **Json Response Example (Status: 200 OK)**:
  
  ```json
  
  ```

- **Json Response Example (Status: 400 Bad Request)**:
  
  ```json
  {
    "errors": [
        "Invalid token"    
      ]      
  }
  ```
  
   - All types of error messages:
     
      - `"Access token cannot be empty"` (Status: 400 Bad Request)
     
      - `"Refresh token cannot be empty"` (Status: 400 Bad Request)
     
      - `"Room id cannot be empty"` (Status: 400 Bad Request)
     
      - `"Room id must be greater than zero"` (Status: 400 Bad Request)
     
      - `"Invalid token"` (Status: 400 Bad Request)
     
      - Other (Status: 500 Internal Server Error)

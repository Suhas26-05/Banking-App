# 🏦 Banking App

The **Banking App** is a Spring Boot RESTful application that allows basic account management operations.  
It follows a clean layered architecture with Controllers, Services, Repositories, DTOs, and Entities.  

With this app, users can:
- Create and manage bank accounts
- Deposit and withdraw money
- Retrieve account details
- View all accounts
- Delete accounts
- View all Transaction

---

## 📂 File Structure
```
Banking_App/
│── src/main/java/com/example/Banking_App/
│   │── BankingAppApplication.java          # Main Spring Boot app
│   │
│   ├── controller/
│   │   └── AccountController.java          # REST API endpoints
│   │
│   ├── dto/
│   │   └── AccountDto.java                 # Data Transfer Object
│   │
│   ├── entity/
│   │   ├── Account.java                    # Account Entity
│   │   └── Transaction.java                # Transaction Entity
│   │
│   ├── mapper/
│   │   └── AccountMapper.java              # DTO ↔ Entity converter
│   │
│   ├── repository/
│   │   ├── AccountRepository.java          # JPA Repository
│   │   └── TransactionRepository.java      # JPA Repository
│   │
│   ├── services/
│   │   └── AccountServices.java            # Interface
│   │
│   └── services/impl/
│       └── AccountServicesImpl.java        # Service Implementation
│
└── src/main/resources/
    └── application.properties              # DB Config

````

---

## ⚙️ Technologies Used
- **Java 17+**
- **Spring Boot 3.x**
- **Spring Data JPA (Hibernate)**
- **Maven** (build tool)
- **Lombok** (boilerplate reduction)
- **H2 / MySQL** (database – configurable)
- **REST APIs with JSON**

---

## 🚀 Features
- ➕ Create a new bank account
- 📄 Retrieve account details by ID
- 💰 Deposit money into an account
- 💸 Withdraw money (with insufficient funds check)
- 📋 Get all accounts
- 🗑️ Delete an account
- 📜 View all transactions of an account
- 📈 Apply monthly interest to all accounts
- ✅ Layered architecture with DTOs & Mappers

---

## 🔧 Installation / Setup
### 1. Clone the repository
```bash
git clone https://github.com/username/Banking-App.git
cd Banking-App
````

### 2. Build and run with Maven

```bash
mvn spring-boot:run
```

### 3. Access the application

The app will start on **[http://localhost:8080](http://localhost:8080)**

---

## ⚙️ Configuration

Edit `src/main/resources/application.properties` to set your database:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bankingdb
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

*(You can also switch to in-memory H2 database for testing.)*

---

## 📡 API Endpoints

### ➕ Create Account

`POST /api/accounts`

```json
{
  "accountHolderName": "Alice",
  "balance": 1000
  "accountType": "SAVINGS"
}
```

### 📄 Get Account by ID

`GET /api/accounts/{id}`

### 💰 Deposit

`POST /api/accounts/{id}/deposit`

```json
{
  "amount": 500
}
```

### 💸 Withdraw

`POST /api/accounts/{id}/withdraw`

```json
{
  "amount": 200
}
```

### 📋 Get All Accounts

`GET /api/accounts`

### 🗑️ Delete Account

`DELETE /api/accounts/{id}`

###📜 Get Transactions for an Account

`GET /api/accounts/{id}/transactions`

###📈 Apply Monthly Interest

`POST /api/accounts/apply-interest`

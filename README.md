# BankWalletApp
## Description
Rest service for conducting banking operations with a bank wallet: depositing and withdrawing money from an account.
This project is made as a test task for JavaCode using Spring Boot, Spring Data JPA, PostgreSQL, Liquibase, Docker.
## Before use

To use these files, you must first have the following installed:

- [Docker](https://docs.docker.com/engine/installation/)
- [docker-compose](https://docs.docker.com/compose/install/)

## How to use

The following steps will run a local instance of the BankWalletApp using the default configuration file (`docker-compose.yml`):

1. Clone this repository.
2. Change directory into the root of the project.
3. Run the `docker-compose up` command.

```bash
git clone https://github.com/katemerek/BankWalletApp.git
cd  BankWalletApp/
docker compose up
```

After the Server has started, you can open the BankWalletApp in your browser or test the API with Postman to perform various operations described below.
The app will start running at <http://localhost:8080>.
> ⚠️Please note that when you start the application, test data is added via migrations (12 wallets and 24 operations).
### REST APIs
This collection of requests is intended to interact with the BankWalletApp API.
#### Wallet
- Get all wallets

  http://localhost:8080/api/v1/wallets

  This request displays all wallets in table wallets.
- Add a new wallets

  http://localhost:8080/api/v1/add_wallets

  This request adds a new wallet to the table.
```
{
    "balance": 1000
}
```
- Get wallet by id

  http://localhost:8080/api/v1/wallets/{walletId}

  This request display the wallet by its UUID (walletId) in table wallets.

#### Operation
- Get all operations

  http://localhost:8080/api/v1/operations

  This request displays all operations in table operations.
- Add a new operation

  http://localhost:8080/api/v1/wallet

  This request adds a new operation to the table. To enter the type use: DEPOSIT or WITHDRAW.
```
    "walletId": "275a2416-e7f2-47bf-afdc-03645b3a83ec",
    "type": "DEPOSIT",
    "amount": 250
}
```

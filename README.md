# Java AWT Banking System

This is a simple Banking System implemented in Java using the AWT (Abstract Window Toolkit) library. The system allows users to perform various banking operations like creating a new account, depositing money, withdrawing money, checking account balance, and viewing transaction history.

## Features

- **Create a New Account**: Allows users to create a new account by entering their name, gender, date of birth, phone number, and email.
- **Deposit Money**: Allows users to deposit money into their account.
- **Withdraw Money**: Allows users to withdraw money from their account.
- **Check Balance**: Allows users to view the balance in their account.
- **Transaction History**: Displays a history of all transactions (deposit/withdraw) for the selected account.
- **Phone Number Validation**: Ensures that the phone number entered is exactly 10 digits.
- **Account Number Generation**: Each account is assigned a unique 12-digit account number.
- **Total Account Count**: Displays the total number of accounts created.

## Requirements

- **Java Development Kit (JDK)**: This project requires JDK 8 or higher to compile and run.

## How to Run

1. **Clone the repository**:


2. **Navigate to the project directory**:


3. **Compile the Java files**:


4. **Run the application**:


The application will open a graphical user interface where you can interact with the banking system.

## How It Works

### Create a New Account
- Click the "Open New Account" button and fill in the required details:
- Name
- Gender (Male, Female, or Other) (Using radio buttons)
- Date of Birth (Using calendar drop-downs)
- Phone Number (Validated to be exactly 10 digits)
- Email Address
- The account number will be generated automatically (12 digits).

### Deposit/Withdraw Money
- Once the account is created, you can deposit or withdraw money by entering the amount in the provided field and clicking the corresponding button.

### View Account Info
- Clicking "Account Info" displays the account details including transaction history.

### Check Balance
- The "Check Balance" button will display the current balance in the selected account.

## Sample UI

The user interface consists of the following components:

- **Text Fields**: To input the name, phone number, email, and amount for deposit/withdrawal.
- **Radio Buttons**: For selecting gender (Male, Female, Other).
- **Choice Dropdowns**: For selecting date of birth (day, month, and year).
- **Buttons**: For various operations like opening a new account, depositing, withdrawing, checking balance, and viewing account info.
- **Text Area**: To display output such as transaction history, balance, or account details.

## Future Improvements

- **Persistent Data Storage**: The system currently does not save the accounts to a file or database. You can enhance it by adding file/database integration.
- **Enhance Security**: Add features like PIN/password authentication for account access.
- **More Complex Operations**: Implement features like fund transfer between accounts, account statement generation, etc.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

## Author

- **Your Name**: [Your Profile or Contact Info]



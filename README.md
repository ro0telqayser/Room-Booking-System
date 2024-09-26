Here’s a `README.md` file for the Room Booking System application:

---

# Room Booking System

This is a console-based Room Booking System application written in Java. It allows users to reserve, search for, and cancel room reservations. The application also features password encryption and decryption for reserving and canceling rooms.

## Features

- **Reserve a Room**: Allows users to reserve a room by entering basic details and an optional encrypted password.
- **Search & Reserve**: Users can search for available rooms by type, price, and other criteria, then reserve a room.
- **Cancel Reservation**: Users can cancel a reservation by providing the correct password.
- **Password Encryption & Decryption**: Supports encrypting passwords using Caesar Cipher encryption, offering additional security for room reservations.
- **Data Persistence**: Room details and reservation information are saved to a file (`rooms.txt`) for persistence across program runs.

## Prerequisites

To run this application, ensure you have the following:

- **Java**: Make sure you have Java installed (version 8 or later).
- **Git**: If you want to clone the repository, install Git.
- **File Access**: The application saves room data to a text file (`rooms.txt`). Ensure you have read/write permissions to the directory.

## Running the Application

1. **Clone the Repository** (if applicable):
   ```bash
   git clone https://github.com/your-username/your-repository.git
   cd your-repository
   ```

2. **Compile the Application**:
   If using a terminal, navigate to the project folder and compile the code with:
   ```bash
   javac Console.java
   ```

3. **Run the Application**:
   Once compiled, run the application:
   ```bash
   java Console
   ```

4. **Follow On-Screen Prompts**:
   The application will guide you through various operations, including room reservation, search, and cancellation.

## Application Flow

- **Main Menu**: After starting the application, you will see the main menu with options:
  - Reserve Any Room
  - Search & Reserve Room
  - Cancel Room
  - Quit the application

- **Reserving a Room**:
  1. Select an available room.
  2. Provide an email and optionally encrypt your password.
  3. The room will be reserved if available.

- **Searching for Rooms**:
  1. Search by room type (Single, Double, Suite), price, and whether the room has a balcony or lounge.
  2. Choose a room from the search results to reserve.

- **Canceling a Room Reservation**:
  1. Enter your email and the room number.
  2. Provide the password used during reservation.
  3. If the password matches, the reservation will be canceled.

- **Password Encryption**: When reserving or canceling a room, users can choose to encrypt/decrypt their password using a simple Caesar Cipher.

## Room Data File

The application uses a file (`rooms.txt`) to store room details. Each room is saved in the following format:

```
[Room Number] [Room Type] [Price] [Has Balcony] [Has Lounge] [Email] [Password] [Additional Data]
```

Make sure this file is accessible and correctly formatted for the application to run smoothly.

## Password Encryption and Decryption

The application uses a Caesar Cipher for encrypting and decrypting passwords. You will be prompted to specify:
1. The number of shifts (for the Caesar Cipher).
2. The direction (left or right) for the shift.
   
Once set, the password is encrypted and saved with the reservation. You can decrypt the password when canceling a reservation.

## Example Output

Here is an example of the output when running the application:

```
-- Room Booking System --

-- MAIN MENU --
1 - Reserve Any Room
2 - Search & Reserve Room
3 - Cancel Room
Q - Quit
Pick: 1

-- AVAILABLE ROOMS --
Room No: 101, Type: Single, Price: $100.0, Balcony: true, Lounge: false
...

Reserve Room No: 101
eMail: user@example.com
Do you want to encrypt your password (Y/N): Y
How many shifts: 3
Choose the direction (left or right): right
Give me your password to encrypt: secretpass

This is your Encrypted Password (Save it in a safe place): vfuuhwkdvv
Victory!
```

## Error Handling

If any error occurs during input or file access, the application will provide meaningful error messages. Common issues include:
- Incorrect file path for `rooms.txt`.
- Providing incorrect or missing room details.

## Future Improvements

- **Room Filtering**: Add more detailed filtering options for room search.
- **Room Availability**: Automatically show available rooms before making a reservation.
- **Improved Encryption**: Implement stronger encryption algorithms for password security.


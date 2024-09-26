import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;


/**
 * The `Console` class is the main entry point for the Room Booking System application.
 * It provides a command-line interface for users to perform various operations, such as:
 * - Reserving a room
 * - Searching and reserving a room
 * - Cancelling a room reservation
 * - Performing encryption and decryption of passwords
 *
 * The class manages the loading and saving of room data from/to a file, and handles the
 * user input and output through the console.
 */
public class Console {
	private static final String ROOMS = "../rooms.txt";
	private static final Scanner S = new Scanner(System.in);

	// roomNo's are not logically progressive so we use a hash map
	private static final HashMap<Integer, Room> rooms = new HashMap<Integer, Room>();

	public static void main(String[] args) throws Exception {
		loadData();

		String choice = "";

		System.out.println("-- Room Booking System --");

		// gui spacing
		System.out.println();

		do {
			System.out.println("-- MAIN MENU --");
			System.out.println("1 - Reserve Any Room");
			System.out.println("2 - Search & Reserve Room");
			System.out.println("3 - Cancel Room");
			System.out.println("4 - ToDo");
			System.out.println("Q - Quit");
			System.out.print("Pick : ");

			choice = S.next().toUpperCase();

			switch (choice) {
				case "1" : {
					reserveRoom();
					break;
				}
				case "2" : {
					searchRoom();
					break;
				}
				case "3" : {
					cancelRoom();
					break;
				}
				case "4" : {

					break;
				}
			}
		} while (!choice.equals("Q"));

		// always advisable to close scanners
		S.close();

		saveData();

		System.out.println("Bye Bye :)");

		// System.exit(0);
	}

	private static void loadData() throws Exception {
		Scanner s;
		
		// setup for seats hash map
		s = new Scanner(new FileReader(ROOMS));

		while (s.hasNext()) {
			
			String[] strs = s.nextLine().split(" ");

			rooms.put(Integer.parseInt(strs[0]), new Room(Integer.parseInt(strs[0]), strs[1], Double.parseDouble(strs[2]), Boolean.parseBoolean(strs[3]), Boolean.parseBoolean(strs[4]), strs[5], strs[6],strs[7]));
		}

		// always advisable to close scanners
		s.close();
	}
	private static String encryptionndecryption() {
        // Encryption method


        System.out.println("How many shifts:");
        int shifts = S.nextInt();

        System.out.println("Choose the direction as either left to right (left) or right to left (right):");
        String direction = S.next();

		System.out.println("Choose the direction as either left to right (left) or right to left (right):");
		String direction1 = S.next();
        System.out.println("Give me your password to encrypt:");
        String text = S.next();

        String ciphertencryption = passwordencrypted(text, shifts, direction);
		System.out.println("This is your Encrypted Password (Save it in a save place): " + ciphertencryption);
		String cipherdecryption = decrypt(text, shifts, direction);
		System.out.println("This is your Decrypt Password (Save it in a save place): " + cipherdecryption);
		return cipherdecryption;
    }
	public static String decrypt(String text, int shifts, String direction) {
		// StringBuilder to store the decrypted result
		StringBuilder result = new StringBuilder();
		// Loop through each character in the input text
		for (int i = 0; i < text.length(); i++) {
			// Retrieve the current character
			char ch = text.charAt(i);
			// Check if the character is a letter
			if (Character.isLetter(ch)) {
				// Perform Caesar Cipher shift on letters for decryption
				char shifted = (char) (ch - shifts);
				// Ensure the result stays within the valid range of alphabetic characters
				if (Character.isLowerCase(ch) && shifted < 'a') {
					shifted = (char) (shifted + 26);
				} else if (Character.isUpperCase(ch) && shifted < 'A') {
					shifted = (char) (shifted + 26);
				}
				// Append the shifted character to the result
				result.append(shifted);
			} else {
				// If the character is not a letter, append it unchanged
				result.append(ch);
			}
		}
		// Convert the StringBuilder to String and return the decrypted result
		return result.toString();
	}
	
	public static String passwordencrypted(String text, int shifts, String direction) {
		// Encryption logic
		StringBuilder result = new StringBuilder();
	
		for (int i = 0; i < text.length(); i++) {
			// Retrieve the current character
			char ch = text.charAt(i);
	
			// Check if the character is a letter
			if (Character.isLetter(ch)) {
				// Perform Caesar Cipher shift on letters
				char shifted = (char) (ch + shifts);
	
				// Ensure the result stays within the valid range of alphabetic characters
				if (Character.isLowerCase(ch) && shifted > 'z') {
					shifted = (char) (shifted - 26);
				} else if (Character.isUpperCase(ch) && shifted > 'Z') {
					shifted = (char) (shifted - 26);
				}
				// Append the shifted character to the result
				result.append(shifted);
			} else {
				// If the character is not a letter, append it unchanged
				result.append(ch);
			}
		}
	
		return result.toString();
	}
	
	
	private static void reserveRoom() {
		System.out.println("-- AVAILABLE ROOMS --");

		boolean areRooms = false;

		for (Room room : rooms.values()) {
			if (!room.isReserve()) {
				System.out.println(room.toString());
				areRooms = true;
			}
		}

		aquireRoom(areRooms);
	}

	private static void searchRoom() {
		System.out.println("-- SEARCH ROOMS --");

		boolean areRooms = false;

		// we are not validating inputs :(
		System.out.print("Type [Single|Double|Suite] : ");
		String type = S.next().toUpperCase();

		System.out.print("Max Price : ");
		double price = S.nextDouble();

		System.out.print("Balcony [Y|N] : ");
		boolean balcony = S.next().toUpperCase().equals("Y") ? true : false;

		System.out.print("Lounge [Y|N] : ");
		boolean lounge = S.next().toUpperCase().equals("Y") ? true : false;

		// only interested in 3 or more matching criterion
		for (int match = 4; match >= 2; match--) {
			areRooms = false;

			System.out.println("Showing Rooms Matching " + match + " Requirements !");

			for (Room room : rooms.values()) {
				if (!room.isReserve()) {
					if (room.checkMatch(type, price, balcony, lounge) == match) {
						System.out.println(room.toString());
						areRooms = true;
					}
				}
			}

			if (areRooms) {
				break;
			}
			else {
				System.out.println("No Rooms Matching : " + match + " Requirements !");
			}
		}

		aquireRoom(areRooms);
	}

	private static void aquireRoom(boolean areRooms) {
		if (areRooms) {
            System.out.print("Reserve Room No : ");
            int no = S.nextInt();

            Room roomToReserve = rooms.get(no);


            if (!roomToReserve.isReserve()) {
                System.out.print("eMail : ");
                String eMail = S.next();
				String ciphertext = "";
                // Prompt user to set a 4-digit password during reservation
				System.out.print("Do you want to encrpt your password (Y/N)");
                String answer = S.next();
				if (answer.equalsIgnoreCase("Y")){
					ciphertext = encryptionndecryption();
					try {
						roomToReserve.reserveRoom(eMail, ciphertext);
						System.out.println("Victory !");
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				} else if (answer.equalsIgnoreCase("N")){
					String password = readPassword();
					try {
						// Reserve room with the provided email and password
						roomToReserve.reserveRoom(eMail, password);
	
						System.out.println("Victory !");
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
				try {
					// Reserve room with the provided email and encrypted password
					roomToReserve.reserveRoom(eMail, ciphertext);
					System.out.println("Victory !");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
                
            } else {
                System.out.println("Room is already reserved.");
            }
        } else {
            System.out.println("No Available Rooms !");
        }
    }
	private static String readPassword() {
		java.io.Console console = System.console();
		if (console != null) {
			char[] passwordChars = console.readPassword("Enter Password: ");
			return new String(passwordChars);
		} else {
			// Fallback for environments where System.console() might be null (e.g., IDEs)
			return S.next();
		}
	}
	

	private static void cancelRoom() {
		System.out.print("eMail : ");
		String eMail = S.next();

		System.out.println("-- " + eMail + " RESERVE ROOMS --");

		boolean areRooms = false;

		for (Room room : rooms.values()) {
			if (room.isReserve()) {
				if (eMail.equals(room.getMail())) {
					System.out.println(room.toString());
					areRooms = true;
				}
			}
		}

		if (areRooms) {
			System.out.print("Cancel Room No : ");
			int no = S.nextInt();

			Room roomToCancel = rooms.get(no);

            if (roomToCancel.isReserve()) {
                System.out.print("Enter 4-digit Password for Cancellation: ");
                String password = S.next();
                // Check if the entered password matches the room's password
                if (password.equals(roomToCancel.getPassword())) {
                	
                    try {
                        // Cancel reservation only if the password is correct
                        roomToCancel.cancelRoom(roomToCancel.getMail());

                        System.out.println("Victory !");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    System.out.println("Incorrect Password. Cancellation failed.");
                }
            } else {
                System.out.println("No Reserve Rooms !");
            }
        } 
    }
	private static void saveData() throws Exception {
		// java's try-with-resource statement
		try (PrintWriter pw = new PrintWriter(new FileWriter(ROOMS))) {
			for (Room room : rooms.values()) {
				pw.println(room.toSaveString());
			}
		}

	
//		PrintWriter pw = new PrintWriter(new FileWriter(ROOMS));
//
//		for (Room room : rooms) {
//			pw.println(room.toSaveString());
//		}
//
//		pw.close();
	}
}

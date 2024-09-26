
public class Room {
	private int no;
	private String type;
	private double price;
	private boolean balcony;
	private boolean lounge;
	private String eMail;
	private String Password;
	private int shifts;
	private String text;
	private String encryption;

	// constructors must do everything as set methods are not necessary here
	public Room(int no, String type, double price, boolean balcony, boolean lounge, String eMail, String Password, String encryption) {
		this.no = no;
		this.type = type;
		this.price = price;
		this.balcony = balcony;
		this.lounge = lounge;
		this.eMail = eMail;
		this.Password = Password;
		this.encryption = encryption;
	}

	@Override
	public String toString() {
		// ternary operators kick ass in string formatting
		return String.format("No:%02d  Type:%s  Price:%.2f  isBalcony:%s  isLounge:%s  isReserve:%s  %s",
				no, type, price, (balcony ? "Y" : "N"), (lounge ? "Y" : "N"), (isReserve() ? "Y" : "N"), (isReserve() ? "eMail:" + eMail : ""));
	}

	// not sure to have this class doing this or have the calling class using get methods
	public String toSaveString() {
		return no + " " + type + " " + price + " " + balcony + " " + lounge + " " + eMail;
	}

	public boolean isReserve() {
		return !eMail.equals("free") ? true : false;

		
//		if (eMail != "") {
//			return true;
//		}
//
//		return false;
//		
	}

	public void reserveRoom(String eMail, String Password) throws Exception {
		// reserve check may be unnecessary but is best practice
		if (!isReserve()) {
			this.eMail = eMail;
			this.Password = Password;
		}
		else {
			throw new Exception("Already Reserve :(");
		}
	}

	public void cancelRoom(String eMail) throws Exception {
		if (eMail.equals(this.eMail)) {
			this.eMail = "free";
		}
		else {
			throw new Exception("eMails Don't Match :(");
		}
	}


	public int checkMatch(String type, double price, boolean balcony, boolean lounge) {
		// variable to tally up matching data
		int matching = 0;

		if (this.type.equalsIgnoreCase(type)) {
			matching++;
		}

		if (this.price <= price) {
			matching++;
		}

		if (this.balcony == balcony) {
			matching++;
		}

		if (this.lounge == lounge) {
			matching++;
		}

		return matching;
	}
	
	

	// only get methods are necessary
	public int getNo() {
		return no;
	}

	public String getType() {
		return type;
	}

	public double getPrice() {
		return price;
	}
	public String getencryption(){
		return encryption;
	}

	// predicate accessors use 'is' not 'get'
	public boolean isBalcony() {
		return balcony;
	}

	public boolean isLounge() {
		return lounge;
	}

	public String getMail() {
		return eMail;
	}
	public String getPassword() {
		return Password;
	}
}

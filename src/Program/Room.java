package Program;
public class Room extends RoomType {

	private int roomNumber;
	private boolean isAvailable;
	private int tinyIntTranslate;

	public Room() {
		super();
	}

	public String getRoomCategoryName() {
		return category;
	}

	public void setRoomCategoryName(String roomCategoryName) {
		this.category = roomCategoryName;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean available) {
		isAvailable = available;
	}

	public int getTinyIntTranslate() {
		int temp;
		if (this.isAvailable) {
			temp = 1;
		} else {
			temp = 0;

		}
		return temp;
	}

	public void setTinyIntToIsAvailable(int tinyIntTranslate) {
		this.tinyIntTranslate = tinyIntTranslate;
		if (tinyIntTranslate == 1) {
			this.isAvailable = true;
		} else if (tinyIntTranslate == 0) {
			this.isAvailable = false;
		}

	}

	@Override
	public String toString() {
		return "Room{" + "roomNumber=" + roomNumber + ", Category=" + category + ", isAvailable=" + isAvailable + '}';
	}
}

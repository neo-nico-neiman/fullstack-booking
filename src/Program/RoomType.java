package Program;

public class RoomType {

    protected String category;
    protected int maxGuestAAllowance;
    protected double pricePerNight;
    protected String imageURL;
    private String roomDetails;
    private String roomDescription;
   
    public RoomType(){}
    
    public RoomType(String category, int maxGuestAAllowance, double pricePerNight, String imgURL, String roomInfo, String roomDescription) {
    	this.category=category;
    	this.maxGuestAAllowance=maxGuestAAllowance;
    	this.pricePerNight=pricePerNight;
    	this.imageURL=imgURL;
    	this.roomDetails= roomInfo;
    }
    
   
	

	public String getCategory() {
        return category;
    }

    private void setCategory(String category) {
        this.category = category;
    }

    public int getMaxGuestAAllowance() {
        return maxGuestAAllowance;
    }

    private void setMaxGuestAAllowance(int maxGuestAAllowance) {
        this.maxGuestAAllowance = maxGuestAAllowance;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    private void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }
    
    public void setRoomType(String category, int maxGuestAAllowance, double pricePerNight, String imgURL, String roomDescription, String roomDetails) {
    	this.category=category;
    	this.maxGuestAAllowance=maxGuestAAllowance;
    	this.pricePerNight=pricePerNight;
    	this.imageURL=imgURL;
    	this.roomDetails= roomDetails;
    	this.roomDescription= roomDescription;
    }
    
    public void setImgURL (String imgURL) {
    	this.imageURL=imgURL;
    }
    
    public String getImgURL() {
    	return imageURL;
    }
    
    public String getRoomDetails() {
		return roomDetails;
	}

	public void setRoomDetails(String roomDetails) {
		this.roomDetails = roomDetails;
	}

	public String getRoomDescription() {
		return roomDescription;
	}

	public void setRoomDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}
    
    @Override
    public String toString() {
        return "RoomType{" +
                "category='" + category + '\'' +
                ", maxGuestAAllowance=" + maxGuestAAllowance +
                ", pricePerNight=" + pricePerNight + "imgURL: "+ imageURL+ " Room info: "+ roomDetails+" "+roomDescription+
                '}';
    }
}

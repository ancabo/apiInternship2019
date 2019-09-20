package entities;

public class Order {
	
	public static final String ORDER_ID = "id";
	public static final String PET_ID = "petId";
	public static final String STATUS = "status";
	

	private int id;
	private String petId;
	private String status;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPetId() {
		return petId;
	}
	public void setPetId(String pet) {
		this.petId = pet;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}

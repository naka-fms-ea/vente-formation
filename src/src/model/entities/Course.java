package model.entities;

public class Course {

	private int id;
	private String name;
	private String description;
	private int duration;
	private String type;
	private double price;
	
	/**
	 * @param id
	 * @param name
	 * @param description
	 * @param duration
	 * @param type
	 * @param price
	 */
	public Course(int id, String name, String description, int duration, String type, double price) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.duration = duration;
		this.type = type;
		this.price = price;
	}
	
	public Course(String name, String description, int duration, String type, double price) {
		super();
		this.name = name;
		this.description = description;
		this.duration = duration;
		this.type = type;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return this.id + " - " + this.name + " - " + this.description + " - " + this.duration + " - " + this.type + " - " + this.price;
	}
	
	
	
}

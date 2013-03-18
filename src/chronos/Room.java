package chronos;

public class Room implements Comparable<Room> {
	private String name;
	private String description;
	private int capacity;

	public Room(String name, int capacity, String description) {
		this.name = name;
		this.capacity = capacity;
		this.description = description;
	}

	public int getCapacity() {
		return capacity;
	}

	public String getName() {
		return name;
	}

	@Override
	public int compareTo(Room o) {
		return capacity - o.getCapacity();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return ("Room " + this.name + " with capacity " + this.capacity);
	}
}

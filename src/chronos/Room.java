package chronos;

public class Room implements Comparable<Room> {
	String name;
	int capacity;

	public Room(String name, int capacity) {
		this.name = name;
		this.capacity = capacity;
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
}

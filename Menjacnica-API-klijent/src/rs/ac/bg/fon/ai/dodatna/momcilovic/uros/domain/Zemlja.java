package rs.ac.bg.fon.ai.dodatna.momcilovic.uros.domain;

public class Zemlja {
	
	private String name;
	private String id;
	
	
	public Zemlja(String name, String id) {
		super();
		this.name = name;
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Zemlja [id=" + id + ", name=" + name + "]";
	}

	
	
}

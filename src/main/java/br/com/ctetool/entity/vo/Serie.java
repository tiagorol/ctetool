package br.com.ctetool.entity.vo;

public class Serie {
	
	private Long id;
	
	private String name;
	
	private int[] data;
	
	public Serie() {
		super();
	}

	public Serie(Long id, String name, int size) {
		this();
		this.id = id;
		this.name = name;
		this.data = new int[size];
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int[] getData() {
		return data;
	}

	public void setData(int[] data) {
		this.data = data;
	}
	
}

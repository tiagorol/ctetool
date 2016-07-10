package br.com.ctetool.entity.vo;

public class Serie {
	
	private String name;
	
	private int[] data;
	
	public Serie() {
		super();
	}

	public Serie(String name, int size) {
		this();
		this.name = name;
		this.data = new int[size];
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

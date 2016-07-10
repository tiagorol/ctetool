package br.com.ctetool.entity.vo;

import java.io.Serializable;
import java.util.List;

public class ResultVO implements Serializable{
	
	private static final long serialVersionUID = 7996853769436071757L;
	
	private List<Serie> series;
	
	private String[] categories;

	public List<Serie> getSeries() {
		return series;
	}

	public void setSeries(List<Serie> series) {
		this.series = series;
	}

	public String[] getCategories() {
		return categories;
	}

	public void setCategories(String[] categories) {
		this.categories = categories;
	}
	
}

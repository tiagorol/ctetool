package br.com.ctetool.entity.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResultVO implements Serializable{
	
	private static final long serialVersionUID = 7996853769436071757L;
	
	private List<Serie> series;
	
	private String[] categories;
	
	public ResultVO(List<GraphicVO> resultGraphic) {
		super();
		this.series = new ArrayList<>();
		criateListCategoria(resultGraphic);
	}

	private void criateListCategoria(List<GraphicVO> resultGraphic) {
		
		List<Integer> listCategories = new ArrayList<>();
		
		for (GraphicVO graphicVO : resultGraphic) {
			if(!listCategories.contains(graphicVO.getWorkload())){
				listCategories.add(graphicVO.getWorkload());
			}
		}
		
		Collections.sort(listCategories);
		categories = new String[listCategories.size()];
		
		int i = 0;
		for (Integer categorie : listCategories) {
			categories[i++] = String.valueOf(categorie);
		}
	}

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

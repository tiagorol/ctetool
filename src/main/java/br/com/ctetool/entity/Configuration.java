package br.com.ctetool.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Configuration implements Serializable {

	private static final long serialVersionUID = 1833837358969476244L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
    private Long id;
	
	@Column(nullable = false, length = 20)
	private String image;
	
	@Column(nullable = false, length = 20)
	private String size;
	
	@Column(name = "size_wordpress", nullable = false, length = 20)
	private String sizeWordpress;
	
	@Column(name = "agent_user", nullable = false, length = 20)
	private String agentUser;
	
	@Column(name = "host_crawler", nullable = false, length = 20)
	private String hostCrawler;
	
	@Column(nullable = false)
	private Integer rounds;
	
	@Column(nullable = false, length = 40)
	private String workloads;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getSizeWordpress() {
		return sizeWordpress;
	}

	public void setSizeWordpress(String sizeWordpress) {
		this.sizeWordpress = sizeWordpress;
	}

	public String getAgentUser() {
		return agentUser;
	}

	public void setAgentUser(String agentUser) {
		this.agentUser = agentUser;
	}

	public String getHostCrawler() {
		return hostCrawler;
	}

	public void setHostCrawler(String hostCrawler) {
		this.hostCrawler = hostCrawler;
	}

	public Integer getRounds() {
		return rounds;
	}

	public void setRounds(Integer rounds) {
		this.rounds = rounds;
	}

	public String getWorkloads() {
		return workloads;
	}

	public void setWorkloads(String workloads) {
		this.workloads = workloads;
	}
	
}

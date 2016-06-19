package br.com.ctetool.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "configuration")
public class Configuration implements Serializable {

	private static final long serialVersionUID = 1833837358969476244L;
	
	@Transient
	private static final String IPADDRESS_PATTERN = 
			"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@NotNull
	@Size(min = 3, max = 20)
	private String image;
	
	@NotNull
	@Size(min = 3, max = 20)
	private String size;
	
	@NotNull
	@Size(min = 3, max = 20)
	@Column(name = "size_wordpress")
	private String sizeWordpress;
	
	@NotNull
	@Size(min = 3, max = 20)
	@Column(name = "agent_user")
	private String agentUser;
	
	@NotNull
	@Size(min = 3, max = 20)
	@Column(name = "host_crawler")
	@Pattern(regexp = IPADDRESS_PATTERN, message = "Valor invalido")
	private String hostCrawler;
	
	@NotNull
	private Integer rounds;
	
	@NotNull
	@Size(min = 2, max = 40)
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

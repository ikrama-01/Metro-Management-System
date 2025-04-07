package com.metro.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="routes")
@NoArgsConstructor
@AllArgsConstructor
public class Routes {
	
	@Id
	private String id;
	
	@ManyToOne
	@JoinColumn(name="source_id", referencedColumnName = "id")
	private Station source;
	
	@ManyToOne
	@JoinColumn(name="destination_id", referencedColumnName = "id")
	private Station destination;
	
	private Double fare;
	
	private Integer distance;
}

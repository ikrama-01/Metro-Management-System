package com.metro.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="stations")
@NoArgsConstructor
@AllArgsConstructor
public class Station 
{
	 @Id
	 private String id;

	 private String name;

	 @Column(unique = true, nullable = false)
	 private String code;

}

package com.metro.model;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User 
{
	@Id
	private String id;
	
	private String name;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	private String password;
	
	
	private Double walletBalance;
	
	@Enumerated(EnumType.STRING)
	private Role role;
}

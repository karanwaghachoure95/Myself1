package com.example.Myself.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Setter
@Getter
@NoArgsConstructor
public class Protected {

	@Id
	private final int Id=1;
	private String username;
	private String password;
	
	
}

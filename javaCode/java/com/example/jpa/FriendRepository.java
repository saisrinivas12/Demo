package com.example.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Friend;

public interface FriendRepository extends JpaRepository<Friend, Integer>{
	

	
	
}
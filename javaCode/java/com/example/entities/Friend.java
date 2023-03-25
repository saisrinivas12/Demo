package com.example.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name = "FRIEND")
public class Friend {

	@Id
	private int friendId;
	@Value("Nothing has been done on Friend")
	private String friendName;
	@Value("Nothing has been done on salary")
	private long salary;
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Smartphone>smartPhones;
	public List<Smartphone> getSmartPhone() {
		return smartPhones;
	}
	public void setSmartPhone(List<Smartphone> smartPhone) {
		this.smartPhones = smartPhone;
	}
	public int getFriendId() {
		return friendId;
	}
	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}
	public String getFriendName() {
		return friendName;
	}
	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}
	public long getSalary() {
		return salary;
	}
	public void setSalary(long salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Friend [friendId=" + friendId + ", friendName=" + friendName + ", salary=" + salary + ", smartPhone="
				+ smartPhones + "]";
	}
	
}

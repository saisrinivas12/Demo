package com.example.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Smartphone {

	@Id
	@GeneratedValue
	private int mobileId;
	private String mobileName;
	private long mobilePrice;
   @ManyToMany(cascade = CascadeType.ALL,mappedBy = "smartPhones")
	private List<Friend> friend;
	public List<Friend> getFriends() {
		return friend;
	}
	public void setFriends(List<Friend> friends) {
		this.friend = friends;
	}
	public int getMobileId() {
		return mobileId;
	}
	public void setMobileId(int mobileId) {
		this.mobileId = mobileId;
	}
	public String getMobileName() {
		return mobileName;
	}
	public void setMobileName(String mobileName) {
		this.mobileName = mobileName;
	}
	public long getMobilePrice() {
		return mobilePrice;
	}
	public void setMobilePrice(long mobilePrice) {
		this.mobilePrice = mobilePrice;
	}
	@Override
	public String toString() {
		return "Smartphone [mobileId=" + mobileId + ", mobileName=" + mobileName + ", mobilePrice=" + mobilePrice
				+ ", friends=" + friend + "]";
	}
}

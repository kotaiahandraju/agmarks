package com.aurospaces.neighbourhood.bean;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class AnimalPanchangam {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected int SNo   = 0;
	
	protected String AnimalName ;
	
	protected String Category ;
	
	protected String Breed ;
	protected String Feeding ;
	protected String Disease ;
	protected String Vaccination ;
	
	
	protected String AtTheAge ;
	protected String VaccinationInterval ;
	protected String Dose ;
	protected String Month ;
	protected String MilkYield ;
	
	
	protected String Wool ;


	public int getSNo() {
		return SNo;
	}


	public void setSNo(int sNo) {
		SNo = sNo;
	}


	public String getAnimalName() {
		return AnimalName;
	}


	public void setAnimalName(String animalName) {
		AnimalName = animalName;
	}


	public String getCategory() {
		return Category;
	}


	public void setCategory(String category) {
		Category = category;
	}


	public String getBreed() {
		return Breed;
	}


	public void setBreed(String breed) {
		Breed = breed;
	}


	public String getFeeding() {
		return Feeding;
	}


	public void setFeeding(String feeding) {
		Feeding = feeding;
	}


	public String getDisease() {
		return Disease;
	}


	public void setDisease(String disease) {
		Disease = disease;
	}


	public String getVaccination() {
		return Vaccination;
	}


	public void setVaccination(String vaccination) {
		Vaccination = vaccination;
	}


	public String getAtTheAge() {
		return AtTheAge;
	}


	public void setAtTheAge(String atTheAge) {
		AtTheAge = atTheAge;
	}


	public String getVaccinationInterval() {
		return VaccinationInterval;
	}


	public void setVaccinationInterval(String vaccinationInterval) {
		VaccinationInterval = vaccinationInterval;
	}


	public String getDose() {
		return Dose;
	}


	public void setDose(String dose) {
		Dose = dose;
	}


	public String getMonth() {
		return Month;
	}


	public void setMonth(String month) {
		Month = month;
	}


	public String getMilkYield() {
		return MilkYield;
	}


	public void setMilkYield(String milkYield) {
		MilkYield = milkYield;
	}


	public String getWool() {
		return Wool;
	}


	public void setWool(String wool) {
		Wool = wool;
	}


	@Override
	public String toString() {
		return "AnimalPanchangam [SNo=" + SNo + ", AnimalName=" + AnimalName + ", Category=" + Category + ", Breed="
				+ Breed + ", Feeding=" + Feeding + ", Disease=" + Disease + ", Vaccination=" + Vaccination
				+ ", AtTheAge=" + AtTheAge + ", VaccinationInterval=" + VaccinationInterval + ", Dose=" + Dose
				+ ", Month=" + Month + ", MilkYield=" + MilkYield + ", Wool=" + Wool + "]";
	}
	
	
	
	
	
	

}

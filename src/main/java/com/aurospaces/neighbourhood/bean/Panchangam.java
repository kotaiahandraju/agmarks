package com.aurospaces.neighbourhood.bean;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Panchangam {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected int SNo   = 0;
	
	protected String CropType ;
	
	protected String CropName ;
	
	protected String Season ;
	protected String SowingTime ;
	protected String SeedRate ;
	protected String Spacing ;
	
	
	protected String Fertilizers ;
	protected String WeedManagement ;
	protected String Irrigation ;
	protected String Diseases ;
	protected String DiseaseManagement ;
	
	
	protected String Pests ;
	protected String PestManagement ;
	protected String Harvesting ;
	protected String Yield ;
	public int getSNo() {
		return SNo;
	}
	public void setSNo(int sNo) {
		SNo = sNo;
	}
	public String getCropType() {
		return CropType;
	}
	public void setCropType(String cropType) {
		CropType = cropType;
	}
	public String getCropName() {
		return CropName;
	}
	public void setCropName(String cropName) {
		CropName = cropName;
	}
	public String getSeason() {
		return Season;
	}
	public void setSeason(String season) {
		Season = season;
	}
	public String getSowingTime() {
		return SowingTime;
	}
	public void setSowingTime(String sowingTime) {
		SowingTime = sowingTime;
	}
	public String getSeedRate() {
		return SeedRate;
	}
	public void setSeedRate(String seedRate) {
		SeedRate = seedRate;
	}
	public String getSpacing() {
		return Spacing;
	}
	public void setSpacing(String spacing) {
		Spacing = spacing;
	}
	public String getFertilizers() {
		return Fertilizers;
	}
	public void setFertilizers(String fertilizers) {
		Fertilizers = fertilizers;
	}
	public String getWeedManagement() {
		return WeedManagement;
	}
	public void setWeedManagement(String weedManagement) {
		WeedManagement = weedManagement;
	}
	public String getIrrigation() {
		return Irrigation;
	}
	public void setIrrigation(String irrigation) {
		Irrigation = irrigation;
	}
	public String getDiseases() {
		return Diseases;
	}
	public void setDiseases(String diseases) {
		Diseases = diseases;
	}
	public String getDiseaseManagement() {
		return DiseaseManagement;
	}
	public void setDiseaseManagement(String diseaseManagement) {
		DiseaseManagement = diseaseManagement;
	}
	public String getPests() {
		return Pests;
	}
	public void setPests(String pests) {
		Pests = pests;
	}
	public String getPestManagement() {
		return PestManagement;
	}
	public void setPestManagement(String pestManagement) {
		PestManagement = pestManagement;
	}
	public String getHarvesting() {
		return Harvesting;
	}
	public void setHarvesting(String harvesting) {
		Harvesting = harvesting;
	}
	public String getYield() {
		return Yield;
	}
	public void setYield(String yield) {
		Yield = yield;
	}
	@Override
	public String toString() {
		return "Panchangam [SNo=" + SNo + ", CropType=" + CropType + ", CropName=" + CropName + ", Season=" + Season
				+ ", SowingTime=" + SowingTime + ", SeedRate=" + SeedRate + ", Spacing=" + Spacing + ", Fertilizers="
				+ Fertilizers + ", WeedManagement=" + WeedManagement + ", Irrigation=" + Irrigation + ", Diseases="
				+ Diseases + ", DiseaseManagement=" + DiseaseManagement + ", Pests=" + Pests + ", PestManagement="
				+ PestManagement + ", Harvesting=" + Harvesting + ", Yield=" + Yield + "]";
	}
	
	
	
	
	
	
	
	

}

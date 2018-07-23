package com.aurospaces.neighbourhood.bean;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class InnovativeCrops {

	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected int SNo   = 0;
	
	
    protected String Crop ;
	protected String Soil ;
	protected String Climate ;
	protected String LandPreparation ;
	
	
	
	protected String Sowing ;
	protected String Varieties ;
	protected String Irrigation ;
	protected String Fertilizers ;
	
	
	
	protected String PestsDiseases ;
	protected String PestManagement ;
	protected String PropagationMethods ;
	protected String PlantationMethod ;
	
	
	protected String InterculturalOperations ;
	protected String Weeding ;
	protected String Harvesting ;
	protected String Yield ;
	
	protected String TipsForCultivation ;
	protected String CultivationProcedure ;
	protected String Uses ;
	public int getSNo() {
		return SNo;
	}
	public void setSNo(int sNo) {
		SNo = sNo;
	}
	public String getCrop() {
		return Crop;
	}
	public void setCrop(String crop) {
		Crop = crop;
	}
	public String getSoil() {
		return Soil;
	}
	public void setSoil(String soil) {
		Soil = soil;
	}
	public String getClimate() {
		return Climate;
	}
	public void setClimate(String climate) {
		Climate = climate;
	}
	public String getLandPreparation() {
		return LandPreparation;
	}
	public void setLandPreparation(String landPreparation) {
		LandPreparation = landPreparation;
	}
	public String getSowing() {
		return Sowing;
	}
	public void setSowing(String sowing) {
		Sowing = sowing;
	}
	public String getVarieties() {
		return Varieties;
	}
	public void setVarieties(String varieties) {
		Varieties = varieties;
	}
	public String getIrrigation() {
		return Irrigation;
	}
	public void setIrrigation(String irrigation) {
		Irrigation = irrigation;
	}
	public String getFertilizers() {
		return Fertilizers;
	}
	public void setFertilizers(String fertilizers) {
		Fertilizers = fertilizers;
	}
	public String getPestsDiseases() {
		return PestsDiseases;
	}
	public void setPestsDiseases(String pestsDiseases) {
		PestsDiseases = pestsDiseases;
	}
	public String getPestManagement() {
		return PestManagement;
	}
	public void setPestManagement(String pestManagement) {
		PestManagement = pestManagement;
	}
	public String getPropagationMethods() {
		return PropagationMethods;
	}
	public void setPropagationMethods(String propagationMethods) {
		PropagationMethods = propagationMethods;
	}
	public String getPlantationMethod() {
		return PlantationMethod;
	}
	public void setPlantationMethod(String plantationMethod) {
		PlantationMethod = plantationMethod;
	}
	public String getInterculturalOperations() {
		return InterculturalOperations;
	}
	public void setInterculturalOperations(String interculturalOperations) {
		InterculturalOperations = interculturalOperations;
	}
	public String getWeeding() {
		return Weeding;
	}
	public void setWeeding(String weeding) {
		Weeding = weeding;
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
	public String getTipsForCultivation() {
		return TipsForCultivation;
	}
	public void setTipsForCultivation(String tipsForCultivation) {
		TipsForCultivation = tipsForCultivation;
	}
	public String getCultivationProcedure() {
		return CultivationProcedure;
	}
	public void setCultivationProcedure(String cultivationProcedure) {
		CultivationProcedure = cultivationProcedure;
	}
	public String getUses() {
		return Uses;
	}
	public void setUses(String uses) {
		Uses = uses;
	}
	@Override
	public String toString() {
		return "InnovativeCrops [SNo=" + SNo + ", Crop=" + Crop + ", Soil=" + Soil + ", Climate=" + Climate
				+ ", LandPreparation=" + LandPreparation + ", Sowing=" + Sowing + ", Varieties=" + Varieties
				+ ", Irrigation=" + Irrigation + ", Fertilizers=" + Fertilizers + ", PestsDiseases=" + PestsDiseases
				+ ", PestManagement=" + PestManagement + ", PropagationMethods=" + PropagationMethods
				+ ", PlantationMethod=" + PlantationMethod + ", InterculturalOperations=" + InterculturalOperations
				+ ", Weeding=" + Weeding + ", Harvesting=" + Harvesting + ", Yield=" + Yield + ", TipsForCultivation="
				+ TipsForCultivation + ", CultivationProcedure=" + CultivationProcedure + ", Uses=" + Uses + "]";
	}
	
	
	
	
	
	
	
	
	
}

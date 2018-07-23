package com.aurospaces.neighbourhood.bean;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class CostCultivationVeg {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected int SNo   = 0;
	
	
	protected String CropName ;
	protected String FieldPreparation ;
	protected String NurseryPlanting ;
	protected String Weeding ;
	protected String PlantProtection ;
	
	
	protected String Fertilizers ;
	protected String Wages ;
	protected String StakingTransportMisc ;
	protected String TotalCost ;
	protected String YieldMTHec ;
	
	protected String NetIncomeLp ;
	protected String MarketpriceRange ;
	public int getSNo() {
		return SNo;
	}
	public void setSNo(int sNo) {
		SNo = sNo;
	}
	public String getCropName() {
		return CropName;
	}
	public void setCropName(String cropName) {
		CropName = cropName;
	}
	public String getFieldPreparation() {
		return FieldPreparation;
	}
	public void setFieldPreparation(String fieldPreparation) {
		FieldPreparation = fieldPreparation;
	}
	public String getNurseryPlanting() {
		return NurseryPlanting;
	}
	public void setNurseryPlanting(String nurseryPlanting) {
		NurseryPlanting = nurseryPlanting;
	}
	public String getWeeding() {
		return Weeding;
	}
	public void setWeeding(String weeding) {
		Weeding = weeding;
	}
	public String getPlantProtection() {
		return PlantProtection;
	}
	public void setPlantProtection(String plantProtection) {
		PlantProtection = plantProtection;
	}
	public String getFertilizers() {
		return Fertilizers;
	}
	public void setFertilizers(String fertilizers) {
		Fertilizers = fertilizers;
	}
	public String getWages() {
		return Wages;
	}
	public void setWages(String wages) {
		Wages = wages;
	}
	public String getStakingTransportMisc() {
		return StakingTransportMisc;
	}
	public void setStakingTransportMisc(String stakingTransportMisc) {
		StakingTransportMisc = stakingTransportMisc;
	}
	public String getTotalCost() {
		return TotalCost;
	}
	public void setTotalCost(String totalCost) {
		TotalCost = totalCost;
	}
	
	public String getNetIncomeLp() {
		return NetIncomeLp;
	}
	public void setNetIncomeLp(String netIncomeLp) {
		NetIncomeLp = netIncomeLp;
	}
	public String getMarketpriceRange() {
		return MarketpriceRange;
	}
	public void setMarketpriceRange(String marketpriceRange) {
		MarketpriceRange = marketpriceRange;
	}
	public String getYieldMTHec() {
		return YieldMTHec;
	}
	public void setYieldMTHec(String yieldMTHec) {
		YieldMTHec = yieldMTHec;
	}
	@Override
	public String toString() {
		return "CostCultivationVeg [SNo=" + SNo + ", CropName=" + CropName + ", FieldPreparation=" + FieldPreparation
				+ ", NurseryPlanting=" + NurseryPlanting + ", Weeding=" + Weeding + ", PlantProtection="
				+ PlantProtection + ", Fertilizers=" + Fertilizers + ", Wages=" + Wages + ", StakingTransportMisc="
				+ StakingTransportMisc + ", TotalCost=" + TotalCost + ", YieldMTHec=" + YieldMTHec + ", NetIncomeLp="
				+ NetIncomeLp + ", MarketpriceRange=" + MarketpriceRange + "]";
	}
	
	
	
	
	
	
	

}

package com.aurospaces.neighbourhood.bean;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class CostCultivationCrops {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected int SNo   = 0;
	
	protected String CropName ;
	protected String HumanLabour ;
	protected String AnimalLabour ;
	protected String MachinePower ;
	protected String Seed ;
	
	
	protected String FertilizersManures ;
	protected String PlantprotectionCharges ;
	protected String IrrigationCharges ;
	protected String IntWorkingcapital ;
	protected String OperationalCost ;
	
	
	protected String FixedCost ;
	protected String SubTotal ;
	protected String ManagerialCost ;
	protected String TotalCost ;
	protected String YieldQtl ;
	
	
	protected String CostOfProductionQtl ;
	
	
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
	public String getHumanLabour() {
		return HumanLabour;
	}
	public void setHumanLabour(String humanLabour) {
		HumanLabour = humanLabour;
	}
	public String getAnimalLabour() {
		return AnimalLabour;
	}
	public void setAnimalLabour(String animalLabour) {
		AnimalLabour = animalLabour;
	}
	public String getMachinePower() {
		return MachinePower;
	}
	public void setMachinePower(String machinePower) {
		MachinePower = machinePower;
	}
	public String getSeed() {
		return Seed;
	}
	public void setSeed(String seed) {
		Seed = seed;
	}
	public String getFertilizersManures() {
		return FertilizersManures;
	}
	public void setFertilizersManures(String fertilizersManures) {
		FertilizersManures = fertilizersManures;
	}
	public String getPlantprotectionCharges() {
		return PlantprotectionCharges;
	}
	public void setPlantprotectionCharges(String plantprotectionCharges) {
		PlantprotectionCharges = plantprotectionCharges;
	}
	public String getIrrigationCharges() {
		return IrrigationCharges;
	}
	public void setIrrigationCharges(String irrigationCharges) {
		IrrigationCharges = irrigationCharges;
	}
	public String getIntWorkingcapital() {
		return IntWorkingcapital;
	}
	public void setIntWorkingcapital(String intWorkingcapital) {
		IntWorkingcapital = intWorkingcapital;
	}
	public String getOperationalCost() {
		return OperationalCost;
	}
	public void setOperationalCost(String operationalCost) {
		OperationalCost = operationalCost;
	}
	public String getFixedCost() {
		return FixedCost;
	}
	public void setFixedCost(String fixedCost) {
		FixedCost = fixedCost;
	}
	public String getSubTotal() {
		return SubTotal;
	}
	public void setSubTotal(String subTotal) {
		SubTotal = subTotal;
	}
	public String getManagerialCost() {
		return ManagerialCost;
	}
	public void setManagerialCost(String managerialCost) {
		ManagerialCost = managerialCost;
	}
	public String getTotalCost() {
		return TotalCost;
	}
	public void setTotalCost(String totalCost) {
		TotalCost = totalCost;
	}
	public String getYieldQtl() {
		return YieldQtl;
	}
	public void setYieldQtl(String yieldQtl) {
		YieldQtl = yieldQtl;
	}
	
	
	
	
	public String getCostOfProductionQtl() {
		return CostOfProductionQtl;
	}
	public void setCostOfProductionQtl(String costOfProductionQtl) {
		CostOfProductionQtl = costOfProductionQtl;
	}
	@Override
	public String toString() {
		return "CostCultivationCrops [SNo=" + SNo + ", CropName=" + CropName + ", HumanLabour=" + HumanLabour
				+ ", AnimalLabour=" + AnimalLabour + ", MachinePower=" + MachinePower + ", Seed=" + Seed
				+ ", FertilizersManures=" + FertilizersManures + ", PlantprotectionCharges=" + PlantprotectionCharges
				+ ", IrrigationCharges=" + IrrigationCharges + ", IntWorkingcapital=" + IntWorkingcapital
				+ ", OperationalCost=" + OperationalCost + ", FixedCost=" + FixedCost + ", SubTotal=" + SubTotal
				+ ", ManagerialCost=" + ManagerialCost + ", TotalCost=" + TotalCost + ", YieldQtl=" + YieldQtl
				+ ", CostOfProductionQtl=" + CostOfProductionQtl + "]";
	}
	
	
	
	
	
	

}

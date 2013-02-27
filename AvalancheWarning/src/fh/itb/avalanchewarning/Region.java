package fh.itb.avalanchewarning;

public class Region {
	String regionName;
	String currentAvalancheProbability;
	
	public Region(){
	}
	
	public void setRegionName(String name){
		this.regionName = name;
	}
	
	public void setCurrentAvalancheProbability(String probability){
		this.currentAvalancheProbability = probability;
	}
	
	public String getRegionName(){
		return regionName;
	}
	
	public String getCurrentAvalancheProbability(){
		return currentAvalancheProbability;
	}
}

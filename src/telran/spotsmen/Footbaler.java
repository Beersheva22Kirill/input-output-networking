package telran.spotsmen;

public class Footbaler implements Sportsman {

	String team;
	
	@Override
	public void action() {
		System.out.printf("plays football %s\n", team != null ? "for team" + team : "");
	}
	
	public Footbaler() {
		
	}
	
	public Footbaler(String team) {
		this.team = team;
	}
	

}

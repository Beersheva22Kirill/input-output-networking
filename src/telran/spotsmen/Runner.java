package telran.spotsmen;

public class Runner implements Sportsman {

	int distance;
	
	@Override
	public void action() {
		System.out.printf("Runs distance %s\n", distance != 0 ? "distance: "  + distance + " km": "");
	}
	
	public Runner() {
		
	}
	
	public Runner(String distance) {
		this.distance = Integer.valueOf(distance);
	}
	

}

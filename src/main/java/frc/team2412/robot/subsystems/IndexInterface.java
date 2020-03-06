package frc.team2412.robot.subsystems;

public interface IndexInterface {

	void set(double val);
	
	void up();
	
	void out();
	
	void off();
	
	void pid(double val);
	
	double getRevolutions();
	
	double getCurrentDraw();
	
}

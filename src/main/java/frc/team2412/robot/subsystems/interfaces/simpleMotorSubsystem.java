package frc.team2412.robot.subsystems.interfaces;

public interface simpleMotorSubsystem {

	void set(double speed);

	void posMax();

	void negMax();

	void off();

	double getRevolutions();

	void resetEncoder();

	void pid(double gain);

}

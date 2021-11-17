package frc.team2412.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.robototes.units.Distance;
import com.robototes.units.UnitTypes;
import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Config;
import io.github.oblarg.oblog.annotations.Log;

// This is an example subsystem. Make sure all subsystems extend Subsystem base and take in all dependencies through a constructor
public class ExampleSubsystem extends SubsystemBase implements Loggable{
	// Constants for the example subsystem
	public static class ExampleConstants {
		// Example int (@Config allows us to configure the constants)
		@Config.NumberSlider
		public static int SAMPLE_INTEGER = 3;

		// Example double
		@Config.NumberSlider
		public static double SAMPLE_DOUBLE = 0.5;


	}
	// Enums will go below here
	public enum ExampleState{
		ON, OFF;
	}

	// Public so other subsystems can access it, but final so they cant mess with it (@Log allows us to log the object)
	@Log
	public final CANSparkMax motor;

	// Private so other subsystems dont mess with it but this one can
	private ExampleState exampleState;

	// Create the example subsystem
	public ExampleSubsystem(CANSparkMax example) {
		motor = example;
		exampleState = ExampleState.OFF;
		setName("ExampleSubsystem");
	}

	// Run every loop of the robot
	@Override
	public void periodic() {
		if(isOn()) System.out.println("Subsystem "+getName()+" says hi.");
	}

	// Example methods to be called by commands
	public void enable(double value) {
		if(value > ExampleConstants.SAMPLE_DOUBLE && value < ExampleConstants.SAMPLE_INTEGER) exampleState = ExampleState.ON;
	}
	public void disable() {
		exampleState = ExampleState.OFF;
	}

	// Getter value for a subsystem private variable
	public boolean isOn(){
		return exampleState == ExampleState.ON;
	}



}

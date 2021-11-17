package frc.team2412.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;

import static frc.team2412.robot.Hardware.HardwareConstants.*;

//This is the class in charge of all the motors, motor ids, and any other sensors the robot uses.
//remember to declare robot container at the bottom of this class 
public class Hardware {

	public static class HardwareConstants{
		// IDs
		public static int EXAMPLE_PORT = 1;

		public static int EXAMPLE_CAN = 20;

		public static int EXAMPLE_PWM = 5;

		public static int EXAMPLE_INPUT = 2;
	}

	// example device
	// soleniod (kinda like minecraft piston)
	public static Solenoid EXAMPLE_SOLENIOD = new Solenoid(EXAMPLE_PORT);
	// motor
	public static CANSparkMax EXAMPLE_MOTOR = new CANSparkMax(EXAMPLE_CAN, CANSparkMaxLowLevel.MotorType.kBrushless);
	// servo
	public static Servo EXAMPLE_SERVO = new Servo(EXAMPLE_PWM);
	// Sensor
	public static DigitalInput EXAMPLE_SENSOR = new DigitalInput(EXAMPLE_INPUT);
}

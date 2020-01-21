package frc.team2412.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.robototes.sensors.Limelight;
import com.robototes.sensors.Limelight.*;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

//This is the class in charge of all the motors, motor ids, and any other sensors the robot uses. 
//remember to declare robot container at the bottom of this class 
public class RobotMap {

	// IDs
	public static int exampleID = 1;

	// Limelight subsystem
	public static NetworkTable limelightNetworkTable = NetworkTableInstance.getDefault().getTable("limelight");
	public static Limelight limelight = new Limelight(limelightNetworkTable, LEDMode.OFF, CamMode.VISION_PROCESSER,
			Pipeline.ZERO, StreamMode.STANDARD, SnapshotMode.OFF);

	// Turret Subsystem
	public static int turretMotorID = 0;
	public static WPI_TalonSRX turretMotor = new WPI_TalonSRX(turretMotorID);

	// Robot container
	public static RobotContainer robotContainer = new RobotContainer();

	// OI
	public static OI m_OI = new OI(robotContainer);
}

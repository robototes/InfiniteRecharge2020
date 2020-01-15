package frc.team2412.robot;

import com.robototes.sensors.Limelight;
import com.robototes.sensors.Limelight.CamMode;
import com.robototes.sensors.Limelight.LEDMode;
import com.robototes.sensors.Limelight.Pipeline;
import com.robototes.sensors.Limelight.SnapshotMode;
import com.robototes.sensors.Limelight.StreamMode;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

//This is the class in charge of all the motors, motor ids, and any other sensors the robot uses. 
//remember to declare robot container at the bottom of this class 
public class RobotMap {

	// IDs
	public static int exampleID = 1;

	// Limelight subsystem
	public static NetworkTable limelightTable = NetworkTableInstance.create().getTable("limelight");
	public static Limelight limelight = new Limelight(limelightTable, LEDMode.OFF, CamMode.VISION_PROCESSER,
			Pipeline.ZERO, StreamMode.STANDARD, SnapshotMode.OFF);

	// Robot container
	public static RobotContainer robotContainer = new RobotContainer();
}

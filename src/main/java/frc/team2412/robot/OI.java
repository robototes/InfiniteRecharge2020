package frc.team2412.robot;

import com.robototes.units.Rotations;
import com.robototes.units.UnitTypes.RotationUnits;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.team2412.robot.Commands.turret.TurretAddRotateCommand;

//This is the class in charge of all the buttons and joysticks that the drivers will use to control the robot
public class OI {

	// Joysticks
	public Joystick driverStick = new Joystick(0);

	// Buttons
	public Button exampleSubsystemMethod = new JoystickButton(driverStick, 1);
	public Button exampleSubsystemMethod2 = new JoystickButton(driverStick, 2);
	public Button exampleSubsystemMethod3 = new JoystickButton(driverStick, 3);
	public Button exampleSubsystemMethod4 = new JoystickButton(driverStick, 4);

	// Constructor to set all of the commands and buttons
	public OI(RobotContainer robotContainer) {
//		exampleSubsystemMethod.whenPressed(new TurretRotateCommand(robotContainer.m_TurretSubsystem,
//				robotContainer.m_LimelightSubsystem, new Rotations(180, RotationUnits.DEGREE)));

		exampleSubsystemMethod4.whenPressed(
				new TurretAddRotateCommand(robotContainer.m_TurretSubsystem, new Rotations(15, RotationUnits.DEGREE)));

		exampleSubsystemMethod3.whenPressed(
				new TurretAddRotateCommand(robotContainer.m_TurretSubsystem, new Rotations(-15, RotationUnits.DEGREE)));

//		exampleSubsystemMethod.whenPressed(new TurretFollowLimelightCommand(robotContainer.m_TurretSubsystem,
//				robotContainer.m_LimelightSubsystem));
	}
}

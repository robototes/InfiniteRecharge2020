package frc.team2412.robot.Subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.RobotMap;

public class DriveBaseSubsystem extends SubsystemBase {

	private DifferentialDrive robotDrive = RobotMap.robotDrive;

	public void aller(Joystick joystick) {
		robotDrive.arcadeDrive(joystick.getY(), joystick.getTwist(), true);

	}

}

package frc.team2412.robot.Commands.DriveCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.RobotMap;
import frc.team2412.robot.Subsystems.DriveBaseSubsystem;

public class DriveCommand extends CommandBase {

	private DriveBaseSubsystem driveBaseSubsystem;

	public DriveCommand(DriveBaseSubsystem driveBaseSubsystem) {
		addRequirements(driveBaseSubsystem);
		this.driveBaseSubsystem = driveBaseSubsystem;

	}

	public void execute() {
		driveBaseSubsystem.aller(RobotMap.robotContainer.m_OI.driverStick);
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}

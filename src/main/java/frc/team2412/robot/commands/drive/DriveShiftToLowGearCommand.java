package frc.team2412.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.DriveBaseSubsystem;

public class DriveShiftToLowGearCommand extends CommandBase {

	private DriveBaseSubsystem m_driveBaseSubsystem;

	public DriveShiftToLowGearCommand(DriveBaseSubsystem driveBaseSubsystem) {
		addRequirements(driveBaseSubsystem);
		this.m_driveBaseSubsystem = driveBaseSubsystem;

	}

	@Override
	public void execute() {
		m_driveBaseSubsystem.shiftToLowGear();
		;
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}

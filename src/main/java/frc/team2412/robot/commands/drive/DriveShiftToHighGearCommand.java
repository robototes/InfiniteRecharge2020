package frc.team2412.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.DriveBaseSubsystem;

public class DriveShiftToHighGearCommand extends CommandBase {

	private DriveBaseSubsystem m_driveBaseSubsystem;

	public DriveShiftToHighGearCommand(DriveBaseSubsystem driveBaseSubsystem) {
		addRequirements(driveBaseSubsystem);
		this.m_driveBaseSubsystem = driveBaseSubsystem;
	}

	@Override
	public void execute() {
		m_driveBaseSubsystem.shiftToHighGear();
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}

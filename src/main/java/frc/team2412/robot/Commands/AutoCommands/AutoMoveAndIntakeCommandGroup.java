package frc.team2412.robot.Commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team2412.robot.Subsystems.DriveBaseSubsystem;
import frc.team2412.robot.Subsystems.IntakeOnOffSubsystem;
import frc.team2412.robot.Subsystems.IntakeUpDownSubsystem;

public class AutoMoveAndIntakeCommandGroup extends ParallelCommandGroup {

	IntakeOnOffSubsystem m_intakeOnOffSubsystem;
	IntakeUpDownSubsystem m_intakeUpDownSubsystem;
	DriveBaseSubsystem m_driveBaseSubsystem;

	public AutoMoveAndIntakeCommandGroup(DriveBaseSubsystem driveBaseSubsystem,
			IntakeOnOffSubsystem intakeOnOffSubsystem, IntakeUpDownSubsystem intakeUpDownSubsystem) {
		m_intakeOnOffSubsystem = intakeOnOffSubsystem;
		m_intakeUpDownSubsystem = intakeUpDownSubsystem;
		m_driveBaseSubsystem = driveBaseSubsystem;
	}

	public void execute() {
		m_driveBaseSubsystem.getMoveCertainAmountCommand(5, 1);
	}

}

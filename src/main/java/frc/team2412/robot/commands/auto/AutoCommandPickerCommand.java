package frc.team2412.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.team2412.robot.RobotState;
import frc.team2412.robot.commands.drive.MoveToPowerCellsCommand;
import frc.team2412.robot.subsystems.DriveBaseSubsystem;
import frc.team2412.robot.subsystems.FlywheelSubsystem;
import frc.team2412.robot.subsystems.HoodSubsystem;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerSensorSubsystem;
import frc.team2412.robot.subsystems.IntakeOnOffSubsystem;
import frc.team2412.robot.subsystems.IntakeUpDownSubsystem;
import frc.team2412.robot.subsystems.LiftSubsystem;

public class AutoCommandPickerCommand extends CommandBase {

	DriveBaseSubsystem m_driveBaseSubsystem;
	IntakeOnOffSubsystem m_intakeOnOffSubsystem;
	IntakeUpDownSubsystem m_intakeUpDownSubsystem;
	LiftSubsystem m_liftSubsystem;
	FlywheelSubsystem m_flywheelSubsystem;
	IndexerSensorSubsystem m_indexerSensorSubsystem;
	IndexerMotorSubsystem m_indexerMotorSubsystem;
	HoodSubsystem m_hoodSubsystem;

	Command command;

	public AutoCommandPickerCommand(DriveBaseSubsystem driveBaseSubsystem, IntakeOnOffSubsystem intakeOnOffSubsystem,
			IntakeUpDownSubsystem intakeUpDownSubsystem, LiftSubsystem liftSubsystem,
			FlywheelSubsystem flywheelSubsystem, IndexerSensorSubsystem indexerSensorSubsystem,
			IndexerMotorSubsystem indexerMotorSubsystem, HoodSubsystem hoodSubsystem) {

		m_driveBaseSubsystem = driveBaseSubsystem;
		m_intakeOnOffSubsystem = intakeOnOffSubsystem;
		m_intakeUpDownSubsystem = intakeUpDownSubsystem;
		m_liftSubsystem = liftSubsystem;
		m_flywheelSubsystem = flywheelSubsystem;
		m_indexerMotorSubsystem = indexerMotorSubsystem;
		m_indexerSensorSubsystem = indexerSensorSubsystem;
		m_hoodSubsystem = hoodSubsystem;

	}

	@Override
	public void execute() {

		if (RobotState.sixBallAuto == true) {

			command = new SixBallAutoCommandGroup(m_driveBaseSubsystem, m_intakeOnOffSubsystem, m_intakeUpDownSubsystem,
					m_liftSubsystem, m_flywheelSubsystem, m_indexerSensorSubsystem, m_indexerMotorSubsystem,
					m_hoodSubsystem);

		} else if (RobotState.threeBallAuto == true) {

			command = new SixBallAutoCommandGroup(m_driveBaseSubsystem, m_intakeOnOffSubsystem, m_intakeUpDownSubsystem,
					m_liftSubsystem, m_flywheelSubsystem, m_indexerSensorSubsystem, m_indexerMotorSubsystem,
					m_hoodSubsystem);

		} else if (RobotState.justMoveAuto == true) {

			command = new MoveToPowerCellsCommand(m_driveBaseSubsystem);

		} else {

			command = null;

		}

		if (command != null) {
			CommandScheduler.getInstance().schedule(command);
		}

	}

	@Override
	public boolean isFinished() {
		return command.isFinished();
	}

}

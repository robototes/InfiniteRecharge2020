package frc.team2412.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team2412.robot.subsystems.DriveBaseSubsystem;
import frc.team2412.robot.subsystems.FlywheelSubsystem;
import frc.team2412.robot.subsystems.IntakeOnOffSubsystem;
import frc.team2412.robot.subsystems.IntakeUpDownSubsystem;
import frc.team2412.robot.subsystems.LiftSubsystem;

public class SixBallAutoCommandGroup extends SequentialCommandGroup {

	DriveBaseSubsystem m_driveBaseSubsystem;
	IntakeOnOffSubsystem m_intakeOnOffSubsystem;
	IntakeUpDownSubsystem m_intakeUpDownSubsystem;
	LiftSubsystem m_liftSubsystem;
	FlywheelSubsystem m_flywheelSubsystem;
	// IndexerSensorSubsystem m_sensorSubsystem;
	// IndexerMotorSubsystem m_motorSubsystem;

	// , IndexerSensorSubsystem sensorSubsystem, IndexerMotorSubsystem
	// motorSubsystem

	public SixBallAutoCommandGroup(DriveBaseSubsystem driveBaseSubsystem, IntakeOnOffSubsystem intakeOnOffSubsystem,
			IntakeUpDownSubsystem intakeUpDownSubsystem, LiftSubsystem liftSubsystem,
			FlywheelSubsystem flywheelSubsystem) {
		m_intakeOnOffSubsystem = intakeOnOffSubsystem;
		m_intakeUpDownSubsystem = intakeUpDownSubsystem;
		m_liftSubsystem = liftSubsystem;
		// m_sensorSubsystem = sensorSubsystem;
		// m_motorSubsystem = motorSubsystem;
		m_driveBaseSubsystem = driveBaseSubsystem;

//		addCommands(
//				// Spin up flywheel
//				new FlywheelShootCommand(m_flywheelSubsystem),
//
//				new IndexShootCommand(m_sensorSubsystem, m_motorSubsystem),
//
//				new AutoMoveAndIntakeCommandGroup(m_driveBaseSubsystem, m_intakeOnOffSubsystem, m_intakeUpDownSubsystem)
//
		// );
	}

}

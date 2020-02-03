package frc.team2412.robot.Subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class AutonumousSubsystem extends SubsystemBase {

	DriveBaseSubsystem m_driveBaseSubsystem;
	LiftSubsystem m_liftSubsystem;
	TurretSubsystem m_turretSubsystem;
	FlywheelSubsystem m_flywheelSubsystem;
	IndexerSubsystem m_indexerSubsystem;
	HoodSubsystem m_hoodSubsystem;
	IntakeOnOffSubsystem m_intakeOnOffSubsystem;
	IntakeUpDownSubsystem m_intakeUpDownSubsystem;

	public AutonumousSubsystem(DriveBaseSubsystem driveBaseSubsystem, LiftSubsystem liftSubsystem,
			TurretSubsystem turretSubsystem, FlywheelSubsystem flyweheelSubsystem, HoodSubsystem hoodSubsystem,
			IndexerSubsystem indexerSubsystem, IntakeOnOffSubsystem intakeOnOffSubsystem,
			IntakeUpDownSubsystem intakeUpDownSubsystem) {
		m_driveBaseSubsystem = driveBaseSubsystem;
		m_liftSubsystem = liftSubsystem;
		m_turretSubsystem = turretSubsystem;
		m_flywheelSubsystem = flyweheelSubsystem;
		m_indexerSubsystem = indexerSubsystem;
		m_intakeOnOffSubsystem = intakeOnOffSubsystem;
		m_intakeUpDownSubsystem = intakeUpDownSubsystem;
		m_hoodSubsystem = hoodSubsystem;
	}

	public void basicAutoCommand() {
		m_liftSubsystem.liftUp(); // StartUp
		m_flywheelSubsystem.shoot();
		m_hoodSubsystem.servoExtend();

		m_driveBaseSubsystem.angleDrive(90); // move to goal
		m_driveBaseSubsystem.setDriveSpeed(1, 0);

		try {
			wait(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		m_driveBaseSubsystem.setDriveSpeed(0, 0); // stop driving
		m_driveBaseSubsystem.angleDrive(90);
		m_indexerSubsystem.shoot(); // Shoot balls

		m_intakeUpDownSubsystem.frontIntakeDown(); // Get ready to intake from the trench run
		m_intakeOnOffSubsystem.frontIntakeOn();
		m_driveBaseSubsystem.setDriveSpeed(1, 0);

	}

}

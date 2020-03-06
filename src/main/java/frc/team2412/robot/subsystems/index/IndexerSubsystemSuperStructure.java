package frc.team2412.robot.subsystems.index;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import io.github.oblarg.oblog.Loggable;

public class IndexerSubsystemSuperStructure extends SubsystemBase implements Loggable {

	private IndexerMotorBackSubsystem m_IndexerMotorBackSubsystem;
	private IndexerMotorFrontSubsystem m_IndexerMotorFrontSubsystem;
	private IndexerMotorLiftSubsystem m_IndexerMotorLiftSubsystem;
	private IndexerSensorSubsystem m_IndexerSensorSubsystem;

	public IndexerSubsystemSuperStructure(CANSparkMax frontMotor, CANSparkMax midLeftMotor, CANSparkMax midRightMotor,
			CANSparkMax backMotor, IndexerSensorSubsystem indexerSensorSubsystem) {

		m_IndexerMotorFrontSubsystem = new IndexerMotorFrontSubsystem(frontMotor);
		m_IndexerMotorLiftSubsystem = new IndexerMotorLiftSubsystem(midLeftMotor, midRightMotor);
		m_IndexerMotorBackSubsystem = new IndexerMotorBackSubsystem(backMotor);

		m_IndexerSensorSubsystem = indexerSensorSubsystem;
	}

	public double getCurrentDraw() {
		return m_IndexerMotorBackSubsystem.getCurrentDraw() + m_IndexerMotorLiftSubsystem.getCurrentDraw()
				+ m_IndexerMotorFrontSubsystem.getCurrentDraw();
	}

	public IndexerMotorBackSubsystem getIndexerMotorBackSubsystem() {
		return m_IndexerMotorBackSubsystem;
	}

	public IndexerMotorFrontSubsystem getIndexerMotorFrontSubsystem() {
		return m_IndexerMotorFrontSubsystem;
	}

	public IndexerMotorLiftSubsystem getIndexerMotorLiftSubsystem() {
		return m_IndexerMotorLiftSubsystem;
	}

	public IndexerSensorSubsystem getIndexerSensorSubsystem() {
		return m_IndexerSensorSubsystem;
	}

	@Override
	public void periodic() {
	}

}

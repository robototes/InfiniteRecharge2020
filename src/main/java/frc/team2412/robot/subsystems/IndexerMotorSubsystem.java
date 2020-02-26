package frc.team2412.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.robototes.math.MathUtils;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.team2412.robot.commands.indexer.IndexIntakeBackCommandGroup;
import frc.team2412.robot.commands.indexer.IndexIntakeFrontCommandGroup;
import frc.team2412.robot.subsystems.constants.IndexerConstants;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Log;

public class IndexerMotorSubsystem extends SubsystemBase implements Loggable {

	private double frontTicks, backTicks, midTicks;

	private CANEncoder m_frontEncoder, m_backEncoder, m_midEncoder;
	private CANPIDController m_frontPIDController, m_backPIDController, m_midPIDController;

	@Log.NumberBar(min = -1, max = 1, name = "Index Front Speed", tabName = "Indexer", methodName = "get")
	private CANSparkMax m_indexFrontMotor;
	@Log.NumberBar(min = -1, max = 1, name = "Index Mid Speed", tabName = "Indexer", methodName = "get")
	private CANSparkMax m_indexMidMotor;
	@Log.NumberBar(min = -1, max = 1, name = "Index Back Speed", tabName = "Indexer", methodName = "get")
	private CANSparkMax m_indexBackMotor;

	private SpeedControllerGroup m_allMotors;
	private SpeedControllerGroup m_sideMotors;

	public IndexerMotorSubsystem(CANSparkMax frontMotor, CANSparkMax midMotor, CANSparkMax backMotor,
			IndexerSensorSubsystem indexerSensorSubsystem) {
		m_indexFrontMotor = frontMotor;
		m_indexMidMotor = midMotor;
		m_indexBackMotor = backMotor;

		m_frontEncoder = m_indexFrontMotor.getEncoder();
		m_frontPIDController = m_indexFrontMotor.getPIDController();
		configureMotorPID(m_frontPIDController);

		m_backEncoder = m_indexBackMotor.getEncoder();
		m_backPIDController = m_indexBackMotor.getPIDController();
		configureMotorPID(m_backPIDController);

		m_midEncoder = m_indexMidMotor.getEncoder();
		m_midPIDController = m_indexMidMotor.getPIDController();
		configureMotorPID(m_midPIDController);

		m_sideMotors = new SpeedControllerGroup(m_indexFrontMotor, m_indexBackMotor);
		m_allMotors = new SpeedControllerGroup(m_indexFrontMotor, m_indexMidMotor, m_indexBackMotor);

		Trigger frontProcess = new Trigger(indexerSensorSubsystem::getIntakeFrontSensorValue);
		frontProcess.whenActive(new IndexIntakeFrontCommandGroup(indexerSensorSubsystem, this), true);

		Trigger backProcess = new Trigger(indexerSensorSubsystem::getIntakeBackSensorValue);
		backProcess.whenActive(new IndexIntakeBackCommandGroup(indexerSensorSubsystem, this), true);
		midTicks = m_midEncoder.getPosition();
	}

	private void configureMotorPID(CANPIDController motorController) {
		motorController.setP(IndexerConstants.PID_P);
		motorController.setI(IndexerConstants.PID_I);
		motorController.setD(IndexerConstants.PID_D);
		motorController.setOutputRange(-IndexerConstants.MAX_SPEED, IndexerConstants.MAX_SPEED);
	}

	public void setFrontMotor(double val) {
		val = MathUtils.constrain(val, -IndexerConstants.MAX_SPEED, IndexerConstants.MAX_SPEED);
		m_indexFrontMotor.set(val);
	}

	public void setMidMotor(double val) {
		val = MathUtils.constrain(val, -IndexerConstants.MAX_SPEED, IndexerConstants.MAX_SPEED);
		m_indexMidMotor.set(val);
	}

	public void setBackMotor(double val) {
		val = MathUtils.constrain(val, -IndexerConstants.MAX_SPEED, IndexerConstants.MAX_SPEED);
		m_indexBackMotor.set(val);
	}

	public void stopAllMotors() {
		m_allMotors.set(0);
	}

	public void stopMidMotor() {
		m_indexMidMotor.set(0);
	}

	public void stopSideMotors() {
		m_sideMotors.set(0);
	}

	public void stopFrontPID(double val) {

		resetEncoderZero();
		if (m_indexFrontMotor.get() > 0) {
			m_frontPIDController.setReference(frontTicks + (val * IndexerConstants.INCH_STOP_DISTANCE),
					ControlType.kPosition);
		} else {
			m_frontPIDController.setReference(frontTicks - (val * IndexerConstants.INCH_STOP_DISTANCE),
					ControlType.kPosition);
		}
	}

	public void stopBackPID(double val) {

		resetEncoderZero();
		if (m_indexBackMotor.get() > 0) {
			m_backPIDController.setReference(backTicks + (val * IndexerConstants.INCH_STOP_DISTANCE),
					ControlType.kPosition);
		} else {
			m_backPIDController.setReference(backTicks - (val * IndexerConstants.INCH_STOP_DISTANCE),
					ControlType.kPosition);
		}
	}

	public void setMidPID(boolean upOrDown) {
		if (upOrDown) {
			m_midPIDController.setReference(midTicks + IndexerConstants.TOP_TICKS, ControlType.kPosition);
		} else {
			m_midPIDController.setReference(midTicks + IndexerConstants.BOTTOM_TICKS, ControlType.kPosition);

		}
	}

	public void resetEncoderZero() {
		frontTicks = m_frontEncoder.getPosition();
		backTicks = m_backEncoder.getPosition();
	}

	public double getCurrentDraw() {
		return m_indexBackMotor.getOutputCurrent() + m_indexFrontMotor.getOutputCurrent()
				+ m_indexMidMotor.getOutputCurrent();
	}

	@Override
	public void periodic() {

	}

}

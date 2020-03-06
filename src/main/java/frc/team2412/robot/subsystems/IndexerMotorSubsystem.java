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
	//private CANPIDController m_frontPIDController, m_backPIDController, m_midPIDController;

	//@Log.NumberBar(min = -1, max = 1, name = "Index Front Speed", tabName = "Indexer", methodName = "get")
	//private CANSparkMax m_indexFrontMotor;
	//@Log.NumberBar(min = -1, max = 1, name = "Index Mid Speed", tabName = "Indexer", methodName = "get")
	//private CANSparkMax m_indexMidMotor1;
	//private CANSparkMax m_indexMidMotor2;
	//@Log.NumberBar(min = -1, max = 1, name = "Index Back Speed", tabName = "Indexer", methodName = "get")
	//private CANSparkMax m_indexBackMotor;

	private IndexerLiftMotorSubsystem m_liftMotorSubsystem;
	private IndexerFrontMotorSubsystem m_FrontMotorSubsystem;
	private IndexerBackMotorSubsystem m_BackMotorSubsystem;

	private SpeedControllerGroup m_allMotors;
	private SpeedControllerGroup m_sideMotors;

	private boolean lifting = false;

	public IndexerMotorSubsystem(CANSparkMax c){
		configureMotorPID(c.getPIDController());
	}
	
	public IndexerMotorSubsystem(CANSparkMax frontMotor, CANSparkMax midMotor1, CANSparkMax midMotor2, CANSparkMax backMotor,
			IndexerSensorSubsystem indexerSensorSubsystem) {
		m_FrontMotorSubsystem = new IndexerFrontMotorSubsystem(frontMotor);
		m_liftMotorSubsystem = new IndexerLiftMotorSubsystem(midMotor1, midMotor2);
		m_BackMotorSubsystem = new IndexerBackMotorSubsystem(backMotor);

		m_sideMotors = new SpeedControllerGroup(frontMotor, backMotor);
		m_allMotors = new SpeedControllerGroup(frontMotor, midMotor1, backMotor);

		Trigger frontProcess = new
		Trigger(indexerSensorSubsystem::getIndexFrontSensorValue);
		frontProcess.whenActive(new
		IndexIntakeFrontCommandGroup(indexerSensorSubsystem, this), true);

		Trigger backProcess = new
		Trigger(indexerSensorSubsystem::getIndexBackSensorValue);
		backProcess.whenActive(new
		IndexIntakeBackCommandGroup(indexerSensorSubsystem, this), true);
		midTicks = m_midEncoder.getPosition();
	}

	private void configureMotorPID(CANPIDController motorController) {
		motorController.setP(IndexerConstants.PID_P);
		motorController.setI(IndexerConstants.PID_I);
		motorController.setD(IndexerConstants.PID_D);
		motorController.setOutputRange(-IndexerConstants.MAX_SPEED, IndexerConstants.MAX_SPEED);
	}

	private void configureMotorPIDL(CANPIDController motorController) {
		motorController.setP(IndexerConstants.PID_P);
		motorController.setI(IndexerConstants.PID_I);
		motorController.setD(IndexerConstants.PID_D);
		motorController.setOutputRange(-IndexerConstants.MAX_LIFT_SPEED, IndexerConstants.MAX_LIFT_SPEED);
	}

	public void setFrontMotor(double val) {
		val = MathUtils.constrain(val, -IndexerConstants.MAX_SPEED, IndexerConstants.MAX_SPEED);
		m_FrontMotorSubsystem.setFrontMotor(val);
	}

	public void setMidMotor(double val) {
		val = MathUtils.constrain(val, -IndexerConstants.MAX_SPEED, IndexerConstants.MAX_SPEED);
		m_liftMotorSubsystem.setMidMotor(val);

	}

	public void setBackMotor(double val) {
		val = MathUtils.constrain(val, -IndexerConstants.MAX_SPEED, IndexerConstants.MAX_SPEED);
		m_BackMotorSubsystem.setBackMotor(val);
	}

	public void stopAllMotors() {
		m_allMotors.set(0);
	}

	public void stopMidMotor() {
		m_liftMotorSubsystem.stopMidMotor();
	}

	public void stopSideMotors() {
		m_sideMotors.set(0);
	}

	public void stopFrontPID(double val) {

		m_FrontMotorSubsystem.setFrontPID(val);
	}

	public void stopBackPID(double val) {

		m_BackMotorSubsystem.setBackPID(val);
	}

	public void setMidPID(boolean upOrDown) {
		
		m_liftMotorSubsystem.setMidPID(upOrDown);
	}

	public void resetEncoderZero() {
		frontTicks = m_frontEncoder.getPosition();
		backTicks = m_backEncoder.getPosition();
	}

	public double getCurrentDraw() {
		return m_FrontMotorSubsystem.getCurrentDraw()+m_liftMotorSubsystem.getCurrentDraw()+m_BackMotorSubsystem.getCurrentDraw();
	}

	@Override
	public void periodic() {
	}



	public void setLifting(boolean b) {
		this.lifting = b;
	}

}

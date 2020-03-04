package frc.team2412.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.robototes.math.MathUtils;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.subsystems.constants.IndexerConstants;
import io.github.oblarg.oblog.annotations.Log;

public class IndexerLiftMotorSubsystem extends SubsystemBase {

	@SuppressWarnings("unused")
	private double midTicks;

	private CANEncoder m_midEncoder;
	private CANPIDController m_midPIDController;

	@Log.NumberBar(min = -1, max = 1, name = "Index Mid Speed", tabName = "Indexer", methodName = "get")
	private CANSparkMax m_indexMidMotor;

	private boolean lifting;

	public IndexerLiftMotorSubsystem(CANSparkMax midMotor) {
			m_indexMidMotor = midMotor;

			m_midEncoder = m_indexMidMotor.getEncoder();
			m_midPIDController = m_indexMidMotor.getPIDController();
			configureMotorPID(m_midPIDController);
		
			midTicks = m_midEncoder.getPosition();
	}

	private void configureMotorPID(CANPIDController motorController) {
			motorController.setP(IndexerConstants.PID_P);
			motorController.setI(IndexerConstants.PID_I);
			motorController.setD(IndexerConstants.PID_D);
			motorController.setOutputRange(-IndexerConstants.MAX_SPEED, IndexerConstants.MAX_SPEED);
	}

	public void setMidMotor(double val) {
		val = MathUtils.constrain(val, -IndexerConstants.MAX_LIFT_SPEED, IndexerConstants.MAX_LIFT_SPEED);
		m_indexMidMotor.set(lifting ? IndexerConstants.MAX_LIFT_SPEED : val);
		System.out.println(m_indexMidMotor.get());
	}

	public void stopMidMotor() {
		m_indexMidMotor.set(0);
	}

	public void setMidPID(boolean upOrDown) {
		//return;
		if (upOrDown) {

		m_midPIDController.setReference(midTicks + IndexerConstants.TOP_TICKS,
		ControlType.kPosition);
		} else {
			m_midPIDController.setReference(midTicks + IndexerConstants.BOTTOM_TICKS,
					ControlType.kPosition);
		 }
	}

	public double getCurrentDraw() {
		return m_indexMidMotor.getOutputCurrent();
	}

	@Override
	public void periodic() {
		System.out.println(m_indexMidMotor.get());
	}

	public void setLifting(boolean b) {
		this.lifting = b;
	}
}
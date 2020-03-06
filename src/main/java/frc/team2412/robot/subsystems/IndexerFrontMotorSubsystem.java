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

public class IndexerFrontMotorSubsystem extends IndexerMotorSubsystem implements Loggable {

	private double ticks;

	private CANEncoder m_Encoder;
    private CANPIDController m_PIDController;

	private CANSparkMax m_indexFrontMotor;


	public IndexerFrontMotorSubsystem(CANSparkMax motor) {
        super(motor);
        m_indexFrontMotor = motor;
        
		m_Encoder = m_indexFrontMotor.getEncoder();
		m_PIDController = m_indexFrontMotor.getPIDController();

		ticks = m_Encoder.getPosition();
	}

	public void setFrontMotor(double val) {
		val = MathUtils.constrain(val, -IndexerConstants.MAX_SPEED, IndexerConstants.MAX_SPEED);
		m_indexFrontMotor.set(val);
	}
	public void stopFrontMotor() {
		m_indexFrontMotor.set(0);
    }

    public void setFrontPID(double val) {
		ticks = super.resetEncoderZero(m_indexFrontMotor);
		m_PIDController.setReference(ticks + (val * IndexerConstants.INCH_STOP_DISTANCE),
			ControlType.kPosition);
    }
    @Override
    public double getCurrentDraw(){
        return m_indexFrontMotor.getOutputCurrent();
    }

}

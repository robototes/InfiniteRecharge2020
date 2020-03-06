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

public class IndexerLiftMotorSubsystem extends IndexerMotorSubsystem implements Loggable {

	private double ticks;

	private CANEncoder m_Encoder;
    private CANPIDController m_PIDController;

	private CANSparkMax m_indexMidMotor1;
	private CANSparkMax m_indexMidMotor2;


	public IndexerLiftMotorSubsystem(CANSparkMax midMotor1, CANSparkMax midMotor2) {
        super(midMotor1);
		m_indexMidMotor1 = midMotor1;
		m_indexMidMotor2 = midMotor2;

		m_Encoder = m_indexMidMotor1.getEncoder();
		m_PIDController = m_indexMidMotor1.getPIDController();

		ticks = m_Encoder.getPosition();
	}

	public void setMidMotor(double val) {
		val = MathUtils.constrain(val, -IndexerConstants.MAX_SPEED, IndexerConstants.MAX_SPEED);
		m_indexMidMotor1.set(val);
	//	System.out.println(lifting);
//		System.out.println(m_indexMidMotor.get());
	}
	public void stopMidMotor() {
		m_indexMidMotor1.set(0);
    }

	public void setMidPID(boolean upOrDown) {
		return;

		
    }
    @Override
    public double getCurrentDraw(){
        return m_indexMidMotor1.getOutputCurrent()+m_indexMidMotor2.getOutputCurrent();
    }

}

package frc.team2412.robot.logging;

import com.robototes.control.logging;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.team2412.robot.subsystems.IntakeMotorSubsystem;

public class IntakeMotorLogging implements logging {

	private IntakeMotorSubsystem intakeMotorSubsystem;
	private ShuffleboardTab tab = Shuffleboard.getTab("Intake");;

	public IntakeMotorLogging(IntakeMotorSubsystem intakeMotorSubsystem) {
		this.intakeMotorSubsystem = intakeMotorSubsystem;
		setup();
	}

	public void setup() {
		tab.add((Sendable) intakeMotorSubsystem.m_intakeFrontMotor);
		tab.add((Sendable) intakeMotorSubsystem.m_intakeBackMotor);
		tab.add(intakeMotorSubsystem);
	}

	public void update() {
		
	}

}

package frc.team2412.robot.Commands.ClimbCommands;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.Subsystems.ClimbMotorSubsystem;

public class ClimbRails extends CommandBase {
	private ClimbMotorSubsystem m_climbMotorSubsystem;

	public ClimbRails(ClimbMotorSubsystem climbMotorSubsystem) {
		m_climbMotorSubsystem = climbMotorSubsystem;
	}

	public void climbUp() {
		m_climbMotorSubsystem.climbUp();
		System.out.println("Extending");
	}

	public void pullUp() {
		m_climbMotorSubsystem.pullUp();
		System.out.println("Retracting");
	}

	public void climbStop() {
		m_climbMotorSubsystem.climbStop();
		System.out.println("Stopped");
	}
}

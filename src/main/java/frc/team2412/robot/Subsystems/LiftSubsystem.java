package frc.team2412.robot.Subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.RobotMap;

public class LiftSubsystem extends SubsystemBase{
	
private static DoubleSolenoid liftUpDown = RobotMap.liftUpDown;
	
	public static void liftUp() {
		liftUpDown.set(DoubleSolenoid.Value.kForward);
	}
	
	public static void liftDown() {
		liftUpDown.set(DoubleSolenoid.Value.kReverse);		
	}

}

package infiniteRecharge2020;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClimbLiftSubsystem extends Subsystem{

	private Solenoid pneumatic1;
	private Solenoid pneumatic2;
	
	public ClimbLiftSubsystem(int deployArmPneumatic1, int deployArmPneumatic2) {
		pneumatic1 = new Solenoid(deployArmPneumatic1);
		pneumatic2 = new Solenoid(deployArmPneumatic2);
	}

	public void DeployArm() {
		pneumatic1.set(true);
		pneumatic2.set(true);
	}

	@Override
	protected void initDefaultCommand() {
		
		
	}

}

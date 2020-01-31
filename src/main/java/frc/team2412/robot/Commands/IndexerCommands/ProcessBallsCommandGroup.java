package frc.team2412.robot.Commands.IndexerCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.Button;
import frc.team2412.robot.Subsystems.IndexerSubsystem;

//This is an example command for this year. Make sure all commands extend CommandBase and they use take all dependencies(fields) through a constructor
public class ProcessBallsCommandGroup extends ParallelCommandGroup {
	IndexerSubsystem m_Subsystem;
	ShootBalls shoot;
	IntakeBalls intake;
	SwitchBalls switchB;
	Button shootButton;

	public ProcessBallsCommandGroup(IndexerSubsystem subsystem, Button sButton) {
		shootButton = sButton;
		m_Subsystem = subsystem;
		shoot = new ShootBalls(subsystem);
		intake = new IntakeBalls(subsystem);
		switchB = new SwitchBalls(subsystem);
		addRequirements(subsystem);
		execute();
	}

	@Override
	public void execute() {
		// run the example method
		// get the button value for shooting
		if (shootButton.get()) {
			shoot.execute();
		} else {
			intake.execute();
			switchB.execute();
		}

	}

	@Override
	public boolean isFinished() {
		return true;
	}

}

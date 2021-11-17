package frc.team2412.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.ExampleSubsystem;

//This is an example command for this year. Make sure all commands extend CommandBase and they use take all dependencies(fields) through a constructor
public class ExampleCommand extends CommandBase {
	// Subsystem instance for the command
	public final ExampleSubsystem subsystem;

	public final Timer timer;
	// Command constructor. You pass through the subsystem to use through the constructor
	public ExampleCommand(ExampleSubsystem sub) {
		subsystem = sub;
		// Add subsystem requirements, so that two commands using the same subsystem cant run simultaneously
		addRequirements(subsystem);

		timer = new Timer();

	}
	// Methods for the command, you dont need to implement all of these if they arent needed

	// Runs once when the command is started
	@Override
	public void initialize() {
		timer.start();
	}
	// Runs every loop while the command is active. This stops when either isFinished() returns true of the command gets forcefully cancelled
	@Override
	public void execute() {
		// run the example method
		subsystem.enable(timer.get());
	}

	// Tells you when the command finished, so it can end.
	@Override
	public boolean isFinished() {
		return timer.get() > ExampleSubsystem.ExampleConstants.SAMPLE_INTEGER;
	}

	// Runs once when the command ends, the interruped is whether the command ended normally or through a cancel
	@Override
	public void end(boolean interrupted) {
		subsystem.disable();
	}
}

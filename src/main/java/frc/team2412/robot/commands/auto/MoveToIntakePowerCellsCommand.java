package frc.team2412.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team2412.robot.commands.drive.MoveToPowerCellsCommand;
import frc.team2412.robot.commands.intake.IntakeFrontBothOnCommandGroup;
import frc.team2412.robot.subsystems.DriveBaseSubsystem;
import frc.team2412.robot.subsystems.IntakeOnOffSubsystem;
import frc.team2412.robot.subsystems.IntakeUpDownSubsystem;

public class MoveToIntakePowerCellsCommand extends SequentialCommandGroup {

	public MoveToIntakePowerCellsCommand(DriveBaseSubsystem driveBaseSubsystem, IntakeOnOffSubsystem intakeOnOffSubsystem,
			IntakeUpDownSubsystem intakeUpDownSubsystem) {
		
		addCommands(
				new IntakeFrontBothOnCommandGroup(intakeUpDownSubsystem, intakeOnOffSubsystem),
				new MoveToPowerCellsCommand(driveBaseSubsystem)				
				);
	}

}

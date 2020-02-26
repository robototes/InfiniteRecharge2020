package frc.team2412.robot.commands.auto.subsistentCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team2412.robot.commands.drive.MoveToPowerCellsCommand;
import frc.team2412.robot.commands.intake.front.IntakeFrontBothOnCommandGroup;
import frc.team2412.robot.subsystems.DriveBaseSubsystem;
import frc.team2412.robot.subsystems.IntakeOnOffSubsystem;
import frc.team2412.robot.subsystems.IntakeUpDownSubsystem;

public class MoveToIntakePowerCellsCommandGroup extends ParallelCommandGroup {

	public MoveToIntakePowerCellsCommandGroup(DriveBaseSubsystem driveBaseSubsystem,
			IntakeOnOffSubsystem intakeOnOffSubsystem, IntakeUpDownSubsystem intakeUpDownSubsystem) {

		addCommands(new IntakeFrontBothOnCommandGroup(intakeUpDownSubsystem, intakeOnOffSubsystem),
				new MoveToPowerCellsCommand(driveBaseSubsystem));
	}

}

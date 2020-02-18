package frc.team2412.robot.Commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team2412.robot.Commands.DriveCommands.MoveToPowerCellDriveCommand;
import frc.team2412.robot.Commands.IntakeCommands.IntakeFrontBothOnCommandGroup;
import frc.team2412.robot.Subsystems.DriveBaseSubsystem;
import frc.team2412.robot.Subsystems.IntakeOnOffSubsystem;
import frc.team2412.robot.Subsystems.IntakeUpDownSubsystem;

public class MoveToPowerCellAutoCommand extends ParallelCommandGroup {

	public MoveToPowerCellAutoCommand(DriveBaseSubsystem driveBaseSubsystem, IntakeOnOffSubsystem intakeOnOffSubsystem,
			IntakeUpDownSubsystem intakeUpDownSubsystem) {

		addCommands(new MoveToPowerCellDriveCommand(driveBaseSubsystem),
				new IntakeFrontBothOnCommandGroup(intakeUpDownSubsystem, intakeOnOffSubsystem));

	}

}

package frc.team2412.robot.autonomous;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team2412.robot.commands.intake.front.IntakeFrontBothOnCommandGroup;
import frc.team2412.robot.subsystems.DriveBaseSubsystem;
import frc.team2412.robot.subsystems.IntakeLiftSubsystem;
import frc.team2412.robot.subsystems.IntakeMotorSubsystem;

public class MoveToIntakePowerCellsCommandGroup extends ParallelCommandGroup {

	public MoveToIntakePowerCellsCommandGroup(DriveBaseSubsystem driveBaseSubsystem,
			IntakeMotorSubsystem intakeOnOffSubsystem, IntakeLiftSubsystem intakeUpDownSubsystem) {

		addCommands(new IntakeFrontBothOnCommandGroup(intakeUpDownSubsystem, intakeOnOffSubsystem)
		// new MoveToPowerCellsCommand(driveBaseSubsystem)
		);
	}

}

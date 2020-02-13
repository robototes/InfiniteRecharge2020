package frc.team2412.robot.Commands.AutoCommands;

import com.robototes.PIDControls.PIDConstants;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.team2412.robot.Subsystems.DriveBaseSubsystem;
import frc.team2412.robot.Subsystems.constants.DriveBaseConstants;

public class FollowTrajectoryCommand extends CommandBase {

	private DriveBaseSubsystem m_driveBaseSubsystem;

	private PIDConstants pidConstants = new PIDConstants(DriveBaseConstants.kPDriveVel);

	double m_x1;
	double m_y1;
	double m_startHeading;
	double m_x2;
	double m_y2;
	double m_finalHeading;
	double m_vertexX;
	double m_vertexY;

	public FollowTrajectoryCommand(DriveBaseSubsystem driveBaseSubsystem, double x1, double y1, double startHeading,
			double x2, double y2, double finalHeading, double vertexX, double vertexY) {
//		addRequirements(driveBaseSubsystem);
		m_driveBaseSubsystem = driveBaseSubsystem;
		m_x1 = x1;
		m_y1 = y1;
		m_startHeading = startHeading;
		m_x2 = x2;
		m_y2 = y2;
		m_finalHeading = finalHeading;
		m_vertexX = vertexX;
		m_vertexY = vertexY;
	}

	@Override
	public void execute() {
		RamseteCommand command = new RamseteCommand(
				m_driveBaseSubsystem.makeTrajectory(m_x1, m_y1, m_startHeading, m_x2, m_y2, m_finalHeading, m_vertexX,
						m_vertexY),
				m_driveBaseSubsystem::getPose,
				new RamseteController(DriveBaseConstants.kRamseteB, DriveBaseConstants.kRamseteZeta), null,
				DriveBaseConstants.kDriveKinematics, m_driveBaseSubsystem::getWheelSpeeds,
				new PIDController(pidConstants.k_P, pidConstants.k_I, pidConstants.k_D),
				new PIDController(pidConstants.k_P, pidConstants.k_I, pidConstants.k_D),
				m_driveBaseSubsystem::tankDriveVolts, m_driveBaseSubsystem);

		command.execute();
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}

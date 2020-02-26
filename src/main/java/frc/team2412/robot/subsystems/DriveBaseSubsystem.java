package frc.team2412.robot.subsystems;

import static frc.team2412.robot.subsystems.constants.DriveBaseConstants.encoderTicksPerRevolution;
import static frc.team2412.robot.subsystems.constants.DriveBaseConstants.kDriveKinematics;
import static frc.team2412.robot.subsystems.constants.DriveBaseConstants.kGyroReversed;
import static frc.team2412.robot.subsystems.constants.DriveBaseConstants.kMaxAccelerationMetersPerSecondSquared;
import static frc.team2412.robot.subsystems.constants.DriveBaseConstants.kMaxSpeedMetersPerSecond;
import static frc.team2412.robot.subsystems.constants.DriveBaseConstants.kPDriveVel;
import static frc.team2412.robot.subsystems.constants.DriveBaseConstants.kRamseteB;
import static frc.team2412.robot.subsystems.constants.DriveBaseConstants.kRamseteZeta;
import static frc.team2412.robot.subsystems.constants.DriveBaseConstants.kaVoltSecondsSquaredPerMeter;
import static frc.team2412.robot.subsystems.constants.DriveBaseConstants.ksVolts;
import static frc.team2412.robot.subsystems.constants.DriveBaseConstants.kvVoltSecondsPerMeter;
import static frc.team2412.robot.subsystems.constants.DriveBaseConstants.lowGearRatio;
import static frc.team2412.robot.subsystems.constants.DriveBaseConstants.metersPerWheelRevolution;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.Button;
import frc.team2412.robot.RobotState;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Log;

public class DriveBaseSubsystem extends SubsystemBase implements Loggable {

	public WPI_TalonFX m_leftMotor1, m_leftMotor2, m_rightMotor1, m_rightMotor2;

	public Gyro m_gyro;

	public Solenoid m_gearShifter;

	@Log.Dial(max = 1, min = -1, showValue = true, tabName = "Drivebase Subsystem")
	public double m_currentYSpeed;

	@Log.SpeedController(name = "Left Motor Speed", tabName = "Drivebase Subsystem")
	public SpeedControllerGroup m_leftMotors;

	@Log.SpeedController(name = "Right Motor Speed", tabName = "Drivebase Subsystem")
	public SpeedControllerGroup m_rightMotors;

	private DifferentialDriveOdometry m_odometry;

	@SuppressWarnings("unused")
	private double m_rightMotorRevolutions, m_leftMotorRevolutions;

	@Log.BooleanBox(colorWhenFalse = "#0000ff", colorWhenTrue = "#ffff00", tabName = "Drivebase Subsystem")
	public boolean doItWork = false;

//	DifferentialDrive m_drive;

	@Log(tabName = "Drivebase Subsystem")
	public double m_driveBaseCurrentDraw;

	public DriveBaseSubsystem(Solenoid gearShifter, Gyro gyro, WPI_TalonFX leftMotor1, WPI_TalonFX leftMotor2,
			WPI_TalonFX rightMotor1, WPI_TalonFX rightMotor2) {

		m_leftMotor1 = leftMotor1;
		m_leftMotor2 = leftMotor2;
		m_rightMotor1 = rightMotor1;
		m_rightMotor2 = rightMotor2;

		m_leftMotor2.follow(leftMotor1);
		m_rightMotor2.follow(rightMotor1);

		m_rightMotor1.setInverted(true);
		m_rightMotor2.setInverted(true);

		m_leftMotors = new SpeedControllerGroup(m_leftMotor1, m_leftMotor2);
		m_rightMotors = new SpeedControllerGroup(m_rightMotor1, m_rightMotor2);
//		m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

		m_gyro = gyro;
		m_gearShifter = gearShifter;

		m_rightMotorRevolutions = m_rightMotor1.getSelectedSensorPosition() / encoderTicksPerRevolution * lowGearRatio;
		m_leftMotorRevolutions = m_leftMotor1.getSelectedSensorPosition() / encoderTicksPerRevolution * lowGearRatio;

		m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(0));
	}

	public void drive(Joystick rightJoystick, Joystick leftJoystick, Button button) {
		if (button.get()) {
			m_rightMotor1.set(rightJoystick.getY());
			m_leftMotor1.set(rightJoystick.getY());
		} else {
			m_rightMotor1.set(rightJoystick.getY());
			m_leftMotor1.set(leftJoystick.getY());
		}
	}

	public void oneJoystickDrive(Joystick joystick) {
//		m_drive.arcadeDrive(-1 * joystick.getY(), joystick.getTwist(), true);
	}

	public void setDriveSpeed(double forwardness, double turn) {
//		m_drive.arcadeDrive(forwardness, turn);
//		m_currentYSpeed = forwardness;
	}

	public void angleDrive(double angle) {
		double startAngle = m_gyro.getAngle();
		if (angle > 0) {
			if (m_gyro.getAngle() <= startAngle) {
				setDriveSpeed(0, 1);
			} else {
				setDriveSpeed(0, 0);
			}
		} else {
			if (m_gyro.getAngle() >= startAngle) {
				setDriveSpeed(0, -1);
			} else {
				setDriveSpeed(0, 0);
			}
		}
	}

	public void shiftToHighGear() {
		m_gearShifter.set(true);
		RobotState.m_gearState = RobotState.GearboxState.HIGH;
	}

	public void shiftToLowGear() {
		m_gearShifter.set(false);
		RobotState.m_gearState = RobotState.GearboxState.LOW;
	}

	public double getGyroHeading() {
		return Math.IEEEremainder(m_gyro.getAngle(), 360) * (kGyroReversed ? -1.0 : 1.0);
	}

	public double getCurrentYSpeed() {
		return m_currentYSpeed;
	}

	public double getCurrentDraw() {
		return m_driveBaseCurrentDraw;
	}

	// OBLOG
	// ---------------------------------------------------------------------------------

	@Override
	public String configureLogName() {
		return "Drivebase Subsystem";
	}

	// Periodic
	// ------------------------------------------------------------------------------

	@Override
	public void periodic() {

		m_currentYSpeed = (m_rightMotor1.get() + m_leftMotor1.get()) / -2.0;

		m_rightMotorRevolutions = m_rightMotor1.getSelectedSensorPosition();
		m_leftMotorRevolutions = m_leftMotor1.getSelectedSensorPosition();

		// m_odometry.update(Rotation2d.fromDegrees(m_gyro.getAngle()),
		// (m_leftMotorRevolutions / encoderTicksPerRevolution * lowGearRatio) *
		// metersPerWheelRevolution,
		// (m_rightMotorRevolutions / encoderTicksPerRevolution * lowGearRatio) *
		// metersPerWheelRevolution);

		m_driveBaseCurrentDraw = m_rightMotor1.getStatorCurrent() + m_rightMotor2.getStatorCurrent()
				+ m_leftMotor1.getStatorCurrent() + m_leftMotor2.getStatorCurrent();

	}

	// Trajectory stuff
	// _________________________________________________________________________________________________

	public Pose2d getPose() {
		return m_odometry.getPoseMeters();
	}

	public DifferentialDriveWheelSpeeds getWheelSpeeds() {
		return new DifferentialDriveWheelSpeeds(
				m_leftMotor1.getSelectedSensorVelocity() / encoderTicksPerRevolution * lowGearRatio
						* metersPerWheelRevolution * 10,
				m_rightMotor1.getSelectedSensorVelocity() / encoderTicksPerRevolution * lowGearRatio
						* metersPerWheelRevolution * 10);
	}

	public void tankDriveVolts(double leftVolts, double rightVolts) {
		m_leftMotors.setVoltage(leftVolts);
		m_rightMotors.setVoltage(-rightVolts);
//		m_drive.feed();
	}

	public double getHeading() {
		return Math.IEEEremainder(m_gyro.getAngle(), 360) * (kGyroReversed ? -1.0 : 1.0);
	}

	public double getTurnRate() {
		return m_gyro.getRate() * (kGyroReversed ? -1.0 : 1.0);
	}

	// Create a voltage constraint to ensure we don't accelerate too fast
	DifferentialDriveVoltageConstraint autoVoltageConstraint = new DifferentialDriveVoltageConstraint(
			new SimpleMotorFeedforward(ksVolts, kvVoltSecondsPerMeter, kaVoltSecondsSquaredPerMeter), kDriveKinematics,
			10);

	// Create config for trajectory
	public TrajectoryConfig config = new TrajectoryConfig(kMaxSpeedMetersPerSecond,
			kMaxAccelerationMetersPerSecondSquared)
					// Add kinematics to ensure max speed is actually obeyed
					.setKinematics(kDriveKinematics)
					// Apply the voltage constraint
					.addConstraint(autoVoltageConstraint);

	SimpleMotorFeedforward simpleMotorFeedforward = new SimpleMotorFeedforward(ksVolts, kvVoltSecondsPerMeter,
			kaVoltSecondsSquaredPerMeter);

	RamseteController ramseteControlller = new RamseteController(kRamseteB, kRamseteZeta);

	PIDController pidController = new PIDController(kPDriveVel, 0, 0);

	public Command getAutonomousCommand() {

		DriveBaseSubsystem thisSub = this;

		// An example trajectory to follow. All units in meters.
		Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
				// Start at the origin facing the +X direction
				new Pose2d(0, 0, new Rotation2d(0)),
				// Pass through these two interior waypoints, making an 's' curve path
				List.of(new Translation2d(1, 1), new Translation2d(2, -1)),
				// End 3 meters straight ahead of where we started, facing forward
				new Pose2d(3, 0, new Rotation2d(0)),
				// Pass config
				config);

		RamseteCommand ramseteCommand = new RamseteCommand(exampleTrajectory, thisSub::getPose, ramseteControlller,
				simpleMotorFeedforward, kDriveKinematics, thisSub::getWheelSpeeds, pidController, pidController,
				// RamseteCommand passes volts to the callback
				thisSub::tankDriveVolts, thisSub);

		// Run path following command, then stop at the end.
		return ramseteCommand.andThen(() -> thisSub.tankDriveVolts(0, 0));

	}

	public Command getMoveThreeMetersForwardFromStartCommand() {

		DriveBaseSubsystem thisSub = this;

		Pose2d currentPose = getPose();
		Translation2d currentTranslation = currentPose.getTranslation();

		Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(currentPose,
				List.of(new Translation2d(currentTranslation.getX() + 1.5, 0)),
				new Pose2d(currentTranslation.getX() + 3, 0, currentPose.getRotation()), config);

		RamseteCommand ramseteCommand = new RamseteCommand(exampleTrajectory, thisSub::getPose, ramseteControlller,
				simpleMotorFeedforward, kDriveKinematics, thisSub::getWheelSpeeds, pidController, pidController,
				// RamseteCommand passes volts to the callback
				thisSub::tankDriveVolts, thisSub);

		// Run path following command, then stop at the end.
		return ramseteCommand.andThen(() -> thisSub.tankDriveVolts(0, 0));

	}

	public Command getMoveCertainAmountCommand(double finalX, double finalY) {

		DriveBaseSubsystem thisSub = this;

		Pose2d currentPose = getPose();
		Translation2d currentTranslation = currentPose.getTranslation();

		Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(currentPose,
				List.of(new Translation2d(currentTranslation.getX() + (finalX / 2), finalY / 2)),
				new Pose2d(currentTranslation.getX() + finalX, finalY, currentPose.getRotation()), config);

		RamseteCommand ramseteCommand = new RamseteCommand(exampleTrajectory, thisSub::getPose, ramseteControlller,
				simpleMotorFeedforward, kDriveKinematics, thisSub::getWheelSpeeds, pidController, pidController,
				// RamseteCommand passes volts to the callback
				thisSub::tankDriveVolts, thisSub);

		// Run path following command, then stop at the end.
		return ramseteCommand.andThen(() -> thisSub.tankDriveVolts(0, 0));

	}

	public Command getMoveToPowerCellCommand() {

		DriveBaseSubsystem thisSub = this;

		Pose2d currentPose = getPose();
		Translation2d currentTranslation = currentPose.getTranslation();

		Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(

				currentPose,

				List.of(new Translation2d(currentTranslation.getX() + 2, currentTranslation.getY() - 0.15)),

				new Pose2d(currentTranslation.getX() + 4.85, currentTranslation.getY() - 0.15,
						currentPose.getRotation()),
				config);

		RamseteCommand ramseteCommand = new RamseteCommand(exampleTrajectory, thisSub::getPose, ramseteControlller,
				simpleMotorFeedforward, kDriveKinematics, thisSub::getWheelSpeeds, pidController, pidController,
				// RamseteCommand passes volts to the callback
				thisSub::tankDriveVolts, thisSub);

		// Run path following command, then stop at the end.
		return ramseteCommand.andThen(() -> thisSub.tankDriveVolts(0, 0));

	}

//	m_odometry.resetPosition(new Pose2d( new Translation2d(3.186, -0.263), new Rotation2d(0)), new Rotation2d(0));

	public Command getMoveFromPowerCellCommand() {

		DriveBaseSubsystem thisSub = this;

		Pose2d currentPose = getPose();
		Translation2d currentTranslation = currentPose.getTranslation();

		Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(

				currentPose,

				List.of(new Translation2d(currentTranslation.getX() + 2, currentTranslation.getY() - 0.15)),

				new Pose2d(currentTranslation.getX() + 4.85, currentTranslation.getY() - 0.15,
						currentPose.getRotation()),
				config);

		RamseteCommand ramseteCommand = new RamseteCommand(exampleTrajectory, thisSub::getPose, ramseteControlller,
				simpleMotorFeedforward, kDriveKinematics, thisSub::getWheelSpeeds, pidController, pidController,
				// RamseteCommand passes volts to the callback
				thisSub::tankDriveVolts, thisSub);

		// Run path following command, then stop at the end.
		return ramseteCommand.andThen(() -> thisSub.tankDriveVolts(0, 0));

	}

	public Command getMoveToPowerCellPathFromPathWeaverCommand() {

		DriveBaseSubsystem thisSub = this;

		m_odometry.resetPosition(new Pose2d(new Translation2d(3.18, -0.26), new Rotation2d(0)), new Rotation2d());

		Path trajectoryPath = Filesystem.getDeployDirectory().toPath()
				.resolve("c:/Users/s-panatulas/git/InfiniteRecharge2020/src/main/deploy/MoveToPowerCell.wpilib.json");
		Trajectory exampleTrajectory = null;

		try {
			exampleTrajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
		} catch (IOException e) {
			System.out.println("There is no path available");
		}

		RamseteCommand ramseteCommand = new RamseteCommand(exampleTrajectory, thisSub::getPose, ramseteControlller,
				simpleMotorFeedforward, kDriveKinematics, thisSub::getWheelSpeeds, pidController, pidController,
				// RamseteCommand passes volts to the callback
				thisSub::tankDriveVolts, thisSub);

		// Run path following command, then stop at the end.
		return ramseteCommand.andThen(() -> thisSub.tankDriveVolts(0, 0));

	}

	public Command getMoveFromPowerCellPathFromPathWeaverCommand() {

		DriveBaseSubsystem thisSub = this;

		m_odometry.resetPosition(new Pose2d(new Translation2d(5.38, -2.29), new Rotation2d(0)), new Rotation2d());

		Path trajectoryPath = Filesystem.getDeployDirectory().toPath()
				.resolve("c:/Users/s-panatulas/git/InfiniteRecharge2020/src/main/deploy/MoveFromPowerCell.wpilib.json");
		Trajectory exampleTrajectory = null;

		try {
			exampleTrajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
		} catch (IOException e) {
			System.out.println("There is no path available");
		}

		RamseteCommand ramseteCommand = new RamseteCommand(exampleTrajectory, thisSub::getPose, ramseteControlller,
				simpleMotorFeedforward, kDriveKinematics, thisSub::getWheelSpeeds, pidController, pidController,
				// RamseteCommand passes volts to the callback
				thisSub::tankDriveVolts, thisSub);

		// Run path following command, then stop at the end.
		return ramseteCommand.andThen(() -> thisSub.tankDriveVolts(0, 0));

	}

}
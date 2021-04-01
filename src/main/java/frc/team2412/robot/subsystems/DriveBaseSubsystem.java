package frc.team2412.robot.subsystems;

import static frc.team2412.robot.subsystems.constants.AutoConstants.KaAngular;
import static frc.team2412.robot.subsystems.constants.AutoConstants.KaLinear;
import static frc.team2412.robot.subsystems.constants.AutoConstants.KvAngular;
import static frc.team2412.robot.subsystems.constants.AutoConstants.KvLinear;
import static frc.team2412.robot.subsystems.constants.AutoConstants.kGyroReversed;
import static frc.team2412.robot.subsystems.constants.AutoConstants.kTrackwidthMeters;
import static frc.team2412.robot.subsystems.constants.DriveBaseConstants.ENCODER_TICKS_PER_SECOND;
import static frc.team2412.robot.subsystems.constants.DriveBaseConstants.encoderTicksPerRevolution;
import static frc.team2412.robot.subsystems.constants.DriveBaseConstants.lowGearRatio;
import static frc.team2412.robot.subsystems.constants.DriveBaseConstants.metersPerWheelRevolution;
import static frc.team2412.robot.subsystems.constants.DriveBaseConstants.wheelRadiusMeters;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.system.plant.DCMotor;
import edu.wpi.first.wpilibj.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpiutil.math.VecBuilder;

import frc.team2412.robot.RobotMap;
import frc.team2412.robot.subsystems.constants.DriveBaseConstants;

public class DriveBaseSubsystem extends SubsystemBase {

	public WPI_TalonFX leftFrontMotor, leftBackMotor, rightFrontMotor, rightBackMotor;

	public Gyro gyro;

	public Solenoid gearShifter;

	public double currentYSpeed;

	private DifferentialDriveOdometry odometry;

	private double rightMotorRevolutions, leftMotorRevolutions;

	private boolean oneJoystickDrive = false;

	private boolean inOneJoystickDrive = oneJoystickDrive;

	// DifferentialDrive drive;

	public double driveBaseCurrentDraw;

	private int telemetryCounter = 0;

	private boolean isSimulation = RobotBase.isSimulation();

	public DriveBaseSubsystem(Solenoid gearShifter, Gyro gyro, WPI_TalonFX leftFrontMotor, WPI_TalonFX leftBackMotor,
			WPI_TalonFX rightFrontMotor, WPI_TalonFX rightBackMotor) {

		this.leftFrontMotor = leftFrontMotor;
		this.leftBackMotor = leftBackMotor;
		this.rightFrontMotor = rightFrontMotor;
		this.rightBackMotor = rightBackMotor;

		this.leftBackMotor.follow(leftFrontMotor);
		this.rightBackMotor.follow(rightFrontMotor);

		this.rightFrontMotor.setInverted(true);
		this.rightBackMotor.setInverted(true);

		// drive = new DifferentialDrive(leftMotors, rightMotors);

		this.gyro = gyro;
		this.gearShifter = gearShifter;

		rightMotorRevolutions = rightFrontMotor.getSelectedSensorPosition() / encoderTicksPerRevolution * lowGearRatio;
		leftMotorRevolutions = leftFrontMotor.getSelectedSensorPosition() / encoderTicksPerRevolution * lowGearRatio;

		odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(0));
		if (isSimulation) {
			simulationSetup();
		}
	}

	public void drive(Joystick rightJoystick, Joystick leftJoystick, Button button) {
		if (oneJoystickDrive) {
			rightFrontMotor.set(rightJoystick.getY() - Math.pow(rightJoystick.getTwist(), 3));
			leftFrontMotor.set(rightJoystick.getY() + Math.pow(rightJoystick.getTwist(), 3));
		} else {

			if (button.get()) {
				rightFrontMotor.set(rightJoystick.getY());
				leftFrontMotor.set(rightJoystick.getY());
			} else {
				rightFrontMotor.set(rightJoystick.getY());
				leftFrontMotor.set(leftJoystick.getY());
			}
		}
	}

	public void oneJoystickDrive(Joystick joystick) {
		// drive.arcadeDrive(-1 * joystick.getY(), joystick.getTwist(), true);
	}

	public void setDriveSpeed(double forwardness, double turn) {
		// drive.arcadeDrive(forwardness, turn);
		// currentYSpeed = forwardness;
	}

	public void angleDrive(double angle) {
		double startAngle = gyro.getAngle();
		if (angle > 0) {
			if (gyro.getAngle() <= startAngle) {
				setDriveSpeed(0, 1);
			} else {
				setDriveSpeed(0, 0);
			}
		} else {
			if (gyro.getAngle() >= startAngle) {
				setDriveSpeed(0, -1);
			} else {
				setDriveSpeed(0, 0);
			}
		}
	}

	public void shiftToHighGear() {
		gearShifter.set(true);
	}

	public void shiftToLowGear() {
		gearShifter.set(false);
	}

	public double getGyroHeading() {
		return Math.IEEEremainder(gyro.getAngle(), 360) * (kGyroReversed ? -1.0 : 1.0);
	}

	public double getCurrentYSpeed() {
		return currentYSpeed;
	}

	public double getCurrentDraw() {
		return driveBaseCurrentDraw;
	}

	// Periodic
	// ------------------------------------------------------------------------------

	@Override
	public void periodic() {

		currentYSpeed = (rightFrontMotor.get() + leftFrontMotor.get()) / -2.0;

		rightMotorRevolutions = rightFrontMotor.getSelectedSensorPosition();
		leftMotorRevolutions = leftFrontMotor.getSelectedSensorPosition();

		// System.out.println((leftMotorRevolutions / encoderTicksPerRevolution) * lowGearRatio *
		// metersPerWheelRevolution + " " + (rightMotorRevolutions / encoderTicksPerRevolution) * lowGearRatio *
		// metersPerWheelRevolution);

		double angle = gyro.getAngle();
		odometry.update(Rotation2d.fromDegrees(angle),
		(leftMotorRevolutions / encoderTicksPerRevolution * lowGearRatio) *
		metersPerWheelRevolution,
		(rightMotorRevolutions / encoderTicksPerRevolution * lowGearRatio) *
		metersPerWheelRevolution);

		if (!isSimulation && (--telemetryCounter <= 0)) {
			System.out.println("odometry: " + odometry.getPoseMeters() + ", Gyro: " + String.valueOf(angle) + " " + String.valueOf(RobotMap.driveGyro.isConnected()));
			telemetryCounter = 10;
		}

		driveBaseCurrentDraw = rightFrontMotor.getStatorCurrent() + rightBackMotor.getStatorCurrent()
				+ leftFrontMotor.getStatorCurrent() + leftBackMotor.getStatorCurrent();

	}

	// Trajectory stuff
	// _________________________________________________________________________________________________

	public Pose2d getPose() {
		return isSimulation ? drivetrainSim.getPose() : odometry.getPoseMeters();
	}

	public void resetPos() {
		rightFrontMotor.setSelectedSensorPosition(0);
		leftFrontMotor.setSelectedSensorPosition(0);
		odometry.resetPosition(new Pose2d(), Rotation2d.fromDegrees(0));
		if (isSimulation) {
			drivetrainSim.setPose(new Pose2d());
		}
	}

	public DifferentialDriveWheelSpeeds getWheelSpeeds() {
		double leftMetersPerSecond = isSimulation ? drivetrainSim.getLeftVelocityMetersPerSecond() :
			(leftFrontMotor.getSelectedSensorVelocity() / encoderTicksPerRevolution)
				* lowGearRatio * metersPerWheelRevolution * ENCODER_TICKS_PER_SECOND;
		double rightMetersPerSecond = isSimulation ? drivetrainSim.getRightVelocityMetersPerSecond() :
			(rightFrontMotor.getSelectedSensorVelocity() / encoderTicksPerRevolution)
				* lowGearRatio * metersPerWheelRevolution * ENCODER_TICKS_PER_SECOND;
		return new DifferentialDriveWheelSpeeds(leftMetersPerSecond, rightMetersPerSecond);
	}

	public void tankDriveVolts(double leftVolts, double rightVolts) {
		//System.out.println("tankDriveVolts: " + leftVolts + ", " + rightVolts);

		leftFrontMotor.setVoltage(leftVolts);
		rightFrontMotor.setVoltage(rightVolts);
		// drive.feed();
	}

	// Simulator
	// _________________________________________________________________________________________________

	public DifferentialDrivetrainSim drivetrainSim;
	private Field2d fieldSim;

	private void simulationSetup() {
		drivetrainSim = new DifferentialDrivetrainSim(
				// Create a linear system from our characterization gains.
				LinearSystemId.identifyDrivetrainSystem(KvLinear, KaLinear, KvAngular, KaAngular),
				DCMotor.getFalcon500(2), // 2 Falcon500 motors on each side of the drivetrain.
				1 / DriveBaseConstants.lowGearRatio, // low gear reduction.
				kTrackwidthMeters, // The track width is 0.7112 meters.
				wheelRadiusMeters, // The robot uses 3" radius wheels.

				// The standard deviations for measurement noise:
				// x and y: 0.001 m
				// heading: 0.001 rad
				// l and r velocity: 0.1 m/s
				// l and r position: 0.005 m
				VecBuilder.fill(0.001, 0.001, 0.001, 0.1, 0.1, 0.005, 0.005));

		// the Field2d class lets us visualize our robot in the simulation GUI.
		fieldSim = new Field2d();
		SmartDashboard.putData("Field", fieldSim);
	}

	int count = 0;

	public void simulationPeriodic() {
		// Need to invert rightFrontMotor for simulation, as it's inverted on the physical robot
		// such that negative power moves forwards
		drivetrainSim.setInputs(leftFrontMotor.get() * RobotController.getBatteryVoltage(),
				-rightFrontMotor.get() * RobotController.getBatteryVoltage());
		drivetrainSim.update(0.020);
		fieldSim.setRobotPose(drivetrainSim.getPose());
		if (count++ % 20 == 0) {
			System.out.println("updated in simluation periodic: " + drivetrainSim.getPose());
		}
	}
}
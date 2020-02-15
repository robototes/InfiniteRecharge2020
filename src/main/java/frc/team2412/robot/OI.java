package frc.team2412.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

//This is the class in charge of all the buttons and joysticks that the drivers will use to control the robot
public class OI {

	// Joystick ports
	public static final int DRIVER_STICK_PORT = 0;
//	public static final int CODRIVER_STICK_PORT = 1;

//	// LIFT button ports
//	public static final int LIFT_UP_BUTTON_PORT = 1;
//	public static final int LIFT_DOWN_BUTTON_PORT = 1;
//
//	// INTAKE front on off
//	public static final int INTAKE_FRONT_ON_BUTTON_PORT = 1;
//	public static final int INTAKE_FRONT_OFF_BUTTON_PORT = 1;
//
//	// INTAKE back on off
//	public static final int INTAKE_BACK_ON_BUTTON_PORT = 1;
//	public static final int INTAKE_BACK_OFF_BUTTON_PORT = 1;
//
//	// INTAKE bring it up and down
//	public static final int INTAKE_UP_BUTTON_PORT = 1;
//	public static final int INTAKE_DOWN_BUTTON_PORT = 1;
//
//	// INTAKE ON/OFF BUTTONS
//	public static final int INTAKE_FRONT_ON_BACK_OFF_BUTTON_PORT = 1;
//	public static final int INTAKE_BACK_ON_FRONT_OFF_BUTTON_PORT = 1;
//
//	// CONTROL PANEL button ports
//	public static final int CONTROL_PANEL_SPIN_3_TIMES_BUTTON_PORT = 1;
//	public static final int CONTROL_PANEL_SET_TO_TARGET_COLOR_BUTTON_PORT = 1;
//
//	// CLIMB button ports
//	private static final int CLIMB_DEPLOY_RAILS_BUTTON_PORT = 0;
//	private static final int CLIMB_EXTEND_ARM_BUTTON_PORT = 0;
//	private static final int CLIMB_RETRACT_RAILS_BUTTON_PORT = 0;
//	private static final int CLIMB_STOP_ARM_BUTTON_PORT = 0;
//	private static final int CLIMB_RETRACT_ARM_BUTTON_PORT = 0;

	// Joysticks
	public Joystick driverStick = new Joystick(DRIVER_STICK_PORT);
//	public Joystick codriverStick = new Joystick(CODRIVER_STICK_PORT);
	
	public Button autoCommand = new JoystickButton(driverStick, 1);


	// Constructor to set all of the commands and buttons
	public OI(RobotContainer robotContainer) {
		// telling the button that when its pressed to execute example command with the
		// robot container's instance of example subsystem
		
		autoCommand.whenPressed(RobotMap.m_robotContainer.m_driveBaseSubsystem.getCartonCommmand());
	
	}
}

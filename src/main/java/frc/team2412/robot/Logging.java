package frc.team2412.robot;

import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Log;

public class Logging implements Loggable {

	// 4 rows, 10 col

	private static final String green = "#7FFF00";
	private static final String red = "#B22222";
	private static final String white = "#FFFFFF";

	// Goal Able
	@Log.BooleanBox(colorWhenFalse = white, colorWhenTrue = green, columnIndex = 0, rowIndex = 0, height = 2)
	@Log.BooleanBox(colorWhenFalse = white, colorWhenTrue = green, columnIndex = 2, rowIndex = 0, height = 2)
	public static boolean outerGoalAble = false;
	@Log.BooleanBox(colorWhenFalse = white, colorWhenTrue = green, columnIndex = 0, rowIndex = 0, height = 2)
	public static boolean innerGoalAble = false;
	
	// Goal Aimed
	@Log.BooleanBox(colorWhenFalse = white, colorWhenTrue = green, columnIndex = 0, rowIndex = 0, height = 2)
	@Log.BooleanBox(colorWhenFalse = white, colorWhenTrue = green, columnIndex = 2, rowIndex = 0, height = 2)
	public static boolean outerGoalAimed= false;
	@Log.BooleanBox(colorWhenFalse = white, colorWhenTrue = green, columnIndex = 0, rowIndex = 0, height = 2)
	public static boolean innerGoalAimed = false;
	
	
	

	@Log
	public static boolean hum = true;

	public void periodic() {

	}

}

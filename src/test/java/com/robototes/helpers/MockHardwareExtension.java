package com.robototes.helpers;

import edu.wpi.first.hal.HAL;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.simulation.DriverStationSim;

/**
 * JUnit 5 testing extension which ensures all WPILib foundational bits are
 * initialized to be able to run the scheduler.
 */
public final class MockHardwareExtension {

	public static void afterAll() {
		DriverStation.getInstance().release();
		HAL.releaseDSMutex();
	}

	public static void beforeAll() {
		initializeHardware();
	}

	private static void initializeHardware() {
		HAL.initialize(500, 0);
		DriverStationSim dsSim = new DriverStationSim();
		dsSim.setDsAttached(true);
		dsSim.setAutonomous(false);
		dsSim.setEnabled(true);
		dsSim.setTest(true);
	}
}
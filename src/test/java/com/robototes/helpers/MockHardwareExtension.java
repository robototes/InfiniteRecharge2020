package com.robototes.helpers;

import edu.wpi.first.hal.HAL;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.simulation.DriverStationSim;

/**
 * JUnit 5 testing extension which ensures all WPILib foundational bits are
 * initialized to be able to run the scheduler.
 */
public final class MockHardwareExtension {
	private static boolean m_executeUnlockThread = true;

	public static void afterAll() {
		m_executeUnlockThread = true;
		Thread unlockThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (m_executeUnlockThread) {
					HAL.releaseDSMutex();
				}
			}
		});

		unlockThread.start();
		DriverStation.release();
		m_executeUnlockThread = false;
		try {
			unlockThread.join();
		} catch (InterruptedException ignored) {}
	}

	public static void beforeAll() {
		initializeHardware();
	}

	private static void initializeHardware() {
		HAL.initialize(500, 0);
		DriverStationSim.setDsAttached(true);
		DriverStationSim.setAutonomous(false);
		DriverStationSim.setEnabled(true);
		DriverStationSim.setTest(true);
	}
}
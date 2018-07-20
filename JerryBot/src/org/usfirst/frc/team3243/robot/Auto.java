package org.usfirst.frc.team3243.robot;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;

public class Auto {
	
	WPI_TalonSRX driveM1 = new WPI_TalonSRX(2);
    WPI_TalonSRX driveM2 = new WPI_TalonSRX(3);
	WPI_TalonSRX driveM3 = new WPI_TalonSRX(1);
	WPI_TalonSRX driveM4 = new WPI_TalonSRX(4);
	int timeout = 10;
	int PIDIdx = 0;
	
	public void autoInit() {
		//Init drive 1 PID
		driveM1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, timeout);
		driveM1.configNominalOutputForward(0, timeout);
		driveM1.configNominalOutputReverse(0, timeout);
		driveM1.configPeakOutputForward(1, timeout);
		driveM1.configPeakOutputReverse(-1, timeout);
		driveM1.config_kF(PIDIdx, 1, timeout);
		driveM1.config_kP(PIDIdx, 0, timeout);
		driveM1.config_kI(PIDIdx, 0, timeout);
		driveM1.config_kD(PIDIdx, 0, timeout);
		
		//Init drive 3 PID
		driveM2.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, timeout);
		driveM2.configNominalOutputForward(0, timeout);
		driveM2.configNominalOutputReverse(0, timeout);
		driveM2.configPeakOutputForward(1, timeout);
		driveM2.configPeakOutputReverse(-1, timeout);
		driveM2.config_kF(PIDIdx, 1, timeout);
		driveM2.config_kP(PIDIdx, 0, timeout);
		driveM2.config_kI(PIDIdx, 0, timeout);
		driveM2.config_kD(PIDIdx, 0, timeout);
		
		//get drive 2 and 4 to follow
		driveM3.follow(driveM1);
		driveM4.follow(driveM2);
	}
	
	public void autoDrive(Double[] value) {
		driveM1.set(ControlMode.Velocity, value[0]* 500.0 * 4096 / 600);
		driveM2.set(ControlMode.Velocity, -value[1]* 500.0 * 4096 / 600);
	}

	public void stopVelocity() {
		driveM1.set(ControlMode.PercentOutput, 0);
		driveM2.set(ControlMode.PercentOutput, 0);
	}
}

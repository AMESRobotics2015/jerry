package org.usfirst.frc.team3243.robot;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Spark;

public class MotorController {

	double switchTime;
	double scaleTime;
	int timeout = 10;
	int PIDIdx = 0;
	
	WPI_TalonSRX driveM1 = new WPI_TalonSRX(2);
    WPI_TalonSRX driveM2 = new WPI_TalonSRX(3);
	WPI_TalonSRX driveM3 = new WPI_TalonSRX(1);
	WPI_TalonSRX driveM4 = new WPI_TalonSRX(4);
	
	VictorSP greenLight = new VictorSP(4);
	
	Spark collect1 = new Spark(2);	
	Spark collect2 = new Spark(3);
	
	Spark lift1 = new Spark(0);
	Spark lift2 = new Spark(1);
	
	DigitalOutput underGlow = new DigitalOutput(0);
	
	boolean init = false;
	
	public void PIDinit() {
		//Init drive 1 PID
				driveM1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, timeout);
				driveM1.configNominalOutputForward(0, timeout);
				driveM1.configNominalOutputReverse(0, timeout);
				driveM1.configPeakOutputForward(1, timeout);
				driveM1.configPeakOutputReverse(-1, timeout);
				driveM1.config_kF(PIDIdx, .3, timeout);
				driveM1.config_kP(PIDIdx, 1, timeout);
				driveM1.config_kI(PIDIdx, 0, timeout);
				driveM1.config_kD(PIDIdx, 0, timeout);
				
				//Init drive 3 PID
				driveM2.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, timeout);
				driveM2.configNominalOutputForward(0, timeout);
				driveM2.configNominalOutputReverse(0, timeout);
				driveM2.configPeakOutputForward(1, timeout);
				driveM2.configPeakOutputReverse(-1, timeout);
				driveM2.config_kF(PIDIdx, .3, timeout);
				driveM2.config_kP(PIDIdx, 1, timeout);
				driveM2.config_kI(PIDIdx, 0, timeout);
				driveM2.config_kD(PIDIdx, 0, timeout);
				
				//get drive 2 and 4 to follow
				driveM3.follow(driveM1);
				driveM4.follow(driveM2);
	}
	public void drive(Double [] val) {
		if(!init) {
			PIDinit();
			init = true;
		}
		driveM1.set(ControlMode.PercentOutput, 0.8*val[1]);
		driveM2.set(ControlMode.PercentOutput, -0.8*val[0]);
		
	}
	public void light() {
		greenLight.set(.6);
	}
	public void autoDrive(Double[] value) {
		driveM1.set(ControlMode.PercentOutput, value[0]);
		driveM2.set(ControlMode.PercentOutput, -value[1]);
	}
	
	public void ballControl(Boolean suck, Boolean push) {
		if(suck && !push) {
			collect1.set(.8);
			collect2.set(-.8);
		}else if(!suck && push) {
			collect1.set(-.8);
			collect2.set(.8);
		}else {
			collect1.set(0);
			collect2.set(0);
		}
	}
	
	public void lift(Double val) {
		lift1.set(val);
		lift2.set(-val);
	}
	public void autoDrives(boolean in) {
		if(in) {
			driveM1.set(-.2);
			driveM2.set(.2);
			driveM3.set(-.2);
			driveM4.set(.2);
		}
		if(!in) {
			driveM1.set(0);
			driveM2.set(0);
			driveM3.set(0);
			driveM4.set(0);
		}
	}
	
	/*public void autoDrive(double degrees, double distance) {
		double mvLeft = -.15-.01*Math.sqrt(Math.abs(degrees));
		double mvRight = .2+.01*Math.sqrt(Math.abs(degrees));
		double mvForward = .2+.02*Math.pow(distance, -1.5);
		
		if(degrees > 7) {
			driveM1.set(mvLeft);
			driveM2.set(mvLeft);
			driveM3.set(mvLeft);
			driveM4.set(mvLeft);
		}else if (degrees < -7) {
			driveM1.set(mvRight);
			driveM2.set(mvRight);
			driveM3.set(mvRight);
			driveM4.set(mvRight);
		}else {
			if(distance < .6) {
				driveM1.set(-mvForward);
				driveM2.set(mvForward);
				driveM3.set(-mvForward);
				driveM4.set(mvForward);
				collect1.set(.8);
				collect2.set(-.8);
			}else {
			}
		}
		
	}*/
	public void rave(boolean input) {
		underGlow.set(input);
	}
}

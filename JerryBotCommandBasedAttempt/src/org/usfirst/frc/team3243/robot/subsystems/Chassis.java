package org.usfirst.frc.team3243.robot.subsystems;

import org.usfirst.frc.team3243.robot.commands.Drive;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Chassis extends Subsystem {
	
	Talon driveM1 = new Talon(1);
	Talon driveM2 = new Talon(2);
	Victor driveM3 = new Victor(1);
	Victor driveM4 = new Victor(2);
	
    public void drive(Double [] vals) {
		driveM1.set(vals[0]);
		driveM2.set(vals[1]);
		driveM3.set(vals[0]);
		driveM4.set(vals[1]);
    }

    public void initDefaultCommand() {
    	setDefaultCommand(new Drive());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


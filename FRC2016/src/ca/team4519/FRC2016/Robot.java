package ca.team4519.FRC2016;

import ca.team4519.lib.MechaIterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.*;
import ca.team4519.FRC2016.subsystems.*;
import ca.team4519.FRC2016.auton.*;
import ca.team4519.lib.MultiLooper;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends MechaIterativeRobot{

	SendableChooser auton = new SendableChooser();
	Command autoMode;
	
	boolean step1 = false;
	boolean step2 = false;
	boolean step3 = false;
	boolean step4 = false;
	boolean needTime = true;
	int tim = 0;
	int temptim = 50000;
	double goalcount = 0;
	
	public boolean fireBumper = false;
	
	Joystick gamepad = new Joystick(0);
	Joystick stick = new Joystick(1);
	Drivebase Drivebase = new Drivebase();
	ShoulderPivot Shoulder = new ShoulderPivot();
	Flywheel Flywheel = new Flywheel();
	Lifter Lifter = new Lifter();
	MultiLooper subsystemUpdater100hz = new MultiLooper(1.0 /100);

	public void robotInit() {
		
		Flywheel.shooterInit();
		Drivebase.drivebaseInit();
		
		subsystemUpdater100hz.addLoopable(Drivebase);
		subsystemUpdater100hz.addLoopable(Flywheel);
		subsystemUpdater100hz.addLoopable(Shoulder);
		subsystemUpdater100hz.addLoopable(Lifter);
		subsystemUpdater100hz.addLoopable(Shoulder);
		subsystemUpdater100hz.start();
		
		Drivebase.gyro.calibrate();
		SmartDashboard.putNumber("Auto Mode", 0);
		SmartDashboard.putNumber("Distance to drive", 10.0);
		needTime = true;
		
		auton.addObject("", new CrossLowbar());
		auton.addObject("", new DriveDist(1));
		SmartDashboard.putData("Autonomous Selector", auton);

	}
	
	public void autonomousInit(){
	
		Drivebase.resetSensors();
		needTime = true;
	
	}
	
	public void autonomousPeriodic(){
	if (needTime){
		tim = (int)(System.currentTimeMillis()); // in milliseconds
		needTime = false;
	}
	temptim =  (int)(System.currentTimeMillis());
	SmartDashboard.putNumber("tim", tim);
	SmartDashboard.putNumber("temptim", temptim);
	SmartDashboard.putNumber("elapsed tim", temptim-tim);
	
	if(SmartDashboard.getNumber("Auto Mode") == 0){
			//Do nothing
	}else if(SmartDashboard.getNumber("Auto Mode") == 1){
		
		if(temptim - tim <2500){
			Drivebase.LeftDrive.set(1.0);
			Drivebase.RightDrive.set(-1.0);
		}else{
			
			Drivebase.LeftDrive.set(0.0);
			Drivebase.RightDrive.set(0.0);
		}
	}else if(SmartDashboard.getNumber("Auto Mode") == 2){
			
		Shoulder.changePos(Gains.ShooterPositions.Intake);
		 
		if ((temptim - tim <2750) && (Shoulder.rotatPid.getValue() > 70)){
		
			Drivebase.LeftDrive.set(0.5);
			Drivebase.RightDrive.set(-0.5);
		}else{
			Drivebase.LeftDrive.set(0.0);
			Drivebase.RightDrive.set(0.0);
		}

		
	}else if (SmartDashboard.getNumber("Auto Mode") == 3){
	

		Shoulder.changePos(Gains.ShooterPositions.Intake);
		 
		if ((temptim - tim <2750) && (Shoulder.rotatPid.getValue() > 70)){
		
			Drivebase.LeftDrive.set(0.5);
			Drivebase.RightDrive.set(-0.5);
		}else{
			
			Drivebase.LeftDrive.set(0.0);
			Drivebase.RightDrive.set(0.0);
		}
		
		if(step1){
			Flywheel.shooterControl(true, false, false);
			Timer.delay(1);
			Flywheel.shooterControl(false, false, false);
			step1 = false;
			step2 = true;
		}
		
		if(step2){
			if(Drivebase.gyro.getAngle() != 180){
				Drivebase.LeftDrive.set((180 - Drivebase.gyro.getAngle()) / 22.5);
				Drivebase.RightDrive.set((180 - Drivebase.gyro.getAngle()) / 22.5);
			}else if (Drivebase.gyro.getAngle() == (180 +- 3)){
				step2 = false;
			}
		}
		
		
	}else if(SmartDashboard.getNumber("Auto Mode") == 4){
		if ((temptim - tim <500)){
			
			Drivebase.LeftDrive.set(0.5);
			Drivebase.RightDrive.set(-0.5);
		}else{
			
			Drivebase.LeftDrive.set(0.0);
			Drivebase.RightDrive.set(0.0);
		}
	}
	
	}
	
	public void teleopInit() { 
		Drivebase.resetSensors();
		Shoulder.illuminatedAngles.clear();
		
	}
	
	public void teleopPeriodic() {
		
		Drivebase.arcadeDriveMath(
				gamepad.getRawAxis(Constants.MovementAxis), 
				gamepad.getRawAxis(Constants.RotationAxis));
		
		Flywheel.shooterControl(stick.getRawButton(Constants.Spin_Joystick) || gamepad.getRawButton(Constants.Spin_Gamepad),
				(stick.getRawButton(Constants.Intake_Joystick) || gamepad.getRawButton(2) || gamepad.getRawButton(Constants.Intake_GamePad)),
				stick.getRawButton(Constants.Fire_Joystick));
				
		Shoulder.changePos(Shoulder.setPos(
				stick.getRawButton(Constants.FloorShot),
				stick.getRawButton(Constants.RampShotPos),
				stick.getRawButton(Constants.LowgoalPos),
				stick.getRawButton(Constants.Intake_Joystick),
				gamepad.getRawButton(Constants.Intake_GamePad),
				gamepad.getRawButton(Constants.RampPos)));
		
	}
		
	public void allPeriodic(){
			
		SmartDashboard.putNumber("Gyro turn rate", Drivebase.gyro.getRate());
		Drivebase.update();
		
	}
}
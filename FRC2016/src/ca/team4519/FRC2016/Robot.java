package ca.team4519.FRC2016;

import ca.team4519.lib.MechaIterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.*;
import ca.team4519.FRC2016.subsystems.*;
import ca.team4519.FRC2016.auton.*;
import ca.team4519.lib.MultiLooper;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends MechaIterativeRobot{

	SendableChooser auton = new SendableChooser();
	Command autoMode;
		
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
		
		auton.addObject("Do Nothing", new DoNothing());
		auton.addObject("", new CrossLowbar());
		auton.addObject("", new DriveDist(1));
		SmartDashboard.putData("Autonomous Selector", auton);

	}
	
	public void autonomousInit(){
	
		Drivebase.resetSensors();
		autoMode = (Command) auton.getSelected();
		autoMode.start();
	
	}
	
	public void autonomousPeriodic(){
		Scheduler.getInstance().run();
	}
	
	public void teleopInit() { 
		Scheduler.getInstance().removeAll();
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
package ca.team4519.FRC2016.auton;

import ca.team4519.FRC2016.Gains;
import ca.team4519.FRC2016.subsystems.Drivebase;
import ca.team4519.FRC2016.subsystems.ShoulderPivot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CrossLowbar extends Command {

	int tim = 0;
	int temptim = 50000;
	boolean needTime = true;
	boolean routineDone = false;
	
	private ShoulderPivot Shoulder = new ShoulderPivot();
	private Drivebase Drivebase = new Drivebase();
	
    public CrossLowbar() {
    }


    protected void initialize() {

    	Drivebase.resetSensors();
		needTime = true;
    }
    
    protected void execute() {
    	
		Shoulder.changePos(Gains.ShooterPositions.Intake);
		 
		if ((Drivebase.leftDriveEncoder.get() / Gains.DrivetrainGains.TicksPerInch)< 10){
		
			Drivebase.LeftDrive.set(0.5);
			Drivebase.RightDrive.set(-0.5);
		}else{
			routineDone = true;
			Drivebase.LeftDrive.set(0.0);
			Drivebase.RightDrive.set(0.0);
		}
		
    }

    protected boolean isFinished() {
        return routineDone;
    }

    protected void end() {
    	Drivebase.LeftDrive.set(0.0);
    	Drivebase.RightDrive.set(0.0);
    }

    protected void interrupted() {
    }
}

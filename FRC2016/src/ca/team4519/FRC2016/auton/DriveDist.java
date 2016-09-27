package ca.team4519.FRC2016.auton;

import edu.wpi.first.wpilibj.command.Command;
import ca.team4519.FRC2016.Gains;
import ca.team4519.FRC2016.subsystems.*;

public class DriveDist extends Command {

	private Drivebase Drivebase = new Drivebase();
	
	public double distance;
	public boolean cmdDone = false;
	
	
	/*
	 * @Param Distance in inches
	 */
    public DriveDist(double distance) {
    	
    	this.distance = distance;
    }

    protected void initialize() {
    	Drivebase.leftDriveEncoder.reset();
    	Drivebase.rightDriveEncoder.reset();
    }

    protected void execute() {
    	if ((Drivebase.leftDriveEncoder.get() / Gains.DrivetrainGains.TicksPerInch)< distance){
			Drivebase.LeftDrive.set(1.0);
			Drivebase.RightDrive.set(-1.0);
		}else{
			
			Drivebase.LeftDrive.set(0.0);
			Drivebase.RightDrive.set(0.0);
			cmdDone = true;
		}
    }

    protected boolean isFinished() {
        return cmdDone;
    }

    protected void end() {
    	Drivebase.LeftDrive.set(0.0);
    	Drivebase.RightDrive.set(0.0);
    }

    protected void interrupted() {
    	Drivebase.LeftDrive.set(0.0);
    	Drivebase.RightDrive.set(0.0);
    }
}

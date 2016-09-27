package ca.team4519.lib;

public class DrivePower {

	public double rightPower;
	public double leftPower;
	
	public DrivePower(double left, double right){
		this.rightPower = right;
		this.leftPower = left;
	}
	
	public static DrivePower NULL_INPUT = new DrivePower(0,0);
	
}

package ca.team4519.FRC2016.subsystems;

import ca.team4519.lib.Subsystem;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class PDP extends Subsystem{

	PowerDistributionPanel PDP = new PowerDistributionPanel();
	

	public void PDPInit() {
		PDP.clearStickyFaults();
		System.out.println("PDP Initialized");
	}
	
	public void update() {
		SmartDashboard.putNumber("PDP Temperature", PDP.getTemperature());
		SmartDashboard.putNumber("Input Voltage",PDP.getVoltage());
		SmartDashboard.putNumber("Pivot Motor Current", PDP.getCurrent(13));
		
	}
	
	public PDP() {
		super("PDP");
		// TODO Auto-generated constructor stub
	}

}

package ca.team4519.lib.pid;

import edu.wpi.first.wpilibj.interfaces.Potentiometer;

/**
 * A PID source that converts a rotary potentiometer's voltage output into degrees of
 * rotation
 */
public class PotentiometerPidSrc implements AnglePidSrc {
    
    private final Potentiometer pot;
    private final float minVolt, maxVolt, minAngle, maxAngle;
    
    /**
     * Create a new PotentiometerPidSrc instance
     * @param pot a Potentiometer object to measure voltages from
     * @param minVolt the minimum measured voltage from the potentiometer at the "small" movement endpoint of the system
     * @param maxVolt the maximum measured voltage from the potentiometer at the "large" movement endpoint of the system
     * @param minAngle the minimum angle the system can physically rotate to
     * @param maxAngle  the maximum angle the system can physically rotate to
     */
    public PotentiometerPidSrc(final Potentiometer pot,
            final float minVolt, final float maxVolt,
            final float minAngle, final float maxAngle) {
        this.pot = pot;
        this.minVolt = minVolt;
        this.maxVolt = maxVolt;
        this.minAngle = minAngle;
        this.maxAngle = maxAngle;
    }
    
    /**
     * Retrieve the original sensor used to construct this PIDSrc
     * @return the Potentiometer
     */
    public Potentiometer getSensor() {
        return pot;
    }
    
    /**
     * Inherited from PIDSrc
     * @return the measured value of this PIDSrc
     */
    public float getValue() {
        final float slope = (maxAngle - minAngle) / (maxVolt - minVolt);
        final float offset = minAngle - slope * minVolt;
        return (float)(slope * pot.get() + offset);
    }
    
    /**
     * Inherited from AnglePidSrc
     * @return the minimal angle of this sensor
     */
    public float getMinAngle() {
        return minAngle;
    }
    
    /**
     * Inherited from AnglePidSrc
     * @return the maximal angle of this sensor
     */
    public float getMaxAngle() {
        return maxAngle;
    }
    
}
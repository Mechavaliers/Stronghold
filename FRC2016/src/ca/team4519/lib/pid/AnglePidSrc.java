 package ca.team4519.lib.pid;

public interface AnglePidSrc extends PIDSrc {
    /**
     * The minimal angle of this sensor
     * @return the minimal angle of this sensor
     */
    public float getMinAngle();
    /**
     * The maximal angle of this sensor
     * @return the maximal angle of this sensor
     */
    public float getMaxAngle();
}

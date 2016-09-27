package ca.team4519.lib.pid;

public interface PIDSrc {
    /**
     * The measured value of this PIDSrc
     * @return the measured value of this PIDSrc
     */
    public float getValue();
}

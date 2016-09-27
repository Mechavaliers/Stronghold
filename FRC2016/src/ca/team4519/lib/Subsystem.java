package ca.team4519.lib;

public abstract class Subsystem {
	
  protected String name;

  public Subsystem(String name){
    this.name = name;
  }

  public String toString() {
    return name;
  }
}
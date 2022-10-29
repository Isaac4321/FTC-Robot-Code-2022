# Esquimalt Atom Smashers

## Who We Are

We are the Esquimalt Atom Smashers.

## Table of Contents
1. [Creating a subsystem](#creating-a-subsystem)
3. [Creating a command](#creating-a-command)
4. [Attaching a command to a subsystem](#attaching-a-command-to-a-subsystem)
5. [Binding a command to a controller](#binding-a-command-to-a-controller)
6. [Creating a route in autonomous](#creating-a-route-in-autonomous)
7. [Drive Team important notes](#drive-team-important-notes)



## Creating a subsystem

A subsystem is a component that controls one part of the robot, for example the drive base or the claw. 

### Step 1: Create class that extends CustomSubsytemBase

You create a class with name that coresponds with the subsystem you are creating. You then use the ```extends``` keyword to make the subsystem a subclass (child) of CustomSubsystemBase. In this case, the subsytem is called ClawSubsystem.
```java
public class ClawSubsystem extends CustomSubsystemBase {

}
```

### Step 2: Define the hardware the subsystem controls

Next, you define the constants, hardware and any other variables the subsytem would need. In this case, we only need one hardware, a servo. 
```java
public class ClawSubsystem extends CustomSubsystemBase {
    private final ServoEx claw;
}
```

### Step 3: Create a constructor matching parent class

Next, you call the parent's constructor using the keyword ```super```. You initialize your hardware and other objects that need initialization. In this case, the claw is initialized. 
```java
public class ClawSubsystem extends CustomSubsystemBase {

    private final ServoEx claw;

    public ClawSubsystem(HardwareMap hardwareMap, boolean auto) {
        super(hardwareMap, auto);
        claw = new SimpleServo(hardwareMap, "clawServo", MINIMUM_ANGLE, MAXIMUM_ANGLE, AngleUnit.DEGREES);
    }

}
```


### Step 4: Create the necessary methods for controling the subsystem

For each individual function the subsystem will do, you should create an individual method for each one. Sometimes during autonomous, you might need more specific methods. In this case, there is two functions the claw subsystem must do, opening the claw and closing the claw. So two corresponding methods must be created. 
```java
public class ClawSubsystem extends CustomSubsystemBase {
    private final ServoEx claw;
    
    public ClawSubsystem(HardwareMap hardwareMap, boolean auto) {
        super(hardwareMap, auto);
        claw = new SimpleServo(hardwareMap, "clawServo", MINIMUM_ANGLE, MAXIMUM_ANGLE, AngleUnit.DEGREES);
    }

    public void openClaw() {
        claw.rotateByAngle(90);
    }

    public void closeClaw() {
        claw.rotateByAngle(-90);
    }
}
```


## Creating a command

A command is a class that makes reference to one subsystem method and calls it when it is scheduled or bound to a keybind.

### Step 1: Create a class that extends CommandBase

You create a class with name that coresponds with the method the command will call. You then use the ```extends``` keyword to make the command a subclass (child) of CommandBase. In this case, the commmand is called OpenClawCommand.
```java
public class OpenClawCommand extends CommandBase {

}
```

### Step 2: Define the subsystem the command uses

```java
public class OpenClawCommand extends CommandBase {
    private final ClawSubsystem clawSubsystem;
}
```
## Attaching a command to a subsystem
## Binding a command to a controller
## Creating a route in autonomous
## Drive Team important notes

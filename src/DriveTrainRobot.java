
class DriveTrainRobot extends Robot{
    public DriveTrainRobot(RobotPool pool){super(pool);}
    protected void performService(){
        System.out.println(this + " installing driveTrain");
        assembler.car().addDriveTrain();
    }
}
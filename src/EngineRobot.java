class EngineRobot extends Robot{
    public EngineRobot(RobotPool pool){super(pool);}
    protected void performService(){
        System.out.println(this + " installing engine");
        assembler.car().addEngine();
    }
}
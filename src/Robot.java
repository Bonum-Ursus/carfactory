import java.util.concurrent.BrokenBarrierException;

abstract public class Robot implements Runnable{
    private RobotPool pool;
    public Robot(RobotPool p) {pool = p;}
    protected Assembler assembler;
    public Robot assingAssembler(Assembler assembler){
        this.assembler = assembler;
        return this;
    }
    private boolean engine = false;
    public synchronized void engage(){
        engine = true;
        notifyAll();
    }
    abstract protected void performService();
    @Override
     public void run() {
        try{
            powerDown();
            while (!Thread.interrupted()){
                performService();
                assembler.barrier().await();
                powerDown();
            }
        }catch (InterruptedException e){
            System.out.println("Exiting " + this + " via interrupt");
        }catch (BrokenBarrierException e){
            throw  new RuntimeException(e);
        }
        System.out.println(this + " off");
     }
     private synchronized void powerDown() throws InterruptedException{
        engine = false;
        assembler = null;
        pool.release(this);
        while (engine == false)
            wait();
     }
     public String toSting(){return getClass().getName();}
 }


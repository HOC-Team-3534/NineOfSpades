package org.usfirst.frc3534.RobotBasic.functions;

public class FunctionBase implements FunctionInterface{

    boolean running;
    boolean finished;
    int state;

    @Override
    public void process(){
        
    }

    @Override
    public boolean isFinished(){

        return isFinished();

    }

    @Override
    public void reset(){

        state = 1;
        running = false;
        finished = false;

    }

    @Override
    public void completed(){

        running = false;
        finished = true;

    }

}
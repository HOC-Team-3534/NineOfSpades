package org.usfirst.frc3534.RobotBasic.systems;

import org.usfirst.frc3534.RobotBasic.RobotMap;

import edu.wpi.first.wpilibj.I2C;

public class Arduino extends SystemBase implements SystemInterface{

    private I2C arduino = RobotMap.arduino;

    private LEDState ledState = LEDState.OFF;

    public Arduino(){

        String WriteString = "test"; //"test" will be the message we will send to the arduino
        char[] CharArray = WriteString.toCharArray(); //Turns the message into an array
        byte[] WriteData = new byte[CharArray.length]; //Turns the array into a byte
        for(int i = 0; i < CharArray.length; i++){
            WriteData[i] = (byte) CharArray[i];
        }
        arduino.transaction(WriteData, WriteData.length, null, 0); //Send the message "test" to the arduino

    }

    @Override
    public void process(){

        switch(ledState){

        case OFF:

            break;

        case BLUE:

            break;

        case RED:

            break;

        case GREEN:

            break;
        
        case RAINBOW:

            break;
        }

    }

    public enum LEDState{

        OFF,
        BLUE,
        RED,
        GREEN,
        RAINBOW

    }

    public void setLEDToOff(){
        
    }

    public void setLEDToBlue(){

    }

    public void setLEDToRed(){

    }

    public void setLEDToGreen(){

    }

    public void setLEDToRainbow(){

    }

}
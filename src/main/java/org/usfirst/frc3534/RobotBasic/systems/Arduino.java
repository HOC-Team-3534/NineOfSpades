package org.usfirst.frc3534.RobotBasic.systems;

import org.usfirst.frc3534.RobotBasic.RobotMap;

import edu.wpi.first.wpilibj.I2C;

public class Arduino extends SystemBase implements SystemInterface{

    private I2C arduino = RobotMap.arduino;

    private LEDState ledState = LEDState.OFF;

    private boolean firstTimeOff = true;
    private boolean firstTimeBlue = true;
    private boolean firstTimeRed = true;
    private boolean firstTimeGreen = true;
    private boolean firstTimeRainbow = true;

    public Arduino(){

        String WriteString = "test"; //"test" will be the message we will send to the arduino
        char[] CharArray = WriteString.toCharArray(); //Turns the message into an array
        byte[] WriteData = new byte[CharArray.length]; //Turns the array into a byte
        for(int i = 0; i < CharArray.length; i++){
            WriteData[i] = (byte) CharArray[i];
        }
        //arduino.transaction(WriteData, WriteData.length, null, 0); //Send the message "test" to the arduino

    }

    @Override
    public void process(){

        switch(ledState){

        case OFF:

            setLEDToOff();

            break;

        case BLUE:

            setLEDToBlue();

            break;

        case RED:

            setLEDToRed();

            break;

        case GREEN:

            setLEDToGreen();

            break;
        
        case RAINBOW:

            setLEDToRainbow();

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

    public void setLEDState(LEDState state){

        ledState = state;

    }

    public void setLEDToOff(){

        firstTimeOff = false;
        firstTimeBlue = true;
        firstTimeRed = true;
        firstTimeGreen = true;
        firstTimeRainbow = true;

        String WriteString = "off"; //"test" will be the message we will send to the arduino
        char[] CharArray = WriteString.toCharArray(); //Turns the message into an array
        byte[] WriteData = new byte[CharArray.length]; //Turns the array into a byte
        for(int i = 0; i < CharArray.length; i++){
            WriteData[i] = (byte) CharArray[i];
        }
        arduino.transaction(WriteData, WriteData.length, null, 0); //Send the message "test" to the arduino
        
    }

    public void setLEDToBlue(){

        firstTimeOff = true;
        firstTimeBlue = false;
        firstTimeRed = true;
        firstTimeGreen = true;
        firstTimeRainbow = true;

        String WriteString = "blue"; //"test" will be the message we will send to the arduino
        char[] CharArray = WriteString.toCharArray(); //Turns the message into an array
        byte[] WriteData = new byte[CharArray.length]; //Turns the array into a byte
        for(int i = 0; i < CharArray.length; i++){
            WriteData[i] = (byte) CharArray[i];
        }
        arduino.transaction(WriteData, WriteData.length, null, 0); //Send the message "test" to the arduino

    }

    public void setLEDToRed(){

        firstTimeOff = true;
        firstTimeBlue = true;
        firstTimeRed = false;
        firstTimeGreen = true;
        firstTimeRainbow = true;

        String WriteString = "red"; //"test" will be the message we will send to the arduino
        char[] CharArray = WriteString.toCharArray(); //Turns the message into an array
        byte[] WriteData = new byte[CharArray.length]; //Turns the array into a byte
        for(int i = 0; i < CharArray.length; i++){
            WriteData[i] = (byte) CharArray[i];
        }
        arduino.transaction(WriteData, WriteData.length, null, 0); //Send the message "test" to the arduino

    }

    public void setLEDToGreen(){

        firstTimeOff = true;
        firstTimeBlue = true;
        firstTimeRed = true;
        firstTimeGreen = false;
        firstTimeRainbow = true;

        String WriteString = "green"; //"test" will be the message we will send to the arduino
        char[] CharArray = WriteString.toCharArray(); //Turns the message into an array
        byte[] WriteData = new byte[CharArray.length]; //Turns the array into a byte
        for(int i = 0; i < CharArray.length; i++){
            WriteData[i] = (byte) CharArray[i];
        }
        arduino.transaction(WriteData, WriteData.length, null, 0); //Send the message "test" to the arduino

    }

    public void setLEDToRainbow(){

        firstTimeOff = true;
        firstTimeBlue = true;
        firstTimeRed = true;
        firstTimeGreen = true;
        firstTimeRainbow = false;

        String WriteString = "rainbow"; //"test" will be the message we will send to the arduino
        char[] CharArray = WriteString.toCharArray(); //Turns the message into an array
        byte[] WriteData = new byte[CharArray.length]; //Turns the array into a byte
        for(int i = 0; i < CharArray.length; i++){
            WriteData[i] = (byte) CharArray[i];
        }
        arduino.transaction(WriteData, WriteData.length, null, 0); //Send the message "test" to the arduino

    }

}
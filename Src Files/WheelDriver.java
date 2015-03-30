/**
 * WheelDriver for our robot. This will handle ONLY movement. This should (hopefully) be able to be used w/o any sensors.
 * All movement LOGIC (search for ball, follow line, etc), and sensor handling should be done in another file.
 * Fill in anything as needed, make clear notes either in here or preferrably on the Git.
 * Feel free to add your name to the author list when changes are made.
 * @author Grayson Lorenz
 * @author Brett Knecht
 */

// add imports to make the things do the stuff -bk
import java.util.Date;
import com.pi4j.io.serial.Serial;
import com.pi4j.io.serial.SerialDataEvent;
import com.pi4j.io.serial.SerialDataListener;
import com.pi4j.io.serial.SerialFactory;
import com.pi4j.io.serial.SerialPortException;

public class WheelDriver {

	//Constants
	private final byte MOTORCONTROLCOMMAND = (byte)0x03; //command code for motor control
	private final byte MOTORSPEEDHIGH = (byte)0x00; //Motor speed high Byte
	private final byte MOTORSPEEDLOW = (byte) 0xFF; //Motor speed low byte	
	private final byte MOTORSTOPHIGH = (byte)0x00;
	private final byte MOTORSTOPLOW = (byte)0x00;
	private final byte FORWARDHIGH = (byte)0x00;
	private final byte FORWARDLOW = (byte)0x00;
	private final byte RIGHTHIGH = (byte)0x5A;
	private final byte RIGHTLOW = (byte)0x00;
	private final byte REVERSEHIGH = (byte)0x00;
	private final byte REVERSELOW = (byte)0xB4;
	private final byte LEFTHIGH = (byte)0x01;
	private final byte LEFTLOW = (byte)0x0E;
	private final byte ROTATIONHIGH = (byte)0x00;
	private final byte ROTATIONLOW = (byte)0x00;
	private final byte NOTREQUIREDHIGH = (byte)0x00;
	private final byte NOTREQUIREDLOW = (byte)0x00;
	private final int BAUDRATE = 9600;
	private final Serial serial = SerialFactory.createInstance();

	//////////////////////////////////////////////
	//FIELDS//////////////////////////////////////
	private boolean frontRight;
	private boolean frontLeft;
	private boolean backRight;
	private boolean backLeft;
	

	//Movements command packs
	private byte [] MAKEMOVEFORWARD = {MOTORCONTROLCOMMAND, MOTORSPEEDHIGH, MOTORSPEEDLOW, FORWARDHIGH, FORWARDLOW, ROTATIONHIGH, ROTATIONLOW, NOTREQUIREDHIGH, NOTREQUIREDLOW};
	private byte [] MAKEMOVEREVERSE = {MOTORCONTROLCOMMAND, MOTORSPEEDHIGH, MOTORSPEEDLOW, REVERSEHIGH, REVERSELOW, ROTATIONHIGH, ROTATIONLOW, NOTREQUIREDHIGH, NOTREQUIREDLOW};
	private byte [] MAKEMOVELEFT = {MOTORCONTROLCOMMAND, MOTORSPEEDHIGH, MOTORSPEEDLOW, LEFTHIGH, LEFTLOW, ROTATIONHIGH, ROTATIONLOW, NOTREQUIREDHIGH, NOTREQUIREDLOW};
	private byte [] MAKEMOVERIGHT = {MOTORCONTROLCOMMAND, MOTORSPEEDHIGH, MOTORSPEEDLOW, RIGHTHIGH, RIGHTLOW, ROTATIONHIGH, ROTATIONLOW, NOTREQUIREDHIGH, NOTREQUIREDLOW};
	private byte [] STOPMOVE = {MOTORCONTROLCOMMAND, MOTORSTOPHIGH, MOTORSTOPLOW, FORWARDHIGH, FORWARDLOW, ROTATIONHIGH, ROTATIONLOW, NOTREQUIREDHIGH, NOTREQUIREDLOW};

	
	//CONSTRUCTORS////////////////////////////////
	
	public WheelDriver(){
		setFrontRight(false);
		setFrontLeft(false);
		setBackRight(false);
		setBackLeft(false);
	}

	
	//MOVEMENT METHODS////////////////////////////
	
	/**
	 * Moves the bot x distance at y speed
	 * @param distance the distance that the bot will move (0+)
	 */
	public void moveForwardDistance(int dist){
		
	}
	
	/**
	 * Moves the bot x distance at y speed
	 * @param dist distance the distance that the bot will move (0+)
	 */
	public void moveBackwardDistance(int dist){
		
	}
	
	/**
	 * Moves the bot x distance at y speed
	 * @param dist distance the distance that the bot will move (0+)
	 */
	public void moveLeftDistance(int dist){
		
	}
	
	/**
	 * Moves the bot x distance at y speed
	 * @param dist distance the distance that the bot will move (0+)
	 */
	public void moveRightDistance(int dist){
		
	}
	
	/**
	 * Start all wheels in the forward direction.
	 * @param speed The speed in which the bot will move (0-255)
	 */
	public void moveForward(){
		openSerial();
		serial.write(MAKEMOVEFORWARD);
		serial.flush();
		closeSerial();
	}
	
	/**
	 * Start all wheels in the reverse direction.
	 * @param speed The speed in which the bot will move (0-255)
	 */
	public void moveBackward(){
		openSerial();
    		serial.write(MAKEMOVEREVERSE);
		serial.flush();
		closeSerial();
	}
	
	/**
	 * Start front wheels and back wheels in the opposite direction
	 * Causing the robot to slide to the left using Omniwheels
	 * @param speed The speed in which the bot will move (0-255)
	 */
	public void moveLeft(){
		openSerial();
		serial.write(MAKEMOVELEFT);
		serial.flush();
		closeSerial();
	}
	
	/**
	 * Start front wheels and back wheels in the opposite direction
	 * Causing the robot to slide to the right using Omniwheels
	 * @param speed The speed in which the bot will move (0-255)
	 */
	public void moveRight(){
	 	openSerial();
	 	serial.write(MAKEMOVERIGHT);
    		serial.flush();
		closeSerial();
	}
	
	/**
	 * Start the left wheels and right wheels in the opposite direction
	 * Causing the robot to turn left.
	 * LEFT REVERSE
	 * RIGHT FORWARD
	 * @param speed The speed in which the bot will move (0-255)
	 */
	public void turnLeft(){
		
	}
	
	
	/**
	 * Start the left wheels and right wheels in the opposite direction
	 * Causing the robot to turn right.
	 * LEFT FORWARD
	 * RIGHT REVERSE 
	 * @param speed The speed in which the bot will move (0-255)
	 */
	public void turnRight(){
		
	}
	
	/**
	 * Stop all wheel movement
	 */
	public void stopMovement(){
	 	openSerial();
		serial.write(STOPMOVE);
		serial.flush();
		closeSerial();
	}

	//GETTERS AND SETTERS/////////////////////////
	public String ToString(){
		return "WHEEL DRIVER: output";
	}

	/**
	 * @return the frontRight
	 */
	public boolean isFrontRight() {
		return frontRight;
	}

	/**
	 * @param frontRight the frontRight to set
	 */
	public void setFrontRight(boolean frontRight) {
		this.frontRight = frontRight;
	}

	/**
	 * @return the frontLeft
	 */
	public boolean isFrontLeft() {
		return frontLeft;
	}

	/**
	 * @param frontLeft the frontLeft to set
	 */
	public void setFrontLeft(boolean frontLeft) {
		this.frontLeft = frontLeft;
	}

	/**
	 * @return the backRight
	 */
	public boolean isBackRight() {
		return backRight;
	}

	/**
	 * @param backRight the backRight to set
	 */
	public void setBackRight(boolean backRight) {
		this.backRight = backRight;
	}

	/**
	 * @return the backLeft
	 */
	public boolean isBackLeft() {
		return backLeft;
	}

	/**
	 * @param backLeft the backLeft to set
	 */
	public void setBackLeft(boolean backLeft) {
		this.backLeft = backLeft;
	}

	/**
	* Open the serial port if it is not already open
	*/
	private void openSerial()
	{
		try
		{
			if (!serial.isOpen())
			{
				serial.open(Serial.DEFAULT_COM_PORT, BAUDRATE);
			}
		}
		catch(Exception ex)
		{
			
		}
	}

	/**
	* Close the serial port
	*/
	private void closeSerial()
	{
		try
		{
			if (!serial.isClosed())
			{
				serial.close();
			}
		}
		catch (Exception ex)
		{
			
		}
	}
}

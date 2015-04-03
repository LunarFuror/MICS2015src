/**
 * WheelDriver for our robot. This will handle ONLY movement. This should (hopefully) be able to be used w/o any sensors.
 * All movement LOGIC (search for ball, follow line, etc), and sensor handling should be done in another file.
 * Fill in anything as needed, make clear notes either in here or preferrably on the Git.
 * Feel free to add your name to the author list when changes are made.
 * @author Grayson Lorenz
 * @author Brett Knecht
 */

// add imports to make the things do the stuff -bk
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;

public class WheelDriver {

	//Constants
	private final byte STOP_BYTE = 0x00;
	private final byte FORWARD_BYTE = 0x01;
	private final byte BACKWARD_BYTE = 0x02;
	private final byte LEFT_BYTE = 0x03;
	private final byte RIGHT_BYTE = 0x04;
	private final byte SPIN_RIGHT_BYTE = 0x05;
	private final byte SPIN_LEFT_BYTE = 0x06;
	private final byte MOTOR_FULL = (byte) 0xFF;
	private final byte MOTOR_HALF = (byte) 0x0F;
	private final byte MOTOR_OFF = (byte) 0x00;
	private final byte MOTOR_LEFTRIGHT_DEFAULT = (byte)0x0C;
	private final byte MOTOR_FORWARD_BACKWARD_DEFAULT = (byte) 0xFF;
	private final byte MOTOR_SPIN_DEFAULT_SPEED = (byte)0x0F;
	
	//will be used to store the bus
	private I2CDevice motorController;
	
	//byte array to send for motor commands
	private byte [] motorCommand = {STOP_BYTE, MOTOR_OFF};

	//////////////////////////////////////////////
	//FIELDS//////////////////////////////////////
	private boolean frontRight;
	private boolean frontLeft;
	private boolean backRight;
	private boolean backLeft;
	
	//CONSTRUCTORS////////////////////////////////
	
	public WheelDriver(I2CDevice theArduino){
		motorController = theArduino;
		setFrontRight(false);
		setFrontLeft(false);
		setBackRight(false);
		setBackLeft(false);
	}

	
	//MOVEMENT METHODS////////////////////////////
	/**
	* Send command to make bot move in given direction with given wheel speed
	* @direction Should be one of the constants of FORWARD_BYTE, LEFT_BYTE, RIGHT_BYTE, BACKWARD_BYTE
	* @Speed byte value between 0 and 255 for speed 255 max 0 is no motor.
	*/
	public void makeMove(byte direction, byte speed)
	{
		motorCommand[0] = direction;
		motorCommand[1] = speed;
		motorController.write(motorCommand, 0, motorCommand.length);
	}
	
	/**
	 * Start all wheels in the forward direction.
	 * @param speed The speed in which the bot will move (0-255)
	 */
	public void moveForward(){
		makeMove(FORWARD_BYTE, MOTOR_FORWARD_BACKWARD_DEFAULT);
	}
	
	/**
	 * Start all wheels in the reverse direction.
	 * @param speed The speed in which the bot will move (0-255)
	 */
	public void moveBackward(){
		makeMove(BACKWARD_BYTE, MOTOR_FORWARD_BACKWARD_DEFAULT);
	}
	
	/**
	 * Start front wheels and back wheels in the opposite direction
	 * Causing the robot to slide to the left using Omniwheels
	 * @param speed The speed in which the bot will move (0-255)
	 */
	public void moveLeft(){
		makeMove(LEFT_BYTE, MOTOR_LEFTRIGHT_DEFAULT);
	}
	
	/**
	 * Start front wheels and back wheels in the opposite direction
	 * Causing the robot to slide to the right using Omniwheels
	 * @param speed The speed in which the bot will move (0-255)
	 */
	public void moveRight(){
	 	makeMove(RIGHT_BYTE, MOTOR_LEFTRIGHT_DEFAULT);
	}
	
	/**
	 * Stop all wheel movement
	 */
	public void stopMovement(){
	 	makeMove(STOP_BYTE, MOTOR_OFF);
	}
	
	/**
	 * Start the left wheels and right wheels in the opposite direction
	 * Causing the robot to turn left.
	 * LEFT REVERSE
	 * RIGHT FORWARD
	 * @param speed The speed in which the bot will move (0-255)
	 */
	public void turnLeft(){
		makeMove(SPIN_LEFT_BYTE, MOTOR_SPIN_DEFAULT_SPEED);
	}
	
	
	/**
	 * Start the left wheels and right wheels in the opposite direction
	 * Causing the robot to turn right.
	 * LEFT FORWARD
	 * RIGHT REVERSE 
	 * @param speed The speed in which the bot will move (0-255)
	 */
	public void turnRight(){
		makeMove(SPIN_RIGHT_BYTE, MOTOR_SPIN_DEFAULT_SPEED);
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
}

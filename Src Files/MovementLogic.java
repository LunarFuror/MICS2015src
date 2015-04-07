/**
 * This file will handle any extra movement logic we may need. 
 * This is where finding the line with sensors, rotation to line up, and other fancy business can be done.
 */
 
	/**
	 * @author austin
	 *
	 */
	@SuppressWarnings("static-access")
public class MovementLogic {
	//////////////////////////////////////////////
	//FIELDS//////////////////////////////////////
	public static WheelDriver wheels;
	public static int speed;
	public static int dist;
	public static boolean touchFrontRight;
	public static boolean touchFrontLeft;
	public static boolean touchBackRight;
	public static boolean touchBackLeft;
	public static int colorFront;
	public static int colorRear;
	public static int sensors = 0;
	public static USBSend send;
	//  ints for the color sensors: 0 white, 1 = red , 2 = black
	
	
	//CONSTRUCTORS////////////////////////////////
	public MovementLogic(){
		send = new USBSend();
		speed = 100;
		//wheels = new WheelDriver();
		touchFrontRight = false;
		touchFrontLeft = false;
		touchBackRight = false;
		touchBackLeft = false;
		
		colorFront = 0; 
		colorRear = 0; 
	}
	
	//ACTION METHODS//////////////////////////////
	
	/*
	 *	Just a thing to test the sensors with rolling frame or w/e we need it for
	 */
	public static void test(){
	
	updateSensors();
	
//		wheels.moveRight();
//		while(!touchFrontRight()){}
//		wheels.stopMovement();
//		
//		wheels.moveBackward();
//		while(!touchFrontRight()){}
//		wheels.stopMovement();
//		
//		wheels.moveLeft();
//		while(!touchFrontRight()){}
//		wheels.stopMovement();
	}
	
	/**
	 * @Author Austin Duppong 
	 * 
	 * Starting in the left corner it will move right collecting balls along the wall until the sensor returns a true value.
	 */
	public static void startFindBall(){
		wheels.moveRight();
		while(!touchFrontRight()){}
		wheels.stopMovement();
	}
	
	/**
	 * Will align the robot to the hoop after picking up the balls. This is done by finding the center line and then moving forward x distance
	 * to be in range of the hoop.
	 */
	public static void alignToHoop(){
		wheels.moveLeft();
		while(colorFront() == 0){}
		wheels.moveLeft();
		while(colorFront() == 0){}
		wheels.moveForward();
		while(colorFront() == 2){}
		wheels.stopMovement();
	}
	
	/**
	 * Will move the robot to the left wall and down to the back wall to reset its position after the shots.
	 */
	public static void resetAfterShots(){
		wheels.moveLeft();
		while( !touchFrontLeft()){}
		wheels.moveBackward();
		
	}
	
	
	/**
	 * 
	 * While rear and front color sensors read black robot will move forward.
	 * This is for moving up the center line
	 */
	public static void followCenterLineForward(){
		wheels.moveForward();
		while(colorFront() == 2){}
		wheels.stopMovement();
	}
	
	
	/**
	 * 
	 * While rear and front color sensors read black robot will move backward.
	 * This is for moving backward on the center line.
	 */
	public static void followCenterLineBackward(){
		wheels.moveBackward();
		while(colorFront() == 2 ){}
		wheels.stopMovement();
	}

	/**
	 * Will move right until front color sensor reads something other than default value.
	 * For finding the right line from the right wall.
	 */
	public static void findRightLineFromRight(){
		wheels.moveLeft();
		while(colorFront() == 0){} 
		wheels.stopMovement();	
	}
	
	/**
	 * Will move right until front and rear color sensor reads something other than default value.
	 * For finding the Center line from the right wall.
	 */
	public static void findCenterLineFromRight(){
		wheels.moveLeft();
		while(colorFront() == 0){}
		wheels.stopMovement();
	}
	
	/**
	 * Decodes the Sensor data (sensors)
	 * Sets local sensor var's
	 */
	public static void decode() {
		// Does the decoding, either add another global variable that this
		// modifies, or find a way to use this in your own way.
		while (sensors != 0) {
			// COLOR BACK
			if ((sensors - 128) >= 0) {
				sensors -= 128;
				System.out.print("COLOR BACK : BLACK ");
			} else if ((sensors - 64) >= 0) {
				sensors -= 64;
				System.out.print("COLOR BACK : RED ");
			} else{
				System.out.print("COLOR BACK : white ");
			}
			
			// COLOR FRONT
			if ((sensors - 32) >= 0) {
				sensors -= 32;
				System.out.print("COLOR FRONT : BLACK ");
				colorFront = 2;
			} else if ((sensors - 16) >= 0) {
				sensors -= 16;
				System.out.print("COLOR FRONT : RED ");
				colorFront = 1;
			} else{
				System.out.print("COLOR FRONT : white ");
				colorFront = 0;
				}
			
			// TOUCH BACK LEFT
			if ((sensors - 8) >= 0) {
				sensors -= 8;
				System.out.print("TOUCH BACK LEFT : TRUE ");
				touchBackLeft = true;
			} else{
				System.out.print("TOUCH BACK LEFT : false ");
				touchBackLeft = false;
				}
			
			// TOUCH BACK RIGHT
			if ((sensors - 4) >= 0) {
				sensors -= 4;
				System.out.print("TOUCH BACK RIGHT : TRUE ");
				touchBackRight = true;
			} else{
				System.out.print("TOUCH BACK RIGHT : false ");
				touchBackRight = false;
				}
			
			// TOUCH FRONT LEFT
			if ((sensors - 2) >= 0) {
				sensors -= 2;
				System.out.print("TOUCH FRONT LEFT : TRUE ");
				touchFrontLeft = true;
			} else{
				System.out.print("TOUCH FRONT LEFT : false ");
				touchFrontLeft = false;
				}
			
			// TOUCH FRONT RIGHT
			if ((sensors - 1) >= 0) {
				sensors -= 1;
				System.out.print("TOUCH FRONT RIGHT : TRUE ");
				touchFrontRight = true;
			} else{
				System.out.print("TOUCH FRONT RIGHT : false ");
				touchFrontRight = false;
				}
				
				System.out.println("");
		}
	}
	
	//GETTERS AND SETTERS/////////////////////////
	
	/*
	 *	Get's the sensor code from the NXT
	 *	Decodes the sensor code & sets the local sensor data
	 */
	public static void updateSensors(){
		setSensors();
		decode();
	}
	
	/*
	 *	Updates sensors
	 *	Returns local sensor data (mostly for loops)
	 */
	public static int colorFront() {
		updateSensors();
		return colorFront;
	}
	
	/*
	 *	Updates sensors
	 *	Returns local sensor data (mostly for loops)
	 */
	public static boolean touchBackRight(){
		updateSensors();
		return touchBackRight;
	}
	
	/*
	 *	Updates sensors
	 *	Returns local sensor data (mostly for loops)
	 */
	public static boolean touchBackLeft(){
		updateSensors();
		return touchBackLeft;
	}
	
	/*
	 *	Updates sensors
	 *	Returns local sensor data (mostly for loops)
	 */
	public static boolean touchFrontLeft(){
		updateSensors();
		return touchFrontLeft;
	}
	
	/*
	 *	Updates sensors
	 *	Returns local sensor data (mostly for loops)
	 */
	public static boolean touchFrontRight(){
		updateSensors();
		return touchFrontRight;
	}
	
	public static void setSensors(){
		sensors=send.getSensor();
	}
	
	/*
	 *	Updates sensors
	 *	Returns local sensor data (mostly for loops)
	 */
	public static String ToString(){
		return "MOVEMENT: output";
	}
	
}

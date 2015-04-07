/**
 * This file will handle any extra movement logic we may need. 
 * This is where finding the line with sensors, rotation to line up, and other fancy business can be done.
 */
 
	/**
	 * @author austin
	 *
	 */
	public class MovementLogic {
	//////////////////////////////////////////////
	//FIELDS//////////////////////////////////////
	public WheelDriver wheels;
	public int speed;
	public int dist;
	public boolean touchFrontRight;
	public boolean touchFrontLeft;
	public boolean touchBackRight;
	public boolean touchBackLeft;
	public int colorFront;
	public int colorRear;
	public int sensors = 0;
	public int lastColor;
	public USBSend send;
	//  ints for the color sensors: 0 white, 1 = red , 2 = black
	
	
	//CONSTRUCTORS////////////////////////////////
	public MovementLogic(WheelDriver wd){
		send = new USBSend();
		speed = 100;
		wheels = wd;
		touchFrontRight = false;
		touchFrontLeft = false;
		touchBackRight = false;
		touchBackLeft = false;
		
		colorFront = 0; 
		colorRear = 0; 
		
		lastColor = 0;
	}
	
	//ACTION METHODS//////////////////////////////
	
	/*
	 *	Just a thing to test the sensors with rolling frame or w/e we need it for
	 */
	public void test(){

	}
	
	public void findBall(){
		wheels.moveRight();
		while(!touchFrontRight() && !touchBackRight()){}
		wheels.stopMovement();
	}
	

	public void moveToHoop(){
		wheels.moveLeft();
		while(colorFront() != 2){}
		lastColor = colorFront();
		
		wheels.moveLeft();
		while(colorFront() == lastColor){}
		lastColor = 0;
		
		wheels.moveLeft();
		while(colorFront() != 2){}
		
		wheels.moveForward();
		while(colorFront() != 1){}
		
		wheels.moveBackward();
		while(colorFront() == 1){}
		wheels.stopMovement();
	}

	public void followCenterLineForward(){
		//PUT USEFUL SHIT HERE
//		wheels.moveForward();
//		while(colorFront() == 2){}
//		wheels.stopMovement();
	}

	public void sendDeploySignal(){
		
	}
	
	public void sendGatherSignal(){
		
	}
	
	public void moveToStart(){
		wheels.moveLeft();
		while(!touchFrontLeft() && !touchBackLeft()){}
		
		wheels.moveBackward();
		while(!touchBackLeft() && !touchBackRight()){}
		wheels.stopMovement();
	}
	
	///////////////////////////////////////////////////////////////////////
	/**
	 * Decodes the Sensor data (sensors)
	 * Sets local sensor var's
	 */
	public void decode() {
		// Does the decoding, either add another global variable that this
		// modifies, or find a way to use this in your own way.
		while (sensors != 0) {
			// COLOR BACK unused
//			if ((sensors - 128) >= 0) {
//				sensors -= 128;
//				System.out.print("COLOR BACK : BLACK ");
//			} else if ((sensors - 64) >= 0) {
//				sensors -= 64;
//				System.out.print("COLOR BACK : RED ");
//			} else{
//				System.out.print("COLOR BACK : white ");
//			}
			
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
	public void updateSensors(){
		setSensors();
		decode();
	}
	
	/*
	 *	Updates sensors
	 *	Returns local sensor data (mostly for loops)
	 */
	public int colorFront() {
		updateSensors();
		return colorFront;
	}
	
	/*
	 *	Updates sensors
	 *	Returns local sensor data (mostly for loops)
	 */
	public boolean touchBackRight(){
		updateSensors();
		return touchBackRight;
	}
	
	/*
	 *	Updates sensors
	 *	Returns local sensor data (mostly for loops)
	 */
	public boolean touchBackLeft(){
		updateSensors();
		return touchBackLeft;
	}
	
	/*
	 *	Updates sensors
	 *	Returns local sensor data (mostly for loops)
	 */
	public boolean touchFrontLeft(){
		updateSensors();
		return touchFrontLeft;
	}
	
	/*
	 *	Updates sensors
	 *	Returns local sensor data (mostly for loops)
	 */
	public boolean touchFrontRight(){
		updateSensors();
		return touchFrontRight;
	}
	
	public void setSensors(){
		sensors=send.getSensor();
	}
	
	/*
	 *	Updates sensors
	 *	Returns local sensor data (mostly for loops)
	 */
	public String ToString(){
		return "MOVEMENT: output";
	}
	
}

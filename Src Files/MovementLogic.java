/**
 * This file will handle any extra movement logic we may need. 
 * This is where finding the line with sensors, rotation to line up, and other fancy business can be done.
 * @author Grayson Lorenz
 *
 */
public class MovementLogic {
	//////////////////////////////////////////////
	//FIELDS//////////////////////////////////////
	public static WheelDriver wheels;
	public static int speed;
	public static boolean touchRight;
	public static boolean touchLeft;
	public static int colorFront;
	public static int colorRear;
	//  ints for the color sensors: 0 default, 1 = red , 2 = black
	
	
	//CONSTRUCTORS////////////////////////////////
	public MovementLogic(){
		speed = 100;
		wheels = new WheelDriver();
		touchRight = false;
		touchLeft = false;
		colorFront = 0; 
		colorRear = 0; 
	}
	
	//ACTION METHODS//////////////////////////////
	
	/**
	 * @Author Austin Duppong
	 * 
	 * 
	 * Starts the robot moving right until the sensor hits the opposite wall.
	 */
	public static void startFindBall(){
		wheels.moveRight(speed);
		while(!touchRight){}
		wheels.stopMovement();
		 
		}
	/**
	 * 
	 * While rear and front color sensors read black robot will move forward.
	 */
	public static void followLineForward(){
		wheels.moveForward(speed);
		while(colorFront == 2 && colorRear == 2){}
		wheels.stopMovement();
	}
	/**
	 * 
	 * While rear and front color sensors read black robot will move backward.
	 */
	public static void followLineBackward(){
		wheels.moveBackward(speed);
		while(colorFront == 2 && colorRear == 2){}
		wheels.stopMovement();
	}
	
	
	/**
	 * Will move right until front color sensor reads something other than dWefault value.
	 * For finding the right line from the right wall.
	 */
	public static void findRightLineFromRight(){
		wheels.moveLeft(speed);
		while(colorFront == 0){} 
		wheels.stopMovement();
		// need to move back sensor over line not sure how at the moment
		
	}
	/**
	 * Will move right until front color sensor reads something other than default value.
	 * For finding the Center line from the right wall.
	 */
	public static void findCenterLineFromRight(){
		wheels.moveRight(speed);
		while(colorFront == 0){}
		
	}
	
	
	

	
	
	//GETTERS AND SETTERS/////////////////////////
	public static String ToString(){
		return "MOVEMENT: output";
	}
	
}

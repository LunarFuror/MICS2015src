/**
 * This file will handle any extra movement logic we may need. 
 * This is where finding the line with sensors, rotation to line up, and other fancy business can be done.
 * @author Grayson Lorenz
 *
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
	public static boolean touchRight;
	public static boolean touchLeft;
	public static int colorFront;
	public static int colorRear;
	//  ints for the color sensors: 0 white, 1 = red , 2 = black
	
	
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
	 * Starting in the left corner it will move right collecting balls along the wall until the sensor returns a true value.
	 */

	public static void startFindBall(){
		wheels.moveRight();
		while(!touchRight){}
		wheels.stopMovement();
 
	}
	
	/**
	 * Will align the robot to the hoop after picking up the balls. This is done by finding the center line and then moving forward x distance
	 * to be in range of the hoop.
	 */
	public static void alignToHoop(){
		wheels.moveLeft();
		while(colorFront == 0 && colorRear == 0){}
		wheels.moveForward();
		while(colorFront != 1){}
		wheels.stopMovement();
	}
	
	/**
	 * Will move the robot to the left wall and down to the back wall to reset its position after the shots.
	 */
	public static void resetAfterShots(){
		wheels.moveLeft();
		while( !touchLeft){}
		wheels.moveBackward();
		
	}
	
	
	/**
	 * 
	 * While rear and front color sensors read black robot will move forward.
	 * This is for moving up the center line
	 */
	public static void followCenterLineForward(){
		wheels.moveForward();
		while(colorFront == 2 && colorRear == 2){}
		wheels.stopMovement();
	}
	
	
	/**
	 * 
	 * While rear and front color sensors read black robot will move backward.
	 * This is for moving backward on the center line.
	 */
	public static void followCenterLineBackward(){
		wheels.moveBackward();
		while(colorFront == 2 && colorRear == 2){}
		wheels.stopMovement();
	}

	/**
	 * Will move right until front color sensor reads something other than default value.
	 * For finding the right line from the right wall.
	 */
	public static void findRightLineFromRight(){
		wheels.moveLeft();
		while(colorFront == 0){} 
		wheels.stopMovement();
		
		
	}
	/**
	 * Will move right until front and rear color sensor reads something other than default value.
	 * For finding the Center line from the right wall.
	 */
	public static void findCenterLineFromRight(){
		wheels.moveLeft();
		while(colorFront == 0 && colorRear == 0){}
		wheels.stopMovement();
		
	}

	

	
	
	//GETTERS AND SETTERS/////////////////////////
	public static String ToString(){
		return "MOVEMENT: output";
	}
	
}

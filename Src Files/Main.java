/**
 * The main file for the robot, this should be where all the logic and juicy bits are.
 * Fill in anything as needed, make clear notes either in here or preferrably on the Git.
 * Feel free to add your name to the author list when changes are made.
 * Remember: Someone has to read this.
 * @author Grayson Lorenz
 *
 */
public class Main {
	
	public static void main(String[] args) {
		
		USBSend send = new USBSend();
		for(int i = 0; i < 500; i++){
			System.out.println(send.getSensor());
		}
		send.closeStreams();
	}
	
	//////////////////////////////////////////////
	//METHODS/////////////////////////////////////
	
}

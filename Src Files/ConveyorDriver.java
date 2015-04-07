/**
 * ConveyorDriver for our robot. This will handle ONLY movement. This should (hopefully) be able to be used w/o any sensors.
 * All movement LOGIC (move ball until in place, distribute ball to hoop, etc), and sensor handling should be done in another file.
 * Fill in anything as needed, make clear notes either in here or preferrably on the Git.
 * Feel free to add your name to the author list when changes are made.
 * @author Grayson Lorenz
 *
 */

// add imports to make the things do the stuff -bk
import com.pi4j.io.i2c.I2CDevice;

public class ConveyorDriver {

	//Constants///////////////////////////////////
	
	private final byte STOP_BYTE = 0x07;
	private final byte LOAD_BYTE = 0x08;
	private final byte RELEASE_BYTE = 0x09;
	
	//will be used to store the bus
	private I2CDevice motorController;
		
	//CONSTRUCTORS////////////////////////////////
	public ConveyorDriver(I2CDevice theArduino){
	motorController = theArduino;	
	}
	
	//ACTION METHODS//////////////////////////////
	public void sendStop()
	{
		try
		{
			motorController.write(STOP_BYTE);
		}
		catch(Exception ex)
		{
			System.out.println("Error in ConveyorDriver Stop Message");
		}
	}
	public void sendLoad()
	{
		try
		{
			motorController.write(LOAD_BYTE);
		}
		catch(Exception ex)
		{
			System.out.println("Error in ConveyorDriver Load Message");
		}
	}
	public void sendRelease()
	{
		try
		{
			motorController.write(RELEASE_BYTE);
		}
		catch(Exception ex)
		{
			System.out.println("Error in ConveyorDriver Release Message");
		}
	}

	
	//GETTERS AND SETTERS/////////////////////////
	public String ToString(){
		return "CONVEYOR DRIVER: output";
	}
}

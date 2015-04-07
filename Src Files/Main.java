/**
 * The main file for the robot, this should be where all the logic and juicy
 * bits are. Fill in anything as needed, make clear notes either in here or
 * preferrably on the Git. Feel free to add your name to the author list when
 * changes are made. Remember: Someone has to read this.
 * 
 * @author Grayson Lorenz
 *
 */
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
public class Main {
	
	public static void main(String[] args) {
		try {
			final I2CBus bus = I2CFactory.getInstance(I2CBus.BUS_1);
			I2CDevice arduino = bus.getDevice(0x04);
			
			WheelDriver wheelDrive = new WheelDriver(arduino);
			ConveyorDriver conveyor = new ConveyorDriver(arduino);
			MovementLogic move = new MovementLogic(wheelDrive);
			
			while(true){
				conveyor.sendLoad();
				move.findBall();
				move.moveToHoop();
				conveyor.sendRelease();
				Thread.sleep(3000);
				move.moveToStart();
			}
			
			
			//PUT ALL THE SHIT HERE
//			USBSend send = new USBSend();
//			for (int i = 0; i < 500; i++) {
//				System.out.println(send.getSensor());
//			}
//			send.closeStreams();
		} catch (Exception ex) {
			
		}
	}
}

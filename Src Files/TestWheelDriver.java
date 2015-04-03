import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import java.util.Scanner;

public class TestWheelDriver
{

	public static void main(String [] args)
	{
		try
		{
			Scanner in = new Scanner(System.in);
			final I2CBus bus = new I2CFactory.getInstance(I2CBus.BUS_1);
			I2CDevice arduino = bus.getDevice(0x04);
		
			WheelDriver wheelDrive = new WheelDriver(arduino);
			
			while (true)
			{
				System.out.println("Select what you would like me to do.")
				System.out.println("q - stop motor, w - Up, s - Down, a - Left, d - Right, z - rotate left, x - rotate right, e - load conveyor, t - stop conveyor, r - release");
				char com = in.next().charAt(0);
				
				switch(com)
				{
					case 'q':
						wheelDrive.stopMovement();
						break;
					case 'w':
						wheelDrive.moveForward();
						break;
					case 's':
						wheelDrive.moveBackward();
						break;
					case 'a':
						wheelDrive.moveLeft();
						break;
					case 'd':
						wheelDrive.moveRight();
						break;
					case 'z':
						wheelDrive.turnLeft();
						break;
					case 'x':
						wheelDrive.turnRight();
						break;
					case 'e':
						break;
					case 't':
						break;
					case 'r':
						break;
					default:
						System.out.println("Invalid input");
						break;
				}
				
			}
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		
	}
}
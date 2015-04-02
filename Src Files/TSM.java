package Robot;
import lejos.robotics.Touch;
import lejos.nxt.ADSensorPort;
import lejos.nxt.SensorConstants;

/**
 * Interface for the HiTechnic NXT Touch Sensor Multiplexer
 * More or less a copy of the Interface for the Mindsensors Touch Multiplexer by Andy
 * http://sourceforge.net/p/lejos/code/HEAD/tree/trunk/classes/src_shared/lejos/nxt/addon/TouchMUX.java
 * 
 * Mostly untested at the moment
 *
 * @author jim
 *
 */

public class TSM {
   
   /** number of touch sensors supported by this device **/
    public static final int NUMBER_OF_SENSORS = 4;
    /** Bit ID returned by readSensors when sensor T1 is pressed **/
    public static final int ID_T1 = (1 << 0);
    /** Bit ID returned by readSensors when sensor T2 is pressed **/
    public static final int ID_T2 = (1 << 1);
    /** Bit ID returned by readSensors when sensor T3 is pressed **/
    public static final int ID_T3 = (1 << 2);
    /** Bit ID returned by readSensors when sensor T4 is pressed **/
    public static final int ID_T4 = (1 << 3);
    
    private class MuxTouchSensor implements Touch {
        
       private int id;

        /**
         * Create an object to represent one touch sensor attached to the
         * multiplexer.
         * @param touchId The id of this sensor.
         */
        MuxTouchSensor(int touchId) {
            
           id = touchId;
        }

        /**
         * Check if the sensor is pressed.
         * @return <code>true</code> if sensor is pressed, <code>false</code> otherwise.
         */
        public boolean isPressed() {
            return (readSensors() & id) != 0;
        }
    }
    
    private ADSensorPort port;

    /** Instance for the touch sensor connected to port T1 **/
    public final Touch T1 = new MuxTouchSensor(ID_T1);
    /** Instance for the touch sensor connected to port T2 **/
    public final Touch T2 = new MuxTouchSensor(ID_T2);
    /** Instance for the touch sensor connected to port T3 **/
    public final Touch T3 = new MuxTouchSensor(ID_T3);
    /** Instance for the touch sensor connected to port T4 **/
    public final Touch T4 = new MuxTouchSensor(ID_T4);

    
    /**
     * Create a object to provide access to a touch sensor multiplexer
     * @param port The NXT sensor port to which the multiplexer is attached
     */
    public TSM(ADSensorPort port)
    {
        this.port = port;
        port.setTypeAndMode(SensorConstants.TYPE_CUSTOM, SensorConstants.MODE_RAW);
    }

    /**
     * Return a Touch interface providing access to the sensor specified by the
     * given id.
     * @param id a number between 0..{@link #NUMBER_OF_SENSORS}-1
     * @return the requested Touch interface
     */
    public synchronized Touch getInstance(int id)
    {
        switch(id)
        {
            case 0:
                return T1;
            case 1:
                return T2;
            case 2:
                return T3;
            default:
                throw new IllegalArgumentException("no such touch sensor port");
        }
    }
    
    // Measured 994
    private static final int H1 = 1004;
    private static final int L1 = 984;
    // Measured 967
    private static final int H2 = 977;
    private static final int L2 = 957;
    // Measured 916
    private static final int H3 = 926;
    private static final int L3 = 906;
    // Measured 829
    private static final int H4 = 839;
    private static final int L4 = 819;
    // Measured 941
    private static final int H12 = 951;
    private static final int L12 = 931;
    // Measured 892
    private static final int H13 = 902;
    private static final int L13 = 882;
    // Measured 809
    private static final int H14 = 818;
    private static final int L14 = 801;
    // Measured 871
    private static final int H23 = 881;
    private static final int L23 = 861;
    // Measured 792
    private static final int H24 = 800;
    private static final int L24 = 784;
    // Measured 756
    private static final int H34 = 763;
    private static final int L34 = 749;
    // Measured 850
    private static final int H123 = 860;
    private static final int L123 = 840;
    // Measured 773
    private static final int H124 = 781;
    private static final int L124 = 765;
    // Measured740
    private static final int H134 = 748;
    private static final int L134 = 732;
    // Measured 710
    private static final int H1234 = 720;
    private static final int L1234 = 700;
    
    
    /**
     * Read the touch multiplexer and return a bit mask showing which sensors
     * are currently pressed. Returns ID_T1 if T1 is pressed, ID_T2 if T2 is
     * pressed, ID_T3 id T3 is pressed, ID_T4 id T4 is pressed. If more then one sensor is pressed
     * the return will be an or of the values.
     * @return bit mask showing which sensor buttons are pressed.
     */
    public int readSensors()
    {
        int ret;
        int val = port.readRawValue();
        System.out.println(val);
        /*
         * Note the following code can be used to correct the RAW value to be
         * the same as the RAW value for a Lego light sensor. Allowing the use
         * of the original Mindsensor constants.
        if (val > 200)
            val -= 200;
        else
            val = 0;
        val = (val*100)/68;
        val = 1023 - val
         */
        
        if (L1 <= val && val < H1)
            ret = ID_T1;
        else if (L2 <= val && val < H2)
            ret = ID_T2;
        else if (L12 <= val && val < H12)
            ret = ID_T1|ID_T2;
        else if (L3 <= val && val < H3)
            ret = ID_T3; 
        else if (L13 <= val && val < H13)
            ret = ID_T1|ID_T3;
        else if (L23 <= val && val < H23)
            ret = ID_T2|ID_T3;
        else if (L123 <= val && val < H123)
            ret = ID_T1|ID_T2|ID_T3;
        else if (L4 <= val && val < H4)
            ret = ID_T4;
        else if (L14 <= val && val < H14)
            ret = ID_T1|ID_T4;
        else if (L24 <= val && val < H24)
            ret = ID_T2|ID_T4;
        else if (L124 <= val && val < H124)
            ret = ID_T1|ID_T2|ID_T4;
        else if (L34 <= val && val < H34)
            ret = ID_T3|ID_T4;
        else if (L134 <= val && val < H134)
            ret = ID_T1|ID_T3|ID_T4;
        else if (L1234 <= val && val < H1234)
            ret = ID_T1|ID_T2|ID_T3|ID_T4;
        else
            ret = 0;
        return ret;
    }

}

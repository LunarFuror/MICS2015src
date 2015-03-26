//package org.lejos.pcsample.usbsend;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import lejos.pc.comm.NXTCommLogListener;
import lejos.pc.comm.NXTConnector;

/**
 * This is a PC sample. It connects to the NXT, and then sends an integer and
 * waits for a reply, 100 times.
 *
 * Compile this program with javac (not nxjc), and run it with java.
 *
 * You need pccomm.jar on the CLASSPATH and the jfantom.dll DLL or liblibnxt.so
 * shared library on the Java library path.
 *
 * Run the program by:
 *
 * java USBSend
 *
 * Your NXT should be running a sample such as USBReceive.
 *
 * @author Lawrie Griffiths
 *
 */
public class USBSend {
	public static NXTConnector conn = new NXTConnector();
	public static DataInputStream inDat;
	public static DataOutputStream outDat;
	
	public USBSend() {
	
		conn.addLogListener(new NXTCommLogListener() {
			public void logEvent(String message) {
				System.out.println("USBSend Log.listener: " + message);
			}

			public void logEvent(Throwable throwable) {
				System.out.println("USBSend Log.listener - stack trace: ");
				throwable.printStackTrace();
			}
		});
		if (!conn.connectTo("usb://")) {
			System.err.println("No NXT found using USB");
			System.exit(1);
		}
		inDat = new DataInputStream(conn.getInputStream());
		outDat = new DataOutputStream(conn.getOutputStream());
		
		//Call and get dat shit.
			//getSensor();
	}
	
	public static int getSensor(){
		int x = 0;
		try {
			outDat.writeInt(1);
			outDat.flush();
		} catch (IOException ioe) {
			System.err.println("IO Exception writing bytes");
		}
		try {
			x = inDat.readInt();
		} catch (IOException ioe) {
			System.err.println("IO Exception reading reply");
		}
		System.out.println("Sent " + 1 + " Received " + x);
		send x;
	}
	
	public static void closeStreams(){
		try {
			inDat.close();
			outDat.close();
			System.out.println("Closed data streams");
		} catch (IOException ioe) {
			System.err.println("IO Exception Closing connection");
		}
		try {
			conn.close();
			System.out.println("Closed connection");
		} catch (IOException ioe) {
			System.err.println("IO Exception Closing connection");
		}
	}
}


//DS Assignment 1: Implement multi-threaded client/server Process communication using RMI.
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Circle extends Remote {

	public double getArea(int radius) throws RemoteException;

	public double getPerimeter(int radius) throws RemoteException;
}


Command to run 
Terminal 1    javac *.java
Terminal 2    java server
Terminal 3    java client

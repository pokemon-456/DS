import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import CalculatorApp.Calculator;
import CalculatorApp.CalculatorHelper;

public class Server {
	public static void main(String[] args) {
		try {
			// create and initialize the orb
			ORB orb = ORB.init(args, null);
			// get reference to rootPOA
			POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			// activate the POAManager
			rootPOA.the_POAManager().activate();
			// create servant and register it with the ORB
			CalculatorImpl calculator = new CalculatorImpl(orb);
			// get object reference from the servant
			org.omg.CORBA.Object ref = rootPOA.servant_to_reference(calculator);
			Calculator href = CalculatorHelper.narrow(ref);
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			NameComponent[] path = ncRef.to_name("ABC");
			ncRef.rebind(path, href);
			System.out.println("Server ready and waiting...");
			while (true) {
				orb.run();
			}
		} catch (Exception e) {
			System.out.println("Server Error: " + e);
			e.printStackTrace(System.out);
		}
		System.out.println("Server Exiting...");
	}
}

ASSIGNMENT 2

Terminal 1
idlj -fall Calculator.idl
javac CalculatorApp/*.java
javac *.java


Terminal 2
tnameserv -ORBInitialPort 1050

Terminal 3
java Server -ORBInitialPort 1050 -ORBInitialHost localhost

Terminal 4
java Client -ORBInitialPort 1050 -ORBInitialHost localhost

// EchoClient.java

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;


// client program
public class BankClient
{
    public static void main(String args[])
    {
	org.omg.CORBA.Object objRef;

	try{
	    // create and initialize the ORB
	    ORB orb = ORB.init(args, null);
	    
	    // get the naming service
	    objRef = orb.resolve_initial_references("NameService");
	    NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
	    
	    // resolve the object reference from the naming service
	    //String name = "socgenial"; // id.kind
	    //objRef = ncRef.resolve_str(name);
	    
	    // convert the object reference into Bank reference

	    Bank bank = BankHelper.narrow(ncRef.resolve_str("bank1"));
	    Bank bank2 = BankHelper.narrow(ncRef.resolve_str("bank2"));

	    Account account = bank.create();

	    account.deposit(2000);
	    System.out.println(bank._toString());

	    account.withdraw(500);
	    System.out.println(bank._toString());

	    Account account2 = bank2.move(bank, account);
	    //System.out.println(account._toString()); //genere exception CORBA.OBJECT_NOT_EXIST
	    System.out.println(bank._toString());
	    System.out.println(bank2._toString());

	    
	    	    
	    
	} catch (Exception e) {
	    System.out.println("ERROR : " + e) ;
	    e.printStackTrace(System.out);
	}
    }
    
}
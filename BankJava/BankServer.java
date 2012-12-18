import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

// object implementation (servant)

class AccountImpl extends AccountPOA {

	int solde;
	UUID id;

	public AccountImpl(UUID id){
		this.id = id;
	}
	
	public String getID() {
		return id.toString();
	}

	public void deposit(int amount){
		this.solde += amount;
	}

	public void withdraw(int amount){
		this.solde -= amount;
	}

	public int balance(){
		return this.solde;
	}


	public String _toString(){
		return "[Account - id = " + this.id + " ,solde = " + this.solde + "]" ;
	}

}


class BankImpl extends BankPOA {

	private POA rootpoa;
	private Map<UUID, Account> accounts = new HashMap<UUID, Account>();
	private UUID id;

	public BankImpl(POA rootpoa) {
		this.rootpoa = rootpoa;
		id = UUID.randomUUID();
	}

	public Account create() {
		UUID accountId = UUID.randomUUID();
		AccountImpl accountImpl = new AccountImpl(accountId);
		Account newAccount = null;
		try{
			org.omg.CORBA.Object objRef = rootpoa.servant_to_reference(accountImpl);
			newAccount = AccountHelper.narrow(objRef);
			accounts.put(UUID.fromString(newAccount.getID()), newAccount);
		} 

		catch (Exception e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}

		return newAccount;

	}    

	public void destroy(Account a) {
		try{
			//accounts.values().remove(a);
			accounts.remove(UUID.fromString(a.getID()));
			byte[] b = rootpoa.reference_to_id(a);
			rootpoa.deactivate_object(b);
		}
		catch (Exception e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}
	}    

	public Account move(Bank old_bank, Account old_account) {
		int balance = old_account.balance();
		old_account.withdraw(balance);
		old_bank.destroy(old_account);

		Account newAcc = this.create();
		newAcc.deposit(balance);

		return newAcc;
	} 

	public Account lookup (String id){
		UUID searchAccountId = UUID.fromString(id);
		Account searchAccount = null;

		if(accounts.containsKey(searchAccountId))
			searchAccount = accounts.get(searchAccountId);

		return searchAccount;
	}	

	public String _toString(){
		String str = "Bank - " + this.id + "\n";

		for (Account currentAccount: accounts.values()) {
			System.out.println("BOUH");
			str +=  "\t" + currentAccount._toString();
			str += "\n";
		}
		
		return str;
	}
}




// server program
public class BankServer {

	public static void main(String args[]) {

		org.omg.CORBA.Object objRef;

		try{

			// create and initialize the ORB
			ORB orb = ORB.init(args, null);

			// get reference to rootpoa & activate the POAManager
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();

			// get the naming service
			objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			// get object reference from the servant
			BankImpl bankImpl = new BankImpl(rootpoa);
			objRef = rootpoa.servant_to_reference(bankImpl);
			ncRef.rebind(ncRef.to_name("bank1"), objRef);

			BankImpl bankImpl2 = new BankImpl(rootpoa);
			objRef = rootpoa.servant_to_reference(bankImpl2);;
			ncRef.rebind(ncRef.to_name("bank2"), objRef);

			// wait for invocations from clients
			System.out.println("BankServer ready and waiting...");
			orb.run(); // blocking...
		} 

		catch (Exception e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}

	}
}


/**
* BankPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from bank.idl
* lundi 17 décembre 2012 17 h 20 CET
*/

public abstract class BankPOA extends org.omg.PortableServer.Servant
 implements BankOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("create", new java.lang.Integer (0));
    _methods.put ("destroy", new java.lang.Integer (1));
    _methods.put ("move", new java.lang.Integer (2));
    _methods.put ("lookup", new java.lang.Integer (3));
    _methods.put ("toString", new java.lang.Integer (4));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // Bank/create
       {
         Account $result = null;
         $result = this.create ();
         out = $rh.createReply();
         AccountHelper.write (out, $result);
         break;
       }

       case 1:  // Bank/destroy
       {
         Account a = AccountHelper.read (in);
         this.destroy (a);
         out = $rh.createReply();
         break;
       }

       case 2:  // Bank/move
       {
         Bank old_bank = BankHelper.read (in);
         Account old_account = AccountHelper.read (in);
         Account $result = null;
         $result = this.move (old_bank, old_account);
         out = $rh.createReply();
         AccountHelper.write (out, $result);
         break;
       }

       case 3:  // Bank/lookup
       {
         String id = in.read_string ();
         Account $result = null;
         $result = this.lookup (id);
         out = $rh.createReply();
         AccountHelper.write (out, $result);
         break;
       }

       case 4:  // Bank/_toString
       {
         String $result = null;
         $result = this._toString ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:Bank:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Bank _this() 
  {
    return BankHelper.narrow(
    super._this_object());
  }

  public Bank _this(org.omg.CORBA.ORB orb) 
  {
    return BankHelper.narrow(
    super._this_object(orb));
  }


} // class BankPOA

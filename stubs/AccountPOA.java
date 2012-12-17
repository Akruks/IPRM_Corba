
/**
* AccountPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from bank.idl
* lundi 17 décembre 2012 17 h 20 CET
*/


// bank.idl
public abstract class AccountPOA extends org.omg.PortableServer.Servant
 implements AccountOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("getID", new java.lang.Integer (0));
    _methods.put ("deposit", new java.lang.Integer (1));
    _methods.put ("withdraw", new java.lang.Integer (2));
    _methods.put ("balance", new java.lang.Integer (3));
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
       case 0:  // Account/getID
       {
         String $result = null;
         $result = this.getID ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 1:  // Account/deposit
       {
         int amount = in.read_ulong ();
         this.deposit (amount);
         out = $rh.createReply();
         break;
       }

       case 2:  // Account/withdraw
       {
         int amount = in.read_ulong ();
         this.withdraw (amount);
         out = $rh.createReply();
         break;
       }

       case 3:  // Account/balance
       {
         int $result = (int)0;
         $result = this.balance ();
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }

       case 4:  // Account/_toString
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
    "IDL:Account:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Account _this() 
  {
    return AccountHelper.narrow(
    super._this_object());
  }

  public Account _this(org.omg.CORBA.ORB orb) 
  {
    return AccountHelper.narrow(
    super._this_object(orb));
  }


} // class AccountPOA

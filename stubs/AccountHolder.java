
/**
* AccountHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from bank.idl
* mercredi 7 novembre 2012 16 h 28 CET
*/


// bank.idl
public final class AccountHolder implements org.omg.CORBA.portable.Streamable
{
  public Account value = null;

  public AccountHolder ()
  {
  }

  public AccountHolder (Account initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = AccountHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    AccountHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return AccountHelper.type ();
  }

}


public class InvalidBankAccountException extends Exception
{
	public InvalidBankAccountException()
	{
		super("Sorry! You don't have enough money for shopping today. See you later!");
	}
	public InvalidBankAccountException( String message)
	{
		super(message);
	}

}

package personnel;

public class dateIncorrect extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public dateIncorrect()
	{
		super("La date d'arrivée ne peut pas être superieur à la date de départ");
		System.err.println("La date d'arrivée doit être obligatoirement inférieur à la date de départ");
	}
}

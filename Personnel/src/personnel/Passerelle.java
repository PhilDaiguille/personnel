package personnel;

public interface Passerelle 
{
	public GestionPersonnel getGestionPersonnel() throws SauvegardeImpossible;
	public void sauvegarderGestionPersonnel(GestionPersonnel gestionPersonnel)  throws SauvegardeImpossible;
	// LIGUE
	public int insert(Ligue ligue) throws SauvegardeImpossible;
	public void deleteLigue(Ligue ligue) throws SauvegardeImpossible;
	public void updateLigue(Ligue ligue) throws SauvegardeImpossible;
	
	// EMPLOYE
	public int insert(Employe employe) throws SauvegardeImpossible;
	public void updateEmploye(Employe employe, String column) throws SauvegardeImpossible;
	
}

package personnel;

public interface Passerelle {
    public GestionPersonnel getGestionPersonnel();

    public void sauvegarderGestionPersonnel(GestionPersonnel gestionPersonnel) throws SauvegardeImpossible;

    // LIGUE

    public int insert(Ligue ligue) throws SauvegardeImpossible;

    public void updateLigue(Ligue ligue) throws SauvegardeImpossible;

    public void deleteLigue(Ligue ligue) throws SauvegardeImpossible;

    
    // EMPLOYE

    public int insert(Employe employe) throws SauvegardeImpossible;

    public void updateEmploye(Employe employe, String column) throws SauvegardeImpossible;

    public void deleteEmploye(Employe employe) throws SauvegardeImpossible;

    
    // ROOT / ADMIN

    public void nouveauAdmin(Employe employe) throws SauvegardeImpossible;

    public void removeAdmin(Ligue ligue) throws SauvegardeImpossible;

    public Employe Root(Employe root) throws SauvegardeImpossible;

}
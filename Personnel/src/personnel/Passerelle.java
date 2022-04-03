package personnel;

public interface Passerelle {
    public GestionPersonnel getGestionPersonnel() throws SauvegardeImpossible;

    public void sauvegarderGestionPersonnel(GestionPersonnel gestionPersonnel) throws SauvegardeImpossible;

    // LIGUE

    public int insert(Ligue ligue) throws SauvegardeImpossible;

    public void update(Ligue ligue) throws SauvegardeImpossible;

    public void delete(Ligue ligue) throws SauvegardeImpossible;

    
    // EMPLOYE

    public int insert(Employe employe) throws SauvegardeImpossible;

    public void update(Employe employe, String column) throws SauvegardeImpossible;

    public void delete(Employe employe) throws SauvegardeImpossible;


}
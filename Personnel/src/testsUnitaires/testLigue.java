package testsUnitaires;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import personnel.*;

class testLigue 
{
	GestionPersonnel gestionPersonnel = GestionPersonnel.getGestionPersonnel();
	
	@Test
	void createLigue() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		assertEquals("Fléchettes", ligue.getNom());
		
		Ligue ligueF = gestionPersonnel.addLigue("Football");
		assertEquals("Football", ligueF.getNom());
	}

	@Test
	void addEmploye() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Ligue ligueF = gestionPersonnel.addLigue("Football");
		
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty", LocalDate.now(), null);
		Employe employe1 = ligueF.addEmploye("Dupont", "Geraldine", "g.dupont@gmail.com", "qwerty", LocalDate.parse("2018-09-12") , LocalDate.parse("2023-02-23")); 
		
		assertEquals(employe, ligue.getEmployes().first());
		assertEquals(employe1, ligueF.getEmployes().last());
	}
	@Test
	void SupprimerEmploye() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Casquette");
		Employe employe = ligue.addEmploye("Bouchar", "Gérard", "g.bouchard@gmail.com", "azerty", null, null);
		
		employe.remove();
		
	}
	
}

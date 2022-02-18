package testsUnitaires;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import personnel.*;

class testEmploye
{
	GestionPersonnel gestionPersonnel = GestionPersonnel.getGestionPersonnel();
	
	@Test
	void addEmploye() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Ligue ligueF = gestionPersonnel.addLigue("Football");
		
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty", LocalDate.now(), null);
		Employe employe1 = ligueF.addEmploye("Dupont", "Geraldine", "g.dupont@gmail.com", "qwerty", LocalDate.parse("2019-11-12") , LocalDate.parse("2023-02-23")); 
		
		assertEquals(employe, ligue.getEmployes().first());
		assertEquals(employe1, ligueF.getEmployes().last());
	}
	
	@Test
	void SupprimerEmploye() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Casquette");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty", LocalDate.parse("2019-11-12"), LocalDate.parse("2020-11-12"));
		employe.remove();
		
	}
	@Test
	void isAdmin() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Football");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty", LocalDate.parse("2019-11-12"), LocalDate.parse("2020-11-12"));
		assertFalse(employe.estAdmin(ligue));
		ligue.setAdministrateur(employe);
		assertTrue(employe.estAdmin(ligue));
	}
	
	@Test
	void isRoot() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Football");
		Employe employe = gestionPersonnel.getRoot();
		Employe employe1 = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty", LocalDate.parse("2019-11-12"), LocalDate.parse("2020-11-12"));
		assertTrue(employe.estRoot());
		assertFalse(employe1.estRoot());
	}
	
	@Test
	void getNom() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Football");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty", LocalDate.parse("2018-12-11"), LocalDate.parse("2020-11-12"));
		assertEquals("Bouchard", employe.getNom());
	}

	@Test
	void setNom() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Football");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty", LocalDate.parse("2018-12-11"), null);
		employe.setNom("Samuel");
		assertEquals("Samuel", employe.getNom());
	}
	
	@Test
	void getPrenom() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Football");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty", LocalDate.parse("2018-12-11"), null);
		assertEquals("Gérard", employe.getPrenom());
	}
	
	@Test
	void setPrenom() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Football");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty", LocalDate.now(), null);
		employe.setPrenom("Gérard");
		assertEquals("Gérard", employe.getPrenom());
	}
	
	@Test
	void getMail() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Football");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty", LocalDate.parse("2018-12-11"), null);
		assertEquals("g.bouchard@gmail.com", employe.getMail());
		
	}

	@Test
	void setMail() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Football");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty", LocalDate.parse("2018-12-11"), null);
		employe.setMail("g.bouchard@gmail.com");
		assertEquals("g.bouchard@gmail.com", employe.getMail());
	}
	
	@Test
	void getLigue() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Football");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty", LocalDate.parse("2018-12-11"), null);
		assertEquals(ligue, employe.getLigue());
	}
	
	
	@Test
	void getDateDepart() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Football");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty", LocalDate.parse("2017-12-30"), LocalDate.parse("2020-11-28"));
		assertEquals(LocalDate.parse("2020-11-28"), employe.getdateDepart());
	}
	
	
	@Test
	void getDateArrive() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Football");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty", LocalDate.parse("2019-12-28"), LocalDate.parse("2020-11-09"));
		assertEquals(LocalDate.parse("2019-12-28"), employe.getdateArrivee()) ;
	}
	
	@Test
	void checkPassword() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Football");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty", LocalDate.parse("2019-11-12") , LocalDate.parse("2023-02-23"));
		assertFalse(employe.checkPassword("azerti"));
		assertTrue(employe.checkPassword("azerty"));
	}
	
	@Test
	void setPassword() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Football");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty", LocalDate.parse("2019-11-12") , LocalDate.parse("2023-02-23"));
		employe.setPassword("azerty");
		assertFalse(employe.checkPassword("azerti"));
		assertTrue(employe.checkPassword("azerty"));
	}
	
	@Test
	void remove() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Football");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty", LocalDate.parse("2019-11-12") , LocalDate.parse("2023-02-23"));
		employe.remove();
		assertEquals(0, ligue.getEmployes().size());
	}
	
	
}
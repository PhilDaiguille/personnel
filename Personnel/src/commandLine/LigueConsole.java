package commandLine;

import static commandLineMenus.rendering.examples.util.InOut.getString;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import commandLineMenus.List;
import commandLineMenus.Menu;
import commandLineMenus.Option;
import personnel.Ligue;
import personnel.Employe;
import personnel.*;

public class LigueConsole 
{
	private GestionPersonnel gestionPersonnel;
	private EmployeConsole employeConsole;

	public LigueConsole(GestionPersonnel gestionPersonnel, EmployeConsole employeConsole)
	{
		this.gestionPersonnel = gestionPersonnel;
		this.employeConsole = employeConsole;
	}

	Menu menuLigues()
	{
		Menu menu = new Menu("Gérer les ligues", "l");
		menu.add(afficherLigues());
		menu.add(ajouterLigue());
		menu.add(selectionnerLigue());
		menu.addBack("q");
		return menu;
	}

	private Option afficherLigues()
	{
		return new Option("Afficher les ligues", "l", () -> {
			
			System.out.println(gestionPersonnel.getLigues());
			
		});
	}

	private Option afficher(final Ligue ligue)
	{
		return new Option("Afficher la ligue", "l", 
				() -> 
				{
					System.out.println(ligue);
					System.out.println("administrée par " + ligue.getAdministrateur());
				}
		);
	}
	private Option afficherEmployes(final Ligue ligue)
	{
		return new Option("Afficher les employes", "l", () -> {System.out.println(ligue.getEmployes());});
	}

	private Option ajouterLigue()
	{
		return new Option("Ajouter une ligue", "a", () -> 
		{
			try
			{
				gestionPersonnel.addLigue(getString("nom : "));
			}
			catch(SauvegardeImpossible exception)
			{
				System.err.println("Impossible de sauvegarder cette ligue");
			}
		});
	}
	
	private Menu editerLigue(Ligue ligue)
	{
		Menu menu = new Menu("Editer " + ligue.getNom());
		menu.add(afficher(ligue));
		menu.add(gererEmployes(ligue));
		menu.add(changerAdministrateur(ligue));
		menu.add(changerNom(ligue));
		menu.add(supprimer(ligue));
		menu.addBack("q");
		return menu;
	}

	private Option changerNom(final Ligue ligue)
	{
		return new Option("Renommer la ligue", "r", 
				() -> {ligue.setNom(getString("Nouveau nom : "));});
	}

	private List<Ligue> selectionnerLigue()
	{
		return new List<Ligue>("Sélectionner une ligue", "e", 
				() -> new ArrayList<>(gestionPersonnel.getLigues()),
				(element) -> editerLigue(element)
					
				);
	}
	
	private Option ajouterEmploye(final Ligue ligue) throws dateIncorrect
	{
		return new Option("ajouter un employé", "a",
				() -> 
				{
					ligue.addEmploye(
						getString("nom : "), 
						getString("prenom : "), 
						getString("mail : "), 
						getString("password : "),
						getDate("Date d'arrivée YYYY-MM-JJ : "), 
						getDate("Date de depart YYYY-MM-JJ : "));
					
				}
		);
		
		
	}

	private Menu gererEmployes(Ligue ligue)
	{
		Menu menu = new Menu("Gérer les employés de " + ligue.getNom(), "e");
		menu.add(afficherEmployes(ligue));
		menu.add(ajouterEmploye(ligue));
		menu.add(ChangerEmploye(ligue));
		menu.addBack("q");
		return menu;
	}
	private Menu selectionerEmploye(Ligue ligue)
	{
		Menu menu = new Menu("Selectionner les employés de " + ligue.getNom(), "e");
		menu.add(ChangerEmploye(ligue));
		menu.addBack("q");
		return menu;
	}
	private Menu editerEmploye(Ligue ligue)
	{
		Menu menu = new Menu("Selectionner les employés de " + ligue.getNom(), "e");
		menu.add(modifierEmploye(ligue));
		menu.add(supprimerEmploye(ligue));
		menu.addBack("q");
		return menu;
	}
	
	private List<Employe> ChangerEmploye(final Ligue ligue)
	{
		return new List<Employe>("Selectionner un employé", "e", 
				() -> new ArrayList<>(ligue.getEmployes()),
				employeConsole.editerEmploye());
	}
	
	private List<Employe> supprimerEmploye(final Ligue ligue)
	{
		return new List<>("Supprimer un employé", "s", 
				() -> new ArrayList<>(ligue.getEmployes()),
				(index, element) -> {
					element.remove();}
				);
	}
	
	private List<Employe> changerAdministrateur(final Ligue ligue)
	{
		return new List<>("Changer l'administrateur d'une ligue", "a", 
				() -> new ArrayList<>(ligue.getEmployes()),
					employeConsole.editerEmploye()
				);
	}		

	private List<Employe> modifierEmploye(final Ligue ligue)
	{
		return new List<>("Modifier un employé", "e", 
				() -> new ArrayList<>(ligue.getEmployes()),
					employeConsole.editerEmploye()
				);
	}
	
	private Option supprimer(Ligue ligue)
	{
		return new Option("Supprimer la ligue", "d", () -> {
			
			ligue.remove();
			
		});
	}
	
	private LocalDate getDate(String message)
	{
		while (true) {
			try {
				String date = getString(message);
				if(date.equals("")) {
					return null;
				}
				else {
					return LocalDate.parse(null);
				}
			} catch (DateTimeParseException e) {
				System.out.println("Date incorrect");
			}
		}
	

	}
	
	
}

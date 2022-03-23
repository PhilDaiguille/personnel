package commandLine;

import static commandLineMenus.rendering.examples.util.InOut.getString;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import commandLineMenus.ListOption;
import commandLineMenus.Menu;
import commandLineMenus.Option;

import personnel.Employe;
import personnel.Ligue;
import personnel.dateIncorrect;

public class EmployeConsole {
	private Option afficher(final Employe employe) {
		return new Option("Afficher l'employé", "l", () -> {
			System.out.println(employe);
		});
	}

	ListOption<Employe> editerEmploye() {
		return (employe) -> editerEmploye(employe);
	}

	Option editerEmploye(Employe employe) {
		Menu menu = new Menu("Gérer le compte de : " + employe.getNom(), "c");
		menu.add(afficher(employe));
		menu.add(setAdmin(employe));
		menu.add(changerNom(employe));
		menu.add(changerPrenom(employe));
		menu.add(changerMail(employe));
		menu.add(changerPassword(employe));
		menu.add(changerDateArrivee(employe));
		menu.add(changerDateDepart(employe));
		menu.add(supprimerEmploye(employe));
		menu.addBack("q");
		return menu;
	}

	private Option changerNom(final Employe employe) {
		return new Option("Changer le nom", "n", () -> {
			employe.setNom(getString("Nouveau nom : "));
		});
	}

	private Option changerPrenom(final Employe employe) {
		return new Option("Changer le prénom", "p", () -> {

			employe.setPrenom(getString("Nouveau prénom : "));

		});
	}

	private Option changerMail(final Employe employe) {
		return new Option("Changer le mail", "e", () -> {

			employe.setMail(getString("Nouveau mail : "));

		});
	}

	private Option changerPassword(final Employe employe) {
		return new Option("Changer le password", "x", () -> {

			employe.setPassword(getString("Nouveau password : "));

		});
	}

	private Option setAdmin(final Employe employe) {
		Ligue ligue = employe.getLigue();
		return new Option("Mettre administrateur de la ligue", "s", () -> {
			ligue.setAdministrateur(employe);

		});
	}

	private Option supprimerEmploye(final Employe employe) {
		return new Option("Supprimer l'employé", "r", () -> {
			employe.remove();
		});

	}

	private Option changerDateArrivee(final Employe employe) {
		return new Option("Changer la date d'arrivée", "a", () -> {
			boolean verif = false;

			while (!verif) {
				try {
					String date = getString("Nouvelle date d'arrivée YYYY-MM-JJ : ");

					LocalDate dateArrivee = date.equals("") ? null : LocalDate.parse(date);
					employe.setdateArrivee(dateArrivee);
					verif = true;
				} catch (DateTimeParseException | dateIncorrect e) {
					System.out.println("Date incorrect");
				}
			}
		});
	}

	private Option changerDateDepart(final Employe employe) {
		return new Option("Changer la date de départ", "d", () -> {
			boolean verif = false;

			while (!verif) {
				try {
					String date = getString("Nouvelle date de départ YYYY-MM-JJ : ");

					LocalDate dateDepart = date.equals("") ? null : LocalDate.parse(date);
					employe.setdateDepart(dateDepart);
					verif = true;
				} catch (DateTimeParseException | dateIncorrect e) {
					System.out.println("Date incorrect");
				}
			}
		});
	}

}

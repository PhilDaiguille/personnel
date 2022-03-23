package jdbc;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Map;

import personnel.*;

public class JDBC implements Passerelle 
{
	Connection connection;

	public JDBC()
	{
		try
		{
			Class.forName(Credentials.getDriverClassName());
			connection = DriverManager.getConnection(Credentials.getUrl(), Credentials.getUser(), Credentials.getPassword());
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Pilote JDBC non installé.");
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}
	


	@Override
	public GestionPersonnel getGestionPersonnel() 
	{
		GestionPersonnel gestionPersonnel = new GestionPersonnel();
		
		
		try 
		{
			String requete = "SELECT * FROM ligue";
			Statement instruction = connection.createStatement();
			ResultSet ligues = instruction.executeQuery(requete);

			while (ligues.next())
			{
				gestionPersonnel.addLigue(ligues.getInt("id_ligue"), ligues.getString("nom_ligue"));
				PreparedStatement req = connection.prepareStatement("SELECT * FROM employe WHERE id_ligue = ?");
				req.setInt(1, ligues.getInt("id_ligue"));
				ResultSet emp = req.executeQuery();
				Ligue ligue = gestionPersonnel.getLigues().last();
				while (emp.next())
				{
					int id = emp.getInt("id_employee");
					String
						nom = emp.getString("nom"),
						prenom = emp.getString("prenom"), 
						mail = emp.getString("mail"), 
						password = emp.getString("password");
					LocalDate
						arrive = emp.getDate("date_arrivee") != null ? LocalDate.parse(emp.getString("date_arrivee")) : null,
						depart = emp.getDate("date_depart") != null ? LocalDate.parse(emp.getString("date_depart")) : null;
					Employe employe = ligue.addEmploye(nom, prenom, mail, password, arrive, depart, id);
					
					if (emp.getBoolean("habilitation"))
						ligue.setAdministrateur(employe);
				}
			}
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
		
		return gestionPersonnel;
	}

	@Override
	public void sauvegarderGestionPersonnel(GestionPersonnel gestionPersonnel) throws SauvegardeImpossible 
	{
		close();
	}
	
	public void close() throws SauvegardeImpossible
	{
		try
		{
			if (connection != null)
				connection.close();
		}
		catch (SQLException e)
		{
			throw new SauvegardeImpossible(e);
		}
	}
	/**
	 * Methode responsable pour insérer une ligue dans la base de donnée lors de sa création
	 * @param Ligue
	 * @return id de la ligue
	 */
	@Override
	public int insert(Ligue ligue) throws SauvegardeImpossible 
	{
		try 
		{
			PreparedStatement instruction;
			instruction = connection.prepareStatement("insert into ligue (nom_ligue) values(?)", Statement.RETURN_GENERATED_KEYS);
			instruction.setString(1, ligue.getNom());		
			instruction.executeUpdate();
			ResultSet id = instruction.getGeneratedKeys();
			id.next();
			return id.getInt(1);
			
		} 
		catch (SQLException exception) 
		{
			
			throw new SauvegardeImpossible(exception);
		}		
	}
	public void insertRoot(Employe employe) throws SauvegardeImpossible
	{
		try
		{
			PreparedStatement instruction;
			instruction = connection.prepareStatement("INSERT INTO employe (nom, prenom, mail, password, habilitation) VALUES (?,?,?,?,?)");
			instruction.setString(1, employe.getNom());
			instruction.setString(2, employe.getPrenom());
			instruction.setString(3, employe.getMail());
			instruction.setString(4, employe.getPass());
			instruction.setInt(5, 1);
			instruction.executeUpdate();
		}
		catch (SQLException exception)
		{
			throw new SauvegardeImpossible(exception);
		}
	}

	@Override
	public int insert(Employe employe) throws SauvegardeImpossible
	{
		try
		{
			PreparedStatement instruction;
			instruction = connection.prepareStatement("INSERT INTO employe (nom, prenom, mail, password, date_arrivee, date_depart,id_ligue) VALUES (?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			instruction.setString(1, employe.getNom());
			instruction.setString(2, employe.getPrenom());
			instruction.setString(3, employe.getMail());
			instruction.setString(4, employe.getPass());
			instruction.setDate(5, employe.getdateArrivee() == null ? null : Date.valueOf(employe.getdateArrivee()));
			instruction.setDate(6, employe.getdateDepart() == null ? null : Date.valueOf(employe.getdateDepart()));
			instruction.setInt(7, employe.getLigue().getId());
			instruction.executeUpdate();
			ResultSet id = instruction.getGeneratedKeys();
			id.next();
			return id.getInt(1);
		}
		catch (SQLException exception)
		{
		
			throw new SauvegardeImpossible(exception);
		}
	}

	/**
	 * Update a ligue
	 * @param ligue ligue to update
	 * @throws Exception If an error occurs
	 */

	@Override
	public void updateLigue(Ligue ligue) throws SauvegardeImpossible 
	{
		try
		{
			PreparedStatement instruction;
			instruction = connection.prepareStatement("UPDATE ligue SET nom_ligue = ? WHERE id_ligue = ?");
			instruction.setString(1, ligue.getNom());
			instruction.setInt(2, ligue.getId());
			instruction.executeUpdate();
		}
		catch (SQLException e) 
		{
		
			throw new SauvegardeImpossible(e);
		}
	}

	@Override
	public void deleteEmploye(Employe emp) throws SauvegardeImpossible 
	{
		try
		{
			PreparedStatement instruction;
			instruction = connection.prepareStatement("DELETE FROM employe WHERE id_employee = ?");
			instruction.setInt(1, emp.getId());
			instruction.executeUpdate();
			System.out.println("Employé " + emp.getNom() + " supprimé");
		}
		catch (SQLException e) 
		{

			throw new SauvegardeImpossible(e);
		}
	}


	
	@Override
	public void deleteLigue(Ligue ligue) throws SauvegardeImpossible 
	{
		try
		{
			PreparedStatement tableLigue;
			tableLigue = connection.prepareStatement("DELETE FROM ligue WHERE id_ligue = ?");
			tableLigue.setInt(1, ligue.getId());
			tableLigue.executeUpdate();
			System.out.println("Ligue " + ligue.getNom() + " supprimé");
		}
		catch (SQLException exception) 
		{
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}
		
	}

	@Override
	public void updateEmploye(Employe emp, String column) throws SauvegardeImpossible 
	{
		try 
		{
			PreparedStatement instruction;
			instruction = connection.prepareStatement("UPDATE employe SET " + column + "= ? WHERE id_employee = ?");
	
			Map <String, String> map = Map.of(
					"nom", emp.getNom(),
					"prenom", emp.getPrenom(),
					"mail", emp.getMail(),
					"password", emp.getPass(),
					"date_arrivee",String.valueOf(emp.getdateArrivee()),
					"date_depart", String.valueOf(emp.getdateDepart())
			);
			
			if (column.equals("date_arrivee") || column.equals("date_depart"))
				instruction.setDate(1, map.get(column).equals("null") ? null : Date.valueOf(map.get(column)));
			else
				instruction.setString(1, map.get(column));
			instruction.setInt(2, emp.getId());
			instruction.executeUpdate();
		}
		catch (SQLException e) 
		{
			throw new SauvegardeImpossible(e);
		}
	}

	@Override
	public void removeAdmin(Ligue ligue) throws SauvegardeImpossible
	{
		try
		{
			PreparedStatement tableEmploye;
			tableEmploye = connection.prepareStatement("UPDATE employe SET habilitation = 0 WHERE id_ligue = ?");
			tableEmploye.setInt(1, ligue.getId());
			tableEmploye.executeUpdate();
		}
		catch (SQLException e)
		{
			throw new SauvegardeImpossible(e);
		}
	}
	
	@Override
	public void newAdmin(Employe employe) throws SauvegardeImpossible 
	{
		try 
		{
			PreparedStatement tableEmploye;
			tableEmploye = connection.prepareStatement("UPDATE employe SET habilitation = (CASE WHEN id_employee = ? THEN 1 WHEN id_employee <> ? THEN 0 END) WHERE id_ligue = ?");
			tableEmploye.setInt(1, employe.getId());
			tableEmploye.setInt(2, employe.getId());
			tableEmploye.setInt(3, employe.getLigue().getId());
			tableEmploye.executeUpdate();
		} 
		catch (SQLException e) 
		{
			throw new SauvegardeImpossible(e);
		}
	}

	@Override
	public Employe bddRoot(Employe root) throws SauvegardeImpossible {
		try 
		{
			Statement intruction = connection.createStatement();
			String requete = "SELECT * FROM employe WHERE habilitation = 1";
			ResultSet result = intruction.executeQuery(requete);
			
			if(!result.next())
			{
				insertRoot(root);
			}
			
			else
			{
				String nom = (result.getString("nom") != null)? result.getString("nom") : "",
					   prenom = (result.getString("prenom") != null)? result.getString("prenom") : "",
					   mail = (result.getString("mail") != null) ? result.getString("mail") : "",
					   password = (result.getString("password") != null) ? result.getString("password") : "";
				root.setNom(nom);
				root.setPrenom(prenom);
				root.setMail(mail);
				root.setPassword(password);
			}
			return root;
		} 
		catch (SQLException e)
		{
			throw new SauvegardeImpossible(e);
		}
	}
}
package jdbc;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			String requete = "select * from ligue";
			Statement instruction = connection.createStatement();
			ResultSet ligues = instruction.executeQuery(requete);
			while (ligues.next())
				gestionPersonnel.addLigue(ligues.getInt(1), ligues.getString(2));
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
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}		
	}
	
	@Override
	public int insert(Employe employe) throws SauvegardeImpossible
	{
		try
		{
			PreparedStatement instruction;
			instruction = connection.prepareStatement("INSERT INTO employe (nom, prenom, mail, password, date_arrivee, date_depart, habilitation, id_ligue) VALUES (?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			instruction.setString(1, employe.getNom());
			instruction.setString(2, employe.getPrenom());
			instruction.setString(3, employe.getMail());
			instruction.setString(4, employe.getPassword());
			instruction.setDate(5, employe.getdateArrivee() == null ? null : Date.valueOf(employe.getdateArrivee()));
			instruction.setDate(6, employe.getdateDepart() == null ? null : Date.valueOf(employe.getdateDepart()));
			instruction.setInt(7, employe.getHabilitation());
			instruction.setInt(8, employe.getLigue().getId());
			instruction.executeUpdate();
			ResultSet id = instruction.getGeneratedKeys();
			id.next();
			System.out.println("Ajout employé réussi");
			return id.getInt(1);
			
		}
		catch (SQLException exception)
		{
		
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}
	}
	

	@Override
	public void updateEmploye(Employe employe, String column) throws SauvegardeImpossible {
		// TODO Auto-generated method stub
		
	}

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
			System.out.println("Update réussi");
		}
		catch (SQLException exception) 
		{
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}
	}

	@Override
	public void deleteLigue(Ligue ligue) throws SauvegardeImpossible 
	{
		try
		{
			PreparedStatement instruction;
			instruction = connection.prepareStatement("DELETE FROM ligue WHERE id_ligue = ?");
			instruction.setInt(1, ligue.getId());
			instruction.executeUpdate();
			System.out.println("Remove réussi");
		}
		catch (SQLException exception) 
		{
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}
		
	}
}

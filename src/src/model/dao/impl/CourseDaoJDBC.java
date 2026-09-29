package model.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.CourseDao;
import model.entities.Course;

public class CourseDaoJDBC implements CourseDao {
	
	private Connection conn;

	public CourseDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	/**
	 * La fonction ajoute une formation à partir de la table vf_formation.
	 * 
	 */
	@Override
	public void insert(Course obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO vf_formation " + "(fo_name, fo_description, fo_duree_jour, fo_type, fo_prix) "
					+ "VALUES " + "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getName());
			st.setString(2, obj.getDescription());
			st.setInt(3, obj.getDuration());
			st.setString(4, obj.getType());
			st.setDouble(5, obj.getPrice());
			
			int rowsAffected = st.executeUpdate();
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Erreur inattendu. Il n'y pas de ligne affectée.");

			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			
		}

	}
	
	/**
	 * La fonction cherche toutes les formations de la table vf_formation.
	 * 
	 * @return list: La fonction retourne une liste contenant des formations. 
	 * 
	 */
	@Override
	public List<Course> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT vf_formation.* FROM vf_formation");

			rs = st.executeQuery();

			List<Course> list = new ArrayList<>();

			while (rs.next()) {

				int rsIdUSer = rs.getInt(1);	//soit index(de 1 à n) de la colonne, soit le nom de la colonne
				String rsName = rs.getString(2);
				String rsDescription = rs.getString(3);
				int rsDuration = rs.getInt(4);
				String rsType = rs.getString(5);
				double rsPrice = rs.getDouble(6);
				list.add((new Course(rsIdUSer, rsName, rsDescription, rsDuration, rsType, rsPrice)));
			}
			return list;

		} catch (SQLException e) {

			throw new DbException(e.getMessage());
		} finally {

			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	/**
	 * La fonction met à jour les données de la table vf_formation.
	 * 
	 * @param Course obj : Le paramètre contient les données permettant de mettre à jour la table vf_formation.
	 * 
	 */
	@Override
	public void update(Course obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE vf_formation "
					+ "SET fo_name = ?, fo_description = ?, fo_duree_jour = ?, fo_type = ?, fo_prix = ? " + "WHERE fo_id_formation = ?");

			st.setString(1, obj.getName());
			st.setString(2, obj.getDescription());;
			st.setInt(3, obj.getDuration());
			st.setString(4, obj.getType());
			st.setDouble(5, obj.getPrice());
			
			st.setInt(6, obj.getId());
            
			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);

		}
	}
	
	/**
	 * La fonction cherche une formation à partir d'un identifant.
	 * 
	 * @param Integer id : Le paramètre permet de cherche la formation de la table vf_formation.
	 * 
	 * @return obj : La fonction retourne un objet formation.
	 * 
	 */
	@Override
	public Course findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT vf_formation.* " + "FROM vf_formation "
							+ "WHERE fo_id_formation = ?");
			st.setInt(1, id);

			rs = st.executeQuery();

			if (rs.next()) {
				int rsIdUSer = rs.getInt(1);	//soit index(de 1 à n) de la colonne, soit le nom de la colonne
				String rsName = rs.getString(2);
				String rsDescription = rs.getString(3);
				int rsDuration = rs.getInt(4);
				String rsType = rs.getString(5);
				double rsPrice = rs.getDouble(6);
				Course obj = new Course(rsIdUSer, rsName, rsDescription, rsDuration, rsType, rsPrice);
				return obj;
			}
			return null;

		} catch (SQLException e) {

			throw new DbException(e.getMessage());
		} finally {

			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	/**
	 * La fonction supprime une formation à partir d'un identifant.
	 * 
	 * @param Integer id : Le paramètre permet de chercher la formation de la table vf_formation.
	 * 
	 */
	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM vf_formation WHERE fo_id_formation = ?");
			st.setInt(1, id);
			
			int rowsAffected = st.executeUpdate();
			if(rowsAffected == 0) {
				throw new DbException("Id n'existe pas");
			}
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}
	
	/**
	 * La fonction cherche les formations à partir d'un mot clé.
	 * 
	 * @param String keyWord : Le paramètre permet de chercher les formations contenant le mot clé de la table vf_formation.
	 * 
	 * @return list : La fonction retourne une liste contenant les formations ayant le mot clé.
	 * 
	 */
	@Override
	public List<Course> findAllKeyWord(String keyWord) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT vf_formation.* FROM vf_formation WHERE fo_name LIKE ? OR fo_description LIKE ?");
			
			st.setString(1, "%" + keyWord + "%");
			st.setString(2, "%" + keyWord + "%");
			
			
			rs = st.executeQuery();

			List<Course> list = new ArrayList<>();

			while (rs.next()) {

				int rsIdUSer = rs.getInt(1);	//soit index(de 1 à n) de la colonne, soit le nom de la colonne
				String rsName = rs.getString(2);
				String rsDescription = rs.getString(3);
				int rsDuration = rs.getInt(4);
				String rsType = rs.getString(5);
				double rsPrice = rs.getDouble(6);
				list.add(new Course(rsIdUSer, rsName, rsDescription, rsDuration, rsType, rsPrice));
			}
			return list;

		} catch (SQLException e) {

			throw new DbException(e.getMessage());
		} finally {

			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	/**
	 * La fonction cherche les formations à partir du type.
	 * 
	 * @param String type : Le paramètre permet de chercher les formations contenant le type de la table vf_formation.
	 * 
	 * @return list : La fonction retourne une liste contenant les formations ayant le type.
	 * 
	 */
	@Override
	public List<Course> findAllType(String type) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT vf_formation.* FROM vf_formation WHERE LOWER(fo_type) = LOWER(?)");
			
			st.setString(1, type);


			rs = st.executeQuery();

			List<Course> list = new ArrayList<>();

			while (rs.next()) {

				int rsIdUSer = rs.getInt(1);	//soit index(de 1 à n) de la colonne, soit le nom de la colonne
				String rsName = rs.getString(2);
				String rsDescription = rs.getString(3);
				int rsDuration = rs.getInt(4);
				String rsType = rs.getString(5);
				double rsPrice = rs.getDouble(6);
				list.add((new Course(rsIdUSer, rsName, rsDescription, rsDuration, rsType, rsPrice)));
			}
			return list;

		} catch (SQLException e) {

			throw new DbException(e.getMessage());
		} finally {

			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}

package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Program {

	public static void main(String[] args) {
		Connection conn = DB.getConnection();
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO tbcursos " + "(Nome,Creditos) " + "VALUES " + "(?,?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, "Teste");
			st.setDouble(2, 40);

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					System.out.println(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		}

		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
		DB.closeConnection();
	}

}

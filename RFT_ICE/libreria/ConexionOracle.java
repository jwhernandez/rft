package libreria;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import libreria.utileria.Tipo;

import com.rational.test.ft.script.RationalTestScript;

public class ConexionOracle {
	/**
	 * @author SS
	 * To establish a database connection in scripts generated by RFT, 
	 * 1) First install the JDBC driver (Propiedades / Via de construcci�n JAVA  / Bibliotecas / A�adorJARs externos
	 * Luego de hacer esto se acepta la sentencia "Driver driver = new oracle.jdbc.OracleDriver()"
	 * 2) Create a JDBC connection class (que es esta clase)
	 * 3) Add code to scripts to use this class.
	 * http://www.ibm.com/developerworks/rational/library/05/saracevic/
	 * http://www.ibm.com/developerworks/rational/library/07/1218_gouveia/
	 */
	public static void main(String[] args) throws SQLException
	{
		new ConexionOracle();
	}

	static Statement statement = null;
	static Connection connection = null;

	public static Connection Conectar(String ConnectString, String Usuario, String Clave) throws SQLException
	{
		try 
		{
			Driver driver = new oracle.jdbc.OracleDriver();
			DriverManager.registerDriver(driver);
			connection = DriverManager.getConnection(ConnectString, Usuario, Clave);
			connection.setAutoCommit(true);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Mensaje de excepci�n: "+e.getMessage());
			Accion.infoPaso("Accion", Tipo.SYSERR,"Conexi�n Oracle", e.toString());

			connection.close();
		}
		return connection;
	}

	public static ResultSet Consultar (String consulta) throws SQLException
	{
		ResultSet resultado = null;
		statement = null;

		statement = connection.createStatement
				(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		resultado = statement.executeQuery(consulta);

		return resultado;
	}
	public static void Desconectar (ResultSet Resconsulta) throws SQLException
	{

		if (Resconsulta != null)
			try
		{
				Resconsulta.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		if (statement != null)
			try
		{
				statement.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		if (connection != null)
			try
		{
				connection.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
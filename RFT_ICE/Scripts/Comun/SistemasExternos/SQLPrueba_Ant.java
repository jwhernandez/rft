package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.SQLPrueba_AntHelper;
import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.object.interfaces.generichtmlsubdomain.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
/**
 * Description   : Prueba desde SQL
 * @author SS
 * Script Name   : <b>SQLPrueba</b>
 * @since  2016/11/23
 */
public class SQLPrueba_Ant extends SQLPrueba_AntHelper
{
	public void testMain(Object[] args) 
	{
		  Connection connection =  null;

		try 
		{ 
			String driverName = "com.jnetdirect.jsql.JSQLDriver";

			// NetDirect JDBC driver 
			String serverName = "127.0.0.1";
			String portNumber = "1433";
			String mydatabase = serverName + 	":" + portNumber;
			String url = 	"jdbc:JSQLConnect://" + mydatabase; 
			// a JDBC url 
			String username = "username";
			String password = 	"password";
			// Loads the JDBC driver 
			Class.forName(driverName);
			// Crear la conexión a la Base 
			connection = DriverManager.getConnection(url, username, password);
		} 

		catch (ClassNotFoundException e) 
		{ 
			// Could not find the database driver 
		} 

		catch (SQLException e) 
		{ 
			// Could not connect to the database 
		} 
	}
}


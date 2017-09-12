package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.SQLPruebaHelper;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
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
public class SQLPrueba extends SQLPruebaHelper
{
	public void testMain(Object[] args) throws SQLException
	   {

	      new OracleConnection();
	   }

	   OracleConnection() throws SQLException
	   {
	      connect();
	      // this.query();

	   }

	   Connection connection;

	   void connect() throws SQLException
	   {

	      Driver driver = new oracle.jdbc.OracleDriver();
	      DriverManager.registerDriver(driver);
	      connection = DriverManager.getConnection("jdbc:oracle:thin:
	         @<hostname>:1521:<service>", "username", "password");
	      connection.setAutoCommit(true);

	   }

	   void query() throws SQLException
	   {

	      ResultSet resultset = null;
	      Statement statement = null;

	      try
	      {

	         statement = connection.createStatement
	            (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	         resultset = statement.executeQuery("Select * from TAX_RETURN where 
	            rownum<=10 order by  1"); 

	         ResultSetMetaData rsmd = resultset.getMetaData();
	         int columns = rsmd.getColumnCount();

	         while (resultset.next())
	         {

	            for (int i = 0; i < columns; i++)
	            {
	               System.out.print(resultset.getObject(i + 1) + " ");
	            } // for

	            System.out.println();
	         } // while

	      }
	      finally
	      {
	         if (resultset != null)
	            try
	            {
	               resultset.close();
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
	         // if (connection != null) ...return to pool...
	      }

	   } //executeQuery

	   ResultSet query(String arg) throws SQLException
	   {

	      ResultSet resultset = null;
	      Statement statement = null;

	      try
	      {

	         statement = connection.createStatement
	            (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	         resultset = statement.executeQuery(arg);

	         ResultSetMetaData rsmd = resultset.getMetaData();
	         int columns = rsmd.getColumnCount();

	         while (resultset.next())
	         {

	            for (int i = 0; i < columns; i++)
	            {
	               System.out.print(resultset.getObject(i + 1) + " ");
	            } // for

	            System.out.println();
	         } // while

	      }
	      finally
	      {
	         if (resultset != null)
	            try
	            {
	               resultset.close();
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
	         // if (connection != null) ...return to pool...
	      }

	      return resultset;
	   } // executeQuery

	}
	}
}


package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.AMM_LoginHelper;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.ResultSetMetaData;

import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.object.interfaces.generichtmlsubdomain.*;
import com.rational.test.ft.script.*;
import libreria.ConexionOracle;
import libreria.utileria.OracleConnection;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
/**
 * Description   : Login a la DBMS de AAM
 * Script Name   : <b>AMM_Login</b>
 * @author SS
 * @since  2016/11/23
 */
public class AMM_Login extends AMM_LoginHelper 
{		
	public void testMain(Object[] argu) throws RationalTestException
	{
		ConexionOracle connection;
		String consulta = null;
		consulta = "select name, PART_NUM from SIEBEL.S_PROD_INT where part_num like 'PRE%'";
		ResultSet resultado=null;
		int cols=0;
		try
		{
			connection = new ConexionOracle();
			connection.Consultar(consulta);
			// veamos si tengo la variable resultado aqui tambien
			System.out.println("Luego de la ejecución de consultar");
			System.out.println("xx " + args[0]);
			if (resultado != null) resultado.beforeFirst(); else stop();
	         ResultSetMetaData rsmd = resultado.getMetaData();
	         cols = rsmd.getColumnCount();
			System.out.println("cols " + cols);
	         if (resultado != null) resultado.beforeFirst(); else stop();
			System.out.println("xx " + resultado.next());
	         while (resultado.next())
	         {

	            for (int i = 0; i < cols; i++)
	            {
	               System.out.print(resultado.getObject(i + 1) + " ");
	            } // for

	            System.out.println();
	         } // while

	  
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}


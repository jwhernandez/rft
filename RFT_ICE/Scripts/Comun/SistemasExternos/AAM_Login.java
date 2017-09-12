package Scripts.Comun.SistemasExternos;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import libreria.ConexionOracle;
import resources.Scripts.Comun.SistemasExternos.AAM_LoginHelper;
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
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
/**
 * Description   : Login AAM
 * Script Name   : <b>AAM_Login</b>
 * @author SS
 * @since  2016/11/28
 * Param 0)OK/NOK 1)usuario 2) Clave
 * res SOIN_SSASTRE Pupina081213
 */
public class AAM_Login extends AAM_LoginHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0]="NOK";
		ConexionOracle connection = null;
		String ConnectString = null;
		String Usuario= null;
		String Clave = null;
		try
		{
			//connection = new ConexionOracle();

			ConnectString = dpString ("ConectAAM");
			Usuario = argu[1].toString();
			Clave = argu[2].toString();
			//ConnectString ="jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=10.129.23.22)(PORT=1522)))(CONNECT_DATA=(SID=descrm)(SERVER=DEDICATED)))";
			//Usuario = "SOIN_SSASTRE";
			// Clave = "Pupina081213";
			ConexionOracle.Conectar (ConnectString, Usuario, Clave);
			connection = (ConexionOracle) ConexionOracle.getConnection();
			sleep(5);
			if (connection != null)  System.out.println("conexion es null" );
			if (connection != null)  argu[0]="OK";
			System.out.println("Objeto conexion en el login " + connection);

			System.out.println("Objeto conexion en el login2 luego de get" + connection);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}


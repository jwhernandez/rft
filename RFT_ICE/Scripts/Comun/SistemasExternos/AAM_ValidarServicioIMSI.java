package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.AAM_ValidarServicioIMSIHelper;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.util.Iterator;
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
 * Description   : Valida servicio IMSI 
 * Script Name   : <b>AAM_ValidarServicioIMSI</b>
 * @author SS
 * @since  2016/11/23
 * Param 0)OK/NOK 1)usuario 2) Clave 3)código 4) cant param 5)param1 6) param2 7) param3 8)param4 9)Param5 
 * 10)param6 11) param7 12) param8 13)param9 14)Param10 
 * res SOIN_SSASTRE Pupina081213 SQL0002 2 "PRE_PLAN_PROD_00028"  "Recarga Triple Chip" 
 */
public class AAM_ValidarServicioIMSI extends AAM_ValidarServicioIMSIHelper 
{		
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0]="NOK";
		String consulta = null;
		boolean bEncontro = false;
		
		dpReset();
		//consulta = "select name, PART_NUM from SIEBEL.S_PROD_INT where part_num like 'PRE%'";
		// select name, PART_NUM from SIEBEL.S_PROD_INT where part_num = %1 AND name = %2
		// Recarga Triple Chip PRE_PLAN_PROD_00028 
		while (!dpDone() && !bEncontro) 
		{
			consulta = dpString("Sentencia");
			if (consulta.equals(argu[3].toString())) bEncontro = true;
			else dpNext(); 
		} 	
	
		// leer parametros y reemplazar
		int iCantParam = Integer.valueOf(argu[4].toString());
		for (int i = 0; i < iCantParam; i++) {
			String NewString = "'" + argu[i+5].toString() + "'";
			consulta=consulta.replace("%"+(i+1),NewString);
		}
		
		System.out.println("Consulta a realizar" + consulta);
		int cols=0;

		Connection connection = null;
		String ConnectString = null;
		String Usuario= null;
		String Clave = null;
		try
		{
			//ConnectString = dpString ("ConectAAM");
			Usuario = argu[1].toString();
			Clave = argu[2].toString();
			ConnectString ="jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=10.129.23.22)(PORT=1522)))(CONNECT_DATA=(SID=descrm)(SERVER=DEDICATED)))";
			//Usuario = "SOIN_SSASTRE";
			// Clave = "Pupina081213";
			connection = ConexionOracle.Conectar (ConnectString, Usuario, Clave);

			sleep(5);
			if (connection == null) 
				System.out.println("conexion es null" );
			else 
			{
				System.out.println("conexion no es null " + connection );
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		try
		{
			//String ConnectString ="jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=10.129.23.22)(PORT=1522)))(CONNECT_DATA=(SID=descrm)(SERVER=DEDICATED)))";
			//String Usuario = "SOIN_SSASTRE";
			//String Clave = "Pupina081213";
			//connection.Conectar (ConnectString, Usuario, Clave);

			ResultSet Resconsulta = ConexionOracle.Consultar(consulta);

			if (Resconsulta != null) 
			{
				ResultSetMetaData rsmd = Resconsulta.getMetaData();
				cols = rsmd.getColumnCount();
				
				System.out.println("cols " + cols);
				Resconsulta.beforeFirst(); 

				System.out.println("Resultado de la consulta");
				System.out.println("------------------------");
				int rows = 0;
				while (Resconsulta.next())
				{
					rows++;
					for (int i = 0; i < cols; i++)
					{
						System.out.print("Fila " + rows + " Columna " + i + "-" + Resconsulta.getObject(i + 1) + " ");
					} // for

					System.out.println();
				} // while
				ConexionOracle.Desconectar (Resconsulta);
				if (connection == null) 
					System.out.println("conexion es null" );
				else 
				{
					System.out.println("conexion no es null " + connection );
				}
				System.out.println("Cantidad total de filas " + rows);
				if (rows == 1) argu[0]="OK";
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}


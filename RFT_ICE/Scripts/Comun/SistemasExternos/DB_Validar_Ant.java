package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.DB_Validar_AntHelper;
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
 * Param 0)OK/NOK 1)usuario 2) Clave 3) Codigo connectString 4)códigoSQL 5) cant param 6)param1 7) param2 8) param3 9)param4 10)Param5 
 * 11)param6 12) param7 13) param8 14)param9 15)Param10  
 * res USRVALIDACIONESCONS USRVALIDACIONESCONS ConectAAM SQL0003 2 "89520058"  "712011003192298" 
 * res SOIN_SSASTRE Pupina081213 ConectAAM SQL0002 2 "Video Llamada Prepago"  "PRE_VIDEO_PROD_00001" 
 */
public class DB_Validar_Ant extends DB_Validar_AntHelper 
{		
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0]="NOK";
		String consulta = null;
		String CodigoconsultaDeseada = null;
		String ConnectString = null;
		String CodigoConnectStringDeseado = null;
		String Codigo = null;
		boolean bEncontro = false;
		CodigoConnectStringDeseado = argu[3].toString();
		CodigoconsultaDeseada = argu[4].toString();

		dpReset();
		while (!dpDone() && !bEncontro) 
		{
			Codigo = dpString("Codigo");
			consulta=dpString("Valor");
			if (CodigoconsultaDeseada.equals(Codigo)) bEncontro = true;
			else dpNext(); 
		} 	
		System.out.println("Consulta a realizar" +  consulta);

		dpReset();
		bEncontro = false;
		while (!dpDone() && !bEncontro) 
		{
			Codigo = dpString("Codigo");
			ConnectString=dpString("Valor");
			System.out.println("Codigo" +  Codigo + " Valor" + ConnectString);
			if (CodigoConnectStringDeseado.equals(Codigo)) bEncontro = true;
			else dpNext(); 
		} 
		ConnectString = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=10.129.23.22)(PORT=1522)))(CONNECT_DATA=(SID=descrm)(SERVER=DEDICATED))";
		System.out.println("Connect String" + ConnectString);

		if (bEncontro)
		{
			// leer parametros y reemplazar
			int iCantParam = Integer.valueOf(argu[5].toString());
			for (int i = 0; i < iCantParam; i++) {
				String NewString = "'" + argu[i+6].toString() + "'";
				consulta=consulta.replace("%"+(i+1),NewString);
			}

			int cols=0;

			Connection connection = null;
			String Usuario= null;
			String Clave = null;
			try
			{
				Usuario = argu[1].toString();
				Clave = argu[2].toString();
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
		}
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}


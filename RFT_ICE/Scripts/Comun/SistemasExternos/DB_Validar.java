package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.DB_ValidarHelper;
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
import libreria.utileria.Tipo;

import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
/**
 * Description   : Validar usando una consulta a una DB Oracle
 * Script Name   : <b>DB_Validar</b>
 * @author SS
 * @since  2016/11/23
 * Param 0)OK/NOK 1)usuario 2) Clave 3) Codigo connectString 4)códigoSQL 5) Validar Si o No 6) cant param 
 * 7)param1 8) param2 9) param3 10)param4 11)Param5 
 * 12)param6 13) param7 14) param8 15)param9 16)Param10  
 * res SOIN_SSASTRE Pupina081213 ConectSBL SQL0002  Si 2  "PRE_VIDEO_PROD_00001" "Video Llamada Prepago" 
 * res USRVALIDACIONESCONS USRVALIDACIONESCONS ConectAAM SQL0003 Si 2 89770660  712012004502811
 * res USRVALIDACIONESCONS USRVALIDACIONESCONS ConectUSM SQL0004 No 4 89843883 c_Celular c_Blackberry N
 */
public class DB_Validar extends DB_ValidarHelper 
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
		String Usuario= argu[1].toString();
		String Clave =argu[2].toString();
		CodigoConnectStringDeseado = argu[3].toString();
		CodigoconsultaDeseada = argu[4].toString();
		boolean bValidar=false;
		if (argu[5].toString().toLowerCase().equals("si")) bValidar=true;
		String sScriptName=getScriptName().toString(); // 22/11/2016
		ConnectString = null;
		int cols=0;

		Connection connection = null;
		
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
			ConnectString="jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=" +
					dpString("Host") + ")(PORT=" + dpString("Port") + ")))(CONNECT_DATA=(SID=" +
					dpString("SID") + ")(SERVER=DEDICATED)))";
			if (CodigoConnectStringDeseado.equals(Codigo)) bEncontro = true;
			else dpNext(); 
		} 

		//ConnectString = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=10.129.23.22)(PORT=1522)))(CONNECT_DATA=(SID=descrm)(SERVER=DEDICATED))";
		System.out.println("Connect String:" + ConnectString);
		
		//consulta="select name, PART_NUM from SIEBEL.S_PROD_INT where part_num = %1 AND name = %2";
		//consulta="select msisdn, IMSI from SERVICE_VERIFY where msisdn = %1 and IMSI = %2";

		// leer parametros y reemplazar
		int iCantParam = Integer.valueOf(argu[6].toString());
		for (int i = 0; i < iCantParam; i++) {
			String NewString = "'" + argu[i+7].toString() + "'";
			consulta=consulta.replace("%"+(i+1),NewString);
		}
		System.out.println("Consulta a realizar" +  consulta);

		try
		{
			// por algun motivo del dp lo toma mal. 
			//ConnectString ="jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=10.149.127.207)(PORT=1521)))(CONNECT_DATA=(SID=ICEAAM)(SERVER=DEDICATED)))";
			System.out.println("Connect String" + ConnectString);
			//ConnectString ="jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=10.129.23.22)(PORT=1522)))(CONNECT_DATA=(SID=descrm)(SERVER=DEDICATED)))";
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
				String Col = null;
				String sFila = null;
				while (Resconsulta.next())
				{
					rows++;
					sFila = "";
					for (int i = 0; i < cols; i++)
					{
						if (Resconsulta.getObject(i + 1) != null)
						{
							Col = Resconsulta.getObject(i + 1).toString(); 
							if (Col!=null)
							{
								sFila = sFila + Col + "-";
//								System.out.print("Fila:"+rows+" Col:" +i + "-" + Col);
//								infoPaso(sScriptName, Tipo.DATO,"Fila:"+rows+" Col:" +i,  Col);
							}
						}
					} // for

					System.out.println(sFila);
					infoPaso(sScriptName, Tipo.DATO,"Fila:"+rows,  sFila);
				} // while
				ConexionOracle.Desconectar (Resconsulta);
				if (connection == null) 
					System.out.println("conexion es null" );
				else 
				{
					System.out.println("conexion no es null " + connection );
				}
				System.out.println("Cantidad total de filas " + rows);
				if (bValidar)
					if (rows == 1) argu[0]="OK";
					else 
						{
						infoPaso(sScriptName, Tipo.ERROR,sScriptName, "No se encontró 1 solo registro en la base");
						System.out.println("No se encontró 1 solo registro en la base");
						argu[0]="NOK";
						}
				else argu[0]="OK";
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}

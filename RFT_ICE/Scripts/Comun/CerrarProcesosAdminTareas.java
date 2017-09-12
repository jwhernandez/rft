package Scripts.Comun;
import resources.Scripts.Comun.CerrarProcesosAdminTareasHelper;
import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.object.interfaces.generichtmlsubdomain.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.sys.OSProcess;
import com.rational.test.ft.sys.OperatingSystem;
import com.rational.test.ft.util.FtDebug;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
/**
 * Description   : Cerrar los procesos abiertos de IE en el administrador de tareas.
 * @author SS
 * Script Name   : <b>CerrarProcesosAdminTareas</b>
 * @Param 0) OK/NOK 1) nombre del proceso
 * @since  2016/06/09
 * ult modif 09/02/2017 / ss se agrega comentario cuando no puede ser removido el proceso
 * ej res iexplore.exe
 */
public class CerrarProcesosAdminTareas extends CerrarProcesosAdminTareasHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());

		String processname = null;
		String processname_nickname = null;

		int pid =0;
		argu[0]="OK";
		if (argu[1].toString().length()>=11) 
			argu[1] = argu[1].toString().substring(0,11);
		
		//Get the list of all running processes
		OSProcess[] procList = OperatingSystem.getProcessList();
		if(procList != null)
		{
			int procCount = procList.length;
			for(int i=0;i<procCount;i++)
			{
				pid = procList[i].processId;
				processname = procList[i].processName;
				if (processname.length()>=11) 
					processname_nickname = procList[i].processName.substring(0,11);
				else 
					processname_nickname = procList[i].processName;
				if(processname_nickname.toLowerCase().equals(argu[1].toString().toLowerCase().substring(0,11)) )
				{
					System.out.println(processname +"-" + pid );
					System.out.println(processname_nickname +"-" + pid );

					procList[i].kill();
					System.out.println("El proceso "+ processname +"-" + pid +"fue removido");
					sleep(2);
					//make sure that the process is removed
					if(IsProcessStillRunning(processname, pid))
					{
						//Si el proceso aun está corriendo esperar y reintentar
						sleep(10);   
						if(IsProcessStillRunning(processname, pid)) 
							{
							argu[0]="NOK";
							System.out.println("El proceso "+ processname +"-" + pid +"no pudo ser removido");
							}
					} 
				}
				else System.out.println("Se descarta el proceso:" + processname +"-" + pid );
			} // end del loop
		} // No hay procesos
		else
		{
			System.out.println("No se han encontrado procesos abiertos");
		}

		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	} // fin de test main


	private boolean IsProcessStillRunning(String procName,int pid)
	{
		boolean result = false;
		//Chequeo de si el proceso dejó de correr
		OSProcess[] procList = OperatingSystem.getProcessList();
		if(procList != null)
		{
			int procCount = procList.length;
			for(int i=0;i <procCount;i++)
			{
				if( procList[i].processId == pid)
				{
					//Chequeo del nombre del proceso
					if(procList[i].processName.toLowerCase().equals(procName.toLowerCase()))
					{
						result = true;
						break;
					}
				}
			}
		}           
		return result;
	}
}


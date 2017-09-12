package Scripts.Comun;
import resources.Scripts.Comun.KillProcessHelper;
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
import com.rational.test.ft.util.FtDebug;
import com.rational.test.ft.sys.OSProcess;
import com.rational.test.ft.sys.OperatingSystem;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
/**
 * Description   : Functional Test Script
 * @author SS
 * @Param 0) OK/NOK 1) nombre del proceso
 * Script Name   : <b>KillProcess</b>
 * ej res iexplore.exe
 * 
 * */
public class KillProcess extends KillProcessHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		FtDebug debug = new FtDebug("ProcessUtility");
		int passCount = 0;
		int failCount = 0;
		String processname = null;
		int pid =0;
		argu[0]="NOK";
		//Get the list of all running processes
		OSProcess[] procList = OperatingSystem.getProcessList();
		if(procList != null)
		{
			int procCount = procList.length;
			for(int i=0;i<procCount;i++)
			{
				pid = procList[i].processId;
				processname = procList[i].processName;
				if(processname.toLowerCase().equals(argu[1].toString().toLowerCase()) )
				{
					System.out.println(processname +"-" + pid );
					System.out.println("FtDebug=" + FtDebug.DEBUG);
					if(FtDebug.DEBUG)
						debug.debug("Matando el proceso " + processname +" con pid = " + pid);
					System.out.println("El proceso "+ processname +"-" + pid +"sera removido");
					procList[i].kill();
					System.out.println("El proceso "+ processname +"-" + pid +"fue removido");
					sleep(2);
					//make sure that the process is removed
					if(IsProcessStillRunning(processname, pid))
					{
						//Si el proceso aun está corriendo esperar y reintentar
						if(FtDebug.DEBUG)
							debug.error("Se encontró " + processname + "(" +pid + ")  aun ejecutandose. Se espera y reintenta");
						sleep(30);  // 30 segs
						if(IsProcessStillRunning(processname, pid))
						{
							if(FtDebug.DEBUG)
								debug.debug("Se falló al matar el proceso " + processname + "("+pid +")" );
							failCount++;
						}
					}
					else
					{
						if(FtDebug.DEBUG)
							debug.debug("Process " + processname + "("+pid +") Found killed and removed from the active proc list");
						passCount++;
					}
				} // end del if procList
			}
			if(FtDebug.DEBUG)
			{
				debug.debug("Killed " + passCount + " Instances successfully... failure count = " + failCount);
			}
		} // si no se encontró el proceso
		else
		{
			System.out.println("El proceso "+ processname +"-" + pid +"no se encontró");
			logInfo("No process found from the Operating system to kill");
		}

		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	} // fin de test main


	private boolean IsProcessStillRunning(String procName,int pid)
	{
		boolean result = false;
		//Get the list of all running processes and look for the existence of the target process
		OSProcess[] procList = OperatingSystem.getProcessList();
		if(procList != null)
		{
			int procCount = procList.length;
			for(int i=0;i <procCount;i++)
			{
				if( procList[i].processId == pid)
				{
					//Just to make sure we are on the right target
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


package Scripts.Comun;
import resources.Scripts.Comun.ConsolaaFileHelper;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.object.interfaces.generichtmlsubdomain.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import java.io.BufferedOutputStream;
import java.io.PrintStream;
import java.io.FileNotFoundException;
import java.io.File;
/**
 * Description   : Redirecciona la consola a archivo
 * Script Name   : <b>ConsolaaFile</b>
 * @author SS
 * @since  2016/11/03
 * ult modif  ss feb 2017 se cambia el nombre para poder aceptar cualquier nombre de proyecto
 * args 0) OK/NOK 1) NroCaso 2) QA
 * ej res CP76_CD1 QA
 * ult modif 
 * ss 14 8 2017 se modifica path para el bat con nombre flexible
 */
public class ConsolaaFile extends ConsolaaFileHelper
{
	public void testMain(Object[] argu) throws RationalTestException, FileNotFoundException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";

		String sPath = getCurrentProject().getLocation();
		String Name =  getCurrentProject().getName();
		sPath = sPath.replace(Name,Name+"_USR"); 
		
		System.out.println("Path DP Entrada = " + sPath);

		//sPath= sPath.replace("RFT_ICE","RFT_ICE_USR"); // ss feb 2017 se cambia el nombre para poder aceptar cualquier nombre de proyecto
		sPath = sPath.concat("\\\\Resultados").concat("\\\\").concat(argu[2].toString()).concat("\\\\");
		String sPath_file =sPath.concat("\\\\").concat(argu[1].toString()  + "_CON.txt");
		
		System.out.println(sPath);
		PrintStream console = System.out;

		File file = new File(sPath_file);
		FileOutputStream fos = new FileOutputStream(file);
		PrintStream ps = new PrintStream(fos);
		
		System.setOut(console);
		System.out.println("La consola se redirecciono a archivo");
		
		System.setOut(ps);
		System.out.println("Inicio de consola en archivo");

		argu[0] = "OK";
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}


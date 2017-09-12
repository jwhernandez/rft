package Scripts.Comun;
import resources.Scripts.Comun.LeerPasoInicialHelper;
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
 * Script Name   : <b>LeerPasoIncial</b>
 * Generated     : <b>may. 15, 2017 10:55:06 AM</b>
 * Description   : Lee el paso en el que se quiere iniciar en el datapool de datos salida, esto con el objetivo de saltar pasos
 * Original Host : WinNT Version 6.1  Build 7601 (S)
 * Parámetros 0) OK/NOK 1) Numero de CP 2) ambiente 3) Retorna el numero de paso
 * @since  2017/05/15
 * @author VC
 */
public class LeerPasoInicial extends LeerPasoInicialHelper
{
	
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		argu[3] = "NA";
		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(argu[1].toString()) && 
				dpString("Ambiente").equals(argu[2].toString()))) {
			dpNext(); 
		} 

		try{
			argu[3] = dpString("PasoInicialDP");
			System.out.println("Inicio diferido desde el DP a partir del paso: " + argu[3]);
			argu[0] = "OK";
		} catch(Exception e){
			System.out.println("No se pudo leer el paso inicial del datapool de DatosEntrada, se seguirá el flujo normal tomando un NA. " + e);
		}
		
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

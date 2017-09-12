package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fAdministrarTemporadasHelper;
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
 * Script Name   : <b>fAdministrarTemporadas</b>
 * Description   : Administracion de temporadas: Apagar / Encender
 * @Param 0) IN nombre del caso 1) IN digito (i) 2) IN Ambiente 
 * @author Sandra
 * @since  2016/01/15
 */
public class fAdministrarTemporadas extends fAdministrarTemporadasHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] Temporadas;
		Temporadas = new String[4];
		/**
		 * @param0) IN Nombre del producto 1) IN  Accion Encender / Apagar 
		 * 2) OUT OK/NOK 3) Valor devuelto
		 */

		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		
		Boolean bError = true;
		int i = Integer.parseInt(args[1].toString());
		Temporadas[0] = dpString("Tempo_Prod"+i); 
		Temporadas[1] = dpString("Tempo_Accion"+i); 
		logInfo (getScriptName( ) + Temporadas);
		
		switch (Temporadas[1].toString()) {
		case "Apagar":
		case "Encender":
			callScript("Scripts.Comun.AdministrarTemporadas", Temporadas);
			if  ((Temporadas[2].toString().equals("OK"))){
				bError = false; 			
			}
			break;
		case "Consultar":
			callScript("Scripts.Comun.AdministrarTemporadas", Temporadas);
			if ((Temporadas[2].toString().equals("OK")) && 
				(Temporadas[3].toString().equals(dpString("Tempo_Valor"+i))))	{
					bError = false; 
				}
			break;
		default:  
			break;
		}  

		if  (bError){
			//MensError[0] = "xDefecto";// ""Problemas con administración de la Temporada"
			MensError[0] = "Problemas con administración de la Temporada";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}


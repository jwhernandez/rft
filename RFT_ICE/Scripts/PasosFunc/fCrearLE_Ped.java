package Scripts.PasosFunc;
import libreria.utileria.Tipo;
import resources.Scripts.PasosFunc.fCrearLE_PedHelper;
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
 * Description   : Chequear una lista especial
 * @since  2016/10/20
 * @param 0) numero de caso 1)un digito indicando el numero de variable en el dp
 * Precondiciones: Si param 2 = true : Estar en el pedido o Si param 2 = false : estar en la pantalla de LE 
 * Ejemplo: res "Número de SMS" true  res   
 * @author SS 
 * Script Name   : <b>fCrearLE</b>
 */
public class fCrearLE_Ped extends fCrearLE_PedHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];
		
		String sScriptName=getScriptName().toString(); // 22/11/2016

		String[] ListaEsp;
		ListaEsp = new String[6];
		//0) 0) NOK / OK 1) Tipo de lista Número de teléfono / Número de SMS
		// 2)Desde pedido true/false 3) volver a pedido true false 
		// 4) out Nombre lista 5) Tramite (tramite y volver al pedido ss agregado 17/11/16)
		
		String[] DatoSalida;
		DatoSalida = new String[5];
		// 0) OK/NOK 1) Nro CP 2) Ambiente 3) Nombre variable 4)valor variable
		

		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		int i = Integer.parseInt(args[1].toString());
		ListaEsp[1] = dpString("LETipo"+i);
		ListaEsp[2] = dpString("LEDesdePed"+i);
		ListaEsp[3] = dpString("LEVolverPed"+i);
		ListaEsp[5] = dpString("Tramite");

		callScript("Scripts.Comun.CrearLE_Ped", ListaEsp);

		if  ((ListaEsp[0].toString().equals("NOK"))){
			MensError[0] = "Lista Especial tuvo error";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		} else {
			/**  Guardar el nro de lista en variable global, dp y texto salida */

			setNomListaEspecial(ListaEsp[4].toString()); 
			
			int sLong = args[0].toString().length(); // nro caso CPnn_CDi_Tj
			DatoSalida[1]= args[0].toString().substring(0, sLong-3); // nro caso CPnn_CDi
			DatoSalida[2]=args[2].toString(); // nro ambiente
			DatoSalida[3]="T"+ getUltimoTramite()+"_NomListaEspecial"; // Tx_Nombre variable // Se modifica de nrotramite a este valor de variable
			DatoSalida[4]=ListaEsp[4].toString(); // Valor de la variable

			callScript("Scripts.Comun.GrabarDatosSalida",DatoSalida);

			infoPaso(sScriptName, Tipo.DATO,"NomListaEspecial",getNomListaEspecial());
		}
	}
}


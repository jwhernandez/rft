package Scripts.PasosFunc;
import libreria.utileria.Tipo;
import resources.Scripts.PasosFunc.fProximoNumeroHelper;
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
 * Script Name   : <b>fProximoNumero</b>
 * Description   : Selecciona el botón del proximo número disponible. 
 * Si lo indica el parametro primero libera el numero deseado
 *  * @param  0) Caso Ej. CP03 1) "El Indice del conjunto de datos en el dp" o NA 2)Ambiente
 * @since  2015/12/27
 * @author SS
 */
public class fProximoNumero extends fProximoNumeroHelper
{
	public void testMain(Object[] args) 
	{
		String[] ProxNum;
		ProxNum = new String[5];
		// Parám: 0) Prepago o Postpago 1) Devuelve NOK o OK 2)nro pedido 
		// 3)nro servicio deseado / NA 4) nro de servicio asignado (output)
		

		String[] MensError;
		MensError = new String[4];
		
		System.out.println(args [0] + "*" + args[1]+ "*" + args[2] ) ;
		
		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		System.out.println("DP Usado: " + dpString("NumeroCaso")+ dpString("Ambiente"));
		
		int i = Integer.parseInt(args[1].toString());
		
		ProxNum[0] = dpString("TipoPerfilCorrecto");
		System.out.println("DP ProxNum: " + dpString("ProxNum" + i));
		System.out.println("DP ProxNumValidar: " + dpBoolean("ProxNumValidar" + i));

		ProxNum[3] = dpString("ProxNum" + i);
		ProxNum[2] = getNroPedido();
		System.out.println("NroPedido: " + getNroPedido());
		callScript("Scripts.Comun.ProximoNumero", ProxNum);

		if (dpBoolean("ProxNumValidar" + i)) {
			if  ((ProxNum[1].toString().equals("NOK"))){
				MensError[0] = "Problema en la asignacion de proximo número";
				//MensError[0] = "xDefecto";
				MensError[1] = args[3].toString();
				MensError[2] = args[0].toString();
				MensError[3] = getScriptName( );
				callScript("Scripts.Comun.TerminarCasoError", MensError);
			} else {
				setNroServicio(ProxNum[4].toString());
				infoPaso(args[0].toString(), Tipo.DATO,"NroServicio",getNroServicio());

			}
				
		}
	}
}


package Scripts.PasosFunc;
import libreria.utileria.Tipo;
import resources.Scripts.PasosFunc.fProximoNumero_AntHelper;
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
 * Description   : Selecciona el bot�n del proximo n�mero disponible. 
 * Si lo indica el parametro primero libera el numero deseado
 * @param  0) Caso Ej. CP03 1) "El Indice del conjunto de datos en el dp" o NA 2)Ambiente
 * @since  2015/12/27
 * @author SS
 */
public class fProximoNumero_Ant extends fProximoNumero_AntHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String sScriptName=getScriptName().toString(); // 22/11/2016
		String[] ProxNum;
		ProxNum = new String[5];
		// Par�m: 0) Prepago o Postpago 1) Devuelve NOK o OK 2)nro pedido 
		// 3)nro servicio deseado / NA 4) nro de servicio asignado (output)
		
		String[] DatoSalida;
		DatoSalida = new String[5];
		// 0) OK/NOK 1) Nro CP 2) Ambiente 3) Nombre variable 4)valor variable
		
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

		if  ((ProxNum[1].toString().equals("NOK"))){
			//MensError[0] = "Problema en la asignacion de proximo n�mero";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}

		if (dpBoolean("ProxNumValidar" + i)) 
		{
			System.out.println("Validacion de numero de servicio asignado.");
			if(ProxNum[4].equals(dpString("NumeroServicio"))) 
			{
				setNroServicio(ProxNum[4].toString());
				
				int sLong = args[0].toString().length(); // nro caso CPnn_CDi_Tj
				DatoSalida[1]= args[0].toString().substring(0, sLong-3);
				DatoSalida[2]=args[2].toString(); // nro ambiente
				DatoSalida[3]="T"+ getUltimoTramite()+"_NroServicio"; // Nombre variable // Se modifica de nrotramite a este valor de variable
				DatoSalida[4]=ProxNum[4].toString(); // Valor de la variable
				callScript("Scripts.Comun.GrabarDatosSalida",DatoSalida);

				infoPaso(sScriptName, Tipo.DATO,"NroServicio",getNroServicio());
				System.out.println("El nro de servicio asignado coincide con el deseado.");
			}
			else 
			{
				MensError[0] = "El n�mero asinado no coincide con el numero deseado";
				//MensError[0] = "xDefecto";
				MensError[1] = args[3].toString();
				MensError[2] = args[0].toString();
				MensError[3] = getScriptName( );
				callScript("Scripts.Comun.TerminarCasoError", MensError);
			}
		} // fin de si habia que validar
		else { // si no hay que validar se asigna el nro de servicio que dio
			setNroServicio(ProxNum[4].toString());
			DatoSalida[1]=args[0].toString(); // nro caso
			DatoSalida[2]=args[2].toString(); // nro ambiente
			DatoSalida[3]="NroServicio"; // Nombre variable
			DatoSalida[4]=ProxNum[4].toString(); // Valor de la variable
			callScript("Scripts.Comun.GrabarDatosSalida",DatoSalida);
			infoPaso(sScriptName, Tipo.DATO,"NroServicio",getNroServicio());
		}
	}
}


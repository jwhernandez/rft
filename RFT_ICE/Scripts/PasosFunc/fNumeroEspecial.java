package Scripts.PasosFunc;
import libreria.utileria.Tipo;
import resources.Scripts.PasosFunc.fNumeroEspecialHelper;
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
 * Descripcion     :Ingresa un numero especial
 * Parámetros	   : 0) nombre del caso 1)NA 2)ambiente
 * Pre-condiciones : Estar en la vista del pedido. No hace falta estar en el producto.
 * Toma del DP el nro de servicio deseado
 * SS Nov 2015
 */
public class fNumeroEspecial extends fNumeroEspecialHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		String[] NroEspecial;
		NroEspecial = new String[7];
		//Parámetros: 0) tipo (Postpago / Prepago) 1) nro pedido  2) nro especial 1era opcion
		// 3) true si acepta primer num como segunda opcion 4) true si debe ir a admin
		// 5)OK / NOK 6) nro de servicio asignado

		String[] MensError;
		MensError = new String[4];
		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 	
		/** 
		 * Obtener el numero especial
		 */
		int i = Integer.parseInt(args[1].toString());
		
		NroEspecial[0] = dpString("TipoPerfilCorrecto");
		NroEspecial[1] = getNroPedido();
		System. out.println("Numero Pedido" + NroEspecial[1]);
		NroEspecial[2] = dpString("NumeroServicio");
		NroEspecial[3] = dpString("NumEspPrimerNum2daopcion" + i);
		NroEspecial[4] = dpString("NumEspIrAdmin"+i);
		System.out.println("Parametros con los que invoca:"+ NroEspecial[0]+"*"+
				NroEspecial[1]+"*"+NroEspecial[2]+"*"+NroEspecial[3]+"*"+NroEspecial[4]);
		callScript("Scripts.Comun.NumeroEspecial", NroEspecial);

		if  (NroEspecial[5].toString().equals("NOK")){
			MensError[0] = "Numero Especial falló";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		} else {
			setNroServicio(NroEspecial[6].toString());
			infoPaso(args[0].toString(), Tipo.DATO,"NroServicio",getNroServicio());
		}
	}
}


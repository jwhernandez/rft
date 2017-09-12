package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidaPromOpcHelper;
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
 * Description   : Si existen bolsas que eliminar, las elimina
 * @author Sandra
 * Script Name   : <b>fValidaPromOpc</b>
 * @Param 0) caso 1) producto 2) ambiente 3) true o false 
 * en el parametro 1 recibe el producto de promociones opcionales a activar. NoPaquetes es para 
 * desactivar todas las bolsas de SMS y Min
 * @since  2016/03/28
 */
public class fValidaPromOpc extends fValidaPromOpcHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Msj;
		Msj = new String[4];
		
		String[] PromOpc;
		PromOpc = new String[3];

		String[] MensError;
		MensError = new String[4];
	
		Msj[0]= "DPM0030";
		Msj[3] = "HTML";
		
		callScript("Scripts.Comun.ValidarMensaje",Msj);

		// Acepta valores como true o No Existe
		if  (!(Msj[1].toString().equals("true") || 
				Msj[1].toString().equals("No Existe"))) {
			MensError[0] = "Paso elimina bolsas dio error";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}

		if  (Msj[1].toString().equals("true")) {
			// Si hay mensaje elimina las bolsas y vuelve a enviar
			
			/**
			 * Itera el data pools de datos del caso para buscar la row correcta
			 */
			dpReset();
			while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
					dpString("Ambiente").equals(args[2]))) {
				dpNext(); 
			} 	

			// Elimina las bolsas
			PromOpc[0]=dpString("TipoPerfilCorrecto");
			PromOpc[1]= args[1].toString();
			callScript("Scripts.Comun.PersonalizarPromOpc", PromOpc);

			if  (PromOpc[2].toString().equals("NOK")) {
				MensError[0] = "Paso elimina bolsas dio error";
				//MensError[0] = "xDefecto";
				MensError[1] = args[3].toString();
				MensError[2] = args[0].toString();
				MensError[3] = getScriptName( );
				callScript("Scripts.Comun.TerminarCasoError", MensError);
			}
			
			// Intentar enviar de nuevo el pedido
			String[] EnviarPedido;
			EnviarPedido = new String[1];

			callScript("Scripts.Comun.EnviarPedido", EnviarPedido);
			
			if (EnviarPedido[0].toString().equals("No creado")) {	
				//MensError[0] = "Problema al enviar el pedido";
				MensError[0] = "xDefecto";
				MensError[1] = args[3].toString();
				MensError[2] = args[0].toString();
				MensError[3] = getScriptName( );
				callScript("Scripts.Comun.TerminarCasoError", MensError);
			}
		}
	}
}


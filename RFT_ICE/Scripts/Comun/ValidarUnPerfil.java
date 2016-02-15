package Scripts.Comun;
import resources.Scripts.Comun.ValidarUnPerfilHelper;

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
 * Descripción: Valida un perfil de facturacion por cuenta del cliente o fact
 * Parametros: 0) OK / NOK ; OK=Se recibe mensaje correcto. 1) Cliente / Fact
 * NOK el mensaje queda sin aceptar.
 * Pre-Condición: Se debe estar en la cuenta de facturación o en la cta de servicio
 */
public class ValidarUnPerfil extends ValidarUnPerfilHelper
{
	public void testMain(Object[] argu) 
	{
		argu[0] = "OK";

		switch (argu[1].toString()) {
		case "Cliente":
			NuevoPerfilCtaCliente().performAction();
			break;
		case "Fact":
			NuevoPerfilCtaFact().performAction();
			break;
		default:  
			argu[0] = "NOK";
			break;
		} // end del switch

		String[] Validaciones;
		Validaciones = new String[3];
		Validaciones[0]="DPM0008";  
		callScript("Scripts.Comun.ValidarMensaje", Validaciones);
		// Validaar que sea el mensaje deseado 
		if (Validaciones[1].toString() == "false") {
			argu[0] = "NOK";
		}
	}
}


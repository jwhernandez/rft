package Scripts.PasosFunc;
import libreria.utileria.Tipo;
import resources.Scripts.PasosFunc.fBuscarActivoHelper;
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
 * Script Name   : <b>fBuscarActivo</b>
 * Description   : Functional Test Script
 * Param 0) Nro caso 1) digito 2) ambiente 3) error para o no
 * Ej CPCPCurso DP DESA false
 * @since  2015/12/28
 * @author Sandra
 */
public class fBuscarActivo extends fBuscarActivoHelper
{
	public void testMain(Object[] args) 
	{
		String[] MensError;
		MensError = new String[4];
		
		String[] Activo;
		Activo = new String[2];
		// Parametros: 0) Nro de servicio (E) , 1)  OK / NOK (S)
		
		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		
		if (args[1].toString().equals("NA")) {
			Activo[0]= getNroServicio();	
		} else {
			Activo[0]= dpString("NumeroServicio");
			setNroServicio(Activo[0].toString());
			infoPaso(args[0].toString(), Tipo.DATO,"NroPedido",getNroPedido());		
		}
		
		System.out.println("NroServicio: " + getNroServicio() );
		logInfo("NroServicio: " + getNroServicio() );
		callScript("Scripts.Comun.BuscarActivo", Activo);
		if (Activo[1].toString().equals("NOK")) {
			//MensError[0] = "Activo no encontrado";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}


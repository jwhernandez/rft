package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarNroOrdenHelper;
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
 * Description   : Validar en el pedido el campo nro de orden
 * Con la opción Valor el contenido y con las opciones true o false si está habilitado o inhabilitado
 * 
 * @author SS
 * Script Name   : <b>fValidarNroOrden</b>
 * @since  2017/03/23
 * Param 1 = Valor o False o True
 */
public class fValidarNroOrden extends fValidarNroOrdenHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] NroOrden;
		NroOrden = new String[4];
		// Parámetros: 0)OK/NOK 1)Accion 2) ValorId 3)Tramite (PortIn u otro)

		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		
		String sAccion = args[1].toString().toLowerCase(); // accion: valor, true, false
		if (sAccion.equals("valor"))
		{
			NroOrden[2] = dpString("ValorNroOrden"); // Opcion no implementada aun
		}
		else
		{
			NroOrden[2] = "NA"; // 
		}
		NroOrden[1] = sAccion;
		NroOrden[3] = dpString("Tramite"); 

		callScript("Scripts.Comun.ValidarIdServicio",NroOrden);
		
		if  ((NroOrden[0].toString().equals("NOK"))){
			//MensError[0] = "Validacion de nro orden falló";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}


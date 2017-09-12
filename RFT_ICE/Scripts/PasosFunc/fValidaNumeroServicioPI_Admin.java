package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidaNumeroServicioPI_AdminHelper;
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
 * Script Name   : <b>fValidaNumeroServicioPI_Admin</b>
 * Descripcion   : Valida el estado (editable o no) del numero de servicio en adm de pedidos port in
 * @Param 0) Caso 1) parametro 2) para anter error
 * @since  2016/04/07
 * @author Sandra
 */
public class fValidaNumeroServicioPI_Admin extends fValidaNumeroServicioPI_AdminHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] ValidaPedPI;
		ValidaPedPI = new String[4];
		// Parámetros	0) OK/NOK 1) True / False para saber que validar 2)Opcion=NroServicio
		// 3) Producto (Servicio)

		String[] MensError;
		MensError = new String[4];

		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		
		ValidaPedPI[1] = args[1].toString().toLowerCase();
		ValidaPedPI[2] = "NroServicio";
		ValidaPedPI[3] = dpString("Servicio");
		callScript("Scripts.Comun.ValidaPI_Admin",ValidaPedPI);

		if  (ValidaPedPI[0].toString().equals("NOK")){
			MensError[0] = "Validacion NroServicio en Administración pedidos falló";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}


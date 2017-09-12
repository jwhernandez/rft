package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidaTipoOrdenPI_AdminHelper;
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
 * Script Name   : <b>fValidaTipoOrdenPI_Admin</b>
 * Descripcion   : Validar el tipo de orden (editable o no)  en adm de pedidos port in
 * @Param 0) Caso 1) parametro 2) para anter error
 * @since  2016/04/07
 * @author Sandra
 */
public class fValidaTipoOrdenPI_Admin extends fValidaTipoOrdenPI_AdminHelper
{

	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] ValidaPedPI;
		ValidaPedPI = new String[3];
		// Parámetros	0) OK/NOK 1) True / False para saber que validar 2)Opcion=TipoOrden

		String[] MensError;
		MensError = new String[4];

		ValidaPedPI[1] = args[1].toString().toLowerCase();
		ValidaPedPI[2] = "TipoOrden";
		callScript("Scripts.Comun.ValidaPI_Admin",ValidaPedPI);

		if  (ValidaPedPI[0].toString().equals("NOK")){
			MensError[0] = "Validacion Tipo de Orden en Administración pedidos falló";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}

	}
}


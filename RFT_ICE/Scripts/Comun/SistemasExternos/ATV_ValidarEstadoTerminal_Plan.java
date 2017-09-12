package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.ATV_ValidarEstadoTerminal_PlanHelper;
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
 * Description   : Lee el estado. 
 * Precond: Debe ser ejecutado luego de buscar el plan terminal para saber que existe el registro.
 * Script Name   :  <b>ATV_BuscarTerminal_Plan</b>
 * @since  2016/09/16
 * Parametros: 0) Resultado = OK/NOK 1) nro pedido 2)Estado
 * 
 * res 1-1735047671  "Activo"
 */
public class ATV_ValidarEstadoTerminal_Plan extends ATV_ValidarEstadoTerminal_PlanHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		String sNroPed = null;

		if (ListaTerminalesPlanes().exists())
		{
			//Leer los registros del servicio y buscar el nro de pedido
			ITestDataTable iDataTable = (ITestDataTable) ListaTerminalesPlanes().getTestData("contents");
			int rows = iDataTable.getRowCount();
			int cols = iDataTable.getColumnCount();
			String sEstado = null;

			// Solo se espera que haya un red con el numero pasado como parametro
			int currentrow=0;
			boolean bEncontrado = false;
			while (currentrow<rows && !bEncontrado)
			{
				currentrow=currentrow+1; // comienza en la fila 1
				sNroPed = iDataTable.getCell(currentrow, 1).toString();
				sEstado = iDataTable.getCell(currentrow, 9).toString();
				System.out.println(currentrow  + sNroPed + sEstado );
				if (sNroPed.equals(argu[1].toString())) bEncontrado = true;
			} // queda posicionado en la fila encontrada
			if (bEncontrado && sEstado.toLowerCase().equals(argu[2].toString().toLowerCase()) ) 
				{
				// agregar el PV
				argu[0] = "OK";		
				}
		}

		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}


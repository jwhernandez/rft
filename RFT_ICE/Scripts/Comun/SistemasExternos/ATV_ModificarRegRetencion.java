package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.ATV_ModificarRegRetencionHelper;
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
 * Description   : Ejecuta la ruta registro de retenciones de clientes por ordenes
 * Script Name   :  <b>ATV_ModificarRegRetención</b>
 * @since  2016/09/06
 * Parametros: 0) Resultado = OK/NOK 1) MsjError 2) nro pedido 3)Accion 4) Motivo 5) Activo
 * res res 1-1735576628 agregar motivo res
 */
public class ATV_ModificarRegRetencion extends ATV_ModificarRegRetencionHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		String sEstadoATV="OK";
		String sActivo=null;
		String sAccion = argu[3].toString().toLowerCase(); // consultar agregar

		Inicio().click();
		OpcionRegdeRetencionCliente().click();		
		RegRetencion().click();
		NroPedido().waitForExistence();
		NroPedido().setText(argu[2].toString());
		Consultar().click();

		RegisitroRetencion().waitForExistence();
		if (RegisitroRetencion().exists())
		{
			//Leer los registros del servicio y buscar el nro de pedido
			ITestDataTable iDataTable = (ITestDataTable) RegisitroRetencion().getTestData("contents");
			int rows = iDataTable.getRowCount();
			int cols = iDataTable.getColumnCount();
			System.out.println(rows  + "*" +cols  );

			sActivo = iDataTable.getCell(5, 1).toString();
			System.out.println("Activo=" + sActivo  );
			sEstadoATV = iDataTable.getCell(8, 1).toString();
			System.out.println("Estado=" + sEstadoATV  );
			if (sAccion.equals("agregar") && sActivo.toLowerCase().equals("no"))
			{
				Motivo().setText(argu[4].toString());
				Agregar().click();		
				sleep(10);
				// ver si refresca solo o si hace falta hacer algo más como volver a consultar
				sActivo = iDataTable.getCell(5, 1).toString();
				System.out.println("Activo=" + sActivo  );
				argu[0]="OK";
			} else
				if (sAccion .equals("consultar") && sActivo.toLowerCase().equals("sí"))
					argu[0]="OK";
				else argu[1]= sEstadoATV;
			argu[5] = sActivo;
		}
		
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0] + " Activo=" + sActivo + " Error" + argu[1]);
	}
}


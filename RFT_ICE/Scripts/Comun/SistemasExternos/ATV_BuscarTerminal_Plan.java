package Scripts.Comun.SistemasExternos;

import com.rational.test.ft.RationalTestException;

import resources.Scripts.Comun.SistemasExternos.ATV_BuscarTerminal_PlanHelper;
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
import java.awt.Rectangle;

/**
 * Description   : Ejecuta la ruta registro de retenciones de clientes por ordenes
 * Script Name   :  <b>ATV_BuscarTerminal_Plan</b>
 * @since  2016/09/14
 * Parametros: 0) Resultado = OK/NOK 1) nro pedido 2)Nombre plan 
 * 3) Nombre terminal o PLAN SIN TERMINAL
 * Ej res 1-1735047671 NA NA 
 * // VC 20170627 No se valida el terminal en el applet si se manda como parámetro PLAN SIN TERMINAL
 */
public class ATV_BuscarTerminal_Plan extends ATV_BuscarTerminal_PlanHelper
{
	public void testMain(Object[]argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		String sNroPed = null;
		String sPlanId = null;
		String sPlan = null;
		String sTerminal = null;
		Rectangle area = new Rectangle();
		String path = "C:\\"; // cambiar por Resultados/CP0x/ obtener de variable global
		String filename = null;

		Inicio().click();
		HistoricoVentaTerminal_Plan().click();

		Tipo().select("NUM.ORDEN");
		ValorTipo().setText(argu[1].toString());
		Consutar().click();

		ListaTerminalesPlanes().waitForExistence();

		if (ListaTerminalesPlanes().exists())
		{
			//Leer los registros del servicio y buscar el nro de pedido
			ITestDataTable iDataTable = (ITestDataTable) ListaTerminalesPlanes().getTestData("contents");
			int rows = iDataTable.getRowCount();
			int cols = iDataTable.getColumnCount();
			System.out.println("Filas=" + rows + " Columnas"+ cols);
			area =ListaTerminalesPlanes().getClippedScreenRectangle();
			ListaTerminalesPlanes().ensureObjectIsVisible();
			// Generar una variable filename
			filename = path + "Planes.jpg";
			CapturarPantalla(filename, area);
			// Solo se espera que haya un red con el numero pasado como parametro
			int currentrow=1;
			boolean bEncontrado = false;
			while (currentrow<rows && !bEncontrado)
			{
				sPlanId = iDataTable.getCell(currentrow, 0).toString();
				sNroPed = iDataTable.getCell(currentrow, 1).toString();
				sPlan = iDataTable.getCell(currentrow, 12).toString();
				sTerminal = iDataTable.getCell(currentrow, 14).toString();
				System.out.println(currentrow + sPlanId+ sPlan  + sNroPed );
				if (sNroPed.equals(argu[1].toString())) bEncontrado = true;
				currentrow=currentrow+1;
			} 
			if (bEncontrado)
			{
				if (sPlan.equals(argu[2].toString()) && 
						sTerminal.equals(argu[3].toString())) 
				{
					if (ListaTerminales().exists() && !sTerminal.equals("PLAN SIN TERMINAL")) // VC 20170627 Si el plan no tiene terminal no se tiene que ejecutar esa validación
					{
						//Leer los registros del servicio y buscar el nro de pedido
						iDataTable = (ITestDataTable) ListaTerminales().getTestData("contents");
						rows = iDataTable.getRowCount();
						cols = iDataTable.getColumnCount();
						System.out.println("Filas=" + rows + " Columnas"+ cols);
						area =ListaTerminales().getClippedScreenRectangle();
						ListaTerminales().ensureObjectIsVisible();
						// Generar una variable filename
						filename = path + "Planes.jpg";
						CapturarPantalla(filename, area);
						// Solo se espera que haya un red con el numero pasado como parametro
						currentrow=1;
						bEncontrado = false;
						while (currentrow<rows && !bEncontrado)
						{
							String sPlanIdTerminal = iDataTable.getCell(currentrow, 0).toString();
							String sTipo = iDataTable.getCell(currentrow, 2).toString();
							System.out.println(currentrow + sPlanId + sTipo );
							if (sPlanIdTerminal.equals(sPlanId)) bEncontrado = true;
							currentrow=currentrow+1;
						}
						if (bEncontrado)
						{
							argu[0] = "OK";					
						}

					}else{
						// VC 20170627 Si el plan no tiene terminal no se tiene que ejecutar esa validación
						argu[0] = "OK";
					}
				} // El terminal o el plan no coincide
			}
		}
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}


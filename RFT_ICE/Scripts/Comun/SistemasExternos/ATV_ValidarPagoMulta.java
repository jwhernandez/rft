package Scripts.Comun.SistemasExternos;
import java.awt.Rectangle;

import libreria.utileria.Tipo;

import resources.Scripts.Comun.SistemasExternos.ATV_ValidarPagoMultaHelper;
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
 * Description   : Validar el pago de multas
 * Script Name   :  <b>ATV_ValidarPagoMulta</b>
 * @since  2016/10/26
 * @author SS
 * Parametros: 0) Resultado = OK/NOK 1) nro pedido 2)Tipo (VENTA TERMINAL O MULTA CAMBIO DE PLAN
 * 3) Estado multa (ENVIADA A NOMINA) 4) Estado Pago (PAGADO, COMPRADO BRM)
 * Ej res 1-1735047671 "VENTA TERMINAL" "ENVIADA A NOMINA" "PAGADO" 
 * Ej res 1-1735047671 "MULTA CAMBIO PLAN" "ENVIADA A NOMINA" "COMPRADO BRM" 
 * Ej CP76 res 1-1735047671 "VENTA TERMINAL" "ENVIADA A GITEL" "MONTO=0" 
 * Ej res 1-1735307529 	"MULTA RETIRO" "PAGADA CON RED"
 * NOTA EN los registros de terminal se puede poner NA para que no valide que haya un registro ahi
 * Ej res 1-1735307529 	"NA" "ENVIADA A GITEL" "NA"  (DA NOK)
 * Ej res 1-1735228566 "NA" "PAGADA CON RED" "NA" (DA OK)
 */
public class ATV_ValidarPagoMulta extends ATV_ValidarPagoMultaHelper
{
	public void testMain(Object[]argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		String sNroPed = null;
		String sPlanId = null;
		String sEstadoMulta = null;
		String sMontoMulta = null;
		String sTipo = null;
		String sEstadoDet = null;
		String sScriptName=getScriptName().toString(); // 22/11/2016

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
			// Solo se espera que haya un red con el numero pasado como parametro
			int currentrow=1;
			boolean bEncontrado = false;
			while (currentrow<rows && !bEncontrado)
			{
				sPlanId = iDataTable.getCell(currentrow, 0).toString();
				sNroPed = iDataTable.getCell(currentrow, 1).toString();
				System.out.println(currentrow + sPlanId + sNroPed );
				if (sNroPed.equals(argu[1].toString())) bEncontrado = true; // Si se encuentra el pedido
				currentrow=currentrow+1;
			} 
			if (bEncontrado)
			{
				if(ListaMultas().exists())
				{
					iDataTable = (ITestDataTable) ListaMultas().getTestData("contents");
					rows = iDataTable.getRowCount();
					cols = iDataTable.getColumnCount();
					System.out.println("Multas: Filas=" + rows + " Columnas"+ cols);
					// Solo se espera que haya un red con el numero pasado como parametro
					currentrow=1;
					bEncontrado = false;
					while (currentrow<rows && !bEncontrado)
					{
						String sPlanIdMulta = iDataTable.getCell(currentrow, 0).toString();
						sMontoMulta = iDataTable.getCell(currentrow, 8).toString();
						sEstadoMulta = iDataTable.getCell(currentrow, 9).toString();
						System.out.println("Multas: "+ currentrow + "-" + sPlanId + "-"  + sEstadoMulta + "-"  + sMontoMulta );
						if (sPlanIdMulta.equals(sPlanId)) bEncontrado = true;
						currentrow=currentrow+1;
					}
					if (bEncontrado)
					{
						// Se chequea los valores de multas con los parametros deseados
						System.out.println("Estado Multa deseado y ATV "+ argu[3] + "-"+ sEstadoMulta);
						infoPaso(sScriptName, Tipo.DATO,"Estado Multa "+argu[1].toString(),sEstadoMulta);
						// Se muestra la multa
						System.out.println("Monto Multa ATV " + sMontoMulta);
						infoPaso(sScriptName, Tipo.DATO,"Monto Multa "+argu[1].toString(),sMontoMulta);

						if (argu[3].toString().equals(sEstadoMulta)) 
						{
							if (argu[2].toString().equals("NA") && argu[4].toString().equals("NA"))
								argu[0]="OK"; // Si es NA No se analiza el panel de terminale
								else
								{
								if (ListaTerminales().exists() )
									{
										//Leer los registros del servicio y buscar el nro de pedido
										iDataTable = (ITestDataTable) ListaTerminales().getTestData("contents");
										rows = iDataTable.getRowCount();
										cols = iDataTable.getColumnCount();
										System.out.println("Filas=" + rows + " Columnas"+ cols);
										// Solo se espera que haya un red con el numero pasado como parametro
										currentrow=1;
										bEncontrado = false;
										while (currentrow<rows && !bEncontrado)
										{
											String sPlanIdTerminal = iDataTable.getCell(currentrow, 0).toString();
											sTipo = iDataTable.getCell(currentrow, 2).toString();
											sEstadoDet = iDataTable.getCell(currentrow, 4).toString();
											System.out.println(currentrow + "-" + sPlanId + "-" + sTipo + "-" +sEstadoDet );
											if (sPlanIdTerminal.equals(sPlanId)) bEncontrado = true;
											currentrow=currentrow+1;
										}
										if (bEncontrado)
										{
											System.out.println("Tipo Multa deseado y ATV "+ argu[2] + "-"+ sTipo);
											infoPaso(sScriptName, Tipo.DATO,"Tipo Multa "+argu[1].toString(),sTipo);

											System.out.println("Estado Det Multa deseado y ATV "+ argu[4] + "-"+ sEstadoDet);
											infoPaso(sScriptName, Tipo.DATO,"Estado Det Multa "+argu[1].toString(),sEstadoDet);

											if (argu[2].toString().equals(sTipo) && argu[4].toString().equals(sEstadoDet))
												argu[0] = "OK";					
										}
										else // El plan id no está en terminales
										{
											argu[0] = "OK";					
											System.out.println("El pedido no tiene registros de terminales");
										}
									} // No hay panel de terminales entonces da error
							} // Se requiere validar el panel de terminales porque el param no es NA
						} // El estado de la multa no coincide con el deseado
					} // No multas para el pedido
				} // No hay registros de multas 
			}// El pedido no se encontró
		}
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}


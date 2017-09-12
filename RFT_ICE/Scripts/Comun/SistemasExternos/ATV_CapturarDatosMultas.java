package Scripts.Comun.SistemasExternos;
import libreria.utileria.Tipo;
import resources.Scripts.Comun.SistemasExternos.ATV_CapturarDatosMultasHelper;

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
 * Description   : Captura los datos para validar las posibles multas de un servicio
 * Script Name   :  <b>ATV_CapturarDatosMultas</b>
 * @since  2016/10/18
 * Parametros: 0) Resultado = OK/NOK 1) nro pedido 2) tipo_CP upgrade downgrade NA  
 * 3)plan capturado 4) terminal capturado 5) RPT 6) CPT-I 7) CPT-S 8) CPT-H 
 * 9)RPB 10)CPB
 * 
 * res 1-1735047671 downgrade res res res res res res res res 
 * res 1-1741391003 downgrade res res res res res res res res 
 * 
 */
public class ATV_CapturarDatosMultas extends ATV_CapturarDatosMultasHelper
{

	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		String sNroPed = null;
		String sPlan = null;
		String sTerminal = null;
		int iTenencia = 0; 
		int iPermanencia = 0;  
		int iDiasRestan = 0;
		double iPrecioPlan = 0; 
		double iPrecioVenta = 0;  
		double iCosto = 0;  
		double iGastos = 0;
		double iDescDiario = 0; 
		double iRPT = 0;  
		double iCPT_i = 0;
		double iCPT_s = 0;
		double iRPB = 0;  
		double iCPB = 0;
		String sScriptName=getScriptName().toString(); // 22/11/2016
		
		Inicio().click();
		HistoricoVentaTerminal_Plan().waitForExistence();
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

			ListaTerminalesPlanes().ensureObjectIsVisible();
			// Generar una variable filename

			// Solo se espera que haya un red con el numero pasado como parametro
			String sTipo_CP = argu[2].toString();
			int currentrow=0;
			boolean bEncontrado = false;
			while (currentrow<rows && !bEncontrado)
			{
				currentrow=currentrow+1;
				sNroPed = iDataTable.getCell(currentrow, 1).toString();
				sPlan = iDataTable.getCell(currentrow, 12).toString();
				sTerminal = iDataTable.getCell(currentrow, 14).toString();
				System.out.println(currentrow +sNroPed+sPlan+sTerminal );
				if (sNroPed.equals(argu[1].toString())) bEncontrado = true;
			} 
			// Salgo con currentrow igual a la del plan encontrado
			if (bEncontrado)
			{	
				iTenencia = Integer.parseInt(iDataTable.getCell(currentrow, 26).toString());
				infoPaso(sScriptName, Tipo.DATO,"Tenencia ",iDataTable.getCell(currentrow, 26).toString());
				System.out.println("Tenencia "+ iTenencia );
				iPermanencia = Integer.parseInt(iDataTable.getCell(currentrow, 25).toString());
				infoPaso(sScriptName, Tipo.DATO,"Permanencia ",iDataTable.getCell(currentrow, 25).toString());
				System.out.println("Permanencia "+ iPermanencia );
				iDiasRestan = Integer.parseInt(iDataTable.getCell(currentrow, 27).toString());
				infoPaso(sScriptName, Tipo.DATO,"DiasRestan ",iDataTable.getCell(currentrow, 27).toString());
				System.out.println("DiasRestan "+ iDiasRestan );
				iDescDiario = Double.parseDouble(iDataTable.getCell(currentrow, 22).toString().replace(",", "")); 
				infoPaso(sScriptName, Tipo.DATO,"DescDiario ",iDataTable.getCell(currentrow, 22).toString());
				System.out.println("DescDiario "+ iDescDiario );
				iGastos = Double.parseDouble(iDataTable.getCell(currentrow, 21).toString().replace(",", "")); //.replace(".", "%").replace(",", ".").replace("%", ",")
				infoPaso(sScriptName, Tipo.DATO,"Gastos ",iDataTable.getCell(currentrow, 21).toString());
				System.out.println("Gastos "+ iGastos );
				iPrecioVenta = Double.parseDouble(iDataTable.getCell(currentrow, 19).toString().replace(",", ""));
				infoPaso(sScriptName, Tipo.DATO,"PrecioVenta ",iDataTable.getCell(currentrow, 19).toString());
				System.out.println("PrecioVenta "+ iPrecioVenta );
				// Agregado el 23-11-2016
				iCosto = Double.parseDouble(iDataTable.getCell(currentrow, 20).toString().replace(",", ""));
				infoPaso(sScriptName, Tipo.DATO,"Costo ",iDataTable.getCell(currentrow, 20).toString());
				System.out.println("Costo "+ iCosto );
				iPrecioPlan = Double.parseDouble(iDataTable.getCell(currentrow, 18).toString().replace(",", ""));
				infoPaso(sScriptName, Tipo.DATO,"PrecioPlan ",iDataTable.getCell(currentrow, 18).toString());
				System.out.println("PrecioPlan "+ iPrecioPlan );
				iRPT = Double.parseDouble(iDataTable.getCell(currentrow, 29).toString().replace(",", ""));
				infoPaso(sScriptName, Tipo.DATO,"RPT ",iDataTable.getCell(currentrow, 29).toString());
				System.out.println("RPT "+ iRPT );
				if (sTipo_CP.toLowerCase().equals("downgrade")) {
					iCPT_i = Double.parseDouble(iDataTable.getCell(currentrow, 30).toString().replace(",", ""));
					infoPaso(sScriptName, Tipo.DATO,"CPT_i",iDataTable.getCell(currentrow, 30).toString());
					System.out.println("CPT_i "+ iCPT_i );				
				} else // Upgrade
				{
					iCPT_s = Double.parseDouble(iDataTable.getCell(currentrow, 30).toString().replace(",", ""));
					infoPaso(sScriptName, Tipo.DATO,"CPT_s",iDataTable.getCell(currentrow, 30).toString());
					System.out.println("CPT_s "+ iCPT_s );	
				}
				iRPB = Double.parseDouble(iDataTable.getCell(currentrow, 31).toString().replace(",", ""));
				infoPaso(sScriptName, Tipo.DATO,"RPB ",iDataTable.getCell(currentrow, 31).toString());
				System.out.println("RPB "+ iRPB );
				iCPB = Double.parseDouble(iDataTable.getCell(currentrow, 32).toString().replace(",", ""));
				infoPaso(sScriptName, Tipo.DATO,"CPB ",iDataTable.getCell(currentrow, 32).toString());
				System.out.println("CPB"+ iCPB );
				
				argu[3] = iDataTable.getCell(currentrow, 12).toString(); // plan
				infoPaso(sScriptName, Tipo.DATO,"Plan ",iDataTable.getCell(currentrow, 12).toString());
				System.out.println("Plan"+ argu[3] );
				argu[4] = iDataTable.getCell(currentrow, 14).toString();
				infoPaso(sScriptName, Tipo.DATO,"Terminal ",iDataTable.getCell(currentrow, 14).toString());
				System.out.println("Terminal"+ argu[4] );
				
				// Calculo de formulas
				double iRPT_Calc = iPrecioVenta - iPrecioPlan;
				double iRPB_Calc = iTenencia * iDescDiario;
				double iCPB_Calc = (iDescDiario * iDiasRestan) + iGastos;
				// Se modifica la formula 23-11-2016
				double iCPT_s_Calc = (iCosto - iPrecioPlan) / iPermanencia * iDiasRestan;
				//double iCPT_s_Calc = (iPrecioVenta - iPrecioPlan) / iPermanencia * iDiasRestan;
				double iCPT_i_Calc = iCPT_s_Calc + iGastos;

				infoPaso(sScriptName, Tipo.DATO,"RPT_Calc ",String.valueOf(iRPT_Calc));
				System.out.println("RPT_Calc"+ iRPT_Calc );
				infoPaso(sScriptName, Tipo.DATO,"RPB_Calc ",String.valueOf(iRPB_Calc));
				System.out.println("RPB_Calc"+ iRPB_Calc );
				infoPaso(sScriptName, Tipo.DATO,"CPB_Calc ",String.valueOf(iCPB_Calc));
				System.out.println("CPB_Calc"+ iCPB_Calc );
				infoPaso(sScriptName, Tipo.DATO,"CPT_s_Calc ",String.valueOf(iCPT_s_Calc));
				System.out.println("CPT_s_Calc"+ iCPT_s_Calc );
				infoPaso(sScriptName, Tipo.DATO,"CPT_i_Calc ",String.valueOf(iCPT_i_Calc));
				System.out.println("CPT_i_Calc"+ iCPT_i_Calc );
				
				argu[5] = String.valueOf(Math.round(iRPT_Calc* 100.0) / 100.0);
				// Se pasan las formulas CPT_i, CPT_s y CPT_h
				argu[6] = String.valueOf(Math.round(iCPT_i_Calc* 100.0) / 100.0);	// CPT_i
				argu[7] = String.valueOf(Math.round(iCPT_s_Calc* 100.0) / 100.0);   // CPT_s = CPT_h
				argu[8] = String.valueOf(Math.round(iCPT_s_Calc* 100.0) / 100.0);	// CPT_h = CPT_s
				argu[9] = String.valueOf(Math.round(iRPB_Calc* 100.0) / 100.0);
				argu[10] = String.valueOf(Math.round(iCPB_Calc* 100.0) / 100.0);	
				
				// Se valida multa CPB que solo esta en esta pestaña
				iCPB_Calc =Math.round(iCPB_Calc* 100.0) / 100.0;
				System.out.println("Se realizará la comparación de multa CPB " + iCPB + "-" + iCPB_Calc);
				if (iCPB == iCPB_Calc) argu[0] = "OK";
			}
		}
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}


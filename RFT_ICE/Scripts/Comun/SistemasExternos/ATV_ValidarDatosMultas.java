package Scripts.Comun.SistemasExternos;
import libreria.utileria.Tipo;
import resources.Scripts.Comun.SistemasExternos.ATV_ValidarDatosMultasHelper;
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
/**Tabla1().
 * Description   : Validar los datos de posibles multas contra las formulas calculads
 * Script Name   :  <b>ATV_ValidarDatosMultas</b>
 * @since  2016/10/19
 * Parametros: 0) Resultado = OK/NOK 1) nro pedido 2) tipo_CP upgrade downgrade NA  
 * 3)plan capturado 4) terminal capturado 5) RPT 6) CPT-I 7) CPT-S 8)CPT-h 9)RPB 10)CPB
 * 
 * res 10112131 downgrade res res res res res res res res
 * última modificación: VC 20170711 se corrige error as columnas de beneficio y terminal se encontraban invertidas
 */
public class ATV_ValidarDatosMultas extends ATV_ValidarDatosMultasHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());

		argu[0] = "NOK";
		String sPlan = null;
		String sTerminal = null;
		String sBeneficio = null;
		boolean bBeneficio = false;

		double iRPT = 0;  
		double iCPT_i = 0;
		double iCPT_h = 0;
		double iCPT_s = 0;
		double iRPB = 0;  
		String sScriptName=getScriptName().toString(); // 22/11/2016

		Inicio().click();
		PosiblesMultas().waitForExistence();
		PosiblesMultas().click();

		Servicio().setText(getNroServicio());
		BrowserIEATV(Documento_IE_ATV(),DEFAULT_FLAGS).inputKeys("{ENTER}");


		Tabla0().waitForExistence();

		if (Tabla0().exists())
		{
			ITestDataTable iDataTable;
			//Leer los registros del plan y terminal
			if (Tabla1().exists()) 
				iDataTable = (ITestDataTable) Tabla1().getTestData("contents");
			else 
				iDataTable = (ITestDataTable) Tabla0().getTestData("contents");

			int rows = iDataTable.getRowCount();
			int cols = iDataTable.getColumnCount();
			System.out.println(rows+"-"+cols);

			// Solo se espera que haya uno o dos registros por plan
			int currentrow=0;
			boolean bEncontrado = false;
			boolean bSalir = false;

			while (currentrow<rows-1 && !bSalir)
			{
				currentrow=currentrow+1; // comienza en la fila 1 (usa las filas 1 2 y 3 como máximo)

				sPlan = iDataTable.getCell(currentrow, 0).toString();

				if (sPlan.contains("TOTALES CRC")) bSalir = true;
				else 
				{
					sBeneficio = iDataTable.getCell(currentrow, 2).toString(); // VC 20170711 Las columnas de beneficio y terminal se encontraban invertidas
					sTerminal = iDataTable.getCell(currentrow, 1).toString();
					System.out.println(currentrow + "-" + sPlan+ "-" + sBeneficio + "-" + sTerminal);


					if (sPlan.equals(argu[3].toString()) || 
							(sPlan.equals("") && bEncontrado && sBeneficio.equals("OBLIGACION POR BENEFICIO"))) 
					{
						bEncontrado = true;


						//if (!sBeneficio.equals("OBLIGACION POR BENEFICIO")){ // VC 20170711 esta validación ya no aplica

							iRPT = Double.parseDouble(iDataTable.getCell(currentrow, 10).toString().replace(",","").replace("CRC ",""));
							iCPT_s = Double.parseDouble(iDataTable.getCell(currentrow, 12).toString().replace(",","").replace("CRC ",""));
							iCPT_h = Double.parseDouble(iDataTable.getCell(currentrow, 13).toString().replace(",","").replace("CRC ",""));
							iCPT_i = Double.parseDouble(iDataTable.getCell(currentrow, 14).toString().replace(",","").replace("CRC ",""));		
							System.out.println("Multa RPT "+iRPT+"-"+argu[5]);
							infoPaso(sScriptName, Tipo.DATO,"Multa RPT",String.valueOf(iRPT));

							System.out.println("Multa CPT-i "+iCPT_i+"-"+argu[6]);
							infoPaso(sScriptName, Tipo.DATO,"Multa CPT-i",String.valueOf(iCPT_i));
							System.out.println("Multa CPT-s "+iCPT_s+"-"+argu[7]);
							infoPaso(sScriptName, Tipo.DATO,"Multa CPT-s",String.valueOf(iCPT_s));
							System.out.println("Multa CPT-h "+iCPT_h+"-"+argu[8]);
							infoPaso(sScriptName, Tipo.DATO,"Multa CPT-h",String.valueOf(iCPT_h));
						//} else
						//{
							/*bBeneficio = true;
							System.out.println("**************************** " + iDataTable.getCell(currentrow, 5).toString());
							iRPB = Double.parseDouble(iDataTable.getCell(currentrow, 10).toString().replace(",","").replace("CRC ",""));
							System.out.println("Multa RPB "+iRPB+"-"+argu[9]);
							infoPaso(sScriptName, Tipo.DATO,"Multa RPB",String.valueOf(iRPB));*/
						//}
					} else
					{
						if (bEncontrado) bSalir = true;
						System.out.println("Plan no coincide" + bEncontrado + bSalir);
					}
				}
			} // queda posicionado en la fila encontrada
			if (bEncontrado ) 
			{
				boolean bValidacion =true;
				double iRPT_Deseado = Double.parseDouble(argu[5].toString());
				if  (iRPT!=iRPT_Deseado && argu[2].toString().toUpperCase().equals("NA")) 
				{
					System.out.println("No coincide la multa RPT ");
					infoPaso(sScriptName, Tipo.ERROR,String.valueOf(getNroPaso()),"Multa RPT No Coincide");
					bValidacion =false;
				}
				// Validaciones de multas de CPT
				// VC 20170711 Se añade opción para que solo valide lo que se mandó por parámetro en la variable ATV_TIPO_CP 
				double iCPT_i_Deseado = Double.parseDouble(argu[6].toString());				
				if  (iCPT_i!=iCPT_i_Deseado && (argu[2].toString().toLowerCase().equals("downgrade") || argu[2].toString().toUpperCase().equals("NA"))) 
				{
					System.out.println("No coincide la multa CPT-Inferior ");
					infoPaso(sScriptName, Tipo.ERROR,String.valueOf(getNroPaso()),"Multa CPT Inferior No Coincide");
					bValidacion =false;
				}
				double iCPT_s_Deseado = Double.parseDouble(argu[7].toString());
				if  (iCPT_s!=iCPT_s_Deseado  && (argu[2].toString().toLowerCase().equals("upgrade") || argu[2].toString().toUpperCase().equals("NA"))) 
				{
					System.out.println("No coincide la multa CPT-Superior ");
					infoPaso(sScriptName, Tipo.ERROR,String.valueOf(getNroPaso()),"Multa CPT Superior No Coincide");
					bValidacion =false;
				}	
				double iCPT_h_Deseado = Double.parseDouble(argu[8].toString());
				if  (iCPT_h!=iCPT_h_Deseado  && (argu[2].toString().toLowerCase().equals("horizontal") || argu[2].toString().toUpperCase().equals("NA"))) 
				{
					System.out.println("No coincide la multa CPT-Horiontal ");
					infoPaso(sScriptName, Tipo.ERROR,String.valueOf(getNroPaso()),"Multa CPT Horiontal No Coincide");
					bValidacion =false;
				}				
				double iRPB_Deseado = Double.parseDouble(argu[9].toString());				
				if  (bBeneficio && iRPB!=iRPB_Deseado  && argu[2].toString().toUpperCase().equals("NA")) 
				{
					System.out.println("No coincide la multa RPB ");
					infoPaso(sScriptName, Tipo.ERROR,String.valueOf(getNroPaso()),"Multa RPB Inferior No Coincide");
					bValidacion =false;
				}
				if (bValidacion) argu[0] = "OK";		
			}
		}
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}


package Scripts.Comun.SistemasExternos;
import libreria.utileria.Tipo;
import resources.Scripts.Comun.SistemasExternos.BRM_ValidarIMSI_ServicioHelper;

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
 * Description   : Valida un cambio de número en BRM  
 * Script Name   : <b>BRM_ValidarIMSI_Servicio</b>
 * @since  2016/12/02
 * @author FF VC
 * Ult Modif ss 17/02/2012 se agrega opcion de validar imsi
 * Parametros: 0) Resultado = OK/NOK 1) NumeroServicio o IMSI 2) NA o Nro de IMSI
 * ej res IMSI 712012008895188
 */
public class BRM_ValidarIMSI_Servicio extends BRM_ValidarIMSI_ServicioHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		argu[0] = "NOK";
		
		sleep(5);
		pestañas().click(atText("Services"));
		sleep(5);

		Servicios().waitForExistence(); 
		
		boolean encontrado = false;
		String sDatoBRM = "";
		String sDatoBuscado = argu[1].toString();
		String sTipo = "";
		ITestDataTable iDataTable = (ITestDataTable) Servicios().getTestData("contents");
		int rows = iDataTable.getRowCount();
		
		for (int i = 0; i < rows; i++) {
			sTipo = iDataTable.getCell(i, 0).toString();
			if (sTipo.equals("/service/telco/gsm/telephony")) {
				Servicios().click(atCell(atRow(atIndex(i)),
						atColumn("Type")));
				encontrado = true;
				break;
			}
		}
		
		if(encontrado){
			Acciones().click();
			popUpAcciones().click(atPath("View Aliases"));
			
			iDataTable = (ITestDataTable) ServiceAliases().getTestData("contents");
			int rowsService = iDataTable.getRowCount();

			if (argu[1].toString().toLowerCase().equals("imsi"))
				sDatoBuscado = argu[2].toString();
			else
				sDatoBuscado = argu[1].toString();
			
			
			encontrado = false;
			for (int i = 0; i < rowsService; i++) {
				sDatoBRM = iDataTable.getCell(i, 0).toString();
				System.out.println(sDatoBRM);
				// Valida que coincida el IMSI o el nro de servicio segun se indique por parametria
				if (sDatoBRM.equals(sDatoBuscado)) {					
					ServiceAliases().click(atCell(atRow(atIndex(i)),atColumn(0)));
					sleep(10);
					encontrado = true;
					i=rowsService;
					break;
				}
			}
			
			OKServiceAliases().click();
		}else{
			System.out.println("Tipo no encontrado " + sTipo);
		}
		
		if(encontrado){
			argu[0] = "OK";
			System.out.println("El número de servicio coincide " + sDatoBRM + " - " + sDatoBuscado);
			infoPaso(getScriptName().toString(), Tipo.DATO,"Servicio o IMSI Validado", sDatoBRM +  sDatoBuscado );
		}
		else 
		{
			System.out.println("El número de servicio no coincide " + sDatoBRM + " - " + sDatoBuscado);
			infoPaso(getScriptName().toString(), Tipo.DATO,"Servicio o IMSI Validado", sDatoBRM +  sDatoBuscado );
		}
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);

	}
}


package Scripts.Comun;
import java.text.SimpleDateFormat;

import libreria.utileria.Tipo;
import resources.Scripts.Comun.BRM_ValidarVigenciaOLDHelper;
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
 * Description   : Validar en BRM la vigencia del producto SMS Favorito.  
 * Script Name   : <b>BRM_ValidarVigencia</b>
 * @since  2016/11/28
 * @author VC-FF
 * Parametros: 0) Resultado = OK/NOK 1) Producto 2)Estado 3) vigencia 4) FechaInicio 
 *  * ej res "Regalia SMS Favorito" "Activo" 3 "17/04/2016"
 */
public class BRM_ValidarVigenciaOLD extends BRM_ValidarVigenciaOLDHelper
{
	public void testMain(Object[] argu) 
	{
		String sEstado = null;
		String sProd =  null;
		ITestDataTable iDataTable = null;
		String sProdDeseado = "Regalia SMS Favorito";//argu[1].toString();
		String sVigencia = argu[3].toString(); 
		String sScriptName=getScriptName().toString();
		boolean error = false;
		
		int iVigencia = 0;

    	final long CONVERTIR_MIN_A_DIAS = 60 * 24;
    	int dias = 0;
    	
    	ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
    	//infoPaso(sScriptName, Tipo.DATO,"Validar vigencia: ", sVigencia);
    	
		argu[0] = "NOK";
		
		iVigencia = Integer.parseInt(sVigencia);
    	
    	dias  = iVigencia * 30;
		
		
		pesta�as().click(atText("Plans"));
		sleep(10);

		productos().waitForExistence(); // no funciona
		
		sleep(5);
		
		iDataTable = (ITestDataTable) productos().getTestData("contents");
		int rows = iDataTable.getRowCount();

		// for (int i = 0; i <rows && !bEncontro; i++)
		for (int i = 0; i < rows; i++) {
			error = true;
			sProd = iDataTable.getCell(i, 1).toString();
			sEstado = iDataTable.getCell(i, 4).toString();
			// Valida que el estado sea activo y que el producto sea el deseado
			if (sEstado.equals("1") && sProd.equals(sProdDeseado)) {
				productos()
						.click(atCell(atRow(atIndex(i)),
								atColumn("Product/Discount")));
				sleep(10);

				error = false;
				break;
			}

		}
		
		if (!error) {
			long diasVigencia = Long.parseLong(vigenciaPromocion().getText()); // lo
																				// obtiene
																				// en
																				// minutos
			diasVigencia = diasVigencia / CONVERTIR_MIN_A_DIAS;
			if (diasVigencia == dias) {
				infoPaso(sScriptName, Tipo.DATO,
						"Vigencia par�metros - Vigencia BRM:", dias + "-"
								+ diasVigencia);
				argu[0] = "OK";
			} else {
				infoPaso(sScriptName, Tipo.ERROR,
						"Vigencia par�metros - Vigencia BRM no coincide:", dias
								+ "-" + diasVigencia);
			}
		}
	  
	    ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}


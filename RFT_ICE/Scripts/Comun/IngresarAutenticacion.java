package Scripts.Comun;
import resources.Scripts.Comun.IngresarAutenticacionHelper;
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
* Descripción: Permite ingresar el tipo de autenticacion
* Parámetros:  0) Tipo de autenticacion 1) Encontrado / No Encontrado 2) Canal o NA
* Ej:  Documentos res "Agencia ICE"
* SS Nov 2015
*/
public class IngresarAutenticacion extends IngresarAutenticacionHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		String sTipo = argu[0].toString();	
		argu[1] = "No Encontrado";
		System.out.println("Ingreso en IngresarAutenticacion");
		
	
		document_detalles().ensureObjectIsVisible();
		BotonesPedidoEncabezado().ensureObjectIsVisible();
	
		System.out.println("Cantidad de filas del tipo de autenticacion: "+ TipoAutenticacion(). getProperty("Count"));	
		int iTotal = (int) TipoAutenticacion(). getProperty("Count");

		if (iTotal == 0) {
			document_detalles().ensureObjectIsVisible();
			BotonesPedidoEncabezado().ensureObjectIsVisible();
		} 

		// Si canal del DP no es NA ingresa el canal
		if (!(argu[2].toString().equals("NA"))) {
			Canal().setText(argu[2].toString());			
		}

		// Verifica que el tipo de identificación exista para ese canal y lo ingresa		
		ITestDataList dataList = (ITestDataList)TipoAutenticacion().getTestData("list");
		ITestDataElementList elementList = (ITestDataElementList)dataList.getElements();

		for (int i=0; i<iTotal ;i++) {
			System.out.println("Número de ítem de la lista de autenticación: "+elementList.getElement(i).getElement().toString());
			if (sTipo.equals(elementList.getElement(i).getElement().toString())) { 
				argu[1] = "Encontrado";
				TipoAutenticacion().setText(sTipo);
				i=iTotal+1;
			}
		}
		System.out.println("Resultado:" + argu[1]);
	}
}
	


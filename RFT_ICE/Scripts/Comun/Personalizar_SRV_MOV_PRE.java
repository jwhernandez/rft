package Scripts.Comun;
import resources.Scripts.Comun.Personalizar_SRV_MOV_PREHelper;
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
* Descripción: Recibe accion nombre pestaña y nombre producto
* Parámetros:  Agrega/elimina producto (aun no implementado esto)
*              devuelve Encontrado o No Encontrado 
* @Param 0) Alta / Baja (no está fucionando aún solo hace click) 
* 1) Facilidades Telefonia Movil 2) Producto 
* 3) para futuro uso de la relación no se usa aun
* 4) Encontrado  / No Encontrado 5) Ambiente 6) Tramite            
* SS Nov 2015 
* ej: Alta "Facilidades Telefonia Movil" "No Caller Id" res res PREQA PortIn
*/
public class Personalizar_SRV_MOV_PRE extends Personalizar_SRV_MOV_PREHelper
{
public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		String sTramite = "Venta";
		if (argu.length >= 7 ) { 
			sTramite = argu[6].toString(); // tramite
		}
		
		String[] ValidarMsj;
		ValidarMsj = new String[5];
		// Parámetros: 0) Recibe código de mensaje a validar, 
		// 1) OUT un boolean true / false 2) OUT devuelve mensaje  
		// 3) recibe un tipo de mensaje si no es HTML el mensaje a validar (opcional)
		// HTML o BrowserScript
		// Si el Msj no es el correcto no lo acepta y queda el sistema en la pantalla del Msj para que el paso
		// 4) Ambiente 
				
		sleep(2);
		
		if (!sTramite.equals("PortIn")) 
		{
			Personalizar().performAction();
			//boolean bExiste = (boolean) Advertencia().isEnabled();
			//boolean bExiste = (boolean)  ErrorMessage().getProperty("IsEnabled");
			//if (bExiste) ButtonOk().performAction();
		}
			
		if (sTramite.equals("PortIn")) PersonalizarPI().performAction();
		
		argu[4] = "No Encontrado";
		String sPestaña = argu[1].toString();
		String sProducto = argu[2].toString();
		
		switch (sPestaña) {
	        case "Facilidad Telefonia Movil":
	        	Facilidades_Telefonia_Movil().click();	
	    		ITestDataTable t=(ITestDataTable)Items_Facilidades_Telefonia_Movil().getTestData("contents");
	    		String sSalir = "no" ;
	    		int i=0;
	    		for( i=0;i<t.getRowCount() && sSalir == "no";i++)
	    		{
	    			System.out.println(t.getCell(i,1));
	    			//ITestDataText iText = (ITestDataText) t.getCell(i,1);
	    			//ITestDataText iText2 = (ITestDataText) t.getCell(i,0);
                    if (t.getCell(i, 1).toString().equals(sProducto) ) {
	    					sSalir = "si";
	    					System.out.println("Encontrado");
	    		   	       // String sText = iText.getText();
	    	    	       // GuiTestObject sItem =  (GuiTestObject) Items_Facilidades_Telefonia_Movil().getSubitem(atCell(atRow(i), atColumn(1)));
	    	    	        GuiTestObject sItem2 =  (GuiTestObject) Items_Facilidades_Telefonia_Movil().getSubitem(atCell(atRow(i), atColumn(0)));
	       	    	        //System.out.println("Esta chequeado " + sItem2.getProperty(".checked") );
	    	    	        //if ((!sItem.isEnabled() && sAccion == "Alta")||
	    	    			//     (sItem.isEnabled() && sAccion == "Baja")){
	    	    				  sItem2.click();
	    	    			//}
	    	   			}
	    		}
	    		System.out.println("celda seleccionada");
	    		System.out.println(t.getCell(i-1,1));
	        	break;
	        case "Facilidades Navideñas":
	            break;
	        default:  
	        	argu[4] = "No Encontrado";
	        	break;
		} // end del switch
		
		Terminar().click();
		ValidarMsj[0]="DPM0027"; // La configuración que desea realizar está incompleta
		ValidarMsj[3] = "BrowserScript";
		ValidarMsj[4] = argu[5].toString();
		callScript("Scripts.Comun.ValidarMensaje",ValidarMsj);

		// Acepta valores como true o No Existe o false 
		// procesa el true
		if  (ValidarMsj[1].toString().equals("true") ){ // dio el mensaje 27
			Finalicelo().click();
			Terminar().click();
		}
		// procesa el false
		if  (ValidarMsj[1].toString().equals("false") ){ // el mensaje no coincide 
			ValidarMsj[0]="DPM0033"; 
			callScript("Scripts.Comun.ValidarMensaje",ValidarMsj);
			if  (ValidarMsj[1].toString().equals("true") ){ // dio el mensaje 33
				Finalicelo().click();
				Terminar().click();
			}
		}
		argu[4] = "Encontrado";
		sleep(5);
		ImpreResultadoScript(getScriptName( ).toString(), argu[4].toString());
	}
}


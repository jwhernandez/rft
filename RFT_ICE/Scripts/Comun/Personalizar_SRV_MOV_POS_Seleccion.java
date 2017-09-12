package Scripts.Comun;
import resources.Scripts.Comun.Personalizar_SRV_MOV_POS_SeleccionHelper;
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
* Descripción: Agrega una facilidad postpago o la quita
* Script Name   : <b>Personalizar_SRV_MOV_POS_Seleccion</b> 
* @since   2017/03/28
* @author SS
* @Param 0) OK/NOK 1) Categoria 2) Producto 3) Accion
* (Accion aun no se usa)
* ej:  res "Facilidad Telefonia" "Roaming Internacional" Alta

*/
public class Personalizar_SRV_MOV_POS_Seleccion extends Personalizar_SRV_MOV_POS_SeleccionHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		argu[0] = "NOK";
		String sPestaña = argu[1].toString();
		String sProducto = argu[2].toString();
		
		switch (sPestaña) {
	        case "Facilidad Telefonia":
	        	Facilidad_Telefonia().click();	
	        	System.out.println("Se presiono Facilidad");
	    		ITestDataTable t=(ITestDataTable)Items_Facilidad_Telefonia().getTestData("contents");
	    		String sSalir = "no" ;
	    		int i=0;
	    		for( i=0;i<t.getRowCount() && sSalir == "no";i++)
	    		{
	    			System.out.println(t.getCell(i,1));
                    if (t.getCell(i, 1).toString().equals(sProducto) ) {
	    					sSalir = "si";
	    					System.out.println("Encontrado");
	    	    	        GuiTestObject sItem2 =  (GuiTestObject) Items_Facilidad_Telefonia().getSubitem(atCell(atRow(i), atColumn(0)));
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
		
		argu[0] = "OK";
		sleep(5);
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}


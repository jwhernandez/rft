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
* Descripción: Recibe accion nombre pestaña y nombre producto si es necesario relacion
* Parámetros:  Agrega/elimna producto
*              devuelve Encontrado o no Encontrado 
* @Param 0) Alta / Baja 1) Facilidades Telefonia Movil 2) Producto 4) Encontrado  / No Encontrado            
* SS Nov 2015
*/
public class Personalizar_SRV_MOV_PRE extends Personalizar_SRV_MOV_PREHelper
{
	public void testMain(Object[] argu) 
	{
		sleep(2);
 		Personalizar().performAction();
		argu[4] = "Encontrado";
		String sAccion = argu[0].toString();
		String sPestaña = argu[1].toString();
		String sProducto = argu[2].toString();
		
		switch (sPestaña) {
	        case "Facilidades Telefonia Movil":
	        	Facilidades_Telefonia_Movil().click();	
	    		ITestDataTable t=(ITestDataTable)Items_Facilidades_Telefonia_Movil().getTestData("contents");
	    		String sSalir = "no" ;
	    		int i=0;
	    		for( i=0;i<t.getRowCount() && sSalir == "no";i++)
	    		{
	    			System.out.println(t.getCell(i,1));
	    			ITestDataText iText = (ITestDataText) t.getCell(i,1);
	    			ITestDataText iText2 = (ITestDataText) t.getCell(i,0);
                    if (t.getCell(i, 1).toString().equals(sProducto) ) {
	    					sSalir = "si";
	    					System.out.println("Encontrado");
	    		   	        String sText = iText.getText();
	    	    	        GuiTestObject sItem =  (GuiTestObject) Items_Facilidades_Telefonia_Movil().getSubitem(atCell(atRow(i), atColumn(1)));
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
		sleep(5);
		
  }
}


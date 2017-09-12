package Scripts.Comun;
import java.util.Iterator;

import resources.Scripts.Comun.Personalizar_SRV_MOV_POS_ValidacionHelper;
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
* Script Name   : <b>Personalizar_SRV_MOV_POS_Validacion</b> 
* @since   2017/03/28
* @author SS
* @Param 0) OK/NOK 1) Categoria 2) Producto 3) EstadoDeseado (True o False)

* ej: res "Facilidad Telefonia" "Roaming Internacional" True
*  res "Facilidad Telefonia" "Llamadas Internacionales" True
*  res "Facilidad Telefonia" "Llamadas Internacionales" True

*/
public class Personalizar_SRV_MOV_POS_Validacion extends Personalizar_SRV_MOV_POS_ValidacionHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		argu[0] = "NOK";
		String sPestaña = argu[1].toString();
		String sProducto = argu[2].toString();
		TestObject[] to;
		GuiTestObject gto; 
		Boolean bEstadoDeseado = Boolean.valueOf(argu[3].toString());
		Boolean bProdHabilitado = null;
		Boolean bProdEncontrado = false;
		
		switch (sPestaña) {
	        case "Facilidad Telefonia":
	        	Facilidad_Telefonia().click();	
	        	System.out.println("Se presiono Facilidad");
	    		ITestDataTable t=(ITestDataTable)Items_Facilidad_Telefonia().getTestData("contents");
	    		String sSalir = "no" ;
	    		int i=0;
	    		// String pos=null;
	    		for( i=0;i<t.getRowCount() && sSalir == "no";i++)
	    		{
	    			System.out.println(t.getCell(i,1));
                    if (t.getCell(i, 1).toString().equals(sProducto) ) {
	    					sSalir = "si";
	    					// .type = checkbox && .classIndex = i
	    					// Buscar el checkbox asociado a ese producto para validar 
	    					// Si está habilitado o no.
	    					
	    					System.out.println(i+"-"+ t.getCell(i, 1));
	    					Property[] props = new Property[1];
								props[0] = new Property(".type", "checkbox");
								//props[1] = new Property(".class", "Html.INPUT.checkbox");
								//props[2] = new Property(".classIndex", i);

								to= Items_Facilidad_Telefonia().find(atChild(props));
								// for (int j = 0; j < to.length; j++) {
									gto = 	(GuiTestObject)	to[i-1];
									// System.out.println(j);
									System.out.println("Seleccionado?" + gto.getProperty(".checked"));
									bProdHabilitado = Boolean.valueOf(gto.getProperty(".checked").toString());
									bProdEncontrado = true;
									//System.out.println("*" + gto.getProperty(".classIndex")+ "*" );
									//System.out.println(gto.getProperty(".class"));
									//System.out.println(gto.getProperty(".disabled"));
								//}
								// if (sAccion.equals("na")) gto.click(); 
    					
	    					System.out.println("Encontrado");
	    	    	        GuiTestObject sItem2 =  (GuiTestObject) Items_Facilidad_Telefonia().getSubitem(atCell(atRow(i), atColumn(0)));
	    	    				  System.out.println(sItem2.getProperties());
	    	    			//}
	    	   			}
	    		}
	    		if (bProdEncontrado) 
	    			{
	    			System.out.println("Producto encontrado en estado = " + bProdHabilitado);
	    			if (bProdHabilitado == bEstadoDeseado) argu[0] = "OK";
	    			}
	        	break;
	        case "Facilidades Navideñas":
    			System.out.println("Opcion no implementada");
	        	argu[0] = "NOK";
	            break;
	        default:  
    			System.out.println("Opcion no implementada");
	        	argu[0] = "NOK";
	        	break;
		} // end del switch

		sleep(5);
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}


package Scripts.Comun;
import resources.Scripts.Comun.AsociarListaEspecialHelper;

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
import com.rational.test.ft.sys.SpyMemory; 
import com.rational.test.ft.sys.SpyMemoryStatistics; 

/**
 * Script Name   : <b>AsociarListaEspecial</b>
 * Descripcion   : Asocia una lista especial a un numero especial SMS o Voz
 * @author SS
 * @param Parámetros: 0) NOK / OK 1) Producto al cual asociar 2) Nombre de la lista
 * Precondiciones: Estar en el pedido
 * @since Nov 2015
 */
public class AsociarListaEspecial extends AsociarListaEspecialHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
        SpyMemoryStatistics stats =  SpyMemory.getStats();                 
        System.out.println("Number for Active heaps "+ stats.numberOfActiveHeaps );                 

		String[] ProductoObjetivo;
		ProductoObjetivo = new String[4];

		argu[0] = "OK";
		
		ProductoObjetivo[0]=argu[1].toString();
		callScript("Scripts.Comun.BuscarProducto", ProductoObjetivo);
		if ((ProductoObjetivo[1].toString().equals("Encontrado"))) {
			ListaEspecial().setText(argu[2].toString());
		
//			ListaEspecial().openPopup();
//			NewQuery().performAction();
//			ListaEspecialBuscada().setText(argu[2].toString());
//			ListaEspecialBuscada().processKey("EnterKey");
//			System.out.println(LineasdeListaEspecial().getProperty("RowsCount"));
//			if (Integer.parseInt(LineasdeListaEspecial().getProperty("RowsCount").toString()) >=1) {
//				Aceptar().performAction();
//			} else { argu[0] = "NOK";}
//			//Cerrar().performAction();
		} else {
			argu[0] = "NOK";
		}
		
        stats = SpyMemory.getStats(); 
        System.out.println("After script "+ stats.numberOfActiveHeaps); 
        com.rational.test.ft.script.RationalTestScript.unregisterAll(); 
	}
}


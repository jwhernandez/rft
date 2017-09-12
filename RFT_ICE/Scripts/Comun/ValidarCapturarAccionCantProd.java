package Scripts.Comun;
import libreria.utileria.Tipo;
import resources.Scripts.Comun.ValidarCapturarAccionCantProdHelper;
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
 * Description   : Functional Test Script
 * Parametros: 0) OK/NOK 1) tramite 2) Atrib_Padre 3)Patron_Atrib_Actual
 * 5) Patron_atrib_Ant 
 * Script Name   : <b>ValidarCapturarAccionCantProd</b>
 * @since  2017/07/03
 * @author SS
 * ej res "Cambio de plan" "Servicio de Telefonia Movil" "Plan Especial.*" "Plan Especial.*" 
 */
public class ValidarCapturarAccionCantProd extends ValidarCapturarAccionCantProdHelper
{
	public void testMain(Object[] argu)  throws RationalTestException 
	{
		
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK" ;
		String[] BuscProd;
		BuscProd = new String[6];
		String[] Expandir;
		Expandir = new String[4];
		// Parámetros: 0) Nombre de producto a expandir 1) OK/NOK
		//  2)argumento opcional para indicar desde posicion actual  3) tramite
		//Tramite = Cambio de plan / Venta / PostVenta / PortIn (No implementado)
		Boolean Error = false;
				
		String tramite = argu[1].toString();
		String padre = argu[2].toString();
		String patron_act = argu[3].toString();
		String patron_ant = argu[4].toString();
		String nrolinea = "NA"; 
		int iPos_act =0;
		int iPos_ant =0;
		String prod_ant=null;
		String prod_act=null;
		String nro_ant = null;
		String nro_act = null; 
		String cant_ant = null;
		String cant_act = null; 
		String acc_ant = null;
		String acc_act = null; 
		String accion_act_esp = null;
		String accion_ant_esp = null;
		Boolean Ant_es_Actual = false; 

		// Posicionarse en forma conveniente en la pantalla
		Menu().ensureObjectIsVisible();
		if (!padre.equals("NA")){
			// Buscar el padre y expandirlo 
			BuscProd[0] = padre;  
			BuscProd[4] = "Si"; // Que busque desde el principio 	
			BuscProd[5] = tramite; 	
			callScript("Scripts.Comun.BuscarProducto", BuscProd);
			if  ((BuscProd[1].toString().equals("Encontrado"))){
				Expandir[0] = padre;
				Expandir[2] = "Si";
				Expandir[3] = tramite;
				callScript("Scripts.Comun.ExpandirLinea", Expandir);
				if (Expandir[1].toString().equals("NOK")) {	
					Error = true;
				} else 
				{
					// Capturar el nro de linea del padre
				}
			} else Error = true;
		}
		
		// Buscar el patron asociado al servicio actual - 1er patron
		BuscProd[1] = null;  // Encontrado/No Encontrado/Solo el excluido
		BuscProd[2] = null;  // posicion
		BuscProd[3] = null;  // action code
		BuscProd[5] = tramite; 	

		BuscProd[0] = patron_act;  
		if (!padre.equals("NA"))  BuscProd[4] = "Si"; // Que busque desde el principio 
		else BuscProd[4] = "No"; 
		callScript("Scripts.Comun.BuscarProducto", BuscProd);
		if  ((BuscProd[1].toString().equals("No Encontrado"))) Error = true;
		else {
			iPos_act = Integer.parseInt (BuscProd[2]);
			// Capturar el producto, la accion y la cantidad e imprimir
			LineasPedido().activateRow(iPos_act);
			acc_act = (String) LineasPedido().getCellText("Action Code Calc", iPos_act);
			prod_act = (String) LineasPedido().getCellText("Product", iPos_act);
			nro_act = (String) LineasPedido().getCellText("Outline Number", iPos_act);
			System.out.println("CP: Patron act " +prod_act +" "+ acc_act+" "+ nro_act + iPos_act);
			// cantidad_actual=
			infoPaso(getScriptName().toString(), Tipo.DATO,"Patrón buscado= " + patron_act 	,  
					" Producto encontrado=" + prod_act + " accion=" + acc_act + " cant=" + cant_act);
		}
		
		if (!Error){
			// Decidir si hay que buscar el segundo producto
			if (tramite.toLowerCase().equals("cambio de plan") || tramite.toLowerCase().equals("posventa" ) && !patron_ant.equals("NA")) 
			{
				// Buscar el servicio anterior si es CP o la potencial modificacion del servicio si es PV
				// Si es un CP patron_ant no debería ser NA. Si es PV patron_ant puede ser NA
				BuscProd[0] = patron_ant;  
				BuscProd[4] = "Nro:" + nro_act; // para que busque desde el comienzo excluyendo el encontrado
				callScript("Scripts.Comun.BuscarProducto", BuscProd);
				if (BuscProd[1].toString().equals("No Encontrado")) Error = true;
				if (BuscProd[1].toString().equals("Solo el excluido")) Ant_es_Actual=true;
				if (BuscProd[1].toString().equals("Encontrado"))
				{
					iPos_ant = Integer.parseInt (BuscProd[2]);
					// Capturar el producto, la accion y la cantidad e imprimir
					LineasPedido().activateRow(iPos_ant);
					acc_ant = (String) LineasPedido().getCellText("Action Code Calc", iPos_ant);
					prod_ant = (String) LineasPedido().getCellText("Product", iPos_ant);
					nro_ant = (String) LineasPedido().getCellText("Outline Number", iPos_ant);
					System.out.println("CP: Patron ant " +prod_ant +" "+ acc_ant+" "+ nro_ant + + iPos_ant);
					// cantidad_ant=
					infoPaso(getScriptName().toString(), Tipo.DATO,"Patrón buscado= " + patron_ant 	,  
							" Producto encontrado=" + prod_ant + " accion=" + acc_ant + " cant=" + cant_ant);
				}
				//-----------------------------------------------------------
				// Hay un solo producto que cumple el patron de ambos patrones
				//-----------------------------------------------------------
				if (Ant_es_Actual && !Error)
				{
					// accion_act_esp="-" || Modificación
					if (acc_act.equals("-") || acc_act.equals("Modificación")) argu[0]="OK";
					System.out.println(tramite+ "-Se encontro 1 solo producto. Con -?" + argu[0].toString());
					infoPaso(getScriptName().toString(), Tipo.DATO, tramite, "-Se encontro 1 solo producto. Con -?" + argu[0].toString() );
				}
				//-----------------------------------------------------
				// Hay dos productos y son iguales
				//-----------------------------------------------------
				if ((prod_act.equals(prod_ant) && !Ant_es_Actual) && !Error)
				{
					accion_act_esp="nuevo";
					accion_ant_esp="desconexión";
					if ((acc_act.toLowerCase().equals(accion_act_esp) &&
					    acc_ant.toLowerCase().equals(accion_ant_esp)) ||
					    (acc_act.toLowerCase().equals(accion_ant_esp) &&
							    acc_ant.toLowerCase().equals(accion_act_esp)))
						argu[0]="OK";
					System.out.println(acc_act.toLowerCase().equals(accion_act_esp) &&
						    acc_ant.toLowerCase().equals(accion_ant_esp));
					System.out.println(acc_act.toLowerCase().equals(accion_ant_esp) &&
								    acc_ant.toLowerCase().equals(accion_act_esp));
					System.out.println(tramite + "-Se encontraron dos productos iguales. Uno con desconexión y otro con Nuevo? " + argu[0].toString());
					infoPaso(getScriptName().toString(), Tipo.DATO, tramite +" ", "-Se encontraron dos productos iguales. Uno con desconexión y otro con Nuevo?" + argu[0].toString() );
				}
				//-----------------------------------------------------------
				// Hay dos productos y son diferentes
				//-----------------------------------------------------------
				if (!prod_act.equals(prod_ant) && !Ant_es_Actual && !Error)
				{
					accion_act_esp="nuevo";
					accion_ant_esp="desconexión";
					if ((acc_act.toLowerCase().equals(accion_act_esp) &&
					    acc_ant.toLowerCase().equals(accion_ant_esp)) ||
					    (acc_act.toLowerCase().equals(accion_ant_esp) &&
							    acc_ant.toLowerCase().equals(accion_act_esp)))
						argu[0]="OK";
					System.out.println(acc_act.toLowerCase().equals(accion_act_esp) &&
						    acc_ant.toLowerCase().equals(accion_ant_esp));
					System.out.println(acc_act.toLowerCase().equals(accion_ant_esp) &&
								    acc_ant.toLowerCase().equals(accion_act_esp));
					System.out.println(tramite + "-Se encontraron dos productos distintos. Uno con desconexión y otro con nuevo?" + argu[0].toString());
					infoPaso(getScriptName().toString(), Tipo.DATO, tramite +" ", "-Se encontraron dos productos distintos. Uno con desconexión y otro con Nuevo?" + argu[0].toString() );
				}
			} 
			if (tramite.toLowerCase().equals("venta") || tramite.equals("port-in" ) ) // port-in aun no implementado
			{
				//-----------------------------------------------------------
				// Es venta
				//-----------------------------------------------------------			
				accion_act_esp="nuevo";  
				if (acc_act.toLowerCase().equals(accion_act_esp)) {
					argu[0]="OK";
					System.out.println(tramite + "- Se encontró un producto. Con accion Nuevo?" + argu[0].toString());
					infoPaso(getScriptName().toString(), Tipo.DATO, tramite +" ", "-Se encontró un producto. Con accion Nuevo?" + argu[0].toString() );
				}
			}
		} 
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}


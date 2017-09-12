package Scripts.Comun;
import resources.Scripts.Comun.BuscaryValidarProductoREDCMHelper;
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
 * Description   : Busca el producto pasado como parametro y chequea la existencia de la opción 
 * de nómina. Opcionalmente selecciona a nomina. Opcionalmente puede validar que no exista
 *  (No Encontrar) el producto.
 * Script Name   : <b>BuscaryValidarProductoREDCM</b>
 * Parametros : 0) OK/NOK 1) Producto o "Listar"  (PRODi) 2) "No encontrar"  o encontrar (PROD_Accioni)
 * si se desea  enviar a RED indicar RED. También acepta NA que es para listar.
 * Ej: res "Deposito de Garantia" RED  
 * Ej: res listar res  
 * Ej res "cambio de aparato" "No Encontrar" 
 * @author SS
 * @since  2016/04/21
 * ult modif ss 13022017 se agrega la opcion de setear a NoRED es decir nomina
 */
public class BuscaryValidarProductoREDCM extends BuscaryValidarProductoREDCMHelper
{
	public void testMain(Object[] argu) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		// Análisis de parámetros
		//Boolean bREDSBL=Boolean.parseBoolean(aRED().getProperty("IsChecked").toString());
		Boolean bRED=false;
		Boolean bNoRED=false;
		Boolean bEncontrar=null;
		
		// Se inicializa el resultado en OK si es la opción listar o en NOK si es buscar un ítem.
		String sProductoObjetivo = argu[1].toString();
		if (argu[1].toString().toLowerCase().equals("listar")) argu[0] = "OK"; else argu[0] = "NOK"; 

		
 		switch (argu[2].toString().toLowerCase()) {
		case "red":
			System.out.println("Se enviara a RED");
			bRED = true;
			bEncontrar = true;
			break;
		case "nored":
			System.out.println("No se enviara a RED");
			bNoRED = true;
			bEncontrar = true;
			break;
		case "encontrar":
			System.out.println("Se dejara el red IGUAL");
			bEncontrar = true;
			break;
		case "no encontrar":
			System.out.println("Se dejara el red IGUAL");
			bEncontrar = false;
			break;
		case "na":   // opcion que es para listar
			System.out.println("Se dejara el red IGUAL");
			bEncontrar = false;
			break;
		default:  
			System.out.println("------------------ERROR EN PARAMETRO ----------------");
			System.out.println("Parámetro enviado como opción no es una opción válida");
			break;
		} // end del switch
	
		String[] RecordCount;
		RecordCount = new String[5];

		System.out.println(AppletRED().exists());
		System.out.println(AppletRED().getProperty("RecordCounter"));
		RecordCount[0] = AppletRED().getProperty("RecordCounter").toString();

		callScript("Scripts.Comun.RecordCount",RecordCount);

		int iDesde = Integer.parseInt(RecordCount[1]);
		int iSubtotal = Integer.parseInt(RecordCount[2]);
		int iStart1 = (int) RecordCount[3].toString().indexOf("+");
		int iTotal = 0;
		if (iStart1 >=1) {
			iTotal = 1000; // si encuentra un "+", no importa el total hay mas registros
		} else {
			iTotal = Integer.parseInt(RecordCount[3]);
		}

		int irows = 1;
		irows=(int) ListaRED().getProperty("RowsCount");

		System.out.println("-------------------------------------------------------------------");
		System.out.println("Producto Objetivo: *" + sProductoObjetivo + "*" );
		System.out.println("Inicio de logica de buscar ítem RED (1)" );
		System.out.println("Desde= " + iDesde + " Subtotal= " + iSubtotal + " Total= " + iTotal );
		System.out.println("Ítem a buscar= " + argu[1]);
		System.out.println("Subtotal < Total" + iSubtotal + (iSubtotal < iTotal) );
		System.out.println("Total de lineas a procesar en el rowset" + irows );
		System.out.println("-------------------------------------------------------------------");


		int i = 0;
		int Aj = 0;
		
		// Ir al primer RowSet
		while (iDesde > 1 ) {
			ListaRED().firstRowSet();
			RecordCount[0] = AppletRED().getProperty("RecordCounter").toString();

			callScript("Scripts.Comun.RecordCount",RecordCount);

			iDesde = Integer.parseInt(RecordCount[1]);
			iSubtotal = Integer.parseInt(RecordCount[2]);
			iTotal = Integer.parseInt(RecordCount[3]);
			System.out.println("-------------------------------------------------------------------");
			System.out.println("Inicio de logica de buscar ítem RED  (2)" );
			System.out.println("Desde= " + iDesde + "Subtotal= " + iSubtotal + "Total= " + iTotal );
			System.out.println("Subtotal < Total" + iSubtotal + (iSubtotal < iTotal) );
			System.out.println("Total de lineas a procesar en el rowset: " + i + " de: " + irows );
			System.out.println("-------------------------------------------------------------------");
		} // end del while


		int iPosicion = i;
		String sProducto = "Nada";
		sProducto = (String) ListaRED().getCellText("Prod Name", iPosicion);

		System.out.println("Total de lineas a procesar en el rowset: " + i + " de: " + irows );
		System.out.println("Producto: *" + sProducto + "*" );

		Boolean Iterar =  true;  
		while (Iterar){
			while (i <= irows - 1 && !(sProducto.equals(sProductoObjetivo))) {
				System.out.println("Total de lineas a procesar en el rowset: " + i + " de: " + irows );
				iPosicion = i;
				ListaRED().activateRow(i);
				sProducto = (String) ListaRED().getCellText("Prod Name", i);
				if (!aRED().isEnabled() && argu[1].toString().toLowerCase().equals("listar")) 
					argu[0]="NOK";
				System.out.println("Ítem: " + ListaRED().getCellText("Prod Name", i) );
				System.out.println("Ítem: " + Precio().getProperty("Text") );
				System.out.println("Opción RED habilitada?: " + aRED().getProperty("IsEnabled") + "-" + argu[0]);
				System.out.println("Opción RED seleccionada: " + aRED().getProperty("IsChecked") );
				i = i + 1;
				Aj = 1;
			}

			if ((sProducto.equals(sProductoObjetivo))) {
				ListaRED().activateRow(i-Aj);
				Iterar = false; // para salir del loop de buscar
				System.out.println("-------------------------------------------------------------------");
				System.out.println("Ítem encontrado");
				if  (bRED) {
					aRED().setOn();
					System.out.println("Se setea RED");
				}
				if  (bNoRED) {
					aRED().setOff();
					System.out.println("Se setea a Nomina");
				}
				System.out.println("-------------------------------------------------------------------");
			} 
			else // si el producto es diferentes (no encontró aun producto igual)
			{
				if (iSubtotal < iTotal) {
					ListaRED().nextRowSet();
					RecordCount[0] = AppletRED().getProperty("RecordCounter").toString();

					callScript("Scripts.Comun.RecordCount",RecordCount);

					iDesde = Integer.parseInt(RecordCount[1]);
					iSubtotal = Integer.parseInt(RecordCount[2]);
					iStart1 = (int) RecordCount[3].toString().indexOf("+");
					iTotal = 0;
					if (iStart1 >=1) {
						iTotal = 1000; // si encuentra un "+", no importa el total hay mas registros
					} else {
						iTotal = Integer.parseInt(RecordCount[3]);
					}					
					irows=(int) ListaRED().getProperty("RowsCount");
					System.out.println("-------------------------------------------------------------------");
					System.out.println("Siguiente rowset" );
					System.out.println("Subtotal" + iSubtotal + "Total " + iTotal );
					System.out.println("Subtotal < Total" + iSubtotal + (iSubtotal < iTotal) );
					System.out.println("Total de lineas a procesar en el rowset" + irows );
					System.out.println("-------------------------------------------------------------------");
				} else {
					Iterar = false; 
				}
			} // subtotal < itotal
			if (Iterar) i = 0; // vuelvo a posicionarme en la primer fila del siguiente row_set
		} // end del while iterar 

		if (sProducto.equals(sProductoObjetivo)) {
			System.out.println("Ítem Seleccionado: " + sProducto + " en posición: " + iPosicion);
			if (bEncontrar) // lo encontró y lo deseaba encontrar
				argu[0] = "OK";
			else // lo encontró y no lo deseaba encontrar
				argu[0] = "NOK";
		} else { 
			if (!argu[1].toString().toLowerCase().equals("listar")) System.out.println("El producto buscado no se encontro");
			if (bEncontrar) // no lo encontró y lo deseaba encontrar
				argu[0] = "NOK";
			else // no lo deseaba encontrar y no lo encontro
				argu[0] = "OK";
		}
		
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}


package Scripts.Comun;
import resources.Scripts.Comun.CheckPagoFactHelper;
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
import com.ibm.xtq.ast.nodes.ValueOf;
/**
 * Descripción: 
 * ------------
 * Chequea el campo factura se encuentre con o sin tilde  según se indique
 * por parametro o permite setar si se habilitará o no el tilde.
 * 
 * 
 * @Param 0) OK/NOK 1) true o false 2) Valida, SeteoSimple o SeteoNinguno 3) Servicio  
 * ej: res false SeteaNinguno "Servicio de Telefonia Movil"
 * ej: res true valida NA
 *
 * Detalle de parámetros
 * --------------------
 * Ninguno implica sacar todos los tildes  
 * Ninguno utiliza el parámetro Servicio (que no es mandatorio) para recibir 
 * Si el producto (Servicio de Telefonia Movil, por ejemplo)
 * La lógica procesa el primer nivel de productos debajo del servicio.
 * El segundo nivel de productos en la jerarquia no es procesado por este script.
 * Colocar NA en servicio si no se usa la opción Ninguno     
 *   
 * @since  2016/05/04 
 * Modif   2016/10/09 (AAAA/MM/DD) - SS agrega la opción de Setear ninguno
 * Modificado el 2/10/16 para agregar el validar
 * Script Name   : <b>CheckPagoFact</b>
 * @author SS
*/
public class CheckPagoFact extends CheckPagoFactHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0]="NOK";
		
		// ---------------------------------------------------------
		// Determina si la opción ingresada como parámetro es simple
		// ---------------------------------------------------------
		String sOpcion = null;
		String sServicio = null;
		
		if (argu.length >= 4 && !argu[3].toString().equals("NA")) 
				sServicio = argu[3].toString();
		
		if (argu.length >= 3 && !argu[2].toString().equals("NA")) 
			sOpcion = argu[2].toString().toLowerCase();

		boolean bHabilitado=Boolean.parseBoolean(argu[1].toString().toLowerCase());
		boolean bEstadoCheck=false;

		System.out.println("La opcion a procesar es " + sOpcion);
		switch (sOpcion) {
		case "valida":
			System.out.println("Se procesa la opción valida");
			
			bEstadoCheck = Boolean.parseBoolean(PagoaFactura().getProperty("IsChecked").toString());
			
			if ((bHabilitado && bEstadoCheck) || (!bHabilitado && !bEstadoCheck ))
			{
				System.out.println("Coincide Valor deseado y real : " + bHabilitado + "-" +bEstadoCheck) ;
				argu[0]="OK";
			}
			else
				System.out.println("No coincide Valor deseado y real : " + bHabilitado + "-" +bEstadoCheck) ;
			break;
		case "seteosimple":
			System.out.println("Se procesa la opción seteosimple");
			if (bHabilitado) PagoaFactura().setOn(); 
			else PagoaFactura().setOff(); 
			System.out.println("El botón a factura quedó en : " + PagoaFactura().getProperty("IsChecked"));
			argu[0]="OK";
			break;
		case "seteoninguno":
			if (!sServicio.isEmpty() ) {
				System.out.println("Se procesa la opción seteoninguno");
				String[] BuscProd;
				BuscProd = new String[4];
				//Parámetros: 0) Nombre del producto 1) Encontrado/No Encontrado 
				// 2)posicion 3)action code 4) desde la linea("Si") por defecto 
				// busca en todas las lineas 5)Tramite 
				
				String[] ExpandirLinea;
				ExpandirLinea = new String[3];
				//0) Nombre del producto a expandir 1) OK/NOK 2)argumento opcional para indicar desde posicion actual 
				// Se utilizará el segundo argumento en "Si" para indicar desde la posición actual
				
				// Se busca el producto servicio
				System.out.println("Se procesa el servicio");
				BuscProd[0] =sServicio ; // Servicio postpago, prepago, hibrido, etc.
				callScript("Scripts.Comun.BuscarProducto", BuscProd);
				if (BuscProd[1].toString().equals("Encontrado")) {
					String sNro=null;
					int iEnd = 0;
					String sNroNodoPadre = null;
					String sNroNodoHijo = null;
					int iPosicion = Integer.parseInt(BuscProd[2].toString());

					// Se contrae la linea del producto servicio (para reducir niveles)
					if (LineasPedido().isRowExpanded(iPosicion)) {
						LineasPedido().clickHier(); 
						argu[1] = "OK";
					}	
					
					// Se expande la linea del producto servicio
					ExpandirLinea[0]=sServicio;
					ExpandirLinea[2]="Si";	
					callScript("Scripts.Comun.ExpandirLinea",ExpandirLinea);
					// Obtener NroNodo
					sNroNodoPadre = NroLinea().getProperty("Text").toString();
					System.out.println(NroLinea().getProperty("Text"));
					// Lee los datos de la primer linea
					iPosicion = iPosicion + 1; 
					LineasPedido().activateRow(iPosicion);	
					sNro = NroLinea().getProperty("Text").toString();
					iEnd = (int) sNro.indexOf(".");
					System.out.println("iEnd "+iEnd);
					if (iEnd !=-1) 
						sNroNodoHijo = sNro.substring(0,iEnd).trim();
					else 
						sNroNodoHijo = sNro;
					
					System.out.println("Linea a evaluar=" + 
						LineasPedido().getCellText("Product",iPosicion) +
						" Nodo = "+ sNroNodoHijo);

					while (sNroNodoPadre.equals(sNroNodoHijo)) { 
						// Procesa la linea para el tema pago factura
						LineasPedido().activateRow(iPosicion);	
						System.out.println("Linea a procesar=" + 
								LineasPedido().getCellText("Product",iPosicion) +
								" Nodo = "+ sNroNodoHijo + " .Se procesa tilde factura"
								+ " - Tilde a factura estaba en:  " 
								+ PagoaFactura().getProperty("IsChecked"));
						if (bHabilitado) PagoaFactura().setOn(); 
						else PagoaFactura().setOff(); 
						System.out.println("El tilde a factura quedó en : " + PagoaFactura().getProperty("IsChecked"));
						
						// Lee los datos de la siguiente linea
						iPosicion = iPosicion + 1; 
						LineasPedido().activateRow(iPosicion);	
						sNro = NroLinea().getProperty("Text").toString();
						iEnd = (int) sNro.indexOf(".");
						//System.out.println("iEnd "+iEnd);
						if (iEnd !=-1) 
							sNroNodoHijo = sNro.substring(0,iEnd).trim();
						else 
							sNroNodoHijo = sNro;
						System.out.println("Linea a evaluar=" + 
								LineasPedido().getCellText("Product",iPosicion) +
								" Nodo = "+ sNroNodoHijo);
					}  
				} // fin de procesamiento del servicio
				argu[0]="OK";
			}
			break;
		default:  
			System.out.println("La opción no es correcta");
			break;
		} // end del switch
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());

	}
}


package Scripts.Comun;
import resources.Scripts.Comun.PagoREDOnlyCMHelper;

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
 * Description   : Valida que el check de enviar un cm a red se encuentre habilitado-deshabilitado y marcado-desmarcado
 * Script Name   : <b>PagoREDOnlyCM</b>
 * Parametros : 0) OK/NOK 1) Producto o listar (PRODi) 2) marcado (true) o desmarcado (false) (PROD_Marcadoi) 3) habilitado (true) o deshabilitado (false) (PROD_Habilitado)
 * Ej: res "Recarga Super Chip 1000" true false   
 * @author VC
 * @since  2017/03/22
 */
public class PagoREDOnlyCM extends PagoREDOnlyCMHelper
{
	public void testMain(Object[] argu) 
	{
ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		//System.out.println("check");
		//System.out.println(aRED2().getProperties());
		
		// Análisis de parámetros
		//Boolean bREDSBL=Boolean.parseBoolean(aRED().getProperty("IsChecked").toString());
		Boolean bREDMarcado=false;
		Boolean bREDHabilitado=false;
		boolean error = false;
		boolean bMarcadoValidado=false;
		boolean bHabilitadoValidado=false;
		
		// Se inicializa el resultado en NOK.
		String sProductoObjetivo = argu[1].toString().toLowerCase();
		String sEstado = argu[2].toString().toLowerCase();
		String sHabilitado = argu[3].toString().toLowerCase();
		argu[0] = "NOK";
		//if (argu[1].toString().toLowerCase().equals("listar")) argu[0] = "OK"; else argu[0] = "NOK"; 
		
		if(sEstado.equals("true")){
			bREDMarcado = true;
		}else if(sEstado.equals("false")){
			bREDMarcado = false;
		}else{
			error = true;
			System.out.println("Error, parámetro incorrecto para la variable PROD_Marcado");
		}
		
		if(sHabilitado.equals("true")){
			bREDHabilitado = true;
		}else if(sHabilitado.equals("false")){
			bREDHabilitado = false;
		}else{
			error = true;
			System.out.println("Error, parámetro incorrecto para la variable PROD_Habilitado");
		}
	
		if(!error){
			String[] RecordCount;
			RecordCount = new String[5];

			RecordCount[0] = AppletRED2().getProperty("RecordCounter").toString();

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
			irows=(int) ListaRED2().getProperty("RowsCount");

			int i = 0;
			int Aj = 0;
			
			// Ir al primer RowSet
			while (iDesde > 1 ) {
				ListaRED2().firstRowSet();
				RecordCount[0] = AppletRED2().getProperty("RecordCounter").toString();

				callScript("Scripts.Comun.RecordCount",RecordCount);

				iDesde = Integer.parseInt(RecordCount[1]);
				iSubtotal = Integer.parseInt(RecordCount[2]);
				iTotal = Integer.parseInt(RecordCount[3]);
			} // end del while


			int iPosicion = i;
			String sProducto = "Nada";
			sProducto = (String) ListaRED2().getCellText("Prod Name", iPosicion);

			Boolean Iterar =  true;  
			while (Iterar){
				while (i <= irows - 1 && !(sProducto.equals(sProductoObjetivo))) {
					iPosicion = i;
					ListaRED2().activateRow(i);
					sProducto = (String) ListaRED2().getCellText("Prod Name", i);
					//if (!aRED().isEnabled() && argu[1].toString().toLowerCase().equals("listar")) 
					//	argu[0]="NOK";
					System.out.print("Ítem: " + ListaRED2().getCellText("Prod Name", i) );
					System.out.print(" | Opción RED habilitada: " + aRED2().getProperty("IsEnabled"));
					System.out.println(" | Opción RED seleccionada: " + aRED2().getProperty("IsChecked") );
					i = i + 1;
					Aj = 1;
				}

				if ((sProducto.equals(sProductoObjetivo)) || sProductoObjetivo.equals("listar")) {
					ListaRED2().activateRow(i-Aj);
					Iterar = false; // para salir del loop de buscar*/
					//System.out.println("Ítem encontrado");
					if  (bREDMarcado == Boolean.parseBoolean(aRED2().getProperty("IsChecked").toString())){
						bMarcadoValidado = true;
					}else{
						error = true;
						bMarcadoValidado = false; //Aplica para el caso de listar, se coloca de esta forma porque en esta linea la validación no coincide
					}
					if  (bREDHabilitado == aRED2().isEnabled()){
						bHabilitadoValidado = true;
					}else{
						error = true;
						bHabilitadoValidado = false; //Aplica para el caso de listar, se coloca de esta forma porque en esta linea la validación no coincide
					}
					if(sProductoObjetivo.equals("listar") && !error && iSubtotal < iTotal) Iterar = true; //si es listar no debe de terminar el ciclo a menos de que alguno de los elementos no cumpla lo deseado
				} 
				else // si el producto es diferentes (no encontró aun producto igual)
				{
					if (iSubtotal < iTotal) {
						ListaRED2().nextRowSet();
						RecordCount[0] = AppletRED2().getProperty("RecordCounter").toString();

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
						irows=(int) ListaRED2().getProperty("RowsCount");
					} else {
						Iterar = false; 
					}
				} // subtotal < itotal
				if (Iterar) i = 0; // vuelvo a posicionarme en la primer fila del siguiente row_set
			} // end del while iterar
			
			if (!error && (sProducto.equals(sProductoObjetivo) || sProductoObjetivo.equals("listar"))) { //sProducto.equals(sProductoObjetivo) && 
				if (bMarcadoValidado && bHabilitadoValidado) // lo encontró y lo deseaba encontrar
					argu[0] = "OK";
				else{ 
					if(!error){
						System.out.println("No se encontró el producto deseado");
					}else{
						System.out.println("Uno de los campos no coincide con lo deseado - Marcado: " + bREDMarcado +  "|" + aRED2().getProperty("IsChecked").toString() + " - Habilitado: " + bHabilitadoValidado + "|" + aRED2().isEnabled());
					}		
					argu[0] = "NOK";
				}
			} else { 
				System.out.println("No se encontró el producto: " + sProductoObjetivo + " o los parámetros son incorrectos");
				System.out.println("Marcado: deseado:" + bREDMarcado +  "| obtenido:" + aRED2().getProperty("IsChecked").toString());
				System.out.println("Habilitado: deseado:" + bREDHabilitado + "| obtenido:" + aRED2().isEnabled());
				argu[0] = "NOK";
			}
			
		}else{
			System.out.println("Error en los parámetros");
		}
		
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
		
	}
}


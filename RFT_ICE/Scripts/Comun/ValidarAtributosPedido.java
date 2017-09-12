package Scripts.Comun;
import libreria.utileria.Tipo;
import resources.Scripts.Comun.ValidarAtributosPedidoHelper;
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
 * Description   : Valida una cantidad (parametrizada) de atributos (set nombre - valor)
 * @author Sandra
 * Parametros 0) OK/NOK 1) tramite 2) cantidad de atributos 3) Nombre 4) Valor
 * 5)Nombre 6)Valor 7)Nombre 8)Valor
 * Script Name   : <b>ValidarAtributosPedido</b>
 *  @since  12/11/2016
 *  ultima modificacion 18/11/2016
 */
public class ValidarAtributosPedido extends ValidarAtributosPedidoHelper
{
	public void testMain(Object[] argu) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0]="OK";
		
		String sNombre=null;
		String sValor=null;
		String sNombreDeseado=null;
		String sValorDeseado=null;
		boolean Correcto=true;
		
		String sScriptName=getScriptName().toString(); // 22/11/2016
		
		String sTramite = "Venta";
		if (argu.length >= 2 ) { 
			sTramite = argu[1].toString();
		}
		
		int index = 3;
		
		if (!sTramite.equals("PortIn"))
		{
			BtonConsultaPedido().ensureObjectIsVisible(); // 18/11/2016
			TabsPedidoVta().goTo("Order Entry - Line Items XA View (Sales)", "L4");

			int iRows = 0;
			if (!sTramite.equals("PortIn")) iRows = Integer.parseInt(ListaAtributos().getProperty("RowsCount").toString());
			System.out.println("Files = " + iRows);
			
			int iRowsDeseada = Integer.parseInt(argu[2].toString());
			if (iRows == iRowsDeseada) 
			{
				for (int j = 0; j < iRows; j++) {
					ListaAtributos().activateRow(j);
					// lineas cambiadas
					sNombre = ListaAtributos().getCellText("Display Name",j); // 18/11/2016
					sValor = ListaAtributos().getCellText("Value",j);
					System.out.println("Nombre y Valor " + sNombre + "-" + sValor);
					sNombreDeseado = argu[index].toString();
					index++;
					sValorDeseado = argu[index].toString();
					index++;
					System.out.println("Nombre y Valor deseado " + sNombreDeseado + "-" + sValorDeseado);
					infoPaso(sScriptName, Tipo.DATO,"Atributo " + j + ": Nombre y Valor", sNombre + "-" + sValor);
					if(!sNombre.equals(sNombreDeseado) && !sValor.equals(sValorDeseado)){
						Correcto = false;
						//No se termina el ciclo, para que se muestre al final el resultado de las demás comparaciones si aún quedan
					}
				}
			} else 	Correcto = false;	
			
		} else System.out.println("Paso no implementado para PortIn");
		
		if(!Correcto)argu[0]="NOK";
		
		Atributos().ensureObjectIsVisible();
		TabsPedidoVta().goTo("Order Entry - Line Items Detail View (Sales)","L4");
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}	
}


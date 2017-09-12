package Scripts.Comun.SistemasExternos;
import java.util.Iterator;

import libreria.utileria.Tipo;
import resources.Scripts.Comun.SistemasExternos.BRM_FavoritoValidarCambioNumHelper;

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
 * Description   : Valida Cambio de numero en BRM  
 * Script Name   : <b>BRM_FavoritoValidarCambioNum</b>
 * @since  016/11/11
 * @author SS
 * Parametros: 0) Resultado = OK/NOK 1) Producto 2)Numero 3) servicio
 * ej res "Numero Kolbi SMS Favorito" 88888888 
 * Ultima modificacion: VC 30/11/2016
 * Se cambia el leer el billing type y compararlo con el deseado por seleccionar el billing type deseado
 */
public class BRM_FavoritoValidarCambioNum extends BRM_FavoritoValidarCambioNumHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		ITestDataList iDataList = null;
		ITestDataTable iDataTable = null;
		String sName = null;
		String sValue = null;
		argu[0]="NOK";	
		//argu[1]="Numero Kolbi SMS Favorito";
		String sSerDeseado = argu[3].toString();
		String sScriptName=getScriptName().toString(); // 22/11/2016
		
		Pestañas().click(atText("Promotion"));
		
		ServiceFav().waitForExistence();
		System.out.println("Numero Favorito " +ServiceFav().getProperties());

		//Se modifica la forma en la que se selecciona el service id, ya que antes solo tomaba el primer valor, con la modificación actual busca el deseado (30/11/2016)
		//int Index = Integer.parseInt(ServiceFav().getProperty("selectedIndex").toString());
		int items = (int) ServiceFav().getProperty("itemCount");
		System.out.println("Cantidad de elementos " + items);
		if(items <= 0) {
			sleep(10); //En ocaciones busca el objeto cuando aún no está visible, se hace este sleep para dar tiempo a que aparezca (30/11/2016)
			items = (int) ServiceFav().getProperty("itemCount");
			System.out.println("Indice seleccionado " + items);
		}
		ITestDataList elementos = (ITestDataList) ServiceFav().getTestData("list");
		ITestDataElement elemento = elementos.getElements().getElement(0);
		for(int i=0; i < items; i++){
			elemento = elementos.getElements().getElement(i);
			if(elemento.getElement().toString().contains(sSerDeseado)){
				ServiceFav().select(i);
				break;
			}
		}
		//ServiceFav().click();
		/*System.out.println("Indice seleccionado " + Index);
		if(Index <0) {
			sleep(10);
			Index = Integer.parseInt(ServiceFav().getProperty("selectedIndex").toString()); 
			System.out.println("Indice seleccionado " + Index);
		}
		ITestDataList Services = (ITestDataList) ServiceFav().getTestData("list");
		ITestDataElement Service = Services.getElements().getElement(Index);*/
		String sBillingType= elemento.getElement().toString();
		System.out.println("sBillingType " + sBillingType);
		infoPaso(sScriptName, Tipo.DATO,"Billing Type",sBillingType);
		if (sBillingType.contains(sSerDeseado)) 
		{
			System.out.println("Numeros " + ProdFav().getProperty("itemCount").toString());	
			if (ProdFav().exists()) 
			{
				if (Integer.parseInt(ProdFav().getProperty("itemCount").toString()) ==1) 
				{
					System.out.println("Tipos " + ProdFav().getTestDataTypes());
					iDataList = (ITestDataList) ProdFav().getTestData("list");
					sName = iDataList.getElements().getElement(0).getElement().toString().trim();
					System.out.println("Producto Favorito: " + sName);
					infoPaso(sScriptName, Tipo.DATO,"Producto Fav ",sName);
					if (sName.equals(argu[1].toString()))
					{
						System.out.println("Numero Favorito " +NumFav().getProperties());
						if (Integer.parseInt(NumFav().getProperty("componentCount").toString()) ==1)
						{
							iDataTable = (ITestDataTable) NumFav().getTestData("contents");
							sName = iDataTable.getCell(0, 1).toString();
							System.out.println("Numero Favorito en Name " + sName);
							sValue = iDataTable.getCell(0, 1).toString();
							System.out.println("Numero Favorito en Value " + sName);		
							infoPaso(sScriptName, Tipo.DATO,"Num Fav",sName);
							if (sValue.equals(argu[2].toString()) && sName.equals(argu[2].toString()))
							{
								argu[0]="OK";	
							} else System.out.println("El numero no coincide");

						} else System.out.println("No hay numero favorito");
					} else System.out.println("El nombre del producto no coincide");
				} else  System.out.println("Mas de un producto favorito");
			} else  System.out.println("No hay producto favorito");
		} else System.out.println("No coincido el servicio");
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}


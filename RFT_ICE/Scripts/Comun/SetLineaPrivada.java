package Scripts.Comun;
import resources.Scripts.Comun.SetLineaPrivadaHelper;
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
 * Description   : Administra el campo privado en el pedido. Busca primero el servicio.
 * Si la accion es setear, setea a true o false.
 * Si es consulta, valida que sea true o false
 * @author SS
 * Param 0) NOK/OK 1) Setear True o Consultar False o cualquier combinacion 2) Tramite
 * 3) Servicio
 * Script Name   : <b>SetLineaPrivada</b>
 * @since  2017/03/27
 * ej res "Setear True" PortIn "Servicio de Telefonia Movil"
 */
public class SetLineaPrivada extends SetLineaPrivadaHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		argu[0]="NOK";
		Boolean bPrivado = null;
		String sTramite = argu[2].toString();
		String sServicio = argu[3].toString();
		String[] Producto;
		Producto = new String[6];
		// 0) Nombre del producto 1) Encontrado/No Encontrado 2)posicion 
		// y 3)OUT action code 4) desde la linea o no (Si No) 5)Tramite

		// Buscar Servicio
		Producto[0]=sServicio;  
		Producto[4]="Si"; // se le indica buscar desde el comienzo
		Producto[5]=sTramite;	
		callScript("Scripts.Comun.BuscarProducto",Producto);

		if  ((!Producto[1].toString().equals("No Encontrado")))
		{
			if (sTramite.equals("PortIn")){
				bPrivado = Boolean.valueOf(Privado().getProperty("IsChecked").toString());
			} else System.out.println("Solo portIn implementado");

			String sOpcion = argu[1].toString().toLowerCase();
			switch (sOpcion) {
			case "setear true":
				if (sTramite.equals("PortIn")){
					if (!bPrivado)
					{
						Privado().setOn();	
						if (Boolean.valueOf(Privado().getProperty("IsChecked").toString()))
						argu[0]="OK";
					} else argu[0]="OK";
				}
				break;
			case "setear false":
				if (sTramite.equals("PortIn")){
					if (bPrivado)
					{
						Privado().setOff();
						if (!Boolean.valueOf(Privado().getProperty("IsChecked").toString()))
						argu[0]="OK";
					} else argu[0]="OK";
				}
				break;
			case "consultar true":
				if (bPrivado) argu[0]="OK";
				break;
			case "consultar false":
				if (!bPrivado) argu[0]="OK";
				break;
			default:
				System.out.println("Opcion no implementada");
				break;
			}
		} else System.out.println("Servicio no encontrado");
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}


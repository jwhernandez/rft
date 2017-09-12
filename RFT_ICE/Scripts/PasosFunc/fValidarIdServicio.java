package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarIdServicioHelper;
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
 * Description   : Validar en el id de servicio en el pedido
 * Con la opción Valor el contenido y con las opciones true o false si está habilitado o inhabilitado
 * 
 * @author SS
 * Script Name   : <b>fValidarIdServicio</b>
 * @since  2017/03/23
 * Param 1 = Valor o True o False
 */
public class fValidarIdServicio extends fValidarIdServicioHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] IdServicio;
		IdServicio = new String[4];
		// Parámetros: 0)OK/NOK 1)Accion 2) ValorId 3)Tramite (PortIn u otro)
		
		String[] BuscProd;
		BuscProd = new String[6];
		//Parámetros: 0) Nombre del producto 1) Encontrado/No Encontrado 2)posicion y 
		// 3)action code 4) desde la linea o no (Si No) 5)Tramite 

		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		
		// Buscar el servicio
		BuscProd[0] = dpString("Servicio"); // Servicio de telefonia Movil o prepago o ..
		BuscProd[4] = "Si"; // Que busque desde el principio 	
		BuscProd[5] = dpString("Tramite"); 				
		callScript("Scripts.Comun.BuscarProducto", BuscProd);

		if  ((BuscProd[1].toString().equals("No Encontrado"))){
			//MensError[0] = "Producto no se encontró";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}		else 
		{	// Si se encontro el servicio						String sAccion = args[1].toString().toLowerCase(); // accion: valor, true, false			if (sAccion.equals("valor"))			{				IdServicio[2] = dpString("ValorId"); // Opcion no implementada aun			}			else			{				IdServicio[2] = "NA"; // 			}			IdServicio[1] = sAccion;			IdServicio[3] = dpString("Tramite"); 
			callScript("Scripts.Comun.ValidarIdServicio",IdServicio);						if  ((IdServicio[0].toString().equals("NOK"))){				//MensError[0] = "Validacion de id de servicio falló";				MensError[0] = "xDefecto";				MensError[1] = args[3].toString();				MensError[2] = args[0].toString();				MensError[3] = getScriptName( );				callScript("Scripts.Comun.TerminarCasoError", MensError);
			}
		}	}
}


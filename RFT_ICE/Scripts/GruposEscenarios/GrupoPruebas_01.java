package Scripts.GruposEscenarios;
import resources.Scripts.GruposEscenarios.GrupoPruebas_01Helper;
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
 * Descripcion : Ejecuta en el ambiente indicado desde paso Desde incluido, 
 * Si no se especifica paso desde utiliza los parametros de data pool. 
 * Para los pasos posteriores al paso desde se rige por los parametros del dp
 * Argumentos: 0)Usuario 1)Clave 2) Ambiente DESA/QA/PREQA 
 * 3) true cualquier otro valor no se ejecuta
 * 4) idem logout  

 * SS Nov 2015
 */
public class GrupoPruebas_01 extends GrupoPruebas_01Helper
{

	public void testMain(Object[] args) 
	{
		/**
		 * Ejecuta el escenario 03
		 */	
		String[] Ventas;
		Ventas = new String[6];
		
		Ventas[0] = args[0].toString(); // usuario
		Ventas[1] = args[1].toString(); // clave
		Ventas[2] = args[2].toString();	// ambiente

		if ((args.length >= 4) && (args[3].toString().equals("true"))) { 
			callScript("Scripts.Comun.Login",args);}

		Ventas[4] = "false";	// login	
		Ventas[5] = "false";	// logout
		
		Ventas[3] = "CP03";		
		callScript("Scripts.Escenarios.Ventas", Ventas);
		
		Ventas[3] = "CP56";		
		callScript("Scripts.Escenarios.Ventas", Ventas);
		
		if (args.length >= 5 && args[4].toString().equals("true")) { 
			callScript("Scripts.Comun.Logout",args);}
	}
}


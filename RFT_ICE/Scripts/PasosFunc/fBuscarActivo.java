package Scripts.PasosFunc;
import libreria.utileria.Tipo;
import resources.Scripts.PasosFunc.fBuscarActivoHelper;

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
 * Script Name   : <b>fBuscarActivo</b>
 * Description   : Busca un activo usando el nro de servicio del datapool o la variable global.
 * Si busca en el DP o no depende del parametro 1. 
 * Param 0) Nro caso 1) NA para que lo tome de la variable global o <> de NA para que lo tome del DP 2) ambiente 3) error para o no
 * Ej CPCPCurso <>NA (para que lo tome del data pool) DESA false
 * @since  2015/12/28
 * @author Sandra
 * Modificacion 27/01/2017 FF
 * Modificación 07/02/2017 VC
 * Modificacion 15/02/2017 SS se cambia DP por NA y viceversa de manera que usando DP vaya al DataPool y con NA use la variable global
 */
public class fBuscarActivo extends fBuscarActivoHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];
		
		String sScriptName=getScriptName().toString(); // 22/11/2016
		
		String[] Activo;
		Activo = new String[2];
		// Parametros: 0) Nro de servicio (E) , 1)  OK / NOK (S)
		
		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		
		if (args[1].toString().equals("NA")) { // 15/02/2017 SS cambia DP x NA
			Activo[0]= getNroServicio();
		} 
		else if (args[1].toString().equals("DP")) { // 15/02/2017 SS cambia NA x DP
			Activo[0]= dpString("NumeroServicio");
		}  
		else {
			int i = Integer.parseInt(args[1].toString());
			Activo[0]= dpString("NumeroServicio" + i);
			setNroServicio(Activo[0].toString());
			infoPaso(sScriptName, Tipo.DATO,"NroServicio",getNroServicio());		
		}
		
		//Se modifica para que imprima el número que se va a tomar para la prueba, según lo que se envió por parámetro 20170207
		setNroServicio(Activo[0].toString());
		System.out.println("NroServicio: " + getNroServicio() );
		logInfo("NroServicio: " + getNroServicio() );
		infoPaso(sScriptName, Tipo.DATO,"NroServicio", getNroServicio());
		/*System.out.println("NroServicio: " + getNroServicio() );
		logInfo("NroServicio: " + getNroServicio() );
		infoPaso(sScriptName, Tipo.DATO,"NroServicio", getNroServicio());*/
		
		callScript("Scripts.Comun.BuscarActivo", Activo);
		if (Activo[1].toString().equals("NOK")) {
			//MensError[0] = "Activo no encontrado";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}


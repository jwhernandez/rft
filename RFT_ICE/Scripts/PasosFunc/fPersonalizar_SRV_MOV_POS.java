package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fPersonalizar_SRV_MOV_POSHelper;
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
 * Script Name   : <b>fPersonalizar_SRV_MOV_POS</b>
 * Descripcion   : Selecciona la facilidad indicada en el argumento para postpago
 * @Param  0) nombre del caso 1) digito de iteracion en el dp 2) IN Ambiente 3) IN ErrorStop (Si / No)
 * @since  2016/10/25
 * @author SS
 * AE_CD1_T1 1 QA false Paso1
 */
public class fPersonalizar_SRV_MOV_POS extends fPersonalizar_SRV_MOV_POSHelper
{
	public void testMain(Object[] args) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Personalizacion;
		Personalizacion = new String[7];
		//@Param 0) Alta / Baja 1) Facilidades Telefonia Movil 2) Producto 
		// 3) aun no utilizado 4) Encontrado  / No Encontrado 5)Ambiente 6)Tramite           

		String[] MensError;
		MensError = new String[4];
		
		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 	
		/** 
		 * Personaliza el servicio
		 */
		int i = Integer.parseInt(args[1].toString());
		Personalizacion[0] = dpString("PROD_Accion" + i);
		Personalizacion[1] = dpString("PROD_Categoria" +i); // Facilidades Telefonia Movil
		Personalizacion[2] = dpString("PROD"+i); // No Caller Id
		Personalizacion[3] = "No importa";
		Personalizacion[5] = args[2].toString();
		Personalizacion[6] = dpString("Tramite");
		callScript("Scripts.Comun.Personalizar_SRV_MOV_POS", Personalizacion);
		System.out.println(Personalizacion[4]);

		if  (Personalizacion[4].toString().equals("No Encontrado")){
			MensError[0] = "Agregar Facilidad falló";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}


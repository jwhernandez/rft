package Scripts.Comun;
import resources.Scripts.Comun.AgregarRepLegalHelper;

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
 * Description   : Agrega rep legal en caso de cuenta juridica
 * Script Name   : <b>AgregarRepLegal</b>
 * @param  0)Identificación 1)OK/NOK
 * ej:604510444 res
 * @since  2016/04/6
 * @author Sandra
 * Última mod VC Se agrega bloque try catch para determinar si el script falla y se añade la ejecución de un Alt+Enter para hacer commit en base de datos
 */
public class AgregarRepLegal extends AgregarRepLegalHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		String[] Validaciones;
		Validaciones = new String[3];
		
		
		argu[1]="NOK";
		try{
			HIQuery().performAction("ExecuteQuery");
			Siebel().processKeyboardAccelerator("Ctrl+S"); // VC 20170713toolBar_hiQuery().
			RepLegal().openPopup();
			sleep(1);
			NewQuery().performAction();
			sleep(1);
			Identificacion().setText(argu[0].toString());
			Identificacion().processKey("EnterKey");
			sleep(1);
			PickRecord().performAction();
			
			//Siebel().processKeyboardAccelerator("Alt+Enter"); // VC 20170713
			Siebel().processKeyboardAccelerator("Ctrl+S"); // VC 20170713toolBar_hiQuery().
			sleep(1);
			HIQuery().performAction("ExecuteQuery");
			
			//Validaciones[0]="DPM0053"; // este mensaje puede tener la siguiente secuencia una o dos veces (SBL-EXL-00151)
			//callScript("Scripts.Comun.ValidarMensaje", Validaciones);
			
			argu[1]="OK";
		}catch(Exception e){
			System.out.println("Error al agregar representante legal.");
		}
		
		ImpreResultadoScript(getScriptName( ).toString(), argu[1].toString());
	}
}


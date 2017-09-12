package Scripts.PasosFunc;
import libreria.utileria.Tipo;
import resources.Scripts.PasosFunc.fImprimirMensajeHelper;
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
 * Script Name   : <b>fImprimirMensaje</b>
 * Description   : Imprime un mensaje en el reporte de salida
 * @Param   0) nombre del caso 1) código del msj 2) IN Ambiente  * 3) IN ErrorStop true /false
 * @author SS
 * @since  2017/04/19
 * ej CP26_CD1_T1 "Mensaje1" QA NA NA 
 */
public class fImprimirMensaje extends fImprimirMensajeHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		infoPaso(getScriptName(), Tipo.PASO,"Mensaje",args[1].toString());
	}
}


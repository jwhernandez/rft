package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.REMO_AdministrarMorosidadHelper;
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
import com.ibm.xtq.ast.nodes.CaseClause;
/**
 * Description   : Permite cambiar la morosidad a false o consultar la morosidad. 
 * La morosidad a true no se ha implementado aun
 * @since  2017/03/10
 * @author SS
 * Script Name   : <b>REMO_AdministrarMorosidad</b>
 * params 0) OK/NOK 1) Accion Consultar / Setear 2) Estado (True / False ) 
 * 3) nro identificacion 4) Id cliente al cual reasignar la deuda 5) Tipo cliente
 * al cual reasignar la deuda  
 * Opciones: Consultar TRUE o Consultar FALSE o Setear FALSE
 * No se implementa Setear True (aun no se necesita)
 * res consultar false 121660600
 * res setear false 121660600 603310575 Fisica
 */
public class REMO_AdministrarMorosidad extends REMO_AdministrarMorosidadHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		
		String sOpcion = argu[1].toString().toLowerCase() + argu[2].toString().toLowerCase();
		boolean bMorosidad = false;
		System.out.println(sOpcion);
		switch (sOpcion) {
		case "consultarfalse":
			argu[0] = Validar(bMorosidad, argu[3].toString());
			break;
		case "consultartrue":
			bMorosidad = true;
			argu[0] = Validar(bMorosidad, argu[3].toString());
			break;
		case "setearfalse":
			argu[0] = "OK";
			// Se busca la primera deuda existente para reasignar
			bMorosidad = true;
			String sIdServicio = null;
			String sResul = null;
			while (sIdServicio != "NOK"&& sResul!= "NOK") // Siempre que haya deuda para reasignar 
				// y que el resultado de la reasignacion sea correcta
			{
				sIdServicio = Validar(bMorosidad, argu[3].toString());
				if (sResul==null && sIdServicio.equals("NOK"))
				{
					argu[0] = "NOK"; 
					System.out.println("No se encontró deuda, igual se asume paso válido"); 
					
				}
				if (sIdServicio != "NOK" )
				{
					System.out.println("Id Servicio a reasignar " + sIdServicio); 
					sResul = Setear(bMorosidad, argu[3].toString(),
							argu[4].toString(), argu[5].toString(), sIdServicio);		
					if (sResul.equals("NOK")) 
					{
						argu[0] = "NOK"; 
						System.out.println("No se puedo reasignar " + sIdServicio); 
					}
				}
			}
			break;
		default:
			System.out.println("Opción no implementadda");
			argu[0] = "NOK";
			break;
		}
		
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}

	private String Setear(boolean bMorosidad ,String Id, String IdDest, 
			String TipoDest, String IdServ) {
		String res = "NOK";
		boolean bError=false;
		Mantenimientos().click();
		sleep(1);
		try {
			Reasignacion().click();	
			sleep(1);		
		} catch (Exception e) {
			Mantenimientos().click();
			sleep(1);
			Reasignacion().click();
			sleep(1);
		}
		
		Identificador().setText(IdServ);
		sleep(1);
		// TipoServicio().setProperty(".value", "45 - Celular"); No funciona pero parece no hacer falta
		Buscar().click();
		// Buscar la deuda del cliente con el que se está trabajando
		ITestDataTable tabla; 
		tabla = (ITestDataTable) TablaDeudas().getTestData("contents");
		int filaaReasig = -1; // Se inicializa en valor negativo.
		for (int fila =1; fila < tabla.getRowCount(); fila++) {
			System.out.println( tabla.getCell(fila, 1).toString() + "*"+ IdServ + "*");
			System.out.println( tabla.getCell(fila, 5).toString() + "*"+  Id +"*");
			//System.out.println( tabla.getCell(fila, 1) != null);
			//System.out.println(tabla.getCell(fila, 1).toString().equals(IdServ));
			//System.out.println( tabla.getCell(fila, 5).toString().equals(Id));
				if (tabla.getCell(fila, 1) != null && 
						tabla.getCell(fila, 1).toString().equals(IdServ)
						&& tabla.getCell(fila, 5).toString().equals(Id)) {
					//obtener fila;
					if ((fila-2)>=0) filaaReasig = fila-2;
					fila=1000;
			}
		}
		System.out.println( filaaReasig);
		if (filaaReasig>=0)
		{
			TestObject[] to;
			GuiTestObject gto; 
			String sFila = String.valueOf(filaaReasig); 
			to = TablaDeudas().find(atChild(".classIndex",sFila, ".text", IdServ));
			gto = 	(GuiTestObject)	to[0];
			
			gto.click(); 
			sleep(5);

			// Reasignar la deuda
			ClienteCedula().setText(IdDest);
			ClienteTipoCedula().click();
			switch (TipoDest) {
			case "Fisica":
				IEREMO(REMO(),DEFAULT_FLAGS).inputKeys("{ExtPgUp}{ENTER}");
				break;
			case "Juridica":
				IEREMO(REMO(),DEFAULT_FLAGS).inputKeys("{ExtPgUp}");
				IEREMO(REMO(),DEFAULT_FLAGS).inputKeys("{ExtDown}{ENTER}");
				break;
			case "Pasaporte":
				IEREMO(REMO(),DEFAULT_FLAGS).inputKeys("{ExtPgUp}");
				IEREMO(REMO(),DEFAULT_FLAGS).inputKeys("{ExtDown}{ExtDown}{ENTER}");
				break;
			default:
				bError=true;
				break;
			}
			if (!bError)
			{
				ConsultarCliente().click();
				sleep(1);
				Observacion().click();
				IEREMO(REMO(),DEFAULT_FLAGS).inputChars("Observacion ingresada");
				sleep(1);
				System.out.println("Se reasigna la deuda " + IdServ );
				Reasignar().click();
				sleep(2);
				res = "OK";
				return res;
			} else return "NOK";
		} else return "NOK";
	}

	private String Validar(boolean bMorosidad ,String Id) 
	{
		int[] Resultado;
		Resultado = new int[2];

		Consultas().click();
		sleep(1);
		try {
			DeudasCliente().click();
		} catch (Exception e) {
			Consultas().click();
			sleep(1);
			DeudasCliente().click();
		}

		Cedula().waitForExistence();
		Cedula().setText(Id);
		sleep(2);
		Buscar().click();
		Resultado = null;
		Resultado = obtenerFilaColumnaTabla(TablaDeudas(), "Pendiente");
		if (Resultado != null)
		{
			System.out.println("Fila="+Resultado [0]);
			System.out.println("Col="+Resultado [1]);
			if (Resultado[1] == 4  && bMorosidad)  
			{
				// En el caso de moroso se retorna el nro de servicio de morosidad
				String sNroServMoroso = 
						obtenerValorEnFilaColumnaTabla(TablaDeudas(), 
								Resultado[0], 1);
				return sNroServMoroso; 
			}
			else return "NOK"; 
		} 
		else 
		{
			System.out.println("No se encontraron resultados");
			if (bMorosidad) return "NOK";
			else return "OK";
		} 
	}
}



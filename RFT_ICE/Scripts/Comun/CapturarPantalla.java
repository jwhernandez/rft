package Scripts.Comun;
import resources.Scripts.Comun.CapturarPantallaHelper;
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
import java.awt.image.BufferedImage;
import java.awt.Robot;
import java.io.FileOutputStream;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.io.File;
import com.rational.test.ft.object.interfaces.IWindow;
import com.ibm.gsk.ikeyman.io.Logger;
/**
 * Description   : Función para capturar una imagen
 * Script Name   : <b>CapturarPantalla</b>
 * @author SS
 * @since  2016/08/26
 * @param filename path and filename of file to write image.        
 * @param r  Rectangle area that will take screenshot
 *  Ejemplo de como llamar a este script
  	String sImg = "c:\\Image.jpg";
   	Rectangle rImage = this.getRect();
   	if(rImage!=null){
	CapturarPantalla(sImg, rImage );
	}
 */
public class CapturarPantalla extends CapturarPantallaHelper
{
	public void testMain(Object[] argu) 
	{
		String filename = argu[2].toString();
		filename = "C:\\pipi2.jpg";
		browser_htmlBrowser().getClippedScreenRectangle();
		
		Rectangle area = new Rectangle();
		
		area = browser_htmlBrowser().getClippedScreenRectangle();
		CapturarPantalla(filename, area);
		
//		try{
//			BufferedImage capture = null;
//			Robot robot = new Robot();
//			capture = robot.createScreenCapture(area);
//			FileOutputStream out = new FileOutputStream(filename);
//			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//			encoder.encode(capture);
//			out.flush();
//			out.close();
//		}catch(Exception e)
//		{
//
//		}	            
	}
}


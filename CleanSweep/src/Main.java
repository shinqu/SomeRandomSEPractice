import com.blackdroidstudios.cleansweep.gui.GUIControl;
import com.blackdroidstudios.cleansweep.mainsensor.CleanSweepMain;
import com.blackdroidstudios.cleansweep.map.FloorGenerator;

public class Main 
{
	//Variables
	private CleanSweepMain cleanSweep;
	private FloorGenerator floorGen;
	
	public static void main(String[] args) throws InterruptedException
	{
		GUIControl ctrl = new GUIControl();
		ctrl.initializeGUI();	
	}
	
}

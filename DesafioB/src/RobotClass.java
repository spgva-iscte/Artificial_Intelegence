import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;

public class RobotClass {
	 
	public static void main(String[] args) throws Exception {
		Simulator s= new Simulator();
		String filename = "robot.fcl";
		FIS fis = FIS.load(filename, true);
		
		if(fis == null) {
			System.err.println("Can't load file: '" + filename + "'");
			System.exit(1);
		}
		
		while (true) {
		
			FunctionBlock fb = fis.getFunctionBlock(null);
		
			fb.setVariable("sensor_right", s.getDistanceR());
			fb.setVariable("sensor_left", s.getDistanceL());
			fb.setVariable("sensor_center", s.getDistanceC());
		
			fb.evaluate();
		
			s.setRobotAngle(fb.getVariable("rotation_angle").defuzzify());
			s.step();
					
		}
	}

}

package teste;
import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;
import java.awt.Color;

/**
 * Teste - a class by (Ernane Junior)
 */
public class Cleber extends AdvancedRobot {
	private boolean movimento = true;

	public void run() {
		setBodyColor(Color.black);
		setGunColor(Color.magenta);
		setRadarColor(Color.red);
		setScanColor(Color.red);
		setBulletColor(Color.white);
		
		setAdjustGunForRobotTurn(true);
		setAdjustRadarForGunTurn(true);
		setTurnRadarRightRadians(Double.POSITIVE_INFINITY);
		
		while (true) {
		if (movimento) {
			setAhead(Double.POSITIVE_INFINITY);
		} else {
			setBack(Double.POSITIVE_INFINITY);
		}
			execute();
		}
	}
	//tank robo inimigo detectado
	public void onScannedRobot(ScannedRobotEvent e){
		double anguloInimigo = getHeadingRadians() + e.getBearingRadians();
		double virarCanhao = Utils.normalRelativeAngle(anguloInimigo - getGunHeadingRadians());
		
		setTurnGunRightRadians(virarCanhao);
		if (getGunHeat() == 0 && Math.abs(getGunTurnRemaining()) < 10) {
			setFireBullet(Math.min(3 - Math.abs(virarCanhao), getEnergy() - .1));
		}
			
		setTurnRightRadians(Utils.normalRelativeAngle(anguloInimigo - getHeadingRadians() + Math.PI / 2));
		movimento = !movimento;	
	
		scan();
	} 	
}

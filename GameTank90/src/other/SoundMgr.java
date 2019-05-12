package other;

import java.applet.Applet;
import java.applet.AudioClip;

public class SoundMgr {

	public static AudioClip enter_game;
	public static AudioClip life;
	public static AudioClip move;
	public static AudioClip shoot;
	public static AudioClip bum;
	public static AudioClip bum_tank;
	public static AudioClip level_completed;
	public static boolean canPlay=true;
	
	//khởi tạo các âm thanh sử dụng trong chương trình.
	public SoundMgr() {
		enter_game = Applet.newAudioClip(getClass().getResource("/SOUNDS/enter_game.wav"));
		life = Applet.newAudioClip(getClass().getResource("/SOUNDS/life.wav"));
		move = Applet.newAudioClip(getClass().getResource("/SOUNDS/move.wav"));
		shoot = Applet.newAudioClip(getClass().getResource("/SOUNDS/shoot.wav"));
		bum = Applet.newAudioClip(getClass().getResource("/SOUNDS/bum.wav"));
		bum_tank = Applet.newAudioClip(getClass().getResource("/SOUNDS/bum_tank.wav"));
		level_completed = Applet.newAudioClip(getClass().getResource("/SOUNDS/level_completed.wav"));
	}

	public static void play(final AudioClip audioClip) {
		if(canPlay){
			audioClip.play();
			if(audioClip==move)
				move.loop();
		}
	}
	
	public static void stopAll(){
		enter_game.stop();
		life.stop();
		move.stop();
		shoot.stop();
		bum.stop();
		bum_tank.stop();
		level_completed.stop();
	}
}
package com.ar9013.life09;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.sql.Time;
import java.util.Timer;

// ogg 檔案不能用在 ios 的裝置
// wav 檔案是音樂檔案，沒有壓縮比較佔空間，播放用 cpu 比較少。
// mp3 檔案是壓縮檔案（比較小），播放用 cpu 比較多。

public class Life09 extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	Sound wav;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		wav =Gdx.audio.newSound(Gdx.files.internal("sound/baby_crying_mono.wav"));
		final long id = wav.play();
		//wav.setVolume(id,0.3f); // 設定音量 0.0f ~f 1.0f
		//wav.setPitch(id,0.2f); // 設定播放速度
		//wav.setPan(id,1.0f,-1f); // 聲音要先用 audacity 處理成 mono(不能直接使用立體聲)，-1 左聲道 1右聲道
		wav.loop(1.0f,1.0f,1.0f); // 循環播放

		com.badlogic.gdx.utils.Timer.schedule(new com.badlogic.gdx.utils.Timer.Task() {
			@Override
			public void run() {
				wav.pause(id); // 10 秒後 有設 id 的會停止播放，另一個會循環播放
			}
		},10);
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		wav.dispose();
	}
}

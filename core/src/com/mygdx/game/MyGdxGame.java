package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	BitmapFont font;
	//Texture img;

	ShapeRenderer shapeRenderer;
	Vector3 ballPos;
	boolean moveLeft = false;
	boolean moveUp = true;
	float ballRadius = 44;
	float speed = 2;
	
	@Override
	public void create () {

		batch = new SpriteBatch();
		font = new BitmapFont();
		shapeRenderer = new ShapeRenderer();
		//img = new Texture("badlogic.jpg");

		ballPos = new Vector3(ballRadius,ballRadius,0);

	}

	@Override
	public void render () {
		String speedText = "";
		if(Gdx.input.isTouched()) {
			if(Gdx.input.getX() > (Gdx.graphics.getWidth() / 2)) {
				if(speed < 100) {
					speed += 2;
					speedText = "++++";
				}
				else {
					speedText = "Max Speed !!";
				}
			}
			else if(Gdx.input.getX() < (Gdx.graphics.getWidth() / 2)){
				if(speed > 2) {
					speed -= 2;
					speedText = "----";
				}
				else {
					speedText = "Min Speed !!";
				}
			}
		}
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//batch.draw(img, 0, 0);
		//batch.end();
		batch.begin();
		updateBallPos();
		font.draw(batch, "X: " + ballPos.x + ", Y: " + ballPos.y + speedText, 30, 30);
		batch.end();
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.BLUE);
		shapeRenderer.circle(ballPos.x, ballPos.y, ballRadius);
		shapeRenderer.end();


	}

	private void updateBallPos() {

		if(ballPos.x+ballRadius >= Gdx.graphics.getWidth()) {
			moveLeft = true;
		}
		if(ballPos.y + ballRadius >= Gdx.graphics.getHeight()) {
			moveUp = false;
		}
		if(ballPos.x-ballRadius <= 0) {
			moveLeft = false;
		}
		if(ballPos.y-ballRadius <= 0) {
			moveUp = true;
		}

		if(moveUp) {
			ballPos.y = ballPos.y + speed;
		}
		else {
			ballPos.y = ballPos.y - speed;
		}
		if(moveLeft) {
			ballPos.x = ballPos.x - speed;
		}
		else {
			ballPos.x = ballPos.x + speed;
		}
	}

	@Override
	public void dispose () {
		//img.dispose();
		shapeRenderer.dispose();
		batch.dispose();
	}
}

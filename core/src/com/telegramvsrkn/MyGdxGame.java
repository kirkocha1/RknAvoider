package com.telegramvsrkn;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class MyGdxGame extends ApplicationAdapter {

    public static final float SPEED = 300;

    public static final float SHIP_ANIMATION_SPEED = 0.5f;
    public static final int SHIP_WIDTH_PIXEL = 17;
    public static final int SHIP_HEIGHT_PIXEL = 32;
    public static final int SHIP_WIDTH = SHIP_WIDTH_PIXEL * 3;
    public static final int SHIP_HEIGHT = SHIP_HEIGHT_PIXEL * 3;
    public static final int SCREEN_WIDTH = 1024;
    public static final int SCREEN_HEIGHT = 2048;
    public static final float ROLL_TIMER_SWITCH_TIME = 0.25f;
    public static final float SHOOT_WAIT_TIME = 0.3f;

    public static final float MIN_ASTEROID_SPAWN_TIME = 0.05f;
    public static final float MAX_ASTEROID_SPAWN_TIME = 0.1f;

    SpriteBatch batch;
    Texture img;
    Vector2 velocity;
    TextureRegion npc;
    float x = 0;
    float y = 0;

    float width;
    float height;

    boolean left = false;
    boolean right = false;
    boolean down = false;
    boolean up = false;

    @Override
    public void create() {
//        velocity = new Vector2();
//        batch = new SpriteBatch();
//        img = new Texture("badlogic.jpg");
//        npc = new TextureRegion(img, 0, 0, img.getWidth(), img.getHeight());
//        width = npc.getRegionWidth() / 2;
//        height = npc.getRegionHeight() / 2;

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        moveUpIfNeed();
        moveDownIfNeed();
        moveLeftIfNeed();
        moveRightIfNeed();
        batch.begin();
        batch.draw(npc, x, y, width, height);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }

    private void moveUpIfNeed() {
        if (isUp()) {
            y += SPEED * Gdx.graphics.getDeltaTime();


        }
    }

    private void moveDownIfNeed() {
        if (isDown()) {
            y -= SPEED * Gdx.graphics.getDeltaTime();
        }
    }

    private void moveLeftIfNeed() {
        if (isLeft()) {//Left
            x -= SPEED * Gdx.graphics.getDeltaTime();
            if (x < 0)
                x = 0;
        }
    }

    private void moveRightIfNeed() {
        if (isRight()) {//Right
            x += SPEED * Gdx.graphics.getDeltaTime();

        }

    }

    private boolean isDown() {
        return Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isTouched();
    }

    private boolean isUp() {
        return Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isTouched();
    }

    private boolean isRight() {
        return Gdx.input.isKeyPressed(Input.Keys.RIGHT) || (Gdx.input.isTouched());
    }

    private boolean isLeft() {
        return Gdx.input.isKeyPressed(Input.Keys.LEFT) || (Gdx.input.isTouched());
    }

}

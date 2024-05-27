import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.util.awt.TextRenderer;

import java.awt.Font;

public class Renderer implements GLEventListener {
    private final ImageResources imageResources = new ImageResources();    //bn3ml instance
    private final Game game; //reference as object
    private TextRenderer textRenderer;
    private GL2 gl;

    public Renderer(Game game) {
        this.game = game;
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        gl = drawable.getGL().getGL2();
        gl.glClearColor(0, 0, 0, 1);  //color
        textRenderer = new TextRenderer(new Font("SansSerif", Font.PLAIN, 24));
        imageResources.loadBackgroundTexture(gl, "C:\\Users\\drago\\Desktop\\tic tac toe\\image.jpg");


    }

    @Override
    public void display(GLAutoDrawable drawable) {
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        imageResources.renderBackground(gl);
        renderTopBar(gl);
        renderBoard(gl);
        renderMarkers(gl);
        renderScores();
        renderResetButton();

        if (game.checkWin()) {
            game.resetBoard();
        }
    }

    private void renderTopBar(GL2 gl) {
        gl.glColor3f(1.0f, 1.0f, 1.0f);
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f(-1.0f, 1.0f);
        gl.glVertex2f(1.0f, 1.0f);
        gl.glVertex2f(1.0f, 0.9f);
        gl.glVertex2f(-1.0f, 0.9f);
        gl.glEnd();
    }

    private void renderBoard(GL2 gl) {
        float top = 0.9f;
        float bottom = -1.0f;
        float gridSize = top - bottom;
        float horizontalInterval = gridSize / 3;
        float verticalInterval = 2.0f / 3;

        gl.glColor3f(1.0f, 1.0f, 1.0f);
        gl.glLineWidth(2.0f);
        gl.glBegin(GL2.GL_LINES);
        for (int i = 1; i < 3; i++) {
            float x = -1.0f + i * verticalInterval;
            gl.glVertex2f(x, top);
            gl.glVertex2f(x, bottom);
        }
        for (int i = 1; i < 3; i++) {
            float y = top - i * horizontalInterval;
            gl.glVertex2f(-1.0f, y);
            gl.glVertex2f(1.0f, y);
        }
        gl.glEnd();
    }

    private void renderMarkers(GL2 gl) {
        char[][] board = game.getBoard();  //out loop by3adi 3la kol row w inner loop by3adi 3la kol column
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                float x = -0.66f + j * 0.66f;
                float y = 0.66f - i * 0.66f;
                if (board[i][j] == 'X') {
                    renderX(gl, x, y);
                } else if (board[i][j] == 'O') {
                    renderO(gl, x, y);
                }
            }
        }
    }

    private void renderX(GL2 gl, float x, float y) {
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2f(x - 0.1f, y + 0.1f);
        gl.glVertex2f(x + 0.1f, y - 0.1f);
        gl.glVertex2f(x + 0.1f, y + 0.1f);
        gl.glVertex2f(x - 0.1f, y - 0.1f);
        gl.glEnd();
    }

    private void renderO(GL2 gl, float x, float y) {
        gl.glColor3f(0.0f, 0.0f, 1.0f);
        gl.glBegin(GL2.GL_LINE_LOOP);
        for (int i = 0; i < 360; i++) {
            double theta = Math.PI * i / 180.0;
            double cx = x + 0.1 * Math.cos(theta);
            double cy = y + 0.1 * Math.sin(theta);
            gl.glVertex2f((float) cx, (float) cy);
        }
        gl.glEnd();
    }

    private void renderScores() {
        textRenderer.beginRendering(600, 600);
        textRenderer.setColor(0.0f, 0.0f, 0.0f, 1.0f);
        textRenderer.draw("X Wins: " + game.getXWins(), 10, 570);
        textRenderer.draw("O Wins: " + game.getOWins(), 200, 570);
        textRenderer.endRendering();
    }

    private void renderResetButton() {
        textRenderer.beginRendering(600, 600);
        textRenderer.setColor(0.0f, 0.0f, 0.0f, 1.0f);
        textRenderer.draw("RESET", 500, 570);
        textRenderer.endRendering();
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {}

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        gl = drawable.getGL().getGL2();
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(-1.0, 1.0, -1.0, 1.0, -1.0, 1.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
    }
}

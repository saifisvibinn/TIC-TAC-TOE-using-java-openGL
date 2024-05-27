import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import java.io.File;
import java.io.IOException;

public class ImageResources {
    private Texture backgroundTexture;

    public void loadBackgroundTexture(GL2 gl, String imagePath) {
        try {
            File file = new File(imagePath);
            backgroundTexture = TextureIO.newTexture(file, true);
            backgroundTexture.setTexParameteri(gl, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR);
            backgroundTexture.setTexParameteri(gl, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);
        } catch (IOException e) {
            System.err.println("Error loading texture: " + e.getMessage());
        }
    }

    public void renderBackground(GL2 gl) {
        if (backgroundTexture != null) {
            backgroundTexture.enable(gl);
            backgroundTexture.bind(gl);

            gl.glBegin(GL2.GL_QUADS);
            gl.glTexCoord2f(0f, 0f); gl.glVertex2f(-1.0f, -1.0f);
            gl.glTexCoord2f(1f, 0f); gl.glVertex2f(1.0f, -1.0f);
            gl.glTexCoord2f(1f, 1f); gl.glVertex2f(1.0f, 1.0f);
            gl.glTexCoord2f(0f, 1f); gl.glVertex2f(-1.0f, 1.0f);
            gl.glEnd();

            backgroundTexture.disable(gl);
        } else {
            System.out.println("Background texture is not loaded.");
        }
    }
}

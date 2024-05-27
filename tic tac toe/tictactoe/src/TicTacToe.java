import com.jogamp.newt.event.WindowAdapter;
import com.jogamp.newt.event.WindowEvent;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.util.FPSAnimator;

public class TicTacToe {
    public static void main(String[] args) {
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);

        GLWindow window = GLWindow.create(capabilities);
        window.setSize(600, 600);
        window.setTitle("Tic-Tac-Toe");
        window.setVisible(true);
        window.setResizable(false);

        Game game = new Game();
        Renderer renderer = new Renderer(game);
        MouseInput mouseInput = new MouseInput(game, window);

        window.addGLEventListener(renderer);
        window.addMouseListener(mouseInput);

        final FPSAnimator animator = new FPSAnimator(window, 60, true);
        animator.start();

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDestroyNotify(WindowEvent e) {
                new Thread(() -> {
                    animator.stop();
                    System.exit(0);
                }).start();
            }
        });
    }
}

import com.jogamp.newt.event.MouseEvent;
import com.jogamp.newt.event.MouseListener;
import com.jogamp.newt.opengl.GLWindow;

public class MouseInput implements MouseListener {
    private final Game game;
    private final GLWindow window;
    private final float topBarHeightPercentage = 0.1f; // 10% of the height is for the top bar
    private static final int GRID_SIZE = 3;
    private static final int RESET_BUTTON_WIDTH = 100;

    public MouseInput(Game game, GLWindow window) {
        this.game = game;
        this.window = window;
    }

    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int windowWidth = window.getWidth();
        int windowHeight = window.getHeight();

        // Adjust for the top bar
        float topBarHeight = windowHeight * topBarHeightPercentage;

        if (y <= topBarHeight) {
            // Handle top bar click
            handleTopBarClick(x, windowHeight);
        } else {
            // Only process clicks within the game area
            float gameHeight = windowHeight - topBarHeight;

            // Calculate cell indices based on mouse coordinates
            int col = (int) ((x / (float) windowWidth) * GRID_SIZE);
            int row = GRID_SIZE - 1 - (int) (((windowHeight - y) / gameHeight) * GRID_SIZE);

            // Debugging statement
            System.out.println("Mouse clicked at: (" + x + ", " + y + ")");
            System.out.println("Grid position: (" + row + ", " + col + ")");

            // Make sure the indices are within bounds
            if (row >= 0 && row < GRID_SIZE && col >= 0 && col < GRID_SIZE) {
                System.out.println("Making move at: (" + row + ", " + col + ")");
                game.makeMove(row, col);
            } else {
                System.out.println("Click outside the game grid boundaries.");
            }
        }
    }



    private void handleTopBarClick(int x, int windowHeight) {
        if (x >= window.getWidth() - RESET_BUTTON_WIDTH) {
            System.out.println("Reset button clicked.");
            game.resetBoard();
        }
    }

    // Unused override methods
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseMoved(MouseEvent e) {}
    @Override
    public void mouseDragged(MouseEvent e) {}
    @Override
    public void mouseWheelMoved(MouseEvent e) {}
}
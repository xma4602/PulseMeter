import javax.swing.*;
import java.awt.event.WindowEvent;

public class VideoShow {

    private static JFrame window;
    private static JLabel screen;

    static{
        window = new JFrame();
        screen = new JLabel();
        window.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        window.setVisible(true);
    }

    public static void refreshForm(ImageIcon ic) {
        screen.setIcon(ic);
        window.setContentPane(screen);
        window.pack();
    }
    public static void closeForm() {
        window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
    }

}

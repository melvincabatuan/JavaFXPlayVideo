package ph.edu.dlsu;

import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.opencv.core.Mat;
import ph.edu.dlsu.ui.CustomMenuItem;
import ph.edu.dlsu.ui.MenuHBox;
import ph.edu.dlsu.utils.Utils;

/**
 * Created by cobalt on 3/11/16.
 */
public class CameraScene extends BaseCameraScene {
    @Override
    public Parent createContent() {

        // Create App Menu pane
        Pane rootNode = new Pane();
        rootNode.setPrefSize(displayWidth, displayHeight);

        // Initialize background image and load to Imageview
        ImageView imgBackground = Utils.loadImage2View("res/skyrim.jpg", displayWidth, displayHeight);
        if (imgBackground != null) {
            rootNode.getChildren().add(imgBackground);
        }

        currentFrame = Utils.loadImage2View("res/video.jpg", frameWidth, frameHeight);
        currentFrame.setTranslateX((displayWidth - frameWidth) / 2.0);
        currentFrame.setTranslateY(0);
        rootNode.getChildren().add(currentFrame);
        startCamera();

        // Create Menu title and content
        createHMenu();

        // Add menu w/ title in the Pane
        rootNode.getChildren().add(menuBox);

        return rootNode;
    }

    @Override
    public void createHMenu() {
        final CustomMenuItem home = new CustomMenuItem("HOME", menuWidth, menuHeight);
        final CustomMenuItem training = new CustomMenuItem("TRAINING", menuWidth, menuHeight);
        final CustomMenuItem facts = new CustomMenuItem("FACTS", menuWidth, menuHeight);
        final CustomMenuItem help = new CustomMenuItem("HELP", menuWidth, menuHeight);
        final CustomMenuItem about = new CustomMenuItem("ABOUT", menuWidth, menuHeight);
        final CustomMenuItem exit = new CustomMenuItem("EXIT", menuWidth, menuHeight);

        // handle menu events
        home.setOnMouseClicked(e -> {
            stopCamera();
            App.onHome();
        });

        exit.setOnMouseClicked(e -> {
            Boolean confirmQuit = App.onExit();
            if (confirmQuit) {
                stopCamera();
            }
        });

        menuBox = new MenuHBox(
                home,
                training,
                facts,
                help,
                about,
                exit);

        menuBox.setTranslateX((displayWidth - 6 * menuWidth) / 2.0);
        menuBox.setTranslateY(0);

    }

    @Override
    public void onCameraFrame(Mat frame) {

    }
}
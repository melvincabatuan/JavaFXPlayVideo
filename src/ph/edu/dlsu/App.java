package ph.edu.dlsu;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.opencv.core.Core;
import ph.edu.dlsu.ui.CustomMenuItem;
import ph.edu.dlsu.ui.MenuTitle;
import ph.edu.dlsu.ui.MenuVBox;
import ph.edu.dlsu.utils.ConfirmationBox;
import ph.edu.dlsu.utils.ScreenSize;
import ph.edu.dlsu.utils.Utils;

public class App extends Application {

    private static final String WINDOW_TITLE = "MY APPLICATION -- Alpha Version";
    public static final String MENU_TITLE = " MAIN MENU";

    // Window size
    private static double displayWidth;
    private static double displayHeight;

    // App stage
    static Stage stage;

    // Main Menu
    MenuVBox menuBox;

    // Scene
    static Scene menuScene;


    @Override
    public void start(Stage primaryStage) throws Exception {
        initializeScreenSize();
        menuScene = new Scene(createHomeContent());
        stage = primaryStage;
        stage.setTitle(WINDOW_TITLE);
        stage.setScene(menuScene);
        stage.setFullScreen(true);
        stage.show();
    }

    private void initializeScreenSize() {
        displayWidth = ScreenSize.getDisplayWidth();
        displayHeight = ScreenSize.getDisplayHeight();
    }


    // Create content for the Main Menu scene
    private Parent createHomeContent() {

        // Create Main Menu pane
        Pane rootNode = new Pane();
        rootNode.setPrefSize(displayWidth, displayHeight);

        // Initialize background image and load to Imageview
        ImageView imgBackground = Utils.loadImage2View("res/skyrim.jpg", displayWidth, displayHeight);
        if (imgBackground != null) {
            rootNode.getChildren().add(imgBackground);
        }

        // Create Menu title and content
        MenuTitle title = new MenuTitle(MENU_TITLE);
        title.setTranslateX(50);
        title.setTranslateY(200);
        createVMenu();

        // Add menu w/ title in the Pane
        rootNode.getChildren().addAll(title, menuBox);

        return rootNode;
    }


    private void createVMenu() {

        // final CustomMenuItem login = new CustomMenuItem("LOGIN");
        final CustomMenuItem start = new CustomMenuItem("START");
        final CustomMenuItem tutorial = new CustomMenuItem("TUTORIAL");
        final CustomMenuItem facts = new CustomMenuItem("FACTS");
        final CustomMenuItem help = new CustomMenuItem("HELP");
        final CustomMenuItem about = new CustomMenuItem("ABOUT");
        final CustomMenuItem exit = new CustomMenuItem("EXIT");

        // handle menu events
        start.setOnMouseClicked(e -> onStart());
        tutorial.setOnMouseClicked(e -> onTutorial());
        exit.setOnMouseClicked(e -> onExit());

        menuBox = new MenuVBox(
                start,
                tutorial,
                facts,
                help,
                about,
                exit);

        menuBox.setTranslateX(100);
        menuBox.setTranslateY(300);
    }

    // HOME Menu
    public static void onHome() {
        stage.setTitle(WINDOW_TITLE);
        stage.setScene(menuScene);
    }

    // START Menu
    public static void onStart() {
        CameraScene startScene = new CameraScene();
        stage.setTitle("START SCENE");
        stage.setScene(new Scene(startScene.createContent(), displayWidth, displayHeight));
    }

    // START Menu
    public static void onTutorial() {
        TutorialScene tutorialScene = new TutorialScene();
        stage.setTitle("TUTORIAL SCENE");
        stage.setScene(new Scene(tutorialScene.createContent(), displayWidth, displayHeight));
    }



    // EXIT Menu
    public static boolean onExit() {
        boolean confirmQuit = ConfirmationBox.show(
                "Are you sure you want to quit?",
                "Yes", "No");
        if (confirmQuit) {
            // Perform cleanup tasks here
            Platform.exit();
        }
        return confirmQuit;
    }

    // Load OpenCV in main()
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME); // OpenCV
        launch(args);
    }
}
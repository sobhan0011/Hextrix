import java.io.File;
import java.util.Random;
import javafx.util.Duration;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;



class Hextris
{
    public static void main(String[] args)
    {
        Application.launch(JavaFx.class, args);
    }
}

public class JavaFx extends Application
{
    private static final Polygon[][] polygons = new Polygon[15][21];
    private static final boolean[][] status = new boolean[15][21];
    private static final boolean[][] movement = new boolean[15][21];
    private static final Random random = new Random();
    private static Timeline playing;
    private static int rand, points = 0, rotator, centerX, centerY;
    private static boolean key = false;
    private static boolean gameOver = false;
    private static ImagePattern filler;


    @Override
    public void init() {
        System.out.println("Starting...");
    }

    private static void createHexamino()
    {
        polygons[7][20].setFill(filler);
        polygons[7][19].setFill(filler);
        status[7][20] = movement[7][20] = true;
        status[7][19] = movement[7][19] = true;
        centerX = 7;
        centerY = 19;

        switch (rand) {
            case 0 -> {
                polygons[7][18].setFill(filler);
                polygons[7][17].setFill(filler);
                status[7][18] = movement[7][18] = true;
                status[7][17] = movement[7][17] = true;
            }
            case 1 -> {
                polygons[7][18].setFill(filler);
                polygons[8][18].setFill(filler);
                status[7][18] = movement[7][18] = true;
                status[8][18] = movement[8][18] = true;
            }
            case 2 -> {
                polygons[7][18].setFill(filler);
                polygons[6][20].setFill(filler);
                status[7][18] = movement[7][18] = true;
                status[6][20] = movement[6][20] = true;
            }
            case 3 -> {
                polygons[7][18].setFill(filler);
                polygons[8][20].setFill(filler);
                status[7][18] = movement[7][18] = true;
                status[8][20] = movement[8][20] = true;
            }
            case 4 -> {
                polygons[6][20].setFill(filler);
                polygons[8][20].setFill(filler);
                status[6][20] = movement[6][20] = true;
                status[8][20] = movement[8][20] = true;
            }
            case 5 -> {
                polygons[6][19].setFill(filler);
                polygons[8][19].setFill(filler);
                status[6][19] = movement[6][19] = true;
                status[8][19] = movement[8][19] = true;
            }
            case 6 -> {
                polygons[6][19].setFill(filler);
                polygons[6][18].setFill(filler);
                status[6][19] = movement[6][19] = true;
                status[6][18] = movement[6][18] = true;
            }
        }

    }

    private static void moveHexamino(KeyCode keyCode, ImagePattern woodPattern)
    {
        if(keyCode == KeyCode.W && key)
        {
            switch(rand)
            {
                case 0:
                    if(rotator % 3 == 0)
                    {
                        if(centerX % 2 != 0 && !status[centerX - 1][centerY + 1] && !status[centerX + 1][centerY] && !status[centerX + 2][centerY - 1])
                        {
                            status[centerX][centerY + 1] = movement[centerX][centerY + 1] = false;
                            status[centerX][centerY - 1] = movement[centerX][centerY - 1] = false;
                            status[centerX][centerY - 2] = movement[centerX][centerY - 2] = false;
                            status[centerX - 1][centerY + 1] = movement[centerX - 1][centerY + 1] = true;
                            status[centerX + 1][centerY] = movement[centerX + 1][centerY] = true;
                            status[centerX + 2][centerY - 1] = movement[centerX + 2][centerY - 1] = true;
                            polygons[centerX - 1][centerY + 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 1][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 2][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            for(int i = -2; i <= 1; i++)
                                if(i != 0)
                                    polygons[centerX][centerY + i].setFill(woodPattern);
                            rotator++;
                        }

                        else if(centerX % 2 == 0 && !status[centerX - 1][centerY] && !status[centerX + 1][centerY - 1] && !status[centerX + 2][centerY - 1])
                        {
                            status[centerX][centerY + 1] = movement[centerX][centerY + 1] = false;
                            status[centerX][centerY - 1] = movement[centerX][centerY - 1] = false;
                            status[centerX][centerY - 2] = movement[centerX][centerY - 2] = false;
                            status[centerX - 1][centerY] = movement[centerX - 1][centerY] = true;
                            status[centerX + 1][centerY - 1] = movement[centerX + 1][centerY - 1] = true;
                            status[centerX + 2][centerY - 1] = movement[centerX + 2][centerY - 1] = true;
                            polygons[centerX - 1][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 1][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 2][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            for(int i = -2; i <= 1; i++)
                                if(i != 0)
                                    polygons[centerX][centerY + i].setFill(woodPattern);
                            rotator++;
                        }

                    }

                    else if(rotator % 3 == 1)
                    {
                        if(centerX % 2 != 0 && !status[centerX + 1][centerY + 1] && !status[centerX - 1][centerY] && !status[centerX - 2][centerY - 1])
                        {
                            status[centerX - 1][centerY + 1] = movement[centerX - 1][centerY + 1] = false;
                            status[centerX + 1][centerY] = movement[centerX + 1][centerY] = false;
                            status[centerX + 2][centerY - 1] = movement[centerX + 2][centerY - 1] = false;
                            status[centerX + 1][centerY + 1] = movement[centerX + 1][centerY + 1] = true;
                            status[centerX - 1][centerY] = movement[centerX - 1][centerY] = true;
                            status[centerX - 2][centerY - 1] = movement[centerX - 2][centerY - 1] = true;
                            polygons[centerX + 1][centerY + 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 2][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY + 1].setFill(woodPattern);
                            polygons[centerX + 1][centerY].setFill(woodPattern);
                            polygons[centerX + 2][centerY - 1].setFill(woodPattern);
                            rotator++;
                        }

                        else if(centerX % 2 == 0 && !status[centerX - 1][centerY - 1] && !status[centerX - 2][centerY - 1] && !status[centerX + 1][centerY])
                        {
                            status[centerX + 1][centerY - 1] = movement[centerX + 1][centerY - 1] = false;
                            status[centerX - 1][centerY] = movement[centerX - 1][centerY] = false;
                            status[centerX + 2][centerY - 1] = movement[centerX + 2][centerY - 1] = false;
                            status[centerX - 1][centerY - 1] = movement[centerX - 1][centerY - 1] = true;
                            status[centerX - 2][centerY - 1] = movement[centerX - 2][centerY - 1] = true;
                            status[centerX + 1][centerY] = movement[centerX + 1][centerY] = true;
                            polygons[centerX - 1][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 2][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 1][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 1][centerY - 1].setFill(woodPattern);
                            polygons[centerX + 2][centerY - 1].setFill(woodPattern);
                            polygons[centerX - 1][centerY].setFill(woodPattern);
                            rotator++;
                        }
                    }

                    else if(rotator % 3 == 2)
                    {
                        if(!status[centerX][centerY + 1] && !status[centerX][centerY - 1] && !status[centerX][centerY - 2])
                        {
                            if(centerX % 2 != 0)
                            {
                                status[centerX - 1][centerY] = movement[centerX - 1][centerY] = false;
                                status[centerX + 1][centerY + 1] = movement[centerX + 1][centerY + 1] = false;
                                status[centerX - 2][centerY - 1] = movement[centerX - 2][centerY - 1] = false;
                                polygons[centerX - 1][centerY].setFill(woodPattern);
                                polygons[centerX + 1][centerY + 1].setFill(woodPattern);
                            }

                            else
                            {
                                status[centerX + 1][centerY] = movement[centerX + 1][centerY] = false;
                                status[centerX - 1][centerY - 1] = movement[centerX - 1][centerY - 1] = false;
                                status[centerX - 2][centerY - 1] = movement[centerX - 2][centerY - 1] = false;
                                polygons[centerX + 1][centerY].setFill(woodPattern);
                                polygons[centerX - 1][centerY - 1].setFill(woodPattern);
                            }
                            polygons[centerX - 2][centerY - 1].setFill(woodPattern);

                            status[centerX][centerY + 1] = movement[centerX][centerY + 1] = true;
                            status[centerX][centerY - 1] = movement[centerX][centerY - 1] = true;
                            status[centerX][centerY - 2] = movement[centerX][centerY - 2] = true;
                            polygons[centerX][centerY + 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX][centerY - 2].setFill(polygons[centerX][centerY].getFill());
                            rotator++;
                        }
                    }

                    break;

                case 1:
                    if(rotator % 6 == 0)
                    {
                        if(centerX % 2 != 0 && !status[centerX + 1][centerY + 1] && !status[centerX - 1][centerY] && !status[centerX - 1][centerY - 1])
                        {
                            status[centerX][centerY - 1] = movement[centerX][centerY - 1] = false;
                            status[centerX][centerY + 1] = movement[centerX][centerY + 1] = false;
                            status[centerX + 1][centerY - 1] = movement[centerX + 1][centerY - 1] = false;
                            status[centerX + 1][centerY + 1] = movement[centerX + 1][centerY + 1] = true;
                            status[centerX - 1][centerY] = movement[centerX - 1][centerY] = true;
                            status[centerX - 1][centerY - 1] = movement[centerX - 1][centerY - 1] = true;
                            polygons[centerX + 1][centerY + 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX][centerY - 1].setFill(woodPattern);
                            polygons[centerX][centerY + 1].setFill(woodPattern);
                            polygons[centerX + 1][centerY - 1].setFill(woodPattern);
                            rotator++;
                        }

                        else if(centerX % 2 == 0 && !status[centerX + 1][centerY] && !status[centerX - 1][centerY - 1] && !status[centerX - 1][centerY - 2])
                        {
                            status[centerX][centerY - 1] = movement[centerX][centerY - 1] = false;
                            status[centerX][centerY + 1] = movement[centerX][centerY + 1] = false;
                            status[centerX + 1][centerY - 2] = movement[centerX + 1][centerY - 2] = false;
                            status[centerX + 1][centerY] = movement[centerX + 1][centerY] = true;
                            status[centerX - 1][centerY - 1] = movement[centerX - 1][centerY - 1] = true;
                            status[centerX - 1][centerY - 2] = movement[centerX - 1][centerY - 2] = true;
                            polygons[centerX + 1][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY - 2].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX][centerY - 1].setFill(woodPattern);
                            polygons[centerX][centerY + 1].setFill(woodPattern);
                            polygons[centerX + 1][centerY - 2].setFill(woodPattern);
                            rotator++;
                        }

                    }

                    else if(rotator % 6 == 1)
                    {
                        if(centerX % 2 != 0 && !status[centerX + 1][centerY] && !status[centerX - 1][centerY + 1] && !status[centerX - 2][centerY])
                        {
                            status[centerX - 1][centerY] = movement[centerX - 1][centerY] = false;
                            status[centerX + 1][centerY + 1] = movement[centerX + 1][centerY + 1] = false;
                            status[centerX - 1][centerY  - 1] = movement[centerX - 1][centerY  - 1] = false;
                            status[centerX + 1][centerY] = movement[centerX + 1][centerY] = true;
                            status[centerX - 1][centerY + 1] = movement[centerX - 1][centerY + 1] = true;
                            status[centerX - 2][centerY] = movement[centerX - 2][centerY] = true;
                            polygons[centerX + 1][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY + 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 2][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY].setFill(woodPattern);
                            polygons[centerX + 1][centerY + 1].setFill(woodPattern);
                            polygons[centerX - 1][centerY  - 1].setFill(woodPattern);
                            rotator++;
                        }

                        else if(centerX % 2 == 0 && !status[centerX + 1][centerY - 1] && !status[centerX - 1][centerY] && !status[centerX - 2][centerY])
                        {
                            status[centerX - 1][centerY - 2] = movement[centerX - 1][centerY - 2] = false;
                            status[centerX + 1][centerY] = movement[centerX + 1][centerY] = false;
                            status[centerX - 1][centerY - 1] = movement[centerX - 1][centerY - 1] = false;
                            status[centerX + 1][centerY - 1] = movement[centerX + 1][centerY - 1] = true;
                            status[centerX - 1][centerY] = movement[centerX - 1][centerY] = true;
                            status[centerX - 2][centerY] = movement[centerX - 2][centerY] = true;
                            polygons[centerX + 1][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 2][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY - 2].setFill(woodPattern);
                            polygons[centerX + 1][centerY].setFill(woodPattern);
                            polygons[centerX - 1][centerY - 1].setFill(woodPattern);
                            rotator++;
                        }
                    }

                    else if(rotator % 6 == 2)
                    {
                        if(centerX % 2 != 0 && !status[centerX][centerY - 1] && !status[centerX][centerY + 1] && !status[centerX - 1][centerY + 2])
                        {
                            status[centerX - 1][centerY + 1] = movement[centerX - 1][centerY + 1] = false;
                            status[centerX + 1][centerY] = movement[centerX + 1][centerY] = false;
                            status[centerX - 2][centerY] = movement[centerX - 2][centerY] = false;
                            status[centerX][centerY - 1] = movement[centerX][centerY - 1] = true;
                            status[centerX][centerY + 1] = movement[centerX][centerY + 1] = true;
                            status[centerX - 1][centerY + 2] = movement[centerX - 1][centerY + 2]= true;
                            polygons[centerX][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX][centerY + 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY + 2].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY + 1].setFill(woodPattern);
                            polygons[centerX + 1][centerY].setFill(woodPattern);
                            polygons[centerX - 2][centerY].setFill(woodPattern);
                            rotator++;
                        }

                        else if(centerX % 2 == 0 && !status[centerX][centerY - 1] && !status[centerX][centerY + 1] && !status[centerX - 1][centerY + 1])
                        {
                            status[centerX - 1][centerY] = movement[centerX - 1][centerY] = false;
                            status[centerX - 2][centerY] = movement[centerX + 1][centerY] = false;
                            status[centerX + 1][centerY - 1] = movement[centerX + 1][centerY - 1] = false;
                            status[centerX][centerY - 1] = movement[centerX][centerY - 1] = true;
                            status[centerX][centerY + 1] = movement[centerX][centerY + 1] = true;
                            status[centerX - 1][centerY + 1] = movement[centerX - 1][centerY + 1] = true;
                            polygons[centerX][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX][centerY + 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY + 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY].setFill(woodPattern);
                            polygons[centerX - 2][centerY].setFill(woodPattern);
                            polygons[centerX + 1][centerY - 1].setFill(woodPattern);
                            rotator++;
                        }
                    }

                    else if(rotator % 6 == 3)
                    {
                        if(centerX % 2 != 0 && !status[centerX - 1][centerY] && !status[centerX + 1][centerY + 1] && !status[centerX + 1][centerY + 2])
                        {
                            status[centerX][centerY + 1] = movement[centerX][centerY + 1] = false;
                            status[centerX][centerY - 1] = movement[centerX][centerY - 1] = false;
                            status[centerX - 1][centerY  + 2] = movement[centerX - 1][centerY + 2] = false;
                            status[centerX - 1][centerY] = movement[centerX - 1][centerY] = true;
                            status[centerX + 1][centerY + 1] = movement[centerX + 1][centerY + 1] = true;
                            status[centerX + 1][centerY + 2] = movement[centerX + 1][centerY + 2] = true;
                            polygons[centerX - 1][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 1][centerY + 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 1][centerY + 2].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX][centerY + 1].setFill(woodPattern);
                            polygons[centerX][centerY - 1].setFill(woodPattern);
                            polygons[centerX - 1][centerY + 2].setFill(woodPattern);
                            rotator++;
                        }

                        else if(centerX % 2 == 0 && !status[centerX + 1][centerY] && !status[centerX + 1][centerY + 1] && !status[centerX - 1][centerY - 1])
                        {
                            status[centerX - 1][centerY + 1] = movement[centerX - 1][centerY + 1] = false;
                            status[centerX][centerY + 1] = movement[centerX][centerY + 1] = false;
                            status[centerX][centerY - 1] = movement[centerX][centerY - 1] = false;
                            status[centerX + 1][centerY] = movement[centerX + 1][centerY] = true;
                            status[centerX + 1][centerY + 1] = movement[centerX + 1][centerY + 1] = true;
                            status[centerX - 1][centerY - 1] = movement[centerX - 1][centerY - 1] = true;
                            polygons[centerX + 1][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 1][centerY + 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY + 1].setFill(woodPattern);
                            polygons[centerX][centerY + 1].setFill(woodPattern);
                            polygons[centerX][centerY - 1].setFill(woodPattern);
                            rotator++;
                        }
                    }

                    else if(rotator % 6 == 4)
                    {
                        if(centerX % 2 != 0 && !status[centerX - 1][centerY + 1] && !status[centerX + 1][centerY] && !status[centerX + 2][centerY])
                        {
                            status[centerX - 1][centerY] = movement[centerX - 1][centerY] = false;
                            status[centerX + 1][centerY + 1] = movement[centerX + 1][centerY + 1] = false;
                            status[centerX + 1][centerY + 2] = movement[centerX + 1][centerY + 2] = false;
                            status[centerX - 1][centerY + 1] = movement[centerX - 1][centerY + 1] = true;
                            status[centerX + 1][centerY] = movement[centerX + 1][centerY] = true;
                            status[centerX + 2][centerY] = movement[centerX + 2][centerY] = true;
                            polygons[centerX - 1][centerY + 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 1][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 2][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY].setFill(woodPattern);
                            polygons[centerX + 1][centerY + 1].setFill(woodPattern);
                            polygons[centerX + 1][centerY + 2].setFill(woodPattern);
                            rotator++;
                        }

                        else if(centerX % 2 == 0 && !status[centerX - 1][centerY] && !status[centerX + 1][centerY - 1] && !status[centerX + 2][centerY])
                        {
                            status[centerX + 1][centerY] = movement[centerX + 1][centerY] = false;
                            status[centerX - 1][centerY - 1] = movement[centerX - 1][centerY - 1] = false;
                            status[centerX + 1][centerY +1] = movement[centerX + 1][centerY +1] = false;
                            status[centerX - 1][centerY] = movement[centerX - 1][centerY] = true;
                            status[centerX + 1][centerY - 1] = movement[centerX + 1][centerY - 1] = true;
                            status[centerX + 2][centerY] = movement[centerX + 2][centerY] = true;
                            polygons[centerX - 1][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 1][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 2][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 1][centerY].setFill(woodPattern);
                            polygons[centerX - 1][centerY - 1].setFill(woodPattern);
                            polygons[centerX + 1][centerY +1].setFill(woodPattern);
                            rotator++;
                        }
                    }

                    else if(rotator % 6 == 5)
                    {
                        if(centerX % 2 != 0 && !status[centerX][centerY + 1] && !status[centerX][centerY - 1] && !status[centerX + 1][centerY - 1])
                        {
                            status[centerX - 1][centerY + 1] = movement[centerX - 1][centerY + 1] = false;
                            status[centerX + 1][centerY] = movement[centerX + 1][centerY] = false;
                            status[centerX + 2][centerY] = movement[centerX + 2][centerY] = false;
                            status[centerX][centerY + 1] = movement[centerX][centerY + 1] = true;
                            status[centerX][centerY - 1] = movement[centerX][centerY - 1] = true;
                            status[centerX + 1][centerY - 1] = movement[centerX + 1][centerY - 1] = true;
                            polygons[centerX][centerY + 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 1][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY + 1].setFill(woodPattern);
                            polygons[centerX + 1][centerY].setFill(woodPattern);
                            polygons[centerX + 2][centerY].setFill(woodPattern);
                            rotator++;
                        }

                        else if(centerX % 2 == 0 && !status[centerX][centerY - 1] && !status[centerX][centerY + 1] && !status[centerX + 1][centerY - 2])
                        {
                            status[centerX - 1][centerY] = movement[centerX - 1][centerY] = false;
                            status[centerX + 1][centerY - 1] = movement[centerX + 1][centerY - 1] = false;
                            status[centerX + 2][centerY] = movement[centerX + 2][centerY] = false;
                            status[centerX][centerY - 1] = movement[centerX][centerY - 1] = true;
                            status[centerX][centerY + 1] = movement[centerX][centerY + 1] = true;
                            status[centerX + 1][centerY - 2] = movement[centerX + 1][centerY - 2] = true;
                            polygons[centerX][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX][centerY + 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 1][centerY - 2].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY].setFill(woodPattern);
                            polygons[centerX + 1][centerY - 1].setFill(woodPattern);
                            polygons[centerX + 2][centerY].setFill(woodPattern);
                            rotator++;
                        }
                    }

                    break;

                case 2:
                    if(rotator % 6 == 0)
                    {
                        if(centerX % 2 != 0 && !status[centerX - 1][centerY] && !status[centerX + 1][centerY + 1])
                        {
                            status[centerX][centerY - 1] = movement[centerX][centerY - 1] = false;
                            status[centerX - 1][centerY + 1] = movement[centerX - 1][centerY + 1] = false;
                            status[centerX - 1][centerY] = movement[centerX - 1][centerY] = true;
                            status[centerX + 1][centerY + 1] = movement[centerX + 1][centerY + 1] = true;
                            polygons[centerX - 1][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 1][centerY + 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX][centerY - 1].setFill(woodPattern);
                            polygons[centerX - 1][centerY + 1].setFill(woodPattern);
                            rotator++;
                        }

                        else if(centerX % 2 == 0 && !status[centerX + 1][centerY] && !status[centerX - 1][centerY - 1])
                        {
                            status[centerX - 1][centerY] = movement[centerX - 1][centerY] = false;
                            status[centerX][centerY - 1] = movement[centerX][centerY - 1] = false;
                            status[centerX + 1][centerY] = movement[centerX + 1][centerY] = true;
                            status[centerX - 1][centerY - 1] = movement[centerX - 1][centerY - 1] = true;
                            polygons[centerX + 1][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY].setFill(woodPattern);
                            polygons[centerX][centerY - 1].setFill(woodPattern);
                            rotator++;
                        }

                    }

                    else if(rotator % 6 == 1)
                    {
                        if(centerX % 2 != 0 && !status[centerX + 1][centerY] && !status[centerX - 1][centerY + 1])
                        {
                            status[centerX][centerY + 1] = movement[centerX][centerY + 1] = false;
                            status[centerX - 1][centerY] = movement[centerX - 1][centerY] = false;
                            status[centerX + 1][centerY] = movement[centerX + 1][centerY] = true;
                            status[centerX - 1][centerY + 1] = movement[centerX - 1][centerY + 1] = true;
                            polygons[centerX + 1][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY + 1].setFill(polygons[centerX][centerY + 1].getFill());
                            polygons[centerX][centerY + 1].setFill(woodPattern);
                            polygons[centerX - 1][centerY].setFill(woodPattern);
                            rotator++;
                        }

                        else if(centerX % 2 == 0 && !status[centerX - 1][centerY] && !status[centerX + 1][centerY - 1])
                        {
                            status[centerX][centerY + 1] = movement[centerX][centerY + 1] = false;
                            status[centerX - 1][centerY - 1] = movement[centerX - 1][centerY - 1] = false;
                            status[centerX - 1][centerY] = movement[centerX - 1][centerY] = true;
                            status[centerX + 1][centerY - 1] = movement[centerX + 1][centerY - 1] = true;
                            polygons[centerX - 1][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 1][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX][centerY + 1].setFill(woodPattern);
                            polygons[centerX - 1][centerY - 1].setFill(woodPattern);
                            rotator++;
                        }
                    }

                    else if(rotator % 6 == 2)
                    {
                        if(centerX % 2 != 0 && !status[centerX][centerY - 1] && !status[centerX][centerY + 1])
                        {
                            status[centerX - 1][centerY + 1] = movement[centerX - 1][centerY + 1] = false;
                            status[centerX + 1][centerY + 1] = movement[centerX + 1][centerY + 1] = false;
                            status[centerX][centerY - 1] = movement[centerX][centerY - 1] = true;
                            status[centerX][centerY + 1] = movement[centerX][centerY + 1] = true;
                            polygons[centerX][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX][centerY + 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY + 1].setFill(woodPattern);
                            polygons[centerX + 1][centerY + 1].setFill(woodPattern);
                            rotator++;
                        }

                        else if(centerX % 2 == 0 && !status[centerX][centerY - 1] && !status[centerX][centerY + 1])
                        {
                            status[centerX + 1][centerY] = movement[centerX + 1][centerY] = false;
                            status[centerX - 1][centerY] = movement[centerX - 1][centerY] = false;
                            status[centerX][centerY - 1] = movement[centerX][centerY - 1] = true;
                            status[centerX][centerY + 1] = movement[centerX][centerY + 1] = true;
                            polygons[centerX][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX][centerY + 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 1][centerY].setFill(woodPattern);
                            polygons[centerX - 1][centerY].setFill(woodPattern);
                            rotator++;
                        }
                    }

                    else if(rotator % 6 == 3)
                    {
                        if(centerX % 2 != 0 && !status[centerX + 1][centerY + 1] && !status[centerX - 1][centerY])
                        {
                            status[centerX][centerY + 1] = movement[centerX][centerY + 1] = false;
                            status[centerX + 1][centerY] = movement[centerX + 1][centerY] = false;
                            status[centerX + 1][centerY + 1] = movement[centerX + 1][centerY + 1] = true;
                            status[centerX - 1][centerY] = movement[centerX - 1][centerY] = true;
                            polygons[centerX + 1][centerY + 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX][centerY + 1].setFill(woodPattern);
                            polygons[centerX + 1][centerY].setFill(woodPattern);
                            rotator++;
                        }

                        else if(centerX % 2 == 0 && !status[centerX + 1][centerY] && !status[centerX - 1][centerY - 1])
                        {
                            status[centerX][centerY + 1] = movement[centerX][centerY + 1] = false;
                            status[centerX + 1][centerY - 1] = movement[centerX + 1][centerY - 1] = false;
                            status[centerX + 1][centerY] = movement[centerX + 1][centerY] = true;
                            status[centerX - 1][centerY - 1] = movement[centerX - 1][centerY - 1] = true;
                            polygons[centerX + 1][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX][centerY + 1].setFill(woodPattern);
                            polygons[centerX + 1][centerY - 1].setFill(woodPattern);
                            rotator++;
                        }
                    }

                    else if(rotator % 6 == 4)
                    {
                        if(centerX % 2 != 0 && !status[centerX + 1][centerY] && !status[centerX - 1][centerY + 1])
                        {
                            status[centerX + 1][centerY + 1] = movement[centerX + 1][centerY + 1] = false;
                            status[centerX][centerY - 1] = movement[centerX][centerY - 1] = false;
                            status[centerX - 1][centerY + 1] = movement[centerX - 1][centerY + 1] = true;
                            status[centerX + 1][centerY] = movement[centerX + 1][centerY] = true;
                            polygons[centerX - 1][centerY + 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 1][centerY].setFill(polygons[centerX][centerY - 1].getFill());
                            polygons[centerX + 1][centerY + 1].setFill(woodPattern);
                            polygons[centerX][centerY - 1].setFill(woodPattern);
                            rotator++;
                        }

                        else if(centerX % 2 == 0 && !status[centerX - 1][centerY] && !status[centerX + 1][centerY - 1])
                        {
                            status[centerX + 1][centerY] = movement[centerX + 1][centerY] = false;
                            status[centerX][centerY - 1] = movement[centerX][centerY - 1] = false;
                            status[centerX - 1][centerY] = movement[centerX - 1][centerY] = true;
                            status[centerX + 1][centerY - 1] = movement[centerX + 1][centerY - 1] = true;
                            polygons[centerX - 1][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 1][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 1][centerY].setFill(woodPattern);
                            polygons[centerX][centerY - 1].setFill(woodPattern);
                            rotator++;
                        }
                    }

                    else if(rotator % 6 == 5)
                    {
                        if(centerX % 2 != 0 && !status[centerX][centerY - 1] && !status[centerX][centerY + 1])
                        {
                            status[centerX + 1][centerY] = movement[centerX + 1][centerY] = false;
                            status[centerX - 1][centerY] = movement[centerX - 1][centerY] = false;
                            status[centerX][centerY - 1] = movement[centerX][centerY - 1] = true;
                            status[centerX][centerY + 1] = movement[centerX][centerY + 1] = true;
                            polygons[centerX][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX][centerY + 1].setFill(polygons[centerX][centerY - 1].getFill());
                            polygons[centerX + 1][centerY].setFill(woodPattern);
                            polygons[centerX - 1][centerY].setFill(woodPattern);
                            rotator++;
                        }

                        else if(centerX % 2 == 0 && !status[centerX][centerY - 1] && !status[centerX][centerY + 1])
                        {
                            status[centerX - 1][centerY - 1] = movement[centerX - 1][centerY - 1] = false;
                            status[centerX + 1][centerY - 1] = movement[centerX + 1][centerY - 1] = false;
                            status[centerX][centerY - 1] = movement[centerX][centerY - 1] = true;
                            status[centerX][centerY + 1] = movement[centerX][centerY + 1] = true;
                            polygons[centerX][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX][centerY + 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY - 1].setFill(woodPattern);
                            polygons[centerX + 1][centerY - 1].setFill(woodPattern);
                            rotator++;
                        }
                    }

                    break;

                case 3:
                    if(rotator % 6 == 0)
                    {
                        if(centerX % 2 != 0 && !status[centerX - 1][centerY] && !status[centerX + 1][centerY])
                        {
                            status[centerX][centerY - 1] = movement[centerX][centerY - 1] = false;
                            status[centerX][centerY + 1] = movement[centerX][centerY + 1] = false;
                            status[centerX - 1][centerY] = movement[centerX - 1][centerY] = true;
                            status[centerX + 1][centerY] = movement[centerX + 1][centerY] = true;
                            polygons[centerX - 1][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 1][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX][centerY - 1].setFill(woodPattern);
                            polygons[centerX][centerY + 1].setFill(woodPattern);
                            rotator++;
                        }

                        else if(centerX % 2 == 0 && !status[centerX + 1][centerY - 1] && !status[centerX - 1][centerY - 1])
                        {
                            status[centerX][centerY - 1] = movement[centerX][centerY - 1] = false;
                            status[centerX][centerY + 1] = movement[centerX][centerY + 1] = false;
                            status[centerX + 1][centerY - 1] = movement[centerX + 1][centerY - 1] = true;
                            status[centerX - 1][centerY - 1] = movement[centerX - 1][centerY - 1] = true;
                            polygons[centerX + 1][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX][centerY - 1].setFill(woodPattern);
                            polygons[centerX][centerY + 1].setFill(woodPattern);
                            rotator++;
                        }

                    }

                    else if(rotator % 6 == 1)
                    {
                        if(centerX % 2 != 0 && !status[centerX][centerY - 1] && !status[centerX - 1][centerY + 1])
                        {
                            status[centerX - 1][centerY] = movement[centerX - 1][centerY] = false;
                            status[centerX + 1][centerY + 1] = movement[centerX + 1][centerY + 1] = false;
                            status[centerX][centerY - 1] = movement[centerX][centerY - 1] = true;
                            status[centerX - 1][centerY + 1] = movement[centerX - 1][centerY + 1] = true;
                            polygons[centerX][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY + 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY].setFill(woodPattern);
                            polygons[centerX + 1][centerY + 1].setFill(woodPattern);
                            rotator++;
                        }

                        else if(centerX % 2 == 0 && !status[centerX][centerY - 1] && !status[centerX - 1][centerY])
                        {
                            status[centerX + 1][centerY] = movement[centerX + 1][centerY] = false;
                            status[centerX - 1][centerY - 1] = movement[centerX - 1][centerY - 1] = false;
                            status[centerX][centerY - 1] = movement[centerX][centerY - 1] = true;
                            status[centerX - 1][centerY] = movement[centerX - 1][centerY] = true;
                            polygons[centerX][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 1][centerY].setFill(woodPattern);
                            polygons[centerX - 1][centerY - 1].setFill(woodPattern);
                            rotator++;
                        }
                    }

                    else if(rotator % 6 == 2)
                    {
                        if(centerX % 2 != 0 && !status[centerX][centerY + 1] && !status[centerX - 1][centerY])
                        {
                            status[centerX + 1][centerY] = movement[centerX + 1][centerY] = false;
                            status[centerX - 1][centerY + 1] = movement[centerX - 1][centerY + 1] = false;
                            status[centerX][centerY + 1] = movement[centerX][centerY + 1] = true;
                            status[centerX - 1][centerY] = movement[centerX - 1][centerY] = true;
                            polygons[centerX][centerY + 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 1][centerY].setFill(woodPattern);
                            polygons[centerX - 1][centerY + 1].setFill(woodPattern);
                            rotator++;
                        }

                        else if(centerX % 2 == 0 && !status[centerX][centerY + 1] && !status[centerX - 1][centerY - 1])
                        {
                            status[centerX + 1][centerY - 1] = movement[centerX + 1][centerY - 1] = false;
                            status[centerX - 1][centerY] = movement[centerX - 1][centerY] = false;
                            status[centerX][centerY + 1] = movement[centerX][centerY + 1] = true;
                            status[centerX - 1][centerY - 1] = movement[centerX - 1][centerY - 1] = true;
                            polygons[centerX][centerY + 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 1][centerY - 1].setFill(woodPattern);
                            polygons[centerX - 1][centerY].setFill(woodPattern);
                            rotator++;
                        }
                    }

                    else if(rotator % 6 == 3)
                    {
                        if(centerX % 2 != 0 && !status[centerX - 1][centerY + 1] && !status[centerX + 1][centerY + 1])
                        {
                            status[centerX][centerY - 1] = movement[centerX][centerY - 1] = false;
                            status[centerX][centerY + 1] = movement[centerX][centerY + 1] = false;
                            status[centerX - 1][centerY + 1] = movement[centerX - 1][centerY + 1] = true;
                            status[centerX + 1][centerY + 1] = movement[centerX + 1][centerY + 1] = true;
                            polygons[centerX - 1][centerY + 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 1][centerY + 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX][centerY - 1].setFill(woodPattern);
                            polygons[centerX][centerY + 1].setFill(woodPattern);
                            rotator++;
                        }

                        else if(centerX % 2 == 0 && !status[centerX + 1][centerY] && !status[centerX - 1][centerY])
                        {
                            status[centerX][centerY - 1] = movement[centerX][centerY - 1] = false;
                            status[centerX][centerY + 1] = movement[centerX][centerY + 1] = false;
                            status[centerX + 1][centerY] = movement[centerX + 1][centerY] = true;
                            status[centerX - 1][centerY] = movement[centerX - 1][centerY] = true;
                            polygons[centerX + 1][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX][centerY - 1].setFill(woodPattern);
                            polygons[centerX][centerY + 1].setFill(woodPattern);
                            rotator++;
                        }
                    }

                    else if(rotator % 6 == 4)
                    {
                        if(centerX % 2 != 0 && !status[centerX][centerY + 1] && !status[centerX + 1][centerY])
                        {
                            status[centerX - 1][centerY] = movement[centerX - 1][centerY] = false;
                            status[centerX + 1][centerY + 1] = movement[centerX + 1][centerY + 1] = false;
                            status[centerX][centerY + 1] = movement[centerX][centerY + 1] = true;
                            status[centerX + 1][centerY] = movement[centerX + 1][centerY] = true;
                            polygons[centerX][centerY + 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 1][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY].setFill(woodPattern);
                            polygons[centerX + 1][centerY + 1].setFill(woodPattern);
                            rotator++;
                        }

                        else if(centerX % 2 == 0 && !status[centerX][centerY + 1] && !status[centerX + 1][centerY - 1])
                        {
                            status[centerX + 1][centerY] = movement[centerX + 1][centerY] = false;
                            status[centerX - 1][centerY - 1] = movement[centerX - 1][centerY - 1] = false;
                            status[centerX][centerY + 1] = movement[centerX][centerY + 1] = true;
                            status[centerX + 1][centerY - 1] = movement[centerX + 1][centerY - 1] = true;
                            polygons[centerX][centerY + 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 1][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 1][centerY].setFill(woodPattern);
                            polygons[centerX - 1][centerY - 1].setFill(woodPattern);
                            rotator++;
                        }
                    }

                    else if(rotator % 6 == 5)
                    {
                        if(centerX % 2 != 0 && !status[centerX][centerY - 1] && !status[centerX + 1][centerY + 1])
                        {
                            status[centerX - 1][centerY + 1] = movement[centerX - 1][centerY + 1] = false;
                            status[centerX + 1][centerY] = movement[centerX + 1][centerY] = false;
                            status[centerX][centerY - 1] = movement[centerX][centerY - 1] = true;
                            status[centerX + 1][centerY + 1] = movement[centerX + 1][centerY + 1] = true;
                            polygons[centerX][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 1][centerY + 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY + 1].setFill(woodPattern);
                            polygons[centerX + 1][centerY].setFill(woodPattern);
                            rotator++;
                        }

                        else if(centerX % 2 == 0 && !status[centerX][centerY - 1] && !status[centerX + 1][centerY])
                        {
                            status[centerX - 1][centerY] = movement[centerX - 1][centerY] = false;
                            status[centerX + 1][centerY - 1] = movement[centerX + 1][centerY - 1] = false;
                            status[centerX][centerY - 1] = movement[centerX][centerY - 1] = true;
                            status[centerX + 1][centerY] = movement[centerX + 1][centerY] = true;
                            polygons[centerX][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 1][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY].setFill(woodPattern);
                            polygons[centerX + 1][centerY - 1].setFill(woodPattern);
                            rotator++;
                        }
                    }

                    break;
                case 4:
                    if(rotator != 0 && rotator % 3 == 0)
                        centerY--;

                    if(rotator % 3 == 0)
                    {
                        if(centerX % 2 != 0 && !status[centerX - 1][centerY])
                        {
                            status[centerX + 1][centerY + 1] = movement[centerX + 1][centerY + 1] = false;
                            status[centerX - 1][centerY] = movement[centerX - 1][centerY] = true;
                            polygons[centerX - 1][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 1][centerY + 1].setFill(woodPattern);
                            rotator++;
                        }

                        else if(centerX % 2 == 0 && !status[centerX - 1][centerY - 1])
                        {
                            status[centerX + 1][centerY] = movement[centerX + 1][centerY] = false;
                            status[centerX - 1][centerY - 1] = movement[centerX - 1][centerY - 1] = true;
                            polygons[centerX - 1][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 1][centerY].setFill(woodPattern);
                            rotator++;
                        }
                    }

                    else if(rotator % 3 == 1 && !status[centerX][centerY - 1])
                    {
                            status[centerX][centerY + 1] = movement[centerX][centerY + 1] = false;
                            status[centerX][centerY - 1] = movement[centerX][centerY - 1] = true;
                            polygons[centerX][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX][centerY + 1].setFill(woodPattern);
                            rotator++;
                    }

                    else if(rotator % 3 == 2)
                    {
                        if(centerX % 2 != 0 && !status[centerX + 1][centerY])
                        {
                            status[centerX - 1][centerY + 1] = movement[centerX - 1][centerY + 1] = false;
                            status[centerX + 1][centerY] = movement[centerX + 1][centerY] = true;
                            polygons[centerX + 1][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY + 1].setFill(woodPattern);
                            rotator++;
                        }

                        else if(centerX % 2 == 0 && !status[centerX + 1][centerY - 1])
                        {
                            status[centerX - 1][centerY] = movement[centerX - 1][centerY] = false;
                            status[centerX + 1][centerY - 1] = movement[centerX + 1][centerY - 1] = true;
                            polygons[centerX + 1][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY].setFill(woodPattern);
                            rotator++;
                        }
                    }

                    break;

                case 5:
                    if(rotator % 2 == 0)
                    {
                        if(centerX % 2 != 0 && !status[centerX + 1][centerY + 1] && !status[centerX][centerY - 1] && !status[centerX - 1][centerY + 1])
                        {
                            status[centerX][centerY + 1] = movement[centerX][centerY + 1] = false;
                            status[centerX - 1][centerY] = movement[centerX - 1][centerY] = false;
                            status[centerX + 1][centerY] = movement[centerX + 1][centerY] = false;
                            status[centerX + 1][centerY + 1] = movement[centerX + 1][centerY + 1] = true;
                            status[centerX][centerY - 1] = movement[centerX][centerY - 1] = true;
                            status[centerX - 1][centerY + 1] = movement[centerX - 1][centerY + 1] = true;
                            polygons[centerX + 1][centerY + 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY + 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX][centerY + 1].setFill(woodPattern);
                            polygons[centerX - 1][centerY].setFill(woodPattern);
                            polygons[centerX + 1][centerY].setFill(woodPattern);
                            rotator++;
                        }
                        else if(centerX % 2 == 0 && !status[centerX + 1][centerY] && !status[centerX - 1][centerY] && !status[centerX][centerY - 1])
                        {
                            status[centerX + 1][centerY - 1] = movement[centerX + 1][centerY - 1] = false;
                            status[centerX - 1][centerY - 1] = movement[centerX - 1][centerY - 1] = false;
                            status[centerX][centerY + 1] = movement[centerX][centerY + 1] = false;
                            status[centerX + 1][centerY] = movement[centerX + 1][centerY] = true;
                            status[centerX - 1][centerY] = movement[centerX - 1][centerY] = true;
                            status[centerX][centerY - 1] = movement[centerX][centerY - 1] = true;
                            polygons[centerX + 1][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 1][centerY - 1].setFill(woodPattern);
                            polygons[centerX - 1][centerY - 1].setFill(woodPattern);
                            polygons[centerX][centerY + 1].setFill(woodPattern);
                            rotator++;
                        }

                    }

                    else if(rotator % 2 == 1)
                    {
                        if(centerX % 2 != 0 && !status[centerX][centerY + 1] && !status[centerX - 1][centerY] && !status[centerX + 1][centerY])
                        {
                            status[centerX + 1][centerY + 1] = movement[centerX + 1][centerY + 1] = false;
                            status[centerX][centerY - 1] = movement[centerX][centerY - 1] = false;
                            status[centerX - 1][centerY + 1] = movement[centerX - 1][centerY + 1] = false;
                            status[centerX][centerY + 1] = movement[centerX][centerY + 1] = true;
                            status[centerX - 1][centerY] = movement[centerX - 1][centerY] = true;
                            status[centerX + 1][centerY] = movement[centerX + 1][centerY] = true;
                            polygons[centerX][centerY + 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 1][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 1][centerY + 1].setFill(woodPattern);
                            polygons[centerX][centerY - 1].setFill(woodPattern);
                            polygons[centerX - 1][centerY + 1].setFill(woodPattern);
                            rotator++;
                        }

                        else if(centerX % 2 == 0 && !status[centerX + 1][centerY - 1] && !status[centerX - 1][centerY - 1] && !status[centerX][centerY + 1])
                        {
                            status[centerX + 1][centerY] = movement[centerX + 1][centerY] = false;
                            status[centerX - 1][centerY] = movement[centerX - 1][centerY] = false;
                            status[centerX][centerY - 1] = movement[centerX][centerY - 1] = false;
                            status[centerX + 1][centerY - 1] = movement[centerX + 1][centerY - 1] = true;
                            status[centerX - 1][centerY - 1] = movement[centerX - 1][centerY - 1] = true;
                            status[centerX][centerY + 1] = movement[centerX][centerY + 1] = true;
                            polygons[centerX + 1][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX][centerY + 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 1][centerY].setFill(woodPattern);
                            polygons[centerX - 1][centerY].setFill(woodPattern);
                            polygons[centerX][centerY - 1].setFill(woodPattern);
                            rotator++;
                        }
                    }
                    break;

                case 6:
                    if(rotator % 3 == 0)
                    {
                        if(centerX % 2 != 0 && !status[centerX + 1][centerY + 1] && !status[centerX - 1][centerY + 1] && !status[centerX - 2][centerY])
                        {
                            status[centerX][centerY + 1] = movement[centerX][centerY + 1] = false;
                            status[centerX - 1][centerY] = movement[centerX - 1][centerY] = false;
                            status[centerX - 1][centerY - 1] = movement[centerX - 1][centerY - 1] = false;
                            status[centerX + 1][centerY + 1] = movement[centerX + 1][centerY + 1] = true;
                            status[centerX - 1][centerY + 1] = movement[centerX - 1][centerY + 1] = true;
                            status[centerX - 2][centerY] = movement[centerX - 2][centerY] = true;
                            polygons[centerX + 1][centerY + 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY + 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 2][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX][centerY + 1].setFill(woodPattern);
                            polygons[centerX - 1][centerY].setFill(woodPattern);
                            polygons[centerX - 1][centerY - 1].setFill(woodPattern);
                            rotator++;
                        }
                        else if(centerX % 2 == 0 && !status[centerX + 1][centerY] && !status[centerX - 1][centerY] && !status[centerX - 2][centerY])
                        {
                            status[centerX][centerY + 1] = movement[centerX][centerY + 1] = false;
                            status[centerX - 1][centerY - 1] = movement[centerX - 1][centerY - 1] = false;
                            status[centerX - 1][centerY - 2] = movement[centerX - 1][centerY - 2] = false;
                            status[centerX + 1][centerY] = movement[centerX + 1][centerY] = true;
                            status[centerX - 1][centerY] = movement[centerX - 1][centerY] = true;
                            status[centerX - 2][centerY] = movement[centerX - 2][centerY] = true;
                            polygons[centerX + 1][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 2][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX][centerY + 1].setFill(woodPattern);
                            polygons[centerX - 1][centerY - 1].setFill(woodPattern);
                            polygons[centerX - 1][centerY - 2].setFill(woodPattern);
                            rotator++;
                        }

                    }

                    else if(rotator % 3 == 1)
                    {
                        if(centerX % 2 != 0 && !status[centerX + 1][centerY] && !status[centerX][centerY + 1] && !status[centerX - 1][centerY + 2])
                        {
                            status[centerX + 1][centerY + 1] = movement[centerX + 1][centerY + 1] = false;
                            status[centerX - 1][centerY + 1] = movement[centerX - 1][centerY + 1] = false;
                            status[centerX - 2][centerY] = movement[centerX - 2][centerY] = false;
                            status[centerX + 1][centerY] = movement[centerX + 1][centerY] = true;
                            status[centerX][centerY + 1] = movement[centerX][centerY + 1] = true;
                            status[centerX - 1][centerY + 2] = movement[centerX - 1][centerY + 2] = true;
                            polygons[centerX + 1][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX][centerY + 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY + 2].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 1][centerY + 1].setFill(woodPattern);
                            polygons[centerX - 1][centerY + 1].setFill(woodPattern);
                            polygons[centerX - 2][centerY].setFill(woodPattern);
                            rotator++;
                        }

                        else if(centerX % 2 == 0 && !status[centerX + 1][centerY - 1] && !status[centerX][centerY + 1] && !status[centerX - 1][centerY + 1])
                        {
                            status[centerX + 1][centerY] = movement[centerX + 1][centerY] = false;
                            status[centerX - 1][centerY] = movement[centerX - 1][centerY] = false;
                            status[centerX - 2][centerY] = movement[centerX - 2][centerY] = false;
                            status[centerX + 1][centerY - 1] = movement[centerX + 1][centerY - 1] = true;
                            status[centerX][centerY + 1] = movement[centerX][centerY + 1] = true;
                            status[centerX - 1][centerY + 1] = movement[centerX - 1][centerY + 1] = true;
                            polygons[centerX + 1][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX][centerY + 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY + 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 1][centerY].setFill(woodPattern);
                            polygons[centerX - 1][centerY].setFill(woodPattern);
                            polygons[centerX - 2][centerY].setFill(woodPattern);
                            rotator++;
                        }
                    }

                    else if(rotator % 3 == 2)
                    {
                        if(centerX % 2 != 0 && !status[centerX - 1][centerY] && !status[centerX - 1][centerY - 1])
                        {
                            status[centerX + 1][centerY] = movement[centerX + 1][centerY] = false;
                            status[centerX - 1][centerY + 2] = movement[centerX - 1][centerY + 2] = false;
                            status[centerX - 1][centerY] = movement[centerX - 1][centerY] = true;
                            status[centerX - 1][centerY - 1] = movement[centerX - 1][centerY - 1] = true;
                            polygons[centerX - 1][centerY].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 1][centerY].setFill(woodPattern);
                            polygons[centerX - 1][centerY + 2].setFill(woodPattern);
                            rotator++;
                        }

                        if(centerX % 2 == 0 && !status[centerX - 1][centerY - 1] && !status[centerX - 1][centerY - 2])
                        {
                            status[centerX + 1][centerY - 1] = movement[centerX + 1][centerY - 1] = false;
                            status[centerX - 1][centerY + 1] = movement[centerX - 1][centerY + 1] = false;
                            status[centerX - 1][centerY - 1] = movement[centerX - 1][centerY - 1] = true;
                            status[centerX - 1][centerY - 2] = movement[centerX - 1][centerY - 2] = true;
                            polygons[centerX - 1][centerY - 1].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX - 1][centerY - 2].setFill(polygons[centerX][centerY].getFill());
                            polygons[centerX + 1][centerY - 1].setFill(woodPattern);
                            polygons[centerX - 1][centerY + 1].setFill(woodPattern);
                            rotator++;
                        }
                    }

                    break;

            }

        }

        if(keyCode == KeyCode.S)
        {
            int counter = 0;
            for (int i = 1; i < 14; i++)
                for (int j = 1; j < 21; j++)
                    if (status[i][j] && movement[i][j] && ((status[i][j - 1] && movement[i][j - 1]) || !(status[i][j - 1] && !movement[i][j - 1])))
                        counter++;

            if (counter == 4)
            {
                for (int i = 1; i < 14; i++)
                    for (int j = 1; j < 21; j++)
                        if (status[i][j] && movement[i][j] && ((status[i][j - 1] && movement[i][j - 1]) || !(status[i][j - 1] && !movement[i][j - 1])))
                        {
                            status[i][j] = movement[i][j] = false;
                            status[i][j - 1] = movement[i][j - 1] = true;
                            polygons[i][j - 1].setFill(polygons[i][j].getFill());
                            polygons[i][j].setFill(woodPattern);
                        }
            }
            else
                for (int i = 1; i < 14; i++)
                    for (int j = 1; j < 21; j++)
                        movement[i][j] = false;
            centerY--;
        }

        if(keyCode == KeyCode.A && key)
        {
            int counter = 0, u = 0;
            for (int i = 1; i < 14; i++)
                for (int j = 20; j > 0; j--)
                    if (status[i][j] && movement[i][j] && ((status[i - 1][j] && movement[i - 1][j]) || !(status[i - 1][j] && !movement[i - 1][j])))
                    {
                        counter++;
                        u = i;
                    }


            if (counter == 4)
            {
                if(u % 2 != 0)
                {
                    for (int i = 1; i < 14; i++)
                        for (int j = 20; j > 0; j--)
                        {
                            if (status[i][j] && movement[i][j] && ((status[i - 1][j - 1] && movement[i - 1][j - 1]) || !(status[i - 1][j - 1] && !movement[i - 1][j - 1])) && i % 2 == 0)
                            {
                                status[i][j] = movement[i][j] = false;
                                status[i - 1][j] = movement[i - 1][j] = true;
                                polygons[i - 1][j].setFill(polygons[i][j].getFill());
                                polygons[i][j].setFill(woodPattern);
                            }
                            else if ((status[i][j] && movement[i][j] && ((status[i - 1][j] && movement[i - 1][j]) || !(status[i - 1][j] && !movement[i - 1][j])) && i % 2 != 0))
                            {
                                status[i][j] = movement[i][j] = false;
                                status[i - 1][j + 1] = movement[i - 1][j + 1] = true;
                                polygons[i - 1][j + 1].setFill(polygons[i][j].getFill());
                                polygons[i][j].setFill(woodPattern);
                            }
                        }
                    if(centerX % 2 == 0)
                        centerX--;
                    else
                    {
                        centerX--;
                        centerY++;
                    }
                }

                else
                {
                    for (int i = 1; i < 14; i++)
                        for (int j = 20; j > 0; j--)
                        {
                            if (status[i][j] && movement[i][j] && ((status[i - 1][j] && movement[i - 1][j]) || !(status[i - 1][j] && !movement[i - 1][j])) && i % 2 == 0)
                            {
                                status[i][j] = movement[i][j] = false;
                                status[i - 1][j - 1] = movement[i - 1][j - 1] = true;
                                polygons[i - 1][j - 1].setFill(polygons[i][j].getFill());
                                polygons[i][j].setFill(woodPattern);
                            }
                            else if ((status[i][j] && movement[i][j] && ((status[i - 1][j] && movement[i - 1][j]) || !(status[i - 1][j] && !movement[i + 1][j])) && i % 2 != 0))
                            {
                                status[i][j] = movement[i][j] = false;
                                status[i - 1][j] = movement[i - 1][j] = true;
                                polygons[i - 1][j].setFill(polygons[i][j].getFill());
                                polygons[i][j].setFill(woodPattern);
                            }
                        }
                    if(centerX % 2 == 0)
                    {
                        centerX--;
                        centerY--;
                    }
                    else
                        centerX--;
                }


            }

        }

        if (keyCode == KeyCode.D && key)
        {
            int counter = 0, u = 0;
            for (int i = 13; i > 0; i--)
                for (int j = 20; j > 0; j--)
                    if (status[i][j] && movement[i][j] && ((status[i + 1][j] && movement[i + 1][j]) || !(status[i + 1][j] && !movement[i + 1][j])))
                    {
                        counter++;
                        u = i;
                    }


            if (counter == 4)
            {
                if(u % 2 != 0)
                {
                    for (int i = 13; i > 0; i--)
                        for (int j = 20; j > 0; j--)
                        {
                            if (status[i][j] && movement[i][j] && ((status[i + 1][j] && movement[i + 1][j]) || !(status[i + 1][j] && !movement[i + 1][j])) && i % 2 == 0)
                            {
                                status[i][j] = movement[i][j] = false;
                                status[i + 1][j] = movement[i + 1][j] = true;
                                polygons[i + 1][j].setFill(polygons[i][j].getFill());
                                polygons[i][j].setFill(woodPattern);
                            }
                            else if ((status[i][j] && movement[i][j] && ((status[i + 1][j] && movement[i + 1][j]) || !(status[i + 1][j] && !movement[i + 1][j])) && i % 2 != 0))
                            {
                                status[i][j] = movement[i][j] = false;
                                status[i + 1][j + 1] = movement[i + 1][j + 1] = true;
                                polygons[i + 1][j + 1].setFill(polygons[i][j].getFill());
                                polygons[i][j].setFill(woodPattern);
                            }
                        }
                    if(centerX % 2 == 0)
                        centerX++;
                    else
                    {
                        centerX++;
                        centerY++;
                    }
                }
                else
                {
                    for (int i = 13; i > 0; i--)
                    {
                        for (int j = 20; j > 0; j--)
                        {
                            if (status[i][j] && movement[i][j] && ((status[i + 1][j] && movement[i + 1][j]) || !(status[i + 1][j] && !movement[i + 1][j])) && i % 2 == 0)
                            {
                                status[i][j] = movement[i][j] = false;
                                status[i + 1][j - 1] = movement[i + 1][j - 1] = true;
                                polygons[i + 1][j - 1].setFill(polygons[i][j].getFill());
                                polygons[i][j].setFill(woodPattern);
                            }
                            else if ((status[i][j] && movement[i][j] && ((status[i + 1][j] && movement[i + 1][j]) || !(status[i + 1][j] && !movement[i + 1][j])) && i % 2 != 0))
                            {
                                status[i][j] = movement[i][j] = false;
                                status[i + 1][j] = movement[i + 1][j] = true;
                                polygons[i + 1][j].setFill(polygons[i][j].getFill());
                                polygons[i][j].setFill(woodPattern);
                            }
                        }
                    }
                    if(centerX % 2 == 0)
                    {
                        centerX++;
                        centerY--;
                    }
                    else
                        centerX++;
                }


            }

        }
    }

    private static boolean checking(ImagePattern woodPattern)
    {
        int counter;
        for (int i = 1; i < 21; i++)
        {
            counter = 0;

            for (int j = 1; j < 14; j++)
                if(status[j][i] && !movement[j][i])
                    counter++;

                if(counter == 13)
                {
                    points += 20;

                    for (int l = 1; l < 21; l++)
                        for (int k = 1; k < 14; k++)
                            if(status[k][l] && !movement[k][l])
                            {
                                status[k][l] = movement[k][l] = false;
                                status[k][l - 1] = true;
                                movement[k][l - 1] = false;
                                if(l - 1 > 0)
                                    polygons[k][l - 1].setFill(polygons[k][l].getFill());
                                polygons[k][l].setFill(woodPattern);
                            }
                }
        }

        for (int i = 19; i < 21; i++)
            for (int j = 6; j < 9; j++)
                if (status[j][i] && !movement[j][i])
                {
                    gameOver = true;
                    break;
                }

        for (int i = 1; i < 14; i++)
            for (int j = 1; j < 21; j++)
                if(movement[i][j])
                    return false;
        return true;
    }

    private static ImagePattern fill(int rand, ImagePattern[] imagePatterns)
    {
        ImagePattern imagePattern;
        imagePattern = new ImagePattern(imagePatterns[rand + 1].getImage());
        return imagePattern;
    }

    @Override
    public void start(Stage stage)
    {
        stage.setTitle("HEXTRIS");
        stage.setWidth(590.0);
        stage.setHeight(1007.0);
        stage.initStyle(StageStyle.UTILITY);
        stage.setResizable(false);
        File input = new File("data/image/Start.jpg");
        Image background = new Image(input.toURI().toString());
        ImageView backgroundView = new ImageView(background);

        File input2 = new File("data/image/BlackDiamond.jpg");
        Image blackDiamond = new Image(input2.toURI().toString());
        ImagePattern blackDiamondPattern = new ImagePattern(blackDiamond);

        File input3 = new File("data/image/Gold.jpg");
        Image gold = new Image(input3.toURI().toString());
        ImagePattern goldPattern = new ImagePattern(gold);

        File input4 = new File("data/image/Silver.jpg");
        Image silver = new Image(input4.toURI().toString());
        ImagePattern silverPattern = new ImagePattern(silver);

        File input5 = new File("data/image/BlueDiamond.jpg");
        Image blueDiamond = new Image(input5.toURI().toString());
        ImagePattern blueDiamondPattern = new ImagePattern(blueDiamond);

        File input6 = new File("data/image/PurpleDiamond.jpg");
        Image purpleDiamond = new Image(input6.toURI().toString());
        ImagePattern purpleDiamondPattern = new ImagePattern(purpleDiamond);

        File input7 = new File("data/image/RedDiamond.jpg");
        Image redDiamond = new Image(input7.toURI().toString());
        ImagePattern redDiamondPattern = new ImagePattern(redDiamond);

        File input8 = new File("data/image/GreenDiamond.jpg");
        Image greenDiamond = new Image(input8.toURI().toString());
        ImagePattern greenDiamondPattern = new ImagePattern(greenDiamond);

        File input9 = new File("data/image/PinkDiamond.jpg");
        Image pinkDiamond = new Image(input9.toURI().toString());
        ImagePattern pinkDiamondPattern = new ImagePattern(pinkDiamond);

        File input10 = new File("data/image/Wood.jpg");
        Image wood = new Image(input10.toURI().toString());
        ImagePattern woodPattern = new ImagePattern(wood);

        ImagePattern[] imagePatterns = {blackDiamondPattern, goldPattern, silverPattern, blueDiamondPattern, purpleDiamondPattern, redDiamondPattern, greenDiamondPattern, pinkDiamondPattern};

        File input11 = new File("data/image/GameOver.jpg");
        Image gameOverBackground = new Image(input11.toURI().toString());
        ImageView gameOverBackgroundView = new ImageView(gameOverBackground);

        File input12 = new File("data/music/a.aac");
        Media music0 = new Media(input12.toURI().toString());
        MediaPlayer music = new MediaPlayer(music0);

        StackPane parent = new StackPane();
        Scene scene = new Scene(parent);
        Button start = new Button("_Play");
        start.setMinSize(100,20);
        start.setMnemonicParsing(true);
        start.setStyle("-fx-background-color: #e3fae9; -fx-font-size: 25");
        Group buttons = new Group(start);
        parent.getChildren().addAll(backgroundView, buttons);
        stage.setScene(scene);
        stage.show();
        Text text = new Text();
        text.setFont(new Font("Verdana", 45));
        text.setFill(Color.web("white"));

        start.setOnAction(actionEvent -> {

            music.play();

            final double HEXAGON_WIDTH = 25.0;
            final double HEXAGON_HEIGHT = Math.sqrt(3) * HEXAGON_WIDTH;
            final double STARTING_X = 0;
            final double STARTING_Y = 969.0 - (HEXAGON_HEIGHT / 2);
            double x, y;
            AnchorPane map = new AnchorPane();

            File input13 = new File("data/image/White.jpg");
            Image white = new Image(input13.toURI().toString());
            ImageView whiteView = new ImageView(white);

            Font font = new Font(25);
            Label point = new Label("Point : " + points);
            point.setFont(font);
            point.setLayoutX(230.0);
            map.getChildren().addAll(whiteView, point);

            for (int i = 0; i < 15; i++)
            {
                for (int j = 0; j < 21; j++)
                {
                    x = STARTING_X + i * HEXAGON_WIDTH * 1.5;
                    y = STARTING_Y - j * HEXAGON_HEIGHT;
                    if (i % 2 == 1)
                        y -= HEXAGON_HEIGHT / 2;

                    polygons[i][j] = new Map(x, y ,HEXAGON_WIDTH, HEXAGON_HEIGHT);

                    if (i == 0 || i == 14 || j == 0)
                    {
                        polygons[i][j].setFill(imagePatterns[0]);
                        status[i][j] = true;
                        movement[i][j] = false;
                    }
                    else
                    {
                        polygons[i][j].setFill(woodPattern);
                        polygons[i][j].setStroke(Color.web("black"));
                        status[i][j] = movement[i][j] = false;
                    }
                    map.getChildren().add(polygons[i][j]);
                }
            }

            Scene scene2 = new Scene(map);
            stage.setScene(scene2);

            playing = new Timeline(new KeyFrame(Duration.seconds(0.5), new EventHandler<>() {
                int i = 0;

                @Override
                public void handle(ActionEvent actionEvent) {
                    if (gameOver) {
                        //music.stop();
                        playing.stop();
                        text.setText("Your point is : " + points);
                        StackPane gameOver = new StackPane(gameOverBackgroundView, text);
                        Scene scene3 = new Scene(gameOver);
                        stage.setScene(scene3);
                    }


                    if (i == 0) {
                        map.getChildren().clear();
                        point.setText("Point : " + points);
                        map.getChildren().addAll(whiteView, point);
                        rand = random.nextInt(7);
                        filler = fill(rand, imagePatterns);
                        for (int j = 0; j < 15; j++)
                            for (int k = 0; k < 21; k++)
                                map.getChildren().addAll(polygons[j][k]);

                    } else {
                        if (checking(woodPattern) && !gameOver) {
                            key = false;
                            rand = random.nextInt(7);
                            filler = fill(rand, imagePatterns);
                            createHexamino();
                            rotator = 0;
                        } else {

                            map.getChildren().clear();
                            point.setText("Point : " + points);
                            map.getChildren().addAll(whiteView, point);
                            moveHexamino(KeyCode.S, woodPattern);
                            key = true;

                            for (int i = 0; i < 15; i++)
                                for (int j = 0; j < 21; j++)
                                    if (i == 0 || i == 14 || j == 0) {
                                        polygons[i][j].setFill(imagePatterns[0]);
                                        status[i][j] = true;
                                        movement[i][j] = false;
                                    }

                            for (int j = 0; j < 15; j++)
                                for (int k = 0; k < 21; k++)
                                    map.getChildren().addAll(polygons[j][k]);
                        }

                    }
                    i = 1;
                }
            }));

            playing.setCycleCount(Timeline.INDEFINITE);
            playing.play();

            scene2.setOnKeyPressed(keyEvent -> {
                if(keyEvent.getCode() == KeyCode.W)
                    moveHexamino(KeyCode.W, woodPattern);
                if(keyEvent.getCode() == KeyCode.S)
                    moveHexamino(KeyCode.S, woodPattern);
                if(keyEvent.getCode() == KeyCode.A)
                    moveHexamino(KeyCode.A, woodPattern);
                if(keyEvent.getCode() == KeyCode.D)
                    moveHexamino(KeyCode.D, woodPattern);

            });

        });


    }

    @Override
    public void stop() {
        System.out.println("Good bye!");
    }

}

class Map extends Polygon
{
    Map(double x, double y ,double HEXAGON_WIDTH, double HEXAGON_HEIGHT)
    {
        getPoints().addAll(
                x, y,
                x + (HEXAGON_WIDTH) / 2, y + (HEXAGON_HEIGHT / 2),
                x + (3 * HEXAGON_WIDTH / 2), y + (HEXAGON_HEIGHT / 2),
                x + (2 * HEXAGON_WIDTH), y,
                x + (3 * HEXAGON_WIDTH / 2), y - (HEXAGON_HEIGHT / 2),
                x + (HEXAGON_WIDTH) / 2, y - (HEXAGON_HEIGHT / 2)
        );
    }
}

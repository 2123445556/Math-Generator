package com.example.demo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
// This is the GUI of the program.
public class HelloApplication extends Application implements EventHandler<ActionEvent> {
    Button button;
    Stage window;
    Button button2;
    Button button3;
    ComboBox<String> type;
    TextField field;
    TextField field2;
    TextField field3;
    TextField field4;
    MathGenerator math;
    ProgressBar p = new ProgressBar(0);
    double ind = 0.0;
    int index = 0;
    String[] obj = {"Multiplication", "Division", "Addition", "Subtraction", "Multi/Div", "Add/Sub", "All of the Problems"};

    // Creates the Main menu of the program.
    public void start(Stage stage){
        window = stage;
        StackPane anchor = new StackPane();
        anchor.getChildren().add(getVBox());
        Scene scene = new Scene(anchor, 500, 500);
        stage.setTitle("Math Generator Quiz");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    public VBox getVBox(){
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(15, 12, 15, 12));
        vbox.setSpacing(10);
        vbox.setStyle("-fx-background-color: #B0E0E6;");

        Text label = new Text("Welcome to Math Generator");
        label.setTextAlignment(TextAlignment.CENTER);
        button = new Button("Continue");
        button.setStyle("-fx-background-color: #4682B4;");
        button.setTextAlignment(TextAlignment.CENTER);
        button.setOnAction(this :: handle);
        vbox.getChildren().add(label);
        vbox.getChildren().add(button);

        return vbox;
    }
    // handles all the actions the user makes when he clicks on a button or dropdown box.
    @Override
    public void handle(ActionEvent actionEvent) {
        if(actionEvent.getSource() == button){
            Scene scene = new Scene(getStackPane(), 500, 500);
            window.setScene(scene);
            window.show();

        }
        if(actionEvent.getSource() == button2){
            if(field.getText() != "Enter your name" && field2.getText() != "Enter the number of problems you want to do" && field3.getText() != "Enter your Favorite Fruit") {
                int num = Integer.parseInt(field2.getText());
                String typ = (String) field3.getText();
                String temp = (String) type.getValue();
                String name = (String) field.getText();
                math = new MathGenerator(num, temp, typ, name);
                Scene scene = new Scene(getStackPane2(), 1000, 500);
                window.setScene(scene);
                window.show();
            }
        }
        if(actionEvent.getSource() == button3){
            if(math.getNumProblems() == index){
                if(math.getAnswer(index) == Integer.parseInt(field4.getText()) ){
                    math.incrementAnswersCorrect();
                }
                Scene scene3 = new Scene(getStackPane3(), 400, 600);
                window.setScene(scene3);
                window.show();
            }
            else if(Integer.parseInt(field4.getText()) == math.getAnswer(index) && math.getNumProblems() != index){
                math.incrementAnswersCorrect();
                index++;
                Scene scene = new Scene(getStackPane2(), 800, 500);
                window.setScene(scene);
                window.show();
            }
            else if(Integer.parseInt(field4.getText()) != math.getAnswer(index) && math.getNumProblems() != index){
                math.incrementAnswersIncorrect();
                index++;
                Scene scene2 = new Scene(getStackPane2(), 800, 500);
                window.setScene(scene2);
                window.show();
            }

            ind += ((double)1/math.getNumProblems());
            p.setProgress(ind);
        }
    }
    // Creates the window that contains the settings where it asks you how many questions you want and the type you want.
    public VBox getVBox2(){
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(15, 12, 15, 12));
        vbox.setSpacing(10);
        vbox.setStyle("-fx-background-color: #B0E0E6;");

        field = new TextField("Enter your name.");
        field.setStyle("-fx-background-color: #FDF5E6;");
        field2 = new TextField("Enter the number of problems you want to do");
        field2.setStyle("-fx-background-color: #FDF5E6;");
        field3 = new TextField("Enter your Favorite Fruit");
        field3.setStyle("-fx-background-color: #FDF5E6;");


        type = new ComboBox<String>();
        type.setStyle("-fx-background-color: #FDF5E6;");
        for(int i = 0; i < obj.length; i++){
            type.getItems().add(obj[i]);
        }
        type.setOnAction(this :: handle);
        button2 = new Button("Enter");
        button2.setOnAction(this :: handle);
        button2.setStyle("-fx-background-color: #4682B4;");
        vbox.getChildren().addAll(field, field2, field3, type, button2);
        return vbox;

    }
    // gets the window of the settings.
    public StackPane getStackPane(){
        StackPane pane = new StackPane();
        pane.getChildren().add(getVBox2());
        return pane;
    }
    // Creates the window where you get the questions and answer them.
    public GridPane getHBox(){
        GridPane grid = new GridPane();
        grid.getColumnConstraints().add(new ColumnConstraints(50));
        grid.getRowConstraints().add(new RowConstraints(50));



        Text text = new Text();
        grid.setConstraints(text, 2, 20);
        if(index < math.getNumProblems()) {
            text.setText(math.getProblems()[index]);
        }

        field4 = new TextField("Enter your Answer");
        grid.setConstraints(field4, 3, 20);
        button3 = new Button("Enter");
        button3.setStyle("-fx-background-color: #4682B4;");
        grid.setConstraints(button3, 4, 20);
        button3.setOnAction(this :: handle);
        p.setStyle("-fx-accent: #00FF7F;");
        grid.setConstraints(p, 2, 0);

        if(math.getNumProblems() == index){
            text.setText("Bonus Question: " + math.generateWordProblem());
            grid.setConstraints(text, 1, 20);
            grid.setConstraints(field4, 2, 20);
            grid.setConstraints(button3, 3, 20);
        }
        text.setStyle("-fx-accent: #87CEEB;");


        Text text2 = new Text("Questions gotten right: "+math.getAnswersCorrect()+ "/" + math.getNumProblems());
        grid.setConstraints(text2, 1, 0);
        text2.setStyle("-fx-accent: #00FA9A;");
        grid.setStyle("-fx-background-color: #B0E0E6;");


        grid.getChildren().addAll(text, field4, button3, text2, p);
        return grid;
    }
    // Gets the window with the questions
    public StackPane getStackPane2(){
        StackPane pane = new StackPane(getHBox());
        return pane;

    }
    // This creates the end screen where its tells you what you got wrong and right and the answers to the questions
    // Also provides the option to play again as well.
    public VBox getVBox3(){
        VBox vbox  = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(15, 12, 15, 12));
        vbox.setSpacing(10);
        vbox.setStyle("-fx-background-color: #B0E0E6;");

        Text text  = new Text("Congratulations you have completed the Math Generator Quiz ");
        Text text3 = new Text("Here are your Results:");
        text.setTextAlignment(TextAlignment.CENTER);
        Text text2 = new Text("Percentage you got correct: " + math.getPercentageCorrect());
        text2.setTextAlignment(TextAlignment.CENTER);
        int num = math.getNumProblems()+1;
        Text text4 = new Text("Questions you got correct in total: "+math.getAnswersCorrect()+ "/" + num);
        text4.setTextAlignment(TextAlignment.CENTER);
        Button button4 = new Button("Play Again");
        button4.setAlignment(Pos.CENTER);
        button4.setStyle("-fx-background-color: #4682B4;");
        button4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(actionEvent.getSource() == button4){
                    index = 0;
                    p.setProgress(0.0);
                    ind = 0;
                    window.setScene(new Scene(getStackPane(), 500, 500));
                }
            }
        });
        Button button5 = new Button("Exit");
        button5.setAlignment(Pos.CENTER);
        button5.setStyle("-fx-background-color: #4682B4;");
        button5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(actionEvent.getSource() == button5){
                    System.exit(0);
                }
            }
        });
        Text text6 =  new Text("Answer to Questions: ");


        vbox.getChildren().addAll(text,text3, text2,text4, button4, button5, text6);
        for(int i = 0; i< math.getNumProblems()+1; i++){
            int b = i+1;
            vbox.getChildren().add(new Text(b +") "+ math.getAnswer(i)));
        }
        return vbox;
    }
   // gets the window with the end screen
    public StackPane getStackPane3(){
        StackPane stackPane = new StackPane(getVBox3());
        return stackPane;
    }



}
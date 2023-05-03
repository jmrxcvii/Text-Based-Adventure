import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
/**
 * graphical class that displays game in a user interface for user to player game 
 * @author John Rosso
 * @version 1.0
 */
public class Maze extends Application {
    // data members for interface and building game
	Game game = null;
	Text player1 = null;
	Text player2 = null;
	Button restartButton = null;
    // putting buttons inside an array for easy access when creating interface 
    // and attaching handlers
	Button[] up = new Button[2];
	Button[] down = new Button[2];
	Button[] left = new Button[2];
	Button[] right = new Button[2];
	Button[] part = new Button[2];
	Button[] tool = new Button[2];
	Button[] build = new Button[2];
	Button[] help = new Button[2];
    TextField[] outField = new TextField[2];
    /**
     * method for enableing all buttons for a player
     * @param playerNumber player that buttons will be enabled
     */
	public void buttonEnable(int playerNumber) {
		up[playerNumber].setDisable(false);
		down[playerNumber].setDisable(false);
		left[playerNumber].setDisable(false);
		right[playerNumber].setDisable(false);
		part[playerNumber].setDisable(false);
		tool[playerNumber].setDisable(false);
		build[playerNumber].setDisable(false);
		help[playerNumber].setDisable(false);
	}
    /**
     * method for disabling all buttons for a player
     * @param playerNumber player that buttons will disable
     */
	public void buttonDisable(int playerNumber) {
		up[playerNumber].setDisable(true);
		down[playerNumber].setDisable(true);
		left[playerNumber].setDisable(true);
		right[playerNumber].setDisable(true);
		part[playerNumber].setDisable(true);
		tool[playerNumber].setDisable(true);
		build[playerNumber].setDisable(true);
		help[playerNumber].setDisable(true);
	}
    /**
     * method for changing the active player and enabling/disabling 
     * buttons accordingly
     */
    public void gameChange() {
        buttonDisable(game.getCurrentPlayer().getPlayerNumber());
        game.switchPlayer();
		buttonEnable(game.getCurrentPlayer().getPlayerNumber());
    }
    /**
     * method that creates and shows interface
     */
    @Override
	public void start(Stage applicationStage) throws Exception {
        // creating game object and starting game
		game = new Game();
        game.InitGame();
        // creating scene and grid pane for interface
		GridPane gridpane = new GridPane();
		Scene scene = new Scene(gridpane);
        // adding blank text fields for formating interface
        Text text = new Text("");
        Text text2 = new Text("");
        // creating textfields and buttons for each player while also storing them
        // in respective array's
        player1 = new Text("Player 1");
		up[0] = new Button("Up");
		down[0] = new Button("Down");
		left[0] = new Button("Left");
		right[0] = new Button("Right");
		part[0] = new Button("Collect Part");
		tool[0] = new Button("Collect Tools");
		build[0] = new Button("Build Machine");
		help[0] = new Button("Help!");
        outField[0] = new TextField("Welcome player one!");
        player2 = new Text("Player 2");
        up[1] = new Button("Up");
		down[1] = new Button("Down");
		left[1] = new Button("Left");
		right[1] = new Button("Right");
		part[1] = new Button("Collect Part");
		tool[1] = new Button("Collect Tools");
		build[1] = new Button("Build Machine");
		help[1] = new Button("Help!");
        outField[1] = new TextField("Welcome player two!");
        // creating restart button
        restartButton = new Button("Restart");
        // formating grid pane
        gridpane.setPadding(new Insets(10, 10, 10, 10));
        gridpane.setHgap(10);
        gridpane.setVgap(10);
        // formating buttons
        up[0].setPrefWidth(120);
        down[0].setPrefWidth(120);
        left[0].setPrefWidth(120);
        right[0].setPrefWidth(120);
        part[0].setPrefWidth(120);
        tool[0].setPrefWidth(120);
        build[0].setPrefWidth(120);
        help[0].setPrefWidth(120);
        up[1].setPrefWidth(120);
        down[1].setPrefWidth(120);
        left[1].setPrefWidth(120);
        right[1].setPrefWidth(120);
        part[1].setPrefWidth(120);
        tool[1].setPrefWidth(120);
        build[1].setPrefWidth(120);
        help[1].setPrefWidth(120);
        restartButton.setPrefWidth(120);
        // making textfields non editable 
		outField[0].setEditable(false);
        outField[1].setEditable(false);
        // adding elements to grid pane
        gridpane.add(player1, 1, 0);
        gridpane.add(text, 1, 1);
		gridpane.add(up[0], 1, 2);
		gridpane.add(left[0], 0, 3);
		gridpane.add(right[0], 2, 3);
		gridpane.add(down[0], 1, 4);
		gridpane.add(part[0], 0, 5);
		gridpane.add(tool[0], 1, 5);
		gridpane.add(build[0], 2, 5);
		gridpane.add(outField[0], 0, 6);
        GridPane.setColumnSpan(outField[0], 3); // formating textfield
		gridpane.add(help[0], 1, 7); 
        gridpane.add(player2, 6, 0);
        gridpane.add(text2, 6, 1);
        gridpane.add(up[1], 6, 2);
		gridpane.add(left[1], 5, 3);
		gridpane.add(right[1], 7, 3);
		gridpane.add(down[1], 6, 4);
		gridpane.add(part[1], 5, 5);
		gridpane.add(tool[1], 6, 5);
		gridpane.add(build[1], 7, 5);
		gridpane.add(outField[1], 5, 6);
        GridPane.setColumnSpan(outField[1], 3); // fromating textfield
		gridpane.add(help[1], 6, 7);
        gridpane.add(restartButton, 4, 8);
        // disabling player 2's buttons for start of game
        buttonDisable(1);
        /**
         * event handler that allows the player to move in the up directiong
         * @author John Rosso
         * @version 1.0
         */
        class upEvent implements EventHandler <ActionEvent> {
            /** handler method that attempts to move the player up and changes 
             * active player
             * @param event button click
             */
            @Override
            public void handle(ActionEvent event) {
                outField[game.getCurrentPlayer().getPlayerNumber()].setText(game.getCurrentPlayer().move(Direction.up));
				gameChange();
            }
        }
        /**
         * event handler that allows the player to move down 
         * @author John Rosso
         * @version 1.0
         */
        class downEvent implements EventHandler<ActionEvent> {
            /**
             * handler method that attempts to move the player down and changes
             * active player
             * @param event button click
             */
            @Override
            public void handle(ActionEvent event) {
                outField[game.getCurrentPlayer().getPlayerNumber()].setText(game.getCurrentPlayer().move(Direction.down));
                gameChange();
            }
        }
        /**
         * event handler that allows the player to move left
         * @auhtor John Rosso
         * @version 1.0
         */
        class leftEvent implements EventHandler<ActionEvent> {
            /**
             * handler method that attempts to move the player left and changes
             * active player
             * @param event button click
             */
            @Override
            public void handle(ActionEvent event) {
                outField[game.getCurrentPlayer().getPlayerNumber()].setText(game.getCurrentPlayer().move(Direction.left));
                gameChange();
            }
        }
        /**
         * event handler that allows the player to move right
         * @author John Rosso
         * @version 1.0
         */
        class rightEvent implements EventHandler<ActionEvent> {
            /**
             * handler method that attempts to move the player right and changes
             * active player
             * @param event button click
             */
            @Override
            public void handle(ActionEvent event) {
                outField[game.getCurrentPlayer().getPlayerNumber()].setText(game.getCurrentPlayer().move(Direction.right));
                gameChange();
            }
        }
        /**
         * event handler that collects a machine part 
         * @author John Rosso
         * @version 1.0
         */
        class partEvent implements EventHandler<ActionEvent> {
            /**
             * handler method that attempts to collect a machine part and
             * changes active player
             * @param event button click
             */
            @Override
            public void handle(ActionEvent event) {
                outField[game.getCurrentPlayer().getPlayerNumber()].setText(game.getCurrentPlayer().collectPart());
                gameChange();
            }
        }
        /**
         * event handler that collects tools for player
         * @author John Rosso
         * @verson 1.0
         */
        class toolEvent implements EventHandler<ActionEvent> {
            /**
             * handler method that attempts to collect tools and changes 
             * active player
             * @param event button click
             */
            @Override
            public void handle(ActionEvent event) {
                outField[game.getCurrentPlayer().getPlayerNumber()].setText(game.getCurrentPlayer().collectTools());
                gameChange();
            }
        }
        /**
         * event handler that builds machine if all win conditions are met
         * @author John Rosso
         * @version 1.0
         */
        class buildEvent implements EventHandler<ActionEvent> {
            /** 
             * handler method that checks if all win conditions are met and
             * responds accordingly 
             * @param event button click
             */ 
            @Override
            public void handle(ActionEvent event) {
                // checking if player has won if so buttons are disabled and 
                // messages are displayed to winner and loser
                if (game.getCurrentPlayer().build() == "You have built the portal, you may now escape!") {
                    outField[game.getCurrentPlayer().getPlayerNumber()].setText(game.getCurrentPlayer().build());
                    game.switchPlayer();
                    outField[game.getCurrentPlayer().getPlayerNumber()].setText("You are left behind and trapped forever!");
                    buttonDisable(0);
                    buttonDisable(1);
                }
                // if win condistions are not met reason is displayed to player
                else {
                    outField[game.getCurrentPlayer().getPlayerNumber()].setText(game.getCurrentPlayer().build());
                    buttonDisable(game.getCurrentPlayer().getPlayerNumber());
                    game.switchPlayer();
				    buttonEnable(game.getCurrentPlayer().getPlayerNumber());
                } 
            }
        }
        /**
         * event handler that displays players current room
         * @author John Rosso
         * @version 1.0
         */
        class helpEvent implements EventHandler<ActionEvent> {
            /**
             * handler method that displays players current room
             * @param event button click
             */
            @Override
            public void handle(ActionEvent event) {
                outField[game.getCurrentPlayer().getPlayerNumber()].setText(game.getCurrentPlayer().getCurrentRoom().helpMessage());
            }
        }
        /**
         * event handler that restarts game
         * @author John Rosso
         * @version 1.0
         */
        class restartEvent implements EventHandler <ActionEvent> {
            /**
             * handler method that restarts game by resetting players setting
             * textfield text and enabling/disabling buttons accordlying
             * @param event button click
             */
            @Override
            public void handle(ActionEvent event) {
                game.InitGame();
                outField[0].setText("Welcome player one!");
                outField[1].setText("Welcome player two!");
                buttonDisable(1);
                buttonEnable(0);
            }
        }
        // attaching buttons to event handlers
		up[0].setOnAction(new upEvent());
		down[0].setOnAction(new downEvent());
		left[0].setOnAction(new leftEvent());
		right[0].setOnAction(new rightEvent());
		part[0].setOnAction(new partEvent());
		tool[0].setOnAction(new toolEvent());
		build[0].setOnAction(new buildEvent());
		help[0].setOnAction(new helpEvent());
        up[1].setOnAction(new upEvent());
		down[1].setOnAction(new downEvent());
		left[1].setOnAction(new leftEvent());
		right[1].setOnAction(new rightEvent());
		part[1].setOnAction(new partEvent());
		tool[1].setOnAction(new toolEvent());
		build[1].setOnAction(new buildEvent());
		help[1].setOnAction(new helpEvent());
		restartButton.setOnAction(new restartEvent());
        // setting title and displaying interface
        applicationStage.setScene(scene);
		applicationStage.setTitle("Maze");
		applicationStage.show();
    }
    /**
     * main method that launches interface
     * @param args method arguments
     */
    public static void main(String [] args) {
		launch(args);  
	}	
}
package Forms;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

// TODO: Apenas testando JavaFX.
//  Eventualmente se tornará uma UI para o projeto.

public class MainFX extends Application {

    Label lblJogador = new Label("Guerreiro Nero");
    Label lblInimigo = new Label("Caranthir");

    ProgressBar hpJogador = new ProgressBar(1.0);
    ProgressBar hpInimigo = new ProgressBar(1.0);

    TextArea log = new TextArea();

    Button btnAtacar = new Button("Atacar");
    Button btnSkill = new Button("Habilidades");
    Button btnDefender = new Button("Defender");
    Button btnTrocar = new Button("Trocar");
    Button btnFugir = new Button("Fugir");

    @Override
    public void start(Stage stage){

        log.setEditable(false);

        VBox jogadorBox = new VBox(lblJogador, hpJogador);
        VBox inimigoBox = new VBox(lblInimigo, hpInimigo);
        HBox topo = new HBox(jogadorBox, inimigoBox);

        log.setPrefHeight(200);

        HBox botoes1 = new HBox(10, btnAtacar, btnSkill, btnDefender);
        HBox botoes2 = new HBox(10, btnTrocar, btnFugir);
        VBox root = new VBox(15, topo, log, botoes1, botoes2);

        Scene scene = new Scene(root, 500, 400);

        stage.setScene(scene);
        stage.setTitle("Batalha RPG");
        stage.show();

        configurarAcoes();
    }

    private void configurarAcoes() {

        btnAtacar.setOnAction(e -> {
            log.appendText("Você atacou o inimigo!\n");

            // exemplo visual
            hpInimigo.setProgress(hpInimigo.getProgress() - 0.2);
        });
        btnSkill.setOnAction(e -> {
            log.appendText("Usou habilidade!\n");
        });
        btnDefender.setOnAction(e -> {
            log.appendText("Defendendo...\n");
        });
        btnTrocar.setOnAction(e -> {
            log.appendText("Trocou de personagem!\n");
        });
        btnFugir.setOnAction(e -> {
            log.appendText("Você fugiu!\n");
        });
    }
    public static void main(String[] args) {
        launch();
    }
}

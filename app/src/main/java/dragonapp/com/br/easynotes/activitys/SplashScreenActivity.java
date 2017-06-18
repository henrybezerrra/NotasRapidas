package dragonapp.com.br.easynotes.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import dragonapp.com.br.easynotes.R;

/**
 * Created by Murilo Henrique on 18/06/2017.
 */

public class SplashScreenActivity extends AppCompatActivity {


    /* AndroidManifest não pode ser comentado assim o comentario passa para aqui.
    * Theme.AppCompat.NoActionBar vai remover a toolbar da tela
    * assim a imagem passa para a tela inteira, mas a Status Bar continura a ser
    * mostrada na tela.
    */

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);// chama desejado xml
        //Handler é uma classe do Android
        Handler handle = new Handler();//internamente, trabalha com Threads inserindo um delay
        //Runnable é interface que é justamente a Thread que será executada após o tempo de delay.
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                mostrarTelaPrincipal();
            }
            //tempo de delay é em millisegundos.
        }, 2000);
    }
    private void mostrarTelaPrincipal() {
        Intent intent = new Intent(SplashScreenActivity.this, PrincipalActivity.class);
        startActivity(intent);
        finish();
    }
}

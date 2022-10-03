package com.example.a03_ejercicio2devolver;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a03_ejercicio2devolver.modelos.Coche;

public class MainActivity extends AppCompatActivity {

    private TextView lblCoche;
    private TextView lblMoto;
    private TextView lblBici;
    private Button btnCoche;
    private Button btnMoto;
    private Button btnBici;

    // Constantes de Identificación de Actividades
    private final int coche = 1;
    private final int moto = 2;
    private final int bici = 3;

    // ActivityResultLaunchers
    private ActivityResultLauncher<Intent> launcherDirecciones;
    private ActivityResultLauncher<Intent> launcherCamiones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        visualizaVistas();
        iniciarLaunchers();
    }

    private void iniciarLaunchers() {
        // RegisterForActivityResult
        // 1. Modo en que se lanza el Intent
        // 2. Acciones a realizar DESPUÉS de que se cierre el Intent.
        launcherDirecciones = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK){
                    if (result.getData() != null){
                        Bundle bundle = result.getData().getExtras();
                        Coche dir = (Coche) bundle.getSerializable("DIR");
                        // LÓGICA para trabajar con la dirección
                        Toast.makeText(MainActivity.this, dir.toString(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Acción cancelada", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void visualizaVistas() {
        lblCoche = findViewById(R.id.lblCochesMain);
        lblMoto = findViewById(R.id.lblMotoMain);
        lblBici = findViewById(R.id.lblBiciMain);
        btnCoche = findViewById(R.id.btnCocheMain);
        btnMoto = findViewById(R.id.btnMotoMain);
        btnBici = findViewById(R.id.btnBiciMain);
    }
}
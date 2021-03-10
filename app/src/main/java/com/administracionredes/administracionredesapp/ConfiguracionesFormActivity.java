package com.administracionredes.administracionredesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class ConfiguracionesFormActivity extends AppCompatActivity /*implements Status*/ {
    private TextInputLayout textInputLayout_lugar;
    private TextInputLayout textInputLayout_nodos;
    private TextInputLayout textInputLayout_switches;
    private TextInputLayout textInputLayout_topologia;
    private TextInputLayout textInputLayout_dirred;
    private TextInputLayout textInputLayout_cdir;
    private TextInputLayout textInputLayout_host;
    private TextInputLayout textInputLayout_observaciones;
    private MaterialButton materialButton_guardar_configuracion;
    //Configuraciones configuraciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuraciones_form);
        setTitle("Configuración");
        textInputLayout_lugar = findViewById(R.id.textInputLayout_lugar);
        textInputLayout_nodos = findViewById(R.id.textInputLayout_cantidad_de_nodos);
        textInputLayout_switches = findViewById(R.id.textInputLayout_cantidad_de_switch);
        textInputLayout_topologia = findViewById(R.id.textInputLayout_topologia);
        textInputLayout_dirred = findViewById(R.id.textInputLayout_direccion_de_red);
        textInputLayout_cdir = findViewById(R.id.textInputLayout_cdir);
        textInputLayout_host = findViewById(R.id.textInputLayout_rango_de_host);
        textInputLayout_observaciones = findViewById(R.id.textInputLayout_observaciones);
        materialButton_guardar_configuracion = findViewById(R.id.button_guardar_configuracion);
        getDatos();
        materialButton_guardar_configuracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lugarS = textInputLayout_lugar.getEditText().getText().toString();
                String nodosS = textInputLayout_nodos.getEditText().getText().toString();
                String switchesS = textInputLayout_switches.getEditText().getText().toString();
                String topologiaS = textInputLayout_topologia.getEditText().getText().toString();
                String dirredS = textInputLayout_dirred.getEditText().getText().toString();
                String cdirS = textInputLayout_cdir.getEditText().getText().toString();
                String hostS = textInputLayout_host.getEditText().getText().toString();
                String observacionesS = textInputLayout_observaciones.getEditText().getText().toString();
                if (evaluarDatos(lugarS, textInputLayout_lugar) && evaluarDatos(nodosS, textInputLayout_nodos) && evaluarDatos(switchesS, textInputLayout_switches) && evaluarDatos(topologiaS, textInputLayout_topologia) && evaluarDatos(dirredS, textInputLayout_dirred) && evaluarDatos(cdirS, textInputLayout_cdir) && evaluarDatos(hostS, textInputLayout_host) && evaluarDatos(observacionesS, textInputLayout_observaciones)) {
                    String datos[] = {lugarS, nodosS, switchesS, topologiaS, dirredS, cdirS, hostS, observacionesS};
                    if (getIntent().getBooleanExtra("dato", false)) {
                        //edicion e firebase
                        //new FirebaseHelper().editar(ConfiguracionesFormActivity.this::status, Collections.CONFIGURACION.toString(), configuraciones.getId(), StaticHelper.CONFIGURACIONKEYS, datos);
                    } else {
                        //agregacion en firebase
                        //new FirebaseHelper().add(ConfiguracionesFormActivity.this::status, Collections.CONFIGURACION.toString(), StaticHelper.CONFIGURACIONKEYS, datos);
                    }
                    Snackbar.make(view, "Datos válidos", Snackbar.LENGTH_SHORT).show();
                } else {
                    //Conexión
                    Snackbar.make(view, "Datos inválidos", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean evaluarDatos(String valor, TextInputLayout textInputLayout) {
        if (!valor.equals("")) {
            return true;
        } else {
            textInputLayout.setError("Campo vacío");
            return false;
        }
    }

    public void getDatos() {
        if (getIntent().getBooleanExtra("dato", false)) {
            /*configuraciones = (Configuraciones) getIntent().getSerializableExtra("Localizacion");
            textInputLayout_lugar.getEditText().setText(configuraciones.getLugar());
            textInputLayout_nodos.getEditText().setText(configuraciones.getNodos());
            textInputLayout_switches.getEditText().setText(configuraciones.getSwitches());
            textInputLayout_topologia.getEditText().setText(configuraciones.getTopologia());
            textInputLayout_dirred.getEditText().setText(configuraciones.getRed());
            textInputLayout_cdir.getEditText().setText(configuraciones.getCdir);
            textInputLayout_host.getEditText().setText(configuraciones.getRango_host);
            textInputLayout_observaciones.getEditText().setText(configuraciones.getObservaciones);*/
            materialButton_guardar_configuracion.setText("Editar Configuración");
        }
    }

    /*@Override
    public void status(String mensaje) {
        Toast.makeText(ConfiguracionesFormActivity.this, mensaje, Toast.LENGTH_SHORT).show();
    }*/
}
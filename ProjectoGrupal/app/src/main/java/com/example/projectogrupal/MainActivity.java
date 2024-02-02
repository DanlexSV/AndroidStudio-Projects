package com.example.projectogrupal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.projectogrupal.fragments.Fragment1;
import com.example.projectogrupal.fragments.Fragment2;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener referencia al botón
        Button btnCambiarFragmento = findViewById(R.id.btnCambiarFragmento);
        Button btnCambiarFragmento2 = findViewById(R.id.btnCambiarFragmento2);
        Button btnModificarFragmento = findViewById(R.id.btnModificarFragmento);
        Button btnQuitarFragmento = findViewById(R.id.btnQuitarFragmento);
        ConstraintLayout mainContainer = findViewById(R.id.main_container);


        // Configurar el clic del fragmento 1
        btnCambiarFragmento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /// Reemplazar el fragmento actual con uno nuevo (puedes cambiar a otro fragmento si lo deseas)
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new Fragment1())
                        .addToBackStack(null)  // Opcional: agregar transacción al historial de retroceso
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();

                // Hacer invisible el main_container
                mainContainer.setVisibility(View.INVISIBLE);
            }
        });


        // Configurar el clic del fragmento 2
        btnCambiarFragmento2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /// Reemplazar el fragmento actual con uno nuevo (puedes cambiar a otro fragmento si lo deseas)
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new Fragment2())
                        .addToBackStack(null)  // Opcional: agregar transacción al historial de retroceso
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();

                // Hacer invisible el main_container
                mainContainer.setVisibility(View.INVISIBLE);
            }
        });

        // Configurar el clic de modificar fragmento
        btnModificarFragmento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para modificar el fragmento
                // Obtener el fragmento actual
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);

                // Verificar si el fragmento actual es Fragment1
                if (fragment instanceof Fragment1) {
                    // Realizar la modificación en Fragment1
                    //((Fragment1) fragment).modificarContenido();
                } else if (fragment instanceof Fragment2) {
                    ((Fragment2) fragment).modificarContenido();
                }
            }
        });

        // Configurar el clic de quitar fragmento
        btnQuitarFragmento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el fragmento actual
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);

                // Verificar si hay un fragmento para quitar
                if (fragment != null) {
                    // Remover el fragmento actual
                    getSupportFragmentManager().beginTransaction().remove(fragment).commit();

                    // Hacer visible el main_container
                    mainContainer.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
package com.example.ejerciciolibre;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    LinearLayout selector, RegisterValorant, LoginValorant, Genshin, Honkai;
    ConstraintLayout GameValorant, GameGenshin;
    Button selectValo, selectHoYo, BRegisterValo, BLoginValo, BGenshin, BHonkai;
    Switch switchValo, switchHoYo;
    EditText newUsername, newPlayerID, newPlayerPassword, newPlayerEmail, oldUsername, oldPlayerID,
            oldPlayerPassword, oldPlayerEmail, GenshinUID, ServerGenshin, GenshinEmail,
            GenshinPassworld, HSRUID, ServerHSR, EmailHSR, PasswordHSR;
    String SnewUsername, SnewPlayerID, SnewPlayerPassword, SnewPlayerEmail, SoldUsername,
            SoldPlayerID, SoldPlayerPassword, SoldPlayerEmail, SGenshinUID, SServerGenshin,
            SGenshinEmail, SGenshinPassworld, SHSRUID, SServerHSR, SEmailHSR, SPasswordHSR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selector = findViewById(R.id.Selector);
        RegisterValorant = findViewById(R.id.RegisterValorant);
        LoginValorant = findViewById(R.id.LoginValorant);
        Genshin = findViewById(R.id.GenshinImpact);
        Honkai = findViewById(R.id.HonkaiStarRail);
        GameValorant = findViewById(R.id.GameValorant);
        GameGenshin = findViewById(R.id.GameGenshin);
        selectValo = findViewById(R.id.BValorant);
        selectHoYo = findViewById(R.id.BHoYoVerse);
        BRegisterValo = findViewById(R.id.RegisterComplete);
        BLoginValo = findViewById(R.id.loginComplete);
        BGenshin = findViewById(R.id.GenshinComplete);
        BHonkai = findViewById(R.id.HSRComplete);
        switchValo = findViewById(R.id.switchVal);
        switchHoYo = findViewById(R.id.switchHoYo);
        newUsername = findViewById(R.id.NewUsername);
        newPlayerID = findViewById(R.id.NewPlayerID);
        newPlayerPassword = findViewById(R.id.PasswordNewP);
        newPlayerEmail = findViewById(R.id.EmailNewPlayer);
        oldUsername = findViewById(R.id.OldUsername);
        oldPlayerID = findViewById(R.id.oldPlayerID);
        oldPlayerPassword = findViewById(R.id.passwordOldP);
        oldPlayerEmail = findViewById(R.id.emailOldPlayer);
        GenshinUID = findViewById(R.id.GenshinUID);
        ServerGenshin = findViewById(R.id.ServerGenshin);
        GenshinEmail = findViewById(R.id.GenshinEmail);
        GenshinPassworld = findViewById(R.id.passwordGenshin);
        HSRUID = findViewById(R.id.HSR_UID);
        ServerHSR = findViewById(R.id.serverHSR);
        EmailHSR = findViewById(R.id.emailHSR);
        PasswordHSR = findViewById(R.id.passwordHSR);

        GameValorant.setVisibility(View.INVISIBLE);
        GameGenshin.setVisibility(View.INVISIBLE);

        selectValo.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                selector.setVisibility(View.INVISIBLE);
                GameValorant.setVisibility(View.VISIBLE);
                newUsername.setText("");
                newPlayerID.setText("");
                newPlayerPassword.setText("");
                newPlayerEmail.setText("");
                oldUsername.setText("");
                oldPlayerID.setText("");
                oldPlayerPassword.setText("");
                oldPlayerEmail.setText("");
                BLoginValo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getBaseContext(), "Iniciando Sesion en Valorant", Toast.LENGTH_LONG).show();
                        GameValorant.setVisibility(View.INVISIBLE);
                        selector.setVisibility(View.VISIBLE);
                    }
                });
                BRegisterValo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getBaseContext(), "Registrado en VALORANT con Exito", Toast.LENGTH_LONG).show();
                        GameValorant.setVisibility(View.INVISIBLE);
                        selector.setVisibility(View.VISIBLE);
                    }
                });
            }
        });

        switchValo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    RegisterValorant.setVisibility(View.INVISIBLE);
                    oldUsername.setText("");
                    oldPlayerID.setText("");
                    oldPlayerPassword.setText("");
                    oldPlayerEmail.setText("");
                    selector.setVisibility(View.VISIBLE);
                    LoginValorant.setVisibility(View.VISIBLE);
                    BLoginValo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getBaseContext(), "Iniciando Sesion en Valorant", Toast.LENGTH_LONG).show();
                            GameValorant.setVisibility(View.INVISIBLE);
                            newUsername.setText("");
                            newPlayerID.setText("");
                            newPlayerPassword.setText("");
                            newPlayerEmail.setText("");
                            selector.setVisibility(View.VISIBLE);
                        }
                    });
                } else {
                    RegisterValorant.setVisibility(View.VISIBLE);
                    newUsername.setText("");
                    newPlayerID.setText("");
                    newPlayerPassword.setText("");
                    newPlayerEmail.setText("");
                    selector.setVisibility(View.VISIBLE);
                    LoginValorant.setVisibility(View.INVISIBLE);
                    BRegisterValo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getBaseContext(), "Registrado en VALORANT con Exito", Toast.LENGTH_LONG).show();
                            GameValorant.setVisibility(View.INVISIBLE);
                            oldUsername.setText("");
                            oldPlayerID.setText("");
                            oldPlayerPassword.setText("");
                            oldPlayerEmail.setText("");
                            selector.setVisibility(View.VISIBLE);
                        }
                    });
                }
            }
        });

        switchHoYo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Genshin.setVisibility(View.INVISIBLE);
                    GenshinUID.setText("");
                    ServerGenshin.setText("");
                    GenshinEmail.setText("");
                    GenshinPassworld.setText("");
                    Honkai.setVisibility(View.VISIBLE);
                    BHonkai.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getBaseContext(), "Iniciando Sesion en Honkai: Star Rail", Toast.LENGTH_LONG).show();
                            GameGenshin.setVisibility(View.INVISIBLE);
                            HSRUID.setText("");
                            ServerHSR.setText("");
                            EmailHSR.setText("");
                            PasswordHSR.setText("");
                            selector.setVisibility(View.VISIBLE);
                        }
                    });
                } else {
                    Genshin.setVisibility(View.VISIBLE);
                    HSRUID.setText("");
                    ServerHSR.setText("");
                    EmailHSR.setText("");
                    PasswordHSR.setText("");
                    BGenshin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getBaseContext(), "Iniciando Sesion en Genshin Impact", Toast.LENGTH_LONG).show();
                            GameGenshin.setVisibility(View.INVISIBLE);
                            GenshinUID.setText("");
                            ServerGenshin.setText("");
                            GenshinEmail.setText("");
                            GenshinPassworld.setText("");
                            selector.setVisibility(View.VISIBLE);
                        }
                    });
                }
            }
        });

        selectHoYo.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                selector.setVisibility(View.INVISIBLE);
                GameGenshin.setVisibility(View.VISIBLE);
                HSRUID.setText("");
                ServerHSR.setText("");
                EmailHSR.setText("");
                PasswordHSR.setText("");
                GenshinUID.setText("");
                ServerGenshin.setText("");
                GenshinEmail.setText("");
                GenshinPassworld.setText("");
                BHonkai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getBaseContext(), "Iniciando Sesion en Honkai: Star Rail", Toast.LENGTH_LONG).show();
                        GameGenshin.setVisibility(View.INVISIBLE);
                        selector.setVisibility(View.VISIBLE);
                    }
                });
                BGenshin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getBaseContext(), "Iniciando Sesion en Genshin Impact", Toast.LENGTH_LONG).show();
                        GameGenshin.setVisibility(View.INVISIBLE);
                        selector.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
    }
}
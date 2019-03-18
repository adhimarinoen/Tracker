package com.example.adhimarino.tracker;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class jalur3b extends AppCompatActivity {
    private EditText nEmail, nPassword;
    private Button nMasuk, nDaftar;

    private FirebaseAuth nAuth;
    private FirebaseAuth.AuthStateListener FirebaseAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jalur3b);

        nAuth =  FirebaseAuth.getInstance();
        FirebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user!=null){
                    Intent intent = new Intent(jalur3b.this, MapsActivity3.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };


        nEmail = findViewById(R.id.Email2);
        nPassword = findViewById(R.id.Password2);
        nDaftar = findViewById(R.id.daftarr);
        nMasuk = findViewById (R.id.asup);

        nDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = nEmail.getText().toString();
                final String password = nPassword.getText().toString();
                nAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(jalur3b.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(jalur3b.this, "Pendaftaran Gagal !", Toast.LENGTH_SHORT).show();
                        }else{
                            String user_id = nAuth.getCurrentUser().getUid();
                            DatabaseReference current_Driver_db = FirebaseDatabase.getInstance().getReference().child("Driver").child("Driver3B").child(user_id);
                            current_Driver_db.setValue(true);
                        }
                    }
                });
            }
        });


        nMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = nEmail.getText().toString();
                final String password = nPassword.getText().toString();
                nAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(jalur3b.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(jalur3b.this, "Gagal Masuk !", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        nAuth.addAuthStateListener(FirebaseAuthListener);
    }
    @Override
    protected void onStop() {
        super.onStop();
        nAuth.removeAuthStateListener(FirebaseAuthListener);
    }
}

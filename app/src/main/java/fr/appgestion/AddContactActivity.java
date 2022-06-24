package fr.appgestion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddContactActivity extends AppCompatActivity {

    TextInputEditText mNom;
    TextInputEditText mTel;
    TextInputEditText mEmail;
    Button btnSave;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        getSupportActionBar().setTitle("Ajout contact");

        db=FirebaseFirestore.getInstance();

        mNom=findViewById(R.id.input_nom);
        mTel=findViewById(R.id.input_tel);
        mEmail=findViewById(R.id.input_email);
        btnSave=findViewById(R.id.btn_save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String nom=mNom.getText().toString();
                final String tel=mTel.getText().toString();
                final String email=mEmail.getText().toString();

                CollectionReference colRef=db.collection("Contact");

                Contact contact=new Contact("Manuel","+3367373737","manuel@sfr.fr");

                colRef.document().set(contact).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if ((task.isSuccessful())){
                            Toast.makeText(getApplicationContext(),"Données ajouté avec succès", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Erreur", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
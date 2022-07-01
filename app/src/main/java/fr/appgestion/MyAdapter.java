package fr.appgestion;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
public class MyAdapter extends FirestoreRecyclerAdapter<Contact, MyAdapter.ContactVH>

{
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public MyAdapter(@NonNull FirestoreRecyclerOptions<Contact> options) { super(options);}

    @Override
    protected void onBindViewHolder(@NonNull final ContactVH holder, @SuppressLint("RecyclerView") final int position, @NonNull Contact model)
    {
        holder.mNom.setText(model.getNom());
        holder.mTel.setText(model.getTel());
        holder.mCom.setText(model.getCom());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.mNom.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                        .setExpanded(true,1100)
                        .create();

                View myview=dialogPlus.getHolderView();

                EditText nom=myview.findViewById(R.id.uname);
                EditText tel=myview.findViewById(R.id.utel);
                EditText com=myview.findViewById(R.id.ucom);
                Button submit=myview.findViewById(R.id.usubmit);

                nom.setText(model.getNom());
                tel.setText(model.getTel());
                com.setText(model.getCom());

                dialogPlus.show();

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map =new HashMap<>();
                         map.put("nom",nom.getText().toString());
                        map.put("tel",tel.getText().toString());
                      map.put("com",com.getText().toString());

                        db.collection("Contact")
                                .document(docId)
                                update("nom", nom.getText().toString())
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialogPlus.dismiss();

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialogPlus.dismiss();
                                    }
                                });

                    }


                });
            }

        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder builder = new AlertDialog.Builder(holder.mNom.getContext());
                builder.setTitle("Delete Panel");
                builder.setMessage("Delete ... ?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db.delete().addOnSuccessListener(new OnSuccessListener < Void > () {
                            @Override
                            public void onSuccess(Void aVoid) {


                            }
                        });
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });


    }

    @NonNull
    public ContactVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact,parent,false);
        return new ContactVH(view);
    }


    class ContactVH extends RecyclerView.ViewHolder
    {

        final TextView mNom;
        final TextView mTel;
        final TextView mCom;

        final ImageView edit;
        final ImageView delete;

        public ContactVH(@NonNull View itemView)
        {
            super(itemView);
            mNom=itemView.findViewById(R.id.nametext);
            mTel=itemView.findViewById(R.id.teltext);
            mCom=itemView.findViewById(R.id.comtext);
            edit=(ImageView)itemView.findViewById(R.id.editicon);
            delete=(ImageView)itemView.findViewById(R.id.deleteicon);
        }
    }
}
package fr.appgestion;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends FirestoreRecyclerAdapter<Contact, MyAdapter.ContactVH>

{
    FirebaseFirestore db;

    public MyAdapter(@NonNull FirestoreRecyclerOptions<Contact> options) { super(options);}

    @Override
    protected void onBindViewHolder(@NonNull ContactVH holder, int position, @NonNull Contact model) {
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
                     Map<String,Object> map=new HashMap<>();
                     map.put("Nom",nom.getText().toString());
                     map.put("Tel",tel.getText().toString());
                     map.put("Commentaire",com.getText().toString());



                 }
             });

         }
     });


    }

    @NonNull
    @Override
    public ContactVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact,parent,false);
        return new ContactVH(layout);
    }


    class ContactVH extends RecyclerView.ViewHolder {

        TextView mNom;
        TextView mTel;
        TextView mCom;

        ImageView edit,delete;

        public ContactVH(View itemView) {
            super(itemView);
            mNom=itemView.findViewById(R.id.nametext);
            mTel=itemView.findViewById(R.id.teltext);
            mCom=itemView.findViewById(R.id.comtext);
            edit=(ImageView)itemView.findViewById(R.id.editicon);
            delete=(ImageView)itemView.findViewById(R.id.deleteicon);
        }
    }
}



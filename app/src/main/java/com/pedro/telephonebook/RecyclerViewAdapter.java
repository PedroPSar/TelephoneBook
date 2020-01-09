package com.pedro.telephonebook;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pedro.telephonebook.Models.Contact;

import java.io.File;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Contact> contacts;
    private Context context;
    public static final String TEST =  "debugando_img";

    public RecyclerViewAdapter(List<Contact> contacts, Context context){
        this.contacts = contacts;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_recycler_view_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.avatar.setImageURI(Uri.parse(contacts.get(position).getAvatar()));

        holder.txtName.setText(contacts.get(position).getName());
        holder.txtNickName.setText(contacts.get(position).getNickname());
        holder.txtTel.setText(contacts.get(position).getTel());
        holder.txtEmail.setText(contacts.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView avatar;
        TextView txtName;
        TextView txtNickName;
        TextView txtTel;
        TextView txtEmail;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            avatar = itemView.findViewById(R.id.avatar);
            txtName = itemView.findViewById(R.id.textName);
            txtNickName = itemView.findViewById(R.id.textNickName);
            txtTel = itemView.findViewById(R.id.textTelephone);
            txtEmail = itemView.findViewById(R.id.textEmail);

        }
    }
}

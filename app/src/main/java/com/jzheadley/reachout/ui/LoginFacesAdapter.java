package com.jzheadley.reachout.ui;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jzheadley.reachout.R;
import com.jzheadley.reachout.models.dataobjects.Person;

import java.util.List;

public class LoginFacesAdapter extends RecyclerView.Adapter<LoginFacesAdapter.ViewHolder> {
    private List<Person> people;

    public LoginFacesAdapter(List<Person> people) {
        this.people = people;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
            .from(parent.getContext())
            .inflate(R.layout.item_login_face, parent, false);
        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Person person = people.get(position);
        holder.nameText.setText(person.getName());
        Glide.with(holder.itemView.getContext())
            .load(person.getProfile_picture() + ".png")
            .into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PatternLoginActivity.class);
                intent.putExtra("PossibleUser", person);
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameText;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.persons_face_img);
            nameText = (TextView) itemView.findViewById(R.id.persons_name_text);
        }
    }
}

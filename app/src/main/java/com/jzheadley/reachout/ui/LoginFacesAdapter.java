package com.jzheadley.reachout.ui;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private static final String TAG = "LoginFacesAdapter";
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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Person person = people.get(position);
        holder.nameText.setText(person.getName());
        Glide.with(holder.itemView.getContext())
            .load(person.getProfile_picture() + ".png")
            .into(holder.imageView);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: I got clicked");
                Intent intent = new Intent(holder.itemView.getContext(), PatternLoginActivity.class);
                intent.putExtra("PossibleUser", person);
                holder.itemView.getContext().startActivity(intent);
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
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_login);
            imageView = (ImageView) itemView.findViewById(R.id.persons_face_img);
            nameText = (TextView) itemView.findViewById(R.id.persons_name_text);
        }
    }
}

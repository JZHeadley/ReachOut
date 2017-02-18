package com.jzheadley.reachout.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.jzheadley.reachout.R;
import com.jzheadley.reachout.models.dataobjects.Proposal;

import java.util.ArrayList;

public class MyProposalsAdapter extends RecyclerView.Adapter<MyProposalsAdapter.MyProposalViewHolder> {

    private static final String TAG = "MyProposalsAdapter";
    private ArrayList<Proposal> proposals;

    public MyProposalsAdapter(ArrayList<Proposal> proposals) {
        this.proposals = new ArrayList<>(proposals);
    }

    @Override
    public MyProposalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
            .from(parent.getContext())
            .inflate(R.layout.layout_proposal_card, parent, false);
        return new MyProposalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyProposalViewHolder holder, int position) {
        Proposal proposal = proposals.get(position);
        holder.proposalName.setText(proposal.getBusinessDescription());
        if (proposal.getPictures().size() > 1) {
            ArrayList<String> pictures = new ArrayList<>(proposal.getPictures());
            pictures.remove(0);
            Glide.with(holder.itemView.getContext()).load(pictures.get(0))
                .crossFade()
                .fitCenter()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.drawable.image_placeholder)
                .into(new GlideDrawableImageViewTarget(holder.proposalImage));
        }
    }

    @Override
    public int getItemCount() {
        return proposals.size();
    }

    static class MyProposalViewHolder extends RecyclerView.ViewHolder {
        TextView proposalName;
        ImageView proposalImage;

        public MyProposalViewHolder(View itemView) {
            super(itemView);
            proposalName = (TextView) itemView.findViewById(R.id.proposal_name);
            proposalImage = (ImageView) itemView.findViewById(R.id.proposal_image);
        }
    }
}

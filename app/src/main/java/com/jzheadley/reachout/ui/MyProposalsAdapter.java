package com.jzheadley.reachout.ui;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
    public void onBindViewHolder(final MyProposalViewHolder holder, int position) {
        final Proposal proposal = proposals.get(position);
        if (proposal != null) {
            holder.proposalName.setText(proposal.getBusinessDescription());
            holder.itemRequested.setText(proposal.getPurchaseDescription());
            // holder.amountToBeBorrowed.setText(proposal.getAmountBorrowed());
            holder.progressBar.setMax(850);
            holder.progressBar.setProgress(Integer.parseInt(proposal.getCreditScore()));

            if (Integer.parseInt(proposal.getCreditScore()) > 560) {
                holder.progressStatus.setText("Low Risk");
            } else if (Integer.parseInt(proposal.getCreditScore()) > 280) {
                holder.progressStatus.setText("Medium Risk");
            } else {
                holder.progressStatus.setText("High Risk");
            }

            if (proposal.getPictures().size() > 1) {
                ArrayList<String> pictures = new ArrayList<>(proposal.getPictures());
                pictures.remove(0);
                Log.d(TAG, "onBindViewHolder: " + pictures.toString());
                String url = pictures.get(0);
                if (pictures.get(0).length() > 0)
                    url += ".png";
                else {
                    url = holder.itemView.getContext().getResources().getDrawable(R.drawable.image_placeholder).toString();
                }
                Glide.with(holder.itemView.getContext())
                        .load(url)
                        .crossFade()
                        .fitCenter()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.image_placeholder)
                        .into(new GlideDrawableImageViewTarget(holder.proposalImage));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent proposalIntent = null;

                        if (InvestorActivity.returnIsInvestor()) {
                            proposalIntent = new Intent(v.getContext(), ViewInvestorProposalActivity.class);
                            proposalIntent.putExtra("singleProposal", proposal);
                            holder.itemView.getContext().startActivity(proposalIntent);
                        } else {
                            proposalIntent = new Intent(v.getContext(), ViewProposalActivity.class);
                            proposalIntent.putExtra("singleProposal", proposal);
                            holder.itemView.getContext().startActivity(proposalIntent);
                        }

                    /*
                    if (proposal.getState() == 3) {
                        cashIntent = new Intent(v.getContext(), CashActivity.class);
                    } else if (proposal.getState() == 4) {
                        // cashIntent = new Intent(v.getContext(), RepayActivity.class);
                    } else {
                        Log.d(TAG, "onClick: Your Suspicions were correct");
                        cashIntent = new Intent(v.getContext(), CashActivity.class);
                    }


                    cashIntent.putExtra("proposal", proposal);
                    v.getContext().startActivity(cashIntent);
                    */
                    }
                });
            }
        }
   }

    @Override
    public int getItemCount() {
        return proposals.size();
    }

    static class MyProposalViewHolder extends RecyclerView.ViewHolder {
        TextView proposalName;
        ImageView proposalImage;
        // TextView amountToBeBorrowed;
        ProgressBar progressBar;
        TextView itemRequested;
        TextView progressStatus;

        public MyProposalViewHolder(View itemView) {
            super(itemView);
            proposalName = (TextView) itemView.findViewById(R.id.proposal_name);
            proposalImage = (ImageView) itemView.findViewById(R.id.proposal_image);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressbar);
            // amountToBeBorrowed = (TextView) itemView.findViewById(R.id.proposal_amount_to_borrow);
            itemRequested = (TextView) itemView.findViewById(R.id.proposal_item_requested);
            progressStatus = (TextView) itemView.findViewById(R.id.progressStatus);

        }
    }
}

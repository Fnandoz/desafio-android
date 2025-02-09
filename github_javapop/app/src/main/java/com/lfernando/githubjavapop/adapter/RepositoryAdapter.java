package com.lfernando.githubjavapop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lfernando.githubjavapop.R;
import com.lfernando.githubjavapop.activity.RepositoryActivity;
import com.lfernando.githubjavapop.model.Repo;
import com.lfernando.githubjavapop.viewHolder.RepositoryViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class RepositoryAdapter extends RecyclerView.Adapter {
    private List<Repo> repoList;
    private Context context;

    public RepositoryAdapter(List<Repo> repoList, Context context) {
        this.repoList = repoList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.repository_item, viewGroup, false);
        RepositoryViewHolder holder = new RepositoryViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        RepositoryViewHolder holder = (RepositoryViewHolder) viewHolder;
        final Repo repo = repoList.get(i);
        holder.repoName.setText(repo.getName());
        holder.repoDesc.setText(repo.getDescription());
        holder.forksCount.setText(String.valueOf(repo.getForks()));
        holder.starsCount.setText(String.valueOf(repo.getStars()));
        holder.name.setText(repo.getOwner().getLogin());
        holder.userName.setText(repo.getOwner().getName());
        Picasso.get().load(repo.getOwner().getAvatarUrl()).transform(new CropCircleTransformation()).into(holder.avatar);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RepositoryActivity.class);
                intent.putExtra("owner", repo.getOwner().getLogin());
                intent.putExtra("repository", repo.getName());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return repoList.size();
    }
}

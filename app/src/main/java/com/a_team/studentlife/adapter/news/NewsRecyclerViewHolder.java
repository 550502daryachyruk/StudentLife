package com.a_team.studentlife.adapter.news;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.a_team.studentlife.R;
import com.a_team.studentlife.card_view_filling.NewsPost;

public class NewsRecyclerViewHolder extends RecyclerView.ViewHolder {
    private TextView participants;
    private TextView likes;
    private TextView userName;
    private TextView postText;
    private TextView postDate;
    private TextView postTime;
    private ImageView postedPhoto;
    private ImageView userPhoto;

    public NewsRecyclerViewHolder(View itemView) {
        super(itemView);
        participants = (TextView) itemView.findViewById(R.id.participantsStatistic);
        likes = (TextView) itemView.findViewById(R.id.likesStatistic);
        userName = (TextView) itemView.findViewById(R.id.userNewsNickname);
        postText = (TextView) itemView.findViewById(R.id.postedText);
        postedPhoto = (ImageView) itemView.findViewById(R.id.posted_photo_image_view);
        userPhoto = (ImageView) itemView.findViewById(R.id.news_profile_photo);
        postDate = (TextView) itemView.findViewById(R.id.date_of_event);
        postTime = (TextView) itemView.findViewById(R.id.time_of_event);
    }

    @SuppressLint("SetTextI18n")
    public void bind(NewsPost newsPost) {
        participants.setText(newsPost.getParticipants().toString());
        likes.setText(newsPost.getLikes().toString());
        userName.setText(newsPost.getLeagueName());
        postText.setText(newsPost.getPostText());
        postTime.setText(postTime.getText() + " " + newsPost.getPostTime());
        postDate.setText(postDate.getText() + " " + newsPost.getPostDate());
        //postedPhoto.setImageBitmap(BitmapFactory.decodeResource(itemView.getResources(), newsPost.getPostImageId()));
        //userPhoto.setImageBitmap(BitmapFactory.decodeResource(itemView.getResources(), newsPost.getUserImageId()));
    }
}
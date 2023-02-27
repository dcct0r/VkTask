package com.example.vktask;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ContactsUsersAdapter extends RecyclerView.Adapter<ContactsUsersAdapter.UserHolder> {

    private List<Cusr> contactUserList;

    ContactsUsersAdapter(List<Cusr> contactUserList) {
        this.contactUserList = contactUserList;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list, parent, false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        Cusr cusr = contactUserList.get(position);
        holder.bind(cusr);
    }

    @Override
    public int getItemCount() {
        return contactUserList.size();
    }

    class UserHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView name;
        private TextView tag;
        
        public UserHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.userPhoto);
            name = itemView.findViewById(R.id.userName);
            tag = itemView.findViewById(R.id.tagUser);
        }
        public void bind(Cusr contactUser) {
            imageView.setImageResource(contactUser.userIcon);
            name.setText(String.valueOf(contactUser.nickname));
            tag.setText("#" + String.valueOf(contactUser.userTag));
        }
    }
}

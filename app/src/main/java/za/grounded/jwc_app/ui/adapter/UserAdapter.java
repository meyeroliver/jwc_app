package za.grounded.jwc_app.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import za.grounded.jwc_app.R;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder>{

    private List<String> stringList = new ArrayList<>();

    public UserAdapter(List<String> stringList) {
        this.stringList = stringList;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.select_user_item, parent, false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        String user =  this.stringList.get(position);
        holder.usernameText.setText(user);
    }

    @Override
    public int getItemCount() {
        return this.stringList.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder {

        private TextView usernameText;

        UserHolder(@NonNull View itemView) {
            super(itemView);
            this.init(itemView);
        }

        private void init(View view) {
            this.usernameText = view.findViewById(R.id.user_item);
        }
    }
}

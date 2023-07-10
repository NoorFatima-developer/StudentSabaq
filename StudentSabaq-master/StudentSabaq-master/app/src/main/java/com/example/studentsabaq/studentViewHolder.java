package com.example.studentsabaq;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class studentViewHolder extends RecyclerView.Adapter<studentViewHolder.MyVH> {

    private List<Student> studentList;
    private Context context;


    public studentViewHolder(List<Student> studentList, Context context) {
        this.studentList = studentList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_student, parent, false);
        return new MyVH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyVH holder, int position) {
        Student student = studentList.get(position);
        holder.textViewFriendName.setText(student.getName());
        holder.agetextview.setText(String.valueOf(student.getAge()));
        holder.classtextview.setText(String.valueOf(student.getCls()));

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, MainActivity3.class);
            intent.putExtra("studentId", student.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public static class MyVH extends RecyclerView.ViewHolder {
        ImageView imageViewFriend;
        TextView textViewFriendName;
        TextView classtextview;
        TextView agetextview;


        public MyVH(@NonNull View itemView) {
            super(itemView);
            imageViewFriend = itemView.findViewById(R.id.imageViewFriendPicture);
            textViewFriendName = itemView.findViewById(R.id.textViewFriendName);
            classtextview=itemView.findViewById(R.id.textViewDate);
            agetextview= itemView.findViewById(R.id.textViewCity);

        }
    }
}

package com.example.nitinsuranatask2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link posts#newInstance} factory method to
 * create an instance of this fragment.
 */
public class posts extends Fragment {
    private RecyclerView recyclerView;
    private List<Upload> uploads;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public posts() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment posts.
     */
    // TODO: Rename and change types and number of parameters
    public static posts newInstance(String param1, String param2) {
        posts fragment = new posts();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uploads=new ArrayList<>();

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        FirebaseDatabase.getInstance().getReference("uploads").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                  //  Toast.makeText(getContext(),dataSnapshot1.child("").getValue()+"",Toast.LENGTH_SHORT).show();

                    uploads.add(new Upload((String) dataSnapshot1.child("title").getValue(),(String) dataSnapshot1.child("description").getValue(),(String) dataSnapshot1.child("url").getValue()));
                }
                homedatasetadpter homedatasetadapter=new homedatasetadpter(getContext(),uploads);
                GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),1);
                gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(homedatasetadapter);
                homedatasetadapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_posts, container, false);
        recyclerView=view.findViewById(R.id.postrecylerview);
        homedatasetadpter homedatasetadapter=new homedatasetadpter(getContext(),uploads);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),1);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(homedatasetadapter);
        homedatasetadapter.notifyDataSetChanged();
        return  view;
    }
}

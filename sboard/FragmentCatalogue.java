package com.info.sboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

public class FragmentCatalogue extends Fragment {
    private RecyclerView rv;
    private ArrayList<Boards> boardsArrayList;
    private BoardAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.catalogue_fragment,container,false);

        rv=view.findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));


        Boards board1 = new Boards(1,"bitezfront","Bitez",getResources().getString(R.string.pink),"565 cm","2500 TL");
        Boards board2 = new Boards(2,"adrasan","Adrasan",getResources().getString(R.string.blue),"589 cm","2500 TL");
        Boards board3 = new Boards(3,"datca","Datça",getResources().getString(R.string.orange),"543 cm","2500 TL");

        boardsArrayList = new ArrayList<>();
        boardsArrayList.add(board1);
        boardsArrayList.add(board2);
        boardsArrayList.add(board3);



        adapter=new BoardAdapter(view.getContext(),boardsArrayList);
        rv.setAdapter(adapter);
        
        return view;
    }
}

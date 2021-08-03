package com.info.sboard;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

public class FragmentFavList extends Fragment {
    private RecyclerView rvFav;
    public ArrayList<Boards> boardsRemoveArrayList;
    private FavListAdapter adapter;

    private Veritabani vt;

    private BoardAdapter boardAdapter;
    private View view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.favlist_fragment,container,false);

        rvFav=view.findViewById(R.id.rvFav);


        rvFav.setHasFixedSize(true);
        rvFav.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));


        vt=new Veritabani(getActivity());
        boardsRemoveArrayList=new FavBoardDAO().allFavBoards(vt);


        adapter=new FavListAdapter(view.getContext(),boardsRemoveArrayList);
        rvFav.setAdapter(adapter);
        return view;
    }
    public class MyAsyncTaskReadBoard extends AsyncTask<String, ProgressDialog,Boolean> {


        @Override
        protected Boolean doInBackground(String... strings) {
            vt=new Veritabani(getActivity());
            adapter=new FavListAdapter(view.getContext(),boardsRemoveArrayList);
            rvFav.setAdapter(adapter);
            boardsRemoveArrayList=new FavBoardDAO().allFavBoards(vt);
            return true;
        }

        @Override
        protected void onProgressUpdate(ProgressDialog... values) {

            AlertDialog.Builder ad=new AlertDialog.Builder(getContext());
            ad.setMessage(getContext().getResources().getString(R.string.plese_wait));
            ad.create().show();

            super.onProgressUpdate(values);
        }
    }
}

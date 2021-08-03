package com.info.sboard;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.CardViewTasarimTutucu>{
    private Context mContext;
    private List<Boards> boardsList;
    private Veritabani vt ;
    private FragmentFavList fl;


    public BoardAdapter(Context mContext, List<Boards> boardsList) {
        this.mContext = mContext;
        this.boardsList = boardsList;
    }

    @NonNull
    @Override
    public CardViewTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tasarim,parent,false);

        return new CardViewTasarimTutucu(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewTasarimTutucu holder, int position) {


        Boards board = boardsList.get(position);

        holder.tvName.setText(board.getBoard_name());
        holder.tvColor.setText(board.getBoard_color());
        holder.tvSize.setText(board.getBoard_size());
        holder.tvPrice.setText(board.getBoard_price());
        holder.ivBoard.setImageResource(mContext.getResources().getIdentifier(board.getBoard_image(),"drawable",mContext.getPackageName()));
        holder.addFavList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new MyAsyncTaskAdddBoard().execute(board);
                Toast.makeText(mContext,board.getBoard_name()+" "+mContext.getResources().getString(R.string.added_to_cart),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return boardsList.size();
    }

    public class CardViewTasarimTutucu extends RecyclerView.ViewHolder{
        public ImageView ivBoard;
        public TextView tvName;
        public TextView tvColor;
        public TextView tvSize;
        public TextView tvPrice;
        public FloatingActionButton addFavList;

        public CardViewTasarimTutucu(@NonNull View itemView) {
            super(itemView);
            ivBoard=itemView.findViewById(R.id.ivBoard);
            tvName=itemView.findViewById(R.id.tvName);
            tvColor=itemView.findViewById(R.id.tvColor);
            tvSize=itemView.findViewById(R.id.tvSize);
            tvPrice=itemView.findViewById(R.id.tvPrice);
            addFavList=itemView.findViewById(R.id.addFavList);
        }
    }
    public class MyAsyncTaskAdddBoard extends AsyncTask<Boards, ProgressDialog,Boolean> {


        @Override
        protected Boolean doInBackground(Boards... boards) {
            Boards board=boards[0];
            vt =new Veritabani(mContext);
            new FavBoardDAO().addBook(vt,board.getBoard_image(),board.getBoard_name(),board.getBoard_color(),board.getBoard_size(),board.getBoard_price());
            Intent intent = new Intent(mContext,PageWithTabs.class);
            mContext.startActivity(intent);

            return true;
        }
        @Override
        protected void onProgressUpdate(ProgressDialog... values) {

            AlertDialog.Builder ad=new AlertDialog.Builder(mContext);
            ad.setMessage(mContext.getResources().getString(R.string.plese_wait));
            ad.create().show();

            super.onProgressUpdate(values);
        }
    }
}

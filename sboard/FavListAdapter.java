package com.info.sboard;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class FavListAdapter extends RecyclerView.Adapter<FavListAdapter.CardViewTasarimTutucu>{
    private Context mContext;
    private List<Boards> boardsRemoveList;
    private Veritabani vt ;
    private FragmentFavList ffl;

    public FavListAdapter(Context mContext, List<Boards> boardsRemoveList) {
        this.mContext = mContext;
        this.boardsRemoveList = boardsRemoveList;
    }

    @NonNull
    @Override
    public CardViewTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_favlist,parent,false);
        return new CardViewTasarimTutucu(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewTasarimTutucu holder, int position) {
        Boards board = boardsRemoveList.get(position);

        holder.tvFavName.setText(board.getBoard_name());
        holder.tvFavColor.setText(board.getBoard_color());
        holder.tvFavSize.setText(board.getBoard_size());
        holder.tvFavPrice.setText(board.getBoard_price());
        holder.ivFavImage.setImageResource(mContext.getResources().getIdentifier(board.getBoard_image(),"drawable",mContext.getPackageName()));
        holder.removeFavList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,board.getBoard_name()+" "+mContext.getResources().getString(R.string.will_be_removed), BaseTransientBottomBar.LENGTH_SHORT)
                        .setAction(mContext.getResources().getString(R.string.yes), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                new MyAsyncTaskDeleteBoard().execute(board);
                                Toast.makeText(mContext,board.getBoard_name()+" "+mContext.getResources().getString(R.string.removed_from_cart),Toast.LENGTH_SHORT).show();
                            }
                        }).show();



            }
        });
    }

    @Override
    public int getItemCount() {
        return boardsRemoveList.size();
    }

    public class CardViewTasarimTutucu extends RecyclerView.ViewHolder{
        public ImageView ivFavImage;
        public TextView tvFavName;
        public TextView tvFavColor;
        public TextView tvFavSize;
        public TextView tvFavPrice;
        public FloatingActionButton removeFavList;

        public CardViewTasarimTutucu(@NonNull View itemView) {
            super(itemView);
            ivFavImage=itemView.findViewById(R.id.ivFavImage);
            tvFavName=itemView.findViewById(R.id.tvFavName);
            tvFavColor=itemView.findViewById(R.id.tvFavColor);
            tvFavSize=itemView.findViewById(R.id.tvFavSize);
            tvFavPrice=itemView.findViewById(R.id.tvFavPrice);
            removeFavList=itemView.findViewById(R.id.removeFavList);
        }
    }
    public class MyAsyncTaskDeleteBoard extends AsyncTask<Boards, ProgressDialog,Boolean> {


        @Override
        protected Boolean doInBackground(Boards... boards) {
            vt =new Veritabani(mContext);
            Boards board=boards[0];
            new FavBoardDAO().deleteBook(vt,board.getBoard_name());
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

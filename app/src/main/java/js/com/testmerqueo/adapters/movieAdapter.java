package js.com.testmerqueo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import js.com.testmerqueo.R;
import js.com.testmerqueo.models.MovieOb;

import java.util.List;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class movieAdapter extends RecyclerView.Adapter<movieAdapter.MovieViewHolder> {

    private List<MovieOb> movies;
    private OnItemClickListener mListener;
    Context contextm;
    private String URL_IMAGE = "https://image.tmdb.org/t/p/w500";


    public interface OnItemClickListener {
        void onItemClick(int position);
        void onCardClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public movieAdapter(List<MovieOb> movies, Context contextm) {
        this.movies = movies;
        this.contextm = contextm;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new MovieViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bind(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        TextView releaseDate;
        TextView title;
      //  TextView rating;
        TextView genres;
        Button addCart;
        LinearLayout item_linear;
        ImageView itemImg;

        public MovieViewHolder(View itemView , final OnItemClickListener listener) {
            super(itemView);
            releaseDate = itemView.findViewById(R.id.item_year);
            title = itemView.findViewById(R.id.item_name);
           // rating = itemView.findViewById(R.id.item_movie_rating);
            genres = itemView.findViewById(R.id.item_genre);
            addCart = itemView.findViewById(R.id.button1);
            item_linear = itemView.findViewById(R.id.item_linear);
            itemImg = itemView.findViewById(R.id.item_image);
            title.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);

            addCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            item_linear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onCardClick(position);
                        }
                    }
                }
            });
        }



        public void bind(MovieOb movie) {
            String url = movie.getPosterPath();
            releaseDate.setText(movie.getReleaseDate().split("-")[0]);
            title.setText(movie.getTitle());
           // rating.setText(String.valueOf(movie.getRating()));
            genres.setText("");

            Glide
                    .with(contextm)
                    .load(URL_IMAGE+url)
                    .centerInside()
                    .placeholder(R.drawable.cart_123)
                    .into(itemImg);

        }

        @Override
        public void onClick(View view) {

        }


    }
}
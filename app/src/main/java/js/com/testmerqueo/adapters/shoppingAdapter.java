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

public class shoppingAdapter extends RecyclerView.Adapter<shoppingAdapter.MovieViewHolder> {

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

    public shoppingAdapter(List<MovieOb> movies, Context contextm) {
        this.movies = movies;
        this.contextm = contextm;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
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

        TextView movie_name,product_rank,movie_quantity;
        ImageView movie_image;

        public MovieViewHolder(View itemView , final OnItemClickListener listener) {
            super(itemView);

            movie_image = itemView.findViewById(R.id.movie_image);
            movie_name = itemView.findViewById(R.id.movie_name);
            product_rank = itemView.findViewById(R.id.product_rank);
            movie_quantity = itemView.findViewById(R.id.movie_quantity);

        }



        public void bind(MovieOb movie) {
            String url = movie.getPosterPath();

            movie_name.setText(movie.getTitle());
            product_rank.setText((int) movie.getRating());
            movie_quantity.setText(movie.getCant());

            Glide
                    .with(contextm)
                    .load(URL_IMAGE+url)
                    .centerInside()
                    .placeholder(R.drawable.cart_123)
                    .into(movie_image);

        }

        @Override
        public void onClick(View view) {

        }


    }
}
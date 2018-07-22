package com.jayzonsolutions.lunchboxfoodmaker.Adaptor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.jayzonsolutions.lunchboxfoodmaker.ApiUtils;
//import com.jayzonsolutions.lunchboxfoodmaker.Fragments.DetailFragment;
import com.jayzonsolutions.lunchboxfoodmaker.R;
import com.jayzonsolutions.lunchboxfoodmaker.Service.ItemClickListener;
import com.jayzonsolutions.lunchboxfoodmaker.model.Foodmaker;
import com.jayzonsolutions.lunchboxfoodmaker.AddDish;
import com.jayzonsolutions.lunchboxfoodmaker.ApiUtils;
import com.jayzonsolutions.lunchboxfoodmaker.model.Foodmaker;
import com.jayzonsolutions.lunchboxfoodmaker.model.FoodmakerDishes;

import java.math.BigDecimal;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyviewHolder> {

    private Context context;
    private List<FoodmakerDishes> movieList;
    private BigDecimal number;

    public RecyclerAdapter(Context context, List<FoodmakerDishes> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    public void setMovieList(List<FoodmakerDishes> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyviewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_layout, parent, false);

      /*  itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "jaidii"+parent.getFocusedChild().toString(), Toast.LENGTH_SHORT).show();
            }
        });*/
        return new MyviewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerAdapter.MyviewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.FoodMakerName.setText(movieList.get(position).getDish().getDishName());
        if(movieList.get(position).getPrice() != null){
            holder.ProductPrice.setText(movieList.get(position).getPrice().toString());
        }else{holder.ProductPrice.setText("not Avaible");}

//        number = new BigDecimal(movieList.get(position).getAverageRatings());
        number = new BigDecimal(3.5);
        if (number.equals(null))
        {
            number = BigDecimal.valueOf(0);
        }
        holder.FoodMakerRating.setRating(number.floatValue());



      //  Glide.with(context).load(movieList.get(position).getImageUrl()).apply(RequestOptions.centerCropTransform()).into(holder.FoodMakerImage);
      /*  Glide.with(context).load(movieList.get(position).
                getFoodmakerImagePath()).
                apply(RequestOptions.
                centerCropTransform().fitCenter().
                diskCacheStrategy(DiskCacheStrategy.ALL)).
                into(holder.FoodMakerImage);*/
        String imagePath = ((movieList.get(position).getImagepath() != null)?movieList.get(position).getImagepath():"");



        Glide.with(context).load(ApiUtils.BASE_URL+(imagePath.substring(21))).
                apply(RequestOptions.
                        centerCropTransform().fitCenter().
                        diskCacheStrategy(DiskCacheStrategy.ALL)).
                into(holder.FoodMakerImage);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Log.d("pos", String.valueOf(pos));
                Toast.makeText(context, "Clicked Position =" + pos, Toast.LENGTH_SHORT).show();
       /*         //INTENT OBJ
                Intent i=new Intent(context,D.class);

                //ADD DATA TO OUR INTENT
                i.putExtra("Name",players[position]);
                i.putExtra("Position",positions[position]);
                i.putExtra("Image",images[position]);

                //START DETAIL ACTIVITY
                context.startActivity(i);

                v.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new DetailFragment()).commit();*/
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                AddDish myFragment = new AddDish();
                myFragment.setId(movieList.get(position).getFoodmakerDishesId());
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, myFragment).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (movieList != null) {
            return movieList.size();
        }
        return 0;

    }

    public class MyviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView FoodMakerName;
        TextView ProductPrice;
        RatingBar FoodMakerRating;
        ImageView FoodMakerImage;


        private ItemClickListener itemClickListener;

        MyviewHolder(View itemView) {
            super(itemView);
            FoodMakerName = itemView.findViewById(R.id.foodmakerName);
            FoodMakerImage = itemView.findViewById(R.id.foodmakerImage);
            ProductPrice = itemView.findViewById(R.id.product_price);
            FoodMakerRating = itemView.findViewById(R.id.foodmakerRating);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(v, getLayoutPosition());
        }

        void setItemClickListener(ItemClickListener ic) {
            this.itemClickListener = ic;

        }
    }
}

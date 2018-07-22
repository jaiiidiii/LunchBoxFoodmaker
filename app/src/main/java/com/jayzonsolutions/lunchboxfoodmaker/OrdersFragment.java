package com.jayzonsolutions.lunchboxfoodmaker;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jayzonsolutions.lunchboxfoodmaker.Service.FoodmakerService;
import com.jayzonsolutions.lunchboxfoodmaker.model.Categories;
import com.jayzonsolutions.lunchboxfoodmaker.model.Foodmaker;
import com.jayzonsolutions.lunchboxfoodmaker.model.FoodmakerDishes;
import com.jayzonsolutions.lunchboxfoodmaker.model.Order;
import com.jayzonsolutions.lunchboxfoodmaker.model.Products;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrdersFragment extends Fragment {
    public static Integer id;

    Animation startAnimation;

    private RecyclerView recyclerView;
    private RecycleAdapter_AddProduct mAdapter;

    private FoodmakerService foodmakerService;
    List<Order> foodmakerOrderList;

    private Map<Integer,Double> orderdishes;
    private Categories categories;
    private ImageView btn;
    private View view;


    public OrdersFragment() {
        // Required empty public constructor
    }

    public void setId(Integer id) {
        OrdersFragment.id = id;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //create this screen orderDishes list
        orderdishes = new HashMap<>();


        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_orders, container, false);

        startAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.bounce);

        //    initComponent(view);

        foodmakerOrderList = new ArrayList<>();
        categories = new Categories();
        categories.productsArrayList = new ArrayList<>();
        mAdapter = new RecycleAdapter_AddProduct(getActivity(), foodmakerOrderList);
        recyclerView = view.findViewById(R.id.recyclerview);



        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        foodmakerService = ApiUtils.getFoodmakerService();


/**
 *start
 ** call to get foodmaker dishes **/

        foodmakerService = ApiUtils.getFoodmakerService();

        foodmakerService.getOrdersByFoodmakerId(8).enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(@NonNull Call<List<Order>> call, @NonNull Response<List<Order>> response) {
                Toast.makeText(getContext(), "success" , Toast.LENGTH_LONG).show();

                foodmakerOrderList = response.body();
                mAdapter.setfoodmakerOrderList(foodmakerOrderList);


            }

            @Override
            public void onFailure(@NonNull Call<List<Order>> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Response Failed", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "failed" );
            }
        });



        /**
         *End
         ** call to get foodmaker dishes**/

        return view;


    }





    public class RecycleAdapter_AddProduct extends RecyclerView.Adapter<RecycleAdapter_AddProduct.MyViewHolder> {

        Context context;
        boolean showingFirst = true;
        int recentPos = -1;
        private List<Order> foodmakerOrderList;


        RecycleAdapter_AddProduct(Context context, List<Order> foodmakerOrderList) {
            this.foodmakerOrderList = foodmakerOrderList;
            this.context = context;
        }

        void setfoodmakerOrderList(List<Order> foodmakerOrderList) {
            this.foodmakerOrderList = foodmakerOrderList;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public RecycleAdapter_AddProduct.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_product, parent, false);


            return new RecycleAdapter_AddProduct.MyViewHolder(itemView);


        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onBindViewHolder(@NonNull final RecycleAdapter_AddProduct.MyViewHolder holder, final int position) {

            //junaid commit

//            Products movie = productsList.get(position);
if(foodmakerOrderList.get(position).getCustomer() == null){
    holder.title.setText("new order");
}else{
    holder.title.setText(foodmakerOrderList.get(position).getCustomer().getCustomerName());

}


//            holder.price.setText(foodmakerOrderList.get(position).getOrderTotalAmount().toString());
            holder.price.setText("900");



            holder.quantity = 1;
            holder.quantity = foodmakerOrderList.get(position).getOrderdishes().size();
            //   holder.quantity = categories.getProductsArrayList().get(position).getQuantity();
          //  int totalPrice = holder.quantity * foodmakerOrderList.get(position).getDish().getDishSellingPrice();


         /*   Glide.with(context).load(ApiUtils.BASE_URL+"images/es2.jpg").
                    apply(RequestOptions.
                            centerCropTransform().fitCenter().
                            diskCacheStrategy(DiskCacheStrategy.ALL)).
                    into(holder.image);*/



            //       categories.getProductsArrayList().get(position).setPriceAsPerQuantity("" + totalPrice);






        }

        @Override
        public int getItemCount() {
            return foodmakerOrderList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {


            ImageView image;
            TextView title;
            TextView price;

            int quantity;



            MyViewHolder(View view) {
                super(view);

                image = view.findViewById(R.id.imageProduct);
                title = view.findViewById(R.id.titleProduct);
                price = view.findViewById(R.id.price);

            }

        }

    }

}



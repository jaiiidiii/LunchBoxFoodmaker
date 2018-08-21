package com.jayzonsolutions.lunchboxfoodmaker.Fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.jayzonsolutions.lunchboxfoodmaker.ApiUtils;
import com.jayzonsolutions.lunchboxfoodmaker.Constant;
import com.jayzonsolutions.lunchboxfoodmaker.R;
import com.jayzonsolutions.lunchboxfoodmaker.Service.FoodmakerService;
import com.jayzonsolutions.lunchboxfoodmaker.Service.ItemClickListener;
import com.jayzonsolutions.lunchboxfoodmaker.Service.OrderService;
import com.jayzonsolutions.lunchboxfoodmaker.model.Categories;
import com.jayzonsolutions.lunchboxfoodmaker.model.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AckOrdersFragment extends Fragment {
    public static Integer id;

    Animation startAnimation;

//    private OrderService orderService;
  //  Context context = getContext();
    private RecyclerView recyclerView;
    private RecycleAdapter_AddProduct mAdapter;

    private FoodmakerService foodmakerService;
    List<Order> foodmakerOrderList;

    private Map<Integer,Double> orderdishes;
    private Categories categories;
    private ImageView btn;
    private View view;


    public AckOrdersFragment() {
        // Required empty public constructor
    }

    public void setId(Integer id) {
        AckOrdersFragment.id = id;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_orders, container, false);
        //create this screen orderDishes list
        orderdishes = new HashMap<>();

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
        Integer foodmakerId =  Constant.foodmaker.getFoodmakerId();
        if(foodmakerId == null ||foodmakerId == 0){
            foodmakerId = 0;
        }
        foodmakerService.getAckOrdersByFoodmakerId(foodmakerId).enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(@NonNull Call<List<Order>> call, @NonNull Response<List<Order>> response) {
                Toast.makeText(getContext(), "success" , Toast.LENGTH_LONG).show();

                foodmakerOrderList = response.body();
                mAdapter.setfoodmakerOrderList(foodmakerOrderList);
              //  mAdapter = new RecycleAdapter_AddProduct(getActivity(), foodmakerOrderList);
              //  recyclerView.setAdapter(mAdapter);


            }

            @Override
            public void onFailure(@NonNull Call<List<Order>> call, @NonNull Throwable t) {
           //     Toast.makeText(getContext(), "Response Failed", Toast.LENGTH_SHORT).show();
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

        @SuppressLint("SetTextI18n")
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onBindViewHolder(@NonNull final RecycleAdapter_AddProduct.MyViewHolder holder, final int position) {

            //junaid commit
//            Products movie = productsList.get(position);
    if (foodmakerOrderList.get(position).getCustomer() != null) {

        holder.title.setText(foodmakerOrderList.get(position).getCustomer().getCustomerName());
        holder.price.setText(foodmakerOrderList.get(position).getOrderTotalAmount().toString());
        holder.quantity = foodmakerOrderList.get(position).getOrderdishes().size();

/*        Glide.with(context).load(ApiUtils.BASE_URL+(foodmakerOrderList.get(position).getFoodmakerImagePath().substring(21))).
                apply(RequestOptions.
                        centerCropTransform().fitCenter().
                        diskCacheStrategy(DiskCacheStrategy.ALL)).
                into(holder.FoodMakerImage);*/
    } else {
        holder.title.setText("new order");
    }


//            holder.price.setText(foodmakerOrderList.get(position).getOrderTotalAmount().toString());



  //  holder.quantity = 1;

    //   holder.quantity = categories.getProductsArrayList().get(position).getQuantity();
    //  int totalPrice = holder.quantity * foodmakerOrderList.get(position).getDish().getDishSellingPrice();




    //       categories.getProductsArrayList().get(position).setPriceAsPerQuantity("" + totalPrice);


    holder.setItemClickListener(new ItemClickListener() {
        @Override
        public void onItemClick(View v, final int pos) {
            Log.d("pos", String.valueOf(pos));
            Toast.makeText(context, "Clicked Position =" + pos, Toast.LENGTH_SHORT).show();

//                    Toast.makeText(context, "Pressed Order Item", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder alert = new AlertDialog.Builder(context);
            alert.setTitle("Done Order");
            alert.setMessage("the meal is prepared ?");
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    Toast.makeText(context, "Clicked Yes", Toast.LENGTH_SHORT).show();

                    OrderService orderService = ApiUtils.getOrderService();
                    orderService.updateOrderStatus(3, foodmakerOrderList.get(pos).getOrderId()).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                            Toast.makeText(context, "Order status changed to 2", Toast.LENGTH_LONG).show();
                            removeAt(pos);

                        }

                        @Override
                        public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                            Toast.makeText(context, "Response Failed", Toast.LENGTH_SHORT).show();
                            Log.d("TAG", "failed");
                        }
                    });

                    //     setOrderStatus(foodmakerOrderList.get(pos).getOrderId());
                /*Intent myIntent = new Intent(
                        CameraPhotoCapture.this,
                        LoginActivity.class);
                startActivity(myIntent);
                finish();*/

                }
            });

            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(context, "Clicked No", Toast.LENGTH_SHORT).show();

                }
            });

            alert.show();


        }
    });
        }

        @Override
        public int getItemCount() {
            return foodmakerOrderList.size();
        }


        public void removeAt(int position) {
            foodmakerOrderList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, foodmakerOrderList.size());
        }



        class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


            ImageView image;
            TextView title;
            TextView price;

            int quantity;


            private ItemClickListener itemClickListener;

            MyViewHolder(View view) {
                super(view);

                image = view.findViewById(R.id.imageProduct);
                title = view.findViewById(R.id.titleProduct);
                price = view.findViewById(R.id.price);
                view.setOnClickListener(this);
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

/*    private void setOrderStatus(Integer orderId) {
        orderService = ApiUtils.getOrderService();
        orderService.updateOrderStatus(2,orderId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                Toast.makeText(context, "Order status changed to 2" , Toast.LENGTH_LONG).show();



            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Toast.makeText(context, "Response Failed", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "failed" );
            }
        });


    }*/

}



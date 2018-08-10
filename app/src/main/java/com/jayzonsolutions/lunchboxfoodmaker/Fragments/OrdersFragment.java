package com.jayzonsolutions.lunchboxfoodmaker.Fragments;

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
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jayzonsolutions.lunchboxfoodmaker.ApiUtils;
import com.jayzonsolutions.lunchboxfoodmaker.Constant;
import com.jayzonsolutions.lunchboxfoodmaker.R;
import com.jayzonsolutions.lunchboxfoodmaker.Service.FoodmakerService;
import com.jayzonsolutions.lunchboxfoodmaker.Service.ItemClickListener;
import com.jayzonsolutions.lunchboxfoodmaker.Service.OrderService;
import com.jayzonsolutions.lunchboxfoodmaker.model.Categories;
import com.jayzonsolutions.lunchboxfoodmaker.model.Order;
import com.jayzonsolutions.lunchboxfoodmaker.model.OrderDish;

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
    Integer foodmakerId = 0;

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
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_orders, container, false);

        if(Constant.foodmaker  != null){
            foodmakerId =  Constant.foodmaker.getFoodmakerId();
           // Intent intent = new Intent(MainFragment.this, signin.class);
        }



        //create this screen orderDishes list
        orderdishes = new HashMap<>();



        startAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.bounce);

        //    initComponent(view);

        foodmakerOrderList = new ArrayList<>();
        categories = new Categories();
        categories.productsArrayList = new ArrayList<>();
      //  mAdapter = new RecycleAdapter_AddProduct(getActivity(), foodmakerOrderList);
        recyclerView = view.findViewById(R.id.recyclerview);



        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(mAdapter);
        foodmakerService = ApiUtils.getFoodmakerService();



/**
 *start
 ** call to get foodmaker dishes **/

        foodmakerService = ApiUtils.getFoodmakerService();

        foodmakerService.getOrdersByFoodmakerId(1).enqueue(new Callback<List<Order>>() { //foodmakerId
            @Override
            public void onResponse(@NonNull Call<List<Order>> call, @NonNull Response<List<Order>> response) {
                Toast.makeText(getContext(), "success" , Toast.LENGTH_LONG).show();

                foodmakerOrderList = response.body();
               // mAdapter.setfoodmakerOrderList(foodmakerOrderList);
                //mAdapter.notifyDataSetChanged();
                mAdapter = new RecycleAdapter_AddProduct(getActivity(), foodmakerOrderList);
                recyclerView.setAdapter(mAdapter);

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

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onBindViewHolder(@NonNull final RecycleAdapter_AddProduct.MyViewHolder holder, final int position) {

            //junaid commit

//            Products movie = productsList.get(position);
if(foodmakerOrderList.get(position).getCustomer() == null){
    holder.title.setText("new order");
}else{
    holder.title.setText(""+foodmakerOrderList.get(position).getCustomer().getCustomerName());

}

            if(foodmakerOrderList.get(position).getOrderTotalAmount() == null){
                holder.price.setText("Amount not avaible yet");
            }else{
                holder.price.setText(""+foodmakerOrderList.get(position).getOrderTotalAmount());
            }


//            holder.price.setText("900");



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


            holder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onItemClick(View v, final int pos) {

                    showDialog(pos);
                }
            });
        }

        @Override
        public int getItemCount() {
            return foodmakerOrderList.size();
        }

        void showDialog(final int pos) {

            List<OrderDish> orderDishList = new ArrayList<>(foodmakerOrderList.get(pos).getOrderdishes());
            String dishes[] = new String[orderDishList.size()];

            for(int i=0;i<orderDishList.size();i++)
            {
                dishes[i] = " " +i+ " : " +orderDishList.get(i).getDishes().getName() + " ^ " + orderDishList.get(i).getQuantity()
                            + " ^ " + orderDishList.get(i).getDishes().getPrice();
            }

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            LayoutInflater inflater = getLayoutInflater();
            View convertView = (View) inflater.inflate(R.layout.custom, null);
            alertDialog.setView(convertView);
            alertDialog.setTitle("Order detail");
            ListView lv = (ListView) convertView.findViewById(R.id.listView1);

            ArrayAdapter<String> adapter = new ArrayAdapter<>(context,R.layout.list_item,R.id.text1,dishes);
            lv.setAdapter(adapter);

            alertDialog.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(context, "you have accepted the order", Toast.LENGTH_SHORT).show();

                    OrderService orderService = ApiUtils.getOrderService();
                    orderService.updateOrderStatus(2, foodmakerOrderList.get(pos).getOrderId()).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                            removeAt(pos);
                        }

                        @Override
                        public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                            Toast.makeText(context, "connection problem", Toast.LENGTH_SHORT).show();
                            Log.d("TAG", "failed");
                        }
                    });

                }
            });

            alertDialog.setNegativeButton("canel order", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    final AlertDialog.Builder alert_Dialog = new AlertDialog.Builder(context);
                    alert_Dialog.setTitle("Cancel order");
                    alert_Dialog.setMessage("are you sure you want to cancel the order?");
                    alert_Dialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(context, "your order is canceled", Toast.LENGTH_SHORT).show();
                            OrderService orderService = ApiUtils.getOrderService();
                            orderService.updateOrderStatus(4, foodmakerOrderList.get(pos).getOrderId()).enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                                    removeAt(pos);
                                }

                                @Override
                                public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                                    Toast.makeText(context, "connection problem", Toast.LENGTH_SHORT).show();
                                    Log.d("TAG", "failed");
                                }
                            });
                        }
                    });
                    alert_Dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        }
                    });

                    alert_Dialog.show();
                }
            });


            alertDialog.show();

        }


         void removeAt(int position) {
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



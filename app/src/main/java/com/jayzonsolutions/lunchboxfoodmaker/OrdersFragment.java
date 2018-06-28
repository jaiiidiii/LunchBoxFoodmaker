package com.jayzonsolutions.lunchboxfoodmaker;

import android.content.Context;
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

import com.jayzonsolutions.lunchboxfoodmaker.model.Categories;
import com.jayzonsolutions.lunchboxfoodmaker.model.Products;

import java.util.ArrayList;


public class OrdersFragment extends Fragment {
Context context = getContext();

    private LinearLayout linear_progressbar;

    private Toolbar toolbar;
    private TextView toolBarTxt;

    private RecyclerView recyclerView;
    private RecycleAdapter_AddProduct mAdapter;
    private int status_code;
    private String token,totalPriceOfProducts;


//    private ProductArrayList productsArrayList;

    private TextView quantityOfTotalProduct,priceOfTotalProduct,next;
    private Categories categories;

    private int[] IMAGES = {R.drawable.biryani,R.drawable.koorma,R.drawable.pulao,R.drawable.chicken_karahi,R.drawable.salad};
    private String[] NamES = {"Biryani","koorma","Pulao","Chicken_karahi","Salad"};
    private String[] PRICE = {"150","132","101","93","85"};


    private View view;


    Animation startAnimation;


    public OrdersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_orders, container, false);

        startAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.bounce);


        initComponent(view);


        categories = new Categories();
        categories.productsArrayList = new ArrayList<>();


        for (int i=0; i<NamES.length ; i++){
            Products products = new Products();
            products.setName(NamES[i]);
            products.setPrice(PRICE[i]);
            products.setImage(IMAGES[i]);

            categories.productsArrayList.add(products);

        }


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);




//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                ProductArrayList productsForSend = new ProductArrayList();
//                productsForSend =  productsToBeSend(categoriesAndProducts);
//                PrefUtils.setProducts(productsForSend, getActivity());
//
//                if (productsForSend.getProductsArrayList().size() >0){
//                    Intent it = new Intent(getActivity(), ProductSummaryActivity.class);
//                    startActivity(it);
//                }else {
//                    Toast.makeText(getActivity(), "Please Select The Products", Toast.LENGTH_SHORT).show();
//                }
//
//
//            }
//        });




        mAdapter = new RecycleAdapter_AddProduct(getActivity(),categories);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        return view;

    }

    private void initComponent(View view) {


    }

    public class RecycleAdapter_AddProduct extends RecyclerView.Adapter<RecycleAdapter_AddProduct.MyViewHolder> {

        Context context;
        boolean showingFirst = true;
        private Categories categories;

        int recentPos = -1;



        public class MyViewHolder extends RecyclerView.ViewHolder {




            ImageView image;
            TextView title;
            TextView price;
            TextView quantityTxt;
            private LinearLayout llMinus,llPlus;
            int quantity;


            public MyViewHolder(View view) {
                super(view);

                image =  view.findViewById(R.id.image);
                title =  view.findViewById(R.id.title);
                price =  view.findViewById(R.id.price);
                quantityTxt =  view.findViewById(R.id.quantityTxt);
                llPlus = view.findViewById(R.id.llPlus);
                llMinus = view.findViewById(R.id.llMinus);
            }

        }



        public RecycleAdapter_AddProduct(Context context, Categories categories) {
            this.categories = categories;
            this.context = context;
        }

        @Override
        public RecycleAdapter_AddProduct.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_product, parent, false);



            return new RecycleAdapter_AddProduct.MyViewHolder(itemView);


        }


        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onBindViewHolder(@NonNull final RecycleAdapter_AddProduct.MyViewHolder holder, final int position) {
//            Products movie = productsList.get(position);

            holder.title.setText(categories.getProductsArrayList().get(position).getName());
            holder.price.setText(categories.getProductsArrayList().get(position).getPrice());
            holder.quantityTxt.setText(categories.getProductsArrayList().get(position).getQuantity() + "");


            holder.quantity = categories.getProductsArrayList().get(position).getQuantity();
            int totalPrice = holder.quantity * Integer.parseInt(categories.getProductsArrayList().get(position).getPrice());


            if (position == recentPos) {
                Log.e("pos", "" + recentPos);
                // start animation
                holder.quantityTxt.startAnimation(startAnimation);

            } else {
                holder.quantityTxt.clearAnimation();

            }

            if (categories.getProductsArrayList().get(position).getQuantity() > 0){
                holder.quantityTxt.setVisibility(View.VISIBLE);
                holder.llMinus.setVisibility(View.VISIBLE);
            }else {
                holder.quantityTxt.setVisibility(View.GONE);
                holder.llMinus.setVisibility(View.GONE);
            }


            categories.getProductsArrayList().get(position).setPriceAsPerQuantity(""+ totalPrice);


            holder.llPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (holder.quantity <10){


                        recentPos = position;
                        holder.quantity = holder.quantity + 1;
                        categories.getProductsArrayList().get(position).setQuantity(holder.quantity);
                        categories.getProductsArrayList().get(position).setPriceAsPerQuantity(""+holder.quantity * Integer.parseInt(categories.getProductsArrayList().get(position).getPrice()));

                        holder.quantityTxt.setText("" + holder.quantity);
                    }


                    notifyDataSetChanged();

                }
            });


            holder.llMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (holder.quantity > 0 && holder.quantity <= 10){

                        recentPos = position;

                        holder.quantity = holder.quantity - 1;
                        categories.getProductsArrayList().get(position).setQuantity(holder.quantity);
                        categories.getProductsArrayList().get(position).setPriceAsPerQuantity(""+holder.quantity *
                                Integer.parseInt(categories.getProductsArrayList().get(position).getPrice()));

                        holder.quantityTxt.setText("" + holder.quantity);


                    }

                    notifyDataSetChanged();

                }
            });



        }

        @Override
        public int getItemCount() {
            return categories.getProductsArrayList().size();
        }

    }

}


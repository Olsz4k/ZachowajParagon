package psm.zachowajparagon;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mReceiptsList;

    private DatabaseReference mDatabase;
    private DatabaseReference mDatabaseUsers;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser() == null) {

                    Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(loginIntent);
                }

            }
        };

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Receipts");
        mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child("Users");

        mDatabaseUsers.keepSynced(true);



        mReceiptsList = (RecyclerView) findViewById(R.id.receipts_list);
        mReceiptsList.setHasFixedSize(true);
        mReceiptsList.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);


        FirebaseRecyclerAdapter<Receipt, ReceiptViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Receipt, ReceiptViewHolder>(

                Receipt.class,
                R.layout.receipt_row,
                ReceiptViewHolder.class,
                mDatabase

        ){


            @Override
            protected void populateViewHolder(ReceiptViewHolder viewHolder, Receipt model, int position) {

                final String receipt_key = getRef(position).getKey();

                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDesc());
                viewHolder.setImage(getApplicationContext(), model.getImage());


                viewHolder.nView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Toast.makeText(MainActivity.this, receipt_key, Toast.LENGTH_LONG).show();

                    }
                });



            }
        };

        mReceiptsList.setAdapter(firebaseRecyclerAdapter);
    }

    private void checkUserExist() {

        final String user_id = mAuth.getCurrentUser().getUid();

        mDatabaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(!dataSnapshot.hasChild(user_id)) {

                    Intent mainIntent = new Intent(MainActivity.this, LoginActivity.class);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(mainIntent);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static class ReceiptViewHolder extends RecyclerView.ViewHolder {

        View nView;

        TextView product_name;


        public ReceiptViewHolder(View itemView) {
            super(itemView);

            nView = itemView;

            product_name = (TextView) nView.findViewById(R.id.product_name);

            product_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.v("MainActivity", "Jakis tekscik");
                }
            });
        }

        public void setTitle(String title) {
           // TextView product_name = (TextView) nView.findViewById(R.id.product_name);
            product_name.setText(title);
        }

        public void setDesc(String desc) {
            TextView product_desc = (TextView) nView.findViewById(R.id.product_desc);
            product_desc.setText(desc);
        }

        public void setImage(Context ctx, String image) {
            ImageView receipt_image = (ImageView) nView.findViewById(R.id.receipt_image);
            Picasso.with(ctx).load(image).into(receipt_image);
        }
    }



    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_add) {

            startActivity(new Intent(MainActivity.this, ParagonActivity.class));
        }

        if(item.getItemId() == R.id.action_logout) {

            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {

        mAuth.signOut();
    }
}

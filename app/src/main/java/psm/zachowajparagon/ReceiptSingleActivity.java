package psm.zachowajparagon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class ReceiptSingleActivity extends AppCompatActivity {

    private String mProduct_key = null;

    private DatabaseReference mDatabase;

    private ImageView mReceiptSingleImage;
    private TextView mReceiptSingleName;
    private TextView mReceiptSingleDesc;
    private TextView mReceiptSinglePrice;
    private TextView mReceiptSingleCategory;
    private TextView mReceiptSingleShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_single);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Receipts");


        mProduct_key = getIntent().getExtras().getString("receipt_id");

        mReceiptSingleDesc = (TextView) findViewById(R.id.singleReceiptDesc);
        mReceiptSingleName = (TextView) findViewById(R.id.singleReceiptName);
        mReceiptSingleImage = (ImageView) findViewById(R.id.singleReceiptImage);
        mReceiptSinglePrice = (TextView) findViewById(R.id.singleReceiptPrice);
        mReceiptSingleCategory = (TextView) findViewById(R.id.singleReceiptCategory);
        mReceiptSingleShop = (TextView) findViewById(R.id.singleReceiptShop);



        //Toast.makeText(ReceiptSingleActivity.this, product_key, Toast.LENGTH_LONG).show();

        mDatabase.child(mProduct_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String product_name = (String) dataSnapshot.child("title").getValue();
                String product_desc = (String) dataSnapshot.child("desc").getValue();
                String product_image = (String) dataSnapshot.child("image").getValue();
                String receipt_uid = (String) dataSnapshot.child("uid").getValue();
                String product_price = (String) dataSnapshot.child("price").getValue();
                String product_shop = (String) dataSnapshot.child("shop").getValue();
                String product_category = (String) dataSnapshot.child("category").getValue();


                mReceiptSingleName.setText(product_name);
                mReceiptSingleDesc.setText(product_desc);

                Picasso.with(ReceiptSingleActivity.this).load(product_image).into(mReceiptSingleImage);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

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

public class ReceiptSingleActivity extends AppCompatActivity {

    private String mProduct_key = null;

    private DatabaseReference mDatabase;

    private ImageView mReceiptSingleImage;
    private TextView mReceiptSingleName;
    private TextView mReceiptSingleDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_single);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Receipts");


        mProduct_key = getIntent().getExtras().getString("receipt_id");

        mReceiptSingleDesc = (TextView) findViewById(R.id.singleReceiptDesc);
        mReceiptSingleName = (TextView) findViewById(R.id.singleReceiptName);
        mReceiptSingleImage = (ImageView) findViewById(R.id.singleReceiptImage);


        //Toast.makeText(ReceiptSingleActivity.this, product_key, Toast.LENGTH_LONG).show();

        mDatabase.child(mProduct_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String product_name = (String) dataSnapshot.child("title").getValue();
                String product_desc = (String) dataSnapshot.child("desc").getValue();
                String product_image = (String) dataSnapshot.child("image").getValue();
                String receipt_uid = (String) dataSnapshot.child("uid").getValue();

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

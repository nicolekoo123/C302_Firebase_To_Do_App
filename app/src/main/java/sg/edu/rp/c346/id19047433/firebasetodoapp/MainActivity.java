package sg.edu.rp.c346.id19047433.firebasetodoapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MainActivity extends AppCompatActivity {
    TextView tvTitle, tvDate;
    EditText etTitle, etDate;
    Button btnAdd;

    private FirebaseFirestore db;
    private CollectionReference colRef;
    private DocumentReference docRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitle = findViewById(R.id.textViewTitle);
        tvDate = findViewById(R.id.textViewDate);
        etTitle = findViewById(R.id.editTextTitle);
        etDate = findViewById(R.id.editTextDate);
        btnAdd = findViewById(R.id.buttonAdd);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String date = etDate.getText().toString();
                toDoItem msg = new toDoItem(title, date);
                //To set
                docRef.set(msg);
                //To update
                //docRef.update("text", text);
                //does not work the database never update the color
            }
        });

        db = FirebaseFirestore.getInstance();

        colRef = db.collection("toDoItem");
        docRef = colRef.document("toDoItem");
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    toDoItem msg = snapshot.toObject(toDoItem.class);//use POJO
                    tvTitle.setText(msg.getTitle());
                    tvDate.setText(msg.getDate());
                }
            }
        });
    }
}
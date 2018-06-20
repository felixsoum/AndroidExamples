package com.collegelasalle.felix.firebasestorageexample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.storage.UploadTask.TaskSnapshot;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private EditText editText;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final String filename = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".txt";
        final File file = new File(getFilesDir(), filename);
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
            editText = findViewById(R.id.editText);
            out.writeBytes(editText.getText().toString());
            out.close();
            StorageReference storageRef = FirebaseStorage.getInstance().getReference().child(filename);
            UploadTask uploadTask = storageRef.putStream(new FileInputStream(file));
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    textView.setText("Error: " + e.getMessage());
                    file.delete();
                }
            }).addOnSuccessListener(new OnSuccessListener<TaskSnapshot>() {
                @Override
                public void onSuccess(TaskSnapshot taskSnapshot) {
                    textView.setText("Uploaded: " + filename);
                    file.delete();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

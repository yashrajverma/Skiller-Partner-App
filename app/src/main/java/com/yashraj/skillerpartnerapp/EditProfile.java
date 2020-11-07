package com.yashraj.skillerpartnerapp;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.squareup.picasso.Picasso;
import com.yashraj.skillerpartnerapp.Model.Vendor;

import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfile extends AppCompatActivity {
    TextView name;
    TextView email;
    EditText password;
    TextView mobile;
    TextView city;
    CircleImageView image;
    Button uploadBtn;
    Button updateBtn;
    FirebaseUser mUser;
    Uri filePath;
    Bitmap bitmap;
    String newPassword;
    boolean imageSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        imageSelected = false;             //// to check if image is selected or not
        password = findViewById(R.id.password);
        mobile = findViewById(R.id.mobile);
        city = findViewById(R.id.city);
        image = findViewById(R.id.uploadImage);
        uploadBtn = findViewById(R.id.upload_btn);
        updateBtn = findViewById(R.id.update_btn);
        mUser = FirebaseAuth.getInstance().getCurrentUser();

        showUsersData();                ////Method to show user details fetched from  the database

        ////// Button to Browse images from mobile///////

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dexter.withContext(getApplicationContext()).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                Intent intent = new Intent();
                                intent.setType("image/*");
                                intent.setAction(Intent.ACTION_GET_CONTENT);
                                startActivityForResult(Intent.createChooser(intent, "Please Select image"), 101);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();

                            }
                        }).check();

                imageSelected = true;


            }
        });

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageSelected) {
                    uploadToFirebase();


                } else {
                    Toast.makeText(EditProfile.this, "Please Select Image", Toast.LENGTH_SHORT).show();
                }

            }
        });


/////// Button to update password and mobile number //////

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vendor vendor = new Vendor();
                newPassword = password.getText().toString();
                if (!validatePassword()) {
                    Toast.makeText(EditProfile.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseDatabase.getInstance().getReference().child("Vendors").child(mUser.getUid()).child("password").setValue(newPassword);
                    Picasso.get().load(vendor.getImageUrl()).placeholder(R.drawable.ic_baseline_account_circle_24).into(image);
                    Toast.makeText(EditProfile.this, "Profile Updated", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

    /////// Part of above used method to help user to browse Images /////////
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK) {
            filePath = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(filePath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                image.setImageBitmap(bitmap);


            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
    }

///// Method to upload image in the firebase storage ////////

    private void uploadToFirebase() {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Uploading");
        pd.show();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        final StorageReference uploader = storage.getReference().child("VendorsImg").child(mUser.getUid().substring(0, 8) + "." + getFileExtension(filePath));
        uploader.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                pd.dismiss();
                Toast.makeText(EditProfile.this, "Image Uploaded", Toast.LENGTH_SHORT).show();
                FirebaseDatabase.getInstance().getReference().child("Vendors").child(mUser.getUid()).child("imageUrl").setValue(uploader.getDownloadUrl().toString());

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(EditProfile.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

////// Method to get extension of selected Image //////

    private String getFileExtension(Uri filePath) {
        return MimeTypeMap.getSingleton().getExtensionFromMimeType(this.getContentResolver().getType(filePath));
    }


    ////// Method to retrieve data from the firebase and show it  in edit profile activity ////////
    private void showUsersData() {

        FirebaseDatabase.getInstance().getReference().child("Vendors").child(mUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Vendor vendor = snapshot.getValue(Vendor.class);
                name.setText(vendor.getName());
                email.setText(vendor.getEmail());
                password.setText(vendor.getPassword());
                mobile.setText(vendor.getPhoneNo());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    ////// To validate updated password ///////

    public boolean validatePassword() {
        String value = password.getText().toString();
        if (value.isEmpty() || value.length() < 6) {
            password.setError("At least 6 characters required");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }


}
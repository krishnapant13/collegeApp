package com.example.gbpiet.authentication;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gbpiet.MainActivity;
import com.example.gbpiet.R;
import com.example.gbpiet.ui.Ebook.Edata;
import com.example.gbpiet.ui.notice.NoticeData;
import com.google.android.gms.safetynet.VerifyAppsConstants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private EditText registerName, registerEmail, registerPass, registerLastName;
    private Button registerButton;
    private String name, lastName, gender ,email, pass, branch, DownloadUrl=" ";
    private RadioGroup radioGroup;
    private FirebaseAuth auth;
    private ImageView uploadImage;
    private LinearLayout ImageViewCard;
    private final int REQ = 1;
    private Bitmap bitmap;
    //    database and firebase data refrences
    private StorageReference storageReference;
    private DatabaseReference reference,dbref;

    private Spinner registerSpinner;
    private TextView openLogin, uploadText;
    private ProgressDialog pd;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerName = findViewById(R.id.registerName);
        registerLastName = findViewById(R.id.registerLastName);
        registerEmail = findViewById(R.id.registerEmail);
        uploadImage = findViewById(R.id.uploadImage);
        registerPass = findViewById(R.id.registerPass);
        registerButton = findViewById(R.id.registerButton);
        registerSpinner = findViewById(R.id.registerSpinner);
        openLogin = findViewById(R.id.openLogin);
        ImageViewCard = findViewById(R.id.ImageViewCard);
        radioGroup = findViewById(R.id.radioGroup);

        //getting database references
        reference = FirebaseDatabase.getInstance().getReference().child("users");
        storageReference = FirebaseStorage.getInstance().getReference();

        pd = new ProgressDialog(this);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.male:
                        Toast.makeText(RegisterActivity.this, "You selected Male", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.female:
                        Toast.makeText(RegisterActivity.this, "You selected Female", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.other:
                        Toast.makeText(RegisterActivity.this, "You selected Other", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });


        registerPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    registerPass.setError("Use the Combination of Upper&Lower Case Characters ,SpecialSymbols with NoWhiteSpaces");

                }
            }
        });

        setTitle("User Registration");


//        setting the spinner categories

        String[] items = new String[]{"Select Branch", "Master Of computer Applications", "Mechanical Engineering", "Computer Science", "Civil Engineering", "Electrical Engineering", "Applied Science and Humanities", "BioTech", "Electronics and Communication"};

        //setting adapter for spinner

        registerSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items));
        auth = FirebaseAuth.getInstance();


        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

//        select Listener for spinner
        registerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                branch = registerSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        openLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });

//Register Button

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });
    }

    private void openGallery() {
        Intent pickImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickImage, REQ);
    }
//    after opening gallery

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }

            uploadImage.setImageBitmap(bitmap);
        }
    }

    public void openLogin() {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        finish();
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (auth.getCurrentUser() != null) {
            openMain();

        }
    }

    private void openMain() {
        startActivity(new Intent(this, MainActivity.class));
    }
//    data validation method
    private void validateData() {
        name = registerName.getText().toString();
        lastName = registerLastName.getText().toString();
        pass = registerPass.getText().toString();
        email = registerEmail.getText().toString();
        int gender = radioGroup.getCheckedRadioButtonId();

        if (name.isEmpty()&&!name.matches("^[a-z]+$")){
            registerName.setError("Name can't be Empty and Contains letters only ");
            registerName.requestFocus();
            if (name.length() > 15) {
                registerName.setError("First Name Too Long");
            }
        }else if (lastName.isEmpty()) {
            registerLastName.setError("Last Name Required");
            registerName.requestFocus();

        }else if (email.isEmpty()) {
            registerEmail.setError("email Required");
            registerEmail.requestFocus();
            Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
        }else if (pass.isEmpty()){
            registerPass.setError("Pass is required");
            registerPass.requestFocus();
        }else if (gender == -1) {

            Toast.makeText(this, "You haven't selected Gender ", Toast.LENGTH_SHORT).show();
            radioGroup.requestFocus();
        } else if (branch.equals("Select Branch")) {
            Toast.makeText(this, "Please Select Branch", Toast.LENGTH_SHORT).show();
        } else if (bitmap == null) {

            Toast.makeText(this, "Please Upload Your Photo Above", Toast.LENGTH_SHORT).show();
            uploadImage.requestFocus();
        }else {
            pd.setTitle("Creating User. . .");
            pd.setMessage("Please Wait");
            pd.show();
            uploadUserImage();
        }
    }

//    private Boolean passValidation(){
//
//        pass = registerPass.getText().toString();
//        if (pass.isEmpty()){
//            registerPass.setError("pass can't be empty");
//            return  false;
//        }else{
//            registerPass.setError(null);
//            return true;
//        }
//    }

    //email validation method
//    private boolean validateEmail() {
//        String emailPattern =  "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
//        String email = registerEmail.getText().toString().trim();
//
//        if (email.isEmpty()) {
//            registerEmail.setError("Email Required");
//            registerEmail.requestFocus();
//            return false;
//
//        } else if (!email.matches(emailPattern)) {
//            registerEmail.setError("Enter a valid Email Address");
//            return false;
//        } else {
//            registerEmail.setError(null);
//            return true;
//
//        }
//    }

    private void uploadUserImage() {
        //never show pd here
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,byteArrayOutputStream);
        byte[] finalImg = byteArrayOutputStream.toByteArray();
        final StorageReference filePath;
        filePath = storageReference.child("Users").child(branch).child(finalImg+"jpg");
        final UploadTask uploadTask = filePath.putBytes(finalImg);
        uploadTask.addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()){
                    pd.dismiss();
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    DownloadUrl = String.valueOf(uri);
                                    uploadData();
                                }
                            });
                        }
                    });
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Something went wrong", Toast.LENGTH_SHORT);
                }
            }
        });
    }
    private void createUser() {
        auth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //user will upload data after creation
                            pd.dismiss();
                            openMain();
                            Toast.makeText(RegisterActivity.this, "You have Created Your Profile Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegisterActivity.this, "Error :" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterActivity.this, "Error :" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void uploadData() {
        dbref = reference.child(branch);
        final String uniqueKey = dbref.push().getKey();

        Calendar  calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yy");
        String date = currentDate.format(calForDate.getTime());

        Calendar calForTime = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm a");
        String time = currentTime.format(calForTime.getTime());

        RegisterData registerData = new RegisterData(DownloadUrl, name ,lastName, gender, branch ,email, uniqueKey,date,time);

        dbref.child(uniqueKey).setValue(registerData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                createUser();
                pd.dismiss();
                Toast.makeText(RegisterActivity.this, "Data Validated", Toast.LENGTH_SHORT).show();
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(RegisterActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

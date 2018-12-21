package np.com.kendraregmi.ersathichat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class login_activity extends AppCompatActivity {

    private Toolbar mToolbar;

    private TextInputLayout mLoginEmail;
    private  TextInputLayout mLoginPassword;

    private Button mLoginButton;

    private ProgressDialog mLoginprogress;

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);

        mAuth = FirebaseAuth.getInstance();

        mToolbar=(Toolbar) findViewById(R.id.login_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mLoginprogress=new ProgressDialog(this);


        mLoginEmail=(TextInputLayout) findViewById(R.id.login_email);
        mLoginPassword=(TextInputLayout) findViewById(R.id.login_password);
        mLoginButton=(Button) findViewById(R.id.login_button);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String email=mLoginEmail.getEditText().getText().toString();
                    String password=mLoginPassword.getEditText().getText().toString();

                    if(!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)){

                        mLoginprogress.setTitle("Login in");
                        mLoginprogress.setCanceledOnTouchOutside(false);
                        mLoginprogress.setMessage("Please Wait!!");
                        mLoginprogress.show();

                        loginUser(email,password);
                    }
            }
        });


    }

    private void loginUser(String email, String password) {

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){

                        mLoginprogress.dismiss();
                        Intent mainIntent=new Intent(login_activity.this, MainActivity.class);
                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(mainIntent);
                        finish();
                    }else{
                        mLoginprogress.hide();
                        Toast.makeText(login_activity.this, "You got some error", Toast.LENGTH_LONG).show();
                    }
                }
            });

    }
}

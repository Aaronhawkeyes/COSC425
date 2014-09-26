package com.AYIC.scheduleApp;

import com.authorwjf.customfontdemo.R;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity {

	DBTools dbTools = new DBTools(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		View view = this.getWindow().getDecorView();
		view.setBackgroundResource(R.drawable.login_background);

		final MyTextView loginButton = (MyTextView) findViewById(R.id.loginButton);
		MyTextView registerButton = (MyTextView) findViewById(R.id.registerButton);

		loginButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				loginUser(v);
			}
		});

		registerButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
				//overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void loginUser(View view)
	{
		MyEditText userEmail = (MyEditText) findViewById(R.id.emailEditText);
		MyEditText userPassword = (MyEditText) findViewById(R.id.passwordEditText);
		String userEmailString = userEmail.getText().toString();
		String userPasswordString = userPassword.getText().toString();
		String msg = "";

		boolean successfulLogin = dbTools.canLogin(userEmailString, userPasswordString);

		if(successfulLogin == true) msg = "You have successfully logged in.";
		else msg = "The login information is incorrect.";

		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();		
	}

	public void registerNewUser(View v)
	{
		Toast.makeText(getApplicationContext(), "MOVING TO REGISTRATION ACTIVITY", Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) 
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

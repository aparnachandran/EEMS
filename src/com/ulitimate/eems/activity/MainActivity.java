package com.ulitimate.eems.activity;



import com.ultimate.eems.utils.Fonts;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	/*
	 * TYPE DECLARATIONS
	 * 
	 * */
	
	//System Memory field variables
	SharedPreferences Prefs;
	
	//View variables
	EditText employeeId,password;
	Button submit,clear;
	TextView warningText;
	
	//Layout Ids
	RelativeLayout warningLayout;
	
	
	
	
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		//Checks whether the App is not yet registered
		Prefs = getSharedPreferences("EEMS", 0);
		SharedPreferences.Editor editor = Prefs.edit();
		int flag = Prefs.getInt("LOGIN", -1);
		if(flag==-1){
			setContentView(R.layout.activity_main_login);
			 
			//This method will call, to register the user whether Employee or Supervisor
			registerUser();
			
			/*editor.putInt("LOGIN",1);
			editor.commit(); */
			
			
			 
			
		}else{
			/*setContentView(R.layout.activity_main_employee);
			userPage();*/
		}
		
	}



	private void userPage() {
		
		
	}


 
	private void registerUser() {
		employeeId = (EditText)findViewById(R.id.employeeID);
		password = (EditText)findViewById(R.id.password);
		submit = (Button)findViewById(R.id.submit);
		warningLayout = (RelativeLayout)findViewById(R.id.warningLayout);
		warningText = (TextView)findViewById(R.id.warningText);
		clear = (Button)findViewById(R.id.clear);
		
		employeeId.setTypeface(Fonts.getTypeFace(getApplicationContext(), Fonts.CHUNKFIVE));
		password.setTypeface(Fonts.getTypeFace(getApplicationContext(), Fonts.CHUNKFIVE));
		submit.setTypeface(Fonts.getTypeFace(getApplicationContext(), Fonts.CHUNKFIVE));
		
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				submitActions();
			}
		});
		
		clear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				clearFields();
			}
		});
		
		
		
	}



	private void submitActions() {
		
		String userID = employeeId.getText().toString();
		String pword = password.getText().toString();
		
		if(userID.length()>0 && pword.length()>0){
			if(warningLayout.getVisibility()==View.VISIBLE){
				warningLayout.setVisibility(View.INVISIBLE);
			}
			
			if(userID.startsWith("EMP")){
				
			}
			else if(userID.startsWith("MNG")){
				
			}
			else{
				
			}
				
			
			
			
		}
		else{
			
			clearFields();
			warningText.setTypeface(Fonts.getTypeFace(getApplicationContext(), Fonts.CHUNKFIVE));
			if(warningLayout.getVisibility()==View.INVISIBLE){
				warningLayout.setVisibility(View.VISIBLE);
			}
		}
		
	}



	private void clearFields() {
		employeeId.setText("");
		password.setText("");
	}

	

}

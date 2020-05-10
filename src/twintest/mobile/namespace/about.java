package twintest.mobile.namespace;


import java.util.zip.InflaterOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class about extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);       
        setContentView(R.layout.about);
    }

    public void mainreturn(View view1)
    {    	
    	Intent ent= new Intent(this,TwinTestActivity.class);
    	startActivity(ent);
    }    
    @Override    
    public void onBackPressed() {
    	Intent ent= new Intent(this,TwinTestActivity.class);
    	startActivity(ent);
    }
    
}






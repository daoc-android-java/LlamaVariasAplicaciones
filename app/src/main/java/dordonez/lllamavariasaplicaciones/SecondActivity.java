package dordonez.lllamavariasaplicaciones;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class SecondActivity extends Activity {

	private EditText et;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		et = (EditText) findViewById(R.id.et1);
		
		Intent intent = getIntent();
		String hint = intent.getStringExtra("hint");
		if(hint != null) {
			et.setHint(hint);
		}
	}
	
	//onClick definido para el bot�n en el archivo de layout
	public void regresaAMain (View view) {
		Intent intent = new Intent();
		intent.putExtra("data", et.getText().toString());
		setResult(RESULT_OK, intent);
		finish();
	}
	
}

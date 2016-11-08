/*
 * --Diego Ord��ez--
 * Demuestra c�mo llamar otras actividades o aplicaciones,
 * posiblemente recibiendo resultados de las mismas.
 */

package dordonez.lllamavariasaplicaciones;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private final int REQCODE_SECOND_ACTIVITY = 1;
	private final int REQCODE_TOMAR_FOTO = 2;
	
	private TextView tv1;
	private ImageView iv1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv1 = (TextView) findViewById(R.id.tv1);
		iv1 = (ImageView) findViewById(R.id.iv1);
	}
	
	//onClick definido para @id/bGal en el archivo de layout
	public void mostrarGaleria(View view) {
		Intent galIntent = Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_GALLERY);
		startActivity(galIntent);

	}	
	
	//onClick definido para @id/bWeb en el archivo de layout
	public void mostrarWeb(View view) {
		Uri androidUri = Uri.parse("https://www.android.com");
		Intent intent = new Intent(Intent.ACTION_VIEW, androidUri);
	    if (intent.resolveActivity(getPackageManager()) != null) {
			startActivity(intent);
	    } else {
			Toast.makeText(this, "Instale un browser", Toast.LENGTH_SHORT).show();    	
	    }
	}
	
	//onClick definido para @id/b2ndActiv en el archivo de layout
	public void irASecond(View view) {
		Intent intent = new Intent(this, SecondActivity.class);
		intent.putExtra("hint", "Escriba su nombre");
		startActivityForResult(intent, REQCODE_SECOND_ACTIVITY);
	}	
	
	//onClick definido para @id/bFoto en el archivo de layout
	public void tomarFoto(View v) {
	    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    //resolveActivity averigua si existe alguna aplicaci�n que pueda
	    //manejar la acci�n definida en el Intent
	    if (intent.resolveActivity(getPackageManager()) != null) {
	        startActivityForResult(intent, REQCODE_TOMAR_FOTO);
	    }
	}
	
	//recibe la informaci�n devuelta por todas las actividades invocadas
	//con startActivityForResult()
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case REQCODE_SECOND_ACTIVITY:
			if(resultCode == RESULT_OK) {
				String resultado = data.getStringExtra("data");
				tv1.setText( "Hola " + resultado + "!!!!!");
			}
			break;
		case REQCODE_TOMAR_FOTO:
			if(resultCode == RESULT_OK) {
				//recupera un "preview" de la foto
		        Bitmap bmp = (Bitmap) data.getExtras().get("data");
		        iv1.setImageBitmap(bmp);				
			}			
			break;
		default:
			break;
		}
	}

}

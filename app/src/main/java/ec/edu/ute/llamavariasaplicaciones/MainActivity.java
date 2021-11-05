package ec.edu.ute.llamavariasaplicaciones;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private final int REQCODE_TOMAR_FOTO = 1;

    private ImageView iv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv1 = (ImageView) findViewById(R.id.iv1);
    }

    //onClick definido para @id/bGal en el archivo de layout
    public void mostrarGaleria(View view) {
        Intent intent = Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_GALLERY);
        //resolveActivity averigua si existe alguna aplicación que pueda manejar la acción definida
        //en el Intent
        //Las llamadas a PackageManager para obtener información (en API30+) requieren <queries>
        //en el manifest
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "No hay galería ?!?!?!", Toast.LENGTH_SHORT).show();
        }

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

    //onClick definido para @id/bFoto en el archivo de layout
    public void tomarFoto(View v) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQCODE_TOMAR_FOTO);
        } else {
            Toast.makeText(this, "No hay cámara ?!?!?!", Toast.LENGTH_SHORT).show();
        }
    }

    //recibe la información devuelta por todas las actividades invocadas
    //con startActivityForResult()
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
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
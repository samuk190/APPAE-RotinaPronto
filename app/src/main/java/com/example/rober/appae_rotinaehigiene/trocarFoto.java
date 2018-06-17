package com.example.rober.appae_rotinaehigiene;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

public class trocarFoto extends Activity implements View.OnClickListener{

    private static int RESULT_LOAD_IMAGE = 1;
    private Button buttonLoadImage;
    private Button troca;
    private Spinner spinner;
    private ImageView imageView;
    Uri selectedImage;
    String picturePath;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trocar_foto);

        buttonLoadImage = (Button) findViewById(R.id.buttonLoadPicture);
        buttonLoadImage.setOnClickListener(this);
        troca = (Button) findViewById(R.id.trocar);
        troca.setOnClickListener(this);
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.opcoes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //spinner.setOnItemSelectedListener(this);
        imageView = (ImageView) findViewById(R.id.imgView);


    }
            public void trocafoto(String s, Uri u)
            {

            }

            @Override
            public void onClick(View arg0) {
                if (arg0 == buttonLoadImage) {
                    Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, RESULT_LOAD_IMAGE);
                }
                else if(arg0 == troca)
                {
                   //trocafoto();
                    int op = spinner.getSelectedItemPosition();
                    ImageView im = (ImageView) findViewById(R.id.ivbanho);
                    ImageView im2 = (ImageView) findViewById(R.id.ivbanheiro);

                    if(op == 0)
                    {
                        im.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                        im.setImageURI(selectedImage);
                    }
                    else  if(op == 1)
                    {
                        im2.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                        im2.setImageURI(selectedImage);
                    }
                }

            }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


            if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            imageView.setImageURI(selectedImage);

        }

        }


    }

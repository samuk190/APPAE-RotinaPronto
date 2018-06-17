package com.example.rober.appae_rotinaehigiene;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;


public class imagens extends SwipeAc implements View.OnClickListener{

    //declaração das variaveis
    ViewFlipper viewFlipper;
    ViewFlipper viewFlipper2;
    Button next;
    Button previous;

    //declaração das variaveis
    ImageView banheiro;
    ImageView banho;
    ImageView lavar;
    ImageView dentes;
    ImageView comida;
    ImageView estudar;
    ImageView brincar;
    ImageView cabelo;
    //declaração das variaveis
    Uri selectedImage;
    String picturePath;
    private static int RESULT_LOAD_IMAGE = 1;
    int r;
    //declaração das variaveis
    public MediaPlayer mpbanheiro;
    public MediaPlayer mpbanho;
    public MediaPlayer mplavar;
    public MediaPlayer mpdentes;
    public MediaPlayer mpcomida;
    public MediaPlayer mpestudar;
    public MediaPlayer mpbrincar;
    public MediaPlayer mpcabelo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagens);

        //atribuindo valores as variaveis
        viewFlipper = (ViewFlipper)findViewById(R.id.viewFlipper);
        viewFlipper2 = (ViewFlipper)findViewById(R.id.viewFlipper2);
        banho = (ImageView)findViewById(R.id.ivbanho);
        banho.setOnClickListener(this);
        banheiro = (ImageView)findViewById(R.id.ivbanheiro);
        banheiro.setOnClickListener(this);
        cabelo = (ImageView)findViewById(R.id.ivcabelo);
        cabelo.setOnClickListener(this);
        brincar = (ImageView)findViewById(R.id.ivbrincar);
        brincar.setOnClickListener(this);
        comida = (ImageView)findViewById(R.id.ivcomida);
        comida.setOnClickListener(this);
        estudar = (ImageView)findViewById(R.id.ivestudar);
        estudar.setOnClickListener(this);
        dentes = (ImageView)findViewById(R.id.ivdentes);
        dentes.setOnClickListener(this);
        lavar = (ImageView)findViewById(R.id.ivlavar);
        lavar.setOnClickListener(this);

        next = (Button) findViewById(R.id.next);
        previous = (Button) findViewById(R.id.previous);
        next.setOnClickListener(this);
        previous.setOnClickListener(this);


        mplavar = MediaPlayer.create(this, R.raw.lavar);
        mpcomida = MediaPlayer.create(this, R.raw.comer);
        mpdentes = MediaPlayer.create(this, R.raw.escovar);
        mpbrincar = MediaPlayer.create(this, R.raw.brincar);
        mpbanho = MediaPlayer.create(this, R.raw.tomar);
        mpcabelo = MediaPlayer.create(this, R.raw.pentear);
        mpestudar = MediaPlayer.create(this, R.raw.estudar);
        mpbanheiro = MediaPlayer.create(this, R.raw.banheiro);
    }

    //método para passar tela
    @Override
    protected void onSwipeRight() {
        viewFlipper.showNext();
        viewFlipper2.showNext();
    }

    //método para voltar tela
    @Override
    protected void onSwipeLeft() {
        viewFlipper.showPrevious();
        viewFlipper2.showPrevious();

    }

    //as imagens estão divididas em 2 flip view, um paraca cada lado da tela

    @Override
    public void onClick(View v) {
        if (v == next) {
            //atribuindo valor a variavel r de acordo com o click na imagem

            int c =viewFlipper.getDisplayedChild();
            if(c==0)
            {
                r = 1;
            }
            else if(c == 1)
            {
                r = 2;
            }
            else if(c == 2)
            {
                r = 3;
            }
            else if(c == 3)
            {
                r = 4;
            }

            //usando método intent para pegar a foto da galeria
            Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, RESULT_LOAD_IMAGE);

        }
        else if (v == previous) {
            //atribuindo valor a variavel r de acordo com o click na imagem

            int c1 =viewFlipper.getDisplayedChild();
            if(c1==0)
            {
                r = 5;
            }
            else if(c1 == 1)
            {
                r = 6;
            }
            else if(c1 == 2)
            {
                r = 7;
            }
            else if(c1 == 3)
            {
                r = 8;
            }
            //usando método intent para pegar a foto da galeria

            Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, RESULT_LOAD_IMAGE);
        }

        //definindo o audio que será tocado no click e se nenhum audio está em execução
        if(v == banheiro && !mplavar.isPlaying())
        {
            mpbanheiro.start();
        }
        else if(v == banho && !mpdentes.isPlaying())
        {
            mpbanho.start();
        }
        else if(v == comida && !mpestudar.isPlaying())
        {
            mpcomida.start();
        }
        else if(v == cabelo && !mpbrincar.isPlaying())
        {
            mpcabelo.start();
        }
        else if(v == dentes && !mpbanho.isPlaying())
        {
            mpdentes.start();
        }
        else if(v == estudar && !mpcomida.isPlaying())
        {
            mpestudar.start();
        }
        else if(v == lavar && !mpbanheiro.isPlaying())
        {
            mplavar.start();
        }
        else if(v == brincar && !mpcabelo.isPlaying())
        {
            mpbrincar.start();
        }
    }

    //método para colcar a imagem no imageview
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //vendo se alguma imagem foi selecionada
        Bundle bundle = data.getExtras();
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            selectedImage = data.getData();//pegando o endereço da imagem
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();

            //colacando a imagem no imageview de acordo com o clik do usuário
            //por isso que dependendo do click é atribuido um valor diferente para a variavel r
            if (r == 1) {
                brincar.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                brincar.setImageURI(selectedImage);
            } else if (r == 2) {
                comida.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                comida.setImageURI(selectedImage);
            } else if (r == 3) {
                banho.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                banho.setImageURI(selectedImage);
            } else if (r == 4) {
                banheiro.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                banheiro.setImageURI(selectedImage);
            } else if (r == 5) {
                cabelo.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                cabelo.setImageURI(selectedImage);
            } else if (r == 6) {
                estudar.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                estudar.setImageURI(selectedImage);
            } else if (r == 7) {
                dentes.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                dentes.setImageURI(selectedImage);
            } else if (r == 8) {
                lavar.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                lavar.setImageURI(selectedImage);
            }


        }
    }

}


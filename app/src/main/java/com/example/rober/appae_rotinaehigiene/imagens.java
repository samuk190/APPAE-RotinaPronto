package com.example.rober.appae_rotinaehigiene;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static java.util.logging.Logger.global;


public class imagens extends SwipeAc implements View.OnClickListener {

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
    Integer valor;
    final int REQUEST_CODE_GALLERY = 999;

    public static SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagens);
        init();
        sqLiteHelper = new SQLiteHelper(this, "ATIVIDADEDB.sqlite", null, 1);
        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS ATIVIDADE(Id INTEGER PRIMARY KEY, IMAGEM BLOB)");
        abrirPadrao();


        mplavar = MediaPlayer.create(this, R.raw.lavar);
        mpcomida = MediaPlayer.create(this, R.raw.comer);
        mpdentes = MediaPlayer.create(this, R.raw.escovar);
        mpbrincar = MediaPlayer.create(this, R.raw.brincar);
        mpbanho = MediaPlayer.create(this, R.raw.tomar);
        mpcabelo = MediaPlayer.create(this, R.raw.pentear);
        mpestudar = MediaPlayer.create(this, R.raw.estudar);
        mpbanheiro = MediaPlayer.create(this, R.raw.banheiro);
       previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer c = viewFlipper.getDisplayedChild();
        if(c==0){
                    valor = 0;
                    ActivityCompat.requestPermissions(
                            imagens.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_CODE_GALLERY
                    );
                }

                if (c==1) {
                    valor = 6;
                    ActivityCompat.requestPermissions(
                            imagens.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_CODE_GALLERY
                    );
                }if (c==2) {
                    valor = 4;
                    ActivityCompat.requestPermissions(
                            imagens.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_CODE_GALLERY
                    );
                }if (c==3) {
                    valor = 2;
                    ActivityCompat.requestPermissions(
                            imagens.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_CODE_GALLERY
                    );
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int c = viewFlipper.getDisplayedChild();

if(c==0){

                    valor = 1;
                    ActivityCompat.requestPermissions(
                            imagens.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_CODE_GALLERY
                    );
                }
                if (c == 1) {
                    valor = 7;
                    ActivityCompat.requestPermissions(
                            imagens.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_CODE_GALLERY
                    );
                }if (c==2) {
                    valor = 5;
                    ActivityCompat.requestPermissions(
                            imagens.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_CODE_GALLERY
                    );
                }if (c==3) {
                    valor = 3;
                    ActivityCompat.requestPermissions(
                            imagens.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_CODE_GALLERY
                    );
                }
            }
        });
    }

    //método para passar tela
    @Override
    protected void onSwipeRight() {

        viewFlipper.showNext();

        viewFlipper2.showNext();

        int c = viewFlipper.getDisplayedChild();
        Toast.makeText(getApplicationContext(), "Página " +String.valueOf(c),
                Toast.LENGTH_SHORT).show();


    }

    //método para voltar tela
    @Override
    protected void onSwipeLeft() {
        viewFlipper.showPrevious();
        viewFlipper2.showPrevious();
        int c = viewFlipper.getDisplayedChild();
        Toast.makeText(getApplicationContext(), "Página "+String.valueOf(c),
                Toast.LENGTH_SHORT).show();
    }

    //as imagens estão divididas em 2 flip view, um para cada lado da tela

    @Override
    public void onClick(View v) {
        if (v == next) {
            //atribuindo valor a variavel r de acordo com o click na imagem

            int c = viewFlipper.getDisplayedChild();
            if (c == 0) {
                r = 1;
            } else if (c == 1) {
                r = 2;
            } else if (c == 2) {
                r = 3;
            } else if (c == 3) {
                r = 4;
            }

            //usando método intent para pegar a foto da galeria


        } else if (v == previous) {
            //atribuindo valor a variavel r de acordo com o click na imagem

            int c1 = viewFlipper.getDisplayedChild();
            if (c1 == 0) {
                r = 5;
            } else if (c1 == 1) {
                r = 6;
            } else if (c1 == 2) {
                r = 7;
            } else if (c1 == 3) {
                r = 8;
            }

        }

        //definindo o audio que será tocado no click e se nenhum audio está em execução
        if (v == banheiro && !mplavar.isPlaying()) {
            mpbanheiro.start();
        } else if (v == banho && !mpdentes.isPlaying()) {
            mpbanho.start();
        } else if (v == comida && !mpestudar.isPlaying()) {
            mpcomida.start();
        } else if (v == cabelo && !mpbrincar.isPlaying()) {
            mpcabelo.start();
        } else if (v == dentes && !mpbanho.isPlaying()) {
            mpdentes.start();
        } else if (v == estudar && !mpcomida.isPlaying()) {
            mpestudar.start();
        } else if (v == lavar && !mpbanheiro.isPlaying()) {
            mplavar.start();
        } else if (v == brincar && !mpcabelo.isPlaying()) {
            mpbrincar.start();
        }
    }

    //método para colcar a imagem no imageview
   /* @Override
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
    */
    public void abrirPadrao() {


        Cursor c = imagens.sqLiteHelper.getData("SELECT IMAGEM FROM ATIVIDADE where id=0");

        if (c.getCount() > 0) {
            c.moveToFirst();
            do {

                byte[] blob = c.getBlob(0);

                Bitmap b = BitmapFactory.decodeByteArray(blob, 0, blob.length);

                cabelo.setImageBitmap(b);

            } while (c.moveToNext());

        }
        c.close();

        Cursor cl = imagens.sqLiteHelper.getData("SELECT IMAGEM FROM ATIVIDADE where id=1");

        if (cl.getCount() > 0) {
            cl.moveToFirst();
            do {

                byte[] blob = cl.getBlob(0);

                Bitmap b = BitmapFactory.decodeByteArray(blob, 0, blob.length);

                brincar.setImageBitmap(b);

            } while (cl.moveToNext());

        }
        cl.close();

        Cursor comidas = imagens.sqLiteHelper.getData("SELECT IMAGEM FROM ATIVIDADE where id=2");

        if (comidas.getCount() > 0) {
            comidas.moveToFirst();
            do {

                byte[] blob = comidas.getBlob(0);

                Bitmap b = BitmapFactory.decodeByteArray(blob, 0, blob.length);

                lavar.setImageBitmap(b);

            } while (comidas.moveToNext());

        }
        comidas.close();

        Cursor estuda = imagens.sqLiteHelper.getData("SELECT IMAGEM FROM ATIVIDADE where id=3");

        if (estuda.getCount() > 0) {
            estuda.moveToFirst();
            do {

                byte[] blob = estuda.getBlob(0);

                Bitmap b = BitmapFactory.decodeByteArray(blob, 0, blob.length);

                banheiro.setImageBitmap(b);

            } while (estuda.moveToNext());

        }
        estuda.close();
        Cursor dente = imagens.sqLiteHelper.getData("SELECT IMAGEM FROM ATIVIDADE where id=4");

        if (dente.getCount() > 0) {
            dente.moveToFirst();
            do {

                byte[] blob = dente.getBlob(0);

                Bitmap b = BitmapFactory.decodeByteArray(blob, 0, blob.length);

                dentes.setImageBitmap(b);

            } while (dente.moveToNext());

        }
        dente.close();
        Cursor lava = imagens.sqLiteHelper.getData("SELECT IMAGEM FROM ATIVIDADE where id=5");

        if (lava.getCount() > 0) {
            lava.moveToFirst();
            do {

                byte[] blob = lava.getBlob(0);

                Bitmap b = BitmapFactory.decodeByteArray(blob, 0, blob.length);

                banho.setImageBitmap(b);

            } while (lava.moveToNext());

        }
        lava.close();
        Cursor banh = imagens.sqLiteHelper.getData("SELECT IMAGEM FROM ATIVIDADE where id=6");

        if (banh.getCount() > 0) {
            banh.moveToFirst();
            do {

                byte[] blob = banh.getBlob(0);

                Bitmap b = BitmapFactory.decodeByteArray(blob, 0, blob.length);

                estudar.setImageBitmap(b);

            } while (banh.moveToNext());

        }
        banh.close();

        Cursor banheir = imagens.sqLiteHelper.getData("SELECT IMAGEM FROM ATIVIDADE where id=7");

        if (banheir.getCount() > 0) {
            banheir.moveToFirst();
            do {

                byte[] blob = banheir.getBlob(0);

                Bitmap b = BitmapFactory.decodeByteArray(blob, 0, blob.length);

                comida.setImageBitmap(b);

            } while (banheir.moveToNext());

        }
        banheir.close();
    }

    ;

    private void init() {
        next = (Button) findViewById(R.id.next);
        previous = (Button) findViewById(R.id.previous);
        //atribuindo valores as variaveis
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        viewFlipper2 = (ViewFlipper) findViewById(R.id.viewFlipper2);
        banho = (ImageView) findViewById(R.id.ivbanho);
        banho.setOnClickListener(this);
        banheiro = (ImageView) findViewById(R.id.ivbanheiro);
        banheiro.setOnClickListener(this);
        cabelo = (ImageView) findViewById(R.id.ivcabelo);
        cabelo.setOnClickListener(this);
        brincar = (ImageView) findViewById(R.id.ivbrincar);
        brincar.setOnClickListener(this);
        comida = (ImageView) findViewById(R.id.ivcomida);
        comida.setOnClickListener(this);
        estudar = (ImageView) findViewById(R.id.ivestudar);
        estudar.setOnClickListener(this);
        dentes = (ImageView) findViewById(R.id.ivdentes);
        dentes.setOnClickListener(this);
        lavar = (ImageView) findViewById(R.id.ivlavar);
        lavar.setOnClickListener(this);

      /*  btnEscolher = (Button) findViewById(R.id.btnEscolher);
        btnEscolher2 = (Button) findViewById(R.id.btnEscolher2);
        //  btnAdd = (Button) findViewById(R.id.btnAdd);
        // btnList = (Button) findViewById(R.id.btnList);
        ImageView = (ImageView) findViewById(R.id.imageView);
        ImageView2 = (ImageView) findViewById(R.id.imageView2); */
    }

    public static byte[] ImageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CODE_GALLERY) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);

            } else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (valor == 0) {

            if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
                Uri uri = data.getData();

                try {

                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    cabelo.setImageBitmap(bitmap);
                    sqLiteHelper.inserirDados(
                            ImageViewToByte(cabelo)
                            , valor);
                    Toast.makeText(getApplicationContext(), "Adicionado com sucesso!", Toast.LENGTH_SHORT).show();


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

            super.onActivityResult(requestCode, resultCode, data);
        }
        if (valor == 1) {
            if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
                Uri uri = data.getData();

                try {

                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    brincar.setImageBitmap(bitmap);
                    sqLiteHelper.inserirDados(
                            ImageViewToByte(brincar),
                            valor);
                    Toast.makeText(getApplicationContext(), "Adicionado com sucesso!", Toast.LENGTH_SHORT).show();


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

            super.onActivityResult(requestCode, resultCode, data);
        }
        if (valor == 2) {
            if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
                Uri uri = data.getData();

                try {

                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    lavar.setImageBitmap(bitmap);
                    sqLiteHelper.inserirDados(
                            ImageViewToByte(lavar),
                            valor);
                    Toast.makeText(getApplicationContext(), "Adicionado com sucesso!", Toast.LENGTH_SHORT).show();


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

            super.onActivityResult(requestCode, resultCode, data);

        }
        if (valor == 3) {
            if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
                Uri uri = data.getData();

                try {

                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    banheiro.setImageBitmap(bitmap);
                    sqLiteHelper.inserirDados(
                            ImageViewToByte(banheiro),
                            valor);
                    Toast.makeText(getApplicationContext(), "Adicionado com sucesso!", Toast.LENGTH_SHORT).show();


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

            super.onActivityResult(requestCode, resultCode, data);


        }
        if (valor == 4) {
            if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
                Uri uri = data.getData();

                try {

                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    dentes.setImageBitmap(bitmap);
                    sqLiteHelper.inserirDados(
                            ImageViewToByte(dentes),
                            valor);
                    Toast.makeText(getApplicationContext(), "Adicionado com sucesso!", Toast.LENGTH_SHORT).show();


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

            super.onActivityResult(requestCode, resultCode, data);
        }
        if (valor == 5) {
            if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
                Uri uri = data.getData();

                try {

                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    banho.setImageBitmap(bitmap);
                    sqLiteHelper.inserirDados(
                            ImageViewToByte(banho),
                            valor);
                    Toast.makeText(getApplicationContext(), "Adicionado com sucesso!", Toast.LENGTH_SHORT).show();


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

            super.onActivityResult(requestCode, resultCode, data);
        }  if (valor == 6) {
            if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
                Uri uri = data.getData();

                try {

                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    estudar.setImageBitmap(bitmap);
                    sqLiteHelper.inserirDados(
                            ImageViewToByte(estudar),
                            valor);
                    Toast.makeText(getApplicationContext(), "Adicionado com sucesso!", Toast.LENGTH_SHORT).show();


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

            super.onActivityResult(requestCode, resultCode, data);
        } if (valor == 7) {
            if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
                Uri uri = data.getData();

                try {

                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    comida.setImageBitmap(bitmap);
                    sqLiteHelper.inserirDados(
                            ImageViewToByte(comida),
                            valor);
                    Toast.makeText(getApplicationContext(), "Adicionado com sucesso!", Toast.LENGTH_SHORT).show();


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}


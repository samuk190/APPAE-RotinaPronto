package com.example.rober.appae_rotinaehigiene;

/**
 * Created by Quoc Nguyen on 13-Dec-16.
 */

public class Atividade {
    private int id;

    private byte[] Imagem;

    public Atividade(String name, String price, byte[] Imagem, int id) {

        this.Imagem = Imagem;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public byte[] getImagem() {
        return Imagem;
    }

    public void setImagem(byte[] Imagem) {
        this.Imagem = Imagem;
    }
}

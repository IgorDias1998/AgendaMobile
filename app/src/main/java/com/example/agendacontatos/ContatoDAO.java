package com.example.agendacontatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContatoDAO extends SQLiteOpenHelper {
    public static final String NOME_BANCO = "agendacontatos";
    public static final int VERSAO_BANCO = 1;
    public static final String TABELA_CONTATO = "contato";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_NOME = "nome";
    public static final String COLUNA_CELULAR = "celular";
    public static final String COLUNA_EMAIL = "email";

    public ContatoDAO(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABELA_CONTATO + " (" +
                COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUNA_NOME + " TEXT NOT NULL, " +
                COLUNA_CELULAR + " TEXT NOT NULL, " +
                COLUNA_EMAIL + " TEXT NOT NULL) "
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void salvarContato(Contato contato) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valores;
        valores = new ContentValues();
        valores.put(COLUNA_NOME, contato.getNome());
        valores.put(COLUNA_CELULAR, contato.getCelular());
        valores.put(COLUNA_EMAIL, contato.getEmail());

        db.insert(TABELA_CONTATO, null, valores);
        db.close();
    }

    public void atualizarContato(Contato contato) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valores;
        valores = new ContentValues();
        valores.put(COLUNA_NOME, contato.getNome());
        valores.put(COLUNA_CELULAR, contato.getCelular());
        valores.put(COLUNA_EMAIL, contato.getEmail());

        String parametro[] = {String.valueOf(contato.getId())};
        db.update(TABELA_CONTATO, valores, "id = ?", parametro);
        db.close();
    }

    public void excluirContato(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String parametro[] = {String.valueOf(id)};
        db.delete(TABELA_CONTATO, "id = ?", parametro);
        db.close();
    }

    public Contato consultarContatoPorNome(String nome) {
        Contato contato;
        contato = null;
        String parametro[] = { nome };
        String campos[] = {"id, nome, celular, email"};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.query(TABELA_CONTATO,
                            campos,
                            "nome = ?",
                            parametro,
                            null,
                            null,
                            null,
                            null);
        if (cr.moveToFirst()) {
            contato = new Contato();
            contato.setId(cr.getInt(0));
            contato.setNome(cr.getString(1));
            contato.setCelular(cr.getString(2));
            contato.setEmail(cr.getString(3));
        }

        db.close();
        return contato;
    }
}

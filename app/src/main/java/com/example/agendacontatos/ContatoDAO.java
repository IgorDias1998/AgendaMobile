package com.example.agendacontatos;

import android.content.Context;
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
        sqLiteDatabase.execSQL("CREATE TABLE " + TABELA_CONTATO + "(" +
                COLUNA_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUNA_NOME + "TEXT NOT NULL, " +
                COLUNA_CELULAR + "TEXT NOT NULL, " +
                COLUNA_EMAIL + "TEXT NOT NULL) "
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}

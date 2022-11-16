package com.example.gymprogress;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class exerciseDB extends SQLiteOpenHelper {

    public static final String TAG = "sql";
    public static final String db_name = "myexercisedatabase.db";
    public static final int db_version = 1;
    public static final String TABLE_NAME = "exercises";
    public static final String COLUNA0 = "_id";
    public static final String COLUNA1 = "exercise_name";
    public static final String COLUNA2 = "exercise_weight";
    public static final String COLUNA3 = "exercise_-type";

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " +TABLE_NAME + " ("
                    + COLUNA0 +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUNA1 + " TEXT,"
                    + COLUNA2 + " TEXT,"
                    + COLUNA3 + " TEXT )";

    public exerciseDB(ExibePeito context)
    {
        super(context, db_name, null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
        Log.d(TAG, "Tabela"+TABLE_NAME+" criada com sucesso");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //nao utilizado
    }

    public long saveexercise(exercise exercise){
        //lê o valor do id do objeto ( Se id = 0 cadastro/ Se id!=0 atualização)
        long id = exercise.get_id();
        //abre a conexão com o banco de dados
        SQLiteDatabase db = getWritableDatabase();//abre a conexão com o banco de dados
        try{

            //cria a variável que aponta para o objeto ContentValues()  e que representa o conteudo do registro a ser alterado ou criado
            //em um formato que o banco de dados entende.
            ContentValues values = new ContentValues();
            //transfere os valores das variáveis de instancia do objeto para a variável valor na forma (chave, valor)
            values.put(COLUNA1,exercise.getExercise_name());
            values.put(COLUNA2, exercise.getExercise_weight());
            values.put(COLUNA3, exercise.getExercise_type());
            if(id!=0){//se já existe este contato e queremos simplesmente atualizá-lo
                String _id = String.valueOf(id);
                String[] whereArgs = new String[]{_id};
                int count = db.update(TABLE_NAME, values, "_id =?",new String[]{String.valueOf(id)});
                return count; // retorna o numero de linhas alteradas.

            }
            else{//se não existe o contato e vamos incluí-lo na tabela.
                id = db.insert(TABLE_NAME,null,values);
                return id; //retorna o ID da nova linha inserida ou -1 se ocorrer erro

            }
        }finally{
            db.close();//fecha a conexão
        }
    }

    @SuppressLint("Range")
    public ArrayList<exercise> showallexercises() {
        SQLiteDatabase db = getWritableDatabase();
        //Declarando o ArrayList de objetos do tipo Contato.
        ArrayList<exercise> lista = new ArrayList<>();
        try {
            //colocando o valor dos campos em null, retorna todos os registros da tabela.
            Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
            if (c.moveToFirst()) {//move o cursor para o primeiro registro
                //laço do while para correr todos os registros e formar o Array que representa a lista de registros.
                do {
                    long id = c.getLong(c.getColumnIndex("_id"));
                    String exercise_name = c.getString(c.getColumnIndex("exercise_name"));
                    String exercise_weight = c.getString(c.getColumnIndex("exercise_weight"));
                    String exercise_type = c.getString(c.getColumnIndex("exercise_type"));

                    exercise currentExercise = new exercise(id, exercise_name, exercise_weight, exercise_type);
                    lista.add(currentExercise);

                } while (c.moveToNext());//move para a próxima posição

            }
            return lista;//retorna o Array contendo os contatos
        } finally {
            db.close();//fecha a conexão
        }
    }

    @SuppressLint("Range")
    public exercise searchexercise(String typeExercise) {
        SQLiteDatabase db = getWritableDatabase();
        long id;
        String exercise_name;
        String exercise_weight;
        String exercise_type;

        try {
            Cursor c = db.query(TABLE_NAME, null,"exercise_name=?", new String[]{typeExercise}, null, null, null, null);
            if(c.moveToFirst()) {//verifica se o contato existe, se sim extrai os valores para criação  um objeto Contato com os valores
                id = c.getLong(c.getColumnIndex("_id"));
                exercise_name = c.getString(c.getColumnIndex("exercise_name"));
                exercise_weight = c.getString(c.getColumnIndex("exercise_weight"));
                exercise_type = c.getString(c.getColumnIndex("exercise_type"));
                // Contato contato = new Contato(id, nome, telefone, email);
            }
            //Se não for encontrado, define  valores dummy para criação de um objeto
            //pois o método retorna um objeto do tipo contato
            else{
                id = 0;
                exercise_name = "dummy";
                exercise_weight = "0" ;
                exercise_type = "xxxxx";
            }
            exercise exercises = new exercise(id, exercise_name, exercise_weight, exercise_type);
            return exercises;
        } finally {
            db.close();
        }
    }
}

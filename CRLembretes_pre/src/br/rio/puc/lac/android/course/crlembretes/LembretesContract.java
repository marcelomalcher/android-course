package br.rio.puc.lac.android.course.crlembretes;

import android.net.Uri;

public class LembretesContract {

  private static final String AUTHORITY =
    "br.rio.puc.lac.android.course.lembretescp";
  private static final String BASE_PATH = "lembretes";

  public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
    + "/" + BASE_PATH);

  public class Lembrete {
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CATEGORY = "categoria";
    public static final String COLUMN_SUMMARY = "resumo";
    public static final String COLUMN_DESCRIPTION = "descricao";
  }

}